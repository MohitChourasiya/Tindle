package com.user.tindle.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.user.tindle.R;
import com.user.tindle.databinding.ActivitySignupBinding;

import java.util.Arrays;
import java.util.List;

public class Signup extends AppCompatActivity {
    ActivitySignupBinding binding;
    BeginSignInRequest signInRequest;
    FirebaseAuth mAuth;
    GoogleSignInClient googleSignInClient;
    FirebaseUser user;


    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),

            new AuthUI.IdpConfig.GoogleBuilder().build(),

            new AuthUI.IdpConfig.PhoneBuilder().build()

    );

    private boolean showOneTapUI = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init_UI();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


      /*  binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser() != null) {
                    Intent in = new Intent(Signup.this, AddPhoneNumber.class);
                    startActivity(in);
                } else {
                    Toast.makeText(getApplication(), "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        });*/


    }

    private void init_UI() {


        AuthenticationMethod();



    }

    private void AuthenticationMethod() {

        mAuthStateListener = firebaseAuth -> {   //auth state listner

            user = mAuth.getCurrentUser();
            if (user != null) {
                Intent in = new Intent(Signup.this, GenderSelection.class);
                startActivity(in);
                finish();
            } else {

                AuthMethodPickerLayout custom = new AuthMethodPickerLayout.Builder(R.layout.activity_signup)
                        .setGoogleButtonId(R.id.googleBtn)
                        .setEmailButtonId(R.id.submitBtn)
                        .setPhoneButtonId(R.id.phoneBtn).build();



                startActivityForResult(

                        AuthUI.getInstance().createSignInIntentBuilder()
                                .setIsSmartLockEnabled(false).setTheme(R.style.firebaseCustom)
                                .setAvailableProviders(providers).setAuthMethodPickerLayout(custom)
                                .build(),

                        RC_SIGN_IN

                );


            }

        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(mAuthStateListener);
    }

}



