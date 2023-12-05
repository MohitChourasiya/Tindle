package com.user.tindle.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.user.tindle.R;
import com.user.tindle.databinding.ActivityVerificationBinding;

public class Verification extends AppCompatActivity {
 ActivityVerificationBinding binding;
 int i,j,k,l,m=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init_UI();
    }

    private void init_UI() {
        EditFieldBackground();

        binding.pwdField1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i==1){
                    binding.pwdField1.setBackgroundColor(getResources().getColor(R.color.pink));
                    binding.pwdField1.setBackgroundColor(getResources().getColor(R.color.pink));
                    i=0;
                }else {i=1;
                    binding.pwdField1.setBackgroundColor(getResources().getColor(R.color.white));
                }

            }
        });


    }

    private void EditFieldBackground() {
        binding.pwdField1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (j==1){
                    binding.pwdField1.setBackgroundColor(getResources().getColor(R.color.pink));
                    j=0;
                }else {j=1;
                    binding.pwdField1.setBackgroundColor(getResources().getColor(R.color.white));
                }

            }
        });

        binding.pwdField2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (k==1){
                    binding.pwdField2.setBackgroundColor(getResources().getColor(R.color.pink));
                    k=0;
                }else {k=1;
                    binding.pwdField2.setBackgroundColor(getResources().getColor(R.color.white));
                }

            }
        });

        binding.pwdField3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (l==1){
                    binding.pwdField3.setBackgroundColor(getResources().getColor(R.color.pink));
                    l=0;
                }else {l=1;
                    binding.pwdField3.setBackgroundColor(getResources().getColor(R.color.white));
                }

            }
        });

        binding.pwdField4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m==1){
                    binding.pwdField4.setBackgroundColor(getResources().getColor(R.color.pink));
                    m=0;
                }else {
                    m=1;
                    binding.pwdField4.setBackgroundColor(getResources().getColor(R.color.white));
                }

                i++;
            }
        });

        binding.sendOtpAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Verification.this, ProfilePage.class);
                startActivity(in);
            }
        });



    }
}