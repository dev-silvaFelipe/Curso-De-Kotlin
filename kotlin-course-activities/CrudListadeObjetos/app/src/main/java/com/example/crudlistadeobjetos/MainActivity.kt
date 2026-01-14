package com.example.crudlistadeobjetos

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudlistadeobjetos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var pos = -1
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
        val listaUtilizadores = ArrayList<Utilizador>()
        listaUtilizadores.add(Utilizador("user", "pass"))
        listaUtilizadores.add(Utilizador("admin", "pwd123"))
        listaUtilizadores.add(Utilizador("aaa", "bbb"))

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUtilizadores)
        binding.listViewUtilizadores.adapter = adapter

        binding.listViewUtilizadores.setOnItemClickListener{ parent, view, position, id ->
            binding.editUsername.setText(listaUtilizadores.get(position).username)
            binding.editPassword.setText(listaUtilizadores.get(position).password)
            pos = position
        }
        binding.buttonInserir.setOnClickListener{
            val username = binding.editUsername.text.toString().trim()
            val password = binding.editPassword.text.toString().trim()

            if(!username.isEmpty() && !password.isEmpty()){
                listaUtilizadores.add(Utilizador(username, password))
                adapter.notifyDataSetChanged()
            }
            binding.editUsername.setText("")
            binding.editPassword.setText("")
        }

        binding.buttonEditar.setOnClickListener{
        if(pos>=0){
            val username = binding.editUsername.text.toString().trim()
            val password = binding.editUsername.text.toString().trim()
            if(!username.isEmpty() && !password.isEmpty()){
                listaUtilizadores.set(pos, Utilizador(username, password))
                binding.editUsername.setText("")
                binding.editPassword.setText("")
                adapter.notifyDataSetChanged()
                pos = -1
            }
        }
        }

        binding.buttonDeletar.setOnClickListener {
            if(pos>=0) {
                listaUtilizadores.removeAt(pos)
                binding.editUsername.setText("")
                binding.editPassword.setText("")
                adapter.notifyDataSetChanged()
                pos = -1
            }
        }
        binding.buttonLimpar.setOnClickListener{
            listaUtilizadores.clear()
            adapter.notifyDataSetChanged()
            binding.editUsername.setText("")
            binding.editPassword.setText("")
            pos=-1
        }
    }
}