package com.user.tindle.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.user.tindle.R;
import com.user.tindle.databinding.ActivityLikeDislikeSwapBinding;

import java.util.ArrayList;

public class LikeDislikeSwapActivity extends AppCompatActivity {

    ActivityLikeDislikeSwapBinding binding;
    Dialog dialog;
    ArrayList<String> al;
    ArrayAdapter<String> arrayAdapter;
    int i;
    int seekbarValue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLikeDislikeSwapBinding.inflate(getLayoutInflater());



        setContentView(binding.getRoot());

        init_UI();

    }

    private void init_UI() {

        PopupMethod();
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        al = new ArrayList<String>();
        al.add("php");
        al.add("c");
        al.add("python");
        al.add("java");

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.name, al );

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {



                Toast.makeText(LikeDislikeSwapActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                Toast.makeText(LikeDislikeSwapActivity.this, "Right!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float v) {

            }
        });

        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
/*
                (LikeDislikeSwapActivity.this, "Clicked!");
*/              Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT);

            }
        });

        binding.rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flingContainer.getTopCardListener().selectRight();
            }
        });

        binding.leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flingContainer.getTopCardListener().selectLeft();

            }
        });

        binding.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent p = new Intent(LikeDislikeSwapActivity.this, Match.class);
                startActivity(p);
                Toast.makeText(getApplication(),"Added to favorite profile list",Toast.LENGTH_LONG).show();

            }
        });




    }

    private void PopupMethod() {


        binding.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dialog = new Dialog(LikeDislikeSwapActivity.this);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.setContentView(R.layout.popup_filter);
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.roundcorner_filter_popup);
                dialog.show();

                CardView applybtn = dialog.findViewById(R.id.submitBtn);
                applybtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });



                TextView kilometer = dialog.findViewById(R.id.km);

                SeekBar seekBar = dialog.findViewById(R.id.seekbar);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                        seekbarValue = i;
                        kilometer.setText(seekbarValue+"km");

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        kilometer.setText(seekbarValue+"km");

                    }
                });


            }
        });







    }
}