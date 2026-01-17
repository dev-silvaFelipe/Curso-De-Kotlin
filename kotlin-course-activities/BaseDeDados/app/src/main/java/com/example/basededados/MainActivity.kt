package com.example.basededados

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.basededados.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<Utilizador>
    private lateinit var binding: ActivityMainBinding
    private var pos: Int = -1
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
        val db = DBhelper(this)

        val listaUtilizador = db.utilizadorListSelectAll()

        binding.listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUtilizador)
        binding.listView.setOnItemClickListener{_, _, position, _ ->
            binding.textId.setText("ID : ${listaUtilizador[position].id}")
            binding.textId.setText("ID : ${listaUtilizador[position].username}")
            binding.textId.setText("ID : ${listaUtilizador[position].password}")
            pos = position
        }
        binding.buttonInserir.setOnClickListener{
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()

            val res = db.utilizadorInsert(username, password)

            if(res>0){
                Toast.makeText(applicationContext, "Insert Ok: $res", Toast.LENGTH_SHORT).show()
                listaUtilizador.add(Utilizador(res.toInt(), username, password))
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(applicationContext, "Insert Erro: $res", Toast.LENGTH_SHORT).show()
            }

        }
        binding.buttonDeletar.setOnClickListener{
            if(pos>=0){
                val id = listaUtilizador[pos].id
                val res = db.utilizadorDelete(id)

                if( res > 0 ){
                    Toast.makeText(applicationContext, "Delete OK: $res", Toast.LENGTH_SHORT).show()
                   listaUtilizador.removeAt(pos)
                    adapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(applicationContext, "Delete Erro: $res", Toast.LENGTH_SHORT ).show()
                }}
        }
        binding.buttonUpdate.setOnClickListener{
            if(pos>=0){
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()
            val id = listaUtilizador[pos].id
            val res = db.utilizadorUpdate(id, username, password)

            if( res > 0 ){
                Toast.makeText(applicationContext, "Update OK: $res", Toast.LENGTH_SHORT).show()
                listaUtilizador[pos].username = username
                listaUtilizador[pos].password = password
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(applicationContext, "Update Erro: $res", Toast.LENGTH_SHORT ).show()
            }}
        }
    }

}