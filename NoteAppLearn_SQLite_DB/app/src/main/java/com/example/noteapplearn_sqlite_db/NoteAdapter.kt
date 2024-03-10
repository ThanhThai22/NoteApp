package com.example.noteapplearn_sqlite_db

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private var notes: List<Note>, context: Context) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val db: NoteDatabase = NoteDatabase(context)

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTV = itemView.findViewById<TextView>(R.id.titletv)
        val contentTV = itemView.findViewById<TextView>(R.id.contenttv)
        val updateBtn = itemView.findViewById<ImageView>(R.id.updateButton)
        val deleteBtn = itemView.findViewById<ImageView>(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTV.text = note.title
        holder.contentTV.text = note.content

        holder.updateBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateActivity::class.java).apply {
                putExtra("id_note", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteBtn.setOnClickListener {
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Note has Deleted", Toast.LENGTH_SHORT).show()
        }

    }

    //tao ham lam moi du lieu
    fun refreshData(newNote: List<Note>){
        notes = newNote
        notifyDataSetChanged()
    }
}