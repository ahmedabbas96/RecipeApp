package com.example.recipeapp.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.recipeapp.R;
import com.example.recipeapp.StepsActivity;
import com.example.recipeapp.StepsDetailedActivity;
import com.example.recipeapp.model.Recipes;

import java.util.ArrayList;


public class StepsFragment extends Fragment {

    public ArrayList<Recipes.RecipeSteps> stepsArrayList ;

    public StepsFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        ListView stepsListView = rootView.findViewById(R.id.steps_lv);


        StepsAdapter stepsAdapter = new StepsAdapter(getContext(),0,stepsArrayList);

        stepsListView.setAdapter(stepsAdapter);

        stepsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                StepsActivity stepsActivity =StepsActivity.getInstance();

                if(stepsActivity.mTwoPane){

                    stepsActivity.fragmentStepNumber = position;
                    stepsActivity.showStepFragment(position);
                }else {

                    Intent intent = new Intent(getActivity(), StepsDetailedActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("detailedStep", stepsArrayList.get(position));
                    bundle.putSerializable("stepsArray", stepsArrayList);
                    bundle.putInt("stepPosition", position);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        return rootView;

    }
}
