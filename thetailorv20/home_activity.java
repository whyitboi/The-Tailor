package com.example.junior.thetailorv20;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;

public class home_activity extends AppCompatActivity {
    private Button btnClient, btnDesign, btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        btnClient = findViewById(R.id.btnClient);
        btnDesign = findViewById(R.id.btnDesign);
        btnPay = findViewById(R.id.btnPay);

        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(home_activity.this, clients_home_activity.class);//testtwo
                startActivity(intent);
                finish();
            }
        });
        btnDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_activity.this, design_home_activity.class);//designActivity
                startActivity(intent);
                finish();
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_activity.this, payment_home_activity.class);//payActivity
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

