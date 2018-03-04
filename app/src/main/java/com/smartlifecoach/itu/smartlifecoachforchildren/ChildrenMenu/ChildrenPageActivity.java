package com.smartlifecoach.itu.smartlifecoachforchildren.ChildrenMenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel.FoodDatabase;
import com.smartlifecoach.itu.smartlifecoachforchildren.LoginPack.ParentLoginActivity;
import com.smartlifecoach.itu.smartlifecoachforchildren.R;

public class ChildrenPageActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = "ChildrenPageActivity";
    final int FOOD_SELECT_CODE = 1;

    final String CHECK = "checked";
    final String UNCHECK = "unchecked";

    FoodDatabase foodDatabase = new FoodDatabase(this);

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //List<FoodItem> foodItemList = new ArrayList<FoodItem>();

    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;

    //CardViews
    ImageView morningImage,morningSnackImage,lunchImage,lunchSnackImage,dinnerImage;
    FloatingActionButton morningFab,morningSnackFab,lunchFab,lunchSnackFab,dinnerFab;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.children_page_activity_layout);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        setupToolbar();
        setupCollapsingToolbarLayout();
        setupYemek();



    }

    private void setupYemek()
    {
        morningFab = (FloatingActionButton) findViewById(R.id.fabMorning);
        morningFab.setOnClickListener(this);
        if(checkButtonState("morningsaved"))
        {
            setCheckedButtonByResult("morning");
        }
        else
            morningFab.setTag(UNCHECK);

        morningSnackFab = (FloatingActionButton) findViewById(R.id.fabMorningSnack);
        morningSnackFab.setOnClickListener(this);
        if(checkButtonState("morning_snacksaved"))
        {
            setCheckedButtonByResult("morning_snack");
        }
        else
            morningSnackFab.setTag(UNCHECK);


        lunchFab = (FloatingActionButton) findViewById(R.id.fabLunch);
        lunchFab.setOnClickListener(this);
        if(checkButtonState("lunchsaved"))
        {
            setCheckedButtonByResult("lunch");
        }
        else
            lunchFab.setTag(UNCHECK);

        lunchSnackFab = (FloatingActionButton) findViewById(R.id.fabLunchSnack);
        lunchSnackFab.setOnClickListener(this);
        if(checkButtonState("lunch_snacksaved"))
        {
            setCheckedButtonByResult("lunch_snack");
        }
        else
            lunchSnackFab.setTag(UNCHECK);

        dinnerFab = (FloatingActionButton) findViewById(R.id.fabDinner);
        dinnerFab.setOnClickListener(this);
        if(checkButtonState("dinnersaved"))
        {
            setCheckedButtonByResult("dinner");
        }
        else
            dinnerFab.setTag(UNCHECK);
    }

    private void setupCollapsingToolbarLayout(){

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        if(collapsingToolbarLayout != null){
            collapsingToolbarLayout.setTitle(toolbar.getTitle());
            //collapsingToolbarLayout.setCollapsedTitleTextColor(0xED1C24);
            //collapsingToolbarLayout.setExpandedTitleColor(0xED1C24);
        }
    }

    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setTitle("Cocuk Paneli");
    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_exit:
                super.onBackPressed();
                return true;
            case R.id.action_parent:
                finish();
                //moveTaskToBack(true);
                //int pid = android.os.Process.myPid();
                //android.os.Process.killProcess(pid);

                Intent intent = new Intent(ChildrenPageActivity.this, ParentLoginActivity.class);
                startActivity(intent);

                return true;
            case R.id.action_add_child:
                /*
                Intent intent = new Intent(ChildrenPageActivity.this, SignInActivity.class);
                startActivity(intent);
                */
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        String extra = "";
        switch (id)
        {
            case R.id.fabMorning:
                extra = "morning";
                break;
            case R.id.fabMorningSnack:
                extra = "morning_snack";
                break;
            case R.id.fabLunch:
                extra = "lunch";
                break;
            case R.id.fabLunchSnack:
                extra = "lunch_snack";
                break;
            case R.id.fabDinner:
                extra = "dinner";
                break;

        }
        Intent i = new Intent(this, GridViewScreenActivity.class);
        i.putExtra("meal_name", extra);

        FloatingActionButton tempButton = (FloatingActionButton)findViewById(id);
        if(tempButton.getTag().equals("checked"))
            i.putExtra("meal_checked",true);
           // Toast.makeText(getApplicationContext(),"Button tag is checked " , Toast.LENGTH_SHORT).show();
        else
            i.putExtra("meal_checked",false);
            //Toast.makeText(getApplicationContext(),"Button tag is unchecked " , Toast.LENGTH_SHORT).show();
        startActivityForResult(i, FOOD_SELECT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == FOOD_SELECT_CODE) {
            if(resultCode == Activity.RESULT_OK){
                Log.i(TAG, "RESULT_OK");
                String result = data.getStringExtra("result");
                Toast.makeText(getApplicationContext(),result + " kaydedildi.",Toast.LENGTH_SHORT).show();
                setCheckedButtonByResult(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Log.i(TAG, "RESULT_CANCELED");
            }
        }
    }//onActivityResult

    public void setCheckedButtonByResult(String result)
    {
       // FloatingActionButton myButton;// = morningFab;
        if(result.equals("morning"))
        {
            morningFab.setImageResource(R.drawable.check_grey);
            morningFab.setTag("checked");
        }
        if(result.equals("morning_snack"))
        {
            morningSnackFab.setImageResource(R.drawable.check_grey);
            morningSnackFab.setTag("checked");
        }
        if(result.equals("lunch"))
        {
            lunchFab.setImageResource(R.drawable.check_grey);
            lunchFab.setTag("checked");
        }
        if(result.equals("lunch_snack"))
        {
            lunchSnackFab.setImageResource(R.drawable.check_grey);
            lunchSnackFab.setTag("checked");
        }
        if(result.equals("dinner"))
        {
            dinnerFab.setImageResource(R.drawable.check_grey);
            dinnerFab.setTag("checked");
        }

    }

    boolean checkButtonState(String key)
    {
        if(sharedPreferences.contains(key))
        {
            Log.i(TAG, "contains KEY at " + key);
            if(sharedPreferences.getBoolean(key,false)) {
                Log.i(TAG, " KEY : checked " + key);
                return true;
            }
            else {
                Log.i(TAG, " KEY : unchecked " + key);
                return false;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder1 = null;
        try {
            builder1 = new AlertDialog.Builder(this);
        } catch (NoSuchMethodError e) {
            builder1 = new AlertDialog.Builder(this);
        }
        builder1.setTitle("");
        builder1.setMessage(getResources().getString(R.string.exit_from_app));
        builder1.setCancelable(true);
        builder1.setPositiveButton(getResources().getString(R.string.str_exit),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        moveTaskToBack(true);
                        int pid = android.os.Process.myPid();
                        android.os.Process.killProcess(pid);
                        dialog.cancel();
                    }
                });
        builder1.setNegativeButton(getResources().getString(R.string.str_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

}
