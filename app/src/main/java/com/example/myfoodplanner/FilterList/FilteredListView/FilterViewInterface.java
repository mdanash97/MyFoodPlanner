package com.example.myfoodplanner.FilterList.FilteredListView;

import com.example.myfoodplanner.Model.Category;
import com.example.myfoodplanner.Model.Meal;

import java.util.List;

public interface FilterViewInterface {
    public void showListIngredient(List<Meal> meals);
    public void showListArea(List<Meal> meals);
    public void showListCategory(List<Category> categories);

}
