package com.example.trash2cash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trash2cash.databinding.ActivityMainBinding;
import com.example.trash2cash.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    TextInputEditText editTextUsername, editTextEmail, editTextPassword;
    Button buttonReg;
    FirebaseAuth mAuth;
    TextView textView;

    ActivityRegisterBinding binding;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_register);
        editTextUsername = findViewById(R.id.username);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.btn_reg);
        mAuth  = FirebaseAuth.getInstance();

        textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password, username;

                username = editTextUsername.getText().toString();
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();

                if (TextUtils.isEmpty(username)){
                    Toast.makeText(Register.this, "Enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Map<String, Object> user = new HashMap<>();
                                    user.put(username, new User(username, email));
                                    db = FirebaseDatabase.getInstance("https://trash2cash-82489-default-rtdb.europe-west1.firebasedatabase.app/");
                                    reference = db.getReference("Users");
                                    reference.child("username").updateChildren(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(Register.this, "Account Added.",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Login.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.e("Firebase Auth", "Sign-in failed", task.getException());
                                    Toast.makeText(Register.this, task.getException().toString(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}
