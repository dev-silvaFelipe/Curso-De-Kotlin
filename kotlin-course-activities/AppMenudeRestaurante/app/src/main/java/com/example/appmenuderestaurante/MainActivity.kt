package com.example.appmenuderestaurante

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appmenuderestaurante.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var precoCafe = 1.0
    private var precoPao = 2.0
    private var precoChocolate = 3.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //  Café
        binding.checkCafe.setOnClickListener {
            if (binding.checkCafe.isChecked) {
                binding.editQuantidadeCafe.setText("1")
                binding.precoCafe.visibility = View.VISIBLE
            } else {
                binding.editQuantidadeCafe.setText("0")
                binding.precoCafe.visibility = View.GONE
            }
        }

        //  Chocolate
        binding.checkChocolate.setOnClickListener {
            if (binding.checkChocolate.isChecked) {
                binding.editQuantidadeChocolate.setText("1")
                binding.precoChocolate.visibility = View.VISIBLE
            } else {
                binding.editQuantidadeChocolate.setText("0")
                binding.precoChocolate.visibility = View.GONE
            }
        }

        //  Pão
        binding.checkPao.setOnClickListener {
            if (binding.checkPao.isChecked) {
                binding.editQuantidadePao.setText("1")
                binding.precoPao.visibility = View.VISIBLE
            } else {
                binding.editQuantidadePao.setText("0")
                binding.precoPao.visibility = View.GONE
            }
        }

        binding.buttonPedido.setOnClickListener {
            val i = Intent(this, SplashScreen::class.java)
            i.putExtra("quant_cafe", binding.editQuantidadeCafe.text.toString())
            i.putExtra("quant_pao", binding.editQuantidadePao.text.toString())
            i.putExtra("quant_chocolate", binding.editQuantidadeChocolate.text.toString())
            i.putExtra("preco_cafe", precoCafe)
            i.putExtra("preco_pao", precoPao)
            i.putExtra("preco_chocolate", precoChocolate)
            startActivity(i)
        }
    }
}
