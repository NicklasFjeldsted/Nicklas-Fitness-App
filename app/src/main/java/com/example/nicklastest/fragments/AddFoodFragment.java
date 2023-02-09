package com.example.nicklastest.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nicklastest.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFoodFragment extends Fragment {

    private static final String ARG_PARAM1 = "ARG_MEAL_TIME";

    private String mealTime;
    private SearchView searchView;
    private Button goBackBtn;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private TextView mealTimeText;
    private PagerAdapter pagerAdapter;
    private String[] titles = new String[] { "All", "My Meals", "My Recipes" };
    private ListView listView;

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
        View addFoodTopLayout = view.findViewById(R.id.layout_add_food_top);
        mealTimeText = view.findViewById(R.id.text_meal_title);
        goBackBtn = addFoodTopLayout.findViewById(R.id.btn_go_back);
        searchView = addFoodTopLayout.findViewById(R.id.search_view);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);

        setupViewPager();

        // Sets title text
        mealTimeText.setText(mealTime);

        // Goes back to DiaryFragment
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryFragment diaryFragment = new DiaryFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, diaryFragment).commit();
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
        return view;
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
                    return new AllFoodFragment();
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