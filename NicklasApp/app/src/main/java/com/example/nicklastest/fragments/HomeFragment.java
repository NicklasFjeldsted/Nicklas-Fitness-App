package com.example.nicklastest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nicklastest.R;
import com.example.nicklastest.UserSharedViewModel;
import com.example.nicklastest.models.PlanProgress.DirectPlanProgressResponse;
import com.example.nicklastest.models.ProgressMeal.StaticProgressMealResponse;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class HomeFragment extends Fragment {
    private UserSharedViewModel viewModel;

    private int baseGoalVal, currentCaloriesVal, burnedCaloriesVal, remainingCaloriesVal;

    private View goalItemLayout, stepsItemLayout, exerciseItemLayout;
    private TextView textBaseGoal, textCurrentCalories, textBurnedCalories, textRemainingCalories, textStepCount, textExerciseCalories;
    private Button btnCalorieGoal, btnStep;
    private CircularProgressBar progressBar;

    private CardView cardView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(UserSharedViewModel.class);

        viewModel.getUserPlan().observe(getViewLifecycleOwner(), item -> {
            // Update the UI with the selected item
            currentCaloriesVal = 0;
            burnedCaloriesVal = 0;
            remainingCaloriesVal = 0;
            displayData();
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assignVariables(view);

        goalItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new NutritionFragment(), "C")
                        .addToBackStack("previous")
                        .commit();
            }
        });
    }

    private void displayData() {
        baseGoalVal = 2800;
        textBaseGoal.setText(String.valueOf(baseGoalVal));
        DirectPlanProgressResponse currentPlanProgress = viewModel.getCurrentPlanProgress();
        if(currentPlanProgress != null) {
            for (StaticProgressMealResponse meal : currentPlanProgress.getProgressMeals()) {
                currentCaloriesVal += viewModel.getSumOfCalories(meal.getSizedProducts());
            }
            remainingCaloriesVal = baseGoalVal - currentCaloriesVal + burnedCaloriesVal;

            textCurrentCalories.setText(String.valueOf(currentCaloriesVal));
            textBurnedCalories.setText(String.valueOf(burnedCaloriesVal));
            textRemainingCalories.setText(String.valueOf(remainingCaloriesVal));

            progressBar.setProgressMax(baseGoalVal);
            progressBar.setProgress(currentCaloriesVal);


        } else {
            textCurrentCalories.setText(String.valueOf(0));
            textBurnedCalories.setText(String.valueOf(0));
            textRemainingCalories.setText(String.valueOf(baseGoalVal));
        }
    }


    private void assignVariables(View v) {
        goalItemLayout = v.findViewById(R.id.goal_item_layout);
        stepsItemLayout = v.findViewById(R.id.steps_item_layout);
        exerciseItemLayout = v.findViewById(R.id.exercise_item_layout);

        textBaseGoal = goalItemLayout.findViewById(R.id.text_base_goal);
        textCurrentCalories = goalItemLayout.findViewById(R.id.text_current_calories);
        textBurnedCalories = goalItemLayout.findViewById(R.id.text_burned_calories);
        textRemainingCalories = goalItemLayout.findViewById(R.id.text_remaining_calories);

        progressBar = goalItemLayout.findViewById(R.id.circularProgressBar);

        textStepCount = stepsItemLayout.findViewById(R.id.text_step_count);

        textExerciseCalories = exerciseItemLayout.findViewById(R.id.text_exercise_item_calories_burned);
    }
}