package com.example.nicklastest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nicklastest.models.PlanProgress.DirectPlanProgressResponse;
import com.example.nicklastest.models.Product.StaticProductResponse;
import com.example.nicklastest.models.ProgressMeal.DirectProgressMealResponse;
import com.example.nicklastest.models.ProgressMeal.StaticProgressMealResponse;
import com.example.nicklastest.models.SizedProduct.DirectSizedProductResponse;
import com.example.nicklastest.models.SizedProduct.SizedProductRequest;
import com.example.nicklastest.models.UserPlan.DirectUserPlanResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserPlanSharedViewModel extends ViewModel {
    private final MutableLiveData<DirectUserPlanResponse> selected = new MutableLiveData<>();
    private final MutableLiveData<List<SizedProductRequest>> requests = new MutableLiveData<>();

    private List<SizedProductRequest> requestsList = new ArrayList<>();

    public void setSelected(DirectUserPlanResponse userPlan) {
        selected.setValue(userPlan);
    }

    public void addRequest(SizedProductRequest request) {
        requestsList.add(request);
        requests.setValue(requestsList);
    }

    public LiveData<DirectUserPlanResponse> getSelected() {
        return selected;
    }

    public LiveData<List<SizedProductRequest>> getRequests() {return requests; }

    public StaticProgressMealResponse getProgressMeal(int planProgressID, int mealTimeID) {
        return selected.getValue()
                .getPlanProgress().stream()
                .filter(o -> o.getPlanProgressID().equals(planProgressID))
                .flatMap(o -> o.getProgressMeals().stream()
                .filter(x -> x.getMealTimeID().equals(mealTimeID)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Somevalue"));
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
        Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH) + 1;
        int year = cldr.get(Calendar.YEAR);

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

    public void updateMealProgress(DirectProgressMealResponse mealResponse) {
        DirectUserPlanResponse userPlan = selected.getValue();

        userPlan.getPlanProgress().stream()
                .flatMap(o -> o.getProgressMeals().stream())
                .filter(o -> o.getProgressMealID().equals(mealResponse.getProgressMealID()))
                .peek(x -> x.setSizedProducts(mealResponse.getSizedProducts()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Does not exist"));

        selected.setValue(userPlan);
    }

    private String getStartDate(String startDate) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/M/yyyy");

        return LocalDate.parse(startDate, inputFormat).format(outputFormat);
    }

}
