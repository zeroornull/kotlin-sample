package indi.zeroornull.org.kotlinlang.play.controlflow

fun eatACake() = println("Eat a Cake")
fun bakeACake() = println("Bake a Cake")


fun main() {
    val cakes = listOf("carrot", "cheese", "chocolate")
    for (cake in cakes) {
        println("Yummy, it's a $cake cake!")
    }

    var cakesEaten = 0
    var cakesBaked = 0
    while (cakesEaten < 5) {
        eatACake()
        cakesEaten++
    }
    do {
        bakeACake()
        cakesBaked++
    } while (cakesEaten < cakesEaten)

}