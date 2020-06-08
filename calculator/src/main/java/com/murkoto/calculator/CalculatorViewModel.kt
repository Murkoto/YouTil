package com.murkoto.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.murkoto.mathstringparser.MathStringParser

class CalculatorViewModel : ViewModel() {

    private val result: MutableLiveData<String> = MutableLiveData()
    private val input: MutableLiveData<String> = MutableLiveData()

    companion object {
        const val ERR = "Err"
    }

    fun getResult(): LiveData<String> = result

    fun getInput(): LiveData<String> = input

    fun appendDigit(digit: String) {
        input.value = (input.value ?: "") + digit
    }

    fun putResult() {
        input.value = result.value
    }

    fun parse() {
        result.value = MathStringParser.parse(input.value ?: "0")
        input.value = ""
    }

}