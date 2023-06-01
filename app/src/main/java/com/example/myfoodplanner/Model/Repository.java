package com.example.myfoodplanner.Model;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LiveData;

import com.example.myfoodplanner.NetworkConnection.NetworkDelegate;
import com.example.myfoodplanner.NetworkConnection.NetworkDelegateFiltered;
import com.example.myfoodplanner.NetworkConnection.NetworkDelegateMeal;
import com.example.myfoodplanner.NetworkConnection.NetworkDelegateRandom;
import com.example.myfoodplanner.NetworkConnection.RemoteSource;
import com.example.myfoodplanner.db.AppDataBase;
import com.example.myfoodplanner.db.LocalSource;
import com.example.myfoodplanner.db.MealDAO;

import java.util.List;

public class Repository implements RepositoryInterface{
    private static Repository repository = null;
    private LiveData<List<Meal>> mealList;
    private Context context;
    private MealDAO mealDAO;
    private RemoteSource remoteSource;
    private LocalSource localSource;

    private Repository(RemoteSource remoteSource, LocalSource localSource, Context context) {
        this.remoteSource = remoteSource;
        this.context = context;
        this.localSource = localSource;
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.mealDAO();
        mealList = mealDAO.getAllMeals();
    }

    public static Repository getInstance(RemoteSource remoteSource, LocalSource localSource,Context context){
        if(repository==null){
            repository = new Repository(remoteSource,localSource,context);
        }
        return repository;
    }

    @Override
    public void insertData(Meal meals) {
        localSource.insertMeal(meals);
    }

    @Override
    public void deleteData(Meal meals) {
        localSource.deleteMeal(meals);
    }

    @Override
    public LiveData<List<Meal>> getStoredData() {
        return localSource.getAllSavedMeals();
    }

    @Override
    public void getAllData(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCallCategory(networkDelegate);
        remoteSource.enqueueCallIngredient(networkDelegate);
        remoteSource.enqueueCallArea(networkDelegate);
    }

    @Override
    public void getAllMeal(NetworkDelegateFiltered networkDelegateFiltered) {
        remoteSource.enqueueCallFilteredMeals(networkDelegateFiltered);
    }

    @Override
    public void getMeal(NetworkDelegateRandom networkDelegateRandom) {
        remoteSource.enqueueCallRandomMeal(networkDelegateRandom);
    }

    @Override
    public void sendFilter(String mealArea, int mode) {
        remoteSource.setFilter(mealArea,mode);
    }

    @Override
    public void sendName(String mealName) {
        remoteSource.setMealName(mealName);
    }

    @Override
    public void getMealMain(NetworkDelegateMeal networkDelegateMeal) {
        remoteSource.enqueueCallMeal(networkDelegateMeal);
    }

}

