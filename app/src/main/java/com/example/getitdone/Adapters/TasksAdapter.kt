package com.example.getitdone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.getitdone.Entities.Task
import com.example.getitdone.databinding.ItemTaskBinding
import com.google.android.material.checkbox.MaterialCheckBox

class TasksAdapter(private val tasks : List<Task>, private val listener: TaskUpdatedListener) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return tasks.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    inner class ViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.textViewTitle.text = task.title
            binding.textViewDetails.text = task.description
            binding.checkbox.addOnCheckedStateChangedListener { _, state ->
                val updatedTask = when (state) {
                    MaterialCheckBox.STATE_CHECKED -> {
                        task.copy(isCompeted = true)
                    }
                    else -> {
                        task.copy(isCompeted = false)
                    }
                }
                listener.onTaskUpdated(updatedTask)
            }

            binding.starSelector.addOnCheckedStateChangedListener { _, state ->
                val updatedTask = when (state) {
                    MaterialCheckBox.STATE_CHECKED -> {
                        task.copy(isStarred = true)
                    }
                    else -> {
                        task.copy(isStarred = false)
                    }
                }
                listener.onTaskUpdated(updatedTask)
            }

            binding.buttonDelete.setOnClickListener {
                listener.onTaskDeleted(task)
                listener.fetchUpdatedTasks()
            }
        }
    }

    interface TaskUpdatedListener{

        fun onTaskUpdated(task: Task)

        fun onTaskDeleted(task: Task)

        fun fetchUpdatedTasks()
    }


}