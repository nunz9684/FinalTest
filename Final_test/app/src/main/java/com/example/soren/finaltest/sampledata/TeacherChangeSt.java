package com.example.soren.finaltest.sampledata;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.soren.finaltest.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherChangeSt extends AppCompatActivity {
    EditText name,last,gender,age,level,st_id,password;
    Button btn_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_change_st);
        Intent intent = getIntent();
        final String user_id = intent.getStringExtra("ID");
    name = (EditText) findViewById(R.id.name);
    last = (EditText) findViewById(R.id.lastname);
    gender = (EditText) findViewById(R.id.gender);
    age = (EditText) findViewById(R.id.age);
    level = (EditText) findViewById(R.id.level);
    st_id = (EditText) findViewById(R.id.st_id);
    password = (EditText) findViewById(R.id.password);

    btn_confirm = (Button) findViewById(R.id.confirm_btn);
    btn_confirm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Student").child(user_id);
            myRef.child("Password").setValue(password.getText().toString());
             myRef = database.getReference("Student").child(user_id);
            myRef.child("Name").setValue(name.getText().toString());
             myRef = database.getReference("Student").child(user_id);
            myRef.child("Lassname").setValue(last.getText().toString());
             myRef = database.getReference("Student").child(user_id);
            myRef.child("Gender").setValue(gender.getText().toString());
             myRef = database.getReference("Student").child(user_id);
            myRef.child("Age").setValue(age.getText().toString());
             myRef = database.getReference("Student").child(user_id);
            myRef.child("Level").setValue(level.getText().toString());
             myRef = database.getReference("Student").child(user_id);
            myRef.child("StudentID").setValue(st_id.getText().toString());

        }
    });

    }

}
