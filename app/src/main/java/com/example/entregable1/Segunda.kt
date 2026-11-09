package com.example.entregable1

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Segunda : AppCompatActivity() {

    private lateinit var tvCuentaAtras: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        tvCuentaAtras = findViewById(R.id.coundw)


        iniciarCuentaAtrasVisual()
        programarRegresoConPendingIntent()
    }

    private fun iniciarCuentaAtrasVisual() {
        val timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val segundos = millisUntilFinished / 1000
                tvCuentaAtras.text = "Volviendo a Actividad 2 en $segundos segundos..."
            }

            override fun onFinish() {
                tvCuentaAtras.text = "¡Volviendo!"
            }
        }
        timer.start()
    }

    private fun programarRegresoConPendingIntent() {

        val intentRegreso = Intent(this, Actividad2::class.java)

        intentRegreso.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intentRegreso,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val tiempoFuturo = System.currentTimeMillis() + 10000

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            tiempoFuturo,
            pendingIntent
        )
    }
}