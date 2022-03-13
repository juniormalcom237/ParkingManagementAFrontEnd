package com.se3.parkingmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VolleyTest extends AppCompatActivity {
    private TextView txt;
    private RequestQueue mqueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_test);
        txt = findViewById(R.id.json_text);
        Button btn = findViewById(R.id.json_button);
        mqueue = Volley.newRequestQueue(this);
//


    }
    public void jsonParse(View view) {
        String url = "http://192.168.8.100:41443/ParkingManagement/webresources/userController/listeuser";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            System.out.println("ok");

                            try {
                                JSONObject jsonO = response.getJSONObject(i);
                                String login = jsonO.getString("login");
                                String mdp = jsonO.getString("password");
                                txt.setText("junior");
                                txt.append(login+", "+ mdp +"\n\n ");

                                System.out.println(login);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        System.out.println("eror");

                    }
                }
        );
        mqueue.add(request);
    }
}