package com.cal.kotlinquizappproject.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cal.kotlinquizappproject.R
import com.cal.kotlinquizappproject.databinding.ActivityStartBinding
import com.cal.kotlinquizappproject.domain.PreferencesHelper

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding
    private val KEY = "gender"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupInsets()

        checkUser()
    }

    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun checkUser() {
        val userName = PreferencesHelper.getName(this)
        if (userName.isNotEmpty()) {
            navigateToMain()
        } else {
            setupRegisterUser()
        }
    }

    private fun setupRegisterUser() {
        binding.btnNext.setOnClickListener {
            val name = binding.inputName.text.toString()
            if (name.isNotEmpty()) {
                if (isGenderSelected()) {
                    PreferencesHelper.saveName(this, name)
                    saveGender()
                    navigateToMain()
                } else {
                    showToast("Please select your gender")
                }
            } else {
                showToast("Please enter your name")
            }
        }
    }

    private fun isGenderSelected(): Boolean {
        return binding.rbMale.isChecked || binding.rbFemale.isChecked
    }

    private fun saveGender() {
        val gender = when {
            binding.rbMale.isChecked -> "male"
            binding.rbFemale.isChecked -> "female"
            else -> {
                // This case is already handled in setupRegisterUser, so this line should never be reached
                showToast("Please select your gender")
                return
            }
        }
        PreferencesHelper.saveGender(this, gender)
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}