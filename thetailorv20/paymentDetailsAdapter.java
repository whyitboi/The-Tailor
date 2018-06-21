package com.example.junior.thetailorv20;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static com.example.junior.thetailorv20.payment_details_fragment.adapterId;

public class paymentDetailsAdapter extends ArrayAdapter {

    private Activity context;
    private  int resources;
    private List<payment_status> clientList;
    private DatabaseReference myRef, myPaymentRef;
    private uploadImage profile_image;
    private payment_status paymentStatus;
    private int id;


    public paymentDetailsAdapter(@NonNull Activity context, int resource, int id, @NonNull List<payment_status> objects) {
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
        final EditText price = v.findViewById(R.id.etprice);
        final EditText paid = v.findViewById(R.id.etpaid);

        Button save = v.findViewById(R.id.btnSave);
        final ImageView img = v.findViewById(R.id.ivAddImage);




        price.setText(String.valueOf(clientList.get(position).getAmountDue()));
        paid.setText(String.valueOf(clientList.get(position).getAmountPaid()));

        myRef = FirebaseDatabase.getInstance().getReference("Designs/" + String.valueOf(id));
        myPaymentRef = FirebaseDatabase.getInstance().getReference("Client_Design_Payment/" + String.valueOf(id) + "/" + adapterId);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                profile_image = new uploadImage();
                profile_image = dataSnapshot.getValue(uploadImage.class);
                Glide.with(context).load(profile_image.getImgUrl())
                        .override(200, 210)
                        .centerCrop()
                        .into(img);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        paymentStatus = new payment_status(Float.valueOf(String.valueOf(price.getText())), Float.valueOf(String.valueOf(paid.getText())));
                       /* paymentStatus = dataSnapshot.getValue(payment_status.class);

                        paymentStatus.setAmountDue(Float.valueOf(String.valueOf(price.getText())));
                        paymentStatus.setAmountPaid(Float.valueOf(String.valueOf(paid.getText())));*/

                        myPaymentRef.setValue(paymentStatus);//.child(String.valueOf()).setValue(paymentStatus);

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString("clientId", String.valueOf(id));
                price_detail_fragment priceDetailFragment = new price_detail_fragment();
                priceDetailFragment.setArguments(bundle);
                FragmentManager fragmentManger = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManger.beginTransaction().replace(R.id.container, priceDetailFragment).commit();
            }
        });

        return v;
    }
}

