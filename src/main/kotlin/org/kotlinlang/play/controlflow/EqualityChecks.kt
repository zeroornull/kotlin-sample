package indi.zeroornull.org.kotlinlang.play.controlflow

fun main() {
    val authors = setOf("Shakespeare", "Hemingway", "Twain")
    val writers = setOf("Twain", "Shakespeare", "Hemingway")
    println(authors==writers)
    println(authors===writers)
}