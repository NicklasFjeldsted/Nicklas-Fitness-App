package com.example.nicklastest.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nicklastest.R;
import com.example.nicklastest.models.OpenFoodData.Nutriments;

public class NutritionFragment extends Fragment {

    private Nutriments nutriments;


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

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
    }


}