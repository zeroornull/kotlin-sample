package indi.zeroornull.com.fullstackaction.kotlinlang.learn.enumeration_and_sealing_classes
// SealedClassExample.kt
sealed class Human
class WhiteRace : Human() // 白种人
class BlackRace : Human() // 黑种人
object Jesus : Human() // 耶稣
object MonkeyKing : Human() // 齐天大圣

/*
它的构造器默认就是 private 的，且不可修改，它的子类只能定义在同个 kt 文件中或者是密封类的内部类：

综上，我们可以发现枚举(enum class)和密封类(sealed class)很相似，但要分清楚两者的区别：

枚举：实例可数，没有子类。
密封类：子类可数，有子类。

 */


