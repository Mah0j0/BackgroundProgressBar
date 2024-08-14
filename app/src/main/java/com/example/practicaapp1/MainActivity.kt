package com.example.practicaapp1

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //--------------------DECLARACION DE VARIABLES--------------------
    private lateinit var textViewContador: TextView
    private lateinit var startButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    //------------------- INCIALIZACION DE VARIABLES -------------------
        textViewContador = findViewById(R.id.contadorTextView)
        startButton= findViewById(R.id.startButton)
        progressBar=findViewById(R.id.progressBar)

    //------------------- EVENTO DE BOTON -------------------
        startButton.setOnClickListener {
            startCounterThread()
        }

    }
    private fun startCounterThread() {
        //Hilo que incrementa el contador cada segundo
        Thread {
            var contador = 0
            var switch=true
            while (switch) {
                Thread.sleep(1000)
                contador+=10
                if(contador>=100){
                    switch=false
                }
                //Actualizar el contador en la interfaz
                runOnUiThread {
                    textViewContador.text = contador.toString()+"%"
                    progressBar.progress=contador
                }
            }
            /*val message = "Botón presionado - esperar 10 segundos"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()*/
        }.start()
    }
}