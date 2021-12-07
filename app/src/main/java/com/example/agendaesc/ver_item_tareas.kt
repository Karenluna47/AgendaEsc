package com.example.agendaesc

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.agendaesc.databinding.ActivityVerItemTareasBinding

class ver_item_tareas : AppCompatActivity() {

    private lateinit var bindingTak : ActivityVerItemTareasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTak = ActivityVerItemTareasBinding.inflate(layoutInflater)
        setContentView(bindingTak.root)

       val idtask = intent.getStringExtra("position")
        Toast.makeText(this, "$idtask", Toast.LENGTH_SHORT).show()

        val admin = Database(this, "agendaEscolar", null, 1)
        val db = admin.writableDatabase

        if (db == null) {
            Toast.makeText(this, "No se pueden mostrar los datos", Toast.LENGTH_SHORT).show()
        } else {
            val cursor = db.rawQuery("SELECT * FROM tareas where titulo_tarea='$idtask'", null)

            if (cursor.moveToFirst()) {
                var titulo = cursor.getString(cursor.getColumnIndex("titulo_tarea"))
                bindingTak.tituloTarea2.setText(titulo)
                var descripcion = cursor.getString(cursor.getColumnIndex("descripcion_tarea"))
                bindingTak.descripciontareas2.setText(descripcion)
                var fecha = cursor.getString(cursor.getColumnIndex("fecha_entrega"))
                bindingTak.fechaEntrega2.setText(fecha)
                var hora = cursor.getString(cursor.getColumnIndex("hora_entrega"))
                bindingTak.horaEntrega2.setText(hora)

                Toast.makeText(this, "Datos cargados", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "No se pueden cargar los datos ", Toast.LENGTH_SHORT).show()
            }
        }


        //Eliminar

        bindingTak.bntDeleteTask.setOnClickListener {
            val admin = Database(this, "agendaEscolar", null, 1)
            val db = admin.writableDatabase
            if (db == null) {
                Toast.makeText(this, "No DB", Toast.LENGTH_SHORT).show()
            } else {
                val cnt = db.delete("tareas", "titulo_tarea = '${bindingTak.tituloTarea2.text.toString()}'", null)
                db.close()
                if (cnt == 1)
                    Toast.makeText(this, "Se borro el registro con exito ", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "No existe el registro ", Toast.LENGTH_SHORT).show()
            }
        }

        // Actualizar
        bindingTak.bntEditTask.setOnClickListener {
            val admin = Database(this, "agendaEscolar", null, 1)
            val db = admin.writableDatabase
            val record = ContentValues()
            record.put("titulo_tarea", bindingTak.tituloTarea2.text.toString())
            record.put("descripcion_tarea", bindingTak.descripciontareas2.text.toString())
            record.put("fecha_entrega", bindingTak.fechaEntrega2.text.toString())
            record.put("hora_entrega", bindingTak.horaEntrega2.toString())
            val cant = db.update("tareas", record, "titulo_tarea ='${idtask}'", null)
            db.close()
            Toast.makeText(this, "${cant}", Toast.LENGTH_SHORT).show()
            if (cant == 1) {
                Toast.makeText(this, "Se actualizo el registro con exito", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "No existe el registro", Toast.LENGTH_SHORT).show()
            }
        }
    }
}