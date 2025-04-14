package indi.zeroornull.com.fullstackaction.kotlinlang.learn.operators_and_infix_expressions

class Book{
    infix fun on(any:Any?):Boolean{
        return false
    }
}

class DeskTop

fun main() {
    val book = Book()
    val deskTop = DeskTop()
    println(book.on(deskTop))// 中缀表达式 本质还是函数
    println(book on deskTop)
}
