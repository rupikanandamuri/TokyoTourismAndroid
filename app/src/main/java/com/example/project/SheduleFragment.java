package com.example.project;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class SheduleFragment extends Fragment implements WebViewFragment.OnFragmentInteractionListener {


    Button showBydateButton;
    Button overViewButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_shedule, container, false);

        // show webview as defualt
        showWebView();

        overViewButton = (Button) view.findViewById(R.id.overviewid);
        overViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWebView();
            }
        });
        showBydateButton = (Button) view.findViewById(R.id.showbydateid);
        showBydateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateView();
            }
        });
        return view;
    }


    void showWebView(){
        WebViewFragment frag = new WebViewFragment();
        // Add the fragment to the 'fragment_container' FrameLayout
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, frag).commit();
    }

    void showDateView(){
        DateFragment frag = new DateFragment();
        // Add the fragment to the 'fragment_container' FrameLayout
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, frag).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
