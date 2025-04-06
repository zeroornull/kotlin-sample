package indi.zeroornull.org.kotlinlang.tour.collections

fun main() {
    val readOnlyFruit = setOf("apple", "banana", "cherry", "cherry")
    val fruit:MutableSet<String> = mutableSetOf("apple", "banana", "cherry", "cherry")
    println(readOnlyFruit)
    val fruitLocked: Set<String> = fruit
    println("This set has ${readOnlyFruit.count()} items")
    fruit.add("dragonfruit")    // Add "dragonfruit" to the set
    println(fruit)              // [apple, banana, cherry, dragonfruit]

    fruit.remove("dragonfruit") // Remove "dragonfruit" from the set
    println(fruit)              // [apple, banana, cherry]
}