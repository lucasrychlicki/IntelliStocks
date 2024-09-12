package com.example.intellistocks.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.intellistocks.model.Usuario

class CadastroViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun cadastro(email: String, nome: String, senha: String, telefone: String) {
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("nome", nome)
        editor.putString("senha", senha)
        editor.putString("telefone", telefone)
        editor.apply()
    }
}