package com.example.myfoodplanner.SavedMeals.SavedMealsView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfoodplanner.FilteredMeals.FilteredMealPresenter.FilteredMealPresenter;
import com.example.myfoodplanner.FilteredMeals.FilteredMealPresenter.FilteredMealsPresenterInterface;
import com.example.myfoodplanner.FilteredMeals.FilteredMealView.FilteredMealsAdaptor;
import com.example.myfoodplanner.Model.Meal;
import com.example.myfoodplanner.Model.Repository;
import com.example.myfoodplanner.NetworkConnection.MealClient;
import com.example.myfoodplanner.R;
import com.example.myfoodplanner.SavedMeals.SavedMealsPresenter.SavedMealPresenterInterface;
import com.example.myfoodplanner.SavedMeals.SavedMealsPresenter.SavedMealsPresenter;
import com.example.myfoodplanner.db.ConcreteLocalSource;

import java.util.List;


public class SavedMealsFragment extends Fragment implements SavedMealInterface,MyClickSaved{

    RecyclerView recyclerView;
    SavedMealPresenterInterface savedMealPresenterInterface;
    SavedMealAdaptor savedMealAdaptor;
    RecyclerView.LayoutManager layoutManager;
    TextView empty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        empty = view.findViewById(R.id.emptyText);
        recyclerView = view.findViewById(R.id.savedRV);
        layoutManager = new LinearLayoutManager(this.getContext());
        savedMealPresenterInterface = new SavedMealsPresenter(Repository.getInstance(MealClient.getInstance(),
                ConcreteLocalSource.getInstance(this.getContext())),this);
        savedMealAdaptor = new SavedMealAdaptor(this.getContext(),this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(savedMealAdaptor);
        savedMealPresenterInterface.getStoredMeals().observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meal) {
                savedMealAdaptor.setList((List<Meal>) meal);
                savedMealAdaptor.notifyDataSetChanged();
                if(savedMealAdaptor.getItemCount()>0){
                    empty.setVisibility(View.GONE);
                }else{
                    empty.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void onClick(String meal) {
        savedMealPresenterInterface.showMeal(meal);

        SavedMealsFragmentDirections.ActionSavedMealsFragmentToMealFragment action = SavedMealsFragmentDirections.actionSavedMealsFragmentToMealFragment();
        action.setSaved(false);
        Navigation.findNavController(this.getView()).navigate(action);
    }
}