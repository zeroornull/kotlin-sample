package indi.zeroornull.de.tuotuo.kotlin_jike.flow

fun List<Int>.quickSort(): List<Int> {
    if (size < 2) return this

    val pivot = this[size / 2]
    val equal = filter { it == pivot }
    val less = filter { it < pivot }
    val greater = filter { it > pivot }

    return less.quickSort() + equal + greater.quickSort()
}

fun main() {
    val unsortedList = listOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5)
    println("Unsorted: $unsortedList")
    println("Sorted: ${unsortedList.quickSort()}")
}
