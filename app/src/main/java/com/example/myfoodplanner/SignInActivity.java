package com.example.myfoodplanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    TextView signup;
    Button login;
    Button loginGuest;
    FirebaseAuth mAuth;
    ImageView facebookAuth;
    ImageView googleAuth;
    ImageView twitterAuth;
    Intent intent;
    ProgressBar progressBar;
    final String guest = "GUEST";
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent();
            intent.setClass(SignInActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        signup = findViewById(R.id.signupBtn);
        login = findViewById(R.id.loginBtn);
        loginGuest = findViewById(R.id.loginAsGuestBtn);
        progressBar = findViewById(R.id.progressBarLogin);
        googleAuth = findViewById(R.id.googleAuth);

        mAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        googleAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signingGoogle();
            }
        });

        loginGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),HomeActivity.class);
                intent.putExtra(guest,guest);
                startActivity(intent);
                finish();
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),SingUpActivity.class);
                startActivity(intent);
            }
        });


    }
    void signIn(){
        progressBar.setVisibility(View.VISIBLE);
        if(!TextUtils.isEmpty(email.getText().toString())){
            if(!TextUtils.isEmpty(password.getText().toString())){
                fireBaseConnection();
            }else{
                Toast.makeText(SignInActivity.this, "Please Enter Your Password",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(SignInActivity.this, "Please Enter Your Email",
                    Toast.LENGTH_SHORT).show();
        }
    }
    void fireBaseConnection(){
        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignInActivity.this, "Login Failed, Please Try Again Later",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void signingGoogle(){
        Intent singinIntent = gsc.getSignInIntent();
        startActivityForResult(singinIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
            } catch (ApiException e) {
                Toast.makeText(this,"Something went wrong, Please try again later",Toast.LENGTH_SHORT);
            }
        }
    }

    private void goToHome() {
        finish();
        Intent intent = new Intent(SignInActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}