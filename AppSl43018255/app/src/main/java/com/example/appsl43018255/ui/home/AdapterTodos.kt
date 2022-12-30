package com.example.appsl43018255.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appsl48399119.R
import com.example.appsl43018255.data.response.todos.TodosResponseItem

class AdapterTodos(private val list: List<TodosResponseItem>): RecyclerView.Adapter<AdapterTodos.AdapterTodosViewHolder>() {


    class AdapterTodosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtId : TextView = itemView.findViewById(R.id.txtId)
        var txtUserId : TextView = itemView.findViewById(R.id.txtUserId)
        var txtTitle : TextView = itemView.findViewById(R.id.txtTitle)
        var txtCompleted : TextView = itemView.findViewById(R.id.txtCompleted)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTodosViewHolder {
        return AdapterTodosViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todos, parent, false)
        )

    }

    override fun onBindViewHolder(holder: AdapterTodosViewHolder, position: Int) {
        holder.txtId.text = list.get(position).id.toString()
        holder.txtUserId.text = list.get(position).userId.toString()
        holder.txtTitle.text = list.get(position).title
        holder.txtCompleted.text = list.get(position).completed.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}