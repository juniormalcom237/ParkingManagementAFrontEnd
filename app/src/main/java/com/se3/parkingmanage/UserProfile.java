package com.se3.parkingmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    public void goSetting(View view) {
        Intent intent = new Intent(this, SettingFragment.class);
        startActivity(intent);
    }

    public void openUpload(View view) {
        Intent intent = new Intent(this, UpdateProfile.class);
        startActivity(intent);
    }
}