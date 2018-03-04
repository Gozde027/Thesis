package com.smartlifecoach.itu.smartlifecoachforchildren.ParentMenu;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smartlifecoach.itu.smartlifecoachforchildren.Defines;
import com.smartlifecoach.itu.smartlifecoachforchildren.R;
import com.smartlifecoach.itu.smartlifecoachforchildren.SignIn.Parent;


public class DiyetisyenFragment extends Fragment {

    private static String TAG = "DiyetisyenFragment";
    private TextInputLayout subject,message;
    private Button sendButton;

    private String mailAdress;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Parent parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_parent_diyetisyen_mail, container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = sharedPreferences.getString(Defines.INSTINCE.PARENTSAVED, null);
        parent = gson.fromJson(json, Parent.class);
        mailAdress = parent.getDiyetisyenEmail();

        subject = (TextInputLayout) v.findViewById(R.id.subject);
        message = (TextInputLayout) v.findViewById(R.id.message);
        sendButton = (Button) v.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Mail atacak
                Toast.makeText(getActivity().getApplicationContext(),"Mail yollayacak " + mailAdress,Toast.LENGTH_SHORT).show();
                sendEmail(mailAdress,subject.getEditText().getText().toString(),message.getEditText().getText().toString());
            }
        });

        return v;

    }

    private void sendEmail(String email,String subject, String message) {
        Log.i(TAG, "Send email");

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.i(TAG, "send mail : try");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity().getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
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
