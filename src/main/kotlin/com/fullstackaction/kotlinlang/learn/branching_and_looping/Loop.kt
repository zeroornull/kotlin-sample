package indi.zeroornull.com.fullstackaction.kotlinlang.learn.branching_and_looping

//for 循环背后的运行机制是先通过 args.iterator() 获取到迭代器 Iterator，再结合迭代器的 next() 和 hasNext() 来处理数组中的元素，所以循环的关键点是 Iterator。

//Java 中 for 循环的原理也是迭代器，但不同的是，Java 的数组需要实现 Iterable<T> 接口，并实现iterator()，而 Kotlin 中则是任意类重写 operator fun iterator() 。

fun main() {
//    // 带索引的for循环
//    for ((index, value) in args.withIndex()) {
//        println("$index : $value")
//    }
//
//    // 上面写法等价于下面写法
//    for (indexValue in args.withIndex()) {
//        println("${indexValue.index} : ${indexValue.value}")
//    }
//    数组 Array 的 withIndex() 方法返回的是 Iterable<IndexedValue<T>>，其中 IndexedValue 是 data class ，支持使用 () 提取属性：

    var x = 5
    while (x > 0) {
        print("$x")
        x--
    }

    println()

    var y = 5
    do {
        print("$y")
        y--
    } while (y > 0)

    println()

    // sample1  
//    val args = intArrayOf(1,2,3,4,5)
//    for (arg in args) {
//        if (arg == 2) continue
//        if (arg == 4) break
//        print("$arg")
//    }
    
//    当有多层循环嵌套需要根据条件　跳过或终止内外套循环语句时，可以使用标签：
    val args = intArrayOf(1, 2, 3, 4, 5)
    Outter@ for (arg in args) {
        println("outter: $arg")
        var i = arg
        print("inner: ")
        Inner@ while (i > 0) {
            if (arg == 2) break@Inner
            if (arg == 4) break@Outter
            print("$i")
            i--
        }
        println()
    }
}

