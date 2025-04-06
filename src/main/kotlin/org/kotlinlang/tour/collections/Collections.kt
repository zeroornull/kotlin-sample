package indi.zeroornull.org.kotlinlang.tour.collections

fun main() {
    val readOnlyShapes = listOf("triangle", "square", "circle")
    println(readOnlyShapes)

    val shapes: MutableList<String> = mutableListOf("triangle", "square", "circle")
    println(shapes)
    
    val shapesLocked:List<String> = shapes
    println(shapesLocked)
    
    println("The first item in the list is: ${readOnlyShapes[0]}")
    println("The first item in the list is: ${readOnlyShapes.first()}")
    println("The first item in the list is: ${readOnlyShapes.last()}")
    println("The first item in the list is: ${readOnlyShapes.count()} items")
    println("circle" in readOnlyShapes)
    shapes.add("pentagon")
    println(shapes)
    shapes.remove("pentagon")
    println(shapes)

}