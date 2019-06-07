package com.example.recipeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.recipeapp.idlingResource.RecipeListIdlingResource;
import com.example.recipeapp.model.Recipes;
import com.example.recipeapp.util.HttpHelper;
import com.example.recipeapp.util.InternetCheck;
import com.example.recipeapp.util.JsonUtil;
import com.example.recipeapp.util.RecipeAdapter;
import com.example.recipeapp.util.StepsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.OnRecipeListener{

    RecyclerView recyclerView;
    RecipeAdapter recipeAdapter;
    TextView opsTv, noInternetTv;

    Button retryBtn;

    @Nullable
    private RecipeListIdlingResource mIdlingResource;


    ArrayList<Recipes> recipesList = new ArrayList<>();

    public static final int [] recipesImages ={R.drawable.nutella_pie, R.drawable.brownies, R.drawable.yellow_cake, R.drawable.chesse_cake};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InternetCheck();



        opsTv = findViewById(R.id.ops_tv);
        noInternetTv = findViewById(R.id.no_internet_message_tv);
        retryBtn = findViewById(R.id.retry_btn);

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InternetCheck();
            }
        });

        String recipeJSONurl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        getRecipesArray(recipeJSONurl);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





    }

    public void getRecipesArray(final String recipeJSONurl){
        new HttpHelper(new HttpHelper.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                recipesList = JsonUtil.recpiesArrayList(output);

                for (int i = 0; i < recipesImages.length;i++) {

                    recipesList.get(i).setRecipeImg(recipesImages[i]);
                }

                recipeAdapter = new RecipeAdapter(recipesList,MainActivity.this);
                recyclerView.setAdapter(recipeAdapter);

            }
        }).execute(recipeJSONurl);
    }

    @Override
    public void onRecipeClick(int position) {

        Recipes recipes = recipesList.get(position);
        Intent intent = new Intent(MainActivity.this, StepsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("recipe",recipes);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void InternetCheck(){
        new InternetCheck(new InternetCheck.InternetState() {
            @Override
            public void processFinish(Boolean state) {
                if(!state){

                    recyclerView.setVisibility(View.GONE);
                    opsTv.setVisibility(View.VISIBLE);
                    noInternetTv.setVisibility(View.VISIBLE);
                    retryBtn.setVisibility(View.VISIBLE);

                } else{

                    recyclerView.setVisibility(View.VISIBLE);
                    opsTv.setVisibility(View.GONE);
                    noInternetTv.setVisibility(View.GONE);
                    retryBtn.setVisibility(View.GONE);
                }
            }
        }).execute();
    }

    @VisibleForTesting
    @NonNull
    public RecipeListIdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new RecipeListIdlingResource();
        }
        return mIdlingResource;
    }
}
