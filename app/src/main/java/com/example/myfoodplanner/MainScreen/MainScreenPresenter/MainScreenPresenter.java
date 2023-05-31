package com.example.myfoodplanner.MainScreen.MainScreenPresenter;

import com.example.myfoodplanner.MainScreen.MainScreenView.MainViewInterface;
import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.Model.Repository;
import com.example.myfoodplanner.NetworkConnection.NetworkDelegateRandom;

import java.util.List;

public class MainScreenPresenter implements MainScreenPresenterInterface , NetworkDelegateRandom {
    Repository repository;
    MainViewInterface mainViewInterface;

    public MainScreenPresenter( MainViewInterface mainViewInterface,Repository repository) {
        this.repository = repository;
        this.mainViewInterface = mainViewInterface;
    }

    @Override
    public void getMeal() {
        repository.getMeal(this);
    }

    @Override
    public void sendMealName(String mealName) {
        repository.sendName(mealName);
    }

    @Override
    public void onSuccessfulMeal(List<Meal> meals) {
        mainViewInterface.showMeal(meals);
    }

    @Override
    public void onFailure(String errMsg) {

    }
}
