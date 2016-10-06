package com.etiennelawlor.discreteslider.library.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.etiennelawlor.discreteslider.library.R;
import com.etiennelawlor.discreteslider.library.utilities.DisplayUtility;

/**
 * Created by etiennelawlor on 7/4/16.
 */

public class DiscreteSlider extends FrameLayout {

    // region Views
    private DiscreteSliderBackdrop discreteSliderBackdrop;
    private DiscreteSeekBar discreteSeekBar;
    // endregion

    // region Member Variables
    private int tickMarkCount;
    private float tickMarkRadius;
    private int position;
    private float horizontalBarThickness;
    private int backdropFillColor;
    private int backdropStrokeColor;
    private float backdropStrokeWidth;
    private Drawable thumb;
    private Drawable progressDrawable;
    private OnDiscreteSliderChangeListener onDiscreteSliderChangeListener;
    private int discreteSeekBarLeftPadding = DisplayUtility.dp2px(getContext(), 32);
    private int discreteSeekBarRightPadding = DisplayUtility.dp2px(getContext(), 32);

    // endregion

    // region Interfaces
    public interface OnDiscreteSliderChangeListener {
        void onPositionChanged(int position);
    }
    // endregion

    // region Constructors
    public DiscreteSlider(Context context) {
        super(context);
        init(context, null);
    }

    public DiscreteSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DiscreteSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    // endregion

    // region Helper Methods
    private void init(Context context, AttributeSet attrs){
        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.DiscreteSlider);

        try {
            tickMarkCount = attributeArray.getInteger(R.styleable.DiscreteSlider_tickMarkCount, 5);
            tickMarkRadius = attributeArray.getDimension(R.styleable.DiscreteSlider_tickMarkRadius, 8);
            position = attributeArray.getInteger(R.styleable.DiscreteSlider_position, 0);
            horizontalBarThickness = attributeArray.getDimension(R.styleable.DiscreteSlider_horizontalBarThickness, 4);
            backdropFillColor = attributeArray.getColor(R.styleable.DiscreteSlider_backdropFillColor, Color.GRAY);
            backdropStrokeColor = attributeArray.getColor(R.styleable.DiscreteSlider_backdropStrokeColor, Color.GRAY);
            backdropStrokeWidth = attributeArray.getDimension(R.styleable.DiscreteSlider_backdropStrokeWidth, 1);
            thumb = attributeArray.getDrawable(R.styleable.DiscreteSlider_thumb);
            progressDrawable = attributeArray.getDrawable(R.styleable.DiscreteSlider_progressDrawable);
        } finally {
            attributeArray.recycle();
        }

        View view = inflate(context, R.layout.discrete_slider, this);
        discreteSliderBackdrop = (DiscreteSliderBackdrop)view.findViewById(R.id.discrete_slider_backdrop);
        discreteSeekBar = (DiscreteSeekBar)view.findViewById(R.id.discrete_seek_bar);

        setTickMarkCount(tickMarkCount);
        setTickMarkRadius(tickMarkRadius);
        setHorizontalBarThickness(horizontalBarThickness);
        setBackdropFillColor(backdropFillColor);
        setBackdropStrokeColor(backdropStrokeColor);
        setBackdropStrokeWidth(backdropStrokeWidth);
        setPosition(position);
        setThumb(thumb);
        setProgressDrawable(progressDrawable);

        discreteSeekBar.setPadding(discreteSeekBarLeftPadding,0,discreteSeekBarRightPadding,0);

        discreteSeekBar.setOnDiscreteSeekBarChangeListener(new DiscreteSeekBar.OnDiscreteSeekBarChangeListener() {
            @Override
            public void onPositionChanged(int position) {
                if(onDiscreteSliderChangeListener != null){
                    onDiscreteSliderChangeListener.onPositionChanged(position);
                    setPosition(position);
                }
            }
        });
    }

    public void setTickMarkCount(int tickMarkCount){
        this.tickMarkCount = tickMarkCount;
        discreteSliderBackdrop.setTickMarkCount(tickMarkCount);
        discreteSliderBackdrop.invalidate();
        discreteSeekBar.setTickMarkCount(tickMarkCount);
        discreteSeekBar.invalidate();
    }

    public void setTickMarkRadius(float tickMarkRadius){
        this.tickMarkRadius = tickMarkRadius;
        discreteSliderBackdrop.setTickMarkRadius(tickMarkRadius);
        discreteSliderBackdrop.invalidate();
    }

    public void setPosition(int position) {
        if(position<0){
            this.position = 0;
        } else if(position>tickMarkCount-1){
            this.position = tickMarkCount-1;
        } else {
            this.position = position;
        }
        discreteSeekBar.setPosition(this.position);
    }

    public void setHorizontalBarThickness(float horizontalBarThickness){
        discreteSliderBackdrop.setHorizontalBarThickness(horizontalBarThickness);
        discreteSliderBackdrop.invalidate();
    }

    public void setBackdropFillColor(int backdropFillColor){
        discreteSliderBackdrop.setBackdropFillColor(backdropFillColor);
        discreteSliderBackdrop.invalidate();
    }

    public void setBackdropStrokeColor(int backdropStrokeColor){
        discreteSliderBackdrop.setBackdropStrokeColor(backdropStrokeColor);
        discreteSliderBackdrop.invalidate();
    }

    public void setBackdropStrokeWidth(float backdropStrokeWidth){
        discreteSliderBackdrop.setBackdropStrokeWidth(backdropStrokeWidth);
        discreteSliderBackdrop.invalidate();
    }

    public void setThumb(Drawable thumb){
        if(thumb != null) {
            discreteSeekBar.setThumb(thumb);
            discreteSeekBar.invalidate();
        }
    }

    public void setProgressDrawable(Drawable progressDrawable){
        if(progressDrawable != null) {
            discreteSeekBar.setProgressDrawable(progressDrawable);
            discreteSeekBar.invalidate();
        }
    }

    public void setOnDiscreteSliderChangeListener(OnDiscreteSliderChangeListener onDiscreteSliderChangeListener) {
        this.onDiscreteSliderChangeListener = onDiscreteSliderChangeListener;
    }

    public int getTickMarkCount() {
        return tickMarkCount;
    }

    public float getTickMarkRadius() {
        return tickMarkRadius;
    }

    public int getPosition() {
        return position;
    }

    // endregion
}
