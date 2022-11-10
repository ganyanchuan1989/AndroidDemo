package com.example.androiddemo.media;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;
import com.example.androiddemo.utils.PermissionUtil;
import com.example.androiddemo.utils.ScreenUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DemoCameraActivity extends AppCompatActivity {

    private Camera camera;
    private boolean isPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission();

        int screenWidth = ScreenUtil.getWindowWidth(this);
        int screenHeight = ScreenUtil.getWindowHeigh(this);

        setContentView(R.layout.activity_demo_camera);
        SurfaceView surfaceView = findViewById(R.id.camera_surface_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);// 自己不维护缓冲

        ImageButton btn_camera_capture = findViewById(R.id.btn_camera_capture);
        btn_camera_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPreview){
                    isPreview = true;
                    camera = Camera.open();
                    try {
                        camera.setPreviewDisplay(surfaceHolder);
                        Camera.Parameters parameters = camera.getParameters();
                        parameters.setPictureFormat(PixelFormat.JPEG);
//                        parameters.set("jpeg-quality", 80);
                        camera.setParameters(parameters);
                        camera.startPreview();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    camera.takePicture(null, null, new Camera.PictureCallback() {
                        @Override
                        public void onPictureTaken(byte[] data, Camera camera) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                            camera.stopPreview();
                            isPreview = false;
                            File appDir = new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera");
                            if(!appDir.exists()){
                                appDir.mkdir();
                            }

                            String fileName = System.currentTimeMillis()+".jpeg";
                            File file = new File(appDir,fileName);
                            FileOutputStream fos = null;
                            try {
                                fos = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                                fos.flush();
                                fos.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_FINISHED, Uri.parse("file://"+"")));
                        }
                    });
                }

            }
        });
    }

    private void resetCamera(){
        if(!isPreview){
            camera.startPreview();
            isPreview = true;
        }
    }

    final int REQUEST_CODE = 0x1;
    private void requestPermission(){
        if(!PermissionUtil.hasPermission(this, Manifest.permission.CAMERA)) {
            PermissionUtil.requestPermission(this, Manifest.permission.CAMERA, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}