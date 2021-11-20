package com.dubizzle.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dubizzle.test.callback.Callback
import com.dubizzle.test.databinding.RowListBinding
import com.dubizzle.test.domain.model.IResults

class DataListAdapter(
    private val context: Context,
    private val items: ArrayList<IResults>,
    private val callback: Callback<IResults, RowListBinding>?
) :
    RecyclerView.Adapter<DataListAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val inflater = LayoutInflater.from(context)
        val binding = RowListBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class DataViewHolder(private val binding: RowListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                callback?.onItemClick(items[adapterPosition], binding)
            }
        }

        fun bind(results: IResults) {
            binding.result = results
            binding.executePendingBindings()
        }
    }

}

