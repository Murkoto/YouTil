package com.murkoto.youtil.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.murkoto.youtil.R
import com.murkoto.youtil.databinding.ItemMainMenuBinding
import com.murkoto.youtil.model.Menu

class MainMenuAdapter(val context: Context, val menus: MutableList<Menu>):
    RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemMainMenuBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_main_menu, parent, false)
        return MainMenuViewHolder(binding)
    }

    override fun getItemCount(): Int = menus.size

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        val menu = menus[position]
        holder.binding.menu = menu
    }

    class MainMenuViewHolder(val binding: ItemMainMenuBinding): RecyclerView.ViewHolder(binding.root)

}