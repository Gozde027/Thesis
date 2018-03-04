package com.smartlifecoach.itu.smartlifecoachforchildren.ChildrenMenu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel.FoodAdapter;

import com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel.FoodDatabase;

import com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel.FoodItem;
import com.smartlifecoach.itu.smartlifecoachforchildren.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GridViewScreenActivity extends AppCompatActivity {

    GridView gridView;
    private Toolbar toolbar;

    FoodDatabase foodDatabase = new FoodDatabase(this);

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    List<FoodItem> foodItemList = new ArrayList<FoodItem>();
    ArrayList<FoodItem> selectedFoodItemList = new ArrayList<FoodItem>();

    String EXTRA;
    Boolean CHECKED;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_grid_view);

        Intent intent = getIntent();
        EXTRA = intent.getStringExtra("meal_name");
        CHECKED = intent.getBooleanExtra("meal_checked", false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        /*
        FoodItem foodItem1 = new FoodItem("Karpuz","watermelon",2.4,5.5,0,1);
        FoodItem foodItem2 = new FoodItem("Muz","banana",5.4,0,0.8,1);
        FoodItem foodItem3 = new FoodItem("Vişne","cherry",3,7.8,8.9,1);
        FoodItem foodItem4 = new FoodItem("Armut","pear",0,0,5.7,1);
        foodDatabase.addFood(foodItem1);
        foodDatabase.addFood(foodItem2);
        foodDatabase.addFood(foodItem3);
        foodDatabase.addFood(foodItem4);
        */
        setupToolbar();

        gridView = (GridView) findViewById(R.id.gridView);
       // gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);

        if(CHECKED)
        {
            //TODO GSON ALMA
            Gson gson = new Gson();
            String json = sharedPreferences.getString(EXTRA, null);
            Type type = new TypeToken<ArrayList<FoodItem>>() {}.getType();
            ArrayList<FoodItem> arrayList = gson.fromJson(json, type);
            foodItemList = arrayList;
            gridView.setChoiceMode(GridView.CHOICE_MODE_NONE);
        }
        else
        {
            foodItemList = foodDatabase.getAllFoods();
            gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        }


        gridView.setAdapter(new FoodAdapter(this, foodItemList));
        gridView.setMultiChoiceModeListener(new MultiChoiceModeListener());

        //CLICK TOAST
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        ((TextView) v.findViewById(R.id.grid_item_label))
                                .getText(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public class MultiChoiceModeListener implements GridView.MultiChoiceModeListener {
        String TAG = "GridMultiChoiceListener";

        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            Log.i(TAG, "onItemCheckedStateChanged");
            int selectCount = gridView.getCheckedItemCount();
            Object obj = gridView.getAdapter().getItem(position);
            FoodItem foodItem = (FoodItem) obj;

            if(checked)
            {
                Toast.makeText(getApplicationContext(),"check",Toast.LENGTH_SHORT).show();
                gridView.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.redTest));
                showDialogForFoodPorsiyon(foodItem);
                selectedFoodItemList.add(foodItem);
            }
            else
            {
                gridView.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.new_blue));
                Toast.makeText(getApplicationContext(),"Uncheck",Toast.LENGTH_SHORT).show();
                selectedFoodItemList.remove(foodItem);
            }

          //  selectedFoodItemList.add(foodItem);

            switch (selectCount) {
                case 1:
                    mode.setSubtitle("Bir " + getResources().getString(R.string.selectedCount));
                    break;
                default:
                    mode.setSubtitle(selectCount + " " + getResources().getString(R.string.selectedCount));
                    break;
            }
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Log.i(TAG, "onCreateActionMode");
            // Infla o recurso do menu a ser exibido
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate( R.menu.grid_menu_selected, menu );
            Drawable drawable = getResources().getDrawable(R.drawable.child);
            menu.getItem(0).setIcon(drawable);

            mode.setTitle(getResources().getString(R.string.selectFood));
            mode.setSubtitle("Bir " + getResources().getString(R.string.selectedCount));

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.i(TAG, "onPrepareActionMode");
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.i(TAG, "onActionItemClicked");
            //TODO Selected'tan buraya dusurcem GSON KAYIT
            Gson gson = new Gson();

            String json = gson.toJson(selectedFoodItemList);
            editor.putBoolean(EXTRA+"saved",true);
            editor.putString(EXTRA, json);
            editor.commit();

          //  Toast.makeText(getApplicationContext(),selectedFoodItemList.get(0).getFoodname(),Toast.LENGTH_SHORT).show();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", EXTRA);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.i(TAG, "onDestroyActionMode");
            selectedFoodItemList.clear();

        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }


    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            setSupportActionBar(toolbar);
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Yemek Seçme Ekranı");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialogForFoodPorsiyon(final FoodItem foodItem)
    {
        final Dialog dialog = new Dialog(GridViewScreenActivity.this,R.style.FullHeightDialog);
        dialog.setContentView(R.layout.food_porsiyon_picker);
        //dialog.setTitle("Hoşgeldin !!");

        TextView nameText = (TextView) dialog.findViewById(R.id.foodNameText);
        nameText.setText(foodItem.getFoodname());

        final TextView porsiyonText = (TextView) dialog.findViewById(R.id.pickedValue);
        porsiyonText.setText(String.valueOf(foodItem.getPorsiyon()));

        NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);// restricted number to minimum value i.e 1
        numberPicker.setMaxValue(10);// restricked number to maximum value i.e. 31
        numberPicker.setWrapSelectorWheel(true);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

              //  Toast.makeText(getApplicationContext(),"Old : "+String.valueOf(oldVal) + "New : " + String.valueOf(newVal),Toast.LENGTH_LONG).show();
                foodItem.setPorsiyon(newVal);
                foodItem.newValues(foodItem.getPorsiyon());
                porsiyonText.setText(String.valueOf(foodItem.getPorsiyon()));
            }
        });

        Button savedPorsiyon = (Button) dialog.findViewById(R.id.savePorsiyonButton);

        // if button is clicked, close the custom dialog
        savedPorsiyon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getApplicationContext(),"SAVED",Toast.LENGTH_SHORT).show();
                //TODO saved porsiyon
                dialog.dismiss();

            }
        });
        dialog.show();
    }


}
