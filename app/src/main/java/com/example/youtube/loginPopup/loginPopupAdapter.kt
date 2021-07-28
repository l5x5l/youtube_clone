package com.example.youtube.loginPopup

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.DataBottomSheetOption
import com.example.youtube.databinding.ItemBottomSheetBinding

class loginPopupAdapter(context: Context, private val dataList : ArrayList<DataBottomSheetOption>) : RecyclerView.Adapter<loginPopupAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemBottomSheetBinding

    class ViewHolder(private val binding : ItemBottomSheetBinding) : RecyclerView.ViewHolder(binding.root) {
        var text = binding.text
        var icon = binding.icon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemBottomSheetBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = dataList[position].text
        holder.icon.setImageResource(dataList[position].icon)
    }

    override fun getItemCount(): Int = dataList.size
}