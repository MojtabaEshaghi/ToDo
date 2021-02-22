package com.duke.todo.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.duke.todo.R
import com.duke.todo.data.db.entity.Priorities
import com.duke.todo.data.db.entity.ToDoData
import com.duke.todo.databinding.ItemRecyclerBinding

class MyRecyclerAdapter : RecyclerView.Adapter<MyRecyclerAdapter.MyReVh>() {

    var list = emptyList<ToDoData>()

    class MyReVh(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReVh {

        return MyReVh(
            ItemRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyReVh, position: Int) {

        holder.binding.txtDescriptionItemRecycler.text = list[position].description
        holder.binding.txtTitleItemRecycler.text = list[position].title
        val priority = list[position].priorities

        when (priority) {

            Priorities.HIGH -> {
                holder.binding.priorityIndicatorItemRecycler.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.red
                    )
                )
            }

            Priorities.MEDIUM -> {
                holder.binding.priorityIndicatorItemRecycler.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.yellow
                    )
                )
            }

            Priorities.LOW -> {
                holder.binding.priorityIndicatorItemRecycler.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.green
                    )
                )
            }

        }


    }


}