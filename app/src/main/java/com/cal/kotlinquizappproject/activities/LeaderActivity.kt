package com.cal.kotlinquizappproject.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cal.kotlinquizappproject.R
import com.cal.kotlinquizappproject.adapter.LeaderAdapter
import com.cal.kotlinquizappproject.databinding.ActivityLeaderBinding
import com.cal.kotlinquizappproject.domain.PreferencesHelper
import com.cal.kotlinquizappproject.domain.UserModel
import com.cal.kotlinquizappproject.domain.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LeaderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeaderBinding
    private lateinit var userRepository: UserRepository
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

        userRepository = UserRepository(this)
        setupInitialData()

        binding.apply {
            leaderView.apply {
                layoutManager = LinearLayoutManager(this@LeaderActivity)
                adapter = leaderAdapter
            }

            // Bottom Navigation selected listener
            bottomNavigation.setItemSelected(R.id.board)
            bottomNavigation.setOnItemSelectedListener {
                if (it == R.id.home) {
                    finish()
                    bottomNavigation.setItemSelected(R.id.home)
                }
            }
        }

        loadData()
    }

    private fun loadImage(pictureName: String, imageView: ImageView) {
        val drawableResourceId = resources.getIdentifier(pictureName, "drawable", packageName)
        Glide.with(this)
            .load(drawableResourceId)
            .into(imageView)
    }

    private fun setupInitialData() {
        // Add initial data only if database is empty
        if (userRepository.getAllUsers().isEmpty()) {
            val users = listOf(
                UserModel(1, "Mikasa", "person1", 5300),
                UserModel(2, "Misa", "person9", 5000),
                UserModel(3, "Kacee", "person9", 4900),
                UserModel(4, "Sasha", "person7", 4800),
                UserModel(5, "Nisa", "person1", 4700),
                UserModel(6, "Levi", "person4", 4200),
                UserModel(7, "Ali", "person3", 4100),
                UserModel(8, "Mark", "person4", 3800),
                UserModel(9, "Ash", "person3", 3600)
            )

            // Insert initial users into the database
            users.forEach { userRepository.insertUser(it) }

            // Add current user data
            val userName = PreferencesHelper.getName(this)
            val userScore = PreferencesHelper.getCoins(this)
            val userGender = PreferencesHelper.getGender(this)
            val userPicture = if (userGender == "male") "person2" else "person5"
            userRepository.insertUser(UserModel(10, userName, userPicture, userScore))
        }
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.Main).launch {
            val users = withContext(Dispatchers.IO) { userRepository.getAllUsers() }
            val sortedUsers = users.sortedByDescending { it.score }
            val currentUserName = PreferencesHelper.getName(this@LeaderActivity)
            val currentUserScore = PreferencesHelper.getCoins(this@LeaderActivity)

            // Update current user's score in the database
            val currentUser = sortedUsers.find { it.name == currentUserName }
            currentUser?.let {
                it.score = currentUserScore
                withContext(Dispatchers.IO) { userRepository.updateUser(it) }
            }

            // Refresh the list
            val updatedUsers = withContext(Dispatchers.IO) { userRepository.getAllUsers() }
            val updatedSortedUsers = updatedUsers.sortedByDescending { it.score }

            // Setting the data on first, second and third place
            if (updatedSortedUsers.isNotEmpty()) {
                val topThreeUsers = updatedSortedUsers.take(3)

                binding.txtTitle1.text = topThreeUsers[0].name
                binding.txtScore1.text = topThreeUsers[0].score.toString()
                loadImage(topThreeUsers[0].picture, binding.profileImage1)

                if (topThreeUsers.size > 1) {
                    binding.txtTitle2.text = topThreeUsers[1].name
                    binding.txtScore2.text = topThreeUsers[1].score.toString()
                    loadImage(topThreeUsers[1].picture, binding.profileImage2)
                }

                if (topThreeUsers.size > 2) {
                    binding.txtTitle3.text = topThreeUsers[2].name
                    binding.txtScore3.text = topThreeUsers[2].score.toString()
                    loadImage(topThreeUsers[2].picture, binding.profileImage3)
                }

                val remainingUsers = updatedSortedUsers.drop(3)
                leaderAdapter.differ.submitList(remainingUsers)

                // Find the current user's position
                val currentUserPosition = updatedSortedUsers.indexOfFirst { it.name == currentUserName } + 1
                // binding.txtUserPosition.text = "Your Position: $currentUserPosition"
               Toast.makeText(this@LeaderActivity, "Your Position: $currentUserPosition", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
