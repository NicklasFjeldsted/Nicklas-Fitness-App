package com.example.nicklastest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nicklastest.fragments.DiaryFragment;
import com.example.nicklastest.fragments.HomeFragment;
import com.example.nicklastest.fragments.NutritionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    // Fragment objects
    final private HomeFragment homeFragment = new HomeFragment();
    final private DiaryFragment diaryFragment = new DiaryFragment();
    final private NutritionFragment nutritionFragment = new NutritionFragment();

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); // Sets this activity as layout on screen

        bottomNavigationView = findViewById(R.id.bottom_navigation); // Gets navigationView

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit(); // Adds Home Fragment to the layout in activity runtime

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
}