package com.etiennelawlor.discreteslider.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etiennelawlor.discreteslider.R;
import com.etiennelawlor.discreteslider.library.ui.DiscreteSlider;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by etiennelawlor on 7/1/16.
 */

public class MainFragment extends Fragment {

    // region Views
    @Bind(R.id.discrete_slider)
    DiscreteSlider discreteSlider;
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

//        discreteSlider.setTickMarkCount(10);
//        discreteSlider.setTickMarkRadius(16);
//        discreteSlider.setHorizontalBarThickness(18);
//        discreteSlider.setBackdropFillColor(getResources().getColor(R.color.purple_500));
//        discreteSlider.setBackdropStrokeColor(getResources().getColor(R.color.orange_300));
//        discreteSlider.setBackdropStrokeWidth(6);
//        discreteSlider.setThumb(getResources().getDrawable(android.R.drawable.ic_notification_clear_all));
//        discreteSlider.setProgressDrawable(getResources().getDrawable(android.R.drawable.progress_horizontal));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    // endregion
}