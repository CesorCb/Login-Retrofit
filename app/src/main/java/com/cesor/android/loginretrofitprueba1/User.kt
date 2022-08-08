package com.cesor.android.loginretrofitprueba1

/****
 * Project: Login API REST
 * From: com.cesor.android.loginapirest
 * Created by: César Castro on 18/07/2022 at 23:25.
 ***/
data class User(
    val id: Long,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String) {

    fun getFullName(): String = "$first_name $last_name"
}

