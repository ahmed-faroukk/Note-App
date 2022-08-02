package com.ahmedfarouk.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditeNoteActivity : AppCompatActivity() {
    lateinit var TitleEdite :EditText
    lateinit var  NoteEdite :EditText
    lateinit var  Savebtn : Button
    lateinit var viewModel :NoteViewModel1
    var id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edite_note_acticity)
        var intent = getIntent()
        TitleEdite = findViewById(R.id.EditeTitle)
        NoteEdite = findViewById(R.id.editeNote)
        Savebtn = findViewById(R.id.button_save)
        viewModel = ViewModelProvider(this ,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel1::class.java)

        val noteType = intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            val noteTit = intent.getStringExtra("noteTitle")
            val notedesc = intent.getStringExtra("noteDescription")
            id = intent.getIntExtra("noteID" , -1)
            Savebtn.setText("Update Note")
            TitleEdite.setText(noteTit)
            NoteEdite.setText(notedesc)


        }else{
            Savebtn.setText("Save Note")
        }
        Savebtn.setOnClickListener {
            val GetTitle = TitleEdite.text.toString()
            val GetDesc = NoteEdite.text.toString()
            if(noteType.equals("Edit")){
            if(GetTitle.isNotEmpty() &&GetDesc.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM , yyyy - HH:mm")
                val currentDate: String = sdf.format(Date())
                val UpdateNote = Note(GetTitle, GetDesc, currentDate)
                UpdateNote.id = id
                viewModel.updateNote(UpdateNote)
                Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()

            }

            }else{
                if(GetTitle.isNotEmpty() &&GetDesc.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM , yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModel.addNote(Note(GetTitle , GetDesc , currentDate))
                    Toast.makeText(this , "Note Added.." , Toast.LENGTH_LONG).show()

                }

            }

        startActivity(Intent(application,MainActivity::class.java))
            this.finish()
        }



    }

}