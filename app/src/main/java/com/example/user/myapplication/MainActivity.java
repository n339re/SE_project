package com.example.user.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity  {
    private Button button;
    private TextView mtextView;
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =(Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openreservation_page();
            }
        });
        webview = findViewById(R.id.Webview);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://www2.cych.org.tw/WebToNewRegister/webOpdSearch.aspx");
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Button button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
                Calendar beginTime = Calendar.getInstance();
                Calendar endTime = Calendar.getInstance();
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.Events.TITLE, "請輸入看診醫生與號碼");
                calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "請輸入看診科別");
                startActivity(calendarIntent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(webview.canGoBack()){
            webview.goBack();
        } else{
            super.onBackPressed();
        }
    }

    public void openreservation_page(){
        Intent intent = new Intent(this, reservation_page.class);
        startActivity(intent);
    }
}