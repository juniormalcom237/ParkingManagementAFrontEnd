package com.se3.parkingmanage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    TextInputLayout login, mdp;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        mdp = findViewById(R.id.pwd);
    }

    public void goNext(View view) {
        String url = "http://192.168.189.212:41443/ParkingManagement/webresources/ClientController/user/login";
        String login1 = login.getEditText().getText().toString();
        String password = mdp.getEditText().getText().toString();
        Map<String,String> data = new HashMap<>();
        data.put("login",login1);
        data.put("password",password);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject jsonO;
                try {
                    String status = response.getString("status");
                    System.out.println(status);
                    if(status.equals("success")){
                        Toast.makeText(Login.this, "Logged In successfullu", Toast.LENGTH_SHORT).show();
                        sp = getSharedPreferences("userCredentials", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("login",login1);
                        editor.putString("password", password);
                        editor.putString("userID", response.getString("clientID"));
                        editor.commit();
                        Intent intent = new Intent(Login.this, Home2.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(Login.this, "password and login incorrect", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Successful add", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Home2.class);
                startActivity(intent);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);

    }

    public void goRegistration(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }


}