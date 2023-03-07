package com.example.nicklastest.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nicklastest.models.PlanProgress.DirectPlanProgressResponse;
import com.example.nicklastest.models.Product.StaticProductResponse;
import com.example.nicklastest.models.ProgressMeal.DirectProgressMealResponse;
import com.example.nicklastest.models.ProgressMeal.StaticProgressMealResponse;
import com.example.nicklastest.models.SizedProduct.DirectSizedProductResponse;
import com.example.nicklastest.models.SizedProduct.SizedProductRequest;
import com.example.nicklastest.models.User.DirectUserResponse;
import com.example.nicklastest.models.UserPlan.DirectUserPlanResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserSharedViewModel extends ViewModel {
    private final MutableLiveData<DirectUserResponse> userData = new MutableLiveData<>();
    private final MutableLiveData<DirectUserPlanResponse> userPlanData = new MutableLiveData<>();
    private final MutableLiveData<List<SizedProductRequest>> requestsData = new MutableLiveData<>();

    private List<SizedProductRequest> requestsList = new ArrayList<>();


    public LiveData<DirectUserResponse> getUser() {
        return userData;
    }

    public LiveData<DirectUserPlanResponse> getUserPlan() { return userPlanData; }

    public LiveData<List<SizedProductRequest>> getRequests() { return requestsData; }

    public void setUser(DirectUserResponse user) { userData.postValue(user); }

    public void setUserPlan(DirectUserPlanResponse userPlan) { userPlanData.postValue(userPlan);}

    public void setRequests() { requestsData.postValue(requestsList); }

    public void addRequest(SizedProductRequest request) { requestsList.add(request); }


    public StaticProgressMealResponse getProgressMeal(int planProgressID, int mealTimeID) {
        return userPlanData.getValue()
                .getPlanProgress().stream()
                .filter(o -> o.getPlanProgressID().equals(planProgressID))
                .flatMap(o -> o.getProgressMeals().stream()
                .filter(x -> x.getMealTimeID().equals(mealTimeID)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s planProgressID: %s, mealTimeID: %s", "Could not find a ProgressMeal", planProgressID, mealTimeID)));
    }

    public Integer getCaloriesOfItem(DirectSizedProductResponse sizedProduct) {
        double totalCalories = 0;
        StaticProductResponse product = sizedProduct.getProduct();
        totalCalories += product.getFatAmount() * 9;
        totalCalories += (product.getCarbohydrateAmount() + product.getProteinAmount()) * 4;
        return (int)Math.round(totalCalories);
    }

    /**
     * Calculates the sum of calories in a list of DirectSizedProductResponse
     * @param sizedProducts DirectSizedProductResponses which needs to be calculated (must be a List of DirectSizedProductResponse)
     * @return Sum of calories as Integer
     */

    public Integer getSumOfCalories(List<DirectSizedProductResponse> sizedProducts) {
        Integer totalCalories = 0;
        for(DirectSizedProductResponse sizedProduct : sizedProducts) {
            totalCalories += getCaloriesOfItem(sizedProduct);
        }
        return totalCalories;
    }

    /**
     * Returns a DirectPlanProgressResponse corresponding to the current date
     * @return  DirectPlanProgressResponse
    */
    public DirectPlanProgressResponse getCurrentPlanProgress() {
        Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH) + 1;
        int year = cldr.get(Calendar.YEAR);

        String calendarDay = String.format("%s/%s/%s", day, month, year);
        return getPlanProgress(calendarDay);
    }

    public DirectPlanProgressResponse getPlanProgress(String currentPlanProgressDate) {
        for (DirectPlanProgressResponse planProgress : userPlanData.getValue().getPlanProgress()) {
            String planProgressDate = getStartDate(planProgress.getProgressDate().replace("T", " "));
            if(currentPlanProgressDate.equals(planProgressDate)) {
                return planProgress;
            }
        }
        return null;
    }

    public void updateMealProgress(DirectProgressMealResponse mealResponse) {
        DirectUserPlanResponse userPlan = userPlanData.getValue();

        userPlan.getPlanProgress().stream()
                .flatMap(o -> o.getProgressMeals().stream())
                .filter(o -> o.getProgressMealID().equals(mealResponse.getProgressMealID()))
                .peek(x -> x.setSizedProducts(mealResponse.getSizedProducts()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Does not exist"));

        userPlanData.setValue(userPlan);
    }

    private String getStartDate(String startDate) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/M/yyyy");

        return LocalDate.parse(startDate, inputFormat).format(outputFormat);
    }

}
