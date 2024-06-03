package com.cal.kotlinquizappproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cal.kotlinquizappproject.R
import com.cal.kotlinquizappproject.databinding.QuestionViewholderBinding

class QuestionAdapter
    (
    val correctAnswer: String,
    val user: MutableList<String> = mutableListOf(),
    var returnScore: score
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {


    interface score {
        fun amount(number: Int, clickedAnswer: String)
    }

    private lateinit var binding: QuestionViewholderBinding
    inner class QuestionViewHolder: RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = QuestionViewholderBinding.inflate(inflater, parent, false)
        return QuestionViewHolder()
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val binding = QuestionViewholderBinding.bind(holder.itemView)
        binding.txtAnswer.text = differ.currentList[position]

        var currentPosition = 0
        when(correctAnswer){
            "a" ->{
                currentPosition = 0
            }
            "b" ->{
                currentPosition = 1
            }
            "c" ->{
                currentPosition = 2
            }
            "d" ->{
                currentPosition = 3
            }
        }
        if (differ.currentList.size == 5 && currentPosition == position){
            binding.txtAnswer.setBackgroundResource(R.drawable.green_background)
            val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.tick)
            binding.txtAnswer.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
        }

        if(differ.currentList.size == 5){
            var clickedPosition = 0
            when(differ.currentList[4]){
                "a" ->{
                    clickedPosition = 0
                }
                "b" ->{
                    clickedPosition = 1
                }
                "c" ->{
                    clickedPosition = 2
                }
                "d" ->{
                    clickedPosition = 3
                }
            }
            if (clickedPosition == position && clickedPosition != currentPosition){
                binding.txtAnswer.setBackgroundResource(R.drawable.red_background)
                val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.thieves)
                binding.txtAnswer.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
            }
        }

        if(position == 4){
            binding.root.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            var str = ""
            when(position){
                0 ->{
                    str = "a"
                }
                1 -> {
                    str = "b"
                }
                2 -> {
                    str = "c"
                }
                3 -> {
                    str = "d"
                }
            }

            user.add(4,str)
            notifyDataSetChanged()

            if(currentPosition == position){
                binding.txtAnswer.setBackgroundResource(R.drawable.green_background)
                val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.tick)
                binding.txtAnswer.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                returnScore.amount(10,str)
            }
            else{
                binding.txtAnswer.setBackgroundResource(R.drawable.red_background)
                val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.thieves)
                binding.txtAnswer.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                returnScore.amount(0,str)
            }
        }

        if(differ.currentList.size == 5) holder.itemView.setOnClickListener(null)

    }


    private val differCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
}

