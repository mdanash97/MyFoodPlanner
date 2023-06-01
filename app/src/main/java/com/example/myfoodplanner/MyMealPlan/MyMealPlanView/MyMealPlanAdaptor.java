package com.example.myfoodplanner.MyMealPlan.MyMealPlanView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myfoodplanner.Model.Day;
import com.example.myfoodplanner.R;
import java.util.ArrayList;
import java.util.List;

public class MyMealPlanAdaptor extends RecyclerView.Adapter<MyMealPlanAdaptor.ViewHolder> {

    private final Context context;
    private List<Day> days = new ArrayList<>();

    public MyMealPlanAdaptor(Context context,List<Day> days) {
        this.context = context;
        this.days = days;
    }

    public void setList(List<Day> days){
        this.days = days;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyMealPlanAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerView.getContext());
        View v = layoutInflater.inflate(R.layout.meal_row, recyclerView, false);
        MyMealPlanAdaptor.ViewHolder vh = new MyMealPlanAdaptor.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyMealPlanAdaptor.ViewHolder holder, int position) {
        holder.name.setText(days.get(position).getTitle());
        Glide.with(context).load(days.get(position).getImg())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.top_background)
                .error(R.drawable.top_background).into(holder.img);

    }


    @Override
    public int getItemCount() {
        return days.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView img;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            name = itemView.findViewById(R.id.mealNameText);
            img = itemView.findViewById(R.id.mealImg);

        }
    }
}
