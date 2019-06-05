package com.example.recipeapp.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipeapp.R;
import com.example.recipeapp.model.Recipes;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    List<Recipes> recipesList;
    Context context;
    private OnRecipeListener onRecipeListener;

    public RecipeAdapter(List<Recipes> recipesList, OnRecipeListener onRecipeListener){
        this.recipesList = recipesList;
        this.onRecipeListener = onRecipeListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view, onRecipeListener);
        context = viewGroup.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Recipes recipes = recipesList.get(position);

        viewHolder.imageView.setImageResource(recipes.getRecipeImg());
        viewHolder.recipeNameTV.setText(recipes.getRecipeName());
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        CardView cardView;
        TextView recipeNameTV;
        TextView servingTV;
        OnRecipeListener onRecipeListener;

        public ViewHolder(View itemView, OnRecipeListener onRecipeListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recipe_image);
            cardView = itemView.findViewById(R.id.card_view);
            recipeNameTV = itemView.findViewById(R.id.recipe_name);
            servingTV = itemView.findViewById(R.id.serving);
            this.onRecipeListener = onRecipeListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onRecipeListener.onRecipeClick(getAdapterPosition());

        }
    }

    public interface OnRecipeListener{
        void onRecipeClick(int position);
    }
}
