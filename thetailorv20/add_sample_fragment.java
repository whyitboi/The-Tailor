package com.example.junior.thetailorv20;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class add_sample_fragment extends Fragment {



    private ImageView imageView;
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    private ProgressDialog progressDialog;
    private DatabaseReference sampleRef;
    private StorageReference stRef;
    private FirebaseAuth auth;
    private Button saveSample, uploadSample;
    private TextView comments;
    public long id = System.currentTimeMillis();


    public add_sample_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_sample_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());



        comments = getView().findViewById(R.id.tvComments);
        saveSample = getView().findViewById(R.id.saveDesign);
        uploadSample = getView().findViewById(R.id.upDesign);
        imageView = getView().findViewById(R.id.imageView);
        sampleRef = FirebaseDatabase.getInstance().getReference("Free_Samples/" + String.valueOf(id));
        stRef = FirebaseStorage.getInstance().getReference();
        auth = FirebaseAuth.getInstance();


        saveSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Saving Information");
                progressDialog.show();
                uploadImg(comments.getText().toString().trim());

            }
        });

        uploadSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });


        checkFilePermissions();
        addFilePaths();
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
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
                this.getActivity().requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }
        } else {
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }




/*    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }*/
private String getFileExtension( Uri uri) {

    ContentResolver c = getActivity().getContentResolver();
    MimeTypeMap mime = MimeTypeMap.getSingleton();
    return mime.getExtensionFromMimeType(c.getType(uri));

}


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == PICK_IMAGE)) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }
    }

    private void uploadImg(String comm) {
        final String comments = comm;
        StorageReference fileRef = stRef.child("Images/samples/" + String.valueOf(id) + "." + getFileExtension(imageUri));


        fileRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        uploadImageAndComment upImge = new uploadImageAndComment("Free_Samples" + String.valueOf(id), taskSnapshot.getDownloadUrl().toString(), comments);
                        sampleRef.setValue(upImge);
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Sample Saved..", Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Sample Upload Failed..", Toast.LENGTH_LONG).show();
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
