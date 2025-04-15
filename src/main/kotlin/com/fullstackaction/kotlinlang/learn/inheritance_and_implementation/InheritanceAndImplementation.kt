package indi.zeroornull.com.fullstackaction.kotlinlang.learn.inheritance_and_implementation

//Kotlin 中默认 类、方法 都是 final 的，因此默认无法被子类继承或重写，但可通过 open 关键字来解除：
//父类需要 open 才可以被继承
//父类方法、属性需要 open 才可以被覆写
//覆写父类成员需要 override 关键字
//open class Person() {
//    open fun walk() {
//        println("Slow Walking")
//    }
//}

// 子类重写父类方法时，会使用到 override 关键字，
// 另外，重写的方法体中可以使用 super.xxx() 来调用父类方法中原有的逻辑，当然你也可以选择完全重写(方法体中不使用 super.xxx() 即可)。
// Kotlin 中父类的属性与方法一样，子类要重写的话，需要使用 open 关键字修饰父类属性，解除 final：
// 重写父类属性
//open class Person(open val name: String) {
//    ...
//}
//class Doctor(override val name: String) : Person(name) {
//    ...
//}
//也不是所有子类都需要重写父类成员属性的，因为子类可通过 getter/setter 对父类的成员属性进行访问。
// 这时子类仅需要将临时变量（即不使用 val 或 var 修饰的构造器参数变量）传递到父类构造器中就好了：
//继承类时实际上是调用了父类构造方法
// 传递构造参数
//open class Person(val name: String) {　// 使用了val，所以name是父类Person的成员属性。
//    ...
//}
//class Doctor(name: String) : Person(name) {　// 没有val，所以name仅仅只是一个临时变量。
//    ...
//}

// 不想重写父亲属性就不要使用val或var修改，因为这相当于在子类中定义了一个与父类成员属性同名的属性。
// class Doctor(val name: String) : Person(name) // IDE报错：'name' hides memeber of supertype 'Person' and needs 'override' modifier
//class Doctor : Person() {
//    override fun walk() {
//        // 调用父类方法
////        super.walk()
//        println("Fast Walking")
//    }
//}

//继承(实现)抽象类(接口)
// 相比于普通类，抽象类与接口就比较"开放"了，因为它们本身设计出来就是为了让子类(实现类)去继承(实现)的：
// 接口、接口方法、抽象类默认为 open

abstract class Person() {
    open fun walk() { // 成员方法，想被子类重写需要使用open关键字
        println("Slow Walking")
    }

    abstract fun work() // 抽象方法，默认就是open的
}

class Doctor : Person() {
    override fun walk() {
        println("fast walking")
    }

    override fun work() {// 子类实现父类抽象方法，还是需要使用override关键字

    }
}

//接口代理
interface Driver {
    fun driver()
}

//class Manager(val driver: Driver):Driver{
//    override fun driver(){
//        driver.driver()
//    }
//}
//但这种写法会显得比较罗嗦，Kotlin 提供了 接口代理 将接口方法实现直接交给代理类实现：
class Manager(driver: Driver) : Driver by driver

//接口方法冲突
//interface Radio {
//    fun display(): String {
//        return "107.1"
//    }
//}
//
//interface Compass {
//    fun display(): String {
//        return "东方"
//    }
//}


//class Phone(val type:Int):Radio,Compass{
//    override fun display(): String {
////        return super.display() // IDE报错：Many supertypes available, please specify the one you mean in angle brackets, e.g. 'super<Foo>'
//    }
//}
// false sample

//class Phone(val type: Int) : Radio, Compass {
//    override fun display(): String {
//        return when (type) {
//            0 -> super<Radio>.display()
//            1 -> super<Compass>.display()
//            else -> "不支持"
//        }
//    }
//}
//要注意，这里的接口方法冲突，指的是 签名一致且返回值相同的冲突 ，如果签名一致但返回值类型不同，那这个问题将无解：

//方法签名一致指的是方法名、参数列表相同。

//interface Radio{
//    fun display():String{
//        return "107.1"
//    }
//}
//interface Compass{
//    fun display():Int{
//        return 180
//    }
//}
// false sample


// IDE报错：
// Platform declaration clash: The following declarations have the same JVM signature (display()Ljava/lang/String;)
// Platform declaration clash: The following declarations have the same JVM signature (display()I;)
//class Phone(val type:Int):Radio,Compass{
//    override fun display(): String { // IDE报错
//    }
//
//    override fun display(): Int { // IDE报错
//    }
//}

//但反过来，如果方法返回值相同但签名不一致，那这情况就不一样了，就相当于 2 个不一样的方法，需要分开重写，
// 只是在使用 super.xxx() 调用接口方法时同样还是需要使用泛型来指明父接口，避免发生歧义：

interface Radio {
    fun display(): String {
        return "107.1"
    }
}

interface Compass {
    fun display(i: Int): Int {
        return i
    }
}

class Phone(val type: Int) : Radio, Compass {
    override fun display(): String {
        return super <Radio>.display()
    }

    override fun display(i: Int): Int {
        return super<Compass>.display(i)
    }
}