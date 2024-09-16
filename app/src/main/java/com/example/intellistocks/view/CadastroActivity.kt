package com.example.intellistocks.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.intellistocks.databinding.ActivityCadastroBinding
import com.example.intellistocks.model.Usuario
import com.example.intellistocks.viewmodel.CadastroViewModel

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private val viewModel: CadastroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observa as mudanças no endereço
        viewModel.enderecoLiveData.observe(this) { endereco ->
            endereco?.let {
                binding.editLogradouroCadastro.setText(it.logradouro)
                binding.editEstadoCadastro.setText(it.uf)
            }
        }

        binding.btnCadastrar.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            val email = binding.editEmailCadastro.text.toString()
            val nome = binding.editNomeCadastro.text.toString()
            val senha = binding.editSenhaCadastro.text.toString()
            val telefone = binding.editTelefoneCadastro.text.toString()
            val cep = binding.editCepCadastro.text.toString()
            val logradouro = binding.editLogradouroCadastro.text.toString()
            val estado = binding.editEstadoCadastro.text.toString()

            // Criar objeto Usuario com os dados do cadastro
            val usuario = Usuario(
                email = email,
                nome = nome,
                senha = senha,
                telefone = telefone,
                cep = cep,
                logradouro = logradouro,
                estado = estado
            )

            viewModel.cadastro(usuario)
            Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show()
            startActivity(i)
            finish()
        }

        // Quando o campo de CEP perde o foco, busca o endereço
        binding.editCepCadastro.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val cep = binding.editCepCadastro.text.toString()
                if (cep.length == 8) {
                    viewModel.buscarEndereco(cep)
                }
            }
        }
    }
}