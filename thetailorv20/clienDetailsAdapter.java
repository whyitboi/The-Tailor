package com.example.junior.thetailorv20;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class clienDetailsAdapter extends ArrayAdapter<client_info> {

    private Activity context;
    private  int resources;
    private List<client_info> clientList;
    private DatabaseReference myRef;
    private uploadImage profile_image;
    private int id;


    public clienDetailsAdapter(@NonNull Activity context, int resource, int id, @NonNull List<client_info> objects) {
        super(context, resource, id, objects);
        this.context = context;
        this.resources = resource;
        this.id = id;
        clientList = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();


        View v = inflater.inflate(resources, null);
        TextView name = v.findViewById(R.id.etname);
        TextView phone = v.findViewById(R.id.etphone);
        TextView email = v.findViewById(R.id.etemail);

        TextView measure = v.findViewById(R.id.tvMeasure);
        final ImageView img = v.findViewById(R.id.ivAddImage);

        measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("clientId", id);
                client_measurement_details_fragment clientMeasurementDetailsFragment = new client_measurement_details_fragment();
                clientMeasurementDetailsFragment.setArguments(bundle);
                FragmentManager fragmentManger = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManger.beginTransaction().replace(R.id.container, clientMeasurementDetailsFragment).commit();
            }
        });

        name.setText(clientList.get(position).getClname());
        phone.setText(clientList.get(position).getClphone());
        email.setText(clientList.get(position).getClmail());

        myRef = FirebaseDatabase.getInstance().getReference("Profile_Image/" + String.valueOf(id));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                profile_image = new uploadImage();
                profile_image = dataSnapshot.getValue(uploadImage.class);
                Glide.with(context).load(profile_image.getImgUrl())
                        .override(200, 210)
                        .centerCrop()
                        .into(img);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return v;
    }
}
