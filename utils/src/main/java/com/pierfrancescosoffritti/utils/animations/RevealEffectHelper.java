package com.pierfrancescosoffritti.utils.animations;

import android.animation.Animator;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

/**
 * Helper class for reveal animations. Provides support for all Android versions.
 * @author Pierfrancesco Soffritti
 */
public class RevealEffectHelper {

    /**
     * <b>On Lollipop+</b> shows reveal + fadeout
     * <br/>
     * <b>On Pre-Lollipop</b> shows fade in + fadeout
     */
    public static void supportRevealAndFadeOut(
            @Nullable View target,
            int centerX, int centerY,
            int revealDuration, int fadeOutDuration,
            int fadeOutOffset,
            int finalRadius,
            @Nullable Animation.AnimationListener listener) {

        if(target == null)
            return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            revealEffectAndFadeOut(target, revealDuration, fadeOutDuration, fadeOutOffset, centerX, centerY, finalRadius, listener);
        else
            AnimationsUtils.fadeInFadeOut(target, revealDuration, fadeOutDuration, 0, fadeOutOffset, listener);
    }

    /**
     * <b>On Lollipop+</b> shows reveal
     * <br/>
     * <b>On Pre-Lollipop</b> shows fade in
     */
    public static void supportReveal(
            @Nullable View target,
            int centerX, int centerY,
            final int revealDuration,
            int finalRadius,
            @Nullable Animation.AnimationListener listener) {

        if(target == null)
            return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            revealEffect(target, centerX, centerY, revealDuration, finalRadius, listener);
        else
            AnimationsUtils.fadeIn(target, revealDuration, 0, listener);
    }

    /**
     * <b>On Lollipop+</b> shows un reveal
     * <br/>
     * <b>On Pre-Lollipop</b> fadeout
     */
    public static void supportUnReveal(
            @Nullable View target,
            int centerX, int centerY,
            int duration,
            int currentRadius,
            @Nullable Animation.AnimationListener listener) {

        if(target == null)
            return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            unRevealEffect(target, centerX, centerY, duration, currentRadius, listener);
        } else
            AnimationsUtils.fadeOut(target, duration, 0, listener);
    }

    private static void revealEffect(
            @NonNull View target,
            int centerX, int centerY,
            int duration,
            int finalRadius,
            @Nullable final Animation.AnimationListener listener) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(target, centerX, centerY, 0, finalRadius);
            revealAnimator.setDuration(duration);
            revealAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            revealAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    if (listener != null) listener.onAnimationStart(null);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if (listener != null) listener.onAnimationEnd(null);
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                    if (listener != null) listener.onAnimationRepeat(null);
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                }
            });
            target.setVisibility(View.VISIBLE);
            revealAnimator.start();
        }
    }

    private static void revealEffectAndFadeOut(
            @NonNull final View target,
            final int revealDuration, final int fadeOutDuration,
            final int fadeOutOffset,
            int centerX, int centerY,
            int finalRadius,
            @Nullable final Animation.AnimationListener listener) {

        Animation.AnimationListener revealListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                AnimationsUtils.fadeOut(target, fadeOutDuration, fadeOutOffset, listener);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                if(listener != null) listener.onAnimationStart(animation);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };

        revealEffect(target, centerX, centerY, revealDuration, finalRadius, revealListener);
    }

    private static void unRevealEffect(
            @NonNull final View target,
            int centerX, int centerY,
            int duration,
            int currentRadius,
            @Nullable final Animation.AnimationListener listener) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(target, centerX, centerY, currentRadius, 0);
            revealAnimator.setDuration(duration);
            revealAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            revealAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    if (listener != null) listener.onAnimationStart(null);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    target.setVisibility(View.INVISIBLE);

                    if (listener != null) listener.onAnimationEnd(null);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    if (listener != null) listener.onAnimationRepeat(null);
                }

                @Override
                public void onAnimationCancel(Animator animation) {}
            });

            revealAnimator.start();
        }
    }
}
