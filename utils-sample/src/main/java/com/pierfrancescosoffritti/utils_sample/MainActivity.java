package com.pierfrancescosoffritti.utils_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;

import com.pierfrancescosoffritti.utils.animations.AnimationsUtils;
import com.pierfrancescosoffritti.utils.animations.RevealEffectHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fade
        // ----

        findViewById(R.id.fadeIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationsUtils.fadeIn(view, 500, 500, null);
            }
        });

        findViewById(R.id.fadeOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationsUtils.fadeOut(view, 500, 0, null);
            }
        });

        findViewById(R.id.fadeInFadeOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationsUtils.fadeInFadeOut(view, 500, 500, 500, 0, null);
            }
        });

        // reveal
        // ----
        findViewById(R.id.revealAndFadeOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RevealEffectHelper.supportRevealAndFadeOut(view, 0, 0, 500, 500, 100, Math.max(view.getHeight(), view.getWidth()), null);
            }
        });

        findViewById(R.id.reveal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RevealEffectHelper.supportReveal(view, 0, 0, 500, Math.max(view.getHeight(), view.getWidth()), null);
            }
        });

        findViewById(R.id.unreveal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                RevealEffectHelper.supportUnReveal(view, 0, 0, 500, Math.max(view.getHeight(), view.getWidth()), new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        if(view.getVisibility() != View.VISIBLE)
                            throw new IllegalStateException("view.getVisibility() != View.VISIBLE");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                            if(view.getVisibility() != View.INVISIBLE)
                                throw new IllegalStateException("view.getVisibility()) != View.INVISIBLE");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }
}
