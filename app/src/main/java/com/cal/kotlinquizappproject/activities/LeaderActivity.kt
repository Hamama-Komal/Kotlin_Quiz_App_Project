package com.cal.kotlinquizappproject.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cal.kotlinquizappproject.R
import com.cal.kotlinquizappproject.R.drawable
import com.cal.kotlinquizappproject.adapter.LeaderAdapter
import com.cal.kotlinquizappproject.databinding.ActivityLeaderBinding
import com.cal.kotlinquizappproject.domain.PreferencesHelper
import com.cal.kotlinquizappproject.domain.UserModel

class LeaderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeaderBinding
    private val leaderAdapter by lazy {
        LeaderAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {

            // Setting the data on first, second and third place

            txtTitle1.text = loadData().get(0).name
            txtScore1.text = loadData().get(0).score.toString()
           // profileImage1.setBackgroundResource(drawable.person1)
            val drawableResourceId1: Int = root.resources.getIdentifier(
                loadData().get(0).picture,
                "drawable",
                root.context.packageName
            )
            Glide.with(root.context)
                .load(drawableResourceId1)
                .into(profileImage1)

            txtTitle2.text = loadData().get(1).name
            txtScore2.text = loadData().get(1).score.toString()
            val drawableResourceId2: Int = root.resources.getIdentifier(
                loadData().get(1).picture,
                "drawable",
                root.context.packageName
            )
            Glide.with(root.context)
                .load(drawableResourceId2)
                .into(profileImage2)

            txtTitle3.text = loadData().get(2).name
            txtScore3.text = loadData().get(2).score.toString()
            val drawableResourceId3: Int = root.resources.getIdentifier(
                loadData().get(2).picture,
                "drawable",
                root.context.packageName
            )
            Glide.with(root.context)
                .load(drawableResourceId3)
                .into(profileImage3)

            // Bottom Navigation selected listener
            bottomNavigation.setItemSelected(R.id.board)
            bottomNavigation.setOnItemSelectedListener {
                if(it == R.id.home){
                    startActivity(Intent(this@LeaderActivity, MainActivity::class.java))
                    finish()
                }
               /* if (it == R.id.board){
                    startActivity(Intent(this@LeaderActivity, LeaderActivity::class.java))
                    finish()
                }*/
            }

            val list : MutableList<UserModel> = loadData()
            list.removeAt(0)
            list.removeAt(1)
            list.removeAt(2)
            leaderAdapter.differ.submitList(list)
            leaderView.apply {
                layoutManager = LinearLayoutManager(this@LeaderActivity)
                adapter = leaderAdapter
            }

        }
        loadData()
    }

    private fun loadData(): MutableList<UserModel> {

        val users : MutableList<UserModel> = mutableListOf()

        users.add(UserModel(1,"Mikasa","person1", 53000,))
        users.add(UserModel(2,"Misa","person5", 50000,))
        users.add(UserModel(3,"Kacee","person9", 49000,))
        users.add(UserModel(4,"Sasha","person7", 48000,))
        users.add(UserModel(5,"Nisa","person1", 47000,))
        users.add(UserModel(6,"Levi","person4", 42000,))
        users.add(UserModel(7,"Ali","person3", 41000,))
        users.add(UserModel(8,"Mark","person4", 38000,))
        users.add(UserModel(9,"Ash","person3", 36000,))

        // To show the data of current user
        val  userName = PreferencesHelper.getName(this)
        val userScore = PreferencesHelper.getCoins(this)
        val userGender = PreferencesHelper.getGender(this)
        if(userGender == "male") {
            users.add(UserModel(10, userName, "person2", userScore,))
        }
        else{
            users.add(UserModel(10, userName, "person1", userScore,))
        }

        return users

    }
}