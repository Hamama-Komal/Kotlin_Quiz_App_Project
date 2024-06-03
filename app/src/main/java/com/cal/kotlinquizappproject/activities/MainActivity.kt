package com.cal.kotlinquizappproject.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cal.kotlinquizappproject.R
import com.cal.kotlinquizappproject.databinding.ActivityMainBinding
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


        binding.apply {

            singlePlayerBtn.setOnClickListener {

                val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
                intent.putParcelableArrayListExtra("list", ArrayList(questionList()))
                startActivity(intent)
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

    private fun questionList() : MutableList<QuestionModel>{
        val questions : MutableList<QuestionModel> = mutableListOf()
        questions.add(
            QuestionModel(
                id = 1,
                question = "What is the capital of France?",
                option1 = "Berlin",
                option2 = "Madrid",
                option3 = "Paris",
                option4 = "Rome",
                correctAnswer = "c",
                score = 10,
                picPath = "q_9",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 2,
                question = "Which planet is known as the Red Planet?",
                option1 = "Earth",
                option2 = "Mars",
                option3 = "Jupiter",
                option4 = "Saturn",
                correctAnswer = "b",
                score = 10,
                picPath = "q_1",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 3,
                question = "What is the largest ocean on Earth?",
                option1 = "Atlantic Ocean",
                option2 = "Indian Ocean",
                option3 = "Southern Ocean",
                option4 = "Pacific Ocean",
                correctAnswer = "d",
                score = 10,
                picPath = "q_10",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 4,
                question = "Who wrote 'To Kill a Mockingbird'?",
                option1 = "Harper Lee",
                option2 = "J.K. Rowling",
                option3 = "Ernest Hemingway",
                option4 = "Mark Twain",
                correctAnswer = "a",
                score = 10,
                picPath = "q_8",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 5,
                question = "What is the smallest prime number?",
                option1 = "1",
                option2 = "2",
                option3 = "3",
                option4 = "5",
                correctAnswer = "b",
                score = 10,
                picPath = "q_6",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 6,
                question = "Which element has the chemical symbol 'O'?",
                option1 = "Gold",
                option2 = "Oxygen",
                option3 = "Osmium",
                option4 = "Hydrogen",
                correctAnswer = "b",
                score = 10,
                picPath = "q_5",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 7,
                question = "What is the square root of 64?",
                option1 = "6",
                option2 = "7",
                option3 = "8",
                option4 = "9",
                correctAnswer = "c",
                score = 10,
                picPath = "q_3",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 8,
                question = "Which continent is the Sahara Desert located on?",
                option1 = "Asia",
                option2 = "Africa",
                option3 = "Australia",
                option4 = "North America",
                correctAnswer = "b",
                score = 10,
                picPath = "q_7",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 9,
                question = "Who painted the Mona Lisa?",
                option1 = "Vincent van Gogh",
                option2 = "Pablo Picasso",
                option3 = "Leonardo da Vinci",
                option4 = "Claude Monet",
                correctAnswer = "c",
                score = 10,
                picPath = "q_2",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 10,
                question = "What is the chemical formula for water?",
                option1 = "CO2",
                option2 = "H2O",
                option3 = "O2",
                option4 = "NaCl",
                correctAnswer = "b",
                score = 10,
                picPath = "q_5",
                clickedOption = null
            )
        )

        return questions
    }

}