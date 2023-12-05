package com.user.tindle.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.user.tindle.Adapter.AllMatchesAdapter;
import com.user.tindle.databinding.FragmentAllMatchesBinding;

import java.util.ArrayList;


public class AllMatchesFragment extends Fragment {

FragmentAllMatchesBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAllMatchesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        init_UI();
        return view;
    }

    private void init_UI() {

        RecyclerRv();

    }

    private void RecyclerRv() {

        ArrayList<String> matchHolder = new ArrayList<>();
        matchHolder.add("1");
        matchHolder.add("1");
        matchHolder.add("1");
        matchHolder.add("1");
        matchHolder.add("1");
        matchHolder.add("1");
        matchHolder.add("1");
        binding.recyclerAllMatches.setAdapter(new AllMatchesAdapter((ArrayList<String>)matchHolder));
    }


}
