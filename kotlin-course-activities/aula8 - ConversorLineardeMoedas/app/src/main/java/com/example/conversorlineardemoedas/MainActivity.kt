package com.example.conversorlineardemoedas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.conversorlineardemoedas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = binding.ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonDolar.setOnClickListener(){
            escrever(0.8)
        }

        binding.buttonReal.setOnClickListener(){
            escrever(5.0)
        }
        binding.buttonPeso.setOnClickListener(){
            escrever(10.2)
        }f


        }
    private fun escrever(taxa:Double){
        val euros = binding.editText.text.toString().trim()

        if(!euros.isEmpty()){
            val resultado = euros.toDouble() * taxa
            Toast.makeText(applicationContext, "${resultado}$", Toast.LENGTH_SHORT).show()
            binding.editEuro.setText("O valor é: ${resultado}$")
        }
    }

}