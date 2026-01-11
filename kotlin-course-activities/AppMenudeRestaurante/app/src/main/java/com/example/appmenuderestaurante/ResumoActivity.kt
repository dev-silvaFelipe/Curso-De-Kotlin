package com.example.appmenuderestaurante

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appmenuderestaurante.databinding.ActivityResumoBinding

class ResumoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResumoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResumoBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val i = intent
        val quantCafe = i.getStringExtra("quant_cafe" ).toString().toInt()
        val quantPao = i.getStringExtra("quant_pao").toString().toInt()
        val quantChocolate = i.getStringExtra("quant_chocolate").toString().toInt()
        val precoCafe = i.getDoubleExtra("preco_cafe",0.0 )
        val precoPao = i.getDoubleExtra("preco_pao", 0.0)
        val precoChocolate = i.getDoubleExtra("preco_chocolate", 0.0)

        val texto = "Resumo do pedido: \n" +
                "Café: $quantCafe Preço:$${quantCafe*precoCafe}\n" +
                "Pão: $quantPao Preço: $${quantPao*precoPao}\n" +
                "Chocolate: $quantChocolate Preço: $${quantChocolate*precoChocolate}";

        binding.resumoPedido.setText(texto)

    }
}