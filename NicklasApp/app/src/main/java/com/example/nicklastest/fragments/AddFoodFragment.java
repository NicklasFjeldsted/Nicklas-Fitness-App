package com.example.nicklastest.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.nicklastest.R;
import com.example.nicklastest.models.ProgressMeal.DirectProgressMealResponse;
import com.example.nicklastest.models.ProgressMeal.ProgressMealPatchRequest;
import com.example.nicklastest.models.ProgressMeal.StaticProgressMealResponse;
import com.example.nicklastest.models.SizedProduct.SizedProductRequest;
import com.example.nicklastest.services.ProgressMealService;
import com.example.nicklastest.viewmodels.UserSharedViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFoodFragment extends Fragment {

    private static final String ARG_PARAM1 = "ARG_PLAN_PROGRESS_ID";
    private static final String ARG_PARAM2 = "ARG_MEAL_TIME_ID";

    private int planProgressID, mealTimeID;

    private SearchView searchView;
    private Button goBackBtn;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private TextView mealTimeText;
    private PagerAdapter pagerAdapter;
    private String[] titles = new String[] { "All", "My Meals", "My Recipes" };
    private String[] mealTimes = new String[] { "Breakfast", "Lunch", "Dinner", "Snacks" };
    private List<SizedProductRequest> productRequests;
    private UserSharedViewModel viewModel;

    // GETS CURRENT PLANPROGRESS AND THE MEALTIME ID
    public static AddFoodFragment newInstance(int planProgressID, int mealTimeID) {
        AddFoodFragment fragment = new AddFoodFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, planProgressID);
        args.putInt(ARG_PARAM2, mealTimeID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(UserSharedViewModel.class);
        if (getArguments() != null) {
            planProgressID = getArguments().getInt(ARG_PARAM1);
            mealTimeID = getArguments().getInt(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_food, container, false);
        viewModel.getRequests().observe(getViewLifecycleOwner(), requests -> {
            Log.d("productRequests", "productRequests has been assigned");
            productRequests = requests;

            Log.d("request size", String.valueOf(productRequests.size()));
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View addFoodTopLayout = view.findViewById(R.id.layout_add_food_top);
        mealTimeText = view.findViewById(R.id.text_meal_title);
        goBackBtn = addFoodTopLayout.findViewById(R.id.btn_go_back);
        searchView = addFoodTopLayout.findViewById(R.id.search_view);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);

        setupViewPager();

        // Sets title text
        mealTimeText.setText(mealTimes[mealTimeID - 1]);


        // Goes back to DiaryFragment
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICKED", "YOU HAVE CLICKED THE GO BACK BUTTON");
                if(productRequests.size() != 0) {
                    Log.d("AddFoodFragment", "Calling http://10.0.2.2:5000/api/ProgressMeal");
                    StaticProgressMealResponse meal = viewModel.getProgressMeal(planProgressID, mealTimeID);

                    ProgressMealPatchRequest request = new ProgressMealPatchRequest(productRequests);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:5000/api/ProgressMeal/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .build();

                    ProgressMealService service = retrofit.create(ProgressMealService.class); // Create service

                    service.PatchUpdate(meal.getProgressMealID(), request) // Make call
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new DisposableObserver<DirectProgressMealResponse>() {
                                @Override
                                public void onNext(DirectProgressMealResponse response) {
                                    Log.d("updateMeal", "Updating meal response");
                                    viewModel.updateMealProgress(response);
                                }

                                @Override
                                public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                    Log.wtf("Error", "There was a an error...");
                                    e.printStackTrace();
                                }

                                @Override
                                public void onComplete() {
                                    Log.d("Completed", "Completed the subscription successfully.");
                                    requireActivity().getSupportFragmentManager().popBackStackImmediate("DiaryFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                    dispose();
                                }
                            });
                } else {
                    Log.d("popBack", "CALLING popBackStackImmediate");
                    requireActivity().getSupportFragmentManager().popBackStackImmediate("DiaryFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the submit event here
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle the text change event here
                return false;
            }
        });
    }

    public void setupViewPager() {
        // set the adapter for the ViewPager
        pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle());

        viewPager.setAdapter(pagerAdapter);

        // link the TabLayout and ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(titles[position]);
                    }
                }).attach();
    }

    public class PagerAdapter extends FragmentStateAdapter {

        public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new AllFoodFragment().newInstance(planProgressID, mealTimeID);
                case 1:
                    return new MyFoodMealsFragment();
                case 2:
                    return new MyFoodRecipesFragment();
                default:
                    return null;
            }
        }
    }
}