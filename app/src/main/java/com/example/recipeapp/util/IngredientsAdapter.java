package com.example.recipeapp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.recipeapp.R;
import com.example.recipeapp.model.Recipes;

import java.util.ArrayList;
import java.util.Objects;

public class IngredientsAdapter extends ArrayAdapter<Recipes.RecipeIngredients> {

    private Context context;

    public IngredientsAdapter(Context context, int resource, ArrayList<Recipes.RecipeIngredients> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String ingredientNameString = Objects.requireNonNull(getItem(position)).getIngredient();
        String ingredientQtyString = String.valueOf(getItem(position).getQuantity());
        String ingredientMeasureString = Objects.requireNonNull(getItem(position)).getMeasure();

        LayoutInflater inflater = LayoutInflater.from(context);

        convertView = inflater.inflate(R.layout.ingredients_row, parent, false);

        TextView ingredientName = convertView.findViewById(R.id.ingredient_name_tv);
        TextView ingredientQty = convertView.findViewById(R.id.ingredient_quantity_tv);
        TextView ingredientMeasure = convertView.findViewById(R.id.ingredient_measure_tv);

        ingredientName.setText(ingredientNameString);
        ingredientQty.setText(ingredientQtyString);
        ingredientMeasure.setText(ingredientMeasureString);

        return convertView;
    }
}
