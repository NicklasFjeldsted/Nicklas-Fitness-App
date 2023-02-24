package com.example.nicklastest;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nicklastest.fragments.DiaryFragment;
import com.example.nicklastest.fragments.HomeFragment;
import com.example.nicklastest.fragments.NutritionFragment;
import com.example.nicklastest.models.User.DirectUserResponse;
import com.example.nicklastest.models.UserPlan.DirectUserPlanResponse;
import com.example.nicklastest.services.UserPlanService;
import com.example.nicklastest.services.UserService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    // Fragment objects
    private HomeFragment homeFragment = new HomeFragment();
    private DiaryFragment diaryFragment = new DiaryFragment();
    final private NutritionFragment nutritionFragment = new NutritionFragment();

    private DirectUserResponse user;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); // Sets this activity as layout on screen

        bottomNavigationView = findViewById(R.id.bottom_navigation); // Gets navigationView

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
                    public void onNext(DirectUserResponse response) {
                        Log.d("Success", "Successfully fetched data...");
                        user = response;
                        homeFragment = new HomeFragment().newInstance(user.getUserID());
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit(); // Adds Home Fragment to the layout in activity runtime

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.wtf("Error", "There was a an error...");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Completed", "Completed the subscription successfully.");
                        dispose();
                    }
                });


        // replaces current fragment in activity runtime with chosen fragment
        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit(); // Home Fragment
                    return true;
                case R.id.diary:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, diaryFragment).addToBackStack(null).commit(); // Diary Fragment
                    return true;
                case R.id.nutrition:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, nutritionFragment).commit(); // Nutrition Fragment
                    return true;
            }
            return false;
        });
    }
}