package com.cal.kotlinquizappproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cal.kotlinquizappproject.databinding.PlaceholderViewBinding
import com.cal.kotlinquizappproject.domain.UserModel

class LeaderAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var binding: PlaceholderViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = PlaceholderViewBinding.inflate(inflater, parent, false)
        return ViewHolder()
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = PlaceholderViewBinding.bind(holder.itemView)
        val item = differ.currentList[position]
        binding.txtName.text = item.name
        binding.txtScore.text = item.score.toString()
        binding.txtId.text = (position+3).toString()

        val drawableResourceId: Int = binding.root.resources.getIdentifier(
            differ.currentList[position].picture,
            "drawable",
            binding.root.context.packageName
        )

        Glide.with(binding.root)
            .load(drawableResourceId)
            .into(binding.profileImage)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}