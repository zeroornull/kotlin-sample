package indi.zeroornull.com.fullstackaction.kotlinlang.learn.enumeration_and_sealing_classes

/*
枚举
Kotlin 支持枚举，需要使用 enum class 进行声明，如：
 */

/*
enum class LogLevel{
    VERBOSE,DEBUG,INFO,WARN,ERROR,ASSERT
}
*/

/*
上面的代码可以理解为是 LogLevel 类的 companion object 语法糖：
 */

/*class LogLevel private constructor(){
    companion object{
        val VERBOSE = LogLevel()
        val DEBUG = LogLevel()
        val INFO = LogLevel()
        val WARN = LogLevel()
        val ERROR = LogLevel()
        val ASSERT = LogLevel()
    }
}*/

/*
所以我们可以知道枚举中的每一个成员，
其实都是枚举的一个实例对象，因此枚举会比较占内存；
另外，因为 class 默认是 final 的，且构造器是 private 的，所以枚举没有子类，
因此枚举是实例可数的。既然枚举是类，那它就可以有构造函数以及成员方法：
 */

/*enum class LogLevel(val id: Int) {
    VERBOSE(0), DEBUG(1), INFO(2), WARN(3), ERROR(4), ASSERT(5);

    override fun toString(): String {
        return "id = $id, name = $name, ordinal = $ordinal"
    }

}*/

/*
枚举 enum class 自身提供了几个有用的成员属性与方法：

成员属性 ordinal 可以获取到当前枚举实例的序号
成员方法 values() 可以获取到枚举的所有实例对象
成员方法 valueOf() 可以根据枚举实例名获取到枚举实例对象
 */

/*
fun main() {
    println(LogLevel.DEBUG.ordinal)
    LogLevel.entries.map(::println)
    println(LogLevel.valueOf("ERROR"))
}*/

/*
密封类
密封类是子类有限的类，需要使用 sealed class 进行声明：
 */

// Sealedzz