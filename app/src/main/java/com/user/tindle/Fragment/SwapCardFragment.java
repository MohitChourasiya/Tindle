package com.user.tindle.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.user.tindle.Activity.HomePage;
import com.user.tindle.Activity.Match;
import com.user.tindle.Adapter.arrayAdapter;
import com.user.tindle.Models.Sessions;
import com.user.tindle.Models.cards;
import com.user.tindle.ProfileFragment;
import com.user.tindle.R;
import com.user.tindle.databinding.FragmentSwapCardBinding;

import java.util.ArrayList;
import java.util.List;


public class SwapCardFragment extends Fragment {

    FragmentSwapCardBinding binding;
    Dialog dialog;
    private cards card_data[];

    private  arrayAdapter arrayAdapter;
    FirebaseDatabase database;
    DatabaseReference userDb;
    String oppositeSex;
    private String userSex;
    FirebaseAuth auth;
    int i;
    int seekbarValue = 0;
    ListView listView;
    List<cards> rowItems;
    String currentUid;
    Sessions sessions;




    @Nullable
    @Override
    public View onCreateView( @Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable   Bundle savedInstanceState) {

        binding = FragmentSwapCardBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        init_UI();

        return  view;



    }

    private void init_UI() {

     sessions = new Sessions(getActivity());






        database = FirebaseDatabase.getInstance();
        userDb = database.getReference().child("user_details");
        auth = FirebaseAuth.getInstance();
        currentUid = auth.getCurrentUser().getUid();

        sessions.setData(Sessions.Gender,userSex);
        dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.popup_filter);
        dialog.setCancelable(true);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource((R.drawable.roundcorner_filter_popup));
        CardView applybtn = dialog.findViewById(R.id.submitBtn);
        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        binding.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        TextView kilometer = dialog.findViewById(R.id.km);
        SeekBar seekBar = dialog.findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                seekbarValue = i;
                kilometer.setText(seekbarValue + "km");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                kilometer.setText(seekbarValue + "km");

            }
        });



        rowItems = new ArrayList<cards>();

        arrayAdapter = new arrayAdapter(getContext(), R.layout.item, rowItems );
        binding.frame.setAdapter(arrayAdapter);
        binding.frame.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                cards obj = (cards)dataObject;
                String userId = obj.getUserId();
                userDb.child(oppositeSex).child(userId).child("connections").child("nope").child(currentUid).setValue(true);
                /*Toast.makeText(getContext(), "Left!", Toast.LENGTH_SHORT).show();*/

            }

            @Override
            public void onRightCardExit(Object dataObject) {

                cards obj = (cards)dataObject;
                String userId = obj.getUserId();
                userDb.child(oppositeSex).child(userId).child("connections").child("yes").child(currentUid).setValue(true);
                isConnectionMatch(userId);
                /* Toast.makeText(getContext(), "Right!", Toast.LENGTH_SHORT).show();*/

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {


            }

            @Override
            public void onScroll(float v) {

            }
        });

        binding.frame.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {


              Toast.makeText(getContext(),"Clicked",Toast.LENGTH_SHORT);

            }
        });

        binding.rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.frame.getTopCardListener().selectRight();
            }
        });

        binding.leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.frame.getTopCardListener().selectLeft();

            }
        });

        binding.favBtn.setOnClickListener(view -> {
            Intent p = new Intent(getContext(), Match.class);
            startActivity(p);
           /* Toast.makeText(getContext(),"Added to favorite profile list",Toast.LENGTH_LONG).show();*/

        });

        CheckUserSex();



    }//init ui finished

    private void isConnectionMatch(String userId) {
        DatabaseReference currentUserConnectionDb =userDb.child(userSex).child(currentUid).child("connections").child("yes").child(userId);
        currentUserConnectionDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Toast.makeText(getContext(),"new connection",Toast.LENGTH_SHORT);
                    userDb.child(oppositeSex).child(snapshot.getKey()).child("connections").child("matches").child(currentUid).setValue(true);
                    userDb.child(userSex).child(currentUid).child("connections").child("matches").child(snapshot.getKey()).setValue(true);
                    Intent o = new Intent(getActivity(),Match.class);
                    startActivity(o);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }


    private void CheckUserSex()
    {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference maledb = database.getReference().child("user_details").child("male");
        maledb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getKey().equals(user.getUid())){
                    userSex = "male";
                    oppositeSex="female";
                    GetOppositeSexUser();}
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference femaledb = database.getReference().child("user_details").child("female");
        femaledb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getKey().equals(user.getUid())){
                    userSex = "female";
                    oppositeSex="male";
                    GetOppositeSexUser();}
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }//checked user sex finished

    private void GetOppositeSexUser() {
        DatabaseReference oppositeSexDb = database.getReference().child("user_details").child(oppositeSex);
        oppositeSexDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists() && !snapshot.child("connections").child("nope").hasChild(currentUid) &&  !snapshot.child("connections").child("yes").hasChild(currentUid))
                {
                    cards item = new cards(snapshot.getKey(), snapshot.child("firstName").getValue().toString(),snapshot.child("lastName").getValue().toString(),snapshot.child("profileImageUrl").getValue().toString());
                    rowItems.add(item);
                   /* snapshot.child("lastName").getValue().toString();*/

                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }// oppositesex finish


}