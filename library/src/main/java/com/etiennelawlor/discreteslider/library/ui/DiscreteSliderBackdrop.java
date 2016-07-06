package com.etiennelawlor.discreteslider.library.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.etiennelawlor.discreteslider.library.utilities.DisplayUtility;

/**
 * Created by etiennelawlor on 7/3/16.
 */

public class DiscreteSliderBackdrop extends FrameLayout {
    
    // region Member Variables
    private Paint fillPaint = new Paint();
    private Paint strokePaint = new Paint();
    private int tickMarkCount = 0;
    private int tickMarkRadius = 0;
    private int horizontalBarThickness = 0;
    private int backdropFillColor = 0;
    private int backdropStrokeColor = 0;
    private int backdropStrokeWidth = 0;
    // endregion

    // region Constructors
    public DiscreteSliderBackdrop(Context context) {
        super(context);
        init(context, null);
    }

    public DiscreteSliderBackdrop(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DiscreteSliderBackdrop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    // endregion

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int interval = (width - (2 * DisplayUtility.dp2px(getContext(), 32) )) / (tickMarkCount-1);

        setUpFillPaint();
        setUpStrokePaint();

        canvas.drawRoundRect(new RectF(DisplayUtility.dp2px(getContext(), 32),
                        (height/2) - DisplayUtility.dp2px(getContext(), horizontalBarThickness/2),
                        width - (DisplayUtility.dp2px(getContext(), 32)),
                        (height/2) + DisplayUtility.dp2px(getContext(), horizontalBarThickness/2)),
                DisplayUtility.dp2px(getContext(), 8),
                DisplayUtility.dp2px(getContext(), 8),
                fillPaint);

        canvas.drawRoundRect(new RectF(DisplayUtility.dp2px(getContext(), 32),
                        (height/2) - DisplayUtility.dp2px(getContext(), horizontalBarThickness/2),
                        width - DisplayUtility.dp2px(getContext(), 32),
                        (height/2) + DisplayUtility.dp2px(getContext(), horizontalBarThickness/2)),
                DisplayUtility.dp2px(getContext(), 8),
                DisplayUtility.dp2px(getContext(), 8),
                strokePaint);

        for(int i=0; i<tickMarkCount; i++){
            canvas.drawCircle(DisplayUtility.dp2px(getContext(), 32) + (i * interval), height/2, DisplayUtility.dp2px(getContext(), tickMarkRadius), fillPaint);
            canvas.drawCircle(DisplayUtility.dp2px(getContext(), 32) + (i * interval), height/2, DisplayUtility.dp2px(getContext(), tickMarkRadius), strokePaint);
        }

        canvas.drawRoundRect(new RectF(DisplayUtility.dp2px(getContext(), 32),
                        (height/2) - DisplayUtility.dp2px(getContext(), (horizontalBarThickness/2)-1),
                        width - DisplayUtility.dp2px(getContext(), 32),
                        (height/2) + DisplayUtility.dp2px(getContext(), (horizontalBarThickness/2)-1)),
                DisplayUtility.dp2px(getContext(), 8),
                DisplayUtility.dp2px(getContext(), 8),
                fillPaint);
    }

    // region Helper Methods
    private void init(Context context, AttributeSet attrs){
    }

    private void setUpFillPaint(){
        fillPaint.setColor(backdropFillColor);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setAntiAlias(true);
    }

    private void setUpStrokePaint(){
        strokePaint.setColor(backdropStrokeColor);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setAntiAlias(true);
        strokePaint.setStrokeWidth(DisplayUtility.dp2px(getContext(), backdropStrokeWidth));
    }

    public void setTickMarkCount(int tickMarkCount) {
        this.tickMarkCount = tickMarkCount < 2 ? 2 : tickMarkCount;
    }

    public void setTickMarkRadius(int tickMarkRadius) {
        this.tickMarkRadius = tickMarkRadius < 2 ? 2 : tickMarkRadius;
    }

    public void setHorizontalBarThickness(int horizontalBarThickness) {
        this.horizontalBarThickness = horizontalBarThickness < 2 ? 2 : horizontalBarThickness;
    }

    public void setBackdropFillColor(int backdropFillColor) {
        this.backdropFillColor = backdropFillColor;
    }

    public void setBackdropStrokeColor(int backdropStrokeColor) {
        this.backdropStrokeColor = backdropStrokeColor;
    }

    public void setBackdropStrokeWidth(int backdropStrokeWidth) {
        this.backdropStrokeWidth = backdropStrokeWidth < 1 ? 1 : backdropStrokeWidth;
    }
    // endregion
}
