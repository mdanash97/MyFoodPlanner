package com.example.myfoodplanner.db;

import androidx.lifecycle.LiveData;

import com.example.myfoodplanner.Model.Meal;

import java.util.List;

public interface LocalSource {
    void insertMeal(Meal meal);
    void deleteMeal(Meal meal);
    LiveData<List<Meal>> getAllSavedMeals();
    void updateMeal(String day,String mealName);
    LiveData<List<Meal>> getPlan(String day);
}
