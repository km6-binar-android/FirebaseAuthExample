package com.catnip.firebaseauthexample.presentation.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.catnip.firebaseauthexample.data.datasource.AuthDataSource
import com.catnip.firebaseauthexample.data.datasource.FirebaseAuthDataSource
import com.catnip.firebaseauthexample.data.repository.UserRepository
import com.catnip.firebaseauthexample.data.repository.UserRepositoryImpl
import com.catnip.firebaseauthexample.data.source.firebase.FirebaseService
import com.catnip.firebaseauthexample.data.source.firebase.FirebaseServiceImpl
import com.catnip.firebaseauthexample.databinding.ActivitySplashBinding
import com.catnip.firebaseauthexample.presentation.login.LoginActivity
import com.catnip.firebaseauthexample.presentation.main.MainActivity
import com.catnip.firebaseauthexample.utils.GenericViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    private val viewModel: SplashViewModel by viewModels {
        val service: FirebaseService = FirebaseServiceImpl()
        val dataSource: AuthDataSource = FirebaseAuthDataSource(service)
        val repo: UserRepository = UserRepositoryImpl(dataSource)
        GenericViewModelFactory.create(SplashViewModel(repo))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkIfUserLogin()
    }

    private fun checkIfUserLogin() {
        lifecycleScope.launch {
            delay(2000)
            if (viewModel.isLoggedIn()) {
                navigateToMain()
            } else {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })

    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}