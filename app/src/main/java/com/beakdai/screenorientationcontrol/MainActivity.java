package com.beakdai.screenorientationcontrol;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    int[] orients = {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,
            ActivityInfo.SCREEN_ORIENTATION_USER,
            ActivityInfo.SCREEN_ORIENTATION_BEHIND,
            ActivityInfo.SCREEN_ORIENTATION_SENSOR,
            ActivityInfo.SCREEN_ORIENTATION_NOSENSOR,
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE,
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT,
            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE,
            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT,
            ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR,
            ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE,
            ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT,
            ActivityInfo.SCREEN_ORIENTATION_FULL_USER,
            ActivityInfo.SCREEN_ORIENTATION_LOCKED,
            ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED};
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        mSpinner = findViewById(R.id.spinner);
        mSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new ScreenOrientChanger(MainActivity.this.getApplicationContext(),
                        orients[position]).start();
            }
        });
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_SETTINGS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_SETTINGS}, 0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
