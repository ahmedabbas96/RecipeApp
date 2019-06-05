package com.example.recipeapp.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.recipeapp.R;
import com.example.recipeapp.StepsDetailedActivity;
import com.example.recipeapp.model.Recipes;

import java.util.ArrayList;

public class IngredientsFragment extends Fragment {

    public ArrayList<Recipes.RecipeIngredients> ingredientsArrayList ;

    public IngredientsFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detailed_steps, container, false);

        ListView ingredientsListView = rootView.findViewById(R.id.ingredient_lv);
        ingredientsListView.setVisibility(View.VISIBLE);

        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(getContext(),0,ingredientsArrayList);

        ingredientsListView.setAdapter(ingredientsAdapter);



        return rootView;

    }
}
