package com.smartlifecoach.itu.smartlifecoachforchildren.LoginPack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smartlifecoach.itu.smartlifecoachforchildren.Defines;
import com.smartlifecoach.itu.smartlifecoachforchildren.ParentMenu.ParentMainActivity;
import com.smartlifecoach.itu.smartlifecoachforchildren.R;
import com.smartlifecoach.itu.smartlifecoachforchildren.SignIn.Parent;

/**
 * Created by Gozde Kaval on 11/28/2015.
 */
public class ParentLoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Parent parent;
    private Toolbar toolbar;

    private EditText username;
    private EditText password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_login);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = sharedPreferences.getString(Defines.INSTINCE.PARENTSAVED, null);
        parent = gson.fromJson(json, Parent.class);

        Toast.makeText(getApplicationContext(), parent.getUsername() + parent.getPassword(), Toast.LENGTH_SHORT).show();
        setupToolbar();

        username = (EditText) findViewById(R.id.parent_user_name);
        password = (EditText) findViewById(R.id.parent_password);
        loginButton = (Button) findViewById(R.id.parent_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check(username) && check(password))
                {
                    if(loginControl(username.getText().toString(),password.getText().toString()))
                    {
                        finish();
                        Intent intent = new Intent(ParentLoginActivity.this, ParentMainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    private boolean loginControl(String user,String pass)
    {
        String realUser = parent.getUsername();
        String realPass = parent.getPassword();
        if(user.equals(realUser) && pass.equals(realPass))
            return true;
        else
        {
            Toast.makeText(getApplicationContext(),"Lütfen Bilgileri kontrol ediniz",Toast.LENGTH_SHORT).show();
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
