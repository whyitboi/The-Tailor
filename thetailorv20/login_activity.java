package com.example.junior.thetailorv20;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;


public class login_activity extends AppCompatActivity {
    private EditText username, password;
    private Button login;
    private TextView frgtpwd, info;
    private int counter = 5;
    private FirebaseAuth mAuth;
    private ProgressDialog proDiag;

   /*@Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        username = findViewById(R.id.etUserName);
        password = findViewById(R.id.etUserPswd);
        login = findViewById(R.id.btnLogin);
        frgtpwd = findViewById(R.id.tvForgotPswd);
        //info = findViewById(R.id.tvinfo);

        // info.setText("");
        mAuth = FirebaseAuth.getInstance();
        proDiag = new ProgressDialog(this);
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(login_activity.this, home_activity.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(), password.getText().toString());
            }
        });


    }

    private void validate(String uname, String passwrd) {
        proDiag.setMessage("Signing In");
        proDiag.show();
        mAuth.signInWithEmailAndPassword(uname, passwrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    proDiag.dismiss();
                    Toast.makeText(login_activity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login_activity.this, home_activity.class));
                }else
                {
                    Toast.makeText(login_activity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    proDiag.dismiss();
                    if(counter == 0){
                        login.setEnabled(false);
                    }
                }
            }
        });
    }

}
