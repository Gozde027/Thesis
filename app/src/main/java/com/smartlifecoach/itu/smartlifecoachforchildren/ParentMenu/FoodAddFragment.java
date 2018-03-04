package com.smartlifecoach.itu.smartlifecoachforchildren.ParentMenu;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel.FoodDatabase;
import com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel.FoodItem;
import com.smartlifecoach.itu.smartlifecoachforchildren.R;


public class FoodAddFragment extends Fragment {

    private static String TAG = "MainPageFragment";
    private EditText e1,e2,e3,e4,e5;
    private Button save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.add_food_fragment, container, false);

        final FoodDatabase foodDatabase = new FoodDatabase(getActivity());

        e1 = (EditText) v.findViewById(R.id.editText4);
        e2 = (EditText) v.findViewById(R.id.editText5);
        e3 = (EditText) v.findViewById(R.id.editText6);
        e4 = (EditText) v.findViewById(R.id.editText7);
        e5 = (EditText) v.findViewById(R.id.editText8);
        save = (Button) v.findViewById(R.id.button2);
        //String name, String path, double crb, double pro, double fat,double porsiyon

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FoodItem foodItem = new FoodItem(e1.getText().toString(),
                        "banana",
                        Double.parseDouble(e2.getText().toString()),
                        Double.parseDouble(e3.getText().toString()),
                        Double.parseDouble(e4.getText().toString()),
                        Double.parseDouble(e5.getText().toString())
                );
                foodDatabase.addFood(foodItem);
                Toast.makeText(getActivity().getApplicationContext(),"Eklendi",Toast.LENGTH_SHORT).show();
            }
        });


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
