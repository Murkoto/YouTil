package com.murkoto.calculator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.murkoto.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalculatorBinding
    lateinit var mViewModel: CalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator)
        mViewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)
        binding.mViewModel = mViewModel
        binding.lifecycleOwner = this
    }
}
