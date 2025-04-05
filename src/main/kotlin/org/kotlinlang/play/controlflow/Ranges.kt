package indi.zeroornull.org.kotlinlang.play.controlflow

fun main() {
    for (i in 0..3) {
        print(i);
    }
    print("  ")
    for (i in 0 until 3) {
        print(i)
    }
    print("  ")
    for (i in 2..8 step 2) {
        print(i)
    }
    print("  ")
    for (i in 3 downTo 0) {
        print(i)
    }
    print("  ")
    
    val x = 2
    if (x in 1..5){
        print("x is in the range from 1 to 5")
    }
    println()
    if (x !in 6..10){
        print("x is not in range from 6 to 10")
    }
}