package com.example.nicklastest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nicklastest.models.PlanProgress.DirectPlanProgressResponse;
import com.example.nicklastest.models.Product.StaticProductResponse;
import com.example.nicklastest.models.SizedProduct.DirectSizedProductResponse;
import com.example.nicklastest.models.UserPlan.DirectUserPlanResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class UserPlanSharedViewModel extends ViewModel {
    private MutableLiveData<DirectUserPlanResponse> selected = new MutableLiveData<>();

    private Calendar cldr;
    private int day, month, year;

    public void setSelected(DirectUserPlanResponse userPlan) {
        selected.setValue(userPlan);
    }

    public LiveData<DirectUserPlanResponse> getSelected() {
        return selected;
    }

    public Integer getCaloriesOfItem(DirectSizedProductResponse sizedProduct) {
        double totalCalories = 0;
        StaticProductResponse product = sizedProduct.getProduct();
        totalCalories += product.getFatAmount() * 9;
        totalCalories += (product.getCarbohydrateAmount() + product.getProteinAmount()) * 4;
        return (int)Math.round(totalCalories);
    }

    public Integer getSumOfCalories(List<DirectSizedProductResponse> sizedProducts) {
        double totalCalories = 0;
        for(DirectSizedProductResponse sizedProduct : sizedProducts) {
            StaticProductResponse product = sizedProduct.getProduct();
            totalCalories += product.getFatAmount() * 9;
            totalCalories += (product.getCarbohydrateAmount() + product.getProteinAmount()) * 4;
        }
        return (int)Math.round(totalCalories);
    }

    public DirectPlanProgressResponse getCurrentPlanProgress() {
        cldr = Calendar.getInstance();
        day = cldr.get(Calendar.DAY_OF_MONTH);
        month = cldr.get(Calendar.MONTH) + 1;
        year = cldr.get(Calendar.YEAR);

        for(DirectPlanProgressResponse planProgress : selected.getValue().getPlanProgress()) {
            String planProgressDate = getStartDate(planProgress.getProgressDate().replace("T", " "));
            String calendarDay = String.format("%s/%s/%s", day, month, year);
            if(calendarDay.equals(planProgressDate)) {
                return planProgress;
            }
        }
        return null;
    }

    public DirectPlanProgressResponse getPlanProgress(String currentPlanProgressDate) {
        for (DirectPlanProgressResponse planProgress : selected.getValue().getPlanProgress()) {
            String planProgressDate = getStartDate(planProgress.getProgressDate().replace("T", " "));
            if(currentPlanProgressDate.equals(planProgressDate)) {
                return planProgress;
            }
        }
        return null;
    }

    private String getStartDate(String startDate) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/M/yyyy");

        return LocalDate.parse(startDate, inputFormat).format(outputFormat);
    }
}
