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
 * Custom container-view class.
 *
 * @author Alexey Turkin, 2017.
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

    /**
     * Default constructor for this custom view class.
     *
     * @param context - activity or application context for obtaining custom attributes.
     * @param attrs   - AttributeSet parameter which allows to obtain attributes and use them in java code.
     */
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

    /**
     * Plays whole sliding animation for this custom view using SlidingPanelAnimator object inside.
     */
    public void slide() {
        isOnTheScreen = !isOnTheScreen;
        if (isOnTheScreen) {
            setFocusable(true);
            setFocusableInTouchMode(true);
            setClickable(true);
        } else {
            this.clearFocus();
        }
        SlidingPanelAnimator animator = new SlidingPanelAnimator();
        animator.playAnimation(this,
                attrSpeed, isOnTheScreen, fadeAnimation, attrDirection, attrInterpolator);
    }
}
