package com.etiennelawlor.discreteslider.library.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

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
    private int tickMarkRadius;
    private int horizontalBarThickness;
    private int backdropFillColor;
    private int backdropStrokeColor;
    private int backdropStrokeWidth;
    private Drawable thumb;
    private Drawable progressDrawable;
    private OnDiscreteSliderChangeListener onDiscreteSliderChangeListener;
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
            tickMarkRadius = attributeArray.getInteger(R.styleable.DiscreteSlider_tickMarkRadius, 8);
            horizontalBarThickness = attributeArray.getInteger(R.styleable.DiscreteSlider_horizontalBarThickness, 4);
            backdropFillColor = attributeArray.getColor(R.styleable.DiscreteSlider_backdropFillColor, Color.GRAY);
            backdropStrokeColor = attributeArray.getColor(R.styleable.DiscreteSlider_backdropStrokeColor, Color.GRAY);
            backdropStrokeWidth = attributeArray.getInteger(R.styleable.DiscreteSlider_backdropStrokeWidth, 1);
            thumb = attributeArray.getDrawable(R.styleable.DiscreteSlider_thumb);
            progressDrawable = attributeArray.getDrawable(R.styleable.DiscreteSlider_progressDrawable);
        } finally {
            attributeArray.recycle();
        }

        View view = inflate(context, R.layout.discrete_slider, this);
        discreteSliderBackdrop = (DiscreteSliderBackdrop)view.findViewById(R.id.discrete_slider_backdrop);
        discreteSliderBackdrop.setTickMarkCount(tickMarkCount);
        discreteSliderBackdrop.setTickMarkRadius(tickMarkRadius);
        discreteSliderBackdrop.setHorizontalBarThickness(horizontalBarThickness);
        discreteSliderBackdrop.setBackdropFillColor(backdropFillColor);
        discreteSliderBackdrop.setBackdropStrokeColor(backdropStrokeColor);
        discreteSliderBackdrop.setBackdropStrokeWidth(backdropStrokeWidth);

        discreteSeekBar = (DiscreteSeekBar)view.findViewById(R.id.discrete_seek_bar);
        discreteSeekBar.setTickMarkCount(tickMarkCount);
        discreteSeekBar.setPadding(DisplayUtility.dp2px(getContext(), 32),0,DisplayUtility.dp2px(getContext(), 32),0);
        if(thumb != null)
            discreteSeekBar.setThumb(thumb);
        if(progressDrawable != null)
            discreteSeekBar.setProgressDrawable(progressDrawable);

        discreteSeekBar.setOnDiscreteSeekBarChangeListener(new DiscreteSeekBar.OnDiscreteSeekBarChangeListener() {
            @Override
            public void onPositionChanged(int position) {
                if(onDiscreteSliderChangeListener != null){
                    onDiscreteSliderChangeListener.onPositionChanged(position);
                }
            }
        });
    }

    public void setTickMarkCount(int tickMarkCount){
        discreteSliderBackdrop.setTickMarkCount(tickMarkCount);
        discreteSeekBar.setTickMarkCount(tickMarkCount);
    }

    public void setTickMarkRadius(int tickMarkRadius){
        discreteSliderBackdrop.setTickMarkRadius(tickMarkRadius);
    }

    public void setHorizontalBarThickness(int horizontalBarThickness){
        discreteSliderBackdrop.setHorizontalBarThickness(horizontalBarThickness);
    }

    public void setBackdropFillColor(int backdropFillColor){
        discreteSliderBackdrop.setBackdropFillColor(backdropFillColor);
    }

    public void setBackdropStrokeColor(int backdropStrokeColor){
        discreteSliderBackdrop.setBackdropStrokeColor(backdropStrokeColor);
    }

    public void setBackdropStrokeWidth(int backdropStrokeWidth){
        discreteSliderBackdrop.setBackdropStrokeWidth(backdropStrokeWidth);
    }

    public void setThumb(Drawable thumb){
        discreteSeekBar.setThumb(thumb);
    }

    public void setProgressDrawable(Drawable progressDrawable){
        discreteSeekBar.setProgressDrawable(progressDrawable);
    }

    public void setOnDiscreteSliderChangeListener(OnDiscreteSliderChangeListener onDiscreteSliderChangeListener) {
        this.onDiscreteSliderChangeListener = onDiscreteSliderChangeListener;
    }
    // endregion

    // region Interfaces
    // endregion
}
