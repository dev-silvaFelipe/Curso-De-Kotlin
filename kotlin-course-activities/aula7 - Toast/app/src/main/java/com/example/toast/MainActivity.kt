package com.example.toast

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.toast.databinding.ActivityMainBinding

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
        binding.button.setOnClickListener{
            val nome =  binding.editNome.text.toString().trim()
            val snome = binding.editSobrenome.text.toString().trim()

            if(nome.isEmpty() || snome.isEmpty()){
                binding.editText.text = "Preencha os campos vazios"
                Toast.makeText(applicationContext, "nome não inserido", Toast.LENGTH_SHORT).show()
            }
            else{
                binding.editText.text = "Olá $nome $snome"
                Toast.makeText(applicationContext, "Olá $nome $snome",Toast.LENGTH_LONG).show()
            }
        }
    }
}