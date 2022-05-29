package com.example.bankline_android.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankline_android.R
import com.example.bankline_android.data.State
import com.example.bankline_android.databinding.ActivityBankStatementBinding
import com.example.bankline_android.databinding.ActivityWelcomeBinding
import com.example.bankline_android.domain.Correntista
import com.example.bankline_android.domain.Movimentacao
import com.example.bankline_android.domain.TipoMovimentacao
import com.google.android.material.snackbar.Snackbar
import me.dio.bankline.ui.adapters.BankStatementAdapter
import java.lang.IllegalArgumentException

class BankStatementActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_CONTA = "com.example.bankline_android.ui.statement.EXTRA_CONTA";
    }
    private val binding by lazy{
        ActivityBankStatementBinding.inflate(layoutInflater);
    }
    private val correntista by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_CONTA) ?: throw IllegalArgumentException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        binding.txtIdTeste.setText(correntista.id.toString());
        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)
        findBankStatement()
        binding.srlBankStatement.setOnRefreshListener { findBankStatement() }
    }
    private fun findBankStatement(){
        viewModel.findBankStatement(correntista.id).observe(this){ state->
            when(state){
                is State.Sucess ->{
                    binding.rvBankStatement.adapter = state.data?.let{ BankStatementAdapter(it)}
                    binding.srlBankStatement.isRefreshing = false
                }
                is State.Error -> {
                    state.message?.let { Snackbar.make(binding.rvBankStatement, state.message, Snackbar.LENGTH_LONG).show()}
                    binding.srlBankStatement.isRefreshing = false
                }
                State.Wait -> binding.srlBankStatement.isRefreshing = true
            }
        }

    }
    private val viewModel by viewModels<BankStatementViewModel>()
}