package com.catnip.firebaseauthexample.presentation.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.catnip.firebaseauthexample.R
import com.catnip.firebaseauthexample.databinding.ActivityMainBinding
import com.catnip.firebaseauthexample.presentation.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                changePhotoProfile(uri)
            }
        }

    private fun changePhotoProfile(uri: Uri) {
        //todo : update profile picture
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupForm()
        showUserData()
        setClickListeners()
        observeData()
    }

    private fun setClickListeners() {
        binding.btnChangeProfile.setOnClickListener {
            if (checkNameValidation()) {
                changeProfileData()
            }
        }
        binding.ivEditPhoto.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.tvChangePwd.setOnClickListener {
            requestChangePassword()
        }
        binding.tvLogout.setOnClickListener {
            doLogout()
        }
    }

    private fun requestChangePassword() {
        //todo : do change password
        val dialog = AlertDialog.Builder(this)
            .setMessage("Change password request sent to your email Please check to your inbox or spam")
            .setPositiveButton(
                "Okay"
            ) { dialog, id ->

            }.create()
        dialog.show()
    }

    private fun doLogout() {
        val dialog = AlertDialog.Builder(this).setMessage("Do you want to logout ?")
            .setPositiveButton(
                "Yes"
            ) { dialog, id ->
                //todo : do logout
                navigateToLogin()
            }
            .setNegativeButton(
                "No"
            ) { dialog, id ->
                //no-op , do nothing
            }.create()
        dialog.show()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    private fun changeProfileData() {
        val fullName = binding.layoutForm.etName.text.toString().trim()
        //todo : update full name
    }

    private fun checkNameValidation(): Boolean {
        val fullName = binding.layoutForm.etName.text.toString().trim()
        return if (fullName.isEmpty()) {
            binding.layoutForm.tilName.isErrorEnabled = true
            binding.layoutForm.tilName.error = getString(R.string.text_error_name_cannot_empty)
            false
        } else {
            binding.layoutForm.tilName.isErrorEnabled = false
            true
        }
    }

    private fun observeData() {
        //todo : observe data
    }

    private fun setupForm() {
        binding.layoutForm.tilName.isVisible = true
        binding.layoutForm.tilEmail.isVisible = true
        binding.layoutForm.etEmail.isEnabled = false
    }

    private fun showUserData() {
        //todo : show current user data
    }

}