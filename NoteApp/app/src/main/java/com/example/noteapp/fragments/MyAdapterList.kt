package com.example.noteapp.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.NoteListDirections
import com.example.noteapp.R
import com.example.noteapp.data.Note

class MyAdapterList(val data: List<Note>) : RecyclerView.Adapter<MyAdapterList.MyViewHolder>() {

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val titleTextView = row.findViewById<TextView>(R.id.titleTextView)
        val emailTextView = row.findViewById<TextView>(R.id.emailTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv,
            parent, false
        )
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView.text = data.get(position).attributes.noteTitle
        holder.emailTextView.text = data.get(position).attributes.userEmail

        holder.row.setOnClickListener {
            val action = NoteListDirections.viewDetails(1)
            Navigation.findNavController(holder.row).navigate(action)
        }
    }

    override fun getItemCount(): Int = data.size
}