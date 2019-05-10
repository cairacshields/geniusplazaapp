package com.example.geniusplazaapp

import com.example.geniusplazaapp.objects.Data
import com.example.geniusplazaapp.objects.UserResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface APIService {

    @GET("users/")
    fun getUsers(): Observable<Data>

    @POST("users/")
    @FormUrlEncoded
    fun createUser(@Field("first_name") first_name: String,
                   @Field("last_name") last_name: String,
                   @Field("email") job: String): Observable<UserResponse>
}