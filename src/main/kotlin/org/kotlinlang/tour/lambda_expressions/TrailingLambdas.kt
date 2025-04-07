package indi.zeroornull.org.kotlinlang.tour.lambda_expressions

fun main() {
    println(listOf(1, 2, 3).fold(0, { x, item -> x + item }))
    println(listOf(1, 2, 3).fold(0) { x, item -> x + item })
}