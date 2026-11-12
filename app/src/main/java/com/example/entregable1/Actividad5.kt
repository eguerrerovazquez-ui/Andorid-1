package com.example.entregable1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.math.BigDecimal

class Actividad5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividad5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val salida = findViewById<TextView>(R.id.Salida)

        val btnSuma = findViewById<Button>(R.id.suma)
        val btnResta = findViewById<Button>(R.id.resta)
        val btnMultiplicacion = findViewById<Button>(R.id.multiplicacion)
        val btnDivision = findViewById<Button>(R.id.division)
        val btnSiete = findViewById<Button>(R.id.siete)
        val btnOcho = findViewById<Button>(R.id.ocho)
        val btnNueve = findViewById<Button>(R.id.nueve)
        val btnBorrar = findViewById<Button>(R.id.borrar)
        val btnCuatro = findViewById<Button>(R.id.cuatro)
        val btnCinco = findViewById<Button>(R.id.cinco)
        val btnSeis = findViewById<Button>(R.id.seis)
        val btnC = findViewById<Button>(R.id.c)
        val btnTres = findViewById<Button>(R.id.tres)
        val btnDos = findViewById<Button>(R.id.dos)
        val btnUno = findViewById<Button>(R.id.uno)
        val btnCE = findViewById<Button>(R.id.ce)
        val btnIgual = findViewById<Button>(R.id.igual)
        val btnCero = findViewById<Button>(R.id.cero)
        val btnComa = findViewById<Button>(R.id.coma)
        val btnPorcentaje = findViewById<Button>(R.id.porcentaje)

        fun agregarTexto(valor: String) {
            val textoActual = salida.text.toString()
            if (textoActual == "0" || textoActual == "Error") {
                when (valor) {
                    "," -> salida.text = "0,"
                    "+", "-", "*", "/" -> salida.text = "0$valor"
                    else -> salida.text = valor
                }
            } else {
                when (valor) {
                    "," -> {
                        val ultimoChar = textoActual.last()
                        if (ultimoChar in listOf('+', '-', '*', '/')) {
                            salida.append("0,")
                        } else if (ultimoChar != ',') {
                            salida.append(",")
                        }
                    }
                    "+", "-", "*", "/" -> {
                        val ultimoChar = textoActual.last()
                        if (ultimoChar in listOf('+', '-', '*', '/')) {
                            salida.text = textoActual.substring(0, textoActual.length - 1) + valor
                        } else {
                            salida.append(valor)
                        }
                    }
                    else -> salida.append(valor)
                }
            }
        }

        btnSuma.setOnClickListener { agregarTexto("+") }
        btnResta.setOnClickListener { agregarTexto("-") }
        btnMultiplicacion.setOnClickListener { agregarTexto("*") }
        btnDivision.setOnClickListener { agregarTexto("/") }

        btnSiete.setOnClickListener { agregarTexto("7") }
        btnOcho.setOnClickListener { agregarTexto("8") }
        btnNueve.setOnClickListener { agregarTexto("9") }

        btnBorrar.setOnClickListener {
            val textoActual = salida.text.toString()
            if (textoActual == "Error" || textoActual.length <= 1) {
                salida.text = "0"
            } else {
                salida.text = textoActual.substring(0, textoActual.length - 1)
            }
        }

        btnCuatro.setOnClickListener { agregarTexto("4") }
        btnCinco.setOnClickListener { agregarTexto("5") }
        btnSeis.setOnClickListener { agregarTexto("6") }

        btnTres.setOnClickListener { agregarTexto("3") }
        btnDos.setOnClickListener { agregarTexto("2") }
        btnUno.setOnClickListener { agregarTexto("1") }

        btnCero.setOnClickListener { agregarTexto("0") }
        btnComa.setOnClickListener { agregarTexto(",") }

        btnC.setOnClickListener { salida.text = "0" }
        btnCE.setOnClickListener { salida.text = "0" }

        btnPorcentaje.setOnClickListener {
            try {
                val expr = salida.text.toString().replace(',', '.')
                val valDouble = expr.toDoubleOrNull()
                if (valDouble != null) {
                    val res = BigDecimal.valueOf(valDouble / 100).stripTrailingZeros()
                    salida.text = res.toPlainString().replace('.', ',')
                }
            } catch (_: Exception) {
                salida.text = "Error"
            }
        }

        btnIgual.setOnClickListener {
            salida.text = evaluarExpresion(salida.text.toString())
        }
    }

    private fun evaluarExpresion(expresion: String): String {
        try {
            val expr = expresion.replace(',', '.').trim()
            if (expr.isEmpty() || expr == "Error") return "0"

            val tokens = mutableListOf<String>()
            var numberBuffer = StringBuilder()

            var i = 0
            while (i < expr.length) {
                val c = expr[i]
                if (c.isDigit() || c == '.') {
                    numberBuffer.append(c)
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    if (c == '-' && (tokens.isEmpty() || tokens.last() in listOf("+", "-", "*", "/")) && numberBuffer.isEmpty()) {
                        numberBuffer.append(c)
                    } else {
                        if (numberBuffer.isNotEmpty()) {
                            tokens.add(numberBuffer.toString())
                            numberBuffer = StringBuilder()
                        }
                        tokens.add(c.toString())
                    }
                }
                i++
            }
            if (numberBuffer.isNotEmpty()) {
                tokens.add(numberBuffer.toString())
            }

            if (tokens.isEmpty()) return "0"

            val tokensMultiplicacionDivision = mutableListOf<String>()
            var index = 0
            while (index < tokens.size) {
                val token = tokens[index]
                if (token == "*" || token == "/") {
                    if (tokensMultiplicacionDivision.isEmpty() || index + 1 >= tokens.size) return "Error"
                    val prevNum = tokensMultiplicacionDivision.removeAt(tokensMultiplicacionDivision.size - 1).toDoubleOrNull() ?: return "Error"
                    val nextNum = tokens[index + 1].toDoubleOrNull() ?: return "Error"

                    val res = if (token == "*") {
                        prevNum * nextNum
                    } else {
                        if (nextNum == 0.0) return "Error"
                        prevNum / nextNum
                    }
                    tokensMultiplicacionDivision.add(res.toString())
                    index += 2
                } else {
                    tokensMultiplicacionDivision.add(token)
                    index++
                }
            }

            if (tokensMultiplicacionDivision.isEmpty()) return "Error"
            var acumulado = tokensMultiplicacionDivision[0].toDoubleOrNull() ?: return "Error"

            index = 1
            while (index < tokensMultiplicacionDivision.size) {
                val op = tokensMultiplicacionDivision[index]
                if (index + 1 >= tokensMultiplicacionDivision.size) return "Error"
                val nextNum = tokensMultiplicacionDivision[index + 1].toDoubleOrNull() ?: return "Error"

                when (op) {
                    "+" -> acumulado += nextNum
                    "-" -> acumulado -= nextNum
                    else -> return "Error"
                }
                index += 2
            }

            val resultadoBigDecimal = BigDecimal.valueOf(acumulado).stripTrailingZeros()
            return resultadoBigDecimal.toPlainString().replace('.', ',')
        } catch (_: Exception) {
            return "Error"
        }
    }
}