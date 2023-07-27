package com.example.note2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.note2.adapters.NoteAdapter
import com.example.note2.databinding.ActivityMainBinding
import com.example.note2.model.Task
import com.example.note2.ui.fragment.AddFragment
import com.example.note2.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TaskViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        noteAdapter = NoteAdapter(
            onNoteDone = { task -> viewModel.markNoteAsCompleted(task) },
            onNoteRemoved = { task -> viewModel.remove(task) })
        binding.recycler.adapter = noteAdapter

        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]


        binding.btnAdd.setOnClickListener {
            showAddNoteDialog()
        }
        observeChanges()
    }

    private fun showAddNoteDialog() {
        val dialog = AddFragment(object : AddFragment.OnNoteAddedListener {
            override fun onNoteAdded(title: String, desc: String) {
                viewModel.add(title, desc)
            }
        })
        dialog.show(supportFragmentManager, "TAG")
    }

    private fun observeChanges() {
        viewModel.getAllTasks().observe(this) { tasks ->
            noteAdapter.submitList(tasks)
        }
    }
}