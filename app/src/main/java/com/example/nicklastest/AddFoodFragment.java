package com.example.nicklastest;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFoodFragment extends Fragment {

    private static final String ARG_PARAM1 = "ARG_MEAL_TIME";

    private String mealTime;

    public static AddFoodFragment newInstance(String param1) {
        AddFoodFragment fragment = new AddFoodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mealTime = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_food, container, false);

        // Sets title text
        TextView textView = view.findViewById(R.id.meal_time_title);
        textView.setText(mealTime);

        Button pressButton = view.findViewById(R.id.press_button);
        pressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryFragment diaryFragment = new DiaryFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, diaryFragment).commit();
            }
        });
        return view;
    }
}