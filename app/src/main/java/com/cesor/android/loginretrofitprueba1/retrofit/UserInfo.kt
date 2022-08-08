package com.cesor.android.loginretrofitprueba1.retrofit

import com.cesor.android.loginretrofitprueba1.Constants
import com.google.gson.annotations.SerializedName

/****
 * Project: Login Retrofit Prueba1
 * From: com.cesor.android.loginretrofitprueba1.retrofit
 * Created by: César Castro on 7/08/2022 at 13:18.
 ***/
class UserInfo(
    @SerializedName(Constants.EMAIL_PARAM) val email: String,
    @SerializedName(Constants.PASSWORD_PARAM) val pass: String
    )