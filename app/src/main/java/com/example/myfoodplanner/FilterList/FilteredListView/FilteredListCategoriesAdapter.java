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
import com.example.myfoodplanner.Model.Category;
import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class FilteredListCategoriesAdapter extends RecyclerView.Adapter<FilteredListCategoriesAdapter.ViewHolder> {

    private final Context context;
    private List<Category> categories = new ArrayList<>();
    private final int mode = 2;
    MyClickListener myClickListener;

    public FilteredListCategoriesAdapter(Context context, MyClickListener myClickListener) {
        this.context = context;
        this.myClickListener = myClickListener;
    }
    public void setList(List<Category> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilteredListCategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerView.getContext());
        View v = layoutInflater.inflate(R.layout.search_row, recyclerView, false);
        FilteredListCategoriesAdapter.ViewHolder vh = new FilteredListCategoriesAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.name.setText(categories.get(position).getStrCategory());
        Glide.with(context).load(categories.get(position).getStrCategoryThumb())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.top_background)
                .error(R.drawable.top_background).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onClick(category.getStrCategory(),v,mode);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
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
