package com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartlifecoach.itu.smartlifecoachforchildren.R;

import java.util.List;

/**
 * Created by gkaval on 10/3/15.
 */
public class FoodAdapter extends BaseAdapter{
    private Context context;
    private List<FoodItem> foodList;// = new ArrayList<FoodItem>();

    public FoodAdapter(Context context, List<FoodItem> fList) {
        this.context = context;
        this.foodList = fList;
    }
    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        FoodItem myFood = foodList.get(position);

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.foodlist, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(myFood.getFoodname());

            // set value into textview
            TextView textViewPorsyion = (TextView) gridView
                    .findViewById(R.id.grid_item_porsyion_label);
            textViewPorsyion.setText(String.valueOf(myFood.getPorsiyon()));

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            Resources resources = context.getResources();
            final int resourceId = resources.getIdentifier(myFood.getImagepath(), "drawable",
                    context.getPackageName());
           // resources.getDrawable(resourceId);
            imageView.setImageResource(resourceId);

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
