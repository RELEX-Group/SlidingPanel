package ru.relex.slidingpanel.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import ru.relex.slidingpanel.R;
import ru.relex.slidingpanel.logic.SlidingPanelAnimator;
import ru.relex.slidingpanel.utils.Utilities;

/**
 * Created by Turkin A. on 13.11.2017.
 */

public class SlidingPanelLinear extends LinearLayout {

    //Attributes
    private int attrSpeed = Utilities.DEFAULT_ANIMATION_SPEED;
    private String attrInterpolator = Utilities.DEFAULT_INTERPOLATOR;
    private String attrDirection = Utilities.DEFAULT_DIRECTION;

    //Variables
    private boolean isOnTheScreen = false;
    private Animation fadeAnimation = null;

    public SlidingPanelLinear(Context context) {
        super(context);
    }

    public SlidingPanelLinear(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.SettingsPanel,
                0, 0);

        attrSpeed = typedArray.getInt(R.styleable.SettingsPanel_speed, Utilities.DEFAULT_ANIMATION_SPEED);
        attrInterpolator = typedArray.getNonResourceString(R.styleable.SettingsPanel_interpolator);
        attrDirection = typedArray.getNonResourceString(R.styleable.SettingsPanel_direction);

        typedArray.recycle();

        fadeAnimation = AnimationUtils.loadAnimation(context, R.anim.fade);
    }

    public void slide() {
        isOnTheScreen = !isOnTheScreen;
        SlidingPanelAnimator animator = new SlidingPanelAnimator();
        animator.playAnimation(this,
                attrSpeed, isOnTheScreen, fadeAnimation, attrDirection, attrInterpolator);
    }
}
