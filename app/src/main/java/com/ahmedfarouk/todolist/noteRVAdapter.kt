package com.ahmedfarouk.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class noteRVAdapter (val context: Context ,
                     val noteClickDeleteInterface:NoteClickDeleteInterface,
                     val noteClickInterface:NoteClickInterface)
    :RecyclerView.Adapter<noteRVAdapter.Viewholder>(){
inner class Viewholder(itemView:View):RecyclerView.ViewHolder(itemView){
    val Title = itemView.findViewById<TextView>(R.id.Text_Title)
    val note = itemView.findViewById<TextView>(R.id.Text_Note)
    val DeleteIcon = itemView.findViewById<ImageView>(R.id.Delete_image)



}
private val allNotes = ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_note_rv , parent,false)
        return Viewholder(itemView)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.Title.setText(allNotes.get(position).noteTitle)
        holder.note.setText(allNotes.get(position).noteDescription)
        holder.DeleteIcon.setOnClickListener{
            noteClickDeleteInterface.OnDeleteIconClick(allNotes.get(position))
        }
        holder.itemView.setOnClickListener {
            noteClickInterface.OnNoteClick(allNotes.get(position))
        }

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun UpdateNotes(newlist : List<Note>){
        allNotes.clear()
        allNotes.addAll(newlist)
        notifyDataSetChanged()
    }


}
interface NoteClickDeleteInterface{
    fun OnDeleteIconClick(get: Note) {

    }

}
interface NoteClickInterface{
    fun OnNoteClick(get: Note) {

    }

}