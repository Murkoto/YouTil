package com.murkoto.mathstringparser

import java.lang.Exception
import kotlin.math.pow

object MathStringParser {

    private const val ERR = "Err"

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

    fun parse(expression: String): String {
        return try {
            val res = parseAddition(expression).toDouble()
            if (res % 1.0 == 0.0) res.toInt().toString() else res.toString()
        } catch (e: Exception) {
            ERR
        }
    }

}