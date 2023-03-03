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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nicklastest.R;
import com.example.nicklastest.UserSharedViewModel;
import com.example.nicklastest.models.OpenFoodData.DirectOpenProductResponse;
import com.example.nicklastest.models.OpenFoodData.Nutriments;
import com.example.nicklastest.models.OpenFoodData.Product;
import com.example.nicklastest.models.PlanProgress.DirectPlanProgressResponse;
import com.example.nicklastest.models.PlanProgress.PlanProgressRequest;
import com.example.nicklastest.models.Product.ProductRequest;
import com.example.nicklastest.models.Product.StaticProductResponse;
import com.example.nicklastest.models.ProgressMeal.ProgressMealRequest;
import com.example.nicklastest.models.SizedProduct.DirectSizedProductResponse;
import com.example.nicklastest.models.UserPlan.DirectUserPlanResponse;
import com.example.nicklastest.services.OpenFoodService;
import com.example.nicklastest.services.PlanProgressService;
import com.example.nicklastest.services.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import reactor.core.publisher.Mono;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class DiaryFragment extends Fragment implements View.OnClickListener {
    private final String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

    private UserSharedViewModel viewModel;
    private TextView goalText, foodText, exerciseText, remainingText, breakfastCalText, lunchCalText, dinnerCalText, snacksCalText;
    private View diaryDailyIntake;
    private Button datePickerBtn, backDateBtn, forwardDateBtn, addFoodBreakfast, addFoodLunch, addFoodDinner, addFoodSnacks;
    private RecyclerView recViewBreakfast, recViewLunch, recViewDinner, recViewSnacks;
    private Calendar cldr;
    private RecyclerView[] recViews;
    private TextView[] textViewMeals;

    private DisposableObserver<DirectPlanProgressResponse> disposable;

    private String currentDateString;
    private int currentDay, currentMonth, day, month, year, goalVal, exerciseVal, remainingVal;

    private DiaryRecyclerAdapter adapter;

    private DirectUserPlanResponse userPlan;
    private DirectPlanProgressResponse currentPlanProgress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(UserSharedViewModel.class);
        if(savedInstanceState != null) { // if is a saved instance
            currentDateString = savedInstanceState.getString("currentDateString");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_diary, container, false);

        viewModel.getUserPlan().observe(getViewLifecycleOwner(), item -> {
            userPlan = item;
            goalVal = 2800;
            applyDate();
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ASSIGNING ELEMENTS
        assignVariables(view);


        View remainingCalories = diaryDailyIntake.findViewById(R.id.diary_remaining_calories);
        remainingCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDateString = String.format("%s/%s/%s", year, month, day);
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new NutritionFragment(), "C")
                        .addToBackStack("C")
                        .commit();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentDateString", currentDateString);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.calender_back_btn:{
                    SetDate(false);
                break;
            }

            case R.id.calender_datePicker_btn:{
                DatePickerDialog picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int newYear, int newMonth, int newDay) {
                        cldr.set(newYear, newMonth, newDay);
                        applyDate();
                    }
                }, year, month , day);
                picker.show();
                break;
            }

            case R.id.calender_forward_btn: {
                SetDate(true);
                break;
            }

            case R.id.add_food_breakfast: {
                OpenAddFood(1);
                break;
            }

            case R.id.add_food_lunch: {
                OpenAddFood(2);
                break;
            }

            case R.id.add_food_dinner: {
                OpenAddFood(3);
                break;
            }

            case R.id.add_food_snacks: {
                OpenAddFood(4);
                break;
            }
        }
    }

    private void setAdapter() {
        exerciseVal = 0;
        goalText.setText(String.valueOf(goalVal).replaceAll("(?<=^\\d)(?=\\d)", ","));

        String currentDate = String.format("%s/%s/%s", day, month + 1, year); // Current date as string

        DirectPlanProgressResponse planProgress = viewModel.getPlanProgress(currentDate); // Gets PlanProgress based of its date

        if(planProgress != null) {
            currentPlanProgress = planProgress;
            int totalCalories = 0;
            for (int i = 0; i < 4; i++) {
                List<DirectSizedProductResponse> products = currentPlanProgress.getProgressMeals().get(i).getSizedProducts(); // gets products from indexed ProgressMeal
                if(products.size() != 0) {
                    int sumOfCalories = viewModel.getSumOfCalories(products); // sum of calories of products
                    textViewMeals[i].setText(String.valueOf(viewModel.getSumOfCalories(products))); // sets text
                    totalCalories += sumOfCalories;
                }

                adapter = new DiaryRecyclerAdapter(products, viewModel);
                recViews[i].setLayoutManager(new LinearLayoutManager(getContext()));
                recViews[i].setAdapter(adapter);
            }
            setDiaryDailyIntake(totalCalories);
            return;

        }
        setDiaryDailyIntake(0);
    }

    private void setDiaryDailyIntake(int totalCal) {
        exerciseText.setText(String.valueOf(exerciseVal)); // set text

        if(totalCal != 0) { // If data has been provided
            foodText.setText(String.valueOf(totalCal));
            remainingVal = goalVal - totalCal + exerciseVal;
            remainingText.setText(String.valueOf(remainingVal));
            return;
        }
        // If data not provided
        foodText.setText("0"); // set text

        for (int i = 0; i < 4; i++) {
            textViewMeals[i].setText("0"); // sets text
            adapter = new DiaryRecyclerAdapter(new ArrayList<>(), viewModel);
            recViews[i].setLayoutManager(new LinearLayoutManager(getContext()));
            recViews[i].setAdapter(adapter);
        }
        remainingText.setText(String.valueOf(goalVal + exerciseVal));
    }

    private void SetDate(boolean increment) {
        if(increment) {
            if(day == cldr.getActualMaximum(Calendar.DAY_OF_MONTH)) { // if it's the last day of the month
                if(month == Calendar.DECEMBER) { // if it's the last month of the year
                    cldr.set(Calendar.MONTH, Calendar.JANUARY); // make it january
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
                if(month == Calendar.JANUARY) { // if last month of year
                    cldr.set(Calendar.MONTH, Calendar.DECEMBER); // make it december
                    cldr.add(Calendar.YEAR, - 1); // decrement year
                } else {
                    cldr.add(Calendar.MONTH, - 1); // decrement month
                }
                cldr.set(Calendar.DAY_OF_MONTH, cldr.getActualMaximum((Calendar.DAY_OF_MONTH))); // assign last day of year

            } else {
                cldr.add(Calendar.DAY_OF_MONTH, - 1); // decrement day
            }
        }
        applyDate();
    }

    private void applyDate() {
        day = cldr.get(Calendar.DAY_OF_MONTH);
        month = cldr.get(Calendar.MONTH);
        year = cldr.get(Calendar.YEAR);

        datePickerBtn.setText(String.format("%s, %s/%s/%s", days[cldr.get(Calendar.DAY_OF_WEEK) - 1], day, month + 1, year));

        if(currentMonth == month) { // if same month
            if(day == currentDay) {
                datePickerBtn.setText(getString(R.string.diary_today));
            } else if(day == currentDay + 1) {
                datePickerBtn.setText(getString(R.string.diary_tomorrow));
            } else if(day == currentDay - 1) {
                datePickerBtn.setText(getString(R.string.diary_yesterday));
            }
        }
        setAdapter();
    }

    private void OpenAddFood(int mealTimeID) {
        if(currentPlanProgress == null) {
            List<ProgressMealRequest> meals = new ArrayList<>(); // new empty list

            for(int i = 1; i < 5; i++) {
                meals.add(new ProgressMealRequest(i, new ArrayList<>())); // add empty list
            }

            String planProgressDate = String.format("%s-%s-%sT00:00:00", year, month < 9 ? String.format("%02d", month + 1) : month +1, day < 10 ? String.format("%02d", day) : day);

            PlanProgressRequest request = new PlanProgressRequest(planProgressDate, meals, userPlan.getStartWeight(), userPlan.getUserPlanID());

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();

            PlanProgressService service = retrofit.create(PlanProgressService.class); // Create service

            Log.d("request", request.getProgressDate());

            service.Create("http://10.0.2.2:5000/api/PlanProgress/", request) // Make call
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(disposable = new DisposableObserver<DirectPlanProgressResponse>() {
                        @Override
                        public void onNext(DirectPlanProgressResponse response) {
                            Log.d("Success", "Successfully fetched DirectPlanProgressResponse");
                            currentPlanProgress = response;
                            List<DirectPlanProgressResponse> plans = userPlan.getPlanProgress();
                            plans.add(response);
                            userPlan.setPlanProgress(plans);
                            viewModel.setUserPlan(userPlan);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.wtf("Error", "There was a an error...");
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            Log.d("Completed", "Completed the subscription successfully.");
                            replaceFragment(mealTimeID);
                        }
                    });

        } else {
            replaceFragment(mealTimeID);
        }
    }

    private void replaceFragment(int mealTimeID) {
        if(disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        currentDateString = String.format("%s/%s/%s", year, month, day);

        AddFoodFragment fragment;
        fragment = AddFoodFragment.newInstance(currentPlanProgress.getPlanProgressID(), mealTimeID);

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("DiaryFragment")
                .commit();
    }

    private void assignVariables(View view) {
        cldr = Calendar.getInstance();
        currentDay = cldr.get(Calendar.DAY_OF_MONTH);
        currentMonth = cldr.get(Calendar.MONTH);

        if(currentDateString != null) {
            String[] substrings = currentDateString.split("/");
            int[] date = new int[substrings.length];
            for (int i = 0; i < substrings.length; i++) {
                date[i] = Integer.parseInt(substrings[i]);
            }
            cldr.set(date[0], date[1], date[2]);
        }

        day = cldr.get(Calendar.DAY_OF_MONTH);
        month = cldr.get(Calendar.MONTH);
        year = cldr.get(Calendar.YEAR);

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
        textViewMeals = new TextView[] { breakfastCalText, lunchCalText, dinnerCalText, snacksCalText };

        recViewBreakfast = view.findViewById(R.id.list_view_breakfast);
        recViewLunch = view.findViewById(R.id.list_view_lunch);
        recViewDinner = view.findViewById(R.id.list_view_dinner);
        recViewSnacks = view.findViewById(R.id.list_view_snacks);
        recViews = new RecyclerView[] { recViewBreakfast, recViewLunch, recViewDinner, recViewSnacks};

        datePickerBtn.setOnClickListener(this);
        backDateBtn.setOnClickListener(this);
        forwardDateBtn.setOnClickListener(this);
        addFoodBreakfast.setOnClickListener(this);
        addFoodLunch.setOnClickListener(this);
        addFoodDinner.setOnClickListener(this);
        addFoodSnacks.setOnClickListener(this);
    }

    public static class DiaryRecyclerAdapter extends RecyclerView.Adapter<DiaryRecyclerAdapter.ViewHolder>  {
        private final List<DirectSizedProductResponse> products;
        private final UserSharedViewModel viewModel;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView title, description, calCount;



            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.text_item_title);
                description = itemView.findViewById(R.id.text_item_description);
                calCount = itemView.findViewById(R.id.text_item_calorie_count);
            }
        }

        public DiaryRecyclerAdapter(List<DirectSizedProductResponse> products, UserSharedViewModel viewModel) {
            this.products = products;
            this.viewModel = viewModel;
        }

        @Override
        public DiaryRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_added_food_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DiaryRecyclerAdapter.ViewHolder holder, int position) {
            DirectSizedProductResponse item = products.get(position);
            holder.title.setText(item.getProduct().getProductName());
            holder.description.setText(String.format("%s kal., %s, %s", viewModel.getCaloriesOfItem(item), item.getProduct().getProductManufacturer(), item.getServingSize() * 100));
            holder.calCount.setText(String.valueOf(viewModel.getCaloriesOfItem(item)));
        }

        @Override
        public int getItemCount() {
            return products.size();
        }
    }
}