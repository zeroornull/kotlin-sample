package indi.zeroornull.com.fullstackaction.kotlinlang.learn.internal_class

/*
内部类
内部类就是定义在类内部的类，Kotlin 中的内部类大致分为 2 种：

静态内部类
非静态内部类
#静态内部类
在某个类中像普通类一样声明即可，可以认为静态内部类与外部类没有关系，只是定义在了外部类"体内"而已，在使用静态内部类时需要"带上"外部类：
注意，在 Java 中，这种写法就是在定义 非静态内部类，而 静态内部类 需要在声明时使用 static 修饰。
*/
/*
class Outer{
    val a:Int = 0
    
    class Inner{
        val a: Int = 5
    }
    
}

fun main() {
    val outer = Outer()
    println(outer.a)
    val inner = Outer.Inner()
    println(inner.a)
}

*/

/*
非静态内部类
非静态内部类与静态内部类有的区别有：

非静态内部类 会持有外部类的引用，而 静态内部类 不会(可以认为两者没有关系)。
非静态内部类 使用时需要基于外部类对象（Outer().Inner()），而 静态内部类 则是基于外部类（Outer.Inner()）。
因为 非静态内部类 会持有外部类的引用，所以内部类可以直接使用外部类成员；当非静态内部类与外部类存在同名成员时，可以使用 @标记 来解决歧义：
*/

/*class Outer{
    val b:Int = 3
    val a:Int = 5
    inner class Inner{
        val a:Int = 5
        fun test(){
            println("Outer b = $b") // 3，因为持有外部类的引用，所以直接使用外部类成员
            println("Outer a = ${this@Outer.a}") // 0，使用 @Outer 指定this是外部类对象
            println("Inner a = ${this@Inner.a}") // 5，使用 @Inner 指定this是内部类对象
            println("Inner a = ${this.a}") // 5，不使用 @标记 默认this就是内部类对象
        }
    }
}

fun main() {
    val inner = Outer().Inner()
    inner.test()
}*/

/*
为了区分 Kotlin 与 Java 中静态内部类和非静态内部类声明上的不同，以下分别使用 Kotlin 和 Java 各自编写内部类代码：

// Kotlin - 非静态内部类
class Outer {
    inner class Inner {...}
}

// Kotlin - 静态内部类
class Outer {
    class Inner {...}
}
// Java - 非静态内部类
public class Outer {
    public class Inner {...}
}

// Java - 静态内部类
public class Outer {
    public static class Inner {...}
}
*/

/*
匿名内部类
匿名内部类就是没有定义名字的内部类，一般格式为 object : 接口或(和)类，实际开发中，方法的回调接口(即 callback)一般不会专门声明一个类再创建对象来使用，而是直接使用匿名内部类：

val textArea = TextArea()
textArea.addTextListener(object : TextListener {
    override fun textValueChanged(e: TextEvent?) {...}
})
Kotlin 的匿名内部类很强大，在使用时，可以有多个接口或父类，如：

val textArea = TextArea()
textArea.addTextListener(object : TextField(), TextListener {
    override fun textValueChanged(e: TextEvent?) {...}
})
这个匿名内部类既是 TextField 的子类，也是 TextListener 的实现类，不过可能实际应用场景会比较少，了解即可。

Java 中的匿名内部类就没这么强大了，只能是 单个 接口(或父类) 的 实现类(子类)。
 */
