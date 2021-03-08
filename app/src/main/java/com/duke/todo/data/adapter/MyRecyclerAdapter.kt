package com.duke.todo.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.duke.todo.R
import com.duke.todo.data.db.entity.Priorities
import com.duke.todo.data.db.entity.ToDoData
import com.duke.todo.databinding.ItemRecyclerBinding
import com.duke.todo.ui.list.ListFragmentDirections
import com.duke.todo.utils.Constance


class MyRecyclerAdapter(val const: Constance) : RecyclerView.Adapter<MyRecyclerAdapter.MyReVh>() {

    private var list = emptyList<ToDoData>()

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

    @SuppressWarnings("ResourceAsColor")
    override fun onBindViewHolder(holder: MyReVh, position: Int) {

        holder.binding.txtDescriptionItemRecycler.text = list[position].description
        holder.binding.txtTitleItemRecycler.text = list[position].title
        holder.binding.rowLayout.setOnClickListener {

            val acction = ListFragmentDirections.actionListFragmentToUpdateFragment(list[position])
            holder.binding.rowLayout.findNavController()
                .navigate(acction)
        }


        when (list[position].priorities) {
            Priorities.HIGH -> {
                holder.binding.priorityIndicatorItemRecycler.setImageResource(R.drawable.shape_ciercle_image_view_hight)
            }

            Priorities.MEDIUM -> {
                holder.binding.priorityIndicatorItemRecycler.setImageResource(R.drawable.shape_ciercle_image_view_medium)
            }

            Priorities.LOW -> {
                holder.binding.priorityIndicatorItemRecycler.setImageResource(R.drawable.shape_ciercle_image_view_low)
            }

        }


    }

    fun setList(toDoData: List<ToDoData>) {
        this.list = toDoData
        notifyDataSetChanged()
    }

    fun getColor(context: Context, id: Int): Int {

        return if (const == Constance.HIGHERMOBILE) {
            ContextCompat.getColor(context, id)
        } else {
            context.resources.getColor(id)
        }
    }

}