package com.example.junior.thetailorv20;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class add_client_fragment extends Fragment {


    public add_client_fragment() {
        // Required empty public constructor
    }

    public static int userID;
    public static client_info storedClientInfo = new client_info();
    private EditText client_name, client_phone, client_email;
    private ImageView imageView, imageView1, imageView2;
    private TextView client_measure, client_Design;
    private Button btnSave, btnChange;
    private DatabaseReference myRef, myRef1;
    private StorageReference stRef;
    private FirebaseAuth auth;
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    private ProgressDialog progressDialog;
    private ArrayList<String> pathArray;
    private int arrayPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_client_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        client_name = getView().findViewById(R.id.etname);
        client_phone = getView().findViewById(R.id.etphone);
        client_email = getView().findViewById(R.id.etemail);
        client_measure = getView().findViewById(R.id.tvMeasure);
        client_Design = getView().findViewById(R.id.tvDesign);
        btnSave = getView().findViewById(R.id.btnSave);
        btnChange = getView().findViewById(R.id.button2);
        imageView = getView().findViewById(R.id.ivAddImage);
        /*imageView1 = getView().findViewById(R.id.imageView);
        imageView2 = getView().findViewById(R.id.imageView3);*/
        pathArray = new ArrayList<>();
        progressDialog = new ProgressDialog(getActivity());

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });


        client_measure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAdded()) {
                    startActivity(new Intent(getActivity(), client_measurements_activity.class));
                }
            }
        });

        client_Design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAdded()) {
                    startActivity(new Intent(getActivity(), client_design_activity.class));
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_client_info();
            }
        });


        myRef = FirebaseDatabase.getInstance().getReference("Client_Data");
        myRef1 = FirebaseDatabase.getInstance().getReference("Profile_Image");
        stRef = FirebaseStorage.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        checkFilePermissions();
        addFilePaths();

    }

    private void addFilePaths() {
        Log.d(TAG, "addFilePaths: Adding File Paths");
        String path = System.getenv("EXTERNAL_STORAGE");

    }

    private void checkFilePermissions() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int permissionCheck = getActivity().checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += getActivity().checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (permissionCheck != 0) {
                this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }
        } else {
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == PICK_IMAGE)) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void save_client_info() {


        //progressDialog.setProgress(15);
        String clname = client_name.getText().toString().trim();
        String clphone = client_phone.getText().toString().trim();
        String clmail = client_email.getText().toString().trim();


        final client_info clientInfo = new client_info(clname, clphone, clmail);
        if (clientInfo != null) {
            storedClientInfo = clientInfo;
            myRef.child(String.valueOf(clientInfo.getID())).setValue(clientInfo);
            userID = clientInfo.getID();
            storeUserId storeUserId = new storeUserId();
            storeUserId.setStoredUserId(userID);
            //myRef.child(String.valueOf(clientInfo.getID())).setValue(clientInfo, uploadImg(clientInfo.getID()));
            uploadImg(clientInfo.getID());
            progressDialog.dismiss();
        } else
            Toast.makeText(getActivity(), "Upload Failed", Toast.LENGTH_LONG).show();


    }

    private void uploadImg(final int uID) {
        //int uID = String.valueOf(uID);
        final String uId = String.valueOf(uID);
        StorageReference fileRef = stRef.child("Images/client_images/client_profile_images/" + uId + "." + getFileExtension(imageUri));
        progressDialog.setMessage("Saving Information");
        fileRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Handler handler = new Handler();
                        /*handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.show();
                            }
                        }, 300);*/


                        progressDialog.show();
                        uploadImage upImge = new uploadImage("Profile Image" + uId, taskSnapshot.getDownloadUrl().toString());
                        // String uploadId = myImgRef.push().getKey();
                        myRef1.child(String.valueOf(uID)).setValue(upImge);
                        // myRef.child(String.valueOf(uId)).setValue(upImge);
                        // StorageReference fileRef = stRef.child("images/client_images"+String.valueOf(clientInfo[0].getID())+"." + getFileExtension(imageUri));
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Information Saved..", Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Information Upload Failed..", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.show();//double prog = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        //progressDialog.setProgress((int) prog);
                    }
                });
    }
}



