package com.example.recipeapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipes implements Serializable {

    private String RecipeName;
    private ArrayList<RecipeIngredients> recipeIngredientsArrayList;
    private ArrayList<RecipeSteps> recipeStepsArrayList;
    private int RecipeServings;
    private int RecipeImg;

    public Recipes(String RecipeName, ArrayList<RecipeIngredients> recipeIngredientsArrayList,
                   ArrayList<RecipeSteps> recipeStepsArrayList, int RecipeServings){
        this.RecipeName = RecipeName;
        this.recipeIngredientsArrayList = recipeIngredientsArrayList;
        this.recipeStepsArrayList = recipeStepsArrayList;
        this.RecipeServings = RecipeServings;
    }

    public Recipes() {

    }

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String RecipeName){ this.RecipeName = RecipeName; }

    public ArrayList<RecipeIngredients> getRecipeIngredientsArrayList(){
        return recipeIngredientsArrayList;
    }

    public void setRecipeIngredientsArrayList(ArrayList<RecipeIngredients> recipeIngredientsArrayList){
        this.recipeIngredientsArrayList = recipeIngredientsArrayList;
    }

    public ArrayList<RecipeSteps> getRecipeStepsArrayList(){
        return recipeStepsArrayList;
    }

    public void setRecipeStepsArrayList(ArrayList<RecipeSteps> recipeStepsArrayList){
        this.recipeStepsArrayList = recipeStepsArrayList;
    }

    public int getRecipeServings() {
        return RecipeServings;
    }

    public void setRecipeServings(int RecipeServings){
        this.RecipeServings = RecipeServings;
    }

    public int getRecipeImg() {
        return RecipeImg;
    }

    public void setRecipeImg(int RecipeImg){
        this.RecipeImg = RecipeImg;
    }


    /*
     * Implement POJO RecipeSteps
     */


    public static class RecipeSteps implements Serializable {

        private String shortDescription;
        private String description;
        private String videoURL;
        private String thumbnailURL;

        public RecipeSteps(){
        }

        public RecipeSteps(String shortDescription, String description, String videoURL, String thumbnailURL) {

            this.shortDescription = shortDescription;
            this.description = description;
            this.videoURL = videoURL;
            this.thumbnailURL = thumbnailURL;
        }

        public String getShortDescription() {return shortDescription;}
        public void setShortDescription(String shortDescription){
            this.shortDescription = shortDescription;
        }

        public String getDescription() {return description;}
        public void setDescription(String description){
            this.description = description;
        }

        public String getVideoURL() { return  videoURL;}
        public void setVideoURL(String videoURL) {
            this.videoURL = videoURL;
        }

        public String getThumbnailURL(){ return thumbnailURL;}
        public void setThumbnailURL(String thumbnailURL){
            this.thumbnailURL = thumbnailURL;
        }
    }

    /*
     * Implement POJO RecipeIngrediants
     */

    public static class RecipeIngredients implements Serializable {

        private double Quantity;
        private String Measure;
        private String Ingredient;

        public RecipeIngredients(double Quantity, String Measure, String Ingredient){
            this.Quantity = Quantity;
            this.Measure = Measure;
            this.Ingredient = Ingredient;
        }

        public double getQuantity() {
            return Quantity;
        }

        public void setQuantity(double quantity) {
            Quantity = quantity;
        }

        public String getMeasure() {
            return Measure;
        }

        public void setMeasure(String measure) {
            Measure = measure;
        }

        public String getIngredient() {
            return Ingredient;
        }

        public void setIngredient(String ingredient) {
            Ingredient = ingredient;
        }
    }




}


