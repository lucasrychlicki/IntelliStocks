package com.example.intellistocks.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intellistocks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabPerfil.setOnClickListener {
            val i = Intent(this, PerfilActivity::class.java)
            startActivity(i)
        }

        binding.btnCadastrarProduto.setOnClickListener {
            val i = Intent(this, CadastrarProdutoActivity::class.java)
            startActivity(i)
        }

        binding.btnVisualizarProdutos.setOnClickListener {
            val i = Intent(this, ProdutosActivity::class.java)
            startActivity(i)
        }

    }
}