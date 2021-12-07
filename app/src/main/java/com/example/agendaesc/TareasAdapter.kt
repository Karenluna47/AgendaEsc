package com.example.agendaesc

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agendaesc.databinding.ItemTareasBinding

class TareasAdapter( val listener:MyonClickListener):RecyclerView.Adapter<TareasAdapter.MyViewHolderTask>() {
    lateinit var cursor: Cursor

    fun RecyclerViewTareaAdapter(cursor: Cursor) {
        this.cursor = cursor
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderTask {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolderTask (inflater.inflate(R.layout.item_tareas, parent, false))
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolderTask, position: Int) {
        cursor.moveToPosition(position)
        holder.DescripcionTarea.text = cursor.getString(2)
        holder.TituloTarea.text = cursor.getString(1)
        }


    override fun getItemCount(): Int {
        if (cursor == null)
            return 0
        else
            return cursor.count
    }

    inner class MyViewHolderTask(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bindingTareas = ItemTareasBinding.bind(itemView)
        val TituloTarea: TextView = bindingTareas.TituloTarea
        val DescripcionTarea: TextView = bindingTareas.descripcionTarea

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                listener.OnClick(position)
            }
        }

    }

    interface MyonClickListener{
        fun OnClick(
            position: Int
        )
    }
}
