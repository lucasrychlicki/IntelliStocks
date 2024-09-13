package com.example.intellistocks.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.intellistocks.databinding.ItemProdutosBinding
import com.example.intellistocks.model.Produto

class ProdutoAdapter(
    private val produtos: List<Produto>,
    private val onEditClick: (Produto) -> Unit,
    private val onDeleteClick: (Produto) -> Unit
) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>(){

    inner class ProdutoViewHolder(val binding: ItemProdutosBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(produto: Produto) {
            binding.textNomeProduto.text = produto.nome
            binding.textQuantidade.text = produto.quantidade.toString()
            binding.textPrecoProduto.text = produto.preco.toString()

            binding.btnEditarProduto.setOnClickListener { onEditClick(produto) }
            binding.btnDeletarProduto.setOnClickListener { onDeleteClick(produto) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val binding = ItemProdutosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProdutoViewHolder(binding)
    }

    override fun getItemCount(): Int = produtos.size


    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.bind(produtos[position])
    }


}