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
import android.widget.TextView;

import com.example.soren.finaltest.MainActivity;
import com.example.soren.finaltest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class student_page extends AppCompatActivity {

    Button btn_grade,btn_message,btn_setting;
    TextView user_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_grade = (Button) findViewById(R.id.grade);
        btn_message = (Button) findViewById(R.id.message);
        btn_setting =(Button) findViewById(R.id.setting);
        user_names = (TextView) findViewById(R.id.User_name);
        Intent intent = getIntent();
        final String user_id = intent.getStringExtra("ID");

        read_data(user_id);


        btn_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_page.this, St_grade.class);
                startActivity(intent);

            }
        });
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_page.this, St_message.class);
                startActivity(intent);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_page.this, St_setting.class);
                intent.putExtra("ID", user_id);
                startActivity(intent);
            }
        });
    }

    public void read_data(String x) {
        Log.d("User",x);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Student").child(x).child("Name");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String user_name=dataSnapshot.getValue(String.class);
                user_names.setText(user_name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("GetData", "Failed to read value.", databaseError.toException());
            }
        });
    }

}
