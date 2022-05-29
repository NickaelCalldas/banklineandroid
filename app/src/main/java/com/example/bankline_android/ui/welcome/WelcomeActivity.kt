package com.example.bankline_android.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bankline_android.R
import com.example.bankline_android.databinding.ActivityWelcomeBinding
import com.example.bankline_android.domain.Correntista
import com.example.bankline_android.ui.statement.BankStatementActivity

class WelcomeActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityWelcomeBinding.inflate(layoutInflater);
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btContinue.setOnClickListener {
            val id = binding.etAccountHolderId.text.toString().toInt()
            val correntista = Correntista(id)
            val intent = Intent(this, BankStatementActivity::class.java).apply {
                putExtra(BankStatementActivity.EXTRA_CONTA,correntista)
            }
            startActivity(intent)
        }

    }
}