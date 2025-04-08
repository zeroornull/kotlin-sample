package indi.zeroornull.org.kotlinlang.tour.nullsafety

fun main() {
    val nullString: String? = null
    println(nullString?.length ?: 0)
}