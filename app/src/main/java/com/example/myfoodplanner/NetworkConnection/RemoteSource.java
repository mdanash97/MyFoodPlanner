package com.example.myfoodplanner.NetworkConnection;

import com.example.myfoodplanner.Model.Meal;

public interface RemoteSource {
    void enqueueCallIngredient(NetworkDelegate networkDelegate);
    void enqueueCallCategory(NetworkDelegate networkDelegate);
    void enqueueCallArea(NetworkDelegate networkDelegate);
    void setFilter(String mealArea,int mode);
    void enqueueCallRandomMeal(NetworkDelegateRandom networkDelegateRandom);
    void enqueueCallFilteredMeals(NetworkDelegateFiltered networkDelegateFiltered);
    void setMealName(String mealName);
    void enqueueCallMeal(NetworkDelegateMeal networkDelegateMeal);
}
