package com.example.myfoodplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    GoogleSignInAccount account;
    BottomNavigationView bottomNavigationView;
    BottomNavigationView bottomNavigationViewGuest;
    BottomNavigationView bottomNavigationNoCon;
    NavController navController;
    boolean guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        account = GoogleSignIn.getLastSignedInAccount(this);
        Intent intent = getIntent();
        guest = intent.getBooleanExtra("guest",true);
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationViewGuest = findViewById(R.id.bottomNavGuest);
        bottomNavigationNoCon = findViewById(R.id.bottomNavNoInternet);
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);

//        if(guest){
//            NavigationUI.setupWithNavController(bottomNavigationView,navController,false);
//        }else{
//            bottomNavigationViewGuest.setVisibility(View.VISIBLE);
//            bottomNavigationView.setVisibility(View.GONE);
//            NavigationUI.setupWithNavController(bottomNavigationViewGuest,navController,false);
//        }

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = connectivityManager.getActiveNetwork();
        if (network != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            if (capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))) {
                if(guest){
                    NavigationUI.setupWithNavController(bottomNavigationView,navController,false);
                }else{
                    bottomNavigationViewGuest.setVisibility(View.VISIBLE);
                    bottomNavigationView.setVisibility(View.GONE);
                    NavigationUI.setupWithNavController(bottomNavigationViewGuest,navController,false);
                }
            } else {
                bottomNavigationNoCon.setVisibility(View.VISIBLE);
                bottomNavigationViewGuest.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.GONE);
                NavigationUI.setupWithNavController(bottomNavigationNoCon,navController,false);
            }
        } else {
            bottomNavigationNoCon.setVisibility(View.VISIBLE);
            bottomNavigationViewGuest.setVisibility(View.GONE);
            bottomNavigationView.setVisibility(View.GONE);
            NavigationUI.setupWithNavController(bottomNavigationNoCon,navController,false);
        }

    }
}