package com.example.susy.simpleservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by susy on 18/06/17.
 */

public class MyService extends Service {

    String TAG = "service";
    Context context;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"Servicio > onStartCommand");

        context = getApplicationContext();

        int number = intent.getIntExtra("numbers",3);

        MyThread myNumberThread = new MyThread(number,context);
        myNumberThread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"Servicio > onCreate");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Servicio > onDestroy");

    }
}

class MyThread extends Thread {

    String TAG = "service";
    int numbers;
    Context context;

    public MyThread(int numbers, Context context) {
        this.numbers = numbers;
        this.context = context;
    }

    @Override
    public void run() {
        for (int i = 0; i < numbers; i++) {
            try {
                sleep(1000);
                Log.i(TAG,"thread > "+i);

                final String toastSTR = String.valueOf(i+"/"+numbers + "  showed");

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable(){
                    @Override
                    public void run(){
                        Toast.makeText(context,toastSTR, Toast.LENGTH_SHORT).show();
                    }
                });


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.i(TAG,"thread > FINISH");

    }
}