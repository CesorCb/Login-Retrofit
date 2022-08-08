package com.cesor.android.loginretrofitprueba1.retrofit

/****
 * Project: Login Retrofit Prueba1
 * From: com.cesor.android.loginretrofitprueba1.retrofit
 * Created by: César Castro on 7/08/2022 at 12:47.
 ***/
data class RegisterResponse(var token: String, var id: String) : SuccessResponse(token)
