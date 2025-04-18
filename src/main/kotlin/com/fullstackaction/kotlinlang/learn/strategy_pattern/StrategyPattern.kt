package indi.zeroornull.com.fullstackaction.kotlinlang.learn.strategy_pattern

/*
一、前言
策略模式
作用：让算法的变化独立于使用算法的客户
核心操作：定义了算法族，分别封装起来，让它们之间可以相互替换
#二、使用策略模式
例子：游泳运动员的游泳姿势
重点：算法抽离，封装成策略
作为一个游泳运动员，最基本的技能就是游泳，所以该类可以这么定义：
 */
/**
 * 游泳运动员
 *
 * @author GitLqr
 */
/*class Swimmer{
    fun swim(){
        println("游泳中...")
    }
}

fun main() {
    // 使用
    val shaw = Swimmer()
    shaw.swim()
}*/

/*
但是，游泳体育项目会对游泳姿势进行细分（姑且称之为技能吧），比如：蛙泳、仰泳、自由泳等等，
那怎么让 Swimmer 可以使用这些技能呢？一种做法是直接给 Swimmer 添加这些技能对应的方法，比如：

// ========== 错误演示 ==========
class Swimmer {
    fun breaststroke() {
        print("蛙泳...")
    }

    fun backstroke() {
        print("仰泳...")
    }

    fun freestyle() {
        print("自由泳...")
    }
}
可以很明确的说，这种做法是不行的，因为违背了开闭原则，后续被纳入标准的游泳姿势可能会越来越多，
比如：狗刨，继续往 Swimmer 增加新方法吗？肯定不行，这时策略模式就派上用场了，站在程序角度看，游泳姿势也不过是一种算法，
可以把这几种游泳姿势（算法）分别封装起来，为了能让算法相互替换，需要定义一个算法接口：
 */
/**
 * 游泳姿势接口
 *
 * @author GitLqr
 */
/*interface SwimStrategy {
    fun swim()
}*/

/**
 * 各种游泳姿势的具体实现
 *
 * @author GitLqr
 */
/*class Breaststroke : SwimStrategy {
    override fun swim() {
        println("蛙泳...")
    }
}

class Backstroke : SwimStrategy {
    override fun swim() {
        print("仰泳...")
    }
}

class Freestyle : SwimStrategy {
    override fun swim() {
        print("自由泳...")
    }
}*/

/*
接着，再通过构造器，把算法交给 Swimmer 使用即可：
 */
/*class Swimmer(val strategy: SwimStrategy) {
    fun swim() {
        strategy.swim()
    }
}

fun main() {
    val freestyleSwimmer = Swimmer(Freestyle())
    freestyleSwimmer.swim()
    val breaststrokeSwimmer = Swimmer(Breaststroke())
    breaststrokeSwimmer.swim()
}*/

/*
以后有更多的游泳姿势，只需要扩展 SwimStrategy 的实现类即可，无需修改 Swimmer。
 */

/*
三、改良策略模式
例子：游泳运动员的游泳姿势
重点：高阶函数
高阶函数是参数或返回值是函数的函数，由于策略模式是对行为算法的一种抽象，上述例子的本质是让 Swimmer 对象执行外界传入的 算法函数 而已，
那么借助高阶函数的特性，我们可以让 算法函数 作为高阶函数的参数传入即可，而不需要单独定义接口，
所以在 Kotlin 中可以使用高阶函数来改良策略模式：
 */

fun breaststroke() {
    print("蛙泳...")
}

fun backstroke() {
    print("仰泳...")
}

fun freestyle() {
    print("自由泳...")
}

/**
 * 游泳运动员（策略模式）改良：高阶函数
 *
 * @author GitLqr
 */
class Swimmer(val strategy:() -> Unit){
    fun swim(){
        strategy()
    }
}

fun main() {
    val freestyleSwimmer = Swimmer(::freestyle)
    freestyleSwimmer.swim()
    val breaststrokeSwimmer = Swimmer(::breaststroke)
    breaststrokeSwimmer.swim()
}
/*
改造之后，不但减少了代码量（去掉了策略算法接口），也使代码结构更加直观。
 */