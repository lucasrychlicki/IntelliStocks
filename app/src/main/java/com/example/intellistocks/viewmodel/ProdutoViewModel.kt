package com.example.intellistocks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intellistocks.model.Produto
import com.example.intellistocks.model.ProdutoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProdutoViewModel(private val repository: ProdutoRepository) : ViewModel() {

    private val _produtos = MutableLiveData<List<Produto>>()
    val produtos: LiveData<List<Produto>> get() = _produtos

    init {
        carregarTodosProdutos()
    }

    fun carregarTodosProdutos() {
        viewModelScope.launch(Dispatchers.IO) {
            val produtosList = repository.getAllProdutos()
            _produtos.postValue(produtosList)
        }
    }

    // Cadastrar um novo produto
    fun cadastrarProduto(produto: Produto) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProduto(produto)
            carregarTodosProdutos()
        }
    }

    // Atualizar um produto existente
    fun atualizarProduto(produto: Produto) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProduto(produto)
            carregarTodosProdutos()
        }
    }

    // Deletar um produto
    fun deletarProduto(produtoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProduto(produtoId)
            carregarTodosProdutos()
        }
    }


}