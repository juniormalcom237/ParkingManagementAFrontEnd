package com.se3.parkingmanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class ParkingReservation extends AppCompatActivity {

    ArrayList<String> items;
    MaterialDatePicker materialDatePicker;
    long today;
    String slotid;
    TextView date;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    TextInputLayout carName,carType,carMatricule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_reservation);
        autoCompleteTextView = findViewById(R.id.auto_complete);
        carName =  findViewById(R.id.carName);
        carType = findViewById(R.id.carType);
        carMatricule =  findViewById(R.id.carMatricule);
        items = new ArrayList<>();
        items.add("1 Dla Bercy Street");
        date = findViewById(R.id.ddate);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+1"));
         today = MaterialDatePicker.todayInUtcMilliseconds();
        MaterialDatePicker.Builder builder =  MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("select a Date");
        builder.setSelection(today);
        materialDatePicker = builder.build();
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                date.setText(materialDatePicker.getHeaderText());
            }
        });

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

    public String getID(String str){

        String id = str.substring(0,1);
        return id;
    }

    public void addReservation(View view) {
        String name = carName.getEditText().getText().toString();
        String type = carType.getEditText().getText().toString();
        String matricule = carMatricule.getEditText().getText().toString();
        Map<String,String> data = new HashMap<>();
        data.put("CarName",name);
        data.put("carType",type);
        data.put("matricule",matricule);
        data.put("slotId",slotid);
        data.put("clientId", "1");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.189.212:41443/ParkingManagement/webresources/reservationController/addReservation", new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(ParkingReservation.this, "Successful add", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ParkingReservation.this, Login.class);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ParkingReservation.this, "successfully added", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);


    }

    public void getDueDate(View view) {
        materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");

    }

//    public ArrayList<String> LoadSpinnerData(){
//
//    }
}
