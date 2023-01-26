package com.example.nicklastest.services;

import com.example.nicklastest.models.User.DirectUserResponse;
import com.example.nicklastest.models.User.StaticUserResponse;
import com.example.nicklastest.models.User.UserRequest;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET()
    Observable<List<StaticUserResponse>> GetAll();

    @GET("{id}")
    Observable<DirectUserResponse> GetById(@Path("id") int id);

    @POST()
    Observable<DirectUserResponse> Create(@Body UserRequest request);

    @PUT("{id}")
    Observable<DirectUserResponse> Update(@Path("id") int id, @Body UserRequest request);
}
