package indi.zeroornull.com.fullstackaction.kotlinlang.learn.functional_programming

/*
函数式编程
什么是函数式编程，以下是维基百科给出的答案：

函数式编程（英语：functional programming）或称函数程序设计、泛函编程，是一种编程范式，
它将电脑运算视为函数运算，并且避免使用程序状态以及易变对象。其中，λ 演算（lambda calculus）为该语言最重要的基础。
而且，λ 演算的函数可以接受函数当作输入（引数）和输出（传出值）。比起指令式编程，函数式编程更加强调程序执行的结果而非执行的过程，
倡导利用若干简单的执行单元让计算结果不断渐进，逐层推导复杂的运算，而不是设计一个复杂的执行过程。

这种文趋趋的概念，了解过后有个印象就好了，下面这句话才重要：

在函数式编程中，函数是第一类对象，意思是说一个函数，既可以作为其它函数的参数（输入值），也可以从函数中返回（输入值），被修改或者被分配给一个变量。

这不就是高阶函数的定义吗？是的，不过，函数式编程是一种编程范式，而高阶函数是函数式编程的基本技术，
换句话说：函数式编程包括但不限于高阶函数这一种技术，还有闭包、柯里化、偏函数等等。

闭包就是能够读取其他函数内部变量的函数。对于支持函数式编程的语言来说，可以在一个函数 A 内部再定义一个函数 B，
函数 B 可以访问函数 A 中的成员，通常函数 A 的返回结果就是函数 B，并且闭包会延长函数 A 的生命周期（函数 A 中的成员不会被回收）。

个人理解：闭包中两个函数的关系，跟内部类与外部类的关系很像，不过闭包较为轻量。

我们可以使用闭包来实现斐波那契数列，斐波那契数列的核心要点就是缓存前面的结果，供后续计算使用，使用闭包正合适：

fun fib(): () -> Long {
    var prev = 0L
    var current = 1L
    return fun(): Long {
        val result = current
        current += prev
        prev = result
        return result
    }
}
fun main() {
    val fib = fib() // fib是fib()内的匿名函数
    println(fib())// 1
    println(fib()) // 1
    println(fib()) // 2
    println(fib()) // 3
    println(fib()) // 5
    println(fib()) // 8
}

可以看到，每次调用这个 fib 匿名函数，出来的结果是不一样的，说明闭包确实可以让内部函数持有外部函数的成员，并且延长了外部函数作用域中变量的生命周期。
 */

/*
柯里化
什么是柯里化？贴一个维基百科对柯里化的定义：

在计算机科学中，柯里化（英语：Currying），又译为卡瑞化或加里化，是把接受多个参数的函数变换成接受一个单一参数（最初函数的第一个参数）的函数，并且返回接受余下的参数而且返回结果的新函数的技术。

假设有一个求和函数 add，它接收多个参数，满足函数柯里化的前提：
 */

/*fun add(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun main() {
    val result = add(1, 2)
    println(result)
}*/

/*
我们如何把上述函数 add 进行柯里化呢？根据柯里化的定义，我们需要把函数 add 进行两处修改：

参数列表：把接收多个参数转变成接收单个参数。
返回值：返回接收余下参数和结果的函数。
 */

/*fun add(num1: Int): (Int) -> Int {
    return fun(num2: Int): Int {
        return num1 + num2
    }
}

fun main() {
    val result = add(1)(2)
    println(result)
}*/

/*
可以看到函数 add 的调用形式发现了变化，从 add(1, 2) 变换成了 add(1)(2)，这就是柯里化。
另外，因为 Kotlin 支持智能类型推导，所以柯里化函数 add 还可以进行简化：
 */

/*fun add(num1: Int) = fun(num2: Int) = num1 + num2*/

/*
这变化也太神奇了吧，简化后的柯里化函数 add 让人眼前一亮。同样的，如果参数个数为 3，4，..n 个，对于 Kotlin 来说，柯里化他们也就是一把梭的事
 */

/*
偏函数
偏函数（Partial function）就是把一个函数的某些参数给固定住（也就是设置默认值），返回一个新的函数，调用这个新函数会更简单。

注意：偏函数与函数默认参数很像，但两者是有区别的。默认参数是在原函数的基础上给某些参数指定默认值，调用的时候还是原来那个函数，而偏函数一般情况下则是通过某种手段生成的新函数，后续调用的是这个新的函数。

举个例子，有一个可以构建不同编码字符串的函数 buildString：
 */

fun buildString(byteArray: ByteArray, charsetName: String): String {
    return String(byteArray, charset(charsetName))
}

fun main() {
    val result = buildString("xxx".toByteArray(), "UTF-8")
    println(result)
}