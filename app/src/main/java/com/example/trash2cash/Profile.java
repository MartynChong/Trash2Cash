package com.example.trash2cash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    int points, total;
    String username;
    String email;

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://trash2cash-82489-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference reference = db.getReference().child("Users").child("username");

    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView profile = (TextView) findViewById(R.id.profile);
        TextView emailAdd = (TextView) findViewById(R.id.emailAdd);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.child("email").getValue().toString().equals(user.getEmail())) {
                        points = Integer.parseInt(snapshot.child("points").getValue().toString());
                        username = snapshot.child("username").getValue().toString();
                        email = snapshot.child("email").getValue().toString();
                    }
                }
                profile.setText(username + "'s Profile" );
                emailAdd.setText("Email: " + "\n" + email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}