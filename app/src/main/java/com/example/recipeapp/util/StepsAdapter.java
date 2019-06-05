package com.example.recipeapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.recipeapp.R;

import com.example.recipeapp.model.Recipes;


import java.util.ArrayList;
import java.util.Objects;

public class StepsAdapter extends ArrayAdapter<Recipes.RecipeSteps> {

    private Context context;

    public StepsAdapter(Context context, int resource, ArrayList<Recipes.RecipeSteps> objects) {
        super(context, resource, objects);
        this.context = context;

    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String shortDesString = Objects.requireNonNull(getItem(position)).getShortDescription();
        String stepNumString = "Step "+(position+1);


        LayoutInflater inflater = LayoutInflater.from(context);

        convertView = inflater.inflate(R.layout.step_row, parent, false);

        TextView stepNum = convertView.findViewById(R.id.step_num_tv);
        TextView stepshortDes = convertView.findViewById(R.id.step_tv);

        stepNum.setText(stepNumString);
        stepshortDes.setText(shortDesString);

        return convertView;
    }

}