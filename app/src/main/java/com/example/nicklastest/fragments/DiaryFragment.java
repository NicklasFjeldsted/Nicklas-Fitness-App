package com.example.nicklastest.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
import com.example.nicklastest.models.SizedProduct.SizedProductDisplay;
import java.util.Calendar;


public class DiaryFragment extends Fragment implements View.OnClickListener {
    private final String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    private DatePickerDialog picker;
    private View diaryDailyIntake;
    private Button datePickerBtn, backDateBtn, forwardDateBtn, addFoodBreakfast, addFoodLunch, addFoodDinner, addFoodSnacks;
    private RecyclerView recViewBreakfast, recViewLunch, recViewDinner, recViewSnacks;
    private Calendar cldr;
    private int currentDay, currentMonth, day, month, year;
    private CustomAdapter adapter;
    private final SizedProductDisplay[] products = {
            new SizedProductDisplay("Product1", "This is a product", 101),
            new SizedProductDisplay("Product2", "This is a product2", 102),
            new SizedProductDisplay("Product3", "This is a product3", 103)};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_diary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ASSIGNING ELEMENTS
        assignVariables(view);

        // Displays products in each Meal
        adapter = new CustomAdapter(products);
        RecyclerView[] recViews = { recViewBreakfast, recViewLunch, recViewDinner, recViewSnacks};
        for (RecyclerView recView : recViews) {
            recView.setLayoutManager(new LinearLayoutManager(getContext()));
            recView.setAdapter(adapter);
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
        addFoodBreakfast = view.findViewById(R.id.add_food_breakfast);
        addFoodLunch = view.findViewById(R.id.add_food_lunch);
        addFoodDinner = view.findViewById(R.id.add_food_dinner);
        addFoodSnacks = view.findViewById(R.id.add_food_snacks);
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
        private SizedProductDisplay[] products;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView title, description, calCount;

            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.text_item_title);
                description = itemView.findViewById(R.id.text_item_description);
                calCount = itemView.findViewById(R.id.text_item_calorie_count);
            }
        }

        public CustomAdapter(SizedProductDisplay[] products) {
            this.products = products;
        }

        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.added_food_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
            SizedProductDisplay item = products[position];
            holder.title.setText(item.getName());
            holder.description.setText(item.getDescription());
            holder.calCount.setText(String.format("%s", products[position].getCalorieCount()));
        }

        @Override
        public int getItemCount() {
            return products.length;
        }
    }
}