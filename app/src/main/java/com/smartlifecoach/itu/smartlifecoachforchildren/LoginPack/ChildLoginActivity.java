package com.smartlifecoach.itu.smartlifecoachforchildren.LoginPack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smartlifecoach.itu.smartlifecoachforchildren.ChildrenDatabesePack.ChildrenDatabase;
import com.smartlifecoach.itu.smartlifecoachforchildren.ChildrenMenu.ChildrenPageActivity;
import com.smartlifecoach.itu.smartlifecoachforchildren.Defines;
import com.smartlifecoach.itu.smartlifecoachforchildren.R;
import com.smartlifecoach.itu.smartlifecoachforchildren.SignIn.Child;
import com.smartlifecoach.itu.smartlifecoachforchildren.SignIn.Parent;

import java.nio.charset.CharacterCodingException;

public class ChildLoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Parent parent;
    private Toolbar toolbar;

    private EditText childName;
    private EditText parentUsername;
    private Button loginButton;

    ChildrenDatabase childDB;
    private Child child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_login);

        childDB = new ChildrenDatabase(this);
        /*
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = sharedPreferences.getString(Defines.INSTINCE.PARENTSAVED, null);
        parent = gson.fromJson(json, Parent.class);
        */

        setupToolbar();

        childName = (EditText) findViewById(R.id.child_name_login);
        parentUsername = (EditText) findViewById(R.id.child_parent_username_login);
        loginButton = (Button) findViewById(R.id.child_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check(childName) && check(parentUsername))
                {
                    //TODO Surasi sacma oldu
                    if(loginControl(childName.getText().toString()))
                    {
                        if(checkParent(child.getParentUsername())) {
                            Intent intent = new Intent(ChildLoginActivity.this, ChildrenPageActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        });

    }

    private boolean loginControl(String name)
    {
        child = childDB.getChild(name);
        if(child.getName() != "")
            return true;
        else
        {
            Toast.makeText(getApplicationContext(),"Listede isim yok!",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private Boolean checkParent(String parentUser)
    {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Defines.INSTINCE.PARENTSAVED, null);
        parent = gson.fromJson(json, Parent.class);
        if(parent.getUsername().equals(parentUser))
            return true;
        else{
            Toast.makeText(getApplicationContext(),"Ebeveyn Adı Yanlış",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean check(EditText editText)
    {
        if(editText.getText().toString().equals("")) {
            editText.setError("Lütfen bu alanı doldurunuz");
            return false;
        }
        return true;

    }

    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        // ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Ebeveyn Giriş");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
