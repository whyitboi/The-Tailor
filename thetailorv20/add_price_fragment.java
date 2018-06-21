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


public class add_price_fragment extends Fragment {

//private OnFragmentInteractionListener mListener;


    public add_price_fragment() {
        // Required empty public constructor
    }

    private ArrayList<String> myArrayList = new ArrayList<>();
    private List<uploadImage> clientArrayList;
    private List<uploadImage> sampleArrayList;
    private ArrayList<String> IdArrayList = new ArrayList<>();
    private ListView myListView;
    private DatabaseReference myRef;
    private int Uid;
    /* private HashMap<uploadImage, uploadImage> hashMap = new HashMap<>();
     private List<HashMap<String, uploadImage>> listItems = new ArrayList<>();*/
    private client_info clientInfo = new client_info();

    private singleImageListAdapter clientAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_price_fragment_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



            myRef = FirebaseDatabase.getInstance().getReference("Designs");

            myListView = getView().findViewById(R.id.listView);

        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myArrayList);

        //myRef = FirebaseDatabase.getInstance().getReference("Deigns");
        myListView =  getView().findViewById(R.id.listView);
        myListView.setAdapter(myArrayAdapter);




        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Object myVal = dataSnapshot.child("imgName").getValue();
                Uid = Integer.parseInt(dataSnapshot.getKey());

                IdArrayList.add(String.valueOf(Uid));
                myArrayList.add(dataSnapshot.getKey());

                myArrayAdapter.notifyDataSetChanged();

                //myRef.child();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                myArrayAdapter.notifyDataSetChanged();

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

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //getActivity().setTitle("Design List");

                Bundle bundle = new Bundle();
                bundle.putString("clientId", IdArrayList.get(position));
                price_detail_fragment priceDetailFragment = new price_detail_fragment();
                priceDetailFragment.setArguments(bundle);
                FragmentManager fragmentManger = getFragmentManager();
                fragmentManger.beginTransaction().replace(R.id.container, priceDetailFragment).addToBackStack(null).commit();

            }
        });
    }
}