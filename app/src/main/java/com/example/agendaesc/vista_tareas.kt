package com.example.agendaesc

import MyToolbar
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agendaesc.databinding.ActivityVistaTareasBinding
import com.huawei.hms.analytics.HiAnalytics
import com.huawei.hms.analytics.HiAnalyticsTools

class vista_tareas : AppCompatActivity(), TareasAdapter.MyonClickListener {
    private lateinit var bintask : ActivityVistaTareasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bintask = ActivityVistaTareasBinding.inflate(layoutInflater)
        setContentView(bintask.root)

        HiAnalyticsTools.enableLog()
        val instance = HiAnalytics.getInstance(applicationContext);

        val admin = Database(this, "agendaEscolar", null, 1)
        val db = admin.readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM tareas", null)
        val adaptador = TareasAdapter(this)
        adaptador.RecyclerViewTareaAdapter(cursor)


        bintask.recyclerTareas.setHasFixedSize(true)
        bintask.recyclerTareas.layoutManager = LinearLayoutManager(this)
        bintask.recyclerTareas.adapter = adaptador

        bintask.btnAddTask.setOnClickListener {
            //Toast.makeText(this, "Prueba", Toast.LENGTH_SHORT).show()
            var int: Intent = Intent(this, Form_tareas::class.java)
            int.putExtra("nombre", "Karen")
            startActivity(int)
            val bundle = Bundle()
            bundle.putString("pt2", "high")
            bundle.putString("exam_level", "1-1")
            bundle.putString("exam_time", "20190520-08")
            instance.onEvent("btn", bundle)
        }
        MyToolbar().show(this, "Agenda Escolar", false)


        bintask.swipetask.setOnRefreshListener {
            // adaptador.notifyDataSetChanged()
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
            Toast.makeText(this, "Refrescado", Toast.LENGTH_SHORT).show()
            bintask.swipetask.isRefreshing = false
        }


    }

    override fun OnClick(position: Int) {
      // Toast.makeText(this, "Esto es:${list_tareas[position]}", Toast.LENGTH_SHORT).show()
        var int = Intent(this, ver_item_tareas::class.java)
        val post: TextView = findViewById(R.id.Titulo_tarea)
        post.text.toString()
        int.putExtra("position", post.text)
        startActivity(int)
    }
}

