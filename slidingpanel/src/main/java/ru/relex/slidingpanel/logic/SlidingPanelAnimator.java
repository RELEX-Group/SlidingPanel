package ru.relex.slidingpanel.logic;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Class-engine for view animations.
 *
 * @author Alexey Turkin, 2017.
 */
public final class SlidingPanelAnimator {

    /**
     * Plays animation and shows effects for target view.
     *
     * @param targetView    - target view for animation actions.
     * @param speed         - animation speed for translate animation and speed sets on targetView.
     * @param isOnTheScreen - boolean flag for detecting is targetView on the screen or not.
     * @param fadeAnimation - Animation object which contains fade animation values.
     * @param direction     - string value for translate animation direction.
     * @param interpolator  - string value for AnimationSet's interpolator.
     */
    public void playAnimation(final View targetView,
                              int speed,
                              boolean isOnTheScreen,
                              Animation fadeAnimation,
                              String direction,
                              String interpolator) {
        TranslateAnimation translateAnimation;

        AnimationSet animationSet = setInterpolator(interpolator);

        if (isOnTheScreen) {
            targetView.setVisibility(View.VISIBLE);
            targetView.setFocusable(true);
            targetView.setFocusableInTouchMode(true);

            translateAnimation = (TranslateAnimation) setEnterDirectionTranslateAnimation(targetView, direction);
        } else {
            targetView.setFocusable(false);
            targetView.setFocusableInTouchMode(false);
            translateAnimation = (TranslateAnimation) setExitDirectionTranslateAnimation(targetView, direction);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    targetView.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            animationSet.addAnimation(fadeAnimation);
        }

        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(speed);
        targetView.startAnimation(animationSet);
    }

    /**
     * Sets  the enter direction of translate animation for target view.
     *
     * @param targetView - apply direction to this view.
     * @param direction  - string value for animation's direction.
     * @return animation object with direction values.
     */
    private Animation setEnterDirectionTranslateAnimation(View targetView,
                                                          String direction) {
        Animation translateAnimation;
        switch (direction) {
            case "topToBottom":
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, -targetView.getHeight(), 0.0f);
                break;

            case "bottomToTop":
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, targetView.getHeight(), 0.0f);
                break;

            case "leftToRight":
                translateAnimation = new TranslateAnimation(-targetView.getWidth(), 0.0f, 0.0f, 0.0f);
                break;

            case "rightToLeft":
                translateAnimation = new TranslateAnimation(targetView.getWidth(), 0.0f, 0.0f, 0.0f);
                break;

            default:
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, -targetView.getHeight(), 0.0f);
                break;
        }

        return translateAnimation;
    }

    /**
     * Sets the exit direction of translate animation for target view.
     *
     * @param targetView - apply direction to this view.
     * @param direction  - string value for animation's direction.
     * @return animation object with direction values.
     */
    private Animation setExitDirectionTranslateAnimation(View targetView,
                                                         String direction) {
        Animation translateAnimation;
        switch (direction) {
            case "topToBottom":
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -targetView.getHeight());
                break;

            case "bottomToTop":
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, targetView.getHeight());
                break;

            case "leftToRight":
                translateAnimation = new TranslateAnimation(0.0f, -targetView.getWidth(), 0.0f, 0.0f);
                break;

            case "rightToLeft":
                translateAnimation = new TranslateAnimation(0.0f, targetView.getWidth(), 0.0f, 0.0f);
                break;

            default:
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -targetView.getHeight());
                break;
        }

        return translateAnimation;
    }

    /**
     * Sets interpolator to AnimationSet object.
     *
     * @param interpolator - string value, responsible for setting concrete interpolator to AnimationSet object.
     * @return AnimationSet object with applied interpolator.
     */
    private AnimationSet setInterpolator(String interpolator) {
        AnimationSet animationSet = new AnimationSet(true);

        switch (interpolator) {
            case "decelerate":
                animationSet.setInterpolator(new DecelerateInterpolator(1.0f));
                break;

            case "accelerate":
                animationSet.setInterpolator(new AccelerateInterpolator(1.0f));
                break;

            case "accelerateDecelerate":
                animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
                break;

            case "anticipate":
                animationSet.setInterpolator(new AnticipateInterpolator(1.0f));
                break;

            case "anticipateOvershoot":
                animationSet.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
                break;

            case "bounce":
                animationSet.setInterpolator(new BounceInterpolator());
                break;

            case "cycle":
                animationSet.setInterpolator(new CycleInterpolator(1.0f));
                break;

            case "overshoot":
                animationSet.setInterpolator(new OvershootInterpolator(1.0f));
                break;

            default:
                animationSet.setInterpolator(new LinearInterpolator());
                break;
        }

        return animationSet;
    }

}
