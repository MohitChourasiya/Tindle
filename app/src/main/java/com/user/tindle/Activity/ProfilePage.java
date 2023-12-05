package com.user.tindle.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.tindle.Models.UserNameAndDob;
import com.user.tindle.R;
import com.user.tindle.databinding.ActivityProfilePageBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProfilePage extends AppCompatActivity {
ActivityProfilePageBinding binding;
    int year;
    int month;
    int day;
    FirebaseAuth auth;

    FirebaseUser user;
    UserNameAndDob ui ;
    DatabaseReference myRef;
    FirebaseDatabase database;
    String gen;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init_UI();
        gen = getIntent().getStringExtra("gender");

        ui = new UserNameAndDob();
        myRef = database.getReference("user_details");


        binding.txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ProfilePage.this,
                        R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        binding.txtDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                        binding.txtDate.setText(i2+ "/"+ (i1 + 1) + "/"+i);
                        String dob = binding.txtDate.getText().toString().trim();
                        ui.setDOB(dob);

                    }
                },year,month,day);

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });
    }

    private void init_UI() {
        Intent in = new Intent(ProfilePage.this,Passions.class);
        startActivity(in);
        ui = new UserNameAndDob();

        database = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        binding.submitBtn.setOnClickListener(view -> {

            String firstname = binding.firstField.getText().toString();
            String lastname = binding.lastField.getText().toString();


            Intent n = new Intent(ProfilePage.this,Passions.class);
            startActivity(n);
            if (TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname)){
                binding.firstField.setHint("enter first name");
                binding.firstField.setHintTextColor(getColor(R.color.pink));

                binding.lastField.setHint("enter Last name");
                binding.lastField.setHintTextColor(getColor(R.color.pink));

            }else{

                ui.setFirstName(firstname);
                ui.setLastName(lastname);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        myRef = database.getReference("user_details");
                        myRef.setValue(ui);
                        Toast.makeText(ProfilePage.this, "data added", Toast.LENGTH_SHORT).show();

                        Intent in = new Intent(ProfilePage.this,Passions.class);
                        startActivity(in);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(ProfilePage.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();

                    }

                });
            }

        });

    }


    }
