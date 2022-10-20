package com.ass2.i190606_i190515;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText name,email,password;
    ImageButton signup;

    String genderstr;

    ImageView male;
    ImageView female;
    ImageView other;

    FirebaseDatabase database ;
    DatabaseReference reference ;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        new Handler().postDelayed(() -> {
//            startActivity(new Intent(this, MainActivity3.class));
//            finish();
//        }, 2000);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");
        mAuth=FirebaseAuth.getInstance();

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signup=findViewById(R.id.signup);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        other = findViewById(R.id.other);


        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                genderstr="Male";
                Toast.makeText(
                        MainActivity2.this,
                        "Male",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                genderstr="Female";

                Toast.makeText(
                        MainActivity2.this,
                        "female",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                genderstr="Other";

                Toast.makeText(
                        MainActivity2.this,
                        "other",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(
                                            MainActivity2.this,
                                            "Successfully Created User",
                                            Toast.LENGTH_LONG
                                    ).show();


                                    reference.push().setValue(
                                            new Profile(
                                                    name.getText().toString(),
                                                    email.getText().toString(),
                                                    password.getText().toString(),
                                                    genderstr
                                            )
                                    );

                                    reference.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                        }

                                        @Override
                                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                        }

                                        @Override
                                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                        }

                                        @Override
                                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

//
//                                    Toast.makeText(
//                                            MainActivity2.this,
//                                            mAuth.getCurrentUser().getUid(),
//                                            Toast.LENGTH_LONG
//                                    ).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(
                                        MainActivity2.this,
                                        "Failed to Create User",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        });
//                startActivity();
//                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!=null)
        {
            Toast.makeText(
                    MainActivity2.this,
                    user.getUid()+"",
                    Toast.LENGTH_SHORT
                    ).show();
        }
    }
}