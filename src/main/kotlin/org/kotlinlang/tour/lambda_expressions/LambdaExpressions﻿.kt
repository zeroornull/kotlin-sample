package indi.zeroornull.org.kotlinlang.tour.lambda_expressions

fun uppercaseString(text: String):String{
    return text.uppercase()
}
fun main() {
    println(uppercaseString("hello"))
    val uppercaseString = { text: String -> text.uppercase() }
    println(uppercaseString("fuck"))
}