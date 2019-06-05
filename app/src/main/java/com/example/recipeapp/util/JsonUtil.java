package com.example.recipeapp.util;

import android.util.Log;

import com.example.recipeapp.model.Recipes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtil {

    private static final String NAME_KEY = "name";
    private static final String INGREDIENTS_KEY = "ingredients";
    private static final String QUANTITY_KEY = "quantity";
    private static final String MEASURE_KEY = "measure";
    private static final String INGREDIENT_KEY = "ingredient";
    private static final String STEPS_KEY = "steps";
    private static final String SHORT_DESCRIPTION_KEY = "shortDescription";
    private static final String DESCRIPTION_KEY = "description";
    private static final String VIDEO_URL_KEY = "videoURL";
    private static final String THUMBNAIL_URL_KEY = "thumbnailURL";
    private static final String SERVINGS_KEY = "servings";

    public static ArrayList<Recipes> recpiesArrayList (String JSON){

        ArrayList<Recipes> recipes = new ArrayList<>();


        if(JSON != null){
            try {
                JSONArray mainJsonArray = new JSONArray(JSON);

                for(int i = 0; i < mainJsonArray.length(); i++){

                    JSONObject recipeJsonObject = mainJsonArray.getJSONObject(i);

                    String recipeName = recipeJsonObject.getString(NAME_KEY);
                    int recpieservings = recipeJsonObject.getInt(SERVINGS_KEY);
                    ArrayList<Recipes.RecipeIngredients> recipeIngredientsArrayList = new ArrayList<>();
                    ArrayList<Recipes.RecipeSteps> recipeStepsArrayList = new ArrayList<>();

                    JSONArray recpieIngredientsJSONArray = recipeJsonObject.getJSONArray(INGREDIENTS_KEY);


                    for (int j = 0 ; j < recpieIngredientsJSONArray.length(); j++){

                        JSONObject ingredientsJSONObject = recpieIngredientsJSONArray.getJSONObject(j);
                        double ingredientQty = ingredientsJSONObject.getDouble(QUANTITY_KEY);
                        String measure = ingredientsJSONObject.getString(MEASURE_KEY);
                        String ingredient = ingredientsJSONObject.getString(INGREDIENT_KEY);

                        Recipes.RecipeIngredients recipeIngredientsObj = new Recipes.RecipeIngredients(ingredientQty, measure, ingredient);
                        recipeIngredientsArrayList.add(recipeIngredientsObj);

                    }

                    JSONArray stepsJSONArray = recipeJsonObject.getJSONArray(STEPS_KEY);

                    for (int k = 0; k < stepsJSONArray.length(); k++){
                        JSONObject stepsJSONObject = stepsJSONArray.getJSONObject(k);

                        String shortDescription = stepsJSONObject.getString(SHORT_DESCRIPTION_KEY);
                        String description = stepsJSONObject.getString(DESCRIPTION_KEY);
                        String videoURL = stepsJSONObject.getString(VIDEO_URL_KEY);
                        String thumbnailURL = stepsJSONObject.getString(THUMBNAIL_URL_KEY);

                        Log.d("Abbas", "recpiesArrayList: "+ shortDescription);


                        Recipes.RecipeSteps recipeStepsObj = new Recipes.RecipeSteps(shortDescription, description, videoURL, thumbnailURL);
                        recipeStepsArrayList.add(recipeStepsObj);

                    }

                    Recipes recipeObj =  new Recipes(recipeName, recipeIngredientsArrayList,recipeStepsArrayList, recpieservings);
                    recipes.add(recipeObj);

                    Log.d("Ahmed", "recpiesArrayList: "+recipes.get(0).getRecipeName());



                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return recipes;
    }
}
