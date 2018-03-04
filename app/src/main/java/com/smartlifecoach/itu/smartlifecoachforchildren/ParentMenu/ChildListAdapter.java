package com.smartlifecoach.itu.smartlifecoachforchildren.ParentMenu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartlifecoach.itu.smartlifecoachforchildren.R;
import com.smartlifecoach.itu.smartlifecoachforchildren.SignIn.Child;

import java.util.List;

/**
 * Created by Gozde Kaval on 11/28/2015.
 */
public class ChildListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Child> childList;

    public ChildListAdapter(Activity activity, List<Child> children) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        childList = children;
    }

    @Override
    public int getCount() {
        return childList.size();
    }

    @Override
    public Object getItem(int position) {
        return childList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.child_layout, null);
        TextView textViewName =
                (TextView) satirView.findViewById(R.id.textView9);
        TextView textViewSurName =
                (TextView) satirView.findViewById(R.id.textView11);


        Child child = childList.get(position);

        textViewName.setText(child.getName());
        textViewSurName.setText(child.getSurname());

        return satirView;
    }
}
