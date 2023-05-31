package com.example.myfoodplanner.MealScreen.MealScreenView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myfoodplanner.MainScreen.MainScreenPresenter.MainScreenPresenter;
import com.example.myfoodplanner.MealScreen.MealScreenPresenter.MealPresenter;
import com.example.myfoodplanner.MealScreen.MealScreenPresenter.MealPresenterInterface;
import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.Model.Repository;
import com.example.myfoodplanner.NetworkConnection.MealClient;
import com.example.myfoodplanner.R;
import com.example.myfoodplanner.db.ConcreteLocalSource;

import java.util.List;


public class MealFragment extends Fragment implements MealViewInterface{

    TextView mealName;
    TextView mealArea;
    ImageView mealImg;
    MealPresenterInterface mealPresenterInterface;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mealPresenterInterface = new MealPresenter(this, Repository.getInstance(MealClient.getInstance(),
                ConcreteLocalSource.getInstance(this.getContext()),getContext()));

        mealPresenterInterface.getMeal();

        mealName = view.findViewById(R.id.nameMeal);
        mealArea = view.findViewById(R.id.areaMeal);
        mealImg = view.findViewById(R.id.imgMeal);

    }

    @Override
    public void showMeal(List<Meal> meal) {
        mealName.setText(meal.get(0).getName());
        mealArea.setText(meal.get(0).getArea());
        Glide.with(this.getContext()).load(meal.get(0).getThumbnail())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.top_background)
                .error(R.drawable.top_background).into(mealImg);
    }
}