package indi.zeroornull.com.fullstackaction.kotlinlang.learn.class_members

class Artist(var name: String) {
    var age = 18
    fun dance(danceName: String) {
        println("dance $danceName")
    }

    fun sing(singName: String) {
        println("sing $singName")
    }
}

fun sayHello() {
    println("Hello")
}


//class Aclass {
//    var b = 0
//        get() {
//            println("some one tries to get b.")
//            return field
//        }
//        set(value) {
//            println("some one tries to set b.")
//            field = value
//        }
//}
//Kotlin 中，对象访问属性实质上都是使用 getter/setter ，而并非直接访问属性对其赋值。这可以通过重写属性的 getter/setter 来验证：

//class Aclass {
//    protected var b = 0
//        get() {
//            println("some one tries to get b.")
//            return field
//        }
//        protected set(value) {
//            println("some one tries to set b.")
//            field = value
//        }
//}
// 修改了 getter 的访问权限，就需要对属性声明做同样的修改，于是，正确的写法如上

//Kotlin 中一般成员属性在声明时就需要初始化：
//class AClass {
//    var b = 0
//    var c: String? = null
//    // var c: String? // IDE报错：Property must be initialized or be abstract
//}

//class X
//class AClass {
//    lateinit var c: String
//    lateinit var d: X
//    // lateinit val e:X // IDE报错：'lateinit' modifier is allowed only on mutable properties
//}

class X
class AClass{
//    val e:X by lazy{} // IDE报错：Type mismatch. Required:X Found: Unit
    val e: X by lazy{
        println("init X")
    X()
}
}

fun main() {
    //sample4 code
    val a = AClass()
    println("init a")
    println(a.e) //init X
//    关于属性初始化的几点建议：
//
//    属性的初始化尽量在构造方法中完成
//    无法在构造方法中初始化，尝试降级为局部变量
//    var 用 lateinit 延迟初始化，val 用 lazy
//    可空类型慎用 null 直接初始化
    
// sample1 code
//    val artist = Artist("Tom")
//    artist.dance("恰恰")
//    artist.sing("龙卷风")
//    sayHello()


// sample2 code
//    val a = Aclass()
//    a.b = 666
//    println(a.b)

// sample3 code
//    val a = AClass()
//    a.d = X()
//    println(a.d)
//    println(a.c)


//    lateinit 只能与 var 搭配，不能与 val 一起使用。
//    lateinit var 变量在使用前一定要确保已经初始化，否则报错：lateinit property c has not been initialized。

    //val 常量的延迟初始化，使用的是 by lazy，作用是 "使用时加载"：
    
}