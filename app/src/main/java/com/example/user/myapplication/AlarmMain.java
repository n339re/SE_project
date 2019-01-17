package com.example.user.myapplication;
import android.app.AlarmManager;
import android.app.PendingIntent
import android.content.Context
import android.content.Intent;

public class AlarmMain extends ActionBarActivity{
    public void Main(int Target,int GuestReservation,boolean ReservationType){
        int NumberRightNow,LastNumber=-1,Predict,setting,temp;
        NumberRightNow = data_table.getNum();
        Predict=data_table.getP();
        if(ReservationType){//0以時間預約 1以號碼預約
            temp=Target-GuestReservation;
            temp=temp-NumberRightNow;
            setting=temp*Predict;
            if(setting<=0) alarm(0);
            else alarm(setting);
        }
        else{//time
            temp=Target-NumberRightNow;
            temp=temp*Predict;
            setting=temp-GuestReservation;
            if(setting<=0) alarm(0);
            else alarm(setting);
        }
    }
    public void alarm(int setting){
        Intent i = new Intent(this,AlarmBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 1234, i,0);
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Content.ALARM_SERVICE);
        alarmMgr.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+setting,pi);
    }
}

