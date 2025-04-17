package indi.zeroornull.com.fullstackaction.kotlinlang.learn.method_overloading_and_default_parameters

//方法重载（Overloads）
//当一个类中有两个或多个具有相同方法名和不同的参数类型的方法时，就是重载：

class Person {

    //  可以发现，Java 中识别不到不带参数的 say()方法，这是因为 Java 并不知道 Kotlin 中有默认参数这种特性，
    //  它只能认为 say(what:String) 和 say(obj:Any) 是重载方法，把 say(what:String) 中的默认参数给忽略了，
    //  Kotlin 提供了 @JvmOverloads 可以将带有默认参数的方法转换成对应的重载方法：
    @JvmOverloads
    fun say(what: String = "hahaha") {
        println(what)
    }

    fun say(obj: Any) {
        println("this is ${obj.javaClass.simpleName}")
    }
}
//可以看到这里只是把 say() 和 say(what:String) 使用默认参数整合了，
// 但 say(obj:Any) 还在，其实 say(obj:Any) 的设计是不合理的，因为 String 是 Any 的子类，在调用方法时会有歧义，开发中，最好避免这种写法。

//JvmOverloads
//现在 Person 类中的 say()是重载方法，而且其中一个 say 有默认参数，我们来看看在 Java 中调用如何：


fun main() {
    val person = Person()
    person.say()
    person.say("hehehe")
    person.say(person)
}