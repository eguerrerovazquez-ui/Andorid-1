package com.example.entregable1 // (Asegúrate de que este sea tu paquete)

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Actividad2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad2) // Tu XML

        val btnViajar = findViewById<Button>(R.id.btnViajar) // Asegúrate de tener este botón en el XML

        btnViajar.setOnClickListener {

            btnViajar.isEnabled = false

            val handler = Handler(Looper.getMainLooper())

            handler.postDelayed({

                val intent = Intent(this, Segunda::class.java)
                startActivity(intent)

                btnViajar.isEnabled = true
            }, 5000)
        }
    }
}