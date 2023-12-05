package com.user.tindle.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.user.tindle.R;
import com.user.tindle.databinding.ActivityPassionsBinding;

public class Passions extends AppCompatActivity {
ActivityPassionsBinding binding;
int i = 0;
int a = 0;
int b,c,d,e,f,g,h,j,k,l,m,n,o,p,z = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPassionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init_UI();
    }

    private void init_UI() {


        colourChange();
    }

    private void colourChange() {

        binding.adhunikKhel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a==1){

                    binding.adhunikKhelImg.setImageResource(R.drawable.console_pink);
                    binding.adhunikKhelTxt.setTextColor(getResources().getColor(R.color.black));
                    binding.adhunikKhel.setCardBackgroundColor(getResources().getColor(R.color.white));

                    a=0;
                }else{
                    a =1;
                    binding.adhunikKhelTxt.setTextColor(getResources().getColor(R.color.white));
                    binding.adhunikKhelImg.setImageResource(R.drawable.console_white);
                    binding.adhunikKhel.setCardBackgroundColor(getResources().getColor(R.color.pink));
                }
            }
        });


        binding.daaruCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i==1){
                    binding.daaruImg.setImageResource(R.drawable.cheers_pink);
                    binding.daaruTxt.setTextColor(getResources().getColor(R.color.black));
                    binding.daaruCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    i=0;

                }else{i=1;
                    binding.daaruImg.setImageResource(R.drawable.cheers_white);
                    binding.daaruTxt.setTextColor(getResources().getColor(R.color.white));
                    binding.daaruCard.setCardBackgroundColor(getResources().getColor(R.color.pink));
                }

            }
        });
        binding.photoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b==1){
                    binding.imgPhoto.setImageResource(R.drawable.pic_pink);
                    binding.phototxt.setTextColor(getResources().getColor(R.color.black));
                    binding.photoCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    b=0;
                }else{
                    b=1;
                    binding.imgPhoto.setImageResource(R.drawable.pic_white);
                    binding.phototxt.setTextColor(getResources().getColor(R.color.white));
                    binding.photoCard.setCardBackgroundColor(getResources().getColor(R.color.pink));
                }

            }
        });

        binding.gaanaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c==1){
                    binding.gaanaImg.setImageResource(R.drawable.music);
                    binding.gaanaTxt.setTextColor(getResources().getColor(R.color.black));
                    binding.gaanaCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    c=0;
                }else{
                    c=1;
                    binding.gaanaImg.setImageResource(R.drawable.music_white);
                    binding.gaanaTxt.setTextColor(getResources().getColor(R.color.white));
                    binding.gaanaCard.setCardBackgroundColor(getResources().getColor(R.color.pink));
                }

            }
        });
        binding.musicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (d==1){
                    binding.musicImg.setImageResource(R.drawable.voice_pink);
                    binding.musicTxt.setTextColor(getResources().getColor(R.color.black));
                    binding.musicCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    d=0;

                }else{
                    d=1;
                    binding.musicImg.setImageResource(R.drawable.voice_white);
                    binding.musicTxt.setTextColor(getResources().getColor(R.color.white));
                    binding.musicCard.setCardBackgroundColor(getResources().getColor(R.color.pink));
                }
                i++;
            }
        });
        binding.yogaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e==1){
                    binding.yogaTxt.setTextColor(getColor(R.color.black));
                    binding.yogaCard.setCardBackgroundColor(getColor(R.color.white));
                    binding.yogaImg.setImageResource(R.drawable.yoga_pink);
                    e=0;
                }else{e=1;
                    binding.yogaImg.setImageResource(R.drawable.yoga_white);
                    binding.yogaTxt.setTextColor(getColor(R.color.white));
                    binding.yogaCard.setCardBackgroundColor(getColor(R.color.pink));
                }
                i++;
            }
        });
        binding.cookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (f==1){
                    binding.cookImg.setImageResource(R.drawable.cook);
                    binding.cookTxt.setTextColor(getResources().getColor(R.color.black));
                    binding.cookCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    f=0;
                }else{f=1;
                    binding.cookImg.setImageResource(R.drawable.cook_white);
                    binding.cookTxt.setTextColor(getResources().getColor(R.color.white));
                    binding.cookCard.setCardBackgroundColor(getResources().getColor(R.color.pink));
                }
                i++;
            }
        });
        binding.tennisCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (g==1){
                    binding.tennishImg.setImageResource(R.drawable.balls_pink);
                    binding.tennishTxt.setTextColor(getResources().getColor(R.color.black));
                    binding.tennisCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    g=0;
                }else{g=1;


                    binding.tennishImg.setImageResource(R.drawable.balls_white);
                    binding.tennishTxt.setTextColor(getResources().getColor(R.color.white));
                    binding.tennisCard.setCardBackgroundColor(getResources().getColor(R.color.pink));
                }

            }
        });
        binding.runCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (h==1){
                    binding.runTxt.setTextColor(getResources().getColor(R.color.black));
                    binding.runCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    binding.runImg.setImageResource(R.drawable.running_pink);
                    h=0;
                }else{

                    h=1;
                    binding.runTxt.setTextColor(getResources().getColor(R.color.white));
                    binding.runImg.setImageResource(R.drawable.running_white);
                    binding.runCard.setCardBackgroundColor(getResources().getColor(R.color.pink));

                }
                i++;
            }
        });
        binding.swimCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (j==1){
                    binding.swipImg.setImageResource(R.drawable.swimming);
                    binding.swimTxt.setTextColor(getResources().getColor(R.color.black));
                    binding.swimCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    j=0;
                }else{


                    j=1;
                    binding.swimTxt.setTextColor(getResources().getColor(R.color.white));
                    binding.swimCard.setCardBackgroundColor(getResources().getColor(R.color.pink));
                    binding.swipImg.setImageResource(R.drawable.water_white);
                }
                i++;
            }
        });
        binding.artcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (k ==1){
                    binding.artTxt.setTextColor(getResources().getColor(R.color.black));
                    binding.artImg.setImageResource(R.drawable.paint_pink);
                    binding.artcard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    k=0;
                }else{

                    k=1;

                    binding.artImg.setImageResource(R.drawable.paint_white);
                    binding.artTxt.setTextColor(getResources().getColor(R.color.white));
                    binding.artcard.setCardBackgroundColor(getResources().getColor(R.color.pink));

                }
                i++;
            }
        });


        binding.travelcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (l==1){
                    binding.travelImg.setImageResource(R.drawable.mountain_pink);
                    binding.travelTxt.setTextColor(getResources().getColor(R.color.black));
                    binding.travelcard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    l=0;
                }else{

                    l=1;


                    binding.travelImg.setImageResource(R.drawable.mountain_white);
                    binding.travelTxt.setTextColor(getResources().getColor(R.color.white));
                    binding.travelcard.setCardBackgroundColor(getResources().getColor(R.color.pink));
                }
                i++;
            }
        });

        binding.externalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m ==1){
                    binding.externalImg.setImageResource(R.drawable.extreme);
                    binding.externalTxt.setTextColor(getResources().getColor(R.color.black));
                    binding.externalCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    m=0;


                }else{

                    m=1;
                    binding.externalImg.setImageResource(R.drawable.extreme_white);
                    binding.externalTxt.setTextColor(getResources().getColor(R.color.white));
                    binding.externalCard.setCardBackgroundColor(getResources().getColor(R.color.pink));
                }
                i++;
            }
        });

        binding.shopCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (n==1){
                    binding.shopImg.setImageResource(R.drawable.shop_pink);
                    binding.shopTxt.setTextColor(getResources().getColor(R.color.black));
                    binding.shopCard.setCardBackgroundColor(getResources().getColor(R.color.white));
                    n=0;
                }else{
                    n=1;
                    binding.shopImg.setImageResource(R.drawable.shop);
                    binding.shopTxt.setTextColor(getResources().getColor(R.color.white));
                    binding.shopCard.setCardBackgroundColor(getResources().getColor(R.color.pink));


                }
                i++;
            }
        });

        binding.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Passions.this, Friends.class);
                startActivity(in);
            }
        });
    }
}