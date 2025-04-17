package indi.zeroornull.com.fullstackaction.kotlinlang.learn.attribute_proxy

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

//自定义属性代理
//代理也叫委托，Kotlin 支持属性代理，把一个属性的获取与赋值交给一个“中介”(下称 Delegate)去管理，
// 属性代理的语法是： val/var <属性名>: <类型> by <表达式> ，by 后面是 Delegate 对象，
// 被代理属性的 get()和 set() 会给 Delegate 对象对应的 getValue() 和 setValue() 分别代理，因此 Delegete 可以这么写：
// 注意：Delegate 并不需要实现任何接口，仅需提供 getValue() 和 setValue() 即可。


class Delegate {
    private var _value: String? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("getValue() thisRef=$thisRef, property=${property.name}")
        return _value ?: ""
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("setValue() thisRef=$thisRef, property=${property.name}, value=$value")
        _value = value
    }
}

// 该 Delegate 的 getValue() 和 setValue() 方法对自己的 _value 属性进行获取与赋值，
// 并分别在这 2 个方法中打印输出各参数值，下面我们将一个类成员属性交给 Delegate 对象代理：

//class Animal{
//    var name:String by Delegate()
//}

//当代理属性被赋值与访问时，就会输出 Delegate 对象 setValue() 和 getValue() 中的日志，从输出的日志可以看出，
// thisRef 是代理属性的实例对象，property 是代理属性的包装。可见，属性代理相比单纯的属性操作更加强大，在某些场景下，属性值的访问会比较复杂，
// 如文件或 Redis，属性代理可以将文件或 Redis 的操作全部交给 Delegate 完成，而业务代码不需要知道具体实现，这对代码解耦与简化很有帮助。
//#延迟属性 lazy

//class Animal{
//    val age:Int by lazy{
//        println("设置age")
//        18 // lambda表达式会把最后语句的执行结果作为返回值
//    }
//    
//}
//可以看到 println("设置age") 只被输出了一次，这是怎么办到的呢？按住 ctrl ＋鼠标左键，点击 lazy 查看源代码：
// LazyJVM.kt
//public actual fun <T> lazy(initializer: () -> T): Lazy<T> = SynchronizedLazyImpl(initializer)

//lazy() 函数需要传入一个 lambda 表达式，返回值是 Lazy<T>，
// 返回结果 SynchronizedLazyImpl(initializer) 就是 Lazy<T> 的实现类对象，
// 我们知道 by 后面接的是 Delegate 对象，lazy() 函数返回的 SynchronizedLazyImpl 就是真正的 Delegate ，
// 再来看看 Lazy 与 SynchronizedLazyImpl 的源代码：

/*// Lazy.kt
public interface Lazy<out T> {
    public val value: T
    ...
}

// LazyJVM.kt
private class SynchronizedLazyImpl<out T>(initializer: () -> T, lock: Any? = null) : Lazy<T>, Serializable {
    private var initializer: (() -> T)? = initializer
    @Volatile private var _value: Any? = UNINITIALIZED_VALUE
    ...

    override val value: T
        get() {
            val _v1 = _value
            if (_v1 !== UNINITIALIZED_VALUE) {
                // 已经初始化过了，直接返回
                return _v1 as T
            }

            return synchronized(lock) {
                ...
                // 执行initializer()初始化_value值
                val typedValue = initializer!!()
                _value = typedValue
                initializer = null
            }
        }
    ...
}*/

//SynchronizedLazyImpl 重写了 value 属性的 getter 方法，其中会判断 value 是否有初始化过，已经初始化就直接返回结果，
// 未初始化才执行 initializer，所以，这就是 by lazy{} 的初始化逻辑只会执行一次的原因。前面说过，
// Delegate 对象需要有 getValue() 和 setValue() 方法，那么 Lazy 的这 2 个方法在哪呢？按住 ctrl ＋鼠标左键，点击 by 可以找到：

/*
// Lazy.kt
@kotlin.internal.InlineOnly
public inline operator fun <T> Lazy<T>.getValue(thisRef: Any?, property: KProperty<*>): T = value
*/

/*
发现，Lazy 只有 getValue()，没有 setValue()，这不难理解，var 变量延迟初始化使用的是 lateinit，
val 常量使用的 by lazy，val 常量不可修改，所以不需要提供 setValue()。
*/

//可观察属性 Observable
/*
除了 by lazy 外，Kotlin 为还提供了可观察属性 Observable，
这个属性代理可以监听属性值的变化，需要通过 by Delegates.observable() 来设置，
这是 Delegates.observable() 的源码，具体实现就不深入了，该函数要求传入 2 个参数，
分别是初始值 initialValue 和用于监听值变化的 lambda 表达式：

// Delegates.kt
public object Delegates {
    /**
    * Returns a property delegate for a read/write property that calls a specified callback function when changed.
    * @param initialValue the initial value of the property.
    * @param onChange the callback which is called after the change of the property is made. The value of the property
    *  has already been changed when this callback is invoked.
    *
    *  @sample samples.properties.Delegates.observableDelegate
    */
    public inline fun <T> observable(initialValue: T, crossinline onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Unit):
            ReadWriteProperty<Any?, T> =
        object : ObservableProperty<T>(initialValue) {
            override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = onChange(property, oldValue, newValue)
        }
    ...
}
*/

//下面我们来使用 Delegates.observable() 监听属性值的变化：

class Animal {
    var gender: Boolean by Delegates.vetoable(true) { prop, old, new ->
        println("prop = ${prop.name}, old = $old, new = $new")
        false // lambda表达式最后语句的结果作为返回值
    }
}
//我们将上面例子中的 gender 属性代理修改成 Delegates.vetoable()，并在 callback 中返回 false，以阻止属性值的修改：

fun main() {
    val animal = Animal()
//    animal.name = "小黑"// setValue() thisRef=com.charylin.kotlinlearn.Animal@1b28cdfa, property=name, value=旺财
//    println(animal.name) // getValue() thisRef=com.charylin.kotlinlearn.Animal@1b28cdfa, property=name
    // 小黑
//    println(animal.age)
//    println(animal.age)
//    println(animal.gender) // true
//    animal.gender = false // prop = gender, old = true, new = false
//    println(animal.gender) // false
    println(animal.gender)
    animal.gender = false
    println(animal.gender)
    
    /*
    可以看到，当修改了属性值时，会触发 Delegates.observable() 参数 2 的 lambda 表达式，
    输出了相应的日志信息，随后属性值也发生了改变。Kotlin 还提供了 Delegates.vetoable() 可观察属性代理，
    vetoable 比 observable 要强大一点，它除了能监听属性值变化，还可以控制属性是否要修改，
    通过查看 Delegates.vetoable() 源码注释可以知道，其参数 2 的 lambda 表达式（也就是 callback）返回值将决定属性值是否被更新：
    */

/*
// Delegates.kt
public object Delegates {
    /**
     * Returns a property delegate for a read/write property that calls a specified callback function when changed,
     * allowing the callback to veto the modification.
     * @param initialValue the initial value of the property.
     * @param onChange the callback which is called before a change to the property value is attempted.
     *  The value of the property hasn't been changed yet, when this callback is invoked.
     *  If the callback returns `true` the value of the property is being set to the new value,
     *  and if the callback returns `false` the new value is discarded and the property remains its old value.
     *
     *  @sample samples.properties.Delegates.vetoableDelegate
     *  @sample samples.properties.Delegates.throwVetoableDelegate
     */
    public inline fun <T> vetoable(initialValue: T, crossinline onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Boolean):
            ReadWriteProperty<Any?, T> =
        object : ObservableProperty<T>(initialValue) {
            override fun beforeChange(property: KProperty<*>, oldValue: T, newValue: T): Boolean = onChange(property, oldValue, newValue)
        }
    ...
}
*/


    
    
    
}