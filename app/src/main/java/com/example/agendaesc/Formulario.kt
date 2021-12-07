package com.example.agendaesc

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.agendaesc.databinding.ActivityFormularioBinding

class Formulario : AppCompatActivity() {
    private lateinit var binfor:ActivityFormularioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binfor = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binfor.root)

//Insercion
        binfor.btnMateria.setOnClickListener {
            val admin = Database(this, "agendaEscolar",null,1)
            val db = admin.writableDatabase
            val record = ContentValues()
            record.put("id_materia", binfor.idMateria.text.toString())
            record.put("nombre_materia", binfor.nombreM.text.toString())
            record.put("nombre_maestros", binfor.nombreMaestroForm.text.toString())
            record.put("acronimo", binfor.acronimoM.text.toString())
            record.put("link_materia", binfor.urlM.text.toString())
            db.insert("materias", null, record)
            db.close()
            Toast.makeText(this, "Se cargaron los datos de la materia", Toast.LENGTH_SHORT).show()
        }

    }
}

