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
        import android.widget.SimpleAdapter;
        import android.widget.Toast;

        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link client_list_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class design_clientsdesign_fragment extends Fragment {

    //private OnFragmentInteractionListener mListener;

    public design_clientsdesign_fragment() {
        // Required empty public constructor
    }

    private ArrayList<String> myArrayList = new ArrayList<>();
    private ArrayList<client_info> userList = new ArrayList<>();
    private ListView myListView;
    private DatabaseReference myRef, myCntRef;
    private HashMap<String, String> hashMap = new HashMap<>();
    private List<HashMap<String, String>> listItems = new ArrayList<>();
    private client_info clientInfo = new client_info();
    public static int counter = 0;
    private String string = new String();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.design_clientsdesign_fragment_layout, container, false);
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


        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2, myArrayList);

        myRef = FirebaseDatabase.getInstance().getReference("Client_Data");
        myCntRef = FirebaseDatabase.getInstance().getReference("Designs");

        myListView = getView().findViewById(R.id.listView);



        final SimpleAdapter adapter = new SimpleAdapter(getActivity(), listItems, R.layout.list_item,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.txt1, R.id.txt2});

        myListView.setAdapter(adapter);
        myListView.setClickable(true);


        myRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(final DataSnapshot dataSnapshot, String s) {

                final HashMap<String, String> resultsMap = new HashMap<>();

                resultsMap.put("First Line", dataSnapshot.child("clname").getValue().toString());


               final String id = dataSnapshot.child("id").getValue().toString();

               for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                }

                myCntRef.addChildEventListener(new ChildEventListener() {//orderByChild("imgName").
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        if(dataSnapshot.getKey().equals(id)) {
                            if(dataSnapshot.getChildrenCount() == 1)
                                resultsMap.put("Second Line", String.valueOf(dataSnapshot.getChildrenCount()) + " Design");
                            else if(dataSnapshot.getChildrenCount() >= 1 )
                                resultsMap.put("Second Line", String.valueOf(dataSnapshot.getChildrenCount()) + " Designs");
                            else
                                resultsMap.put("Second Line", /*String.valueOf(dataSnapshot.getChildrenCount()) +*/ "No Designs");
                        }

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

                //resultsMap.put("Second Line", String.valueOf(dataSnapshot.child("Designs").getChildrenCount() / 2));
                listItems.add(resultsMap);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
                //myArrayAdapter.notifyDataSetChanged();

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
                list_client_designs_fragment listClientDesignsFragment = new list_client_designs_fragment();
                listClientDesignsFragment.setArguments(bundle);
                FragmentManager fragmentManger = getFragmentManager();
                fragmentManger.beginTransaction().replace(R.id.design_container, listClientDesignsFragment).commit();


                //Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), String.valueOf(Integer.parseInt(dataSnapshot.child("id").getValue().toString())), Toast.LENGTH_SHORT).show();

            }
        });

    }
}



