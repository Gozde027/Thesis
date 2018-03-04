package com.smartlifecoach.itu.smartlifecoachforchildren.ParentMenu;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smartlifecoach.itu.smartlifecoachforchildren.Defines;
import com.smartlifecoach.itu.smartlifecoachforchildren.R;
import com.smartlifecoach.itu.smartlifecoachforchildren.SignIn.Parent;


public class InformationFragment extends Fragment {

    private static String TAG = "MainPageFragment";
    private TextView name,surname,username,password,diyetmail,diyetphone;
    private Button updateButton;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Parent parent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_parent_information, container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = sharedPreferences.getString(Defines.INSTINCE.PARENTSAVED, null);
        parent = gson.fromJson(json, Parent.class);

        name = (TextView) v.findViewById(R.id.infoname);
        surname = (TextView) v.findViewById(R.id.infosurname);
        username = (TextView) v.findViewById(R.id.infousername);
        password = (TextView) v.findViewById(R.id.infopassword);
        diyetmail = (TextView) v.findViewById(R.id.infodiyetmail);
        diyetphone = (TextView) v.findViewById(R.id.infodiyetphone);

        updateScreen(parent);

        updateButton = (Button) v.findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Bilgi güncelleme ekranı
                showDialogForSignInAdult();
                Toast.makeText(getActivity().getApplicationContext(),"Bilgiler güncellenecek",Toast.LENGTH_SHORT).show();
            }
        });

        return v;

    }

    private void showDialogForSignInAdult()
    {
        final Dialog dialog = new Dialog(getActivity(),R.style.FullHeightDialog);
        dialog.setContentView(R.layout.adult_sign_in_dialog);

        //TODO gelsin burda degerler ->KOLAY IS
        final EditText nameText = (EditText) dialog.findViewById(R.id.adult_name);
        nameText.setText(parent.getName());
        final EditText surnameText = (EditText) dialog.findViewById(R.id.adult_surname);
        final EditText usernameText = (EditText) dialog.findViewById(R.id.adult_username);
        final EditText passwordText = (EditText) dialog.findViewById(R.id.adult_password);
        final EditText diyetisyenText = (EditText) dialog.findViewById(R.id.adult_diyetisyen);
        final EditText diyetisyenPhoneText = (EditText) dialog.findViewById(R.id.adult_diyetisyen_phone);

        Button dialogButton = (Button) dialog.findViewById(R.id.adult_save_button);
        dialogButton.setText("Güncelle");

        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check(nameText) && check(surnameText) && check(usernameText) && check(passwordText) && check(diyetisyenText) && check(diyetisyenPhoneText)) {

                    parent = new Parent(nameText.getText().toString(),
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

                    updateScreen(parent);
                    Toast.makeText(getActivity().getApplicationContext(), "Güncellendi", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            }
        });
        dialog.show();
    }

    private void updateScreen(Parent parent)
    {
        name.setText(parent.getName());
        surname.setText(parent.getSurname());
        username.setText(parent.getUsername());
        password.setText(parent.getPassword());
        diyetmail.setText(parent.getDiyetisyenEmail());
        diyetphone.setText(parent.getDiyetisyenPhoneNo());
    }

    private boolean check(EditText editText)
    {
        if(editText.getText().toString().equals("")) {
            editText.setError("Lütfen bu alanı doldurunuz");
            return false;
        }
        return true;

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
