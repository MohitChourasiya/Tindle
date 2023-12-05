package com.user.tindle.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.user.tindle.Models.Sessions;
import com.user.tindle.Models.UserNameAndDob;
import com.user.tindle.R;
import com.user.tindle.databinding.FragmentEditProfileBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class EditProfileFragment extends Fragment {

   FragmentEditProfileBinding binding;
   FirebaseUser user;
    String userSex;
    ImageView mProfileimage;
   private FirebaseAuth auth;
   private DatabaseReference mCostumerDatabase;
   private  String userId,name,profession,location,about,ProfileUrl;
   private Uri resultUri;
   Bundle bundle;
    String sex;
    UserNameAndDob ui;
    Sessions sessions;


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable    Bundle savedInstanceState) {
       binding = FragmentEditProfileBinding.inflate(inflater,container,false);
       View view = binding.getRoot();
       init_UI();
       return view;

    }

    private void init_UI() {


        ui = new UserNameAndDob();

        sessions = new Sessions(getActivity());


        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();
        mCostumerDatabase = FirebaseDatabase.getInstance().getReference().child("user_details").child(sessions.getData(Sessions.Gender)).child(userId);



       getUserinfo();
       binding.imageProfile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_PICK);
               intent.setType("image/*");
               startActivityForResult(intent,1);
           }
       });
       mProfileImage();



       binding.submitBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               saveUserInformation();
           }});


    }

    private void mProfileImage() {

    }

    private void getUserinfo() {
       mCostumerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if (snapshot.exists() && snapshot.getChildrenCount()>0){
                   Map<String,Object> map = (Map<String,Object>) snapshot.getValue();
                   if (map.get("name")!= null){name =map.get("name").toString();  binding.userName.setText(name); }
                   if (map.get("profession")!= null){profession =map.get("profession").toString();  binding.occupationTxt.setText(profession); }
                   if (map.get("location")!= null){location =map.get("location").toString();  binding.locationAddTxt.setText(location); }
                   if (map.get("about")!= null){about =map.get("about").toString();  binding.aboutDesc.setText(about); }
                   if (map.get("ProfileUrl")!= null){ProfileUrl =map.get("ProfileUrl").toString();
                       Glide.with(getActivity()).load(ProfileUrl).into(binding.imageProfile);


                   }

               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
    }

    private void saveUserInformation() {
       name = binding.userName.getText().toString();
       profession = binding.occupationTxt.getText().toString();
       location = binding.locationAddTxt.getText().toString();
       about = binding.aboutDesc.getText().toString();



        Map userInfo =new HashMap();
        userInfo.put("name", name);
        userInfo.put("profession", profession);
        userInfo.put("location", location);
        userInfo.put("about", about);
        mCostumerDatabase.updateChildren(userInfo);

        if (resultUri != null){
            StorageReference filepath = FirebaseStorage.getInstance().getReference().child("profileImage").child(userId);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),resultUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,20,bas);
            byte[] data = bas.toByteArray();
            UploadTask uploadTask = filepath.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    getActivity().finish();
                }
            });


            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();

                    Map userInfo =new HashMap();
                    userInfo.put("ProfileUrl", downloadUrl.toString());
                    mCostumerDatabase.updateChildren(userInfo);
                    getActivity().finish();
                    return;
                }
            });
        }else {
            getActivity().finish();
        }

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==1 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            binding.imageProfile.setImageURI(resultUri);
        }
    }
}