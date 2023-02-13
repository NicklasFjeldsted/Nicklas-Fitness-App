package com.example.nicklastest.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nicklastest.MainActivity;
import com.example.nicklastest.R;
import com.example.nicklastest.UserPlanSharedViewModel;
import com.example.nicklastest.models.MealTime.StaticMealTimeResponse;
import com.example.nicklastest.models.PlanProgress.DirectPlanProgressResponse;
import com.example.nicklastest.models.Product.StaticProductResponse;
import com.example.nicklastest.models.ProgressMeal.StaticProgressMealResponse;
import com.example.nicklastest.models.User.DirectUserResponse;
import com.example.nicklastest.models.UserPlan.DirectUserPlanResponse;
import com.example.nicklastest.services.ProductService;
import com.example.nicklastest.services.UserPlanService;
import com.example.nicklastest.services.UserService;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.NumberFormat;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private UserPlanSharedViewModel viewModel;

    private DirectUserPlanResponse userPlan;
    private int userPlanID;

    private int baseGoalVal, currentCaloriesVal, burnedCaloriesVal, remainingCaloriesVal;

    private View goalItemLayout, stepsItemLayout, exerciseItemLayout;
    private TextView textBaseGoal, textCurrentCalories, textBurnedCalories, textRemainingCalories, textStepCount, textExerciseCalories;
    private Button btnCalorieGoal, btnStep;
    private CircularProgressBar progressBar;

    public static HomeFragment newInstance(int userPlanID) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("userPlanID", userPlanID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userPlanID = getArguments().getInt("userPlanID", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(UserPlanSharedViewModel.class);

        viewModel.getSelected().observe(getViewLifecycleOwner(), item -> {
            // Update the UI with the selected item
            baseGoalVal = 2800;
            textBaseGoal.setText(String.valueOf(baseGoalVal));
            DirectPlanProgressResponse currentPlanProgress = viewModel.getCurrentPlanProgress();
            if(currentPlanProgress != null) {
                burnedCaloriesVal = 100;
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
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assignVariables(view);

        if(userPlan == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/api/UserPlan/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();

            UserPlanService service = retrofit.create(UserPlanService.class);
            service.GetById(userPlanID)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<DirectUserPlanResponse>() {
                        @Override
                        public void onNext(DirectUserPlanResponse response) {
                            Log.d("Success", "Successfully fetched data...");
                            userPlan = response;
                            viewModel.setSelected(userPlan);
                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                            Log.wtf("Error", "There was a an error...");
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            Log.d("Completed", "Completed the subscription successfully.");
                            dispose();
                        }
                    });
        }
    }


    private void assignVariables(View v) {
        goalItemLayout = v.findViewById(R.id.goal_item_layout);
        stepsItemLayout = v.findViewById(R.id.steps_item_layout);
        exerciseItemLayout = v.findViewById(R.id.exercise_item_layout);

        btnCalorieGoal = goalItemLayout.findViewById(R.id.btn_calorie_goal_item);

        textBaseGoal = goalItemLayout.findViewById(R.id.text_base_goal);
        textCurrentCalories = goalItemLayout.findViewById(R.id.text_current_calories);
        textBurnedCalories = goalItemLayout.findViewById(R.id.text_burned_calories);
        textRemainingCalories = goalItemLayout.findViewById(R.id.text_remaining_calories);

        progressBar = goalItemLayout.findViewById(R.id.circularProgressBar);

        textStepCount = stepsItemLayout.findViewById(R.id.text_step_count);

        textExerciseCalories = exerciseItemLayout.findViewById(R.id.text_exercise_item_calories_burned);
    }
}