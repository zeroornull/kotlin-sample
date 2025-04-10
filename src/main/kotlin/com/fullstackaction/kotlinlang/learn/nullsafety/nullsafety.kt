package indi.zeroornull.com.fullstackaction.kotlinlang.learn.nullsafety

class nullsafety {
}

fun getName(): String {
    return "xx"
}

fun main() {
    val name: String? = null
    println(name?.length)
    // Java 有三元运算符，而 Kotlin 没有，所以这里只能用 if-else。
    println(if (getName() !=null) getName() else "Default Name")
    // Kotlin 还提供了 Elvis 运算符（?:)，可以对 if-else 进行简化：
    //Elvis 运算符（?:)：如果 ?: 左侧表达式非空，elvis 操作符就返回其左侧表达式，否则返回右侧表达式。 请注意，当且仅当左侧为空时，才会对右侧表达式求值。
    println(getName() ?: "Default Name")
    //非空断言运算符（!!)：将任何值转换为非空类型，若该值为空则抛出异常。
    val value: String? = "Hello xx"
    println(value!!.length)
}