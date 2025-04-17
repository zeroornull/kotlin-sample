package indi.zeroornull.com.fullstackaction.kotlinlang.learn.higher_order_functions_and_function_references

/*
高阶函数
高阶函数是 参数或返回值是函数 的函数，我们之前已经接触过了几个高阶函数，如：
// _Arrays.kt
public inline fun <T> Array<out T>.forEach(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}
public inline fun <T> Array<out T>.filter(predicate: (T) -> Boolean): List<T> {
    return filterTo(ArrayList<T>(), predicate)
}

其中 forEach 要求传入一个函数类型为 (T) -> Unit 的函数作为参数（即带一个参数且无返回值），
而 filter 则是要求函数类型为 (T) -> Boolean 的函数作为参数（即带一个参数且返回值是 Boolean 类型）。

Kotlin 中，函数是第一公民。
 */

/*
函数类型
这里要搞明白什么是函数类型？我们经常会使用 String 、Int 等来描述一个变量的类型，这可以让我们很清楚的知道这个变量具体是个什么东西，而函数类型也一样，它明确描述了变量接收的是一个怎样的函数，函数类型的格式为：(参数1类型, 参数2类型, ..., 参数n类型)->返回值类型。

注意：当返回值类型为 Unit 时，这里的 Unit 不能省略，如：(String, String)->Unit。

举个例子：

val myFun1: (String) -> Unit = fun(value: String) { println(value) }
val myFun2: (String) -> Unit = { value -> println(value) }
上述代码中，类型为 (String) -> Unit 的常量 myFun1，接收了一个同 函数类型 的匿名函数，而 myFun2 则是接收了一个同 函数类型 的 lambda 表达式，两者其实没啥区别，功能也一样。

lambda 表达式也是一种匿名函数。
 */

/*
函数引用
参考对象引用这个概念，我们可以把函数引用简单理解为指向函数的变量，如：

val myFun1: (String) -> Unit = fun(value: String) { println(value) }
val myFun2: (String) -> Unit = { value -> println(value) }
这里的 myFun1 和 myFun2 都是函数的引用。这时回过头来看看 forEach 这个高阶函数，我们可以把函数类型为 (T) -> Unit 的函数引用传递给它，说到底不就是把一个变量作为参数传入而已嘛：


(T) -> Unit 中的 T 是泛型，此处 forEach 的调用者 args 的类型是 Array<String>，所以这里的实际函数类型是 (String) -> Unit，同理，如果 args 的类型是 Array<Int>，那么函数类型则应该是 (Int) -> Unit。

这里就有一个问题，如果不是匿名函数，而是具名函数，能否直接把函数名作为参数传入呢？

https://cdn.jsdelivr.net/gh/FullStackAction/PicBed@resource/image/20210202001405.png

很遗憾，这是不行的，函数名不是函数引用，函数名不是函数引用，函数名不是函数引用，
不过 Kotlin 提供了双冒号 :: 限定符，可以把函数名转成函数引用，因此代码可以这么写：

fun main(args: Array<String>) {
    val myFun: (String) -> Unit = { value -> println(value) }
    args.forEach(myFun)
}

这就不会报错了，如果你仔细观察，就能发现函数 println 本身就符合 (T) -> Unit 要求：

// Prints the given [message] and the line separator to the standard output stream.
@kotlin.internal.InlineOnly
public actual inline fun println(message: Any?) {
    System.out.println(message)
}

所以，如果仅仅只是为了打印字符串，那么我们完全可以把 myFun 这一层丢弃，而直接引用 prinln 函数：


args.forEach(::println)
引用成员方法
到目前为止，我们使用过的函数引用都是一些顶层、局部、扩展函数，而不是类成员方法，如果直接引用类成员方法会报错：
KFunction2<MyPrinter, String, Unit> 是什么东西？？？我们知道顶层、局部、扩展函数的调用并不依赖于对象实例，
而类成员方法则需要依赖对象实例，如果直接通过 类名::方法名 来引用一个成员方法，
那么该函数引用对应的类型就会在参数列表第 1 位多一个 Receiver 参数（用于接收方法依赖的对象实例），
其真实函数类型应该是 (MyPrinter, String) -> Unit，而通过 对象实例::方法名 则是 (String) -> Unit，
因此，通过对象实例引用成员方法可以解决该问题：

class MyPrinter{
    fun myFun(value: String){
        println(value)
    }
}

fun main(args: Array<String>) {
    val myPrinter = MyPrinter()
    args.forEach(myPrinter::myFun)
}


当然了，这不是说类中的方法就一定都是这样引用的，而是要考虑到方法的调用是否需要依赖于对象实例，
像 object 或者 companion object 中的方法，就可以像函数那样直接调用，因为不依赖于对象实例，
所以函数类型也不会多一个 Receiver 参数，因此可以直接通过 类名::方法名 来引用：

object MyPrinter {
    fun myFun(value: String) {
        println(value)
    }
}

class MyPrinter {
    companion object {
        fun myFun(value: String) {
            println(value)
        }
    }
}
fun main(args: Array<String>) {
    args.forEach(MyPrinter::myFun)
}

补充：方法可以认为是函数的一种特殊类型；从形式上，有 Receiver 的函数即为方法。

函数类型实例调用

函数引用可以通过其 invoke(……) 操作符调用：f.invoke(x) 或者 f(x)：

fun main(args: Array<String>) {
    val myFun1:(String)->Unit = fun(value:String){ println(value) }
    myFun1.invoke("xx")
    myFun1("xx")
}

再来看看类成员方法引用的情况，前面说过，直接通过 类名::方法名 来引用一个成员方法，
那么该函数引用对应的函数类型就会在参数列表第 1 位多一个 Receiver 参数，
就比如这个 String 类中的 plus()方法，它的 Receiver 就是一个 String 对象实例：

fun main() {
    val opear: (String, String) -> String = String::plus
    val result = opear("hello", "world")// 等价于 "hello".plus(" world")
    println(result) // hello world
}

Receiver 除了可以作为函数类型的首个参数，还可以将其提取出来，通过 . 与原始函数类型连接，同时，调用方法也会因此发生一些变化，类似扩展函数：

fun main() {
    val opear: String.(String) -> String = String::plus
    val result = "hello".opear(" haha")// 等价于 "hello".plus(" world")
    println(result)
}


 */

