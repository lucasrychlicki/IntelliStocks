package com.example.intellistocks.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.intellistocks.api.Endereco
import com.example.intellistocks.api.RetrofitInstance
import com.example.intellistocks.model.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroViewModel(application: Application) : AndroidViewModel(application) {

    val enderecoLiveData = MutableLiveData<Endereco>()

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun cadastro(usuario: Usuario) {
        val editor = sharedPreferences.edit()
        editor.putString("email", usuario.email)
        editor.putString("nome", usuario.nome)
        editor.putString("senha", usuario.senha)
        editor.putString("telefone", usuario.telefone)
        editor.putString("cep", usuario.cep)
        editor.putString("logradouro", usuario.logradouro)
        editor.putString("estado", usuario.estado)
        editor.apply()
    }

    fun getUsuario(): Usuario? {
        val email = sharedPreferences.getString("email", null)
        val nome = sharedPreferences.getString("nome", null)
        val senha = sharedPreferences.getString("senha", null)
        val telefone = sharedPreferences.getString("telefone", null)
        val cep = sharedPreferences.getString("cep", null)
        val logradouro = sharedPreferences.getString("logradouro", null)
        val estado = sharedPreferences.getString("estado", null)

        return if (email != null && nome != null && senha != null && telefone != null && cep != null && logradouro != null && estado != null) {
            Usuario(email, nome, senha, telefone, cep, logradouro, estado)
        } else {
            null
        }
    }

    fun buscarEndereco(cep: String) {
        RetrofitInstance.api.buscarEndereco(cep).enqueue(object : Callback<Endereco> {
            override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                if (response.isSuccessful) {
                    enderecoLiveData.value = response.body()
                } else {
                    Toast.makeText(
                        getApplication(),
                        "Erro ao buscar o endereço",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Endereco>, t: Throwable) {
                Toast.makeText(getApplication(), "Falha na conexão", Toast.LENGTH_SHORT).show()
            }
        })
    }


}