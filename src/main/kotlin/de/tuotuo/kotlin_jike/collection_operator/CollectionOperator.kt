package indi.zeroornull.de.tuotuo.kotlin_jike.collection_operator

import indi.zeroornull.org.kotlinlang.play.controlflow.MyClass1

data class Student(
    val name: String = "", val score: Int = 0
)

val class1 = listOf(
    Student("小明", 83),
    Student("小红", 92),
    Student("小李", 50),
    Student("小白", 67),
    Student("小琳", 72),
    Student("小刚", 97),
    Student("小强", 57),
    Student("小林", 86)
)

val class2 = listOf(
    Student("大明", 80),
    Student("大红", 97),
    Student("大李", 53),
    Student("大白", 64),
    Student("大琳", 76),
    Student("大刚", 92),
    Student("大强", 58),
    Student("大林", 88)
)

private fun filterNotPass() {
    val result = class1.filter { it.score < 60 }
    println(result)
}

private fun filterAllNotPass() {
    var result = listOf(class1, class2).flatten().filter { it.score < 60 }
    println(result)
}

private fun groupStudent() {
    val result = class1.groupBy { "${it.score / 10}0分组" }
    println(result)
}

private fun takeStudent() {
    val first3 = class1.sortedByDescending { it.score }.take(3)

    val last3 = class1.sortedByDescending { it.score }.takeLast(3)

    println(first3)
    println(last3)
}

private fun dropStudent() {
    val middle = class1.sortedByDescending { it.score }.drop(3).dropLast(3)

    println(middle)
}

private fun sliceStudent() {
    val first3 = class1.sortedByDescending { it.score }.slice(0..2)
    val last3 = class1.sortedByDescending { it.score }.slice(class1.size - 3 until class1.size)

    println(first3)
    println(last3)

}

private fun sumScore() {
    val sum1 = class1.sumOf { it.score }
    val sum2 = class1.map { it.score }.reduce { acc, score -> acc + score }
    val sum3 = class1.map { it.score }.fold(0) { acc, score -> acc + score }
    println(sum1)
    println(sum2)
    println(sum3)
}


fun main() {
//    filterNotPass()
//    filterAllNotPass()
//    groupStudent()
//    takeStudent()
//    dropStudent()
//    sliceStudent()
    sumScore()
}