package com.smartlifecoach.itu.smartlifecoachforchildren.ParentMenu;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartlifecoach.itu.smartlifecoachforchildren.R;


public class MainPageFragment extends Fragment {

    private static String TAG = "MainPageFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.cardviews, container, false);


        return v;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.i(TAG, " onAttach");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, " onDetach");

    }

}
