package com.example.myfoodplanner.MyMealPlan.MyMealPlanView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfoodplanner.Model.Day;
import com.example.myfoodplanner.R;

import java.util.Arrays;
import java.util.List;


public class MyMealPlanFragment extends Fragment {

    RecyclerView recyclerView;
    List<Day> dayList;
    MyMealPlanAdaptor myMealPlanAdaptor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_meal_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.myPlanRV);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        dayList = Arrays.asList(
                new Day("Monday",R.drawable.monday),
                new Day("Tuesday",R.drawable.tuesday),
                new Day("Wednesday",R.drawable.wednesday),
                new Day("Thursday",R.drawable.thursday),
                new Day("Friday",R.drawable.friday),
                new Day("Saturday",R.drawable.saturday),
                new Day("Sun",R.drawable.sunday)
        );
        myMealPlanAdaptor = new MyMealPlanAdaptor(getContext(),dayList);
        recyclerView.setAdapter(myMealPlanAdaptor);
    }
}