package com.example.agendaesc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.agendaesc.databinding.ActivityHorarioBinding

class Horario : AppCompatActivity() {

    private lateinit var bindhor : ActivityHorarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horario)
        bindhor = ActivityHorarioBinding.inflate(layoutInflater)
        setContentView(bindhor.root)



    }
}