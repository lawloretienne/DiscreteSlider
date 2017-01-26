package com.etiennelawlor.discreteslider.library.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etiennelawlor.discreteslider.library.R;
import com.etiennelawlor.discreteslider.library.utilities.DisplayUtility;

/**
 * Created by etiennelawlor on 7/4/16.
 */

public class DiscreteLabelSlider extends FrameLayout {

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

    RelativeLayout tickMarkLabelsRelativeLayout;
    String[] sliderLabels;
    private  int textSelectedColor;
    private int textNormalColor;

    // region Interfaces
    public interface OnDiscreteSliderChangeListener {
        void onPositionChanged(int position);
    }
    // endregion

    // region Constructors
    public DiscreteLabelSlider(Context context) {
        super(context);
        init(context, null);
    }

    public DiscreteLabelSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DiscreteLabelSlider(Context context, AttributeSet attrs, int defStyleAttr) {
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


            // Labels Color
            textNormalColor = attributeArray.getColor(R.styleable.DiscreteSlider_labelNormalColor, Color.GRAY);
            textSelectedColor = attributeArray.getColor(R.styleable.DiscreteSlider_labelSelectedColor, Color.BLUE);

            // Labels Slider
            final int id = attributeArray.getResourceId(R.styleable.DiscreteSlider_sliderLables, 0);
            if (id != 0) {
                sliderLabels = getResources().getStringArray(id);
                Log.d("hello", sliderLabels.length + "");
                for (int i=0; i<sliderLabels.length; i++)
                {
                    Log.d("hello", sliderLabels[i]);
                }
                //sliderLabels = values.clone();
            }

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
                    updateLables(position);
                    onDiscreteSliderChangeListener.onPositionChanged(position);
                    setPosition(position);
                }
                else {
                    updateLables(position);
                }
            }
        });

        findViews();
        updateLables(position);
        invalidate();
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


    // New functionality

    private  void findViews()
    {
        tickMarkLabelsRelativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tickMarkLabelsRelativeLayout.setLayoutParams(params);
        this.addView(tickMarkLabelsRelativeLayout);
        tickMarkLabelsRelativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tickMarkLabelsRelativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                addTickMarkTextLabels();
            }
        });
    }

    private void addTickMarkTextLabels(){
        int tickMarkCount = getTickMarkCount();
        float tickMarkRadius = getTickMarkRadius();
        int width = tickMarkLabelsRelativeLayout.getMeasuredWidth();

        int discreteSliderBackdropLeftMargin = DisplayUtility.dp2px(getContext(), 32);
        int discreteSliderBackdropRightMargin = DisplayUtility.dp2px(getContext(), 32);
        float firstTickMarkRadius = tickMarkRadius;
        float lastTickMarkRadius = tickMarkRadius;
        int interval = (width - (discreteSliderBackdropLeftMargin+discreteSliderBackdropRightMargin) - ((int)(firstTickMarkRadius+lastTickMarkRadius)) )
                / (tickMarkCount-1);

        String[] tickMarkLabels = {"$", "$$", "$$$", "$$$$", "$$$$$"};
        if (sliderLabels != null)
        {
            tickMarkLabels = (String[]) sliderLabels;
        }
        int tickMarkLabelWidth = DisplayUtility.dp2px(getContext(), 40);

        for(int i=0; i<tickMarkCount; i++) {
            TextView tv = new TextView(getContext());

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    tickMarkLabelWidth, RelativeLayout.LayoutParams.WRAP_CONTENT);

            tv.setText(tickMarkLabels[i]);
            tv.setGravity(Gravity.CENTER);
            if(i==getPosition())
                tv.setTextColor((Color.BLUE));
            else
                tv.setTextColor(Color.GRAY);

//                    tv.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

            int left = discreteSliderBackdropLeftMargin + (int)firstTickMarkRadius + (i * interval) - (tickMarkLabelWidth/2);

            layoutParams.setMargins(left,
                    0,
                    0,
                    0);
            tv.setLayoutParams(layoutParams);

            tickMarkLabelsRelativeLayout.addView(tv);
        }
    }

    private void updateLables(int pos)
    {
        int childCount = tickMarkLabelsRelativeLayout.getChildCount();
        for(int i= 0; i<childCount; i++){
            TextView tv = (TextView) tickMarkLabelsRelativeLayout.getChildAt(i);
            if(i == pos)
                tv.setTextColor(textSelectedColor);
            else
                tv.setTextColor(textNormalColor);
        }
    }
}
