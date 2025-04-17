package indi.zeroornull.com.fullstackaction.kotlinlang.learn.extended_members

//扩展方法
//Kotlin 中支持在不修改类原本结构的前提下，对类功能进行扩展，比如对 String 类新增一个 multiply()方法，可返回重复 n 次的字符串：

//fun String.multiply(times: Int): String {
//    val stringBuilder = StringBuilder()
//    for (i in 0 until times) stringBuilder.append(this)
//    return stringBuilder.toString()
//}

// 可以看到扩展方法的格式其实很简单，跟普通的函数差不多，只不过在函数名前面多了 要扩展的类名 和一个 . 而已，
// 扩展方法的方法体中，可以使用 this 得到原本的对象。
// 扩展成员的相关代码外层不需要包裹诸如 class 的代码块，可直接存放在任意 kt 文件中，且在其它 kt 文件中可以直接调用。

//扩展运算符函数
//在 Python 中，字符串若是乘上整数 n，将返回由 n 个此字符串拼接起来的新字符串。这个语法特性我是挺喜欢的，
// 那么 Kotlin 中是否也可以做到像 Python 那样呢？答案是可以的，类似 "hehe" * 3 这种写法，
// 结合之前所学的运算符重载知识，这个问题其实本质上就是对 String 类中的 *运行符函数 times() 进行重载即可，
// 但 String 类我们无法直接修改其源码，同时由于 Kotlin 支持扩展类成员的特性，因此我们可以对运算符函数进行如下扩展：
//
//了解更多操作符对应的具名函数，请点击：https://www.kotlincn.net/docs/reference/operator-overloading.html

operator fun String.times(time: Int): String {
    val stringBuilder = StringBuilder()
    for (i in 0 until time) stringBuilder.append(this)
    return stringBuilder.toString()
}

//扩展运算符函数 跟扩展方法的格式是一样的，先书写要重载的运算符函数，然后在函数名前面增加 类名 和 . 即可。
//扩展属性
//Kotlin 不仅支持扩展类方法，也支持扩展类属性，例如 String 原本只有 length 属性，我们可以扩展一个 size 属性，功能与 length 属性一样：

//val String.size: Int
//    get() {
//        return this.length
//    }
// 注意，扩展属性如果使用 val 修饰，则只有 get() 方法，如果使用 var 修饰，则可以有 set() 方法，
// 但因为扩展属性没有 backing field，即无法使用 field = value 这类代码，因此，这个 set() 方法其实形同虚设。

var String.size:Int
    get(){
        return this.length
    }
    set(value){
        // field = value // IDE报错：Unresolved reference: field
    }

//Java 调用 Kotlin 扩展成员

//Java 不认识 Kotlin 的语法糖，所以没办法像 Kotlin 代码那样使用原对象直接调用扩展成员，而是类似调用静态方法的方式：
//
//String str = "hehe";
//System.out.println(ExtendExampleKt.multiply(str, 4)); // hehehehehehehehe
//System.out.println(ExtendExampleKt.times(str, 5)); // hehehehehehehehehehe
//System.out.println(ExtendExampleKt.getSize(str)); // 4

//Kotlin 的扩展成员在 Java 中会被识别成 类名为 kt文件名＋Kt 组成的类静态方法，附上下图方便理解：


fun main() {
//    println("hehe".multiply(3))
//    println("hehe" * 4)
    println("hehe".size)
}