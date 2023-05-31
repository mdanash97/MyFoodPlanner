package com.example.myfoodplanner.FilterList.FilteredListView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfoodplanner.FilterList.FilteredListPresenter.FilteredListPresenter;
import com.example.myfoodplanner.FilterList.FilteredListPresenter.FilteredListPresenterInterface;
import com.example.myfoodplanner.FilteredMeals.FilteredMealPresenter.FilteredMealsPresenterInterface;
import com.example.myfoodplanner.Model.Category;
import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.Model.Repository;
import com.example.myfoodplanner.NetworkConnection.MealClient;
import com.example.myfoodplanner.NetworkConnection.RemoteSource;
import com.example.myfoodplanner.R;
import com.example.myfoodplanner.db.ConcreteLocalSource;

import java.util.List;


public class FilterFragment extends Fragment implements FilterViewInterface , MyClickListener {
    RecyclerView recyclerView;
    FilteredListIngredientAdapter filteredListIngredientAdapter;
    FilteredListCategoriesAdapter filteredListCategoriesAdapter;
    FilteredListAreaAdapter filteredListAreaAdapter;
    RecyclerView.LayoutManager layoutManager;
    FilteredListPresenterInterface filteredListPresenterInterface;
    RemoteSource remoteSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String filter = FilterFragmentArgs.fromBundle(getArguments()).getFilter();
        recyclerView = view.findViewById(R.id.searchFilterRV);
        layoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        filteredListPresenterInterface = new FilteredListPresenter(this, Repository.getInstance(MealClient.getInstance(),
                ConcreteLocalSource.getInstance(this.getContext()),getContext()));
        filteredListAreaAdapter = new FilteredListAreaAdapter(this.getContext(),this);
        filteredListIngredientAdapter = new FilteredListIngredientAdapter(this.getContext(),this);
        filteredListCategoriesAdapter = new FilteredListCategoriesAdapter(this.getContext(),this);
        switch (filter){
            case "0":
                recyclerView.setAdapter(filteredListIngredientAdapter);
                break;
            case "1":
                recyclerView.setAdapter(filteredListAreaAdapter);
                break;
            case "2":
                recyclerView.setAdapter(filteredListCategoriesAdapter);
                break;
        }

        recyclerView.setLayoutManager(layoutManager);
        filteredListPresenterInterface.getData();
    }

    @Override
    public void showListIngredient(List<Meal> meals) {
        filteredListIngredientAdapter.setListIngredient(meals);
        filteredListIngredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void showListArea(List<Meal> meals) {
        filteredListAreaAdapter.setList(meals);
        filteredListAreaAdapter.notifyDataSetChanged();
    }

    @Override
    public void showListCategory(List<Category> categories) {
        filteredListCategoriesAdapter.setList(categories);
        filteredListCategoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(String mealArea,View view,int mode) {
        filteredListPresenterInterface.sendData(mealArea,mode);
        Navigation.findNavController(view).navigate(R.id.action_filterFragment_to_filteredMeals);
    }
}