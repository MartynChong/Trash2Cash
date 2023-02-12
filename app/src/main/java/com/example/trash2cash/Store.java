package com.example.trash2cash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Store extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    int points, total;
    String username;
    String email;

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://trash2cash-82489-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference reference = db.getReference().child("Users").child("username");

    RelativeLayout RL1;
    RelativeLayout RL2;
    RelativeLayout RL3;
    RelativeLayout RL4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        TextView tView1 = findViewById(R.id.points);

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
                tView1.setText("Points: " + points);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        RL1 = findViewById(R.id.relativelayout);
        RL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        if (points < 400) {
                            Toast.makeText(Store.this, "You have too little points!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    Store.this
                            );
                            total = points - 400;
                            reference.child(username).child("points").setValue(Integer.toString(total));
                            builder.setTitle("Congratulations!");
                            builder.setMessage("Thank you for your purchase!" + "\n" + "Here is your voucher code: " + "\n" + "681295" + "\n" +
                                    "Or check your profile for all of your voucher codes.");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Dismiss dialog
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(getApplicationContext(), Store.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            //Show alert dialog
                            builder.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        RL2 = findViewById(R.id.relativelayout2);
        RL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        if (points < 100) {
                            Toast.makeText(Store.this, "You have too little points!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    Store.this
                            );
                            total = points - 100;
                            reference.child(username).child("points").setValue(Integer.toString(total));
                            builder.setTitle("Congratulations!");
                            builder.setMessage("Thank you for your purchase!" + "\n" + "Here is your voucher code: " + "\n" + "749223" + "\n" +
                                    "Or check your profile for all of your voucher codes.");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Dismiss dialog
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(getApplicationContext(), Store.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            //Show alert dialog
                            builder.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        RL3 = findViewById(R.id.relativelayout3);
        RL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        if (points < 300) {
                            Toast.makeText(Store.this, "You have too little points!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    Store.this
                            );
                            total = points - 300;
                            reference.child(username).child("points").setValue(Integer.toString(total));
                            builder.setTitle("Congratulations!");
                            builder.setMessage("Thank you for your purchase!" + "\n" + "Here is your voucher code: " + "\n" + "091126"+ "\n" +
                                    "Or check your profile for all of your voucher codes.");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Dismiss dialog
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(getApplicationContext(), Store.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            //Show alert dialog
                            builder.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        RL4 = findViewById(R.id.relativelayout4);
        RL4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        if (points < 400) {
                            Toast.makeText(Store.this, "You have too little points!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    Store.this
                            );
                            total = points - 400;
                            reference.child(username).child("points").setValue(Integer.toString(total));
                            builder.setTitle("Congratulations!");
                            builder.setMessage("Thank you for your purchase!" + "\n" + "Here is your voucher code: " + "\n" + "780921" + "\n" +
                                    "Or check your profile for all of your voucher codes.");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Dismiss dialog
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(getApplicationContext(), Store.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            //Show alert dialog
                            builder.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}