package com.se3.parkingmanage.HelperClasses.HomeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.se3.parkingmanage.R;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BoookingViewHolder> {

   ArrayList<BookingHelperClass> bookingLocations;
   private Context context;

    public BookingAdapter(ArrayList<BookingHelperClass> bookingLocations, Context context) {
        this.bookingLocations = bookingLocations;
        this.context = context;
    }

    @NonNull
    @Override
    public BoookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_card,parent,false);
        BoookingViewHolder boookingViewHolder = new BoookingViewHolder(view);
        return boookingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoookingViewHolder holder, int position) {
        BookingHelperClass bookingHelperClass = bookingLocations.get(position);
        holder.price.setText(bookingHelperClass.getPrice());
        holder.title.setText(bookingHelperClass.getBookTitle());
        holder.startdate.setText(bookingHelperClass.getStartDate());
        holder.stopDate.setText(bookingHelperClass.getStopDate());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static  class BoookingViewHolder extends RecyclerView.ViewHolder {
        TextView price,title,startdate,stopDate;
        public BoookingViewHolder(@NonNull View itemView){
            super(itemView);
            price = itemView.findViewById(R.id.book_price);
            title = itemView.findViewById(R.id.book_title);
            startdate = itemView.findViewById(R.id.startDate);
            stopDate = itemView.findViewById(R.id.stopDate);
        }
    }

}
