package com.example.intellistocks.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.intellistocks.databinding.ActivityPerfilBinding
import com.example.intellistocks.viewmodel.PerfilViewModel

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding
    private lateinit var viewModel: PerfilViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )
            .get(PerfilViewModel::class.java)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.usuarioNome.observe(this) { nome ->
            binding.textUsuarioNome.text = nome
        }

        viewModel.usuarioEmail.observe(this) { email ->
            binding.textUsuarioEmail.text = email
        }

        viewModel.usuarioTelefone.observe(this) { telefone ->
            binding.textUsuarioTelefone.text = telefone
        }
    }

}