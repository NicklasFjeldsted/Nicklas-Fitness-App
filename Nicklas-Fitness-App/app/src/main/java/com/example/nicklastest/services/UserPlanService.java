package com.example.nicklastest.services;

import com.example.nicklastest.models.User.StaticUserResponse;
import com.example.nicklastest.models.User.UserRequest;
import com.example.nicklastest.models.UserPlan.DirectUserPlanResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserPlanService {
    @GET()
    Observable<List<StaticUserResponse>> GetAll();

    @GET("{id}")
    Observable<DirectUserPlanResponse> GetById(@Path("id") int id);

    @POST()
    Observable<DirectUserPlanResponse> Create(@Body UserRequest request);

    @PUT("{id}")
    Observable<DirectUserPlanResponse> Update(@Path("id") int id, @Body UserRequest request);
}
