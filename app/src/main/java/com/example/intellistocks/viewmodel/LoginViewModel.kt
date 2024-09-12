package com.example.intellistocks.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun login(emailOuNome: String, senha: String): Boolean {
        val emailSalvo = sharedPreferences.getString("email", null)
        val nomeSalvo = sharedPreferences.getString("nome", null)
        val senhaSalva = sharedPreferences.getString("senha", null)

        return (emailOuNome == emailSalvo || emailOuNome == nomeSalvo) && senha == senhaSalva
    }

}