package com.example.myfoodplanner.SavedMeals.SavedMealsPresenter;

import androidx.lifecycle.LiveData;

import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.Model.Repository;
import com.example.myfoodplanner.SavedMeals.SavedMealsView.SavedMealInterface;

import java.util.List;

public class SavedMealsPresenter implements SavedMealPresenterInterface{

    Repository repository;
    SavedMealInterface savedMealInterface;

    public SavedMealsPresenter(Repository repository, SavedMealInterface savedMealInterface) {
        this.repository = repository;
        this.savedMealInterface = savedMealInterface;
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return repository.getStoredData();
    }

    @Override
    public void removeMeal(Meal meal) {
        repository.deleteData(meal);
    }

    @Override
    public void showMeal(String meal) {
        repository.sendName(meal);
    }
}
