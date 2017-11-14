package com.alexeyturkin.mementosettingspanel.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.alexeyturkin.mementosettingspanel.R;
import com.alexeyturkin.mementosettingspanel.logic.SettingsPanelAnimator;
import com.alexeyturkin.mementosettingspanel.utils.Utilities;

/**
 * Created by Turkin A. on 13.11.2017.
 */

public class SettingsPanelRelative extends RelativeLayout {

    //Attributes
    private int attrSpeed = Utilities.DEFAULT_ANIMATION_SPEED;
    private String attrInterpolator = Utilities.DEFAULT_INTERPOLATOR;
    private String attrDirection = Utilities.DEFAULT_DIRECTION;

    //Variables
    private boolean isOnTheScreen = false;
    private Animation fadeAnimation = null;

    public SettingsPanelRelative(Context context) {
        super(context);
    }

    public SettingsPanelRelative(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SettingsPanel,
                0, 0);

        attrSpeed = a.getInt(R.styleable.SettingsPanel_speed, Utilities.DEFAULT_ANIMATION_SPEED);

        a.recycle();

        fadeAnimation = AnimationUtils.loadAnimation(context, R.anim.fade);
    }

    public void slide() {
        isOnTheScreen = !isOnTheScreen;
        SettingsPanelAnimator animator = new SettingsPanelAnimator();
        animator.playAnimation(this,
                attrSpeed, isOnTheScreen, fadeAnimation, attrDirection, attrInterpolator);
    }
}
