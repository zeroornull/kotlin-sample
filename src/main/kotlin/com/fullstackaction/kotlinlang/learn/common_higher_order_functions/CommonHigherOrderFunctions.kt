package indi.zeroornull.com.fullstackaction.kotlinlang.learn.common_higher_order_functions

/*
forEach
高阶函数 forEach 是可迭代对象的扩展方法，接收函数类型是 (T) -> Unit 的参数 action，
forEach 会将 action 这个函数作用于可迭代对象中的每个元素，这是源码：

//Performs the given [action] on each element.

@kotlin.internal.HidesMembers
public inline fun <T> Iterable<T>.forEach(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}

根据 forEach 的入参要求，我们给其传递一个 lambda 表达式或是函数引用：

forEachIndexed 相比 forEach 只是多了索引 index。

map
高阶函数 map 也是可迭代对象的扩展方法，根据 map 的源码与注释，我们知道 map 接收一个类型是 (T) -> R 的参数 transform，
map 会将 transform 作用于可迭代对象中的每个元素，并最终返回一个新的集合 List：


//Returns a list containing the results of applying the given [transform] function
//to each element in the original collection.
//
//@sample samples.collections.Collections.Transformations.map

public inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> {
    return mapTo(ArrayList<R>(collectionSizeOrDefault(10)), transform)
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    // list.forEach { it -> println(it) } // it可以省略
    list.forEach { println(it) }
    list.forEach(::println)
    list.forEachIndexed { index, i -> println("index=$index,value=$i") }
}

借助 map 的功能，我们可以将一个数组  “映射” 成另一个数组，这在日常开发很有用：

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    // 将int数组中的元素经过某种运算形成一个新的int数组
    val newList = list.map { it * 2 + 3 }
    newList.forEach(::println)

    // 将int转成double
    val newList2 = list.map(Int::toDouble)
    newList2.forEach(::println)
}

注意：toDouble()是 Int 类中的一个方法，在函数引用部分已经讲过，当使用 类名::方法名 这种方式引用一个成员方法时，
会自动在函数类型的参数列表第 1 位多出一个接收者 Receiver，用于接收类实例对象 ，刚好 toDouble()没有参数列表，
因此 Int::toDouble 对应的函数类型是 (Int) -> Double，符合高阶函数 map 的参数要求。

flatMap
高阶函数 flatMap 比 map 高一个维度，可以将可迭代对象中的每个可迭代对象进行处理，最终返回一个 扁平化 的可迭代对象，注意参数 transform 的函数类型是 (T) -> Iterable<R>：


//Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original collection.
//@sample samples.collections.Collections.Transformations.flatMap

public inline fun <T, R> Iterable<T>.flatMap(transform: (T) -> Iterable<R>): List<R> {
    return flatMapTo(ArrayList<R>(), transform)
}

什么是 扁平化 ，直观的说，就是胖变瘦，多维变一维：

fun main() {
    val list = listOf(
        8..10,
        1..3,
        98..100
    )
    val flatList = list.flatMap { it } // it->it，这里同时也是 (Iterable) -> Iterable
    flatList.forEach(::println) // 这时的 flatList 就相当于 [8,9,10,1,2,3,98,99,100]
}

 */

/*
filter
高阶函数 filter 可以将可迭代对象进行过滤，只有满足 predicate 过滤条件的元素(即 return true)才会被 "留下"：

/**
 * Returns a list containing only elements matching the given [predicate].
 *
 * @sample samples.collections.Collections.Filtering.filter
 */
public inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> {
    return filterTo(ArrayList<T>(), predicate)
}
我们可以使用 filter 过滤出数组中的奇数：

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    val newList = list.filter { it %2 == 1 }
    newList.forEachIndexed{index,i -> print(if(index>0)"$i" else i)}
}

 */


/*
takeWhile
高阶函数 takeWhile 会在遇到第一个不符合条件的元素时就结束取数据，留下前面的作为新的集合返回：

/**
 * Returns a list containing first elements satisfying the given [predicate].
 *
 * @sample samples.collections.Collections.Transformations.take
 */
public inline fun <T> Iterable<T>.takeWhile(predicate: (T) -> Boolean): List<T> {
    val list = ArrayList<T>()
    for (item in this) {
        if (!predicate(item))
            break
        list.add(item)
    }
    return list
}
我们可以使用 takeWhile 筛选出前面满足条件的元素：

fun main() {
    val list = listOf(1, 1, 2, 3, 5, 8, 13, 21)
    var newList = list.takeWhile { it %2 == 1 }
    newList.forEachIndexed{index,i -> print(if (index >0) "$i" else i) }
    
    newList = list.takeWhile { it<5 }
    newList.forEachIndexed { index, i -> print(if (index>0) "$i" else i) }
}
*/

/*
reduce
高阶函数 reduce 会从第一个元素开始累加，并从左到右将 operation 函数应用于当前累加值和每个元素：
/**
 * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
 *
 * @sample samples.collections.Collections.Aggregates.reduce
 */
public inline fun <S, T : S> Iterable<T>.reduce(operation: (acc: S, T) -> S): S {
    val iterator = this.iterator()
    if (!iterator.hasNext()) throw UnsupportedOperationException("Empty collection can't be reduced.")
    var accumulator: S = iterator.next()
    while (iterator.hasNext()) {
        accumulator = operation(accumulator, iterator.next())
    }
    return accumulator
}
我们可以用 reduce 来处理一些累加的操作，如计算 1 到 8 之间所有的数进行求和：

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    val result = list.reduce { acc, i -> acc + i }
    println(result)
}

 */

/*
fold
高阶函数 fold 跟 reduce 差不多，只是 fold 多了一个初始值，后续处理与 reduce 一样：

/**
 * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
 */
public inline fun <T, R> Iterable<T>.fold(initial: R, operation: (acc: R, T) -> R): R {
    var accumulator = initial
    for (element in this) accumulator = operation(accumulator, element)
    return accumulator
}
我们可以 fold 来计算在 100 的基础上，再累加 1 到 8 的和：
 */

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val result = list.fold(100, { acc, i -> acc + i })
    println(result)
}