package com.user.tindle.Activity;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.tindle.Models.Sessions;
import com.user.tindle.Models.UserNameAndDob;
import com.user.tindle.R;
import com.user.tindle.databinding.ActivityGenderSelectionBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenderSelection extends AppCompatActivity {

    ActivityGenderSelectionBinding binding;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    UserNameAndDob ui;
    int year;
    int month;
    int day;

    RadioGroup radioGroup;
    int  i =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGenderSelectionBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        init_UI();

        binding.txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(GenderSelection.this,
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
                datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE,"Save",datePickerDialog);


            }
        });

    }

    private void init_UI() {

        firebaseMethod();


        binding.skip.setOnClickListener(view -> {
            Intent in = new Intent(GenderSelection.this, Passions.class);
            startActivity(in);
        });
    }

    private void firebaseMethod() {

        ui = new UserNameAndDob();

        firebaseDatabase = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        /*String user = mAuth.getCurrentUser().getUid();*/
        /*databaseReference = firebaseDatabase.getReference("user_details").child("male").child(user.getUid());*/

        radioGroup = findViewById(R.id.radioBtn);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);

        binding.submitBtn.setOnClickListener(view -> {

            String firstname = binding.firstField.getText().toString();
            String lastname = binding.lastField.getText().toString();


            if (TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname)){
                binding.firstField.setHint("enter first name");
                binding.firstField.setHintTextColor(getColor(R.color.pink));

                binding.lastField.setHint("enter Last name");
                binding.lastField.setHintTextColor(getColor(R.color.pink));

            }else {

                ui.setFirstName(firstname);
                ui.setLastName(lastname);


                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {

                        if (binding.radioFemale.isChecked()) {
                            ui.setGender("female");
                        }
                        if (binding.radioMale.isChecked()) {
                            ui.setGender("male");
                        }if (binding.radioOther.isChecked()){
                            ui.setGender("other");
                        }
                    }
                });


                if (binding.radioFemale.isChecked()) {
                    ui.setGender("female");

                }
                if (binding.radioMale.isChecked()) {
                    ui.setGender("male");

                }if (binding.radioOther.isChecked()){
                    ui.setGender("other");
                }

                String sex = ui.getGender();
                Sessions sessions = new Sessions(this);
                sessions.setData(Sessions.Gender,sex);


                databaseReference = firebaseDatabase.getReference("user_details").child(ui.getGender()).child(user.getUid());


                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.setValue(ui);
                        Intent in = new Intent(GenderSelection.this,Passions.class);
                        startActivity(in);

                        Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        });



    }

}