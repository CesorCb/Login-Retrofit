package com.cesor.android.loginretrofitprueba1.retrofit

import com.cesor.android.loginretrofitprueba1.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/****
 * Project: Login Retrofit Prueba1
 * From: com.cesor.android.loginretrofitprueba1.retrofit
 * Created by: César Castro on 7/08/2022 at 13:17.
 ***/
interface LoginService {
    @Headers("Content-Type : application/json")
    @POST(Constants.API_PATH + Constants.LOGIN_PATH)
    fun login(@Body data: UserInfo) : Call<LoginResponse>

    @POST(Constants.API_PATH + Constants.LOGIN_PATH)
    suspend fun loginUser(@Body data: UserInfo) : LoginResponse

    @POST(Constants.API_PATH + Constants.REGISTER_PATH)
    suspend fun  registerUser(@Body data: UserInfo) : RegisterResponse
}