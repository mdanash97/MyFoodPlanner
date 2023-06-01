package com.example.myfoodplanner.MealScreen.MealScreenPresenter;

import com.example.myfoodplanner.Model.Meal;

public interface MealPresenterInterface {
    public void getMeal();
    public void addToFav(Meal meal);
    public void deleteFromFave(Meal meal);
}
