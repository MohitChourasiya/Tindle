package com.user.tindle.Fragment;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.user.tindle.Activity.MainActivity;
import com.user.tindle.Models.cards;
import com.user.tindle.R;
import com.user.tindle.databinding.FragmentMyProfileBinding;

import java.util.List;


public class MyProfileFragment extends Fragment {


   FragmentMyProfileBinding binding;

    FirebaseDatabase database;
    DatabaseReference userDb;
    String oppositeSex;
  String userSex;
    FirebaseAuth auth;
    int i;
    int seekbarValue = 0;
    ListView listView;
    List<cards> rowItems;
    String currentUid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMyProfileBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        init_UI();
        return  view;

    }

    private void init_UI() {

        database = FirebaseDatabase.getInstance();
        userDb = database.getReference().child("user_details");
        auth = FirebaseAuth.getInstance();
        currentUid = auth.getCurrentUser().getUid();







        binding.editRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();

                b.putString("message", userSex);

                EditProfileFragment ef = new EditProfileFragment();
                ef.setArguments(b);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fm_layout,ef).addToBackStack("ef");
                ft.commit();
            }
        });

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent back = new Intent(getContext(), MainActivity.class);
                startActivity(back);
            }
        });



    }






}