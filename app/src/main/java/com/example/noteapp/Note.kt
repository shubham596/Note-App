package com.example.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note(val text:String) {
    @PrimaryKey(autoGenerate = true)var id:Int=0
}