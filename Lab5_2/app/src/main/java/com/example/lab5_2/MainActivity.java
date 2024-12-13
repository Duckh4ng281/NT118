package com.example.lab5_2;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private PowerStateChangeReceiver powerStateChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tạo và đăng ký BroadcastReceiver
        powerStateChangeReceiver = new PowerStateChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(powerStateChangeReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (powerStateChangeReceiver != null) {
            unregisterReceiver(powerStateChangeReceiver);
        }
    }
}