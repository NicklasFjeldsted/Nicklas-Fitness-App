package com.example.nicklastest.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;

public class NutritionViewModel extends ViewModel {
    private final MutableLiveData<Calendar> calendarData = new MutableLiveData<>();

    public LiveData<Calendar> getCalendar() { return calendarData; }

    public void setCalendar(Calendar calendar) { calendarData.postValue(calendar); }
}
