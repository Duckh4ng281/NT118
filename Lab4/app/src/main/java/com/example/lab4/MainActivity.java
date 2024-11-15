package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnFadeInXml, btnFadeInCode, btnFadeOutXml, btnFadeOutCode,
            btnBlinkXml, btnBlinkCode, btnZoomInXml, btnZoomInCode,
            btnZoomOutXml, btnZoomOutCode, btnRotateXml, btnRotateCode,
            btnMoveXml, btnMoveCode, btnSlideUpXml, btnSlideUpCode,
            btnBounceXml, btnBounceCode, btnCombineXml, btnCombineCode;
    private ImageView ivUitLogo;
    private Animation.AnimationListener animationListener;



    private void findViewsByIds() {
        ivUitLogo = findViewById(R.id.iv_uit_logo);
        btnFadeInXml = findViewById(R.id.btn_fade_in_xml);
        btnFadeInCode = findViewById(R.id.btn_fade_in_code);
        btnFadeOutXml = findViewById(R.id.btn_fade_out_xml);
        btnFadeOutCode = findViewById(R.id.btn_fade_out_code);
        btnBlinkXml = findViewById(R.id.btn_blink_xml);
        btnBlinkCode = findViewById(R.id.btn_blink_code);
        btnZoomInXml = findViewById(R.id.btn_zoom_in_xml);
        btnZoomInCode = findViewById(R.id.btn_zoom_in_code);
        btnZoomOutXml = findViewById(R.id.btn_zoom_out_xml);
        btnZoomOutCode = findViewById(R.id.btn_zoom_out_code);
        btnRotateXml = findViewById(R.id.btn_rotate_xml);
        btnRotateCode = findViewById(R.id.btn_rotate_code);
        btnMoveXml = findViewById(R.id.btn_move_xml);
        btnMoveCode = findViewById(R.id.btn_move_code);
        btnSlideUpXml = findViewById(R.id.btn_slide_up_xml);
        btnSlideUpCode = findViewById(R.id.btn_slide_up_code);
        btnBounceXml = findViewById(R.id.btn_bounce_xml);
        btnBounceCode = findViewById(R.id.btn_bounce_code);
        btnCombineXml = findViewById(R.id.btn_combine_xml);
        btnCombineCode = findViewById(R.id.btn_combine_code);
    }

    private void initVariables() {
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation Stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
    }

    private void handleClickAnimationXml(Button btn, int animResource) {
        final Animation animation = AnimationUtils.loadAnimation(this, animResource);
        animation.setAnimationListener(animationListener);
        btn.setOnClickListener(v -> ivUitLogo.startAnimation(animation));
    }

    private void handleClickAnimationCode(Button btn, final Animation animation) {
        btn.setOnClickListener(v -> ivUitLogo.startAnimation(animation));
    }

    private Animation initBlinkAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(300);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(3);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initBounceAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setInterpolator(this, android.R.interpolator.bounce);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initCombineAnimation() {
        AnimationSet set = new AnimationSet(true);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 3.0f, 1.0f, 3.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(4000);

        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setRepeatMode(Animation.RESTART);

        set.addAnimation(scaleAnimation);
        set.addAnimation(rotateAnimation);
        set.setFillAfter(true);
        set.setInterpolator(this, android.R.interpolator.linear);
        set.setAnimationListener(animationListener);

        return set;
    }

    private Animation initFadeInAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initFadeOutAnimation() {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initMoveAnimation() {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.75f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        animation.setDuration(800);
        animation.setFillAfter(true);
        animation.setInterpolator(this, android.R.interpolator.linear);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    // Rotate Animation
    private Animation initRotateAnimation() {
        RotateAnimation animation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(600);
        animation.setRepeatCount(2);
        animation.setRepeatMode(Animation.RESTART);
        animation.setInterpolator(this, android.R.interpolator.cycle);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    // Slide Up Animation
    private Animation initSlideUpAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    // Zoom In Animation
    private Animation initZoomInAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1.0f, 3.0f, 1.0f, 3.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    // Zoom Out Animation
    private Animation initZoomOutAnimation() {
        ScaleAnimation animation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsByIds();
        initVariables();
        setAnimationHandlers();

        ivUitLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewActivity = new Intent(MainActivity.this, NewActivity.class);
                startActivity(iNewActivity);

                // Apply slide animations for transition
                overridePendingTransition(R.anim.anim_back, R.anim.anim_new);
            }
        });

    }

    private void setAnimationHandlers() {
        handleClickAnimationXml(btnFadeInXml, R.anim.anim_fade_in);
        handleClickAnimationXml(btnFadeOutXml, R.anim.anim_fade_out);
        handleClickAnimationXml(btnBlinkXml, R.anim.anim_blink);
        handleClickAnimationXml(btnZoomInXml, R.anim.anim_zoom_in);
        handleClickAnimationXml(btnZoomOutXml, R.anim.anim_zoom_out);
        handleClickAnimationXml(btnRotateXml, R.anim.anim_rotate);
        handleClickAnimationXml(btnMoveXml, R.anim.anim_move);
        handleClickAnimationXml(btnSlideUpXml, R.anim.anim_slide_up);
        handleClickAnimationXml(btnBounceXml, R.anim.anim_bounce);
        handleClickAnimationXml(btnCombineXml, R.anim.anim_combine);

        handleClickAnimationCode(btnFadeInCode, initFadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode, initFadeOutAnimation());
        handleClickAnimationCode(btnBlinkCode, initBlinkAnimation());
        handleClickAnimationCode(btnZoomInCode, initZoomInAnimation());
        handleClickAnimationCode(btnZoomOutCode, initZoomOutAnimation());
        handleClickAnimationCode(btnRotateCode, initRotateAnimation());
        handleClickAnimationCode(btnMoveCode, initMoveAnimation());
        handleClickAnimationCode(btnSlideUpCode, initSlideUpAnimation());
        handleClickAnimationCode(btnBounceCode, initBounceAnimation());
        handleClickAnimationCode(btnCombineCode, initCombineAnimation());

    }

}
