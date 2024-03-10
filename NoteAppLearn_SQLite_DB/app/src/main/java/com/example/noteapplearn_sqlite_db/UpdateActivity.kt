package com.example.noteapplearn_sqlite_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noteapplearn_sqlite_db.databinding.ActivityAddBinding
import com.example.noteapplearn_sqlite_db.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NoteDatabase
    private var noteID: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabase(this)

        noteID = intent.getIntExtra("id_note", -1)
        if (noteID == -1) {
            finish()
            return
        }

        val note = db.getNoteById(noteID)
        binding.editEtTitle.setText(note.title)
        binding.editEtContent.setText(note.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.editEtTitle.text.toString()
            val newContent = binding.editEtContent.text.toString()
            val updateNote = Note(noteID, newTitle, newContent)

            db.updateNote(updateNote)
            finish()
            Toast.makeText(this, "Save changes", Toast.LENGTH_SHORT).show()
        }

    }
}