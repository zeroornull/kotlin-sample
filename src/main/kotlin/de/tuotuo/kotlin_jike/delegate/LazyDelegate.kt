package indi.zeroornull.de.tuotuo.kotlin_jike.delegate

val data: String by lazy {
    request()
}

fun request(): String {
    println("执行网络请求")
    return "网络数据"
}

fun main() {
    println("start")
    println(data)
    println(data)
}