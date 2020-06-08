package com.murkoto.youtil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.murkoto.youtil.adapter.MainMenuAdapter
import com.murkoto.youtil.databinding.ActivityMainBinding
import com.murkoto.youtil.model.Menu

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var menuAdapter: MainMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        menuAdapter = MainMenuAdapter(this, mutableListOf())
        binding.menuAdapter = menuAdapter
        menuAdapter.menus.add(Menu("Calculator", R.drawable.ic_calculator, ":calculator"))
        menuAdapter.menus.add(Menu("Calculator", R.drawable.ic_calculator, ":calculator"))
        menuAdapter.menus.add(Menu("Calculator", R.drawable.ic_calculator, ":calculator"))
        menuAdapter.menus.add(Menu("Calculator", R.drawable.ic_calculator, ":calculator"))
        menuAdapter.menus.add(Menu("Calculator", R.drawable.ic_calculator, ":calculator"))
        menuAdapter.notifyDataSetChanged()
    }
}