package com.murkoto.youtil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.murkoto.calculator.CalculatorActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gogo.setOnClickListener {
            startActivity(Intent(MainActivity@this, CalculatorActivity::class.java))
        }
    }
}