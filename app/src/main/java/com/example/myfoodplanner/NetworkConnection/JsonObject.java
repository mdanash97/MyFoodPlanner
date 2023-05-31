package com.example.myfoodplanner.NetworkConnection;

import com.example.myfoodplanner.Model.Category;
import com.example.myfoodplanner.Model.Meal;

public class JsonObject {
    private Meal[] meals;
    private Category[] categories;

    public JsonObject(Category[] categories) {
        this.categories = categories;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public Meal[] getMealsAll() {
        return meals;
    }

    public void setMealsAll(Meal[] meals) {
        this.meals = meals;
    }

    public JsonObject(Meal[] meals) {
        this.meals = meals;
    }
}
