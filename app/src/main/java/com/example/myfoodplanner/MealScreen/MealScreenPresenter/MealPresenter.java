package com.example.myfoodplanner.MealScreen.MealScreenPresenter;

import com.example.myfoodplanner.MealScreen.MealScreenView.MealViewInterface;
import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.Model.Repository;
import com.example.myfoodplanner.NetworkConnection.NetworkDelegateMeal;

import java.util.List;

public class MealPresenter implements MealPresenterInterface, NetworkDelegateMeal {
    Repository repository;
    MealViewInterface mealViewInterface;

    public MealPresenter( MealViewInterface mealViewInterface,Repository repository) {
        this.repository = repository;
        this.mealViewInterface = mealViewInterface;
    }

    @Override
    public void getMeal() {
        repository.getMealMain(this);
    }

    @Override
    public void sendMealName(String mealName) {

    }

    @Override
    public void onSuccessful(List<Meal> meals) {
        mealViewInterface.showMeal(meals);
    }

    @Override
    public void onFailure(String errMsg) {

    }
}
