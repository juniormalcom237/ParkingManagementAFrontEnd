package com.se3.parkingmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail_parking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_parking);
        TextView title = findViewById(R.id.detail_title);
        TextView desc = findViewById(R.id.detail_desc);
        Bundle bundle = getIntent().getExtras();
        String mtitle = bundle.getString("title");
        String mdesc = bundle.getString("description");
        title.setText(mtitle);
        desc.setText(mdesc);

    }

    public void goBookingParkingSlot(View view) {
        Intent intent = new Intent(this, ParkingReservation.class);

        startActivity(intent);
    }
}