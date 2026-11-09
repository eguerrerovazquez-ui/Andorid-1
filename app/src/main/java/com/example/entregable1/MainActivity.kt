package com.example.entregable1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn1 = findViewById<Button>(R.id.Act1)
        val btn2 = findViewById<Button>(R.id.Act2)
        val btn3 = findViewById<Button>(R.id.Act3)
        val btn4 = findViewById<Button>(R.id.Act4)
        val btn5 = findViewById<Button>(R.id.Act5)
        val btn6 = findViewById<Button>(R.id.Act6)

        btn1.setOnClickListener{
            startActivity(Intent(this, Actividad1::class.java))
        }

        btn2.setOnClickListener{
            startActivity(Intent(this, Actividad2::class.java))
        }

        btn3.setOnClickListener{
            startActivity(Intent(this, Actividad3::class.java))
        }

        btn4.setOnClickListener{
            startActivity(Intent(this, Actividad4::class.java))
        }

        btn5.setOnClickListener{
            startActivity(Intent(this, Actividad5::class.java))
        }

        btn6.setOnClickListener{
            startActivity(Intent(this, Actividad6::class.java))
        }


    }


}