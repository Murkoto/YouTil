package com.murkoto.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception
import kotlin.math.pow

class CalculatorViewModel : ViewModel() {

    private val result: MutableLiveData<String> = MutableLiveData()
    private val input: MutableLiveData<String> = MutableLiveData()

    companion object {
        const val defaultVal = "0"
        const val DOT = "."
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

    private fun parsePower(expression: String): String {
        val splitExpression = expression.split("^")
        return splitExpression.reduce { acc, s -> acc.toDouble().pow(s.toDouble()).toString() }
    }

    private fun parseDivision(expression: String): String {
        val splitExpression = expression.split("/")
        val powParsedExpression = splitExpression.map { parsePower(it) }
        return powParsedExpression.reduce { acc, s -> (acc.toDouble() / s.toDouble()).toString() }
    }

    private fun parseMultiplication(expression: String): String {
        val splitExpression = expression.split("*")
        val parsedExpression = splitExpression.map { parseDivision(it) }
        val powParsedExpression = parsedExpression.map { parsePower(it) }
        return powParsedExpression.reduce { acc, s -> (acc.toDouble() * s.toDouble()).toString() }
    }

    private fun parseSubtraction(expression: String): String {
        val splitExpression = expression.split("-")
        Log.d("Log", splitExpression.toString())
        val parsedExpressions = splitExpression.map { parseMultiplication(it) }
        val powParsedExpression = parsedExpressions.map { parsePower(it) }
        return powParsedExpression.reduce { acc, s ->
            ((if (acc.isBlank()) "0" else acc).toDouble() - (if (s.isBlank()) "0" else s).toDouble()).toString()
        }
    }

    private fun parseAddition(expression: String): String {
        val splitExpression = expression.split("+")
        val parsedExpressions = splitExpression.map { parseSubtraction(it) }
        val powParsedExpression = parsedExpressions.map { parsePower(it) }
        return powParsedExpression.reduce { acc, s -> (acc.toDouble() + s.toDouble()).toString() }
    }

    fun reset() {
        input.value = ""
    }

    fun parse() {
        try {
            val resultString = parseAddition(input.value ?: "0").toDouble()
            result.value = if (resultString % 1.0 == 0.0) resultString.toInt().toString() else resultString.toString()
            input.value = ""
        } catch (e: Exception) {
            e.printStackTrace()
            result.value = ERR
            input.value = ""
        }
    }

}