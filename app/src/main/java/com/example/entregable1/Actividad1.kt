package com.example.entregable1

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Actividad1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividad1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val url = findViewById<EditText>(R.id.etUrl)
        val validacion = findViewById<Button>(R.id.enviar)

        validacion.setOnClickListener {
            val texto = url.text.toString().trim()

            if(texto.isEmpty()){
                Toast.makeText(this,"No puede estar vacío", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(Patterns.WEB_URL.matcher(texto).matches()){
                Toast.makeText(this,"Es VALIDA", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"NO VALIDA", Toast.LENGTH_SHORT).show()
                url.error = "Introduce una url valida"
            }
        }
    }
}