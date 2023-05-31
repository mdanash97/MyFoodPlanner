package com.example.myfoodplanner.FilteredMeals.FilteredMealView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfoodplanner.FilterList.FilteredListPresenter.FilteredListPresenter;
import com.example.myfoodplanner.FilteredMeals.FilteredMealPresenter.FilteredMealPresenter;
import com.example.myfoodplanner.FilteredMeals.FilteredMealPresenter.FilteredMealsPresenterInterface;
import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.Model.Repository;
import com.example.myfoodplanner.NetworkConnection.MealClient;
import com.example.myfoodplanner.R;
import com.example.myfoodplanner.db.ConcreteLocalSource;

import java.util.List;

public class FilteredMeals extends Fragment implements FilteredMealsInterface {

    RecyclerView recyclerView;
    FilteredMealsPresenterInterface filteredMealsPresenterInterface;
    FilteredMealsAdaptor filteredMealsAdaptor;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filtered_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.filteredMealRV);
        layoutManager = new LinearLayoutManager(this.getContext());

        filteredMealsPresenterInterface = new FilteredMealPresenter(this, Repository.getInstance(MealClient.getInstance(),
                ConcreteLocalSource.getInstance(this.getContext()),getContext()));
        filteredMealsAdaptor = new FilteredMealsAdaptor(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(filteredMealsAdaptor);
        filteredMealsPresenterInterface.getMeals();
    }

    @Override
    public void showList(List<Meal> meals) {
        filteredMealsAdaptor.setList(meals);
        filteredMealsAdaptor.notifyDataSetChanged();
    }
}