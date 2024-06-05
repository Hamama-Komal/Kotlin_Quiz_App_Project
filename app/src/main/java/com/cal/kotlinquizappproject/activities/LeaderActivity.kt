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

        users.add(UserModel(1,"Shumail","person1", 5300,))
        users.add(UserModel(2,"Hamama","person5", 5000,))
        users.add(UserModel(3,"Nayab","person9", 4900,))
        users.add(UserModel(4,"Surraya","person7", 4800,))
        users.add(UserModel(5,"Nimra","person1", 4700,))
        users.add(UserModel(6,"Hamza","person4", 4200,))
        users.add(UserModel(7,"Ali","person3", 4100,))
        users.add(UserModel(8,"Mark","person2", 3800,))
        users.add(UserModel(9,"Ash","person2", 3600,))
        users.add(UserModel(10,"Levi","person4", 3200,))
        users.add(UserModel(11,"Mikasa","person8", 3000,))
        users.add(UserModel(12,"Armin","person2", 2900,))
        users.add(UserModel(13,"Zain","person4", 2700,))

        return users

    }
}