package com.example.junior.thetailorv20;

        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.Toast;

        import com.example.junior.thetailorv20.adapter.MyImageAdapter;
        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Iterator;
        import java.util.List;

        import static com.example.junior.thetailorv20.design_clientsdesign_fragment.counter;
        import com.example.junior.thetailorv20.client_info;
        import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link client_list_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class list_client_designs_fragment extends Fragment {

    //private OnFragmentInteractionListener mListener;

    public list_client_designs_fragment() {
        // Required empty public constructor
    }

    private List<uploadImage> designArrayList;
    private List<uploadImage> sampleArrayList;
    private ArrayList<String> commentArrayList = new ArrayList<>();
    private ListView myListView;
    private DatabaseReference myRef, myRef2, myRef3;
    private HashMap<uploadImage, uploadImage> hashMap = new HashMap<>();
    private List<HashMap<String, uploadImage>> listItems = new ArrayList<>();
    private client_info clientInfo = new client_info();

    private ImageListAdapter imageAdapter;
    private singleImageListAdapter imageAdapter2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_client_designs_fragment_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            final int i = bundle.getInt("clientId");


            myRef = FirebaseDatabase.getInstance().getReference("Designs/" +  String.valueOf(i));
            //myRef2 = FirebaseDatabase.getInstance().getReference("Samples/" + String.valueOf(i));

            sampleArrayList = new ArrayList<>();
            designArrayList = new ArrayList<>();
            myListView = getView().findViewById(R.id.listView);


            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    uploadImage img = (uploadImage) dataSnapshot.getValue(uploadImage.class); //child(String.valueOf(i)).
                    designArrayList.add(img);

                    imageAdapter = new ImageListAdapter(getActivity(), R.layout.list_item_design_sample, designArrayList);//, designArrayList
                     myListView.setAdapter(imageAdapter);



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
            /* .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    uploadImage img = (uploadImage) dataSnapshot.getValue(uploadImage.class); //child(String.valueOf(i)).
                    designArrayList.add(img);

                    imageAdapter = new ImageListAdapter(getActivity(), R.layout.list_item_design_sample, designArrayList);//, designArrayList
                    // imageAdapter2 = new singleImageListAdapter(getActivity(), R.layout.list_item_design_sample, sampleArrayList);
                    myListView.setAdapter(imageAdapter);
                    //myListView.setAdapter(imageAdapter2);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });*/


        }
        else
            Toast.makeText(getActivity(), "Bundle Error; User Details don't exist", Toast.LENGTH_SHORT).show();
    }

}




