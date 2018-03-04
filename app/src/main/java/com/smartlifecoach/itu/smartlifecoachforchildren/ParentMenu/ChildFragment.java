package com.smartlifecoach.itu.smartlifecoachforchildren.ParentMenu;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.smartlifecoach.itu.smartlifecoachforchildren.Defines;
import com.smartlifecoach.itu.smartlifecoachforchildren.R;
import com.smartlifecoach.itu.smartlifecoachforchildren.SignIn.Child;
import com.smartlifecoach.itu.smartlifecoachforchildren.SignIn.Parent;

import java.util.List;


public class ChildFragment extends Fragment {

    private static String TAG = "ChildFragment";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private ListView childList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_parent_child, container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = sharedPreferences.getString(Defines.INSTINCE.PARENTSAVED, null);
        Parent parent = gson.fromJson(json, Parent.class);
        List<Child> children = parent.getChildList();

        childList = (ListView) v.findViewById(R.id.child_list);
        ChildListAdapter childListAdapter = new ChildListAdapter(getActivity(),children);
        childList.setAdapter(childListAdapter);


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
