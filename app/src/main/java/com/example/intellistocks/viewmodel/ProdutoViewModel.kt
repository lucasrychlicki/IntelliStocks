package com.example.intellistocks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.intellistocks.model.Produto
import com.example.intellistocks.model.ProdutoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProdutoViewModel(private val repository: ProdutoRepository) : ViewModel() {

    private val _produtos = MutableLiveData<List<Produto>>()
    val produtos: LiveData<List<Produto>> get() = _produtos

    fun carregarTodosProdutos() {
        CoroutineScope(Dispatchers.IO).launch {
            _produtos.postValue(repository.getAllProdutos())
        }
    }

    fun cadastrarProduto(produto: Produto) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertProduto(produto)
            carregarTodosProdutos()
        }
    }

    fun atualizarProduto(produto: Produto) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateProduto(produto)
            carregarTodosProdutos()
        }
    }

    fun deletarProduto(produtoId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteProduto(produtoId)
            carregarTodosProdutos()
        }
    }


}