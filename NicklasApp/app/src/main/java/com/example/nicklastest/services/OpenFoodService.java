package com.example.nicklastest.services;

import com.example.nicklastest.models.OpenFoodData.DirectOpenProductResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OpenFoodService {
    @GET("{barCode}")
    Observable<DirectOpenProductResponse> GetByBarcode(@Path("barCode") String barCode);
}
