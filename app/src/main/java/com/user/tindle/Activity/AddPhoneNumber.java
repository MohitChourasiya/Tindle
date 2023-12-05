package com.user.tindle.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.user.tindle.databinding.ActivityAddPhoneNumberBinding;

public class AddPhoneNumber extends AppCompatActivity {
ActivityAddPhoneNumberBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddPhoneNumberBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        init_UI();
    }

    private void init_UI() {

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in =new Intent(AddPhoneNumber.this, Verification.class);
                startActivity(in);
            }
        });
    }
}