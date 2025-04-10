package indi.zeroornull.com.fullstackaction.kotlinlang.learn.smart_type_conversion

open class Parent {}

class Child : Parent() {
    fun getName(): String {
        return "xx"
    }
}

fun main() {
//    val man:Parent = Child()
    // Kotlin 里，强转需要使用 as　关键字来处理。
//    println((man as Child).getName())
//    val man:Parent = Child()
//    if (man is Child){
//        println(man.getName())
//    }
    // Kotlin 中使用 is 关键字来判断变量类型，if 代码块中，man 变量已经被识别为 Child 类型了，因此不再需要显式强转，这就是 Kotlin 的智能类型转换，反观 Java 就显的有些笨笨的了：
    //Kotlin 代码中使用 as 进行对象的类型强转，如果我们不先进行类型判断，就直接强制变量类型，一旦被强转的对象类型有误，就必定会抛出ClassCastException：
//    val parent: Parent = Parent()
//    val child: Child = Child()
//    print(child)
    //Kotlin 的智能类型转换功能为 "直接强转党" 提供了一条出路，那就是使用 as?，同时如果变量有显式指定类型的话，需要将其改为可空类型，或者干脆把变量类型声明去掉：
    val parent: Parent = Parent()
    val child: Child? = parent as? Child
// val child = parent as? Child// 这种写法也是OK的
    print(child)// 输出：null
    // as? 相比 as 要智能一些，当强制类型有误会时，结果会为 null。
}