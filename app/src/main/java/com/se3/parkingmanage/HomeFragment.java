package com.se3.parkingmanage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.se3.parkingmanage.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.se3.parkingmanage.HelperClasses.HomeAdapter.FeaturedHelperClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    RecyclerView featuredRecycle;
    RecyclerView.Adapter adapter;
    private RequestQueue mqueue;
    private Context mContext;
    String email;
    String id;
    ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.framel);
//        mapFragment.getMapAsync(this)
        mContext = inflater.getContext();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        featuredRecycle =(RecyclerView)getView().findViewById(R.id.feature_recycle);
        SharedPreferences sp = mContext.getSharedPreferences("userCredentials", Context.MODE_PRIVATE);
        email = sp.getString("login","");
        id = sp.getString("userID","");
        featuredRecycler();
        String url = "http://192.168.189.212:41443/ParkingManagement/webresources/parkingController/listParking";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {

                    @Override

                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonO = response.getJSONObject(i);
                                featuredLocations.add(new FeaturedHelperClass(R.drawable.africa, jsonO.getString("parkingName"), jsonO.getString("adminID") ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter = new FeaturedAdapter(featuredLocations,mContext);
                        featuredRecycle.setAdapter(adapter);
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
        mqueue = Volley.newRequestQueue(mContext);
        mqueue.add(request);
        super.onViewCreated(view, savedInstanceState);
    }

    private void featuredRecycler() {
        featuredRecycle.setHasFixedSize(true);// 3 load only recycle view that are seen on the user interface
        featuredRecycle.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));// 4 set the layout manager
    }
}
