package com.example.nicklastest.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicklastest.R;
import com.example.nicklastest.viewmodels.NutritionViewModel;

import java.util.Calendar;

public class NutritionNutrientsFragment extends Fragment {

    private final String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    private NutritionViewModel viewModel;

    private Calendar cldr;

    private  TextView textViewDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nutrition_nutrients, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(NutritionViewModel.class);
        viewModel.getCalendar().observe(getViewLifecycleOwner(), this::setCalendar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewDate = view.findViewById(R.id.text_view_date);
    }

    private void setCalendar(Calendar calendar) {
        cldr = calendar;

        textViewDate.setText(String.format("%s, %s/%s/%s",
                days[cldr.get(Calendar.DAY_OF_WEEK) - 1],
                cldr.get(Calendar.SHORT),
                cldr.get(Calendar.DAY_OF_MONTH)));

    }
}