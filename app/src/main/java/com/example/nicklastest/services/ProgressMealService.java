package com.example.nicklastest.services;

import com.example.nicklastest.models.Product.ProductRequest;
import com.example.nicklastest.models.Product.StaticProductResponse;
import com.example.nicklastest.models.ProgressMeal.DirectProgressMealResponse;
import com.example.nicklastest.models.ProgressMeal.ProgressMealPatchRequest;
import com.example.nicklastest.models.ProgressMeal.ProgressMealRequest;
import com.example.nicklastest.models.ProgressMeal.StaticProgressMealResponse;
import com.example.nicklastest.models.SizedProduct.SizedProductRequest;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ProgressMealService {
    @GET()
    Observable<List<StaticProgressMealResponse>> GetAll();

    @GET("{id}")
    Observable<DirectProgressMealResponse> GetById(@Path("id") int id);

    @POST()
    Observable<DirectProgressMealResponse> Create(@Body ProgressMealRequest request);

    @PUT("{id}")
    Observable<DirectProgressMealResponse> Update(@Path("id") int id, @Body ProgressMealRequest request);

    @PATCH("{id}")
    Observable<DirectProgressMealResponse> PatchUpdate(@Path("id") int id, @Body ProgressMealPatchRequest request);

    @DELETE("{id}")
    Observable<DirectProgressMealResponse> Delete(@Path("id") int id);
}
