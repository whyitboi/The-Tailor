package com.example.junior.thetailorv20;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.example.junior.thetailorv20.add_client_fragment.userID;

public class client_measurements_activity extends AppCompatActivity {

    //Declare variables with a loop
    NumberPicker noPicker1, noPicker2, noPicker3, noPicker4, noPicker5, noPicker6, noPicker7, noPicker8, noPicker9, noPicker10;
    NumberPicker noPicker11, noPicker12, noPicker13, noPicker14, noPicker15, noPicker16, noPicker17, noPicker18, noPicker19, noPicker20;
    NumberPicker noPicker21, noPicker22, noPicker23, noPicker24, noPicker25, noPicker26, noPicker27, noPicker28;



    private HashMap<String, Integer> measure = new HashMap();
    private HashMap<String, Integer> client_measure = new HashMap();
    private DatabaseReference myClientIDRef, myMeasureIDRef;
    private Button btnSave;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_measurements_layout);



        noPicker1 = findViewById(R.id.pick1);
        noPicker1.setMaxValue(300);
        noPicker1.setMinValue(0);
        noPicker1.setWrapSelectorWheel(false);

        noPicker2 = findViewById(R.id.pick2);
        noPicker2.setMaxValue(300);
        noPicker2.setMinValue(0);
        noPicker2.setWrapSelectorWheel(false);

        noPicker3 = findViewById(R.id.pick3);
        noPicker3.setMaxValue(300);
        noPicker3.setMinValue(0);
        noPicker3.setWrapSelectorWheel(false);

        noPicker4 = findViewById(R.id.pick4);
        noPicker4.setMaxValue(300);
        noPicker4.setMinValue(0);
        noPicker4.setWrapSelectorWheel(false);

        noPicker5 = findViewById(R.id.pick5);
        noPicker5.setMaxValue(300);
        noPicker5.setMinValue(0);
        noPicker5.setWrapSelectorWheel(false);

        noPicker6 = findViewById(R.id.pick6);
        noPicker6.setMaxValue(300);
        noPicker6.setMinValue(0);
        noPicker6.setWrapSelectorWheel(false);

        noPicker7 = findViewById(R.id.pick7);
        noPicker7.setMaxValue(300);
        noPicker7.setMinValue(0);
        noPicker7.setWrapSelectorWheel(false);

        noPicker8 = findViewById(R.id.pick8);
        noPicker8.setMaxValue(300);
        noPicker8.setMinValue(0);
        noPicker8.setWrapSelectorWheel(false);

        noPicker9 = findViewById(R.id.pick9);
        noPicker9.setMaxValue(300);
        noPicker9.setMinValue(0);
        noPicker9.setWrapSelectorWheel(false);

        noPicker10 = findViewById(R.id.pick10);
        noPicker10.setMaxValue(300);
        noPicker10.setMinValue(0);
        noPicker10.setWrapSelectorWheel(false);

        noPicker11 = findViewById(R.id.pick11);
        noPicker11.setMaxValue(300);
        noPicker11.setMinValue(0);
        noPicker11.setWrapSelectorWheel(false);

        noPicker12 = findViewById(R.id.pick12);
        noPicker12.setMaxValue(300);
        noPicker12.setMinValue(0);
        noPicker12.setWrapSelectorWheel(false);

        noPicker13 = findViewById(R.id.pick13);
        noPicker13.setMaxValue(300);
        noPicker13.setMinValue(0);
        noPicker13.setWrapSelectorWheel(false);

        noPicker14 = findViewById(R.id.pick14);
        noPicker14.setMaxValue(300);
        noPicker14.setMinValue(0);
        noPicker14.setWrapSelectorWheel(false);

        noPicker15 = findViewById(R.id.pick15);
        noPicker15.setMaxValue(300);
        noPicker15.setMinValue(0);
        noPicker15.setWrapSelectorWheel(false);

        noPicker16 = findViewById(R.id.pick16);
        noPicker16.setMaxValue(300);
        noPicker16.setMinValue(0);
        noPicker16.setWrapSelectorWheel(false);

        noPicker17 = findViewById(R.id.pick17);
        noPicker17.setMaxValue(300);
        noPicker17.setMinValue(0);
        noPicker17.setWrapSelectorWheel(false);

        noPicker18 = findViewById(R.id.pick18);
        noPicker18.setMaxValue(300);
        noPicker18.setMinValue(0);
        noPicker18.setWrapSelectorWheel(false);

        noPicker19 = findViewById(R.id.pick19);
        noPicker19.setMaxValue(300);
        noPicker19.setMinValue(0);
        noPicker19.setWrapSelectorWheel(false);

        noPicker20 = findViewById(R.id.pick20);
        noPicker20.setMaxValue(300);
        noPicker20.setMinValue(0);
        noPicker20.setWrapSelectorWheel(false);

        noPicker21 = findViewById(R.id.pick21);
        noPicker21.setMaxValue(300);
        noPicker21.setMinValue(0);
        noPicker21.setWrapSelectorWheel(false);

        noPicker22 = findViewById(R.id.pick22);
        noPicker22.setMaxValue(300);
        noPicker22.setMinValue(0);
        noPicker22.setWrapSelectorWheel(false);

        noPicker23 = findViewById(R.id.pick23);
        noPicker23.setMaxValue(300);
        noPicker23.setMinValue(0);
        noPicker23.setWrapSelectorWheel(false);

        noPicker24 = findViewById(R.id.pick24);
        noPicker24.setMaxValue(300);
        noPicker24.setMinValue(0);
        noPicker24.setWrapSelectorWheel(false);

        noPicker25 = findViewById(R.id.pick25);
        noPicker25.setMaxValue(300);
        noPicker25.setMinValue(0);
        noPicker25.setWrapSelectorWheel(false);

        noPicker26 = findViewById(R.id.pick26);
        noPicker26.setMaxValue(300);
        noPicker26.setMinValue(0);
        noPicker26.setWrapSelectorWheel(false);

        noPicker27 = findViewById(R.id.pick27);
        noPicker27.setMaxValue(300);
        noPicker27.setMinValue(0);
        noPicker27.setWrapSelectorWheel(false);

        noPicker28 = findViewById(R.id.pick28);
        noPicker28.setMaxValue(300);
        noPicker28.setMinValue(0);
        noPicker28.setWrapSelectorWheel(false);

        btnSave = findViewById(R.id.btnSave);
        progressDialog = new ProgressDialog(client_measurements_activity.this);
        progressDialog.setMessage("Saving Information");

        final int i = (int) System.currentTimeMillis();
        measure.put("pk", i);


        myMeasureIDRef = FirebaseDatabase.getInstance().getReference("Measurement/" + String.valueOf(i));
        myClientIDRef = FirebaseDatabase.getInstance().getReference("Client_Measure/" + userID);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                populateArray();
                client_measure.put("client id", userID);
                client_measure.put("measure id", i);

                               myClientIDRef.setValue(client_measure)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                myMeasureIDRef.setValue(measure)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                progressDialog.dismiss();
                                                Toast.makeText(client_measurements_activity.this, "Measurements Saved..", Toast.LENGTH_LONG).show();

                                            }
                                        });

                            }
                        });


            }
        });


    }

    public void populateArray(){
        measure.put("shoulder", noPicker1.getValue());
        measure.put("short sleeves", noPicker2.getValue());
        measure.put("3_4 sleeves", noPicker3.getValue());
        measure.put("3_4 elbow", noPicker4.getValue());
        measure.put("long sleeves", noPicker5.getValue());
        measure.put("round sleeves", noPicker6.getValue());
        measure.put("bust", noPicker7.getValue());
        measure.put("bust length", noPicker8.getValue());
        measure.put("under bust", noPicker9.getValue());
        measure.put("blouse 1_2 length", noPicker10.getValue());
        measure.put("blouse length", noPicker11.getValue());
        measure.put("under bust round", noPicker12.getValue());
        measure.put("blouse waist", noPicker13.getValue());
        measure.put("blouse hips", noPicker14.getValue());
        measure.put("princess", noPicker15.getValue());
        measure.put("front neck", noPicker16.getValue());
        measure.put("back neck", noPicker17.getValue());
        measure.put("skirt hips", noPicker18.getValue());
        measure.put("skirt waist", noPicker19.getValue());
        measure.put("skirt knee length", noPicker20.getValue());
        measure.put("skirt full length", noPicker21.getValue());
        measure.put("short dress", noPicker22.getValue());
        measure.put("3_4 dress", noPicker23.getValue());
        measure.put("full dress", noPicker24.getValue());
        measure.put("thigh round", noPicker25.getValue());
        measure.put("front shoulder", noPicker26.getValue());
        measure.put("off shoulder", noPicker27.getValue());
        measure.put("arm hole", noPicker28.getValue());

    }
}
