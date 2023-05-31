package com.example.myfoodplanner.FilteredMeals.FilteredMealView;

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
import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class FilteredMealsAdaptor extends RecyclerView.Adapter<FilteredMealsAdaptor.ViewHolder> {

    private final Context context;
    private List<Meal> meals = new ArrayList<>();

    public FilteredMealsAdaptor(Context context) {
            this.context = context;
            }

    public void setList(List<Meal> meals){
            this.meals = meals;
            notifyDataSetChanged();
            }

    @NonNull
    @Override
    public FilteredMealsAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(recyclerView.getContext());
            View v = layoutInflater.inflate(R.layout.meal_row, recyclerView, false);
        FilteredMealsAdaptor.ViewHolder vh = new FilteredMealsAdaptor.ViewHolder(v);
            return vh;
            }

    @Override
    public void onBindViewHolder(@NonNull FilteredMealsAdaptor.ViewHolder holder, int position) {
            holder.name.setText(meals.get(position).getName());
            Glide.with(context).load(meals.get(position).getThumbnail())
            .apply(new RequestOptions().override(200, 200))
            .placeholder(R.drawable.top_background)
            .error(R.drawable.top_background).into(holder.img);
            }

    @Override
    public int getItemCount() {
            return meals.size();
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
