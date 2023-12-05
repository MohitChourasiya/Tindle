package com.user.tindle.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.user.tindle.R;
import com.user.tindle.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    Handler sliderHandler = new Handler();
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init_UI();

        ArrayList<String> description = new ArrayList<>();

        viewPager2 = findViewById(R.id.viewPagerImageSlider);
        List<SliderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.image,"Algorithm","Users going through a vetting process to ensure you never match with bots."));
        sliderItems.add(new SliderItems(R.drawable.girl_t,"Matches","We match you with people that have a\n" +
                "large array of similar interests."));
        sliderItems.add(new SliderItems(R.drawable.girl_o,"Premium","Sign up today and enjoy the first month\n" +
                "of premium benefits on us."));

        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        binding.dotsIndicator.attachTo(viewPager2);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000);
            }
            private Runnable sliderRunnable = new Runnable() {
                @Override
                public void run() {
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                }
            };
        });
    }

    private void init_UI() {
binding.submitBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent in = new Intent(MainActivity.this, Signup.class);
        startActivity(in);
    }
});


binding.signupbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent n = new Intent(MainActivity.this, Signup.class);
        startActivity(n);
    }
});

    }}




