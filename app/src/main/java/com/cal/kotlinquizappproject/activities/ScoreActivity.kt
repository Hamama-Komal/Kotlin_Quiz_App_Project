package com.cal.kotlinquizappproject.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cal.kotlinquizappproject.R
import com.cal.kotlinquizappproject.databinding.ActivityScoreBinding
import com.cal.kotlinquizappproject.domain.PreferencesHelper
import com.cal.kotlinquizappproject.domain.UserModel
import com.cal.kotlinquizappproject.domain.UserRepository

class ScoreActivity : AppCompatActivity() {

    var score : Int = 0
    private lateinit var binding: ActivityScoreBinding
    private var coins : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        score = intent.getIntExtra("score", 0)
        binding.txtScore.text = score.toString()
        coins = score * 10

        val previous_coins = PreferencesHelper.getCoins(this)
        val current_coins = previous_coins + coins
        PreferencesHelper.saveCoins(this, current_coins)

        binding.txtCoins.text = coins.toString()

        binding.btnHome.setOnClickListener {
            showSaveDailog()

        }


    }

    private fun showSaveDailog() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Save Coins")
        builder.setIcon(R.drawable.garnet)
        builder.setMessage("Do you want save the coins?")

        builder.setPositiveButton("Yes") { dialog, _ ->
            dialog.dismiss()
            showSaveDataDialog()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
            navigateToHome()
        }

        builder.setCancelable(false) // Prevent dialog from being dismissed by clicking outside

        val dialog: androidx.appcompat.app.AlertDialog = builder.create()
        dialog.show()
    }

    private fun showSaveDataDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_layout, null)
        val editTextUserName = dialogLayout.findViewById<EditText>(R.id.input_name)
        val radioGroupGender = dialogLayout.findViewById<RadioGroup>(R.id.radio_btns)
        val buttonNext = dialogLayout.findViewById<Button>(R.id.btn_next)

        val alertDialog = builder.setView(dialogLayout).create()

        buttonNext.setOnClickListener {
            val userName = editTextUserName.text.toString()
            val selectedGenderId = radioGroupGender.checkedRadioButtonId
            val gender = dialogLayout.findViewById<RadioButton>(selectedGenderId)?.text.toString()

            if (userName.isNotEmpty() && selectedGenderId != -1) {
                saveDataToDatabase(userName, gender, coins)
                navigateToHome()
                alertDialog.dismiss()
            } else {
                if (userName.isEmpty()) {
                    editTextUserName.error = "Name cannot be empty"
                }
                if (selectedGenderId == -1) {
                    Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show()
                }
            }
        }

        alertDialog.show()
    }

    private fun saveDataToDatabase(userName: String, gender: String, coins: Int) {


        val userRepository = UserRepository(this)
        val userPicture = if (gender == "Male") "person2" else "person5"
        userRepository.insertOrUpdateUser(UserModel(0, userName, userPicture, coins))
        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()

    }

    private fun navigateToHome() {
        finish()
    }
}