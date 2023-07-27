package com.example.note2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.note2.databinding.ItemRcViewBinding
import com.example.note2.model.Task

class NoteAdapter(
    private val onNoteDone: (Task) -> Unit, private val onNoteRemoved: (Task) -> Unit
) : androidx.recyclerview.widget.ListAdapter<Task, NoteAdapter.NoteViewHolder>(NoteDiffUtil()) {

    inner class NoteViewHolder(private var binding: ItemRcViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, onNoteDone: (Task) -> Unit, onNoteRemoved: (Task) -> Unit) {
            binding.itemTvDesc.text = task.title
            binding.itemTvDesc.text = task.desc
            binding.isDone.isChecked = task.isDone

            binding.isDone.setOnCheckedChangeListener { _, isDone ->
                task.isDone = isDone
                onNoteDone(task)
            }
            itemView.setOnLongClickListener{
                onNoteRemoved(task)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        ItemRcViewBinding.inflate(LayoutInflater.from(parent.context),parent, false)
    )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val notePosition = getItem(position)
        holder.bind(notePosition, onNoteDone, onNoteRemoved)
    }
}

class NoteDiffUtil : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}
