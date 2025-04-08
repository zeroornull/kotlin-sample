package indi.zeroornull.org.kotlinlang.tour.nullsafety

fun lengthString(maybeString: String?): Int? = maybeString?.length
fun main() {
    val nullString: String? = null
    println(lengthString(nullString))
    //null
}