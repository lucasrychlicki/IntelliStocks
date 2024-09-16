package com.example.intellistocks.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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

    private val produtoViewModel: ProdutoViewModel by viewModels {
        ProdutoViewModelFactory(ProdutoRepository(this))
    }
    private lateinit var binding: ActivityProdutosBinding
    private lateinit var produtoAdapter: ProdutoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)


        produtoAdapter = ProdutoAdapter(
            onEditClick = { produto -> editarProduto(produto) },
            onDeleteClick = { produto -> deletarProduto(produto.id) }
        )

        // Configurar RecyclerView
        binding.rvProdutos.apply {
            layoutManager = LinearLayoutManager(this@ProdutosActivity)
            adapter = produtoAdapter
        }


        // Observar os produtos e atualizar a lista no adapter
        produtoViewModel.produtos.observe(this) { produtos ->
            Log.d("ProdutosActivity", "Produtos atualizados: ${produtos.size}")
            produtoAdapter.updateProdutos(produtos)
        }

        produtoViewModel.carregarTodosProdutos()
    }

    private fun editarProduto(produto: Produto) {
        val intent = Intent(this, CadastrarProdutoActivity::class.java)
        intent.putExtra("produto", produto)
        startActivity(intent)
    }

    private fun deletarProduto(produtoId: Int) {
        produtoViewModel.deletarProduto(produtoId)
        Toast.makeText(this, "Produto deletado com sucesso", Toast.LENGTH_SHORT).show()
    }


}


