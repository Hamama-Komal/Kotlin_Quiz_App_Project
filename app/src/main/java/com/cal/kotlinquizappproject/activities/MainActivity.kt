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

            // Quiz for Single Player
            singlePlayerBtn.setOnClickListener {

                val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
                intent.putParcelableArrayListExtra("list", ArrayList(questionList()))
                startActivity(intent)
            }

            // Quiz For different Categories
            catScience.setOnClickListener {

                val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
                intent.putParcelableArrayListExtra("list", ArrayList(scienceQuestionList()))
                startActivity(intent)

            }

            catMath.setOnClickListener {

                val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
                intent.putParcelableArrayListExtra("list", ArrayList(mathQuestionList()))
                startActivity(intent)

            }

            catHistory.setOnClickListener {

                val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
                intent.putParcelableArrayListExtra("list", ArrayList(historyQuestionList()))
                startActivity(intent)

            }


            catEnglish.setOnClickListener {

                val intent = Intent(this@MainActivity, QuestionsActivity::class.java)
                intent.putParcelableArrayListExtra("list", ArrayList(englishQuestionList()))
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
                picPath = "hq2",
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
                picPath = "sq10",
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
                picPath = "mq2",
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
                picPath = "sq1",
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
                picPath = "mq5",
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
                picPath = "q_2",
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
                picPath = "q_7",
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
                picPath = "sq4",
                clickedOption = null
            )
        )

        return questions
    }
    private fun scienceQuestionList() : MutableList<QuestionModel> {
        val questions : MutableList<QuestionModel> = mutableListOf()

        questions.add(
            QuestionModel(
                id = 1,
                question = "What is the chemical symbol for Gold?",
                option1 = "Au",
                option2 = "Ag",
                option3 = "Fe",
                option4 = "Pb",
                correctAnswer = "a",
                score = 10,
                picPath = "sq6",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 2,
                question = "What is the speed of light in a vacuum?",
                option1 = "300,000 km/s",
                option2 = "150,000 km/s",
                option3 = "450,000 km/s",
                option4 = "600,000 km/s",
                correctAnswer = "a",
                score = 10,
                picPath = "sq7",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 3,
                question = "What is the most abundant gas in Earth's atmosphere?",
                option1 = "Oxygen",
                option2 = "Hydrogen",
                option3 = "Nitrogen",
                option4 = "Carbon Dioxide",
                correctAnswer = "c",
                score = 10,
                picPath = "sq5",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 4,
                question = "What is the hardest natural substance on Earth?",
                option1 = "Diamond",
                option2 = "Gold",
                option3 = "Silver",
                option4 = "Platinum",
                correctAnswer = "a",
                score = 10,
                picPath = "sq9",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 5,
                question = "What planet is known as the 'Morning Star' or 'Evening Star'?",
                option1 = "Mars",
                option2 = "Venus",
                option3 = "Jupiter",
                option4 = "Saturn",
                correctAnswer = "b",
                score = 10,
                picPath = "sq10",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 6,
                question = "What is the powerhouse of the cell?",
                option1 = "Nucleus",
                option2 = "Mitochondria",
                option3 = "Ribosome",
                option4 = "Chloroplast",
                correctAnswer = "b",
                score = 10,
                picPath = "sq6",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 7,
                question = "What is the chemical formula for table salt?",
                option1 = "H2O",
                option2 = "CO2",
                option3 = "NaCl",
                option4 = "KCl",
                correctAnswer = "c",
                score = 10,
                picPath = "sq2",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 8,
                question = "What is the process by which plants make their food?",
                option1 = "Respiration",
                option2 = "Transpiration",
                option3 = "Photosynthesis",
                option4 = "Germination",
                correctAnswer = "c",
                score = 10,
                picPath = "sq1",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 9,
                question = "What is the heaviest naturally occurring element?",
                option1 = "Lead",
                option2 = "Uranium",
                option3 = "Gold",
                option4 = "Mercury",
                correctAnswer = "b",
                score = 10,
                picPath = "sq6",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 10,
                question = "What is the boiling point of water at sea level?",
                option1 = "90°C",
                option2 = "100°C",
                option3 = "110°C",
                option4 = "120°C",
                correctAnswer = "b",
                score = 10,
                picPath = "q_10",
                clickedOption = null
            )
        )

        return questions
    }
    private fun historyQuestionList() : MutableList<QuestionModel> {
        val questions : MutableList<QuestionModel> = mutableListOf()

        questions.add(
            QuestionModel(
                id = 1,
                question = "Who was the first Prime Minister of India?",
                option1 = "Mahatma Gandhi",
                option2 = "Jawaharlal Nehru",
                option3 = "Sardar Patel",
                option4 = "Lal Bahadur Shastri",
                correctAnswer = "b",
                score = 10,
                picPath = "hq1",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 2,
                question = "In which year did Pakistan gain independence?",
                option1 = "1945",
                option2 = "1946",
                option3 = "1947",
                option4 = "1948",
                correctAnswer = "c",
                score = 10,
                picPath = "hq2",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 3,
                question = "Who is known as the 'Father of the Nation' in Pakistan?",
                option1 = "Allama Iqbal",
                option2 = "Liaquat Ali Khan",
                option3 = "Muhammad Ali Jinnah",
                option4 = "Zulfikar Ali Bhutto",
                correctAnswer = "c",
                score = 10,
                picPath = "img",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 4,
                question = "Which movement was started by Mahatma Gandhi in 1942?",
                option1 = "Non-Cooperation Movement",
                option2 = "Civil Disobedience Movement",
                option3 = "Quit India Movement",
                option4 = "Swadeshi Movement",
                correctAnswer = "c",
                score = 10,
                picPath = "hq6",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 5,
                question = "Who was the first President of Pakistan?",
                option1 = "Ayub Khan",
                option2 = "Iskander Mirza",
                option3 = "Yahya Khan",
                option4 = "Zia-ul-Haq",
                correctAnswer = "b",
                score = 10,
                picPath = "hq2",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 6,
                question = "Which event led to the partition of Bengal in 1905?",
                option1 = "First World War",
                option2 = "Swadeshi Movement",
                option3 = "Jallianwala Bagh Massacre",
                option4 = "Salt March",
                correctAnswer = "b",
                score = 10,
                picPath = "img_1",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 7,
                question = "When was the Lahore Resolution passed?",
                option1 = "1930",
                option2 = "1940",
                option3 = "1945",
                option4 = "1947",
                correctAnswer = "b",
                score = 10,
                picPath = "hq3",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 8,
                question = "Who was the Viceroy of India during the Quit India Movement?",
                option1 = "Lord Mountbatten",
                option2 = "Lord Wavell",
                option3 = "Lord Linlithgow",
                option4 = "Lord Irwin",
                correctAnswer = "c",
                score = 10,
                picPath = "hq5",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 9,
                question = "Who was the first Governor-General of Pakistan?",
                option1 = "Muhammad Ali Jinnah",
                option2 = "Liaquat Ali Khan",
                option3 = "Ghulam Muhammad",
                option4 = "Khawaja Nazimuddin",
                correctAnswer = "a",
                score = 10,
                picPath = "img",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 10,
                question = "When did the Jallianwala Bagh massacre occur?",
                option1 = "1919",
                option2 = "1920",
                option3 = "1921",
                option4 = "1922",
                correctAnswer = "a",
                score = 10,
                picPath = "q_9",
                clickedOption = null
            )
        )

        return questions
    }
    private fun mathQuestionList() : MutableList<QuestionModel> {
        val questions : MutableList<QuestionModel> = mutableListOf()

        questions.add(
            QuestionModel(
                id = 1,
                question = "What is the value of π (pi) approximately?",
                option1 = "2.14",
                option2 = "3.14",
                option3 = "4.14",
                option4 = "5.14",
                correctAnswer = "b",
                score = 10,
                picPath = "mq9",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 2,
                question = "What is the solution to the equation 2x + 3 = 7?",
                option1 = "x = 1",
                option2 = "x = 2",
                option3 = "x = 3",
                option4 = "x = 4",
                correctAnswer = "b",
                score = 10,
                picPath = "mq8",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 3,
                question = "What is the area of a circle with radius 5 units?",
                option1 = "25π square units",
                option2 = "10π square units",
                option3 = "15π square units",
                option4 = "5π square units",
                correctAnswer = "a",
                score = 10,
                picPath = "mq6",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 4,
                question = "What is the derivative of x² with respect to x?",
                option1 = "2x",
                option2 = "x²",
                option3 = "x",
                option4 = "1",
                correctAnswer = "a",
                score = 10,
                picPath = "mq4",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 5,
                question = "What is the value of the integral ∫x dx?",
                option1 = "x² + C",
                option2 = "x + C",
                option3 = "1 + C",
                option4 = "ln(x) + C",
                correctAnswer = "a",
                score = 10,
                picPath = "mq1",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 6,
                question = "What is the value of sin(90°)?",
                option1 = "0",
                option2 = "1",
                option3 = "√2/2",
                option4 = "√3/2",
                correctAnswer = "b",
                score = 10,
                picPath = "mq8",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 7,
                question = "What is the sum of the interior angles of a triangle?",
                option1 = "90°",
                option2 = "180°",
                option3 = "270°",
                option4 = "360°",
                correctAnswer = "b",
                score = 10,
                picPath = "mq5",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 8,
                question = "What is the value of the expression 2³?",
                option1 = "4",
                option2 = "6",
                option3 = "8",
                option4 = "10",
                correctAnswer = "c",
                score = 10,
                picPath = "mq2",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 9,
                question = "What is the formula for the circumference of a circle?",
                option1 = "2πr",
                option2 = "πr²",
                option3 = "2πr²",
                option4 = "πd",
                correctAnswer = "a",
                score = 10,
                picPath = "mq6",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 10,
                question = "What is the next prime number after 7?",
                option1 = "9",
                option2 = "10",
                option3 = "11",
                option4 = "13",
                correctAnswer = "c",
                score = 10,
                picPath = "mq9",
                clickedOption = null
            )
        )

        return questions
    }
    private fun englishQuestionList() : MutableList<QuestionModel> {
        val questions : MutableList<QuestionModel> = mutableListOf()

        questions.add(
            QuestionModel(
                id = 1,
                question = "What is the antonym of 'happy'?",
                option1 = "Sad",
                option2 = "Angry",
                option3 = "Excited",
                option4 = "Joyful",
                correctAnswer = "a",
                score = 10,
                picPath = "eq1",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 2,
                question = "Which of the following is a synonym for 'beautiful'?",
                option1 = "Ugly",
                option2 = "Pretty",
                option3 = "Weak",
                option4 = "Sad",
                correctAnswer = "b",
                score = 10,
                picPath = "eq2",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 3,
                question = "What is the plural form of 'mouse'?",
                option1 = "Mouses",
                option2 = "Mouse",
                option3 = "Mice",
                option4 = "Mices",
                correctAnswer = "c",
                score = 10,
                picPath = "eq6",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 4,
                question = "Which word is an adjective in the following sentence: 'The quick brown fox jumps over the lazy dog'?",
                option1 = "Fox",
                option2 = "Jumps",
                option3 = "Quick",
                option4 = "Over",
                correctAnswer = "c",
                score = 10,
                picPath = "eq7",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 5,
                question = "What is the correct past tense of 'run'?",
                option1 = "Running",
                option2 = "Ran",
                option3 = "Runned",
                option4 = "Runs",
                correctAnswer = "b",
                score = 10,
                picPath = "en1",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 6,
                question = "Identify the verb in the following sentence: 'She reads a book every night.'",
                option1 = "Reads",
                option2 = "Book",
                option3 = "Every",
                option4 = "Night",
                correctAnswer = "a",
                score = 10,
                picPath = "eq2",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 7,
                question = "What is the comparative form of 'good'?",
                option1 = "Gooder",
                option2 = "More good",
                option3 = "Better",
                option4 = "Best",
                correctAnswer = "c",
                score = 10,
                picPath = "eq4",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 8,
                question = "Which sentence is correct?",
                option1 = "She don't like ice cream.",
                option2 = "She doesn't likes ice cream.",
                option3 = "She doesn't like ice cream.",
                option4 = "She don't likes ice cream.",
                correctAnswer = "c",
                score = 10,
                picPath = "eq5",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 9,
                question = "What is the meaning of the word 'ubiquitous'?",
                option1 = "Rare",
                option2 = "Everywhere",
                option3 = "Nowhere",
                option4 = "Unique",
                correctAnswer = "b",
                score = 10,
                picPath = "eq3",
                clickedOption = null
            )
        )

        questions.add(
            QuestionModel(
                id = 10,
                question = "Choose the correct form of the verb: 'She _____ to the store.'",
                option1 = "Goed",
                option2 = "Going",
                option3 = "Go",
                option4 = "Went",
                correctAnswer = "d",
                score = 10,
                picPath = "eq6",
                clickedOption = null
            )
        )

        return questions
    }



}