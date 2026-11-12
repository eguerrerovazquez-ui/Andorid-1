package com.example.entregable1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Actividad2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad2)

        val btnViajar = findViewById<Button>(R.id.btnViajar)

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