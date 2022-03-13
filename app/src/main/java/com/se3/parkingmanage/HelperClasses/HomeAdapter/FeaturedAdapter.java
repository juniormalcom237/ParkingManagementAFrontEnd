package com.se3.parkingmanage.HelperClasses.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.se3.parkingmanage.Detail_parking;
import com.se3.parkingmanage.MainActivity;
import com.se3.parkingmanage.R;
import com.se3.parkingmanage.databinding.ActivityDetailParkingBinding;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {
    ArrayList<FeaturedHelperClass> featuredLocations;
    private Context mcontext;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations, Context context) {
        this.featuredLocations = featuredLocations;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_card,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass = featuredLocations.get(position);
        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.desc.setText(featuredHelperClass.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, Detail_parking.class);
                Bundle bundle = new Bundle();
                bundle.putString("image", String.valueOf(featuredHelperClass.getImage()));
                bundle.putString("title",featuredHelperClass.getTitle());
                bundle.putString("description", featuredHelperClass.getDescription());
                intent.putExtras(bundle);
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title, desc;
        CardView cardView;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.feature_image);
            title = itemView.findViewById(R.id.feature_title);
            desc = itemView.findViewById(R.id.feature_desc);
            cardView = itemView.findViewById(R.id.feature_item);
        }
    }
}
