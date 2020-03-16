package com.example.emelab406.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Confirm_Thermal_Camera extends AppCompatActivity {
    private ImageButton NextPageBtn;
    private ImageButton PreviousPageBtn;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_thermal__camera);
        NextPageBtn = (ImageButton)findViewById(R.id.ImageButton);
        PreviousPageBtn = (ImageButton)findViewById(R.id.ImageButton1);
        NextPageBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent = new Intent();
                intent.setClass(Confirm_Thermal_Camera.this, Camera.class);
                startActivity(intent);
                Confirm_Thermal_Camera.this.finish();
            }
        });

        PreviousPageBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent = new Intent();
                intent.setClass(Confirm_Thermal_Camera.this, MainActivity.class);
                startActivity(intent);
                Confirm_Thermal_Camera.this.finish();
            }
        });
    }
}
