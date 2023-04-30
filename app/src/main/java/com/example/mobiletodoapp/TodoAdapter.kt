package com.example.mobiletodoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletodoapp.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var onItemClickListener: ((TodoData) -> Unit)? = null


    fun setOnItemClickListener(block: (todoData: TodoData) -> Unit) {
        onItemClickListener = block
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val d = models[position]

            val pos = position + 1
            binding.tvTitle.text = d.title
            binding.tvBody.text = d.body
            binding.tvPosition.text = pos.toString()


            binding.root.setOnClickListener {
                onItemClickListener?.invoke(d)
            }
        }
    }


    var models = listOf<TodoData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TodoViewHolder(
        ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(position)
    }
}