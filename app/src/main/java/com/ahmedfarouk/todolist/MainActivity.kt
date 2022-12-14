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
        //declare elements
        btn = findViewById(R.id.floatingActionButton2)
        rv = findViewById(R.id.recycler)
         //set recyclerView
        rv.layoutManager = LinearLayoutManager(this)
         //setAdapter and link with recyclerView
        val notervAdapter = noteRVAdapter(this , this , this)
        rv.adapter = notervAdapter
       // use viewModel Provider and observer to update all changes
        viewModel = ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel1 ::class.java)
        viewModel.allNotes.observe(this , Observer { List->
            List?.let {
                notervAdapter.UpdateNotes(it)
            }

        })
         //ADD NEW NOTE
        btn.setOnClickListener{
            val intent = Intent (this , AddEditeNoteActivity::class.java)
            startActivity(intent)
        }


    }//DELETE NOTE
    override  fun OnDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this ,"${note.noteTitle} Deleted",Toast.LENGTH_LONG ).show()


    }//EDIT NOTE
    override fun OnNoteClick(get: Note) {
        val intent = Intent(this ,AddEditeNoteActivity::class.java)
        intent.putExtra("noteType" , "Edit")
        intent.putExtra("noteTitle" , get.noteTitle)
        intent.putExtra("noteDescription" ,get.noteDescription)
        intent.putExtra("noteID" , get.id)
        startActivity(intent)




    }
}
