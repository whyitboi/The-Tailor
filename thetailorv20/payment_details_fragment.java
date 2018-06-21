package com.example.junior.thetailorv20;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class payment_details_fragment extends Fragment {


    public payment_details_fragment() {
        // Required empty public constructor
    }


    private List<payment_status> clientArrayList;
    private List<uploadImage> sampleArrayList;
    private ArrayList<String> commentArrayList = new ArrayList<>();
    private ListView myListView;
    private DatabaseReference myRef;
    private static int k;
    private client_info clientInfo = new client_info();
    public  static String adapterId;

    private paymentDetailsAdapter paymentClientAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.payment_details_fragment_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            final String i = bundle.getString("clientId");
            final String k = bundle.getString("parentId");

            adapterId = i;


            myRef = FirebaseDatabase.getInstance().getReference("Client_Design_Payment/" + k + "/"+ String.valueOf(i));

            clientArrayList = new ArrayList<>();
            myListView = getView().findViewById(R.id.listView);


            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    payment_status pytStat = (payment_status) dataSnapshot.getValue(payment_status.class);
                    clientArrayList.add(pytStat);
                    paymentClientAdapter = new paymentDetailsAdapter(getActivity(), R.layout.payment_details_list, Integer.parseInt(k), clientArrayList);//, designArrayList
                    myListView.setAdapter(paymentClientAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else
            Toast.makeText(getActivity(), "Bundle Error; User Details don't exist", Toast.LENGTH_SHORT).show();
    }

    public void onBackPressed() {
        //getParentFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }

}
