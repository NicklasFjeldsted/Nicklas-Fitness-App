package com.example.nicklastest;

import android.os.Bundle;
import android.util.Log;
import androidx.core.splashscreen.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.nicklastest.fragments.DiaryFragment;
import com.example.nicklastest.fragments.HomeFragment;
import com.example.nicklastest.fragments.NutritionFragment;
import com.example.nicklastest.services.UserPlanService;
import com.example.nicklastest.services.UserService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    // Fragment objects
    private final HomeFragment homeFragment = new HomeFragment();
    private final DiaryFragment diaryFragment = new DiaryFragment();
    private final NutritionFragment nutritionFragment = new NutritionFragment();

    private UserSharedViewModel viewModel;

    private boolean isDataFetched = true;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain a reference to the SharedViewModel using the ViewModelProvider
        viewModel = new ViewModelProvider(this).get(UserSharedViewModel.class);

        fetchData(); // Gets data from API

        bottomNavigationView = findViewById(R.id.bottom_navigation); // Gets navigationView

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit(); // Home Fragment

        splashScreen.setKeepOnScreenCondition(new SplashScreen.KeepOnScreenCondition() {
            @Override
            public boolean shouldKeepOnScreen() {
                return isDataFetched;
            }
        });

        // replaces current fragment in activity runtime with chosen fragment
        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit(); // Home Fragment
                    return true;
                case R.id.diary:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, diaryFragment).commit(); // Diary Fragment
                    return true;
                case R.id.nutrition:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, nutritionFragment).commit(); // Nutrition Fragment
                    return true;
            }
            return false;
        });
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate("previous", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            super.onBackPressed();
        }
    }

    private void fetchData() {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/api/User/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // userPlan retrofit
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/api/UserPlan/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Initialize the API services
        UserService userService = retrofit1.create(UserService.class);
        UserPlanService userPlanService = retrofit2.create(UserPlanService.class);

        Observable.just(true)
                .repeatWhen(observable -> observable
                        .flatMap(o -> {
                            if (!isDataFetched) {
                                Log.d("INFO", "Stopping repeating process");
                                return Observable.empty();
                            } else {
                                return userService.GetById(1)
                                        .doOnError(Throwable::printStackTrace)
                                        .flatMap(user -> {
                                            Log.d("SUCCESS", "Found user");
                                            viewModel.setUser(user);
                                            return userPlanService.GetById(user.getUserPlan().getUserPlanID());
                                        })
                                        .doOnNext(userPlan -> {
                                            // Handle the response from the getUserPlan API call
                                            Log.d("SUCCESS", "Found userPlan");
                                            viewModel.setUserPlan(userPlan);
                                            isDataFetched = false;
                                        })
                                        .doOnError(Throwable::printStackTrace)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .delay(5, TimeUnit.SECONDS) // Add a delay between API calls to avoid excessive network traffic
                                        .flatMap(aBoolean -> Observable.just(o));
                            }
                        })
                )
                .subscribe();
    }
}