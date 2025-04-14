package indi.zeroornull.com.fullstackaction.kotlinlang.learn.branching_and_looping

fun main() {
    val x= 5
    when (x) {
        5 -> println("x is 5")
        is Int -> println("Hello $x")
        in 1..10 -> println("x is in 1..10")
        !in 10..100 -> println("x is not in 1..100")
//        args[0].toInt() -> println("x == args[0]")
    }
    var local = "en"
    val hero = when(local){
        "en" -> "Hero"
        "zh" -> "英雄"
        "fr" -> "Un héros"
        "de" -> "Held"
        else -> "未知"
    }
    println(hero)

//    与 Java 的 switch 相比，Kotlin 的 when 更加强大：
//    加强版 switch，支持任意类型
//            支持纯表达式条件分支（类似 if）
//    when表达式 要求完备性，需要将所有情况都列出来
    
}