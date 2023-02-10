package com.example.nicklastest.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nicklastest.R;
import com.example.nicklastest.models.PlanProgress.DirectPlanProgressResponse;
import com.example.nicklastest.models.ProgressMeal.StaticProgressMealResponse;
import com.example.nicklastest.models.SizedProduct.DirectSizedProductResponse;
import com.example.nicklastest.models.SizedProduct.SizedProductDisplay;
import com.example.nicklastest.models.User.DirectUserResponse;
import com.example.nicklastest.models.UserPlan.DirectUserPlanResponse;
import com.example.nicklastest.services.UserPlanService;
import com.example.nicklastest.services.UserService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class DiaryFragment extends Fragment implements View.OnClickListener {
    private final String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    private DatePickerDialog picker;
    private TextView goalText, foodText, exerciseText, remainingText, breakfastCalText, lunchCalText, dinnerCalText, snacksCalText;
    private View diaryDailyIntake;
    private Button datePickerBtn, backDateBtn, forwardDateBtn, addFoodBreakfast, addFoodLunch, addFoodDinner, addFoodSnacks;
    private RecyclerView recViewBreakfast, recViewLunch, recViewDinner, recViewSnacks;
    private Calendar cldr;
    private int currentDay, currentMonth, day, month, year;
    private CustomAdapter adapter;
    private DirectUserPlanResponse userPlan;
    private DirectPlanProgressResponse currentPlanProgress;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_diary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ASSIGNING ELEMENTS
        assignVariables(view);

        if(userPlan == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/api/UserPlan/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();

            UserPlanService service = retrofit.create(UserPlanService.class);
            service.GetById(1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<DirectUserPlanResponse>() {
                        @Override
                        public void onNext(@io.reactivex.rxjava3.annotations.NonNull DirectUserPlanResponse response) {
                            Log.d("Success", "Successfully fetched data...");
                            userPlan = new DirectUserPlanResponse(response.getUserPlanID(), response.getStartWeight(), response.getStartDate(), response.getWeightGoal(), response.getWeeklyGoal(), response.getPlanProgress(), response.getActivityLevelID());
                            setAdapter();
                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                            Log.wtf("Error", "There was a an error...");
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            Log.d("Completed", "Completed the subscription successfully.");
                            dispose();
                        }
                    });
        }

        // TODO: FETCH DATA FROM DATABASE AND DISPLAY PRODUCTS AND MEAL CALORIES (DO NOT DELETE!!)
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

            case R.id.add_food_breakfast: {
                OpenAddFood("Breakfast");
                break;
            }

            case R.id.add_food_lunch: {
                OpenAddFood("Lunch");
                break;
            }

            case R.id.add_food_dinner: {
                OpenAddFood("Dinner");
                break;
            }

            case R.id.add_food_snacks: {
                OpenAddFood("Snacks");
                break;
            }
        }
    }

    public void setAdapter() {
        if(userPlan.getUserPlanID() == 1) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); // DATE FORMAT EXAMPLE 2023-01-31

            String currentDay = String.format("%s-%s-%s", year, month, day);
            for (DirectPlanProgressResponse planProgress : userPlan.getPlanProgress()) { // GETS ALL PLAN PROGRESS
                Date result = new SimpleDateFormat("ddMMMyyyy HH:mm",Locale.ENGLISH).parse(planProgress.getProgressDate().toString());
                Log.d("ASD", planProgress.getProgressDate());
                String apiDay = String.format("%s", dateFormat.format(planProgress.getProgressDate()));
                if(dateFormat.format(apiDay) == dateFormat.format(currentDay)) { // PLAN PROGRESS EXISTS IN USERPLAN
                    currentPlanProgress = planProgress;
                    Log.d("SUCCESS", "FOUND DATE");
                }
            }
            RecyclerView[] recViews = { recViewBreakfast, recViewLunch, recViewDinner, recViewSnacks};
            for (int i = 0; i < 4; i++) {
                List<DirectSizedProductResponse> products = currentPlanProgress.getProgressMeals().get(i).getSizedProducts();
                adapter = new CustomAdapter(products);
                recViews[i].setLayoutManager(new LinearLayoutManager(getContext()));
                recViews[i].setAdapter(adapter);
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

    public void assignVariables(View view) {
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
        goalText = diaryDailyIntake.findViewById(R.id.text_goal_amount);
        foodText = diaryDailyIntake.findViewById(R.id.text_food_amount);
        exerciseText = diaryDailyIntake.findViewById(R.id.text_exercise_amount);
        remainingText = diaryDailyIntake.findViewById(R.id.text_remaining_amount);

        addFoodBreakfast = view.findViewById(R.id.add_food_breakfast);
        addFoodLunch = view.findViewById(R.id.add_food_lunch);
        addFoodDinner = view.findViewById(R.id.add_food_dinner);
        addFoodSnacks = view.findViewById(R.id.add_food_snacks);

        breakfastCalText = view.findViewById(R.id.text_breakfast_calories);
        lunchCalText = view.findViewById(R.id.text_lunch_calories);
        dinnerCalText = view.findViewById(R.id.text_dinner_calories);
        snacksCalText = view.findViewById(R.id.text_snacks_calories);

        recViewBreakfast = view.findViewById(R.id.list_view_breakfast);
        recViewLunch = view.findViewById(R.id.list_view_lunch);
        recViewDinner = view.findViewById(R.id.list_view_dinner);
        recViewSnacks = view.findViewById(R.id.list_view_snacks);

        datePickerBtn.setOnClickListener(this);
        backDateBtn.setOnClickListener(this);
        forwardDateBtn.setOnClickListener(this);
        addFoodBreakfast.setOnClickListener(this);
        addFoodLunch.setOnClickListener(this);
        addFoodDinner.setOnClickListener(this);
        addFoodSnacks.setOnClickListener(this);
    }

    public void OpenAddFood(String mealTime) {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new AddFoodFragment().newInstance(mealTime))
                .addToBackStack(null)
                .commit();
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>  {
        private List<DirectSizedProductResponse> products;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView title, description, calCount;

            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.text_item_title);
                description = itemView.findViewById(R.id.text_item_description);
                calCount = itemView.findViewById(R.id.text_item_calorie_count);
            }
        }

        public CustomAdapter(List<DirectSizedProductResponse> products) {
            this.products = products;
        }

        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_added_food_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
            DirectSizedProductResponse item = products.get(position);
            holder.title.setText(item.getProduct().getProductName());
            holder.description.setText(String.format("%s., %s, %s", 160, item.getProduct().getProductManufacturer(), item.getServingSize() * 100));
            holder.calCount.setText(String.format("%s", 100));
        }

        @Override
        public int getItemCount() {
            return products.size();
        }
    }
}