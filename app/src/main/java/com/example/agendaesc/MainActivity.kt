package com.example.agendaesc

import ItemMateria
import MyToolbar
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agendaesc.databinding.ActivityMainBinding
import com.huawei.hms.analytics.HiAnalytics
import com.huawei.hms.analytics.HiAnalyticsTools
import java.lang.reflect.Array


class MainActivity : AppCompatActivity(), MateriaAdapter.MyonclickListener{

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

// Analytics
        HiAnalyticsTools.enableLog()
        val instance = HiAnalytics.getInstance(applicationContext);

        //database
        val admin = Database(this, "agendaEscolar", null, 1)
        val db = admin.writableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM materias", null)
        val adaptador = MateriaAdapter(this)
        adaptador.RecyclerViewMateriaAdapter(cursor)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adaptador


        binding.btnAdd.setOnClickListener {

            val int = Intent(this, Formulario::class.java)
            int.putExtra("nombre", "Karen")
            startActivity(int)

            val bundle = Bundle()
            bundle.putString("pt1", "high")
            bundle.putString("exam_level", "1-1")
            bundle.putString("exam_time", "20190520-08")
            instance.onEvent("btn", bundle)
        }


        MyToolbar().show(this, "Agenda Escolar", false)


        binding.swipe.setOnRefreshListener {
           // adaptador.notifyDataSetChanged()
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
            Toast.makeText(this, "Refrescado", Toast.LENGTH_SHORT).show()
            binding.swipe.isRefreshing = false
        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_item_one) {
            var int: Intent = Intent(this, vista_tareas::class.java)
            int.putExtra("nombre", "Karen")
            startActivity(int)
        }
           // Toast.makeText(this, "Opcion1", Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }

    override fun OnClick(position:Int) {
       // Toast.makeText(this, "Esto es:${list[position]}", Toast.LENGTH_SHORT).show()
        var int = Intent(this, ver_item_materia::class.java)
        val pos: TextView = findViewById(R.id.NombreMateria)
        pos.text.toString()
        int.putExtra("position", pos.text)
        startActivity(int)

    }

    }



