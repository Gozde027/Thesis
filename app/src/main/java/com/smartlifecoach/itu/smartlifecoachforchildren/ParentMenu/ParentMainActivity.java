package com.smartlifecoach.itu.smartlifecoachforchildren.ParentMenu;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smartlifecoach.itu.smartlifecoachforchildren.Defines;
import com.smartlifecoach.itu.smartlifecoachforchildren.R;
import com.smartlifecoach.itu.smartlifecoachforchildren.SignIn.Parent;

public class ParentMainActivity extends AppCompatActivity implements OnClickListener {

    DrawerLayout drawerLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    FloatingActionButton fab;
    private String phoneNo;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Parent parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yeni);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = sharedPreferences.getString(Defines.INSTINCE.PARENTSAVED, null);
        parent = gson.fromJson(json, Parent.class);

        phoneNo = parent.getDiyetisyenPhoneNo();
        Toast.makeText(getApplicationContext(),parent.getUsername() + parent.getPassword(),Toast.LENGTH_SHORT).show();

        setupNavigationView();
        setupToolbar();
        setupCollapsingToolbarLayout();
        setupFab();

        if (savedInstanceState == null) {
            displayView(0); //ilk aciliste 0.menu secili gelecek
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(drawerLayout != null)
                    drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationView(){

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Initializing NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked())
                    menuItem.setChecked(false);
                else
                    menuItem.setChecked(true);
                //Closing drawer on item click
                drawerLayout.closeDrawers();
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){

                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.navigation_item_1:
                        displayView(0);
                        return true;
                    case R.id.navigation_item_2:
                        displayView(2);
                        return true;
                    case R.id.navigation_item_3:
                        displayView(3);
                        return true;
                    case R.id.navigation_item_4:
                        displayView(4);
                        return true;
                    case R.id.navigation_item_5:
                        displayView(5);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    private void setupCollapsingToolbarLayout(){

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        if(collapsingToolbarLayout != null){
            collapsingToolbarLayout.setTitle(toolbar.getTitle());
            //collapsingToolbarLayout.setCollapsedTitleTextColor(0xED1C24);
            //collapsingToolbarLayout.setExpandedTitleColor(0xED1C24);
        }
    }

    private void setupFab(){
        fab = (FloatingActionButton) findViewById(R.id.fab);
        if(fab != null)
            fab.setOnClickListener(this);
    }
    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Ebeveyn Kontrol Paneli");
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.fab){

            Intent intent = new Intent(Intent.ACTION_CALL);

            intent.setData(Uri.parse("tel:" + phoneNo));
            startActivity(intent);
            /*
            Snackbar
                    .make(findViewById(R.id.coordinatorLayout), phoneNo + " aranacak", Snackbar.LENGTH_LONG)
                    .setAction("Tamam", this)
                    .show(); // Don’t forget to show!*/
        }
    }

    private void displayView(int position) { //gelen positiona gore ilgili fragmenti cagiriyor

        if(position == 5)
            super.onBackPressed();
        else{
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new MainPageFragment();
                    break;
                case 1:
                    fragment = new ChildFragment();
                    break;
                case 2:
                    fragment = new FoodAddFragment();
                    break;
                case 3:
                    fragment = new InformationFragment();
                    break;
                case 4:
                    fragment = new DiyetisyenFragment();
                    break;
                default:
                    break;
            }

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
        }
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

