package indi.zeroornull.com.fullstackaction.kotlinlang.learn.decorator_pattern

/*
一、前言
装饰者模式
作用：在不必改变原类文件和使用继承的情况下，动态地扩展一个对象的功能。
本质：该模式通过创建一个包装对象，来包裹真实的对象。
核心操作：
创建一个装饰类，包含一个被装饰类的实例
装饰类重写所有被装饰类的方法
在装饰类中对需要增强的功能进行扩展
#二、使用装饰者模式
例子：枪支部件
重点：装饰器类设计（实现被装饰类相同的接口，构造器接收被装饰类的接口实例对象）
像绝地求生这种大型射击游戏，里面的枪支系统是很复杂的，有很多种枪，而且几乎每种枪上都可以装配各种各样的部件，
比如消声器、八倍镜之类的，部件的作用各不相同，有的可以增加火力，有的可以提高精确度，等等，现在我们来简单设计一下这个枪支系统，
枪有很多种，所以需要定义一个接口来描述枪都有哪些能力，供后续扩展各种新枪：
 */
/**
 * 枪支接口
 *
 * @author GitLqr
 */
interface Gun {
    /**
     * 攻击力
     */
    fun attack(): Float

    /**
     * 噪音
     */
    fun noise(): Float

    /**
     * 生产日期
     */
    fun prodDate(): String
}

/**
 * Ump9
 *
 * @author GitLqr
 */
class Ump9Gun : Gun {
    override fun attack() = 100f

    override fun noise() = 20f

    override fun prodDate() = "2020-02-18"
}

/*
这里只实现了 Ump9 这个型号的枪，后续还可以根据需要扩展，现在来想想枪支部件怎么设计？在 Java 中，给一个类扩展行为有两种选择：

设计一个继承它的子类
使用装饰者模式对该类进行装饰
那么枪支部件合适用继承方式来设计吗？显然不合适，因为一个部件可以装配在不只一种枪上，所以继承这种方式排除。
另一种方式，使用装饰者模式有一个很大的优势，在于符合“组合优于继承”的设计原则，我们知道，部件可以和任意枪组合，
显示，使用装饰者模式来设计枪支部件是一个不错的选择：
 */

/**
 * 枪支部件
 *
 * @author GitLqr
 */
/*abstract class GunPart(protected val gun: Gun) : Gun*/

abstract class GunPart(protected val gun: Gun): Gun by gun

/**
 * 消声器
 *
 * @author GitLqr
 */
/*class Muffler(gun: Gun) : GunPart(gun) {
    override fun attack() = gun.attack() - 5
    
    override fun noise() = 0f

    override fun prodDate() = gun.prodDate()
}*/

class Muffler(gun: Gun): GunPart(gun){
    override fun attack() = gun.attack() - 5
    override fun noise() = 0f
}

/**
 * 燃烧子弹
 *
 * @author GitLqr
 */
/*class FireBullet(gun: Gun) : GunPart(gun) {
    override fun attack() = gun.attack() + 200

    override fun noise() = gun.noise()

    override fun prodDate() = gun.prodDate()
}*/

class FireBullet(gun: Gun) : GunPart(gun) {
    override fun attack() = gun.attack() + 200
}

/*
程序设计时，装饰器（部件）会引用被装饰实例（枪），并实现被装饰实例的所有接口，然后在需要增强的接口方法中加入增强逻辑。
因为枪支部件 GunPart 接收 Gun 类型构造参数，而且本身也是 Gun 接口的实现类，所以，可以让多种枪支部件 GunPart 嵌套修饰枪实例：
 */
fun main() {
    var ump9: Gun = Ump9Gun()
    println("装配前：ump9 攻击力 ${ump9.attack()}，噪音 ${ump9.noise()}")
    ump9 = Muffler(FireBullet(ump9)) // 装配了 燃烧子弹、消声器 的ump9
    println("装配后：ump9 攻击力 ${ump9.attack()}，噪音 ${ump9.noise()}")
}

/*
三、改良装饰者模式
例子：枪支部件
重点：类委托（by 关键字）
在上面的例子中，装饰者模式可以很好的解决实例组合的情况，但是代码还是显得比较啰唆，因为需要重写所有的装饰对象方法，
所以可能会存在大量样板代码。比如 FireBullet 只装饰增强 attack() 方法，而 noise()、prodDate() 均不做修改，
但还要是把这两个方法重写一遍。Kotlin 中有类委托特性，利用 by 关键字，将装饰类的所有方法委托给一个被装饰的类对象，
然后只需覆写装饰的方法即可：

可以看到，使用类委托之后，装饰类 FireBullet 中的样板代码不用重写了，从而减少了代码量。
 */
