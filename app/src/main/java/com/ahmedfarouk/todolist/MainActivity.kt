package com.ahmedfarouk.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmedfarouk.todolist.splashScreen.Home
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {
    lateinit var btn : FloatingActionButton
    lateinit var rv : RecyclerView
    //define our viewModel "that represent database"
    lateinit var viewModel : NoteViewModel1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.floatingActionButton2)
        rv = findViewById(R.id.recycler)
        rv.layoutManager = LinearLayoutManager(this)
        val notervAdapter = noteRVAdapter(this , this , this)
        rv.adapter = notervAdapter

        viewModel = ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel1 ::class.java)
        viewModel.allNotes.observe(this , Observer { List->
            List?.let {
                notervAdapter.UpdateNotes(it)
            }

        })

        btn.setOnClickListener{
            val intent = Intent (this , AddEditeNoteActivity::class.java)
            startActivity(intent)
        }


    }
    override  fun OnDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this ,"${note.noteTitle} Deleted",Toast.LENGTH_LONG ).show()


    }
    override fun OnNoteClick(get: Note) {
        val intent = Intent(this ,AddEditeNoteActivity::class.java)
        intent.putExtra("noteType" , "Edit")
        intent.putExtra("noteTitle" , get.noteTitle)
        intent.putExtra("noteDescription" ,get.noteDescription)
        intent.putExtra("noteID" , get.id)
        startActivity(intent)




    }
}