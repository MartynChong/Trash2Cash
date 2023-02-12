package com.example.trash2cash;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.trash2cash.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

//    Button button;
    FirebaseAuth auth;
    FirebaseUser user;

    int points, total;
    String username;
    String email;

//    FirebaseDatabase db;
//    DatabaseReference reference;

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://trash2cash-82489-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference reference = db.getReference().child("Users").child("username");

    CardView btScan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tView1 = findViewById(R.id.welcomeText);
        TextView tView2 = findViewById(R.id.pointsText);

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
                tView1.setText("Welcome back, " + username);
                tView2.setText("Points:  " + points);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        if (user == null){
//            Intent intent = new Intent(getApplicationContext(), Login.class);
//            startActivity(intent);
//            finish();
//        }

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        ImageView menuView = findViewById(R.id.profileIcon);
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
                finish();
            }
        });

        btScan = findViewById(R.id.scanQR);
        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(
                        MainActivity.this
                );
                intentIntegrator.setPrompt("For flash use volume up key");
                //Set beep
                intentIntegrator.setBeepEnabled(true);
                //Locked orientation
                intentIntegrator.setOrientationLocked(true);
                //Set capture activity
                intentIntegrator.setCaptureActivity(Capture.class);
                //Initiate scan
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Initialise intent results
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data);
        if (intentResult.getContents() != null){
            //When result content is not null
            //Initialise alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this
            );
            //Set title
            builder.setTitle("Result");
            //Set message
            int earnedP = Integer.parseInt(intentResult.getContents());

            builder.setMessage(Integer.toString(earnedP));

            System.out.println(user.toString());

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
                    total = earnedP + points;
                    reference.child(username).child("points").setValue(Integer.toString(total));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            //reference.child(username).child("points").setValue(Integer.toString(total));

            //Set positive button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Dismiss dialog
                    dialogInterface.dismiss();
                }
            });
            //Show alert dialog
            builder.show();
        }else {
            //When result content is null, display toast
            Toast.makeText(getApplicationContext(), "Ooops, no scan", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
