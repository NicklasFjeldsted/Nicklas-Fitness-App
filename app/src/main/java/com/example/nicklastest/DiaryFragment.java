package com.example.nicklastest;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;

import java.util.Calendar;


public class DiaryFragment extends Fragment implements View.OnClickListener {
    DatePickerDialog picker;
    View diaryDailyIntake;
    Button datePickerBtn;
    Button backDateBtn;
    Button forwardDateBtn;
    Calendar cldr;
    int currentDay, currentMonth, day, month, year;
    String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        cldr = Calendar.getInstance();
        day = cldr.get(Calendar.DAY_OF_MONTH);
        month = cldr.get(Calendar.MONTH) + 1;
        year = cldr.get(Calendar.YEAR);
        currentDay = day;
        currentMonth = month;


        diaryDailyIntake = view.findViewById(R.id.diary_daily_intake);
        datePickerBtn = diaryDailyIntake.findViewById(R.id.calender_datePicker_btn);
        backDateBtn = diaryDailyIntake.findViewById(R.id.calender_back_btn);
        forwardDateBtn = diaryDailyIntake.findViewById(R.id.calender_forward_btn);
        datePickerBtn.setOnClickListener(this);
        backDateBtn.setOnClickListener(this);
        forwardDateBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.calender_back_btn:{
                    SetDate(false);
                break;
            }

            case R.id.calender_datePicker_btn:{
                picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int newYear, int newMonth, int newDay) {
                        cldr.set(newYear, newMonth, newDay);
                        applyDate();
                    }
                }, year, month, day);
                picker.show();
                break;
            }

            case R.id.calender_forward_btn: {
                SetDate(true);
                break;
            }
        }
    }

    public void SetDate(boolean increment) {
        if(increment) {
            if(day == cldr.getActualMaximum(Calendar.DATE)) { // if it's the last day of the month
                if(month == Calendar.DECEMBER) { // if it's the last month of the year
                    cldr.add(Calendar.MONTH, Calendar.JANUARY); // make it january
                    cldr.add(Calendar.YEAR, + 1); // increment year
                }
                else {
                    cldr.add(Calendar.MONTH, + 1); // increment month
                }
                cldr.set(Calendar.DAY_OF_MONTH, 1); // assign first day of month

            } else {
                cldr.add(Calendar.DAY_OF_MONTH, + 1); // increment day
            }
        }
        else {
            if(day == 1) { // if last day of month
                if(month == 1) { // if last month of year
                    cldr.add(Calendar.MONTH, Calendar.DECEMBER); // make it december
                    cldr.add(Calendar.YEAR, - 1); // decrement year
                } else {
                    cldr.add(Calendar.MONTH, - 1); // decrement year
                }
                cldr.set(Calendar.DAY_OF_MONTH, cldr.getActualMaximum((Calendar.DAY_OF_MONTH))); // assign last day of year

            } else {
                cldr.add(Calendar.DAY_OF_MONTH, - 1); // decrement day
            }
        }
        applyDate();
    }

    public void applyDate() {
        day = cldr.get(Calendar.DAY_OF_MONTH);
        month = cldr.get(Calendar.MONTH);
        year = cldr.get(Calendar.YEAR);

        datePickerBtn.setText(String.format("%s, %s/%s/%s", days[cldr.get(Calendar.DAY_OF_WEEK) - 1], day, month + 1, year));

        if(currentMonth == month + 1) { // if same month
            if(day == currentDay) {
                datePickerBtn.setText("Today");
            } else if(day == currentDay + 1) {
                datePickerBtn.setText("Tomorrow");
            } else if(day == currentDay - 1) {
                datePickerBtn.setText("Yesterday");
            }
        }
    }
}