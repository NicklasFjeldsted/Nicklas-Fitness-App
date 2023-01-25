package com.example.nicklastest;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    final HomeFragment homeFragment = new HomeFragment();
    final DiaryFragment diaryFragment = new DiaryFragment();
    final NutritionFragment nutritionFragment = new NutritionFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                    return true;
                case R.id.diary:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, diaryFragment).commit();
                    return true;
                case R.id.nutrition:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, nutritionFragment).commit();
                    return true;
            }
            return false;
        });
    }
}