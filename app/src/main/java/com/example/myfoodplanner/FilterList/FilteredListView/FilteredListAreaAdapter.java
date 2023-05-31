package com.example.myfoodplanner.FilterList.FilteredListView;

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
import java.util.Locale;

public class FilteredListAreaAdapter extends RecyclerView.Adapter<FilteredListAreaAdapter.ViewHolder> {

    private final Context context;
    private List<Meal> meals = new ArrayList<>();
    private MyClickListener myClickListener;
    private final int mode = 1;

    public FilteredListAreaAdapter(Context context,MyClickListener myClickListener) {
        this.context = context;
        this.myClickListener = myClickListener;
    }

    public void setList(List<Meal> meals){
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilteredListAreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerView.getContext());
        View v = layoutInflater.inflate(R.layout.search_row, recyclerView, false);
        FilteredListAreaAdapter.ViewHolder vh = new FilteredListAreaAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FilteredListAreaAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        String imageName = meals.get(position).getArea().toLowerCase(Locale.ROOT);
        int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        holder.name.setText(meals.get(position).getArea());
        Glide.with(context).load(resourceId)
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.top_background)
                .error(R.drawable.top_background).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onClick(meal.getArea(),v,mode);
            }
        });
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