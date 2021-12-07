package com.example.agendaesc

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agendaesc.databinding.ItemMateriaBinding


class MateriaAdapter(val listener: MyonclickListener): RecyclerView.Adapter<MateriaAdapter.ViewHolder>() {

    //lateinit var context: Context
    lateinit var cursor: Cursor


    fun RecyclerViewMateriaAdapter(cursor: Cursor) {
        //this.context = context
        this.cursor = cursor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_materia, parent, false))
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)
        holder.NombreMateria.text = cursor.getString(1)
        holder.NombreMaestro.text = cursor.getString(4)


        }

    override fun getItemCount(): Int {
        if (cursor == null)
            return 0
        else
            return cursor.count
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       // val id_materia: TextView
        val bindingItems = ItemMateriaBinding.bind(itemView)
        val NombreMateria: TextView = bindingItems.NombreMateria
        val NombreMaestro: TextView = bindingItems.NombreMaestro
init {
    itemView.setOnClickListener {
        val position = bindingAdapterPosition
        listener.OnClick(position)
    }
}
      /*  constructor(view: View) : super(view) {

            val bindingItems = ItemMateriaBinding.bind(itemView)
            //id_materia = bindingItems.idmateria
            NombreMateria = bindingItems.NombreMateria
            NombreMaestro = bindingItems.NombreMaestro*/


          /*  view.setOnClickListener {
                Toast.makeText(context, "${NombreMateria.text}, $id_materia", Toast.LENGTH_SHORT).show()
                var int: Intent = Intent(context, ver_item_materia::class.java)
                int.putExtra("position", id_materia.text.toString())
                startActivity(context, int, Bundle())
            }*/
        }

    interface MyonclickListener {
        fun OnClick(
            position: Int
        )

    }
}
