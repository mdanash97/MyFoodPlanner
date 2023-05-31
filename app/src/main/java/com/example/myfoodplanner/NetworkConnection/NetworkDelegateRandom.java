package com.example.myfoodplanner.NetworkConnection;

import com.example.myfoodplanner.Model.Meal;

import java.util.List;

public interface NetworkDelegateRandom {
    public void onSuccessfulMeal(List<Meal> meals);
    public void onFailure(String errMsg);
}
