package com.example.noteapplearn_sqlite_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noteapplearn_sqlite_db.databinding.ActivityAddBinding
import com.example.noteapplearn_sqlite_db.databinding.ActivityMainBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var db: NoteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabase(this)

        binding.savebtn.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()
            val note = Note(0, title, content)

            //them note vao db: NoteDatabase
            db.addNote(note)
            //dong csdl
            finish()
            Toast.makeText(this, "Saved complete!", Toast.LENGTH_SHORT).show()
        }


    }
}