package com.example.nicklastest.services;

import com.example.nicklastest.models.OpenFoodData.DirectOpenProductResponse;
import com.example.nicklastest.models.Product.ProductRequest;
import com.example.nicklastest.models.Product.StaticProductResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ProductService {
    @GET()
    Observable<List<StaticProductResponse>> GetAll();

    @GET("{barCode}")
    Observable<StaticProductResponse> GetById(@Path("barCode") String barCode);

    @GET
    Observable<DirectOpenProductResponse> GetOpenFood(@Url String url);

    @POST()
    Observable<StaticProductResponse> Create(@Body ProductRequest request);

    @PUT("{barCode}")
    Observable<StaticProductResponse> Update(@Path("barCode") String barCode, @Body ProductRequest request);

    @DELETE("{barCode}")
    Observable<StaticProductResponse> Delete(@Path("barCode") String barCode);


}