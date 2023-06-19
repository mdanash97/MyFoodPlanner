package com.example.myfoodplanner.MainScreen.MainScreenView;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myfoodplanner.FilterList.FilteredListPresenter.FilteredListPresenter;
import com.example.myfoodplanner.MainScreen.MainScreenPresenter.MainScreenPresenter;
import com.example.myfoodplanner.MainScreen.MainScreenPresenter.MainScreenPresenterInterface;
import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.Model.Repository;
import com.example.myfoodplanner.NetworkConnection.MealClient;
import com.example.myfoodplanner.R;
import com.example.myfoodplanner.SignInActivity;
import com.example.myfoodplanner.db.ConcreteLocalSource;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainFragment extends Fragment implements MainViewInterface {

    TextView mealName;
    TextView mealArea;
    ImageView mealImg;
    MainScreenPresenterInterface mainScreenPresenterInterface;
    ProgressBar progressBar;
    Button logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainScreenPresenterInterface = new MainScreenPresenter(this, Repository.getInstance(MealClient.getInstance(),
                ConcreteLocalSource.getInstance(this.getContext())));

        mealArea = view.findViewById(R.id.mealAreaMain);
        mealName = view.findViewById(R.id.mealNameMain);
        mealImg = view.findViewById(R.id.mealImgMain);
        progressBar = view.findViewById(R.id.progressBar2);
        logout = view.findViewById(R.id.logoutBtn);
        mainScreenPresenterInterface.getMeal();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }

    @Override
    public void showMeal(List<Meal> meals) {
        Meal meal = meals.get(0);
        mealName.setText(meal.getName());
        mealArea.setText(meal.getArea());
        Glide.with(this.getContext()).load(meal.getThumbnail())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.top_background)
                .error(R.drawable.top_background).into(mealImg);
        progressBar.setVisibility(View.GONE);
        mealImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onClickMain(meals.get(0).getName());
            }
        });
    }

    public void onClickMain(String mealName) {
        mainScreenPresenterInterface.sendMealName(mealName);
        Navigation.findNavController(this.getView()).navigate(R.id.action_mainFragment_to_mealFragment);
    }
}