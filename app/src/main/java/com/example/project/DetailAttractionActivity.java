package com.example.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.RatingBar.OnRatingBarChangeListener;



import java.util.ArrayList;
import java.util.List;

public class DetailAttractionActivity extends AppCompatActivity  {

    String videovalueget;
    RatingBar ratingBar;
    TextView txtRatingValue;
    String attractionnameget;
    String getratingAttractionValue = "";
    SharedPreferences prefs;
    public static final String PREFERENCES_NAME = "RupikaDatabase";
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_attraction);

        // setup toolbar
        Toolbar t1 = (Toolbar) findViewById(R.id.toolbaretailAttractionScreen);
        setSupportActionBar(t1);

        // add an up bar
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        Intent i = getIntent();

        Bundle bundle = i.getExtras();

        if(bundle!=null){
            videovalueget = bundle.getString("videoKey");
            attractionnameget = bundle.getString("attractionnameVlue");
            String longdescget = bundle.getString("longdescKey");

//            YoutubeFragment f = YoutubeFragment.newInstance(videovalueget);
//            getSupportFragmentManager().beginTransaction().replace(R.id.videoView1, f).commit();

            prefs = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor prefsEditor = prefs.edit();

            name = prefs.getString("usernameKeyLoged","");

            WebView webView = (WebView) findViewById(R.id.webViewplay);
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(videovalueget);

            TextView longdes = (TextView) findViewById(R.id.longdwsctxtid);
            longdes.setText(longdescget);
        }
        addListenerOnRatingBar();

    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        //txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically


        List<Rating> RatingData = LoginActivity.ratingConnection.ratingDao().getRatingByAttraction(attractionnameget);

        final ArrayList<String> ratingListValue = new ArrayList<String>();
        for (Rating rate : RatingData){
             ratingListValue.add(rate.getAttractionName());
        }

        for(int i = 0;i<ratingListValue.size();i++){
            getratingAttractionValue = ratingListValue.get(i);
        }

//        List<Rating> valueforRating = LoginActivity.ratingConnection.ratingDao().getRatingByAttractionName(attractionnameget,name);
//
//        final ArrayList<String> ratinglistR= new ArrayList<String>();
//        for (Rating howrate : valueforRating){
//            ratinglistR.add(howrate.getAttractionName());
//            ratinglistR.add(howrate.getUserName());
//        }
//        for(int j=0; j<ratinglistR.size();j++){
//            String nameValueFind = ratinglistR.get(j);
//        }
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                float getStarValues = rating;


                Rating re = new Rating(getStarValues,attractionnameget,name);

                LoginActivity.ratingConnection.ratingDao().addRating(re);

                Toast t = Toast.makeText(getApplication(),"Thanks for the rating rating",Toast.LENGTH_LONG);
                t.show();

            }
        });

    }

}
