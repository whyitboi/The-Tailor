package com.example.junior.thetailorv20;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.HashMap;

import static com.example.junior.thetailorv20.add_client_fragment.userID;


/**
 * A simple {@link Fragment} subclass.
 */
public class client_measurement_details_fragment extends Fragment {


    public client_measurement_details_fragment() {
        // Required empty public constructor
    }


    private HashMap<String, Integer> measure = new HashMap();
    private HashMap<String, Integer> client_measure = new HashMap();
    private Object key;
    private DatabaseReference myClientIDRef, myMeasureIDRef;
    private Button btnSave;
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.client_measurement_details_fragment_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            final int i = bundle.getInt("clientId");


            //Declare variables with a loop
            final NumberPicker noPicker1, noPicker2, noPicker3, noPicker4, noPicker5, noPicker6, noPicker7, noPicker8, noPicker9, noPicker10;
            final NumberPicker noPicker11, noPicker12, noPicker13, noPicker14, noPicker15, noPicker16, noPicker17, noPicker18, noPicker19, noPicker20;
            final NumberPicker noPicker21, noPicker22, noPicker23, noPicker24, noPicker25, noPicker26, noPicker27, noPicker28;


            noPicker1 = view.findViewById(R.id.pick1);
            noPicker1.setMaxValue(300);
            noPicker1.setMinValue(0);
            noPicker1.setWrapSelectorWheel(false);
            noPicker1.setEnabled(false);

            noPicker2 = view.findViewById(R.id.pick2);
            noPicker2.setMaxValue(300);
            noPicker2.setMinValue(0);
            noPicker2.setWrapSelectorWheel(false);
            noPicker2.setEnabled(false);

            noPicker3 = view.findViewById(R.id.pick3);
            noPicker3.setMaxValue(300);
            noPicker3.setMinValue(0);
            noPicker3.setWrapSelectorWheel(false);
            noPicker3.setEnabled(false);

            noPicker4 = view.findViewById(R.id.pick4);
            noPicker4.setMaxValue(300);
            noPicker4.setMinValue(0);
            noPicker4.setWrapSelectorWheel(false);
            noPicker4.setEnabled(false);

            noPicker5 = view.findViewById(R.id.pick5);
            noPicker5.setMaxValue(300);
            noPicker5.setMinValue(0);
            noPicker5.setWrapSelectorWheel(false);
            noPicker5.setEnabled(false);

            noPicker6 = view.findViewById(R.id.pick6);
            noPicker6.setMaxValue(300);
            noPicker6.setMinValue(0);
            noPicker6.setWrapSelectorWheel(false);
            noPicker6.setEnabled(false);

            noPicker7 = view.findViewById(R.id.pick7);
            noPicker7.setMaxValue(300);
            noPicker7.setMinValue(0);
            noPicker7.setWrapSelectorWheel(false);
            noPicker7.setEnabled(false);

            noPicker8 = view.findViewById(R.id.pick8);
            noPicker8.setMaxValue(300);
            noPicker8.setMinValue(0);
            noPicker8.setWrapSelectorWheel(false);
            noPicker8.setEnabled(false);

            noPicker9 = view.findViewById(R.id.pick9);
            noPicker9.setMaxValue(300);
            noPicker9.setMinValue(0);
            noPicker9.setWrapSelectorWheel(false);
            noPicker9.setEnabled(false);

            noPicker10 = view.findViewById(R.id.pick10);
            noPicker10.setMaxValue(300);
            noPicker10.setMinValue(0);
            noPicker10.setWrapSelectorWheel(false);
            noPicker10.setEnabled(false);

            noPicker11 = view.findViewById(R.id.pick11);
            noPicker11.setMaxValue(300);
            noPicker11.setMinValue(0);
            noPicker11.setWrapSelectorWheel(false);
            noPicker11.setEnabled(false);

            noPicker12 = view.findViewById(R.id.pick12);
            noPicker12.setMaxValue(300);
            noPicker12.setMinValue(0);
            noPicker12.setWrapSelectorWheel(false);
            noPicker12.setEnabled(false);

            noPicker13 = view.findViewById(R.id.pick13);
            noPicker13.setMaxValue(300);
            noPicker13.setMinValue(0);
            noPicker13.setWrapSelectorWheel(false);
            noPicker13.setEnabled(false);

            noPicker14 = view.findViewById(R.id.pick14);
            noPicker14.setMaxValue(300);
            noPicker14.setMinValue(0);
            noPicker14.setWrapSelectorWheel(false);
            noPicker14.setEnabled(false);

            noPicker15 = view.findViewById(R.id.pick15);
            noPicker15.setMaxValue(300);
            noPicker15.setMinValue(0);
            noPicker15.setWrapSelectorWheel(false);
            noPicker15.setEnabled(false);

            noPicker16 = view.findViewById(R.id.pick16);
            noPicker16.setMaxValue(300);
            noPicker16.setMinValue(0);
            noPicker16.setWrapSelectorWheel(false);
            noPicker16.setEnabled(false);

            noPicker17 = view.findViewById(R.id.pick17);
            noPicker17.setMaxValue(300);
            noPicker17.setMinValue(0);
            noPicker17.setWrapSelectorWheel(false);
            noPicker17.setEnabled(false);

            noPicker18 = view.findViewById(R.id.pick18);
            noPicker18.setMaxValue(300);
            noPicker18.setMinValue(0);
            noPicker18.setWrapSelectorWheel(false);
            noPicker18.setEnabled(false);

            noPicker19 = view.findViewById(R.id.pick19);
            noPicker19.setMaxValue(300);
            noPicker19.setMinValue(0);
            noPicker19.setWrapSelectorWheel(false);
            noPicker19.setEnabled(false);

            noPicker20 = view.findViewById(R.id.pick20);
            noPicker20.setMaxValue(300);
            noPicker20.setMinValue(0);
            noPicker20.setWrapSelectorWheel(false);
            noPicker20.setEnabled(false);

            noPicker21 = view.findViewById(R.id.pick21);
            noPicker21.setMaxValue(300);
            noPicker21.setMinValue(0);
            noPicker21.setWrapSelectorWheel(false);
            noPicker21.setEnabled(false);

            noPicker22 = view.findViewById(R.id.pick22);
            noPicker22.setMaxValue(300);
            noPicker22.setMinValue(0);
            noPicker22.setWrapSelectorWheel(false);
            noPicker22.setEnabled(false);

            noPicker23 = view.findViewById(R.id.pick23);
            noPicker23.setMaxValue(300);
            noPicker23.setMinValue(0);
            noPicker23.setWrapSelectorWheel(false);
            noPicker23.setEnabled(false);

            noPicker24 = view.findViewById(R.id.pick24);
            noPicker24.setMaxValue(300);
            noPicker24.setMinValue(0);
            noPicker24.setWrapSelectorWheel(false);
            noPicker24.setEnabled(false);

            noPicker25 = view.findViewById(R.id.pick25);
            noPicker25.setMaxValue(300);
            noPicker25.setMinValue(0);
            noPicker25.setWrapSelectorWheel(false);
            noPicker25.setEnabled(false);

            noPicker26 = view.findViewById(R.id.pick26);
            noPicker26.setMaxValue(300);
            noPicker26.setMinValue(0);
            noPicker26.setWrapSelectorWheel(false);
            noPicker26.setEnabled(false);

            noPicker27 = view.findViewById(R.id.pick27);
            noPicker27.setMaxValue(300);
            noPicker27.setMinValue(0);
            noPicker27.setWrapSelectorWheel(false);
            noPicker27.setEnabled(false);

            noPicker28 = view.findViewById(R.id.pick28);
            noPicker28.setMaxValue(300);
            noPicker28.setMinValue(0);
            noPicker28.setWrapSelectorWheel(false);
            noPicker28.setEnabled(false);

            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Saving Information");

            // final int i = (int) System.currentTimeMillis();
            //measure.put("pk", i);
            myClientIDRef = FirebaseDatabase.getInstance().getReference("Client_Measure/" + String.valueOf(i));


            myClientIDRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (Integer.parseInt(String.valueOf(dataSnapshot.child("client id").getValue())) == i) {

                        long m = (long) dataSnapshot.child("measure id").getValue();
                        myMeasureIDRef = FirebaseDatabase.getInstance().getReference("Measurement/" + String.valueOf(m));

                        myMeasureIDRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                               /*measure = dataSnapshot.getValue(HashMap<String, Integer>.class);
                               Integer.parseInt(String.valueOf(dataSnapshot.child("shoulder").getValue())));
                              key = measure.keySet().toArray();
                               
                               for(int j=1; j<= measure.size(); j++) {
                                 String pick = "noPicker" + j;
                                  measure.get(key[j]).intValue();
                               }*/
                                noPicker1.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("shoulder").getValue())));
                                noPicker2.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("short sleeves").getValue())));
                                noPicker3.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("3_4 sleeves").getValue())));
                                noPicker4.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("3_4 elbow").getValue())));
                                noPicker5.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("long sleeves").getValue())));
                                noPicker6.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("round sleeves").getValue())));
                                noPicker7.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("bust").getValue())));
                                noPicker8.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("bust length").getValue())));
                                noPicker9.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("under bust").getValue())));
                                noPicker10.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("blouse 1_2 length").getValue())));
                                noPicker11.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("blouse length").getValue())));
                                noPicker12.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("under bust round").getValue())));
                                noPicker13.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("blouse waist").getValue())));
                                noPicker14.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("blouse hips").getValue())));
                                noPicker15.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("princess").getValue())));
                                noPicker16.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("front neck").getValue())));
                                noPicker17.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("back neck").getValue())));
                                noPicker18.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("skirt hips").getValue())));
                                noPicker19.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("skirt waist").getValue())));
                                noPicker20.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("skirt knee length").getValue())));
                                noPicker21.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("skirt full length").getValue())));
                                noPicker22.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("short dress").getValue())));
                                noPicker23.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("3_4 dress").getValue())));
                                noPicker24.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("full dress").getValue())));
                                noPicker25.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("thigh round").getValue())));
                                noPicker26.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("front shoulder").getValue())));
                                noPicker27.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("off shoulder").getValue())));
                                noPicker28.setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("arm hole").getValue())));
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

    }

    public void onBackPressed() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }


}