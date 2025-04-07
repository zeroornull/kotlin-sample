package indi.zeroornull.org.kotlinlang.tour.controlflow.loop

fun main() {
    val words = listOf("dinosaur", "limousine", "magazine", "language")
    for (word in words) {
        if (word.startsWith("l")){
            println(word);
        }
    }
}