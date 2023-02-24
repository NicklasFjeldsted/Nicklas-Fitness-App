package com.example.nicklastest.services;

import com.example.nicklastest.models.PlanProgress.DirectPlanProgressResponse;
import com.example.nicklastest.models.PlanProgress.PlanProgressRequest;
import com.example.nicklastest.models.PlanProgress.StaticPlanProgressResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface PlanProgressService {
    @GET()
    Observable<List<StaticPlanProgressResponse>> GetAll();

    @GET("{id}")
    Observable<DirectPlanProgressResponse> GetById(@Path("id") int id);

    @POST
    Observable<DirectPlanProgressResponse> Create(@Url String url, @Body PlanProgressRequest request);

    @PUT("{id}")
    Observable<DirectPlanProgressResponse> Update(@Path("id") int id, @Body PlanProgressRequest request);

    @DELETE("{id}")
    Observable<DirectPlanProgressResponse> Delete(@Path("id") int id);
}
