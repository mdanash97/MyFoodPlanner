package com.example.myfoodplanner.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.myfoodplanner.Model.Meal;

import java.util.List;

public class ConcreteLocalSource implements LocalSource {
    private MealDAO mealDAO;
    private static ConcreteLocalSource concreteLocalSource = null;
    private LiveData<List<Meal>> savedMeals;


    private ConcreteLocalSource(Context context){
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.mealDAO();
        savedMeals = mealDAO.getAllMeals();
    }

    public static ConcreteLocalSource getInstance(Context context){
        if(concreteLocalSource == null){
            concreteLocalSource = new ConcreteLocalSource(context);
        }
        return concreteLocalSource;
    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertMeal(meal);
            }
        }).start();
    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteMeal(meal);
            }
        }).start();
    }

    @Override
    public LiveData<List<Meal>> getAllSavedProducts() {
        return savedMeals;
    }
}
