package com.example.soren.finaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.soren.finaltest.sampledata.St_grade;
import com.example.soren.finaltest.sampledata.Teacher_Page;
import com.example.soren.finaltest.sampledata.student_page;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText user_id,user_pass;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = (Button) findViewById(R.id.login) ;

        user_id = (EditText) findViewById(R.id.User_id);
        user_pass = (EditText) findViewById(R.id.User_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                read_data();
            }
        });
    }
        public void read_data() {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Student").child(user_id.getText().toString());
            final String user_id_in = user_pass.getText().toString();
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (user_id_in.equals( dataSnapshot.child("Password").getValue(String.class)) && dataSnapshot.child("StatusT").getValue(String.class).equals("Yes"))
                    {
                        Log.d("User","Switch to Teacher");
                        Intent intent1 = new Intent(MainActivity.this, Teacher_Page.class);
                        intent1.putExtra("ID", user_id.getText().toString());
                        startActivity(intent1);
                    }
                    else
                    {
                        Log.d("User","Switch to Student");
                        Intent intent2 = new Intent(MainActivity.this, student_page.class);
                        intent2.putExtra("ID", user_id.getText().toString());
                        startActivity(intent2);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("GetData", "Failed to read value.", databaseError.toException());
                }
            });
        }

}
