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
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.webkit.MimeTypeMap;
        import android.widget.Button;
        import android.widget.ImageView;
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

        import static android.content.ContentValues.TAG;
import static com.example.junior.thetailorv20.add_client_fragment.userID;
import static com.example.junior.thetailorv20.add_client_fragment.storedClientInfo;
import com.example.junior.thetailorv20.client_info;

public class client_design_activity extends AppCompatActivity {

    private ImageView imageView1, imageView2;
    private static final int PICK_IMAGE=100, PICK_IMAGE1 = 1;
    private Uri imageUri1, imageUri2;
    private ProgressDialog progressDialog;
    private DatabaseReference designRef, sampleRef;
    private StorageReference stRef;
    private FirebaseAuth auth;
    private Button btnUpDesign, btnUpSample, saveDesign;
    private long id = System.currentTimeMillis();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_design_layout);

        //pathArray = new ArrayList<>();
        progressDialog = new ProgressDialog(client_design_activity.this);
        btnUpDesign = findViewById(R.id.button3);
        btnUpSample = findViewById(R.id.button);
        saveDesign = findViewById(R.id.saveDesign);
        imageView1 = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView3);



        designRef = FirebaseDatabase.getInstance().getReference("Designs/" + String.valueOf(userID) + "/" + String.valueOf(id));
        sampleRef = FirebaseDatabase.getInstance().getReference("Samples/" + String.valueOf(userID) + "/" + String.valueOf(id));
        stRef = FirebaseStorage.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        checkFilePermissions();
        addFilePaths();




        btnUpDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });
        btnUpSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery1();

            }
        });
        saveDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImg(userID);
            }
        });

    }
    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    private void openGallery1(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE1);
    }
    private void addFilePaths() {
        Log.d(TAG, "addFilePaths: Adding File Paths");
        String path = System.getenv("EXTERNAL_STORAGE");

    }

    private void checkFilePermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (permissionCheck != 0) {
                this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }
        }else{
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if((resultCode == RESULT_OK) && (requestCode == PICK_IMAGE)){
            imageUri1 = data.getData();
            imageView1.setImageURI(imageUri1);
        }else
        if((resultCode == RESULT_OK) && (requestCode == PICK_IMAGE1)){
            imageUri2 = data.getData();
            imageView2.setImageURI(imageUri2);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR =  this.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadImg(int uID){
        //int uID = String.valueOf(uID);
        final String uId = String.valueOf(uID);
        StorageReference fileRef = stRef.child("Images/client_images/"+uId+"."+getFileExtension(imageUri1));
        final StorageReference fileRef1 = stRef.child("Images/client_images/"+uId+"."+getFileExtension(imageUri2));
        progressDialog.setMessage("Saving Information");
                        fileRef.putFile(imageUri1)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        progressDialog.show();
                                        uploadImage upImge = new uploadImage("sample" + uId + String.valueOf(System.currentTimeMillis()), taskSnapshot.getDownloadUrl().toString());
                                        sampleRef.setValue(upImge);
                                        storedClientInfo.setSampleCount();;
                                        fileRef1.putFile(imageUri2)
                                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                    @Override
                                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                        uploadImage upImge = new uploadImage("design" + uId + String.valueOf(System.currentTimeMillis()), taskSnapshot.getDownloadUrl().toString());
                                                        designRef.setValue(upImge);
                                                        storedClientInfo.setDesignCount();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(client_design_activity.this, "Design Upload Failed..", Toast.LENGTH_LONG).show();
                                                       }
                                                });
                                        // StorageReference fileRef = stRef.child("images/client_images"+String.valueOf(clientInfo[0].getID())+"." + getFileExtension(imageUri));
                                        progressDialog.dismiss();
                                        Toast.makeText(client_design_activity.this, "Information Saved..", Toast.LENGTH_LONG).show();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(client_design_activity.this, "Design or Sample Upload Failed..", Toast.LENGTH_LONG).show();
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
