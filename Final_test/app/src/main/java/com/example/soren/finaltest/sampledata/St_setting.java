package com.example.soren.finaltest.sampledata;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.soren.finaltest.MainActivity;
import com.example.soren.finaltest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class St_setting extends AppCompatActivity {

    EditText old_pass, new_pass1, new_pass2;
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        final String user_id = intent.getStringExtra("ID");


        old_pass = (EditText) findViewById(R.id.user_passRef);
        new_pass1 = (EditText) findViewById(R.id.user_pass2);
        new_pass2 = (EditText) findViewById(R.id.user_pass3);
        btn_confirm = (Button) findViewById(R.id.confirm_btn);


        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new_pass1.getText().toString().equals(new_pass2.getText().toString())) {
                    send_data(user_id);
                    Intent intent = new Intent(St_setting.this, student_page.class);
                    intent.putExtra("ID", user_id);
                    startActivity(intent);
                }
            }
        });
    }

    public void send_data(String x) {
        Log.d("St_setting",x);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Student").child(x).child("Password");
        myRef.setValue(new_pass1.getText().toString());
        Log.d("St_setting", "Password has been set.");
    }
}