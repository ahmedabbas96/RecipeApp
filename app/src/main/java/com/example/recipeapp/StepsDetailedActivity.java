package com.example.recipeapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.recipeapp.model.Recipes;
import com.example.recipeapp.util.IngredientsFragment;
import com.example.recipeapp.util.StepsDetailedFragment;
import com.example.recipeapp.util.StepsFragment;

import java.util.ArrayList;

public class StepsDetailedActivity extends AppCompatActivity {

    Button nextBtn, previousBtn;
    int position;
    ArrayList<Recipes.RecipeSteps> recipeStepsArrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_detailed);

        nextBtn = findViewById(R.id.next_button);
        previousBtn = findViewById(R.id.back_button);

        if (getIntent().hasExtra("stepPosition")) {
            position = getIntent().getIntExtra("stepPosition", 0);
        }

        if(getIntent().hasExtra("stepsArray")){
            recipeStepsArrayList = (ArrayList<Recipes.RecipeSteps>) getIntent().getSerializableExtra("stepsArray");
        }

        Intent intent = getIntent();
        if (savedInstanceState == null) {
            if (intent.hasExtra("ingredients")) {

                IngredientsFragment ingredientsFragment = new IngredientsFragment();

                ingredientsFragment.ingredientsArrayList = (ArrayList<Recipes.RecipeIngredients>) intent.getSerializableExtra("ingredients");

                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .add(R.id.step_detail_fragment, ingredientsFragment)
                        .commit();
            } else if (intent.hasExtra("detailedStep")) {

                nextBtn.setVisibility(View.VISIBLE);
                previousBtn.setVisibility(View.VISIBLE);


                if (position == recipeStepsArrayList.size() - 1) {
                    nextBtn.setText("Finish");
                }

                StepsDetailedFragment stepsDetailedFragment = new StepsDetailedFragment();

                stepsDetailedFragment.recipeSteps = (Recipes.RecipeSteps) intent.getSerializableExtra("detailedStep");

                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .add(R.id.step_detail_fragment, stepsDetailedFragment)
                        .commit();
            }
        }

        /*
        Next Button
         */

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position += 1;
                if (position == recipeStepsArrayList.size()) {
                    finish();
                    position -= 1;
                } else if (position == recipeStepsArrayList.size() - 1) {
                    nextBtn.setText("Finish");
                }

                StepsDetailedFragment stepsDetailedFragment = new StepsDetailedFragment();

                stepsDetailedFragment.recipeSteps = recipeStepsArrayList.get(position);

                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.step_detail_fragment,stepsDetailedFragment)
                        .commit();

            }
        });

        /*
        Previous Button
         */

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position -= 1;
                if (position == -1) {
                    finish();
                    position = 0;
                }

                StepsDetailedFragment stepsDetailedFragment = new StepsDetailedFragment();

                stepsDetailedFragment.recipeSteps = recipeStepsArrayList.get(position);

                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.step_detail_fragment,stepsDetailedFragment)
                        .commit();
            }
        });
    }
}