package com.user.tindle.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.user.tindle.Models.UserNameAndDob;
import com.user.tindle.Models.cards;
import com.user.tindle.R;

import java.util.List;

public class arrayAdapter extends ArrayAdapter<cards> {
    Context context;
    public arrayAdapter(Context context, int resourseId, List<cards> items){
        super(context,resourseId,items);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        cards card_items = getItem(position);

        if (convertView==null){

            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);
        }

        TextView name = convertView.findViewById(R.id.name);
        TextView lastname = convertView.findViewById(R.id.lastNameTxt);

        ImageView image = convertView.findViewById(R.id.image);

        lastname.setText(card_items.getLastname());
        name.setText(card_items.getName());
        Glide.with(getContext()).load(card_items.getProfileImageUrl()).into(image);
        return  convertView;
    }
}
