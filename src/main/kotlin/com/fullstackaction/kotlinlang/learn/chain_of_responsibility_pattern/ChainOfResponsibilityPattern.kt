package indi.zeroornull.com.fullstackaction.kotlinlang.learn.chain_of_responsibility_pattern

/*
一、前言
责任链模式
作用：避免请求的发送者和接收者之间的耦合关系，将这个对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止。
举例：OKHttp 的拦截器、Servlet 中的 FilterChain
#二、使用责任链模式
例子：学生会经费申请
重点：1 个请求会在 n 个处理器组成的处理器链上传递
以学生会经费申请会例，学生会会有一些日常开销以及活动开支，需要向学院的学生会基金申请经费，如果金额在 100 元之内，
由分部长审批；如果金额在 100 到 500 元之间，由会长审批；如果金额在 500 到 1000 元之间，由学院辅导员审批；
而如果金额超过 1000 元，则默认打回申请。像这种需要一层层往后传递请求的情况，非常适合采用责任链模式来设计程序：
 */
/**
 * 经费申请事件
 *
 * @author GitLqr
 */
/*data class ApplyEvent(val money: Int, val title: String)*/

/**
 * 经费审批处理器
 *
 * @author GitLqr
 */
/*interface ApplyHandler {
    val successor: ApplyHandler?
    fun handleEvent(event: ApplyEvent)
}*/

/*
注意：责任链模式需要将处理器对象连成一条链，最简单粗暴的方式就是让前驱处理器持有后继处理器 successor
接着，根据案例需要，编写各个角色对应的处理器类：
 */
/**
 * 部长
 *
 * @author GitLqr
 */
/*class GroupLeader(override val successor: ApplyHandler?) : ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money <= 100 -> println("Group Leader handled application: ${event.title}.")
            successor != null -> successor.handleEvent(event)
            else -> println("Group Leader: This application cannot be handled.")
        }
    }
}*/

/*
注意：责任链模式需要将处理器对象连成一条链，最简单粗暴的方式就是让前驱处理器持有后继处理器 successor

接着，根据案例需要，编写各个角色对应的处理器类：
 */

/**
 * 会长
 *
 * @author GitLqr
 */
/*
class President(override val successor: ApplyHandler?) : ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money <= 500 -> println("President handled application: ${event.title}.")
            successor != null -> successor.handleEvent(event)
            else -> println("President: This application cannot be handled.")
        }
    }
}
*/

/**
 * 学院
 *
 * @author GitLqr
 */
/*class College(override val successor: ApplyHandler?) : ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money <= 1000 -> println("College handled application: ${event.title}.")
            successor != null -> successor.handleEvent(event)
            else -> println("College: This application cannot be handled.")
        }
    }
}*/
/*
最后，创建各个角色处理器实例，并按顺序组成一条链，由链头开始接收、转发需要被处理的经费申请事件：
 */
/*fun main() {
    // 使用
// val college = College(null)
// val president = President(college)
// val groupLeader = GroupLeader(president)
    val groupLeader = GroupLeader(President(College(null)))
    groupLeader.handleEvent(ApplyEvent(10,"buy a pen"))
    groupLeader.handleEvent(ApplyEvent(200,"team building"))
    groupLeader.handleEvent(ApplyEvent(600, "hold a debate match")) // 举行辩论赛
    groupLeader.handleEvent(ApplyEvent(1200, "annual meeting of the college")) // 学院年会
}*/

/*
从输出结果可以看到，经费申请事件会在处理器链上传递，直到被一个合适的处理器处理并终止。

注意：这话是针对当前案例说的，责任链模式没有硬性要求一个请求只能被一个处理器处理，你可以在前面的处理器中对请求进行加工，提取数据等等操作，并且可以选择是否放行，交由后面的处理器继续处理，这需要根据实际情况，灵活应变。
 */

/*
三、改良责任链模式
例子：学生会经费申请
重点：偏函数 Partial Function
在对上述案例进行改良之前，我们先来了解一下偏函数是什么，在不同的编程语言中，对偏函数的理解还不一样，在 Python 中，偏函数是使用 functools.partial 把一个函数的某些参数给固定住（也就是设置默认值），返回一个新的函数，调用这个新函数会更简单。而在 Scala 中，偏函数是使用 PartialFunction 构建一个仅仅处理输入参数的部分分支的函数，换句话说，就是带有判断条件的函数，只有满足条件的参数，才会被函数处理。

以上结论来自以下两篇文章：

廖雪峰官方网站上的 Python 《偏函数》https://www.liaoxuefeng.com/wiki/1016959663602400/1017454145929440(opens new window)
数据工匠记（作者：匠人李）的 Scala 《偏函数（Partial Function）》http://www.lllpan.top/article/77(opens new window)
题外话：对 Scala 偏函数有兴趣的可以看一下上面的文章，写的很通透。

回过头来，责任链模式的核心机理是，整个链条上的每个处理环节都有对其输入的校验标准，
当输入的参数处于某个责任链节的有效接收范围之内，该环节才能对其做出正常的处理操作。
那么，我们是不是可以把链条上的每个处理环节看做是一个个的偏函数呢？是的，不过 Kotlin 中并没有内置偏函数 API，
好在有一个第三方 Kotlin 函数库【funKTionale】 (opens new window)，其中的 partialfunctions.kt 就有 Scala 中偏函数的类似实现：
 */
// https://github.com/MarioAriasC/funKTionale/blob/master/funktionale-utils/src/main/kotlin/org/funktionale/utils/partialfunctions.kt

class PartialFunction<in P1, out R>(private val definetAt: (P1) -> Boolean, private val f: (P1) -> R) : (P1) -> R {
    override fun invoke(p1: P1): R {
        if (definetAt(p1)) {
            return f(p1)
        } else {
            throw IllegalArgumentException("Value: ($p1) isn't supported by this function")
        }
    }

    fun isDefinedAt(p1: P1) = definetAt(p1)
}

/*
这个 PartialFunction 类第一眼看上去感觉好复杂，分成如下几步，方便理解：

PartialFunction 继承自一个函数类型 (P1) -> R，编译器会强制要求实现 invoke() 方法，这意味着 PartialFunction 实例对象可以像调用函数那样使用。
构造参数 1 definetAt: (P1) -> Boolean 用于判断 P1 参数是否满足被处理的条件。
构造参数 2 f: (P1) -> R 用于处理 P1 参数并返回 R 类型值。
成员方法 invoke 中，当 P1 满足条件时，则将 P1 交给 构造参数 2 f: (P1) -> R 处理；否则抛出异常。
成员方法 isDefinedAt 只是构造参数 1 definetAt 的拷贝。
所以，用一句话概括 PartialFunction 实例对象，就是一个带有判断条件的"函数"，只有满足条件的参数，才会被"函数"处理。
现在我们用一个个 PartialFunction 实例来代替处理器是完全没问题的，
问题是怎么把它们链接起来呢？【funKTionale】 (opens new window)中还为 PartialFunction 扩展了一个 orElse 函数，
这就是把偏函数组合起来的关键：
 */
// https://github.com/MarioAriasC/funKTionale/blob/master/funktionale-utils/src/main/kotlin/org/funktionale/utils/partialfunctions.kt

infix fun <P1, R> PartialFunction<P1, R>.orElse(that: PartialFunction<P1, R>): PartialFunction<P1, R> {
    return PartialFunction({ this.isDefinedAt(it) || that.isDefinedAt(it) }) {
        when {
            this.isDefinedAt(it) -> this(it)
            that.isDefinedAt(it) -> that(it)
            else -> throw IllegalArgumentException("function not definet for parameter ($it)")
        }
    }
}
/*
同样，也分成如下几步，方便理解：

orElse 是 PartialFunction 的扩展函数，故内部可以使用 this 获取原本的 PartialFunction 实例（也就是 receiver）。
orElse 只接收一个 PartialFunction 类型参数 that，并且返回一个 PartialFunction 类型实例，故 orElse 可以嵌套调用。
orElse 返回值是一个使用了两个 PartialFunction 实例对象 （即 this 和 that）组合出来的一个新的 PartialFunction 实例对象，
orElse 返回值的意图是，只要原本的 this 和 that 中有一个条件成立，那么就让条件成立的那个来处理参数 P1 ，否则抛出异常。其实，这个 that 就相当于是责任链模式中的 successor。
orElse 使用 infix 修饰，故支持中缀表达式写法。
注意：你可能一时看不懂 PartialFunction({ xxx }){ yyy } 这个奇怪的语法，其实很简单，在创建一个 PartialFunction 实例时，可以传入两个 Lambda 表达式，所以正常写法应该是这样的 PartialFunction({ xxx }, { yyy }) ，不过，在 Kotlin 中，当 Lambda 表达式作为最后一个参数传入时，可以写到函数外部，所以就出现了 PartialFunction({ xxx }){ yyy } 这种写法。

好了，现在用 PartialFunction 来改良原本的责任链模式代码：
 */

data class ApplyEvent(val money: Int, val title: String)

val groupLeader = {
    val defineAt:(ApplyEvent) -> Boolean = {it.money <= 200}
    val handler:(ApplyEvent) ->Unit = {println("Group Leader handled application: ${it.title}.")}
    PartialFunction(defineAt,handler)
}()
val president = {
    val definetAt: (ApplyEvent) -> Boolean = { it.money <= 500 }
    val handler: (ApplyEvent) -> Unit = { println("President handled application: ${it.title}.") }
    PartialFunction(definetAt, handler)
}()
val college = {
    val definetAt: (ApplyEvent) -> Boolean = { true }
    val handler: (ApplyEvent) -> Unit = {
        when {
            it.money <= 1000 -> println("College handled application: ${it.title}.")
            else -> println("College: This application is refused.")
        }
    }
    PartialFunction(definetAt, handler)
}()

/*
注意：自运行 Lambda 相当于是 js 中的立即执行函数。

接下来就是用 orElse 将一个个 PartialFunction 实例链接起来：
 */

fun main() {
    // 使用
// val applyChain = groupLeader.orElse(president.orElse(college))
    val applyChain = groupLeader orElse president orElse college // 中缀表达式
    applyChain(ApplyEvent(10, "buy a pen")) // 买只钢笔
    applyChain(ApplyEvent(200, "team building")) // 团建
    applyChain(ApplyEvent(600, "hold a debate match")) // 举行辩论赛
    applyChain(ApplyEvent(1200, "annual meeting of the college")) // 学院年会

}

/*
使用 PartialFunction 之后，不仅可以不幅度减少代码量，结合 orElse 能获得更好的语法表达。
以上，就是使用偏函数改良责任链模式的全部内容了。为了加深对偏函数的理解，这里引用数据工匠记的 Scala 《偏函数（Partial Function）》原文中的话：

为什么要用偏函数呢？以我个人愚见，还是一个重用粒度的问题。函数式的编程思想是以一种“演绎法”而非“归纳法”去寻求解决空间。
也就是说，它并不是要去归纳问题然后分解问题并解决问题，而是看透问题本质，定义最原初的操作和组合规则，面对问题时，
可以通过组合各种函数去解决问题，这也正是“组合子（combinator）”的含义。偏函数则更进一步，将函数求解空间中各个分支也分离出来，
形成可以被组合的偏函数。
 */