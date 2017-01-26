package com.etiennelawlor.discreteslider.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etiennelawlor.discreteslider.R;
import com.etiennelawlor.discreteslider.library.ui.DiscreteLabelSlider;
import com.etiennelawlor.discreteslider.library.ui.DiscreteSlider;
import com.etiennelawlor.discreteslider.library.utilities.DisplayUtility;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by etiennelawlor on 7/1/16.
 */

public class MainFragment extends Fragment {

    public final String TAG = MainFragment.class.getSimpleName();

    // region Views
    @Bind(R.id.discreteLbl_slider)
    DiscreteLabelSlider discreteSlider;
    @Bind(R.id.tick_mark_labels_rl)
    RelativeLayout tickMarkLabelsRelativeLayout;
    // endregion

    // region Constructors
    public MainFragment() {
    }
    // endregion

    // region Factory Methods
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public static MainFragment newInstance(Bundle extras) {
        MainFragment fragment = new MainFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion

    // region Lifecycle Methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Dynamically update attributes
//        discreteSlider.setTickMarkCount(10);
//        discreteSlider.setTickMarkRadius(16);
//        discreteSlider.setHorizontalBarThickness(18);
//        discreteSlider.setBackdropFillColor(getResources().getColor(R.color.purple_500));
//        discreteSlider.setBackdropStrokeColor(getResources().getColor(R.color.orange_300));
//        discreteSlider.setBackdropStrokeWidth(6);
//        discreteSlider.setThumb(getResources().getDrawable(android.R.drawable.ic_notification_clear_all));
//        discreteSlider.setProgressDrawable(getResources().getDrawable(android.R.drawable.progress_horizontal));

        // Detect when slider position changes
        /*
        discreteSlider.setOnDiscreteSliderChangeListener(new DiscreteSlider.OnDiscreteSliderChangeListener() {
            @Override
            public void onPositionChanged(int position) {
                int childCount = tickMarkLabelsRelativeLayout.getChildCount();
                for(int i= 0; i<childCount; i++){
                    TextView tv = (TextView) tickMarkLabelsRelativeLayout.getChildAt(i);
                    if(i == position)
                        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    else
                        tv.setTextColor(getResources().getColor(R.color.grey_400));
                }
            }
        });

        tickMarkLabelsRelativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tickMarkLabelsRelativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                addTickMarkTextLabels();
            }
        });
        */



        discreteSlider.setOnDiscreteSliderChangeListener(new DiscreteLabelSlider.OnDiscreteSliderChangeListener() {
            @Override
            public void onPositionChanged(int position) {
                Log.d(TAG, "onPositionChanged: " + discreteSlider.getLabel(position));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    // endregion

    // region Helper Methods

    /*
    private void addTickMarkTextLabels(){
        int tickMarkCount = discreteSlider.getTickMarkCount();
        float tickMarkRadius = discreteSlider.getTickMarkRadius();
        int width = tickMarkLabelsRelativeLayout.getMeasuredWidth();

        int discreteSliderBackdropLeftMargin = DisplayUtility.dp2px(getContext(), 32);
        int discreteSliderBackdropRightMargin = DisplayUtility.dp2px(getContext(), 32);
        float firstTickMarkRadius = tickMarkRadius;
        float lastTickMarkRadius = tickMarkRadius;
        int interval = (width - (discreteSliderBackdropLeftMargin+discreteSliderBackdropRightMargin) - ((int)(firstTickMarkRadius+lastTickMarkRadius)) )
                / (tickMarkCount-1);

        String[] tickMarkLabels = {"$", "$$", "$$$", "$$$$", "$$$$$"};
        int tickMarkLabelWidth = DisplayUtility.dp2px(getContext(), 40);

        for(int i=0; i<tickMarkCount; i++) {
            TextView tv = new TextView(getContext());

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    tickMarkLabelWidth, RelativeLayout.LayoutParams.WRAP_CONTENT);

            tv.setText(tickMarkLabels[i]);
            tv.setGravity(Gravity.CENTER);
            if(i==discreteSlider.getPosition())
                tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            else
                tv.setTextColor(getResources().getColor(R.color.grey_400));

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
    // endregion
    */

    @OnClick(R.id.btnCheckValue)
    public void checkSliderValue()
    {
        Log.d(TAG, "checkSliderValue: " + discreteSlider.getSelectedLabel());
    }
}