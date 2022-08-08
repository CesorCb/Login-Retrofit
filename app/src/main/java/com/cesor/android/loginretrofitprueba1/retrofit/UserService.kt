package com.cesor.android.loginretrofitprueba1.retrofit

import com.cesor.android.loginretrofitprueba1.Constants
import retrofit2.http.GET

/****
 * Project: Login Retrofit Prueba1
 * From: com.cesor.android.loginretrofitprueba1.retrofit
 * Created by: César Castro on 7/08/2022 at 20:38.
 ***/
interface UserService {
    @GET(Constants.API_PATH + Constants.USERS_PATH + Constants.TWO_PATH)
    suspend fun getSingleUser() : SingleUserResponse
}