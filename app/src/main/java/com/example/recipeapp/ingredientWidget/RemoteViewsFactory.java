package com.example.recipeapp.ingredientWidget;

import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.recipeapp.R;
import com.example.recipeapp.model.Recipes;

public class RemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Intent intent;
    private final Recipes.RecipeIngredients[] ingredientList;
    private final String packageName;

    RemoteViewsFactory(Intent intent, Recipes.RecipeIngredients[] ingredientList, String packageName) {
        this.intent = intent;
        this.ingredientList = ingredientList;
        this.packageName = packageName;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredientList.length;
    }

    @Override
    public RemoteViews getViewAt(int position) {

        Recipes.RecipeIngredients ingredients = ingredientList[position];

        RemoteViews remoteViews = new RemoteViews(packageName, R.layout.ingredient_widget_row_item);

        remoteViews.setTextViewText(R.id.ingredient_description, getFormattedText(ingredients));

        return remoteViews;
    }

    private String getFormattedText(Recipes.RecipeIngredients ingredient) {
        String text;
        String quantity;
        String DEFAULT_VALUE_NO_UNIT = "UNIT";

        //Check if amount is an integer
        double variable = ingredient.getQuantity();
        if ((variable == Math.floor(variable)) && !Double.isInfinite(variable)) {
            // integer type
            quantity = String.valueOf(variable);
            int length = quantity.length();
            quantity = quantity.substring(0, length - 2);
        } else {
            quantity = String.valueOf(variable);
        }

        // Check is measure is a number or has a unit
        if (ingredient.getMeasure().equals(DEFAULT_VALUE_NO_UNIT)) {
            text = quantity + " " + ingredient.getIngredient();
        } else {
            text = quantity + " " + ingredient.getMeasure() + " of " + ingredient.getIngredient();
        }
        return text;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
