package indi.zeroornull.com.fullstackaction.kotlinlang.learn.observer_pattern

import java.util.*
import kotlin.properties.Delegates

/*
一、前言
观察者模式
作用：定义了一个一对多的依赖关系，让一个或多个观察者对象监听一个主题对象。这样一来，当被观察者状态发生改变时，需要通知相应的观察者，使这些观察者对象能够自动更新。
核心操作：
观察者（订阅者）添加或删除对 被观察者（主题）的状态监听
被观察者（主题）状态改变时，将事件通知给所有观察者，观察者执行响应逻辑
#二、使用观察者模式
例子：监听股票价格变动
重点：使用 Java API 或 自定义实现 观察者模式
#1、使用 Java API 实现观察者模式
Java 标准库中提供了通用观察者模式的 API，分别是：

java.util.Observable：被观察者（主题）
setChanged()：标记状态更新
addObserver()：添加观察者
deleteObserver()：删除观察者
countObservers()：获取观察者数量
notifyObservers()：通知所有观察者
notifyObservers(Object arg)：通知所有观察者（携带参数 arg）
java.util.Observer：观察者（订阅者）
利用 Java API，可以实现监听股票价格变动这个功能：
 */

/**
 * 被观察者（主题）
 *
 * @author GitLqr
 */
/*class StockSubject : Observable() {
    fun changeStockPrice(price: Int) {
        this.setChanged()// 标识状态更新
        this.notifyObservers(price)
    }
}*/

/**
 * 观察者（订阅者）
 *
 * @author GitLqr
 */
/*class StockDisplay(val name: String) : Observer {
    override fun update(o: Observable?, price: Any?) {
        println("$name receive stock price : $price") // 注意 price 的类型是 Any?
    }
}

fun main() {
    // use
    val subject = StockSubject()
    subject.addObserver(StockDisplay("observer 1"))
    subject.addObserver(StockDisplay("observer 2"))
    subject.changeStockPrice(200)
// 输出
// observer 2 receive stock price : 200
// observer 1 receive stock price : 200
}*/

/*
注意：在主题中通过 notifyObservers() 方法通知订阅者之前，需要先调用 setChanged() 标识状态更新，
才能正常通知给订阅者，这是使用 Java API 实现观察者模式时需要注意的一点。

Java 提供的 API 已经涵盖了观察者模式的完整实现，所以我们在使用的时候，只需要关注业务本身，
而不用自己去做模式的具体实现，但是呢，Java 提供的 API 是一种通用实现，
从上面的例子中可以注意到，StockDisplay.update(o: Observable?, price: Any?) 中的 price 参数类型是 Any? ，这就会有以下几个问题：

参数判断：因为参数类型是 Any?，所以开发中不得不对 参数是否为空 以及 参数的实际类型 做判断。
通知入口单一：实际业务需求会更加复杂，而 java.util.Observer 只有唯一一个通知入口 update(o: Observable?, arg: Any?)，
所以我们不得不在该方法中分离响应逻辑，比如股票价格升降，这会让代码显得臃肿。
 */

/*
2、自定义实现观察者模式
虽然 Java 提供了现成的观察者模式 API，但是实际开发中，我们通常还是会自定义实现观察者模式，以便更好的控制代码结构：
 */

/**
 * 回调接口（解耦业务通知入口）
 *
 * @author GitLqr
 */
/*interface StockUpdateListener {
    fun OnRise(price: Int)
    fun onFall(price: Int)
}*/

/**
 * 被观察者（主题）
 *
 * @author GitLqr
 */
/*class StockSubject {
    val listeners = mutableSetOf<StockUpdateListener>()
    var price: Int = 0

    fun subscribe(observer: StockUpdateListener) {
        listeners.add(observer)
    }

    fun unsubscribe(observer: StockUpdateListener) {
        listeners.remove(observer)
    }

    fun changeStock(price: Int) {
        val isRise = price > this.price
        listeners.forEach { if (isRise) it.OnRise(price) else it.onFall(price) }
        this.price = price
    }
}*/

/**
 * 观察者（订阅者）
 *
 * @author GitLqr
 */
/*class StockDisplay : StockUpdateListener {
    override fun OnRise(price: Int) {
        println("The latest stock price has rise to $price")
    }

    override fun onFall(price: Int) {
        println("The latest stock price has fell to $price")
    }
}

fun main() {
    val subject = StockSubject()
    subject.subscribe(StockDisplay())
    subject.changeStock(200)
    subject.changeStock(100)
}*/
/*
可见，自定义实现观察者模式，可以让代码结构变得更加简单直观。
 */

/*
三、改良观察者模式
例子：监听股票价格变动
重点：委托属性 Delegates.observable()
Kotlin 标准库引入了可被观察的委托属性，可通过 xxx by Delegates.observable() 的方式，用来监听 xxx 属性的改变，于是可以用来改良上面的自定义观察者模式：
 */

/**
 * 回调接口（解耦业务通知入口）
 *
 * @author GitLqr
 */
/*interface StockUpdateListener {
    fun OnRise(price: Int)
    fun onFall(price: Int)
}*/

/**
 * 被观察者（主题）
 *
 * @author GitLqr
 */
/*class StockSubject {
    val listeners = mutableSetOf<StockUpdateListener>()

    var price: Int by Delegates.observable(0) { prop, old, new ->
        val isRise = new > old
        listeners.forEach {
            if (isRise) it.OnRise(price) else it.onFall(price)
        }
    }

    fun subscribe(observer: StockUpdateListener) {
        listeners.add(observer)
    }

    fun unsubscribe(observer: StockUpdateListener) {
        listeners.remove(observer)
    }
}*/

/**
 * 观察者（订阅者）
 *
 * @author GitLqr
 */
/*class StockDisplay : StockUpdateListener {
    override fun OnRise(price: Int) {
        println("The latest stock price has rise to $price")
    }

    override fun onFall(price: Int) {
        println("The latest stock price has fell to $price")
    }
}

fun main() {
    val subject = StockSubject()
    subject.subscribe(StockDisplay())
    subject.price = 250 // The latest stock price has rise to 200
}*/
/*
使用 Delegates.observable() 之后，StockSubject 相比之前减少了一个 changeStockPrice() 方法。
使用上，一旦对 price 属性赋值，就可以触发通知，显然，这对使用者更加友好了（直观，少记一个方法）。
 */

/*
四、补充
前面说到，Kotlin 标准库引入可被观察的委托属性，除了 Delegates.observable() 之外，
还有 Delegates.vetoable() 也很实用，当我们不希望被监控的属性被随意修改时，就可以用它来否决属性赋值：
 */

var value:Int by Delegates.observable(0) { prop, old, new ->
    new >0
}

fun main() {
    value  = 1
    println(value)
    value = -1
    println(value)
}

