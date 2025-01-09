package com.example.juegopiedrapapelotijera

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var tvResultado: TextView
    private lateinit var tvPuntuacionJugador: TextView
    private lateinit var tvPuntuacionComputadora: TextView
    private lateinit var seleccionComputadora: ImageView
    private var puntuacionJugador = 0
    private var puntuacionComputador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvResultado = findViewById(R.id.tvResultado)
        tvPuntuacionJugador = findViewById(R.id.tvPuntuacionJugador)
        tvPuntuacionComputadora = findViewById(R.id.tvPuntuacionComputadora)
        seleccionComputadora = findViewById(R.id.seleccionComputadora)

        val btnPiedra: Button = findViewById(R.id.btnPiedra)
        val btnPapel: Button = findViewById(R.id.btnPapel)
        val btnTijera: Button = findViewById(R.id.btnTijera)

        btnPiedra.setOnClickListener { jugar("Piedra") }
        btnPapel.setOnClickListener { jugar("Papel") }
        btnTijera.setOnClickListener { jugar("Tijera") }
    }

    private fun jugar(eleccionJugador: String){
        val opciones = arrayOf("Piedra", "Papel", "Tijera")
        val eleccionComputadora = opciones[Random.nextInt(opciones.size)]

        //Mostrar la seleccion de la computadora
        when(eleccionComputadora){
            "Piedra" -> seleccionComputadora.setImageResource(R.drawable.piedra)
            "Papel" -> seleccionComputadora.setImageResource(R.drawable.papel)
            "Tijera" -> seleccionComputadora.setImageResource(R.drawable.tijeras)
        }

        //Resultado
        val resultado = when{
            eleccionJugador == eleccionComputadora -> "Empate"
            (eleccionJugador == "Piedra" && eleccionComputadora == "Tijera") ||
            (eleccionJugador == "Papel" && eleccionComputadora == "Piedra") ||
            (eleccionJugador == "Tijera" && eleccionComputadora == "Papel") -> {
                puntuacionJugador++
                "Ganaste"
            }else -> {
                puntuacionComputador++
                "Perdiste"
            }
        }

        //Actualizar TexView
        tvResultado.text = resultado
        tvPuntuacionJugador.text = "Jugador: $puntuacionJugador"
        tvPuntuacionComputadora.text = "Computadora: $puntuacionComputador"
    }
}