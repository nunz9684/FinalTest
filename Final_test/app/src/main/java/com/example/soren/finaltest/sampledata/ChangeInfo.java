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
import android.widget.TextView;

import com.example.soren.finaltest.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class ChangeInfo extends AppCompatActivity {

    EditText gradeA,gradeB,gradeC,st_ID_new,st_ID_Ref;
    Button btn_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        Intent intent = getIntent();
        final String user_id = intent.getStringExtra("ID");
        gradeA=(EditText) findViewById(R.id.grade_A);
        gradeB=(EditText) findViewById(R.id.grade_B);
        gradeC=(EditText) findViewById(R.id.grade_C);
        btn_confirm = (Button) findViewById(R.id.confirm_btn);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Student").child(user_id).child("Subject");
                myRef.child("A").setValue(gradeA.getText().toString());
                myRef.child("B").setValue(gradeB.getText().toString());
                myRef.child("C").setValue(gradeC.getText().toString());
                Log.d("St_setting", "Password has been set.");
            }
        });


    }

}
