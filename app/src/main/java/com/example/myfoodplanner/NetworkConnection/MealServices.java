package com.example.myfoodplanner.NetworkConnection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MealServices {
    @GET("categories.php")
    Call<JsonObject> getCategories();

    @GET("random.php")
    Call<JsonObject> getRandoMeal();

    @GET("filter.php")
    Call<JsonObject> getMealsByIngredient(@Query("i") String ingredient);

    @GET("filter.php")
    Call<JsonObject> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<JsonObject> getMealsByArea(@Query("a") String area);

    @GET("list.php?i=list")
    Call<JsonObject> getIngredient();

    @GET("list.php?a=list")
    Call<JsonObject> getArea();

    @GET("search.php")
    Call<JsonObject> getMealByName(@Query("s") String mealName);
}
