package indi.zeroornull.org.kotlinlang.tour.controlflow.loop

fun main() {
    for (number in 1..100) {
        println(
            when {
                number % 15 == 0 -> "fizzbuzz"
                number % 5 == 0 -> "fizz"
                number % 3 == 0 -> "buzz"
                else -> "$number"
            }
        )
    }
}