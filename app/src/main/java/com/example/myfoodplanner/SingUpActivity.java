package com.example.myfoodplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myfoodplanner.Checks.EmailValidation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.xml.validation.Validator;

public class SingUpActivity extends AppCompatActivity {
    EditText userName;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button signup;
    EmailValidation validator;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        userName = findViewById(R.id.nameTextSignup);
        email =findViewById(R.id.emailTextSignup);
        password = findViewById(R.id.passwordTextSignup);
        confirmPassword = findViewById(R.id.confirmPassText);
        signup = findViewById(R.id.signup);
        progressBar = findViewById(R.id.progressBar);
        validator = new EmailValidation();
        mAuth = FirebaseAuth.getInstance();
        intent = new Intent(this,SignInActivity.class);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if(validator.validate(email.getText().toString())){
                    if((password.getText().toString()).equals(confirmPassword.getText().toString())){
                        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SingUpActivity.this, "Signup Successfully.",
                                                    Toast.LENGTH_SHORT).show();

                                            // Sign in success, update UI with the signed-in user's information
                                            //FirebaseUser user = mAuth.getCurrentUser();
                                        } else {
                                            Toast.makeText(SingUpActivity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                    }else{
                        Toast.makeText(SingUpActivity.this, "Password Not Matched", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SingUpActivity.this, "Please Enter a Valid Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}