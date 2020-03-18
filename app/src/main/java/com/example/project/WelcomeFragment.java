package com.example.project;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        TextView welcometxtValue = (TextView) view.findViewById(R.id.tokyowelcometxt);
        TextView nextTxtValue = (TextView) view.findViewById(R.id.tokyonexttxt);
        String welcome = "No trip to Japan would be complete without visiting the capital city of Tokyo." +
                " As polite, respectful, and mild-mannered as the Japanese are, it can also be a fun and quirky culture. " +
                "Tokyo is definitely evidence of this. This huge city is full of light, color, " +
                "and craziness all around contrasted by serious businessmen and women bustling on their way to work and serene ancient temples and shrines.";
        String nextValue = "Getting around Tokyo is easy, especially if you plan on having a phone with your service turned on or using a portable WiFi device. " +
                "If you’re using a Japan Rail Pass, the Tokyo metro will be covered. Read more about why you may NOT want to use your JR Pass in Tokyo here. " +
                " You might end up wanting a Tokyo Metro Pass and using your Japan Rail Pass for the rest of the country. ";
        welcometxtValue.setText(welcome);
        nextTxtValue.setText(nextValue);
        return view;
    }

}
