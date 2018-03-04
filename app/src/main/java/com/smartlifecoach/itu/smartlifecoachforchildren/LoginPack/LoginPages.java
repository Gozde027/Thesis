package com.smartlifecoach.itu.smartlifecoachforchildren.LoginPack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.smartlifecoach.itu.smartlifecoachforchildren.R;

/**
 * Created by Gozde Kaval on 11/28/2015.
 */
public class LoginPages extends AppCompatActivity implements View.OnClickListener{


    private Toolbar toolbar;

    private Button parentButton;
    private Button childButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page_general);
        setupToolbar();

        parentButton = (Button) findViewById(R.id.buttonParent);
        parentButton.setOnClickListener(this);
        childButton = (Button) findViewById(R.id.buttonChild);
        childButton.setOnClickListener(this);


    }

    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        // ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setTitle("Ebeveyn Giriş");
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        Intent mainIntent;
        switch (id)
        {
            case R.id.buttonParent:
                mainIntent = new Intent(LoginPages.this, ParentLoginActivity.class);
                break;
            case R.id.buttonChild:
                mainIntent = new Intent(LoginPages.this, ParentLoginActivity.class);
                break;
            default:
                mainIntent = new Intent(LoginPages.this, ParentLoginActivity.class);
        }
        LoginPages.this.startActivity(mainIntent);
      //  LoginPages.this.finish();

    }
}
