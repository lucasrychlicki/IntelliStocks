package com.example.intellistocks.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

data class Endereco(
    val cep: String,
    val logradouro: String,
    val uf: String
)

interface ViaCepService {
    @GET("{cep}/json/")
    fun buscarEndereco(@Path("cep") cep: String): Call<Endereco>
}