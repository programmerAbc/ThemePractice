package com.practice.themepractice;

import android.animation.Animator;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button lightThemeBtn;
    Button darkThemeBtn;
    View container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtil.useTheme(this);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        lightThemeBtn = findViewById(R.id.lightThemeBtn);
        darkThemeBtn = findViewById(R.id.darkThemeBtn);
        lightThemeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemeUtil.switchToTheme(MainActivity.this, R.style.LightAppTheme);
            }
        });
        darkThemeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemeUtil.switchToTheme(MainActivity.this, R.style.DarkAppTheme);
            }
        });
        container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                performAnimator();
                if (Build.VERSION.SDK_INT < 16) {
                    container.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    container.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }


    public void performAnimator() {
        int cx = darkThemeBtn.getLeft() + darkThemeBtn.getWidth() / 2;
        int cy = darkThemeBtn.getTop() + darkThemeBtn.getHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(container.getWidth(), container.getHeight())*2;

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(container, cx, cy, 0, finalRadius);
        anim.setDuration(1000);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                container.setAlpha(1);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }
}
