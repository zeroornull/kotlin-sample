package indi.zeroornull.com.fullstackaction.kotlinlang.learn.vsibility_of_classes_and_members

//为了方便理解，这里举个例子，
// 一个父亲的零花钱只能自己使用(private)，
// 游戏机可以给孩子使用(protected)，
// 族谱可以给村里人使用(internal)，
// 名片可以给所有人使用(public)。
open class Father {
    private val pinMoney = 200 // 零花钱
    protected val gamePlayer = Switch() // 游戏机
    internal val familyTree = FamilyTree() // 族谱

    // public val callingCard = CallingCard()
    val callingCard = CallingCard() //名片
}

class CallingCard {

}

class FamilyTree {

}

class Switch {

}

//因为 kotlin 中类及成员可见性默认是 public，所以 public 可以不写。来看看子类 Son 中访问父类 Father 中成员属性会是如何：
class Son : Father() {
    fun getFromFather() {
//        val pinMoney = super.pinMoney // IDE报错：Cannot access 'pinMoney': it is private in 'Father'
        val gamePlayer = super.familyTree
        val familyTree = super.familyTree
        val callingCard = super.callingCard
    }
}

//可见子类最多可以访问父类 protected 级别的成员，再来看看外界访问 Father 成员属性的情况：
fun main() {
    val father = Father()
//    val pinMoney = father.pinMoney // IDE报错：Cannot access 'pinMoney': it is private in 'Father'
//    val gamePlayer = father.gamePlayer // IDE报错：Cannot access 'gamePlayer': it is protected in 'Father'
    val familyTree = father.familyTree
    val callingCard = father.callingCard
}
// 外界最多可以访问类中 internal 级别的成员。internal 是模块内可见，
// 这里的模块指的是 IDEA 项目结构中的 Module（ModuleB 不能访问 ModuleA 中 internal 类与成员），有兴趣的话，可以自己尝试下。
// Kotlin 中的 internal 与 Java 中的 default 是不一样的，所以，如果代码要做到两者互通的话，建议还是少用 internal。