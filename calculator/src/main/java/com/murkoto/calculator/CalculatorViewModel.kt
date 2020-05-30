package com.murkoto.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {

    private val resultLiveData: MutableLiveData<String> = MutableLiveData()
    private var temp: Double = 0.0
    private var isInputing = false;
    private var lastOperand: Int = -1

    init {
        resultLiveData.postValue("0")
    }

    companion object {
        const val ADD = 0
        const val SUBTRACT = 1
        const val MULTIPLY = 2
        const val DIVIDE = 3
        const val EQUALS = 4
    }

    fun appendDigit(digit: String) {
        if (!isInputing) {
            temp = resultLiveData.value?.toDouble() ?: 0.0
            resultLiveData.postValue(digit)
            isInputing = true
        } else {
            val output = resultLiveData.value
            resultLiveData.postValue("$output$digit")
        }
    }

    fun resetResult() {
        resultLiveData.postValue("0")
        temp = 0.0
        isInputing = false
    }

    fun calculate(operand: Int) {
        var res = temp
        val input = resultLiveData.value?.toDouble() ?: 0.0
        when (operand) {
            ADD -> res += input
            SUBTRACT -> res -= input
            MULTIPLY -> res *= input
            DIVIDE -> res /= input
            EQUALS -> return calculate(lastOperand)
        }
        lastOperand = operand
        resultLiveData.postValue(res.toString())
        isInputing = false
    }

    fun getResult(): LiveData<String> = resultLiveData

}