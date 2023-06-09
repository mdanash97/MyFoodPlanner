package com.example.myfoodplanner.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myfoodplanner.Model.Meal;

import java.util.List;

@Dao
public interface MealDAO {
    @Query("SELECT * From Meals")
    LiveData<List<Meal>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

    @Query("UPDATE Meals set day = :day WHERE name= :name ")
    void updateMeal(String day,String name);

    @Query("SELECT * From Meals WHERE day = :day")
     LiveData<List<Meal>> getPlan(String day);

}
