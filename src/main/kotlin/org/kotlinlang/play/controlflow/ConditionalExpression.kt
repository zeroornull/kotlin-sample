package indi.zeroornull.org.kotlinlang.play.controlflow
fun main() {
//    println("hello world")
    println(max(99, -42))
}

fun max(a: Int, b: Int) = if (a > b) a else b
