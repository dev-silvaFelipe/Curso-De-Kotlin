package com.example.conversortemperatura

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonConverter.setOnClickListener{
            if(!binding.editCelsius.text.toString().isEmpty()) {
                var celsius: Double = binding.editCelsius.text.toString().toDouble()
                var farenheit = String.format("%.2f", celsius * 1.8 + 32)
                binding.textResultado.text = farenheit.toString() + "°F"
            }
            else{
                binding.textResultado.text = "Digite um valor válido"
            }
        }
    }
}