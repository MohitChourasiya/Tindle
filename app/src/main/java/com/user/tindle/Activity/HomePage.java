package com.user.tindle.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.user.tindle.Fragment.AllMatchesFragment;
import com.user.tindle.Fragment.MessagesFragment;
import com.user.tindle.Fragment.MyProfileFragment;
import com.user.tindle.Fragment.SwapCardFragment;
import com.user.tindle.ProfileFragment;
import com.user.tindle.R;
import com.user.tindle.databinding.ActivityHomePageBinding;

public class HomePage extends AppCompatActivity {
ActivityHomePageBinding binding;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init_UI();

        binding.bottomNav.setSelectedItemId(R.id.swapActivity);


    }

    private void init_UI() {


        Bundle data = this.getIntent().getExtras();






        binding.bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.swapActivity:
                        SwapCardFragment cardFragment = new SwapCardFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fm_layout,cardFragment).commit();

                        return true;
                    case R.id.allMatchesActivity:
                        AllMatchesFragment matchesFragment = new AllMatchesFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fm_layout,matchesFragment).commit();
                        return  true;


                    case R.id.messageActivity:
                        MessagesFragment messagesFragment = new MessagesFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fm_layout,messagesFragment).commit();
                        return  true;

                    case R.id.userActivity:
                        MyProfileFragment pf = new MyProfileFragment();

                        getSupportFragmentManager().beginTransaction().replace(R.id.fm_layout,pf).commit();
                        return true;

                }
                return false;
            }
        });

    }
}