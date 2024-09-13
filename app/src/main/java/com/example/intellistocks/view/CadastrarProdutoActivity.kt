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
import com.example.intellistocks.R
import com.example.intellistocks.databinding.ActivityCadastrarProdutoBinding
import com.example.intellistocks.model.Produto
import com.example.intellistocks.model.ProdutoRepository
import com.example.intellistocks.viewmodel.CadastroViewModel
import com.example.intellistocks.viewmodel.ProdutoViewModel
import com.example.intellistocks.viewmodel.ProdutoViewModelFactory

class CadastrarProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastrarProdutoBinding
    private val produtoViewModel: ProdutoViewModel by viewModels {
        ProdutoViewModelFactory(ProdutoRepository(this))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrarProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSalvarProduto.setOnClickListener {
            cadastrarProduto()

        }


    }

    private fun cadastrarProduto() {

        val i = Intent(this, ProdutosActivity::class.java)
        val nomeProduto = binding.editNomeProduto.text.toString()
        val quantidadeProduto = binding.editQuantidadeProduto.text.toString().toIntOrNull()
        val precoProduto = binding.editPrecoProduto.text.toString().toDoubleOrNull()

        if (nomeProduto.isBlank() || quantidadeProduto == null || precoProduto == null) {
            Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
            return
        }

        val produto = Produto(
            nome = nomeProduto,
            quantidade = quantidadeProduto,
            preco = precoProduto
        )

        produtoViewModel.cadastrarProduto(produto)

        Toast.makeText(this, "Produto cadastrado com sucesso", Toast.LENGTH_SHORT).show()

        limparCampos()

        startActivity(i)
        finish()

    }

    private fun limparCampos() {
        binding.editNomeProduto.text?.clear()
        binding.editQuantidadeProduto.text?.clear()
        binding.editPrecoProduto.text?.clear()
    }
}