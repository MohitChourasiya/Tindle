package com.user.tindle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.user.tindle.Fragment.EditProfileFragment;
import com.user.tindle.Fragment.MyProfileFragment;
import com.user.tindle.databinding.FragmentMyProfileBinding;
import com.user.tindle.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {


    FragmentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        init_UI();
        return  view;

    }

    private void init_UI() {

        binding.profileRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProfileFragment ef = new MyProfileFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fm_layout,ef).addToBackStack("ef");
                ft.commit();
            }
        });

    }
}