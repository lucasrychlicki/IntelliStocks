package com.example.intellistocks.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intellistocks.R
import com.example.intellistocks.databinding.ActivityProdutosBinding
import com.example.intellistocks.model.Produto
import com.example.intellistocks.model.ProdutoRepository
import com.example.intellistocks.viewmodel.ProdutoViewModel
import com.example.intellistocks.viewmodel.ProdutoViewModelFactory

class ProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProdutosBinding
    private val produtoViewModel: ProdutoViewModel by viewModels {
        ProdutoViewModelFactory(ProdutoRepository(this))
    }
    private lateinit var produtoAdapter: ProdutoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.rvProdutos.layoutManager = LinearLayoutManager(this)

        produtoViewModel.produtos.observe(this) { produtos ->
            produtoAdapter = ProdutoAdapter(produtos, ::editarProduto, ::deletarProduto)
            binding.rvProdutos.adapter = produtoAdapter
        }

        produtoViewModel.carregarTodosProdutos()

    }

    private fun editarProduto(produto: Produto) {
        val intent = Intent(this, CadastrarProdutoActivity::class.java)
        intent.putExtra("produto_id", produto.id)
        intent.putExtra("produto_nome", produto.nome)
        intent.putExtra("produto_quantidade", produto.quantidade)
        intent.putExtra("produto_preco", produto.preco)
        startActivity(intent)
    }

    private fun deletarProduto(produto: Produto) {
        produtoViewModel.deletarProduto(produto.id)
        Toast.makeText(this, "Produto excluído", Toast.LENGTH_SHORT).show()
    }
}
