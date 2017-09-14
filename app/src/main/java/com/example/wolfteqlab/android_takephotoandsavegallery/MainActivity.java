package com.example.wolfteqlab.android_takephotoandsavegallery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public final int REQUEST_CODE_ASK_PERMISSIONS = 1001;
    private final int requestCode = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                if (Build.VERSION.SDK_INT >= 23) {
                    int hasPermissionCamera = checkSelfPermission(Manifest.permission.CAMERA);

                    if (hasPermissionCamera != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSIONS);
                    } else if (hasPermissionCamera == PackageManager.PERMISSION_GRANTED){
                        Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        photoCaptureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                                imageUri);
                        startActivityForResult(photoCaptureIntent, requestCode);
                    }
                }else {
                    Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    photoCaptureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                            imageUri);
                    startActivityForResult(photoCaptureIntent, requestCode);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode != CAMERA_PIC_REQUEST || filename == null)
            return;
    }
}
