package com.example.nicklastest.services;

import com.example.nicklastest.models.OpenFoodData.DirectOpenProductResponse;
import com.example.nicklastest.models.Product.ProductRequest;
import com.example.nicklastest.models.Product.StaticProductResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
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
    Single<StaticProductResponse> GetById(@Path("barCode") String barCode);

    @GET()
    Single<DirectOpenProductResponse> GetOpenFood(@Url String url);

    @POST
    Single<StaticProductResponse> Create(@Url String url, @Body ProductRequest request);

    @PUT("{barCode}")
    Observable<StaticProductResponse> Update(@Path("barCode") String barCode, @Body ProductRequest request);

    @DELETE("{barCode}")
    Observable<StaticProductResponse> Delete(@Path("barCode") String barCode);


}