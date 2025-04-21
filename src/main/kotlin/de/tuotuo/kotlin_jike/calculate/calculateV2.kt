package indi.zeroornull.de.tuotuo.kotlin_jike.calculate

import indi.zeroornull.com.fullstackaction.kotlinlang.learn.extended_members.times
import kotlin.system.exitProcess

class CalculatorV2 {
    fun start() {}

    fun calculate(input: String): Int? {
        return try {
            input.toInt() // 尝试将输入字符串转换为整数
        } catch (e: NumberFormatException) {
            null // 如果转换失败，返回 null
        }
    }
}

fun main() {
    val calculator = CalculatorV2()
    calculator.start()
}

data class Expression(
    val left: String,
    val operator: Operation,
    val right: String
)

fun start() {
    while (true) {
        println(help)
        val input = readLine() ?: continue
        val result = calculate(input)
        if (result == null) {
            println("输入格式不对")
            continue
        } else {
            println("$input = $result")
        }
    }
}

fun calculate(input: String): String? {
    if (shouldExit(input)) exitProcess(0)
    val exp = parseExpression(input) ?: return null
    val left = exp.left
    val operator = exp.operator
    val right = exp.right
    return when (operator) {
        Operation.ADD -> addString(left, right)
        Operation.MINUS -> minusString(left, right)
        Operation.MULTI -> multiString(left, right)
        Operation.DIVI -> diviString(left, right)
    }
}

fun addString(left: String, right: String): String {
    val result = left.toInt() + right.toInt()
    return result.toString()
}

fun minusString(left: String, right: String): String {
    val result = left.toInt() - right.toInt()
    return result.toString()
}

fun multiString(left: String, right: String): String {
    val result = left.toInt() * right.toInt()
    return result.toString()
}

fun diviString(left: String, right: String): String {
    val result = left.toInt() / right.toInt()
    return result.toString()
}

fun shouldExit(input: String): Boolean {
    return input == "exit"
}

fun parseOperator(input: String): Operation? {
    Operation.values().forEach {
        if (input.contains(it.value)) {
            return it
        }
    }
    return null
}

fun parseExpression(input: String): Expression? {
    // 解析操作符
    val operation = parseOperator(input) ?: return null
    // 用操作符分割算式，拿到数字
    val list = input.split(operation.value)
    if (list.size != 2) return null
    return Expression(
        //
        left = list[0].trim(),
        operator = operation,
        //
        right = list[2].trim()
    )
}
