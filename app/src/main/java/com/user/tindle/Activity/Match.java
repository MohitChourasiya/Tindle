package com.user.tindle.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.user.tindle.databinding.ActivityMatchBinding;

public class Match extends AppCompatActivity {
ActivityMatchBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMatchBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init_UI();
    }

    private void init_UI() {

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Match.this,HomePage.class);
                startActivity(in);
            }
        });


    }
}