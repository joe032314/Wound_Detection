package com.example.emelab406.myapplication;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class Camera extends AppCompatActivity implements SurfaceHolder.Callback,
        android.hardware.Camera.AutoFocusCallback {
    int width = 640;
    int height = 480;
    Intent intent;
    int count = 11;
    private ImageButton TakePictureBtn;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private android.hardware.Camera camera;
    private TextView textview;
    private String StorePath = "/sdcard/temp.png";

    //android.hardware.Camera.Parameters parameters = camera.getParameters();
    //parameters.setPictureFormat(ImageFormat.JPEG);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mSurfaceView = (SurfaceView) this.findViewById(R.id.surfaceView2);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        TakePictureBtn = (ImageButton)findViewById(R.id.ImageButton);
        textview = (TextView)findViewById(R.id.textView);
        TakePictureBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    camera.autoFocus(Camera.this);
                    //camera.takePicture(null, null, jpeg);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

                intent = new Intent();
                intent.setClass(Camera.this, Confirm_Camera.class);
                startActivity(intent);
                Camera.this.finish();

            }
        });

        mSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.autoFocus(null);
            }
        });


    }

    public static android.hardware.Camera.PictureCallback jpeg = new android.hardware.Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, android.hardware.Camera camera)
        {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (camera == null) {
            camera = camera.open();
            if (mSurfaceHolder != null) {
                camera.startPreview();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        camera.release();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        camera = android.hardware.Camera.open();
        camera.setDisplayOrientation(90);
        try
        {
            camera.setPreviewDisplay(surfaceHolder);
        }
        catch (IOException e)
        {
            camera.release();
            camera = null;
        }
        Log.i("MYLOG","SurfaceView is Creating!");
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        Log.i("MYLOG","SurfaceView changes!");
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        List supportedPictureSizes = parameters.getSupportedPictureSizes();
        parameters.setPictureFormat(PixelFormat.JPEG);
        parameters.setPreviewSize(width, height);
        camera.startPreview();
        //camera.autoFocus(Camera.this);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    @Override
    public void onAutoFocus(boolean success, android.hardware.Camera camera) {
    }

}
