package com.example.recipeapp.ingredientWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.example.recipeapp.R;
import com.example.recipeapp.StepsActivity;

public class ingredientWidget extends AppWidgetProvider {

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {

        Intent intent = getIngredientServiceIntent(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredient_widget);
        views.setRemoteAdapter(R.id.ingredients_lv, intent);

        //Set Name of recipe , get name of saved recipe using Shared Preferences
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        String recipeName = appSharedPrefs.getString(StepsActivity.PREF_KEY_RECIPE_NAME, context.getString(R.string.ingredients));
        views.setTextViewText(R.id.about_ingredient_tv, recipeName);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds ){
            updateAppWidget(context,appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {

    }

    public static Intent getIngredientServiceIntent(Context context) {
        return new Intent(context, IngredientWidgetService.class);
    }

}
