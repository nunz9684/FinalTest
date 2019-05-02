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

import com.example.soren.finaltest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Teacher_Page extends AppCompatActivity {
    Button btn_stinfo,btn_message,btn_setting,btn_add;
    TextView user_names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("data","Teacher Page is Running.");
        btn_stinfo = (Button) findViewById(R.id.grade);
        btn_message = (Button) findViewById(R.id.message);
        btn_setting =(Button) findViewById(R.id.setting);
        user_names = (TextView) findViewById(R.id.User_name);
        btn_add = (Button) findViewById(R.id.add);
        Intent intent = getIntent();
        final String user_id = intent.getStringExtra("ID");

        read_data(user_id);


        btn_stinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Teacher_Page.this, Student_info.class);
                intent.putExtra("ID", user_id);
                startActivity(intent);

            }
        });
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Teacher_Page.this, Teacher_message.class);
                intent.putExtra("ID", user_id);
                startActivity(intent);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Teacher_Page.this, Teacher_setting.class);
                intent.putExtra("ID", user_id);
                startActivity(intent);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Teacher_Page.this, TeacherChangeSt.class);
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
