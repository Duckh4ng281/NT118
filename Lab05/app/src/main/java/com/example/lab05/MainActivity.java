package com.example.lab05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;
    private static final int SMS_PERMISSION_CODE = 101;


    public void processReceive(Context context, Intent intent) {
        Toast.makeText(context, getString(R.string.you_have_a_new_message),
                Toast.LENGTH_LONG).show();

        TextView tvContent = findViewById(R.id.tv_content);
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }

        Object[] messages = (Object[]) bundle.get("pdus");
        if (messages == null) {
            return;
        }

        String sms = "";
        for (Object message : messages) {
            SmsMessage smsMsg;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                smsMsg = SmsMessage.createFromPdu((byte[]) message, bundle.getString("format"));
            } else {
                smsMsg = SmsMessage.createFromPdu((byte[]) message);
            }
            if (smsMsg != null) {
                String msgBody = smsMsg.getMessageBody();
                String address = smsMsg.getDisplayOriginatingAddress();
                sms += address + ":\n" + msgBody + "\n";
            }
        }
        tvContent.setText(sms);
    }


    private void initBroadcastReceiver(){
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceive(context, intent);
            }
        };
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (broadcastReceiver == null)
            initBroadcastReceiver();
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS}, SMS_PERMISSION_CODE);
        } else {
            initBroadcastReceiver();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initBroadcastReceiver();
            } else {
                Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_LONG).show();
            }
        }
    }
}