package com.example.retornodeextras

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.retornodeextras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var nome = ""
    private lateinit var result: ActivityResultLauncher<Intent>
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
        binding.buttonMudarNome.setOnClickListener{
            val i = Intent(this, MainActivity2::class.java)
            i.putExtra("nome", nome)
            //startActivity(i)
            result.launch(i)
        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.data != null && it.resultCode==1){
            nome=it.data?.getStringExtra("nome").toString()
            binding.textNome.text = "Olá $nome"

        }else if(it.data != null && it.resultCode==2){
            Toast.makeText(applicationContext, "Operação Cancelada", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(applicationContext, "Erro ao atualizar nome", Toast.LENGTH_SHORT).show()
        }
        }

    }
}