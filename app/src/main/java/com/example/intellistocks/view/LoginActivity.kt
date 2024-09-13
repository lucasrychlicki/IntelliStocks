package com.example.intellistocks.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.intellistocks.databinding.ActivityLoginBinding
import com.example.intellistocks.viewmodel.LoginViewModel
import androidx.lifecycle.ViewModelProvider


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val emailOuNome = binding.editEmailOuNome.text.toString()
            val senha = binding.editSenha.text.toString()
            val i = Intent(this, MainActivity::class.java)

            if(viewModel.login(emailOuNome, senha)) {
                Toast.makeText(
                    this,
                    "Login efetuado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(i)
                finish()
            }else{
                Toast.makeText(
                    this,
                    "Falha no login. Verifique suas credenciais!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.textCadastroLink.setOnClickListener {
            val i = Intent(this, CadastroActivity::class.java)
            startActivity(i)
            finish()
        }



    }
}