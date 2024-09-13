package com.example.intellistocks.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PerfilViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val _usuarioNome = MutableLiveData<String>()
    val usuarioNome: LiveData<String> get() = _usuarioNome

    private val _usuarioEmail = MutableLiveData<String>()
    val usuarioEmail: LiveData<String> get() = _usuarioEmail

    private val _usuarioTelefone = MutableLiveData<String>()
    val usuarioTelefone: LiveData<String> get() = _usuarioTelefone

    init {
        carregarInfoUsuario()
    }

    // Método para salvar as informações do usuário no cadastro
    private fun salvarInfoUsuario(nome: String, email: String, telefone: String) {
        val editor = sharedPreferences.edit()
        editor.putString("nome", nome)
        editor.putString("email", email)
        editor.putString("telefone", telefone)
        editor.apply()

    }

    // Método para carregar as informações do usuário
    private fun carregarInfoUsuario() {
        _usuarioNome.value = sharedPreferences.getString("nome", "N/A")
        _usuarioEmail.value = sharedPreferences.getString("email", "N/A")
        _usuarioTelefone.value = sharedPreferences.getString("telefone", "N/A")
    }



}