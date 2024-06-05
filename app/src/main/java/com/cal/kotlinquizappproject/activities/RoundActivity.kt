package com.cal.kotlinquizappproject.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cal.kotlinquizappproject.R
import com.cal.kotlinquizappproject.databinding.ActivityRoundBinding
import com.cal.kotlinquizappproject.domain.PreferencesHelper

class RoundActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoundBinding
    private var currentRound: Int = 1
    private var totalScore: Int = 0
    private var totalCoins: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRoundBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        currentRound = intent.getIntExtra("round", 1)
        totalScore = intent.getIntExtra("score", 0)

        // Calculate coins as 10 times the score
        val coins = totalScore * 10

        if(currentRound == 5) {
            totalCoins += coins
            // save in SharedPreferences
            val previous_coins = PreferencesHelper.getCoins(this)
            val current_coins = previous_coins + totalCoins
            PreferencesHelper.saveCoins(this, current_coins)
        }
        else{
            totalCoins += coins
        }

        binding.txtRound.text = "Round $currentRound Completed✨"
        binding.txtScore.text = totalScore.toString()
        binding.txtCoins.text = totalCoins.toString()

        if(currentRound == 5){

            binding.btnNext.text = "Go To Home"
            binding.btnNext.setOnClickListener {
                startActivity(Intent(this@RoundActivity, MainActivity::class.java))
                finishAffinity()
            }

        }else{
            binding.btnNext.text = "Next Round"
            binding.btnNext.setOnClickListener {

                val intent = Intent(this@RoundActivity, QuestionsActivity::class.java)
                intent.putExtra("key", "rounds")
                intent.putExtra("round", currentRound + 1)
                intent.putExtra("score", totalScore)
                startActivity(intent)
                finish()
            }
        }


    }
}