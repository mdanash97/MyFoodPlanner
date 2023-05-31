package com.example.myfoodplanner.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myfoodplanner.Model.Meal;

@Database(entities = {Meal.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase appDataBase = null;
    public abstract MealDAO mealDAO();

    public static synchronized AppDataBase getInstance(Context context){
        if(appDataBase==null){
            appDataBase = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"mealsdb")
                    .build();
        }
        return appDataBase;
    }
}
