package com.example.getitdone

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.getitdone.Dao.TaskDao
import com.example.getitdone.Database.GetItDoneDatabase
import com.example.getitdone.Entities.Task
import com.example.getitdone.Fragments.TasksFragment
import com.example.getitdone.databinding.ActivityMainBinding
import com.example.getitdone.databinding.DialogAddtaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: GetItDoneDatabase
    private val taskDao: TaskDao by lazy { database.getTaskDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = PageAdapter(this)
        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = "Tasks"
        }.attach()

        binding.fab.setOnClickListener {
            showAddTaskDialog()
        }

        database = GetItDoneDatabase.getDatabase(this)

    }

    private fun showAddTaskDialog() {
        val dialogBinding = DialogAddtaskBinding.inflate(layoutInflater)
//        MaterialAlertDialogBuilder(this)
//            .setTitle("Add new task")
//            .setView(dialogBinding.root)
//            .setPositiveButton("Save") { _, _ ->
//                Toast.makeText(
//                    this,
//                    "Your task is ${dialogBinding.editText.text}",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//            .setNegativeButton("Cancel", null)
//            .show()

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.buttonShowDetils.setOnClickListener{
            if(dialogBinding.editTextTaskDetails.visibility == View.VISIBLE)
                dialogBinding.editTextTaskDetails.visibility = View.GONE
            else
                dialogBinding.editTextTaskDetails.visibility = View.VISIBLE
        }

        dialogBinding.buttonSave.setOnClickListener {
            val task = Task(
                title = dialogBinding.editTextTaskDetails.text.toString(),
                description = dialogBinding.editTextTaskDetails.text.toString()
            )
            taskDao.createTask(task)
        }

        dialog.show()
    }

    inner class PageAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount() = 1

        override fun createFragment(position: Int): Fragment {
            return TasksFragment()
        }

    }
}