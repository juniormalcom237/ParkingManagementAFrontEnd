package com.se3.parkingmanage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.se3.parkingmanage.HelperClasses.HomeAdapter.BookingHelperClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListBookFragment extends Fragment {
    RecyclerView.Adapter adapter;
    RecyclerView bookingRecycle;
    LinearLayout l;
    private Context mContext;
    ArrayList<BookingHelperClass> bookingLocation = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_list_book, container, false);
//
        mContext = inflater.getContext();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        bookingRecycle = (RecyclerView)getView().findViewById(R.id.bookListCard);
        l = (LinearLayout)getView().findViewById(R.id.book2);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Ticket.class);
                startActivity(intent);
            }
        });
//        ListBookRecycler();
//        String url = "http://192.168.43.14:41443/ParkingManagement/webresources/parkingController/listParking";
//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i < response.length(); i++) {
//
//                    try {
//                        JSONObject json0 = response.getJSONObject(i);
//                        bookingLocation.add(new BookingHelperClass( new BookingHelperClass(
//                                json0.getString("book_price"),json0.getString(""))));
//                    } catch (JSONException e) {
//                        System.out.println(e.getMessage());
//                    }
//
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        })

        super.onViewCreated(view, savedInstanceState);
    }

//    private void ListBookRecycler(){
//        bookingRecycle.setHasFixedSize(true);
//        bookingRecycle.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL, false));
//    }

    public void sendTicket(View view) {

        Intent intent = new Intent(mContext, Ticket.class);
        startActivity(intent);

    }
}