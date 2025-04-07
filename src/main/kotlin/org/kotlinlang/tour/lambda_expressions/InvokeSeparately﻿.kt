package indi.zeroornull.org.kotlinlang.tour.lambda_expressions

fun main() {
    println({text:String->text.uppercase()}("hello"))
}