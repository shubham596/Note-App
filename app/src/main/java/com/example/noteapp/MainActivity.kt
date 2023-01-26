package com.example.noteapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var recyclerView:RecyclerView
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter



        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)


        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }

        })

    }

        override fun onItemClicked(note: Note) {
            viewModel.deleteNote(note)
            Toast.makeText(this, "${note.text} delete", Toast.LENGTH_SHORT).show()
        }

    fun submitData(view: android.view.View) {
            val input = findViewById<EditText>(R.id.Name)
    val noteText: String = input.text.toString()
 if(noteText.isNotEmpty()){

   viewModel.insertNote(Note(noteText))
     Toast.makeText(this, " Added", Toast.LENGTH_SHORT).show()
 }
    }


    }





