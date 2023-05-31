package com.example.myfoodplanner.NetworkConnection;

import com.example.myfoodplanner.Model.Meal;

import java.util.List;

public interface NetworkDelegateFiltered {
    public void onSuccessfulMealsFilter(List<Meal> meals);
    public void onFailure(String errMsg);
}
