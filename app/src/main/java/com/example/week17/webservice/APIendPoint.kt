package com.example.week17.webservice

import com.example.week17.model.User
import com.example.week17.response.SingleResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIendPoint {

    @FormUrlEncoded
    @POST("auth/sign-up")
    fun register(
            @Field("name")name: String,
            @Field("username")username: String,
            @Field("email")email: String,
            @Field("password")password: String,
    ): Call<SingleResponse<User>>

    @FormUrlEncoded
    @POST("auth/sign-in")
    fun login(
            @Field("username")username: String,
            @Field("password")password: String,
    ): Call<SingleResponse<User>>
}