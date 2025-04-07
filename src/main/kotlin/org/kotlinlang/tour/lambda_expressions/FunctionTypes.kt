package indi.zeroornull.org.kotlinlang.tour.lambda_expressions


val upperCaseString:(String)->String = {text->text.uppercase()}
fun main() {
    println(upperCaseString("hello"))
}