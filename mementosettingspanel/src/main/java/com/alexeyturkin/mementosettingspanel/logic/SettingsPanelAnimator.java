package com.alexeyturkin.mementosettingspanel.logic;

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
 * Created by Turkin A. on 14.11.2017.
 */

public final class SettingsPanelAnimator {

    public void playAnimation(final View targetSettingsPanel,
                              int speed,
                              boolean isOnTheScreen,
                              Animation fadeAnimation,
                              String direction,
                              String interpolator) {
        TranslateAnimation translateAnimation;

        AnimationSet animationSet = setInterpolator(interpolator);

        if (isOnTheScreen) {
            targetSettingsPanel.setVisibility(View.VISIBLE);
            targetSettingsPanel.setFocusable(true);
            targetSettingsPanel.setFocusableInTouchMode(true);

            translateAnimation = (TranslateAnimation) setEnterDirectionTranslateAnimation(targetSettingsPanel, direction);
        } else {
            targetSettingsPanel.setFocusable(false);
            targetSettingsPanel.setFocusableInTouchMode(false);
            translateAnimation = (TranslateAnimation) setExitDirectionTranslateAnimation(targetSettingsPanel, direction);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    targetSettingsPanel.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            animationSet.addAnimation(fadeAnimation);
        }

        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(speed);
        targetSettingsPanel.startAnimation(animationSet);
    }

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
