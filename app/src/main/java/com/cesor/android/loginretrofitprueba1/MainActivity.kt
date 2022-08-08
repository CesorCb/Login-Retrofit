package com.cesor.android.loginretrofitprueba1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.cesor.android.loginretrofitprueba1.databinding.ActivityMainBinding
import com.cesor.android.loginretrofitprueba1.retrofit.LoginService
import com.cesor.android.loginretrofitprueba1.retrofit.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.swType.setOnCheckedChangeListener { button, isChecked ->
            button.text = if (isChecked) getString(R.string.main_type_login)
            else getString(R.string.main_type_register)

            mBinding.btnLogin.text = button.text
        }
        mBinding.btnLogin.setOnClickListener {
            loginOrRegister()
        }
        mBinding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun loginOrRegister(){
        val email = mBinding.etEmail.text.toString().trim()
        val password = mBinding.etPassword.text.toString().trim()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LoginService::class.java)
        if (mBinding.swType.isChecked) login(email, password, service)
        else register(email, password, service)
    }

    private fun login(email: String, password: String, service: LoginService) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = service.loginUser(UserInfo(email, password))
                updateUI("${Constants.TOKEN_PROPERTY}: ${result.token}")
            } catch (e: Exception) {
                (e as? HttpException)?.let { checkError(e) }
            }
        }
    }

    private fun register(email: String, password: String, service: LoginService){
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = service.registerUser(UserInfo(email, password))
                updateUI("${Constants.ID_PROPERTY}: ${result.id}, " +
                        "${Constants.TOKEN_PROPERTY}: ${result.token}")
            } catch (e: Exception) {
                (e as? HttpException)?.let { checkError(e) }
            }
        }
    }

    private suspend fun checkError(e: HttpException) =
        when(e.code()){
            400 -> {
                updateUI(getString(R.string.main_error_server))
            }
            else -> {
                updateUI(getString(R.string.main_error_response))
            }
        }

    private suspend fun updateUI(result: String) = withContext(Dispatchers.Main) {
        mBinding.apply {
            tvResult.visibility = View.VISIBLE
            tvResult.text = result
        }
    }
}