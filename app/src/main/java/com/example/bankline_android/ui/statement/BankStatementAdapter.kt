package me.dio.bankline.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankline_android.R
import com.example.bankline_android.databinding.ActivityBankStatementItemBinding
import com.example.bankline_android.domain.Movimentacao
import com.example.bankline_android.domain.TipoMovimentacao

/**
 * Reference: https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=pt-br#kotlin
 */
class BankStatementAdapter(private val dataSet: List<Movimentacao>) : RecyclerView.Adapter<BankStatementAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ActivityBankStatementItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movimentacao) = with(binding) {
            tvDescription.text = item.descricao
            tvValue.text = item.valor.toString()
            tvDatetime.text = item.dataHora
            val typeIcon = if (TipoMovimentacao.RECEITA == item.tipo) R.drawable.ic_money_in else R.drawable.ic_money_out
            ivIcon.setImageResource(typeIcon)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityBankStatementItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.bind(item)
    }

    override fun getItemCount() = dataSet.size
}