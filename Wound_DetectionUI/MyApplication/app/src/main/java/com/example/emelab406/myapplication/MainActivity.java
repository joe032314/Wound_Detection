package com.example.emelab406.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_PERMISSION_CAMERA = 100;
    private ImageButton TakePictureBtn;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TakePictureBtn = (ImageButton)findViewById(R.id.ImageButton);
        askForPermissions();
        TakePictureBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i("TAG","OKAY!!!");
                    intent = new Intent();
                    intent.setClass(MainActivity.this, Confirm_Thermal_Camera.class);
                    startActivity(intent);
                    MainActivity.this.finish();
            }
        });
    }

    private  boolean askForPermissions() {
        // App需要用的功能權限清單
        String[] permissions= new String[] {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO};

        // 檢查是否已經取得權限
        final List<String> listPermissionsNeeded = new ArrayList<>();
        boolean bShowPermissionRationale = false;

        for (String p: permissions) {
            int result = ContextCompat.checkSelfPermission(MainActivity.this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);

                // 檢查是否需要顯示說明
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        MainActivity.this, p))
                    bShowPermissionRationale = true;
            }
        }

        // 向使用者徵詢還沒有許可的權限
        if (!listPermissionsNeeded.isEmpty()) {
            if (bShowPermissionRationale) {
                AlertDialog.Builder altDlgBuilder =
                        new AlertDialog.Builder(MainActivity.this);
                altDlgBuilder.setTitle("提示");
                altDlgBuilder.setMessage("App需要您的許可才能執行。");
                altDlgBuilder.setIcon(android.R.drawable.ic_dialog_info);
                altDlgBuilder.setCancelable(false);
                altDlgBuilder.setPositiveButton("確定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                                        REQUEST_PERMISSION_CAMERA);
                            }
                        });
                altDlgBuilder.show();
            } else
                ActivityCompat.requestPermissions(MainActivity.this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                        REQUEST_PERMISSION_CAMERA);

            return false;
        }

        return true;
    }

}
