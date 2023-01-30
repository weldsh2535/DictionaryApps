package com.weldsh2535.DictionaryApps.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weldsh2535.DictionaryApps.Model.Dictionary
import com.weldsh2535.DictionaryApps.databinding.ItemListBinding

class SearchAdapter(
    private val directionality: ArrayList<Dictionary>,
    private val listener:(Dictionary,Int) -> Unit
) :RecyclerView.Adapter<SearchAdapter.ViewHolder>(){

    inner class ViewHolder(var itemListBinding: ItemListBinding)
        :RecyclerView.ViewHolder(itemListBinding.root){
        fun bindItem(dictionary: Dictionary){
            itemListBinding.tvGeez.text = dictionary.geez
            itemListBinding.tvAmharic.text = dictionary.amharic
            itemListBinding.image.setImageResource(dictionary.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
      return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(directionality[position])
        holder.itemView.setOnClickListener { listener(directionality[position],position) }
    }

    override fun getItemCount(): Int {
        return  directionality.size
    }
}