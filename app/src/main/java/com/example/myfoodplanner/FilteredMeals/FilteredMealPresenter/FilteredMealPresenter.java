package com.example.myfoodplanner.FilteredMeals.FilteredMealPresenter;

import com.example.myfoodplanner.FilteredMeals.FilteredMealView.FilteredMealsInterface;
import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.Model.Repository;
import com.example.myfoodplanner.Model.RepositoryInterface;
import com.example.myfoodplanner.NetworkConnection.NetworkDelegateFiltered;

import java.util.List;

public class FilteredMealPresenter implements FilteredMealsPresenterInterface, NetworkDelegateFiltered {
    FilteredMealsInterface filteredMealInterface;
    Repository repository;

    public FilteredMealPresenter(FilteredMealsInterface filteredMealInterface, Repository repository) {
        this.filteredMealInterface = filteredMealInterface;
        this.repository = repository;
    }



    @Override
    public void onSuccessfulMealsFilter(List<Meal> meals) {
        filteredMealInterface.showList(meals);
    }
    @Override
    public void onFailure(String errMsg) {
    }

    @Override
    public void getMeals() {
        repository.getAllMeal(this);
    }
}
