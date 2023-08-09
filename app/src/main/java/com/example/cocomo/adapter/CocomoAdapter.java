package com.example.cocomo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocomo.DetailActivity;
import com.example.cocomo.HistoryActivity;
import com.example.cocomo.R;
import com.example.cocomo.UpdateActivity;
import com.example.cocomo.click.ClickListen;
import com.example.cocomo.model.Cocomo;

import java.util.List;

public class CocomoAdapter extends RecyclerView.Adapter<CocomoAdapter.CocomoHolder>{
    HistoryActivity content;
    List<Cocomo> arrCocomo;
    int layout;

    public CocomoAdapter(HistoryActivity content, List<Cocomo> arrCocomo, int layout) {
        this.content = content;
        this.arrCocomo = arrCocomo;
        this.layout = layout;
    }

    @NonNull
    @Override
    public CocomoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(content).inflate(layout, parent, false);
        return new CocomoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocomoHolder holder, int position) {
        holder.name.setText("Name Project: "+arrCocomo.get(position).getProjectName());
        holder.type.setText("Size Type: "+arrCocomo.get(position).getSizeType());
        holder.schedual.setText("Schedual: "+Math.round(arrCocomo.get(position).getSoftwareSchedule()*10.0)/10.0+" Months");
        holder.effort.setText("Effort: "+Math.round(arrCocomo.get(position).getSoftwareEffort()*10.0)/10.0+" Person-months");
        holder.cost.setText("Cost: $"+Math.round(arrCocomo.get(position).getCost()));

        holder.setClickListen(new ClickListen() {
            @Override
            public void setOnCLickListen(View v, int position, int value) {
                if(value == 1){
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(content, DetailActivity.class);
                    bundle.putSerializable("cocomo", arrCocomo.get(position));
                    intent.putExtra("data", bundle);
                    content.startActivity(intent);
                }else if(value == 2){
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(content, UpdateActivity.class);
                    bundle.putSerializable("cocomo", arrCocomo.get(position));
                    intent.putExtra("data", bundle);
                    content.startActivity(intent);
                }else{
                    content.delete(arrCocomo.get(position).get_id());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrCocomo.size();
    }

    class CocomoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name, effort, schedual, cost, type;
        LinearLayout review;
        ImageButton edit, remove;
        ClickListen clickListen;
        public CocomoHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            effort = itemView.findViewById(R.id.txtEffort);
            schedual = itemView.findViewById(R.id.txtSchedual);
            cost = itemView.findViewById(R.id.txtCost);
            type = itemView.findViewById(R.id.txtType);
            remove = itemView.findViewById(R.id.remove);
            edit = itemView.findViewById(R.id.edit);
            review = itemView.findViewById(R.id.view);
            review.setOnClickListener(this);
            edit.setOnClickListener(this);
            remove.setOnClickListener(this);
        }

        public void setClickListen(ClickListen clickListen) {
            this.clickListen = clickListen;
        }

        @Override
        public void onClick(View v) {
            if(v == review){
                clickListen.setOnCLickListen(v, getAdapterPosition(),1);
            }else if(v == edit){
                clickListen.setOnCLickListen(v, getAdapterPosition(),2);
            }else{
                clickListen.setOnCLickListen(v, getAdapterPosition(),3);
            }
        }
    }
}
