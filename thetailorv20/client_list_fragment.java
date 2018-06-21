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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link client_list_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class client_list_fragment extends Fragment {

    //private OnFragmentInteractionListener mListener;

    public client_list_fragment() {
        // Required empty public constructor
    }

    private ArrayList<String> myArrayList = new ArrayList<>();
    private ListView myListView;
    private DatabaseReference myRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.client_list_fragment_layout, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
 /*   public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myArrayList);

        myRef = FirebaseDatabase.getInstance().getReference("Client_Data");
        myListView =  getView().findViewById(R.id.listView);
        myListView.setAdapter(myArrayAdapter);




        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Object myVal = dataSnapshot.child("clname").getValue();
                //for(DataSnapshot child : dataSnapshot.getChildren()) {}
                // Map<String, Object> map = (Map<String, Object>) dataSnapshot.child("clname").getValue();
                //Log.d(TAG, "Value is: " + value);
                myArrayList.add(String.valueOf(myVal));

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
            bundle.putInt("clientId", position+1);
            client_details_fragment clientDetailsFragment = new client_details_fragment();
            clientDetailsFragment.setArguments(bundle);
            FragmentManager fragmentManger = getFragmentManager();
            fragmentManger.beginTransaction().replace(R.id.container, clientDetailsFragment).addToBackStack(null).commit();


            //Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getActivity(), String.valueOf(Integer.parseInt(dataSnapshot.child("id").getValue().toString())), Toast.LENGTH_SHORT).show();

        }
    });
  /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
    }
}
