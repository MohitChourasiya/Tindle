package com.user.tindle.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.tindle.R;

import java.util.ArrayList;

public class AllMatchesAdapter extends RecyclerView.Adapter<AllMatchesAdapter.holder> {

    ArrayList<String> matchHolder = new ArrayList<>();

    public AllMatchesAdapter( ArrayList<String> matchHolder){this.matchHolder=matchHolder;}
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_matches,parent,false);
        return  new holder(view);   }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return matchHolder.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        public holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
