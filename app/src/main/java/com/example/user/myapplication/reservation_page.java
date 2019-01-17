package com.example.user.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.app.AppComponentFactory;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class reservation_page extends AppCompatActivity implements View.OnClickListener{
    Button bu1,bu2;
    EditText et1,et2;
    Button showNotificationBut, stopNotificationBut;

    // Allows us to notify the user that something happened in the background
    NotificationManager notificationManager;

    // Used to track if notification is active in the task bar
    boolean isNotificActive = false;

    // Used to track notifications
    int notifID = 33;

    private int dia,mes,ano,hora,minutos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_page);

        bu1=(Button)findViewById(R.id.bu1);
        bu2=(Button)findViewById(R.id.bu2);
        showNotificationBut=(Button)findViewById(R.id.showNotificationBut);
        et1=(EditText) findViewById(R.id.et1);
        et2=(EditText) findViewById(R.id.et2);
        bu1.setOnClickListener(this);
        bu2.setOnClickListener(this);
        showNotificationBut.setOnClickListener(this);
        // Initialize buttons
        //showNotificationBut = (Button) findViewById(R.id.showNotificationBut);
        showNotificationBut = (Button) findViewById(R.id.showNotificationBut);
        stopNotificationBut  = (Button) findViewById(R.id.stopNotificationBut);
    }

    @Override
    public void onClick(View v) {
        if(v==bu1){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    et1.setText(year + "/"+ (month + 1) + "/"+ dayOfMonth );
                }
            } ,ano, mes,dia);
            datePickerDialog.show();
        }
        if(v==bu2){
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    et2.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();

        }
        if(v==showNotificationBut){
            ITEM reserve = null;
            EditText txtNum = (EditText) findViewById(R.id.editText10);
            EditText txtdeptname = (EditText) findViewById(R.id.editText) ;
            EditText txtdoctorname = (EditText) findViewById(R.id.editText7);
            EditText txtdate = (EditText) findViewById(R.id.et1) ;
            EditText txtslot = (EditText) findViewById(R.id.et2);
            int num = Integer.parseInt(txtNum.getText().toString());
            reserve.setNum(num);
            reserve.setDeptname(txtdeptname.getText().toString());
            reserve.setDoctorname(txtdoctorname.getText().toString());
            reserve.setDate(txtdate.getText().toString());
            reserve.setSlot(txtslot.getText().toString());
            TABLE.add(reserve);
            reservation_page.this.finish();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(View view) {
        // Builds a notification
        NotificationCompat.Builder notificBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Message")
                .setContentText("New Message")
                .setTicker("Alert New Message")
                .setSmallIcon(R.drawable.ic_launcher_foreground);
        // Define that we have the intention of opening MoreInfoNotification
        Intent moreInfoIntent = new Intent(this, MoreInfoNotification.class);
        // Used to stack tasks across activites so we go to the proper place when back is clicked
        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(this);
        // Add all parents of this activity to the stack
        tStackBuilder.addParentStack(MoreInfoNotification.class);
        // Add our new Intent to the stack
        tStackBuilder.addNextIntent(moreInfoIntent);
        // Define an Intent and an action to perform with it by another application
        // FLAG_UPDATE_CURRENT : If the intent exists keep it but update it if needed
        PendingIntent pendingIntent = tStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Defines the Intent to fire when the notification is clicked
        notificBuilder.setContentIntent(pendingIntent);

        // Gets a NotificationManager which is used to notify the user of the background event
        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Post the notification
        notificationManager.notify(notifID, notificBuilder.build());

        // Used so that we can't stop a notification that has already been stopped
        isNotificActive = true;

    }

    public void stopNotification(View view) {
        // If the notification is still active close it
        if(isNotificActive) {
            notificationManager.cancel(notifID);
        }
    }
    public void setAlarm(View view) {

        // Define a time value of 5 seconds
        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        // Define our intention of executing AlertReceiver
        Intent alertIntent = new Intent(this, AlertReceiver.class);

        // Allows you to schedule for your application to do something at a later date
        // even if it is in he background or isn't active
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // set() schedules an alarm to trigger
        // Trigger for alertIntent to fire in 5 seconds
        // FLAG_UPDATE_CURRENT : Update the Intent if active
        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime,
                PendingIntent.getBroadcast(this, 1, alertIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT));

    }
    /*public void Send(View view){
        ITEM reserve = null;
        EditText txtNum = (EditText) findViewById(R.id.editText10);
        EditText txtdeptname = (EditText) findViewById(R.id.editText) ;
        EditText txtdoctorname = (EditText) findViewById(R.id.editText7);
        EditText txtdate = (EditText) findViewById(R.id.et1) ;
        EditText txtslot = (EditText) findViewById(R.id.et2);
        int num = Integer.parseInt(txtNum.getText().toString());
        reserve.setNum(num);
        reserve.setDeptname(txtdeptname.getText().toString());
        reserve.setDoctorname(txtdoctorname.getText().toString());
        reserve.setDate(txtdate.getText().toString());
        reserve.setSlot(txtslot.getText().toString());
        TABLE.add(reserve);
    }
    */
}