package com.example.chpar.test;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage("권한 필요")
                .setDeniedMessage("권한 거부")
                .setPermissions(Manifest.permission.RECORD_AUDIO)
                .check();
    }

    public static class MBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(Intent.ACTION_POWER_CONNECTED)) {
                Toast.makeText(context, "connect", Toast.LENGTH_SHORT).show();
                // Do something when power connected
            }
            else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
                Toast.makeText(context, "disconnect", Toast.LENGTH_SHORT).show();
                // Do something when power disconnected
            }
        }
    }
}
