package com.example.myfoodplanner.FilterList.FilteredListPresenter;

import android.view.View;

import com.example.myfoodplanner.FilterList.FilteredListView.FilterViewInterface;
import com.example.myfoodplanner.Model.Category;
import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.Model.Repository;
import com.example.myfoodplanner.NetworkConnection.NetworkDelegate;

import java.util.List;

public class FilteredListPresenter implements NetworkDelegate , FilteredListPresenterInterface{

    private Repository repository;
    private FilterViewInterface filterViewInterface;

    public FilteredListPresenter(FilterViewInterface filterViewInterface, Repository repository) {
        this.repository = repository;
        this.filterViewInterface = filterViewInterface;
    }


    @Override
    public void onSuccessfulIngredient(List<Meal> meals) {
        filterViewInterface.showListIngredient(meals);
    }

    @Override
    public void onSuccessfulArea(List<Meal> meals) {
        filterViewInterface.showListArea(meals);
    }

    @Override
    public void onSuccessfulCategory(List<Category> categories) {
        filterViewInterface.showListCategory(categories);
    }


    @Override
    public void onFailure(String errMsg) {
    }

    @Override
    public void getData() {
        repository.getAllData(this);
    }

    @Override
    public void sendData(String mealArea, int mode) {
        repository.sendFilter(mealArea,mode);
    }

}
