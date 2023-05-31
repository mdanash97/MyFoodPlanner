package com.example.myfoodplanner.Model;

import android.view.View;

import androidx.lifecycle.LiveData;

import com.example.myfoodplanner.NetworkConnection.NetworkDelegate;
import com.example.myfoodplanner.NetworkConnection.NetworkDelegateFiltered;
import com.example.myfoodplanner.NetworkConnection.NetworkDelegateMeal;
import com.example.myfoodplanner.NetworkConnection.NetworkDelegateRandom;

import java.util.List;

public interface RepositoryInterface {
    public void insertData(Meal meals);
    public void deleteData(Meal meals);
    public LiveData<List<Meal>> getStoredData();
    public void getAllData(NetworkDelegate networkDelegate);
    public void getAllMeal(NetworkDelegateFiltered networkDelegateFiltered);
    public void getMeal(NetworkDelegateRandom networkDelegateRandom);
    public void sendFilter(String mealArea, int mode);
    public void sendName(String mealName);
    public void getMealMain(NetworkDelegateMeal networkDelegateMeal);
}
