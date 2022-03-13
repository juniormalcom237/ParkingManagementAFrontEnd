package com.se3.parkingmanage;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.se3.parkingmanage.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.se3.parkingmanage.HelperClasses.HomeAdapter.FeaturedHelperClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    //1 create a var
    RecyclerView featuredRecycle;
    RecyclerView.Adapter adapter;
    private RequestQueue mqueue;
    ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_home);
        //2 get the id of the recycle
        featuredRecycle = findViewById(R.id.feature_recycle);


//        featuredRecycler();
//        String url = "http://192.168.43.14:41443/ParkingManagement/webresources/userController/listeuser";
//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null,
//                new Response.Listener<JSONArray>() {
//
//                    @Override
//
//                    public void onResponse(JSONArray response) {
//                        for(int i = 0; i < response.length(); i++){
//                            try {
//                                JSONObject jsonO = response.getJSONObject(i);
//                                System.out.println("okr");
//                                featuredLocations.add(new FeaturedHelperClass(R.drawable.africa, jsonO.getString("login"), jsonO.getString("password") ));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        adapter = new FeaturedAdapter(featuredLocations);
//                        featuredRecycle.setAdapter(adapter);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                        System.out.println("eror");
//
//                    }
//                }
//        );
//        mqueue = Volley.newRequestQueue(this);
//        mqueue.add(request);
//
//    }
//    private void featuredRecycler(){
//        featuredRecycle.setHasFixedSize(true);// 3 load only recycle view that are seen on the user interface
//        featuredRecycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));// 4 set the layout manager
//
//    }
    }

}