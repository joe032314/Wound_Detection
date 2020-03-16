package com.example.emelab406.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.Picture;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.Face;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.R.attr.data;

public class Camera extends AppCompatActivity implements SurfaceHolder.Callback, android.hardware.Camera.AutoFocusCallback {
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
        mSurfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        TakePictureBtn = (ImageButton)findViewById(R.id.ImageButton);
        textview = (TextView)findViewById(R.id.textView);
        TakePictureBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //錄影

                new CountDownTimer(10000, 1000) {

                    @Override

                    public void onTick(long millisUntilFinished) {
                        //倒數秒數中要做的事
                        textview.setText(new SimpleDateFormat("s").format(millisUntilFinished));
                    }

                    @Override
                    public void onFinish() {
                        //倒數完成後要做的事
                        intent = new Intent();
                        intent.setClass(Camera.this, Confirm_Camera.class);
                        startActivity(intent);
                        Camera.this.finish();
                    }
                }.start();
            }
        });

        mSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.autoFocus(null);
            }
        });


    }

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
        if (success)
        {
           // android.hardware.Camera.PictureCallback jpeg = new camera.PictureCallback();
            //camera.takePicture(null,null,jpeg);
        }
    }

   // @Override
    //public void onPictureTaken(byte[] bytes, android.hardware.Camera camera) {
        //File mFile = new File(StorePath);
        //try {
         //   FileOutputStream fos=new FileOutputStream(mFile);
         //   fos.write(data);
         //   fos.close();
          //  Intent intent=new Intent(Camera.this,Confirm_Camera.class);
          //  intent.putExtra("picPath",mFile.getAbsolutePath());
          //  startActivity(intent);
          //  finish();
        //} catch (IOException e) {
         //   e.printStackTrace();
        //}
    //}
}
