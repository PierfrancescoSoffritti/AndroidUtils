package com.pierfrancescosoffritti.utils.animations;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

/**
 * Utility class for animations
 * @author Pierfrancesco Soffritti
 */
public class AnimationsUtils {

    /**
     * fades a target View in and then out.
     * @param target View that will fade in/out
     * @param fadeInDuration duration of fade-in animation
     * @param fadeOutDuration duration of fade-out animation
     * @param fadeInOffset fade-in delay
     * @param fadeOutOffset fade-out delay
     * @param listener animation listener
     */
    public static void fadeInFadeOut(
            @Nullable final View target,
            final int fadeInDuration, final int fadeOutDuration,
            final int fadeInOffset, final int fadeOutOffset,
            @Nullable final Animation.AnimationListener listener) {

        if(target == null)
            return;

        Animation.AnimationListener fadeInListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(listener != null) listener.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        };

        Animation.AnimationListener fadeOutListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                if(listener != null) listener.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        };

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(fadeIn(target, fadeInDuration, fadeInOffset, fadeInListener));
        animationSet.addAnimation(fadeOut(target, fadeOutDuration, fadeInDuration + fadeInOffset + fadeOutOffset, fadeOutListener));

        target.startAnimation(animationSet);
    }

    public static @Nullable Animation fadeOut(@Nullable View target, int duration, int startOffset, @Nullable final Animation.AnimationListener listener) {
        if(target == null)
            return null;

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setStartOffset(startOffset);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(listener != null) listener.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(listener != null) listener.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if(listener != null) listener.onAnimationRepeat(animation);
            }
        });

        target.startAnimation(alphaAnimation);
        target.setVisibility(View.INVISIBLE);

        return alphaAnimation;
    }

    public static @Nullable Animation fadeIn(@Nullable View target, int duration, int startOffset, @Nullable final Animation.AnimationListener listener) {
        if(target == null)
            return null;

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setStartOffset(startOffset);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(listener != null) listener.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(listener != null) listener.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if(listener != null) listener.onAnimationRepeat(animation);

            }
        });

        target.startAnimation(alphaAnimation);
        target.setVisibility(View.VISIBLE);

        return alphaAnimation;
    }
}
