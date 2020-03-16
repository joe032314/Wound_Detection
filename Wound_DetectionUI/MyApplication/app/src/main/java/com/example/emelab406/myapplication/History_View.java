package com.example.emelab406.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class History_View extends AppCompatActivity {
    private ImageButton PreviousPage;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__view);
        PreviousPage = (ImageButton)findViewById(R.id.ImageButton1);
        PreviousPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent = new Intent();
                intent.setClass(History_View.this, Result.class);
                startActivity(intent);
                History_View.this.finish();
            }
        });
    }
}
