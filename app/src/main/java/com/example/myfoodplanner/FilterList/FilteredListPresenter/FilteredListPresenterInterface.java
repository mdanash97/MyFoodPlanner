package com.example.myfoodplanner.FilterList.FilteredListPresenter;

import android.view.View;

import com.example.myfoodplanner.Model.Meal;

public interface FilteredListPresenterInterface {
    public void getData();
    public void sendData(String mealArea, int mode);

}
