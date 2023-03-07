package com.example.nicklastest.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nicklastest.R;
import com.example.nicklastest.models.OpenFoodData.Nutriments;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class NutritionFragment extends Fragment {

    private Nutriments nutriments;

    private final String[] titles = new String[] { "Calories", "Nutrients", "Macronutrients" };

    // TODO: Make a nutritional table that provide each macronutrients answell as micronutrients, such as calcium, iron, magnesium etc.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutrition, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View nutritionView = view.findViewById(R.id.include_nutrition_top);
        Button backBtn = nutritionView.findViewById(R.id.go_back_btn);

        setupViewPagerView(view);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
    }

    public void setupViewPagerView(View v) {
        // set the adapter for the ViewPager
        TabLayout tabLayout = v.findViewById(R.id.tab_layout);
        ViewPager2 viewPager = v.findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle());

        viewPager.setAdapter(pagerAdapter);

        // link the TabLayout and ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(titles[position]);
                    }
                }).attach();
    }

    public static class PagerAdapter extends FragmentStateAdapter {

        public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new NutritionCaloriesFragment();
                case 1:
                    return new NutritionNutrientsFragment();
                case 2:
                    return new NutritionMacronutrientsFragment();
                default:
                    return null;
            }
        }
    }
}