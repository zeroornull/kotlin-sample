package indi.zeroornull.org.kotlinlang.tour.collections

fun main() {
    val readOnlyShapes = listOf("triangle", "square", "circle")
    println(readOnlyShapes)

    val shapes: MutableList<String> = mutableListOf("triangle", "square", "circle")
    println(shapes)
    
    val shapesLocked:List<String> = shapes
    println(shapesLocked)
}