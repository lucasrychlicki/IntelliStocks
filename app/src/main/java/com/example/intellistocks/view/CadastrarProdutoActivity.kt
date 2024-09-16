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

    private var produtoExistente: Produto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrarProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        produtoExistente = intent.getParcelableExtra("produto")
        produtoExistente?.let {
            binding.editNomeProduto.setText(it.nome)
            binding.editQuantidadeProduto.setText(it.quantidade.toString())
            binding.editPrecoProduto.setText(it.preco.toString())

            binding.btnSalvarProduto.text = "Atualizar Produto"
        }

        /*binding.btnSalvarProduto.setOnClickListener {
            salvarProduto()
        }*/

        binding.btnSalvarProduto.setOnClickListener {
            val nome = binding.editNomeProduto.text.toString()
            val quantidade = binding.editQuantidadeProduto.text.toString().toIntOrNull() ?: 0
            val preco = binding.editPrecoProduto.text.toString().toDoubleOrNull() ?: 0.0


            if (produtoExistente != null) {
                val i = Intent(this, MainActivity::class.java)
                val produtoAtualizado = produtoExistente!!.copy(
                    nome = nome,
                    quantidade = quantidade,
                    preco = preco
                )
                produtoViewModel.atualizarProduto(produtoAtualizado)
                startActivity(i)
                Toast.makeText(this, "Produto atualizado com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                val novoProduto = Produto(0, nome, quantidade, preco)
                produtoViewModel.cadastrarProduto(novoProduto)
                Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
            }

            finish()
        }



    }

    private fun carregarDadosDoProduto() {
        binding.editNomeProduto.setText(intent.getStringExtra("produto_nome"))
        binding.editQuantidadeProduto.setText(intent.getIntExtra("produto_quantidade", 0).toString())
        binding.editPrecoProduto.setText(intent.getDoubleExtra("produto_preco", 0.0).toString())
    }

    private fun salvarProduto() {
        val nome = binding.editNomeProduto.text.toString().trim()
        val quantidade = binding.editQuantidadeProduto.text.toString().toIntOrNull() ?: 0
        val preco = binding.editPrecoProduto.text.toString().toDoubleOrNull() ?: 0.0

        if (nome.isNotEmpty() && quantidade > 0 && preco > 0) {
            val produto = Produto(0, nome, quantidade, preco)
            produtoViewModel.cadastrarProduto(produto)
            Toast.makeText(this, "Produto cadastrado com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
        }
    }


    private fun limparCampos() {
        binding.editNomeProduto.text?.clear()
        binding.editQuantidadeProduto.text?.clear()
        binding.editPrecoProduto.text?.clear()
    }
}