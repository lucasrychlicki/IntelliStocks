package com.example.intellistocks.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.intellistocks.database.ProdutoDatabaseHelper

class ProdutoRepository(context: Context) {
    private val dbHelper = ProdutoDatabaseHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun insertProduto(produto: Produto): Long {
        val values = ContentValues().apply {
            put(ProdutoDatabaseHelper.COLUMN_NOME, produto.nome)
            put(ProdutoDatabaseHelper.COLUMN_QUANTIDADE, produto.quantidade)
            put(ProdutoDatabaseHelper.COLUMN_PRECO, produto.preco)
        }
        return db.insert(ProdutoDatabaseHelper.TABLE_NAME, null, values)
    }

    // Atualizar um produto existente
    fun updateProduto(produto: Produto): Int {
        val values = ContentValues().apply {
            put(ProdutoDatabaseHelper.COLUMN_NOME, produto.nome)
            put(ProdutoDatabaseHelper.COLUMN_QUANTIDADE, produto.quantidade)
            put(ProdutoDatabaseHelper.COLUMN_PRECO, produto.preco)
        }
        return db.update(
            ProdutoDatabaseHelper.TABLE_NAME,
            values,
            "${ProdutoDatabaseHelper.COLUMN_ID} = ?",
            arrayOf(produto.id.toString())
        )
    }

    // Deletar um produto
    fun deleteProduto(produtoId: Int): Int {
        return db.delete(
            ProdutoDatabaseHelper.TABLE_NAME,
            "${ProdutoDatabaseHelper.COLUMN_ID} = ?",
            arrayOf(produtoId.toString())
        )
    }

    // Retornar todos os produtos
    fun getAllProdutos(): List<Produto> {
        val produtos = mutableListOf<Produto>()
        val cursor: Cursor = db.query(
            ProdutoDatabaseHelper.TABLE_NAME,
            null, null, null, null, null, null
        )

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ProdutoDatabaseHelper.COLUMN_ID))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow(ProdutoDatabaseHelper.COLUMN_NOME))
                val quantidade = cursor.getInt(cursor.getColumnIndexOrThrow(ProdutoDatabaseHelper.COLUMN_QUANTIDADE))
                val preco = cursor.getDouble(cursor.getColumnIndexOrThrow(ProdutoDatabaseHelper.COLUMN_PRECO))
                produtos.add(Produto(id, nome, quantidade, preco))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return produtos
    }

}