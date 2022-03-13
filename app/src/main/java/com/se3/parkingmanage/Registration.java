package com.se3.parkingmanage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    TextInputLayout regname,regpassword,regemail,regtelephone,regcity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        regname = findViewById(R.id.username);
        regpassword = findViewById(R.id.pwd);
        regemail = findViewById(R.id.email);
        regtelephone = findViewById(R.id.telephone);
        regcity = findViewById(R.id.city);
    }
    public void goHome(View view) {

        String name = regname.getEditText().getText().toString();
        String password = regpassword.getEditText().getText().toString();
        String email = regemail.getEditText().getText().toString();
        String telephone = regtelephone.getEditText().getText().toString();
        String city = regcity.getEditText().getText().toString();
        Map<String,String> data = new HashMap<>();
        data.put("name",name);
        data.put("email",email);
        data.put("login",email);
        data.put("password",password);
        data.put("telephone", telephone);
        data.put("city",city);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.189.212:41443/ParkingManagement/webresources/ClientController/user", new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Registration.this, "Successful add", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registration.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);

    }
}