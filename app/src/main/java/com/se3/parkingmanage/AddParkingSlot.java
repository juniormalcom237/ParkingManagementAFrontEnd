package com.se3.parkingmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddParkingSlot extends AppCompatActivity {
    TextInputLayout slotname, slotPrice;
    String slotid;
    ArrayList<String> items;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parking_slot);
        autoCompleteTextView = findViewById(R.id.auto_complete2);
        slotname = findViewById(R.id.slotName);
        slotPrice= findViewById(R.id.slotPrice);
//        items = new ArrayList<>();
//        items.add("1- JuniorMalcom");
        items = LoadParking();

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,items);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "yo: " +  parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                slotid = getID(parent.getItemAtPosition(position).toString());
            }
        });
    }

    public void addparkingSlot(View view) {
        String name = slotname.getEditText().getText().toString();
        String city = slotPrice.getEditText().getText().toString();
        Map<String,String> data = new HashMap<>();
        data.put("slotName",name);
        data.put("slotPrice",city);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.189.212:41443/ParkingManagement/webresources/parkingSlot/addSlot", new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(AddParkingSlot.this, "Successful add", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddParkingSlot.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }
    public String getID(String str){

        String id = str.substring(0,1);
        return id;
    }

    public ArrayList<String> LoadParking(){
        ArrayList<String> data = new ArrayList<String>();
    JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, "http://192.168.59.212:41443/ParkingManagement/webresources/userController/listeuser", null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            for(int i = 0; i < response.length(); i++){
                try {
                    JSONObject jsonO = response.getJSONObject(i);
                    data.add(jsonO.getString("login")+" "+jsonO.getString("password"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });
        return data;
    }

}