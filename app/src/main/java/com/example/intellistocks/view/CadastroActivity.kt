package com.example.intellistocks.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.intellistocks.databinding.ActivityCadastroBinding
import com.example.intellistocks.viewmodel.CadastroViewModel

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private lateinit var viewModel: CadastroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(CadastroViewModel::class.java)

        binding.btnCadastrar.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            val email = binding.editEmailCadastro.text.toString()
            val nome = binding.editNomeCadastro.text.toString()
            val senha = binding.editSenhaCadastro.text.toString()
            val telefone = binding.editTelefoneCadastro.text.toString()

            viewModel.cadastro(email, nome, senha, telefone)
            Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show()
            startActivity(i)
        }

    }
}