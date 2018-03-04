package com.smartlifecoach.itu.smartlifecoachforchildren.SignIn;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smartlifecoach.itu.smartlifecoachforchildren.ChildrenDatabesePack.ChildrenDatabase;
import com.smartlifecoach.itu.smartlifecoachforchildren.Defines;
import com.smartlifecoach.itu.smartlifecoachforchildren.ParentMenu.ParentMainActivity;
import com.smartlifecoach.itu.smartlifecoachforchildren.R;

/**
 * Created by Gozde Kaval on 10/27/2015.
 */
public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton adultSign;
    private ImageButton childSign;
    final String TAG = "SignInActivity";
    ChildrenDatabase childDB;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        Intent intent = getIntent();
        String disabled = intent.getStringExtra(Defines.INSTINCE.SIGNIN);

        adultSign = (ImageButton) findViewById(R.id.sign_in_adult_button);
        adultSign.setOnClickListener(this);

        childSign = (ImageButton) findViewById(R.id.sign_in_children_button);
        childSign.setOnClickListener(this);

        if(disabled.equals("parent"))
            adultSign.setClickable(false);
        if(disabled.equals("child"))
            childSign.setClickable(false);

        childDB = new ChildrenDatabase(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

    }


    @Override
    public void onClick(View v) {

        int buttonId = v.getId();
        if(R.id.sign_in_adult_button == buttonId) //Anne Baba
        {
            showDialogForSignInAdult();
        }
        else //Cocuk
        {
            showDialogForSignInChild();
        }

    }

    private void showDialogForSignInAdult()
    {
        final Dialog dialog = new Dialog(SignInActivity.this,R.style.FullHeightDialog);
        dialog.setContentView(R.layout.adult_sign_in_dialog);
        //dialog.setTitle("Hoşgeldin !!");

        // set the custom dialog components - text, image and button
        //TextView text = (TextView) dialog.findViewById(R.id.textView);
        final EditText nameText = (EditText) dialog.findViewById(R.id.adult_name);
        final EditText surnameText = (EditText) dialog.findViewById(R.id.adult_surname);
        final EditText usernameText = (EditText) dialog.findViewById(R.id.adult_username);
        final EditText passwordText = (EditText) dialog.findViewById(R.id.adult_password);
        final EditText diyetisyenText = (EditText) dialog.findViewById(R.id.adult_diyetisyen);
        final EditText diyetisyenPhoneText = (EditText) dialog.findViewById(R.id.adult_diyetisyen_phone);

        Button dialogButton = (Button) dialog.findViewById(R.id.adult_save_button);

        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check(nameText) && check(surnameText) && check(usernameText) && check(passwordText) && check(diyetisyenText) && check(diyetisyenPhoneText) )
                {
                    Parent parent = new Parent(nameText.getText().toString(),
                            surnameText.getText().toString(),
                            usernameText.getText().toString(),
                            passwordText.getText().toString(),
                            diyetisyenText.getText().toString(),
                            diyetisyenPhoneText.getText().toString()
                    );

                    Gson gson = new Gson();

                    String json = gson.toJson(parent);
                    editor.putString(Defines.INSTINCE.PARENTSAVED, json);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Kayıt Yapıldı", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    Intent mainIntent = new Intent(SignInActivity.this, ParentMainActivity.class);
                    SignInActivity.this.startActivity(mainIntent);
                    SignInActivity.this.finish();
                }
            }
        });
        dialog.show();
    }

    private boolean check(EditText editText)
    {
        if(editText.getText().toString().equals("")) {
            editText.setError("Lütfen bu alanı doldurunuz");
            return false;
        }
        return true;

    }

    public void showDialogForSignInChild()
    {
        final Dialog dialog = new Dialog(SignInActivity.this,R.style.FullHeightDialog);
        dialog.setContentView(R.layout.child_sign_in_dialog);
        //dialog.setTitle("Hoşgeldin !!");

        // set the custom dialog components - text, image and button
        //TextView text = (TextView) dialog.findViewById(R.id.textView);
        final EditText nameText = (EditText) dialog.findViewById(R.id.child_name);
        final EditText surnameText = (EditText) dialog.findViewById(R.id.child_surname);

        final ImageButton genderButton = (ImageButton) dialog.findViewById(R.id.child_gender);
        genderButton.setTag("female");

        genderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(genderButton.getTag().toString().equals("female")) {
                    genderButton.setImageResource(R.drawable.male);
                    genderButton.setTag("male");
                }
                else {
                    genderButton.setImageResource(R.drawable.female);
                    genderButton.setTag("female");
                }

            }

        });

        final EditText ageText = (EditText) dialog.findViewById(R.id.child_age);
        final EditText parentUsernameText = (EditText) dialog.findViewById(R.id.child_adult_username);
        final EditText childKilo = (EditText) dialog.findViewById(R.id.child_kilo);

        Button dialogButton = (Button) dialog.findViewById(R.id.child_save_button);

        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkParentUserName(parentUsernameText.getText().toString()) && check(nameText) && check(surnameText) && check(ageText) && check(parentUsernameText) && check(childKilo))
                {
                    Child child = new Child();
                    child.setName(nameText.getText().toString());
                    child.setSurname(surnameText.getText().toString());
                    child.setAge(Integer.parseInt(ageText.getText().toString()));
                    child.setParentUsername(parentUsernameText.getText().toString());
                    child.setKilo(Integer.parseInt(childKilo.getText().toString()));
                    if(genderButton.getTag().toString().equals("female"))
                        child.setGender(1);
                    else
                        child.setGender(0);
                    childDB.addChild(child);

                    addChildtoParent(child);

                    editor.putBoolean(Defines.INSTINCE.CHILDSAVED, true);
                    editor.commit();

                    Toast.makeText(getApplicationContext(),"Child Kayit",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }

    public boolean checkParentUserName(String name)
    {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Defines.INSTINCE.PARENTSAVED, null);
        Parent parent = gson.fromJson(json, Parent.class);
        if(parent.getUsername().equals(name)) {
            return true;
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Ebeveyn kullanıcı adı yanlış !!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void addChildtoParent(Child child)
    {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Defines.INSTINCE.PARENTSAVED, null);
        Parent parent = gson.fromJson(json, Parent.class);
        parent.addChild(child);
    }

}
