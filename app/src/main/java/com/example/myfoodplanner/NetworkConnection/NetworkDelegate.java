package com.example.myfoodplanner.NetworkConnection;

import com.example.myfoodplanner.Model.Category;
import com.example.myfoodplanner.Model.Meal;

import java.util.List;

public interface NetworkDelegate {
    public void onSuccessfulIngredient(List<Meal> meals);
    public void onSuccessfulArea(List<Meal> meals);
    public void onSuccessfulCategory(List<Category> categories);
    public void onFailure(String errMsg);
}
