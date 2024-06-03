package com.cal.kotlinquizappproject.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.cal.kotlinquizappproject.R
import com.cal.kotlinquizappproject.adapter.QuestionAdapter
import com.cal.kotlinquizappproject.databinding.ActivityQuestionsBinding
import com.cal.kotlinquizappproject.domain.QuestionModel

class QuestionsActivity : AppCompatActivity(), QuestionAdapter.score {
    private lateinit var binding: ActivityQuestionsBinding
    var position: Int = 0
    var receivedList : MutableList<QuestionModel> = mutableListOf()
    var allScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        receivedList = intent.getParcelableArrayListExtra<QuestionModel>("list")!!.toMutableList()

        binding.apply {

            btnBack.setOnClickListener {
                finish()
            }

            progressBar2.progress = 1

            txtQuestion.text = receivedList[position].question
            val drawableResourceId : Int = binding.root.resources.getIdentifier(receivedList[position].picPath, "drawable", binding.root.context.packageName)
            Glide.with(this@QuestionsActivity)
                .load(drawableResourceId)
                .centerCrop()
                .apply(RequestOptions().transform(RoundedCorners(20)))
                .into(quesImage)

            loadAnswers()

            nextBtn.setOnClickListener {

                if(progressBar2.progress == 10){
                    val intent = Intent(this@QuestionsActivity, ScoreActivity::class.java)
                    intent.putExtra("score", allScore)
                    startActivity(intent)
                    finish()
                    return@setOnClickListener
                }

                position++
                progressBar2.progress += 1
                questionNoTxt.text = "Question ${progressBar2.progress}/10"
                txtQuestion.text = receivedList[position].question

                val drawableResourceId : Int = binding.root.resources.getIdentifier(
                    receivedList[position].picPath,
                    "drawable",
                    binding.root.context.packageName
                )

                Glide.with(this@QuestionsActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions().transform(RoundedCorners(20)))
                    .into(quesImage)

                loadAnswers()

            }

            backBtn.setOnClickListener {

                if(progressBar2.progress == 1){
                    return@setOnClickListener
                }

                position--
                progressBar2.progress -= 1
                questionNoTxt.text = "Question ${progressBar2.progress}/10"
                txtQuestion.text = receivedList[position].question

                val drawableResourceId : Int = binding.root.resources.getIdentifier(
                    receivedList[position].picPath,
                    "drawable",
                    binding.root.context.packageName
                )

                Glide.with(this@QuestionsActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions().transform(RoundedCorners(20)))
                    .into(quesImage)

                loadAnswers()

            }
        }
    }


    fun loadAnswers(){
        val users: MutableList<String> = mutableListOf()
        users.add(receivedList[position].option1.toString())
        users.add(receivedList[position].option2.toString())
        users.add(receivedList[position].option3.toString())
        users.add(receivedList[position].option4.toString())

        if(receivedList[position].clickedOption!=null) users.add(receivedList[position].clickedOption.toString())

        val questionAdapter by lazy {
            QuestionAdapter(receivedList[position].correctAnswer.toString(),users, this) }

        questionAdapter.differ.submitList(users)
        binding.rvQuestions.apply {
            layoutManager = LinearLayoutManager(this@QuestionsActivity)
            adapter = questionAdapter
        }

    }

    override fun amount(number: Int, clickedAnswer: String) {
        allScore += number
        receivedList[position].clickedOption = clickedAnswer
    }


}