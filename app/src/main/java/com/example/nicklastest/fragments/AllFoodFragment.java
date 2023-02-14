package com.example.nicklastest.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicklastest.R;
import com.example.nicklastest.UserPlanSharedViewModel;
import com.example.nicklastest.models.PlanProgress.DirectPlanProgressResponse;
import com.example.nicklastest.models.ProgressMeal.StaticProgressMealResponse;
import com.example.nicklastest.models.SizedProduct.DirectSizedProductResponse;
import com.example.nicklastest.models.UserPlan.DirectUserPlanResponse;

import java.util.ArrayList;
import java.util.List;

public class AllFoodFragment extends Fragment {

    private UserPlanSharedViewModel viewModel;
    private RecyclerView listViewRecentFood;
    private AllFoodRecyclerAdapter adapter;
    private DirectUserPlanResponse userPlan;
    List<DirectSizedProductResponse> sizedProducts = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(UserPlanSharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_food, container, false);
        viewModel.getSelected().observe(getViewLifecycleOwner(), item -> {
            // Update the UI with the selected item'
            userPlan = item;
            getRecentFoods(userPlan.getPlanProgress());
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listViewRecentFood = view.findViewById(R.id.list_view_recent_food);


    }

    public void getRecentFoods(List<DirectPlanProgressResponse> planProgresses) {
        for(DirectPlanProgressResponse planProgress : planProgresses) {
            for(StaticProgressMealResponse meal : planProgress.getProgressMeals()) {
                for(DirectSizedProductResponse product : meal.getSizedProducts()) {
                    product.getProduct().getProductName();
                    if(sizedProducts.isEmpty() || !sizedProducts.stream().anyMatch(o -> product.getProduct().getProductName().equals(o.getProduct().getProductName()))) {
                        sizedProducts.add(product);
                    }
                }

            }
        }
        setAdapter();
    }

    public void setAdapter() {
        if(sizedProducts.size() != 0) {
            adapter = new AllFoodRecyclerAdapter(sizedProducts);
            listViewRecentFood.setLayoutManager(new LinearLayoutManager(getContext()));
            listViewRecentFood.setAdapter(adapter);
            return;
        }
    }

    public class AllFoodRecyclerAdapter extends RecyclerView.Adapter<AllFoodRecyclerAdapter.ViewHolder>  {
        private List<DirectSizedProductResponse> products;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView title, description;

            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.text_item_title);
                description = itemView.findViewById(R.id.text_item_description);
            }
        }

        public AllFoodRecyclerAdapter(List<DirectSizedProductResponse> products) {
            this.products = products;
        }

        @Override
        public AllFoodRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_food_recent_food_item, parent, false);
            return new AllFoodRecyclerAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AllFoodRecyclerAdapter.ViewHolder holder, int position) {
            DirectSizedProductResponse item = products.get(position);
            holder.title.setText(item.getProduct().getProductName());
            int servingSize = (int)Math.round(item.getServingSize()) * 100;
            holder.description.setText(String.format("%s., %s, %s", viewModel.getCaloriesOfItem(item),
                    item.getProduct().getProductManufacturer(),
                    String.valueOf(servingSize).replaceAll("(?<=^\\d{2})(?=\\d)", ",0")));
        }

        @Override
        public int getItemCount() {
            return products.size();
        }
    }
}