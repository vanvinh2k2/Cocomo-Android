package com.example.cocomo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cocomo.MainActivity;
import com.example.cocomo.R;
import com.example.cocomo.model.Rank;

import java.util.List;

public class RankAdapter extends BaseAdapter {
    MainActivity context;
    List<Rank> list;

    public RankAdapter(MainActivity context, List<Rank> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_text,null);
        TextView seleted = convertView.findViewById(R.id.listText);
        Rank rank = list.get(position);
        seleted.setText(rank.getTitle());
        context.category();
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_text,null);
        TextView tvText = convertView.findViewById(R.id.itemText);
        Rank rank = list.get(position);
        tvText.setText(rank.getTitle());
        return convertView;
    }
}
