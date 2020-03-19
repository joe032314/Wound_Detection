package com.example.emelab406.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

public class Result extends AppCompatActivity {
    private ImageButton NextStepBtn;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //NextStepBtn = (ImageButton)findViewById(R.id.ImageButton);
        //.setOnClickListener(new View.OnClickListener()
        //{
            //@Override
            //public void onClick(View view)
            //{
                //intent = new Intent();
                //intent.setClass(Result.this, History_View.class);
               // startActivity(intent);
                //Result.this.finish();
            //}
        //});
    }
}
