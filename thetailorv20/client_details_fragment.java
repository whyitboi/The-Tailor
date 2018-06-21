package com.example.junior.thetailorv20;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class client_details_fragment extends Fragment {


    public client_details_fragment() {
        // Required empty public constructor
    }

    private List<client_info> clientArrayList;
    private List<uploadImage> sampleArrayList;
    private ArrayList<String> commentArrayList = new ArrayList<>();
    private ListView myListView;
    private DatabaseReference myRef;
   /* private HashMap<uploadImage, uploadImage> hashMap = new HashMap<>();
    private List<HashMap<String, uploadImage>> listItems = new ArrayList<>();*/
    private client_info clientInfo = new client_info();

    private clienDetailsAdapter clientAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.client_details_fragment_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            final int i = bundle.getInt("clientId");


            myRef = FirebaseDatabase.getInstance().getReference("Client_Data/" + String.valueOf(i));

            clientArrayList = new ArrayList<>();
            myListView = getView().findViewById(R.id.listView);


                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            client_info img = (client_info) dataSnapshot.getValue(client_info.class);
                            clientArrayList.add(img);
                            clientAdapter = new clienDetailsAdapter(getActivity(), R.layout.client_details_list, i, clientArrayList);//, designArrayList
                            myListView.setAdapter(clientAdapter);
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
