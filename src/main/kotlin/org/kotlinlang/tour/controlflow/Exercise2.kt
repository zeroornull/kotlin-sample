package indi.zeroornull.org.kotlinlang.tour.controlflow

fun main() {
    val button = "B"
    println(
        when(button){
            "A" -> "Yes"
            "B" -> "No"
            "X" -> "Menu"
            "Y" -> "nothing"
            else -> "There is no such button"
        }
    )
}