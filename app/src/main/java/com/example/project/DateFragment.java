package com.example.project;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class DateFragment extends Fragment implements DatePickerDialog.OnDateSetListener {


    WebView webView;
    /*here taking month before value if date is less than 10 adding 0 before evry day or month for link if tha day is more than or equal to  10 adding only day
    or month*/
    String eventDateVlue[] = {"0722","0723","0724","0725","0726","0727","0728","0729","0730","0731","0801","0802","0803","0804","0805","0806","0807","0808","0809"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date, container, false);


        Button refreshButton = (Button) view.findViewById(R.id.dateButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        webView = (WebView) view.findViewById(R.id.webView2);
        webView.setWebViewClient(new WebViewClient());
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String day = String.valueOf(dayOfMonth);
        if (dayOfMonth < 10) {
            day =  "0" + day;
        }

        //Month index start from 0, so need to add + 1
        String monthStr = String.valueOf(month + 1);
        if (month < 10) {
            monthStr =  "0" + monthStr;
        }

        boolean showerror = true;

        if(monthStr.equals("07") || monthStr.equals("08")){

            for(int i = 0;i<eventDateVlue.length;i++) {

                String getevrentDateValue = eventDateVlue[i];
                String getValueofParticularDay = monthStr + day;
                if(getevrentDateValue.equals(getValueofParticularDay)){
                    alterBoxShow();
                    String finalDAte = String.valueOf(year) + monthStr + day;
                    String url = "https://tokyo2020.org/en/schedule/" + finalDAte + "-schedule";
                    webView.loadUrl(url);
                    showerror = false;
                }

            }
            if (showerror) {
                webView.loadUrl("about:blank");
                Toast t = Toast.makeText(getActivity(),"The Time period you entered does not have a event",Toast.LENGTH_LONG);
                t.show();

            }
        }
        else{
            webView.loadUrl("about:blank");
            Toast t = Toast.makeText(getActivity(),"The month you entered does not have a event",Toast.LENGTH_LONG);
            t.show();
        }

    }


    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(), DateFragment.this, 2020 , 06  , 15);
        datePickerDialog.show();
    }

    public void alterBoxShow(){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setTitle("Doy you want to sign up for remindar");
        builder1.setMessage("click on the particular date to get notification for event");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        //show notifcation
                        createNotification();

                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast t = Toast.makeText(getActivity(),"Your are not setting notification",Toast.LENGTH_LONG);
                        t.show();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void createNotification() {

        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);

//               ALaramaReciver receiver = new ALaramaReciver();


        Intent notificationIntent = new Intent(getContext(), ALaramaReciver.class);
        notificationIntent.putExtra("title","Tourism app");
        notificationIntent.putExtra("content","Your scheduled event is on today.");
        PendingIntent broadcast = PendingIntent.getBroadcast(getContext(), 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 8);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

    }

}
