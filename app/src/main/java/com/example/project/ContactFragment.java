package com.example.project;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    Button phoneButton;
    Button smsButton;
    Button emailButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contact, container, false);
        phoneButton = (Button) view.findViewById(R.id.buttoncall);
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:6477700932"));

                if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                startActivity(i);
            }
        });

        smsButton = (Button) view.findViewById(R.id.buttonsms);
        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntel = new Intent(Intent.ACTION_VIEW);
                smsIntel.setData(Uri.parse("sms:"));
                startActivity(smsIntel);
            }
        });
        emailButton = (Button) view.findViewById(R.id.buttonemail);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent email=new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:toursimTokya@gmail.com"));
                email.setType("text/plain");
                startActivity(email);
            }
        });
        return view;
    }

}
