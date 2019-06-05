package com.example.recipeapp.ingredientWidget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViewsService;

import com.example.recipeapp.StepsActivity;
import com.example.recipeapp.model.Recipes;
import com.google.gson.Gson;

public class IngredientWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        String jsonIngredientsList = appSharedPrefs.getString(StepsActivity.PREF_KEY_INGREDIENTS, "");
        Recipes.RecipeIngredients[] ingredientList = gson.fromJson(jsonIngredientsList, Recipes.RecipeIngredients[].class);

        return new com.example.recipeapp.ingredientWidget.RemoteViewsFactory(intent, ingredientList, getPackageName());
    }
}
