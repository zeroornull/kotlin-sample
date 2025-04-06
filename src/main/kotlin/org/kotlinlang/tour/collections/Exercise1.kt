package indi.zeroornull.org.kotlinlang.tour.collections

fun main() {
    val greenNumbers = listOf(1,4,23)
    val redNumbers = listOf(17,2)
    //
    val totalCount = greenNumbers.count() + redNumbers.count()
    println(totalCount)
}