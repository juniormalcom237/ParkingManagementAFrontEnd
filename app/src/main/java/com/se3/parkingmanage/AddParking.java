package com.se3.parkingmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddParking extends AppCompatActivity {


    TextInputLayout parkingname, parkingCity,parkingCountry,parkingTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parking);
        parkingname = findViewById(R.id.parkingName);
        parkingCity = findViewById(R.id.parkingCity);
        parkingCountry = findViewById(R.id.parkingCountry);
        parkingTel = findViewById(R.id.parkingTelephone);
    }

    public void addParking(View view) {
        String name = parkingname.getEditText().getText().toString();
        String city = parkingCity.getEditText().getText().toString();
        String country = parkingCountry.getEditText().getText().toString();
        String tel = parkingTel.getEditText().getText().toString();
        // add For Image
        Map<String,String> data = new HashMap<>();
        data.put("parkingName",name);
        data.put("city",city);
        data.put("country",city);
        data.put("tel",city);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.189.212:41443/ParkingManagement/webresources/parkingController/parking", new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(AddParking.this, "Successful add", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddParking.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }
}