package com.example.soren.finaltest.sampledata;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.soren.finaltest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class St_grade extends AppCompatActivity {
    TextView grade_subA,grade_subB,grade_subC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_grade);
        grade_subA = (TextView) findViewById(R.id.grade_sub_A);
        grade_subB = (TextView) findViewById(R.id.grade_sub_B);
        grade_subC = (TextView) findViewById(R.id.grade_sub_C);
        read_data();

    }
    public void read_data() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Student");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value_a = dataSnapshot.child("57113701-7").child("Subject").child("A").getValue(String.class);
                String value_b = dataSnapshot.child("57113701-7").child("Subject").child("B").getValue(String.class);
                String value_c = dataSnapshot.child("57113701-7").child("Subject").child("C").getValue(String.class);
                grade_subA.setText(value_a);
                grade_subB.setText(value_b);
                grade_subC.setText(value_c);

        }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("GetData", "Failed to read value.", databaseError.toException());
            }
        });
    }

}
