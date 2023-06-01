package com.example.myfoodplanner.SavedMeals.SavedMealsPresenter;

import androidx.lifecycle.LiveData;

import com.example.myfoodplanner.Model.Meal;

import java.util.List;

public interface SavedMealPresenterInterface {
    public LiveData<List<Meal>> getStoredMeals();
    public void removeMeal(Meal meal);
    public void showMeal(String meal);
}
