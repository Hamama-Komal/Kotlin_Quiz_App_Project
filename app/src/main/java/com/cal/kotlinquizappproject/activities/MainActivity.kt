package com.cal.kotlinquizappproject.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cal.kotlinquizappproject.R
import com.cal.kotlinquizappproject.databinding.ActivityMainBinding
import com.cal.kotlinquizappproject.domain.MusicService
import com.cal.kotlinquizappproject.domain.PreferencesHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        backgroundMusic()

        binding.apply {
            // Quiz for Single Player
            singlePlayerBtn.setOnClickListener {
                showInfoDialog("single")
            }

            // Quiz For different Categories
            catScience.setOnClickListener {
                showInfoDialog("science")
            }

            catMath.setOnClickListener {
                showInfoDialog("math")
            }

            catHistory.setOnClickListener {
                showInfoDialog("history")
            }

            catEnglish.setOnClickListener {
                showInfoDialog("english")
            }

            // To Set Up Rounds
            btnLevels.setOnClickListener {
                // showInfoDialog("rounds")
                roundShowInfoDialog()
            }

            // Bottom Navigation Bar
            bottomNavigation.setItemSelected(R.id.home)
            bottomNavigation.setOnItemSelectedListener {
                if (it == R.id.board) {
                    startActivity(Intent(this@MainActivity, LeaderActivity::class.java))
                    bottomNavigation.setItemSelected(R.id.board)
                }
            }
        }
    }

    private fun backgroundMusic() {
        // Handle music toggle button
        binding.toggleButtonMusic.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                startMusic()
            } else {
                stopMusic()
            }
        }
    }

    private fun startMusic() {
        startService(Intent(this, MusicService::class.java))
    }

    private fun stopMusic() {
        stopService(Intent(this, MusicService::class.java))
    }

    private fun showInfoDialog(key: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Earn 500 Coins!")
        builder.setIcon(R.drawable.garnet)
        builder.setMessage("If you complete the round within 60 seconds, you will earn 500 coins.\n Are you ready?")

        builder.setPositiveButton("Yes") { dialog, _ ->
            dialog.dismiss()
            // Start QuestionsActivity with the appropriate key
            val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
            intent.putExtra("key", key)
            startActivity(intent)
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        builder.setCancelable(false) // Prevent dialog from being dismissed by clicking outside

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun roundShowInfoDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Round Mode")
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

    override fun onDestroy() {
        super.onDestroy()
        stopMusic()
        binding.toggleButtonMusic.isChecked = false
    }

    /*override fun onStart() {
        super.onStart()
        // Fetch coins from SharedPreferences
        val coins = PreferencesHelper.getCoins(this)
        binding.txtMyCoins.text = coins.toString()

    }*/
}
