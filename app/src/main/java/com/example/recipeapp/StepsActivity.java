package com.example.recipeapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.recipeapp.ingredientWidget.ingredientWidget;
import com.example.recipeapp.model.Recipes;
import com.example.recipeapp.util.IngredientsFragment;
import com.example.recipeapp.util.StepsDetailedFragment;
import com.example.recipeapp.util.StepsFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

public class StepsActivity extends AppCompatActivity {

    public boolean mTwoPane;
    public int fragmentStepNumber = -1;
    public static final String PREF_KEY_INGREDIENTS = "Ingredients list";
    public static final String PREF_KEY_RECIPE_NAME = "Recipe Name";
    public static final String KEY_STEP_NO = "fragment_position_is_saved";
    public static final int DEFAULT_FRAGMENT_STEP_NUMBER = -1;
    CardView ingredientCard;
    static StepsActivity stepsActivity;

    Recipes recipes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        stepsActivity = this;

        recipes = (Recipes) getIntent().getSerializableExtra("recipe");

        StepsFragment stepsFragment = new StepsFragment();
        stepsFragment.stepsArrayList = recipes.getRecipeStepsArrayList();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.step_fragment,stepsFragment)
                .commit();

        ingredientCard = findViewById(R.id.ingredient_card);

        if (findViewById(R.id.step_detail_fragment)!= null){

            mTwoPane = true;

            if(savedInstanceState != null){
                fragmentStepNumber = savedInstanceState.getInt(KEY_STEP_NO,DEFAULT_FRAGMENT_STEP_NUMBER);
            }
        }


        initViews();
        startRecipe();

    }

    private void initViews() {

        if(mTwoPane){
            if(fragmentStepNumber == DEFAULT_FRAGMENT_STEP_NUMBER){
                showIngredientsList();
            } else{
                showStepFragment(fragmentStepNumber);
            }
        }

        ingredientCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mTwoPane){
                    showIngredientsList();
                } else {
                    Intent intent = new Intent(StepsActivity.this, StepsDetailedActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ingredients", recipes.getRecipeIngredientsArrayList());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }


    private void showIngredientsList() {

        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        ingredientsFragment.ingredientsArrayList = recipes.getRecipeIngredientsArrayList();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_detail_fragment, ingredientsFragment)
                .commit();
    }

    public void showStepFragment(int stepPosition) {

        StepsDetailedFragment stepsDetailedFragment = new StepsDetailedFragment();
        stepsDetailedFragment.recipeSteps = recipes.getRecipeStepsArrayList().get(stepPosition);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_detail_fragment,stepsDetailedFragment)
                .commit();
    }

    public void startRecipe (){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

        Gson gson = new Gson();
        String ingredientsJson = gson.toJson(recipes.getRecipeIngredientsArrayList());
        prefsEditor.putString(PREF_KEY_INGREDIENTS, ingredientsJson);
        prefsEditor.putString(PREF_KEY_RECIPE_NAME, recipes.getRecipeName());
        prefsEditor.apply();
        // update the widget
        updateWidget();

    }

    private void updateWidget() {
        Intent intent = new Intent(this, ingredientWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
        // since it seems the onUpdate() is only fired on that:
        int[] ids = AppWidgetManager.getInstance(this)
                .getAppWidgetIds(new ComponentName(this, ingredientWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        this.sendBroadcast(intent);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_STEP_NO,fragmentStepNumber);
    }

    public static StepsActivity getInstance() {
        return stepsActivity;
    }


}
