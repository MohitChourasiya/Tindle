package com.user.tindle.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.user.tindle.Adapter.MessageAdapter;
import com.user.tindle.R;
import com.user.tindle.databinding.FragmentMessagesBinding;

import java.util.ArrayList;


public class MessagesFragment extends Fragment {

FragmentMessagesBinding binding;

    @Nullable
    @Override
    public View onCreateView( @Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable     Bundle savedInstanceState) {

        binding = FragmentMessagesBinding.inflate(inflater,container, false);
        View view = binding.getRoot();
        init_UI();
        return view;
    }

    private void init_UI() {

        ArrayList<String> chatHolder = new ArrayList<>();
        chatHolder.add("1");
        chatHolder.add("1");
        chatHolder.add("1");

        chatHolder.add("1");

/*
        binding.recyclerMessage.setAdapter(new MessageAdapter((ArrayList<String>) chatHolder));
*/


    }
}