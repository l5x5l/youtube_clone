package com.example.youtube

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.ItemStorageContentListviewBinding

interface StorageCategoryItemTouchHelperListener{
    fun onItemMove(from_position : Int, to_position : Int) : Boolean
}

class StorageCategoryItemTouchHelperCallback(listener: StorageCategoryItemTouchHelperListener) : ItemTouchHelper.Callback(){

    private val listener = listener

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

}

class DataStorageCategoryAdapter(context: Context, dataList : ArrayList<DataStorageCategory>) : RecyclerView.Adapter<DataStorageCategoryAdapter.ViewHolder>(), StorageCategoryItemTouchHelperListener{

    private var dataList = dataList
    private lateinit var binding : ItemStorageContentListviewBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    class ViewHolder(binding : ItemStorageContentListviewBinding) : RecyclerView.ViewHolder(binding.root) {
        var mainLayout = binding.mainLayout
        var name = binding.tvCategory
        var icon = binding.icCategory
        var info = binding.tvMeta
        var premium = binding.icCheck
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemStorageContentListviewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = dataList[position].name
        holder.icon.setImageResource(dataList[position].icon)
        if (dataList[position].premium){
            holder.premium.setImageResource(R.drawable.ic_check_circle)
            holder.premium.visibility = View.VISIBLE
        }
        if (dataList[position].count > 0){
            holder.info.text = "동영상 " + dataList[position].count.toString() + "개"
            holder.info.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        var data = dataList[from_position]
        dataList.removeAt(from_position)
        dataList.add(to_position, data)
        notifyItemMoved(from_position, to_position)
        return true
    }
}