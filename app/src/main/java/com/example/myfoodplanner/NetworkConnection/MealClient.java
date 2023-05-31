package com.example.myfoodplanner.NetworkConnection;

import com.example.myfoodplanner.Model.Meal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Array;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealClient implements RemoteSource{

    private static MealClient mealClient = null;
    private static String  BaseURL = "https://www.themealdb.com/api/json/v1/1/";
    private static MealServices apiInterFace;
    private String filter;
    private int mode;
    private String mealName;

    public static MealClient getInstance(){
        if(mealClient == null){
            mealClient = new MealClient();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterFace = retrofit.create(MealServices.class);
        }
        return mealClient;
    }

    @Override
    public void enqueueCallIngredient(NetworkDelegate networkDelegate) {
        Call<JsonObject> call = apiInterFace.getIngredient();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                networkDelegate.onSuccessfulIngredient(Arrays.asList(response.body().getMealsAll()));
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                networkDelegate.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void enqueueCallCategory(NetworkDelegate networkDelegate) {
        Call<JsonObject> call = apiInterFace.getCategories();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                networkDelegate.onSuccessfulCategory(Arrays.asList(response.body().getCategories()));
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                networkDelegate.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void enqueueCallArea(NetworkDelegate networkDelegate) {
        Call<JsonObject> call = apiInterFace.getArea();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                networkDelegate.onSuccessfulArea(Arrays.asList(response.body().getMealsAll()));
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                networkDelegate.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void setFilter(String mealArea,int mode) {
        filter = mealArea;
        this.mode = mode;
    }

    @Override
    public void enqueueCallRandomMeal(NetworkDelegateRandom networkDelegateRandom) {
        Call<JsonObject> call = apiInterFace.getRandoMeal();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                networkDelegateRandom.onSuccessfulMeal(Arrays.asList(response.body().getMealsAll()));
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                networkDelegateRandom.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void enqueueCallFilteredMeals(NetworkDelegateFiltered networkDelegateFiltered) {
        Call<JsonObject> call = null;
        switch (mode){
            case 0:
                call = apiInterFace.getMealsByIngredient(filter);
                break;
            case 1:
                 call = apiInterFace.getMealsByArea(filter);
                break;
            case 2:
                call = apiInterFace.getMealsByCategory(filter);
                break;
        }

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                networkDelegateFiltered.onSuccessfulMealsFilter(Arrays.asList(response.body().getMealsAll()));
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                networkDelegateFiltered.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    @Override
    public void enqueueCallMeal(NetworkDelegateMeal networkDelegateMeal) {
        Call<JsonObject> call = apiInterFace.getMealByName(mealName);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                networkDelegateMeal.onSuccessful(Arrays.asList(response.body().getMealsAll()));
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                networkDelegateMeal.onFailure(t.getMessage());
            }
        });
    }


}
