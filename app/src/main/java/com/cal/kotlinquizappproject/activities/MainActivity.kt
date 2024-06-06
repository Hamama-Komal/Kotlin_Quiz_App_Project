package com.cal.kotlinquizappproject.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cal.kotlinquizappproject.R
import com.cal.kotlinquizappproject.databinding.ActivityMainBinding
import com.cal.kotlinquizappproject.domain.PreferencesHelper
import com.cal.kotlinquizappproject.domain.QuestionModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView( binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fetch coins from SharedPreferences
        val coins = PreferencesHelper.getCoins(this)
        binding.txtMyCoins.text = coins.toString()

        setUserData()

        binding.apply {

            // Quiz for Single Player
            singlePlayerBtn.setOnClickListener {

                val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
                intent.putExtra("key", "single")
                startActivity(intent)
            }

            // Quiz For different Categories
            catScience.setOnClickListener {

                val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
                intent.putExtra("key", "science")
                startActivity(intent)

            }

            catMath.setOnClickListener {

                val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
                intent.putExtra("key", "math")
                startActivity(intent)

            }

            catHistory.setOnClickListener {

                val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
                intent.putExtra("key", "history")
                startActivity(intent)

            }


            catEnglish.setOnClickListener {

                val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
                intent.putExtra("key", "english")
                startActivity(intent)

            }


            // To Set Up Rounds
            btnRounds.setOnClickListener {

                showInfoDialog()

            }



            // Bottom Navigation Bar
            bottomNavigation.setItemSelected(R.id.home)
            bottomNavigation.setOnItemSelectedListener {
                if(it == R.id.board){
                    startActivity(Intent(this@MainActivity, LeaderActivity::class.java))
                    finish()
                }

             /*   if(it == R.id.home){
                    startActivity(Intent(this@MainActivity, MainActivity::class.java))
                    finish()
                }*/
            }
        }
    }

    private fun setUserData() {

      val  userName = PreferencesHelper.getName(this)
        binding.txtUserName.text = userName

        val userGender = PreferencesHelper.getGender(this)
        if(userGender == "male"){
            binding.profileImage.setImageResource(R.drawable.person2)
        }
        else{
            binding.profileImage.setImageResource(R.drawable.person1)
        }
    }

    private fun showInfoDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Collect More Coins!")
        builder.setIcon(R.drawable.garnet)
        builder.setMessage("If you complete all the five rounds you will get the 1000+ points. Rounds start now.")

        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            // You can start the rounds here if needed
            val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
            intent.putExtra("key", "rounds")
            startActivity(intent)
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.setCancelable(false) // Prevent dialog from being dismissed by clicking outside

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }




}