package com.example.susy.simpleservice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context;

    EditText txt;
    Button btStart;
    Button btStop;

    public void setUI(){
        txt = (EditText) findViewById(R.id.txt);
        btStart = (Button) findViewById(R.id.bt_start);
        btStop = (Button) findViewById(R.id.bt_stop);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        context = this;

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txt.getText().toString().equals("")){
                    Toast.makeText(context, "introduce a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Preferences.isServiceEnable(context)){

                    Intent serviceIntent = new Intent(context, MyService.class);
                    serviceIntent.putExtra("numbers",Integer.parseInt(txt.getText().toString()));
                    startService(serviceIntent);
                    Preferences.setServiceEnable(context,true);
                    Toast.makeText(context, "service start", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(context, "service already start", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Preferences.isServiceEnable(context)){
                    stopService(new Intent(context, MyService.class));
                    Preferences.setServiceEnable(context,false);
                    Toast.makeText(context, "service stop", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
