package com.example.myfoodplanner.SearchMeals.SearchView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myfoodplanner.SearchMeals.SearchView.SearchFragmentDirections.ActionSearchFragmentToFilterFragment;


import com.example.myfoodplanner.R;

public class SearchFragment extends Fragment {
    TextView toIngredient;
    TextView toArea;
    TextView toCategories;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toIngredient = view.findViewById(R.id.ingraText);
        toArea = view.findViewById(R.id.countText);
        toCategories = view.findViewById(R.id.cateText);

        toIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ActionSearchFragmentToFilterFragment action =
                        SearchFragmentDirections.actionSearchFragmentToFilterFragment("");
                action.setFilter("0");
                Navigation.findNavController(view).navigate(action);
            }
        });

        toArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionSearchFragmentToFilterFragment action =
                        SearchFragmentDirections.actionSearchFragmentToFilterFragment("");
                action.setFilter("1");
                Navigation.findNavController(view).navigate(action);
            }
        });

        toCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionSearchFragmentToFilterFragment action =
                        SearchFragmentDirections.actionSearchFragmentToFilterFragment("");
                action.setFilter("2");
                Navigation.findNavController(view).navigate(action);
            }
        });
    }
}