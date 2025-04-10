package indi.zeroornull.com.fullstackaction.kotlinlang.learn.range

fun main() {
    val range: IntRange = 0..1024 // 闭区间[0,1024]，包括1024
    val rangeExclusive: IntRange = 0 until 1024 // 半开区间[0,1024)，不包括1024
    val emptyRange: IntRange = 0..-1 // 空区间[]
    // 其实这里的 .. 操作符对应的是 Int 类中的一个 rangeTo() 方法：
    /*
    /** Creates a range from this value to the specified [other] value. */
    public operator fun rangeTo(other: Byte): IntRange
    /** Creates a range from this value to the specified [other] value. */
    public operator fun rangeTo(other: Short): IntRange
    /** Creates a range from this value to the specified [other] value. */
    public operator fun rangeTo(other: Int): IntRange
    /** Creates a range from this value to the specified [other] value. */
    public operator fun rangeTo(other: Long): LongRange
     */
    // 判断某个元素是否在区间内：
    println(range.contains(50))
    // 这里的 in 关键字对应的就是 IntRange 类中的 contains() 方法，因此上面的两行代码实质上是一样的。
    println(500 in range)
    // 判断区间是否为空
    println(rangeExclusive.isEmpty())
    println(emptyRange.isEmpty())
    // 对区间进行遍历：
    for (i in rangeExclusive){
        print("$i, ")
    }
    // 这里的 in 跟 for 配合使用，就可以实现区间的遍历效果。
    // a b c d e f g h i j k l m n o p q r s t u v w x y z
    val lowerRange: CharRange = 'a'..'z'
// A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
    val upperRange: CharRange = 'A'..'Z'
    
}