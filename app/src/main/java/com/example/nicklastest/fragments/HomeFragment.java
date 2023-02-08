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

import com.example.nicklastest.R;
import com.example.nicklastest.models.Product.StaticProductResponse;
import com.example.nicklastest.models.User.DirectUserResponse;
import com.example.nicklastest.services.ProductService;
import com.example.nicklastest.services.UserService;

import java.text.NumberFormat;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private int progress = 0;
    private int baseGoal = 2500;
    private int currentCalories = 1250;
    private int burnedCalories = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        View goalLayout = view.findViewById(R.id.include_goal_layout);
        View stepLayout = view.findViewById(R.id.steps_counter_layout);
        View exerciseLayout = view.findViewById(R.id.exercise_item_layout);
        Button calorieBtn = goalLayout.findViewById(R.id.calorieButton);
        Button stepBtn = stepLayout.findViewById(R.id.step_button);
        TextView textView = view.findViewById(R.id.greeting);

        updateCalorieProgress(goalLayout, exerciseLayout);
        stepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:5000/api/User/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                        .build();

                UserService service = retrofit.create(UserService.class);
                service.GetById(1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableObserver<DirectUserResponse>() {
                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull DirectUserResponse response) {
                                Log.d("Success", "Successfully fetched data...");
                                textView.setText(String.format("Success: Hello %s!",response.getFirstName()));
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                Log.wtf("Error", "There was a an error...");
                                e.printStackTrace();
                                textView.setText("Error!");
                            }

                            @Override
                            public void onComplete() {
                                Log.d("Completed", "Completed the subscription successfully.");
                                dispose();
                            }
                        });
            }
        });

        calorieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:5000/api/Product/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                        .build();

                ProductService service = retrofit.create(ProductService.class);
                service.GetById("1234567")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableObserver<StaticProductResponse>() {
                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull StaticProductResponse response) {
                                Log.d("Success", "Successfully fetched data...");
                                textView.setText(String.format("Success: %s",response.getProductCode()));
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                Log.wtf("Error", "There was a an error...");
                                textView.setText("Error!");
                            }

                            @Override
                            public void onComplete() {
                                Log.d("Completed", "Completed the subscription successfully.");
                                dispose();
                            }
                        });
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void updateCalorieProgress(View goalView, View exerciseView) {
        TextView baseGoalText = goalView.findViewById(R.id.base_goal);
        TextView currentCaloriesText = goalView.findViewById(R.id.current_calories);
        TextView burnedCaloriesText = goalView.findViewById(R.id.burned_calories);
        TextView remainingCaloriesText = goalView.findViewById(R.id.remaining_calories);
        TextView exerciseCaloriesBurned = exerciseView.findViewById(R.id.calories_burned);

        baseGoalText.setText(String.valueOf(baseGoal));
        currentCaloriesText.setText(String.valueOf(currentCalories));
        burnedCaloriesText.setText(String.valueOf(burnedCalories));
        exerciseCaloriesBurned.setText(String.valueOf(burnedCalories));
        progress = currentCalories + burnedCalories;
        remainingCaloriesText.setText(NumberFormat.getInstance(Locale.GERMAN).format(progress < baseGoal ? baseGoal - currentCalories : 0));

    }
}