package indi.zeroornull.com.fullstackaction.kotlinlang.learn.iterator_pattern

/*
一、前言
迭代器模式
作用：将遍历和实现分离开来，在遍历的同时不需要暴露对象的内部表示
举例：ArrayList、LinkedList、TreeSet 均可通过 iterator() 方法获取到迭代器对元素进行遍历，不需要关心元素存放在哪种数据结构中。
#二、使用迭代器模式
例子：遍历图书
重点：Iterator、Iterable 接口的使用
无论 Java 还是 Kotlin，都有 Iterator 接口，基于 Iterator 接口实现迭代器模式的类，
可以使用 for-in 或 forEach 对其进行快速遍历，这里我们让一个自定义的图片列表（BookList）实现迭代器功能：
 */
/**
 * 图书
 *
 * @author GitLqr
 *//*data class Book(val name: String)*/

/**
 * 图书集（迭代器模式）：基于 Iterator，只能遍历一次
 *
 * @author GitLqr
 *//*class BookList(val books: List<Book>) : Iterator<Book> {
    private val iterator: Iterator<Book> = books.iterator()
    
    override fun hasNext(): Boolean = iterator.hasNext()

    override fun next(): Book = iterator.next()
}

fun main() {
    val bookList = BookList(listOf(Book("Kotlin核心编程"),Book("深入理解")))
    for (book in bookList) {
        println(book.name)
    }
    bookList.forEach { book -> println(book.name) }
}*/

/*
可以看到 BookList 确实能使用 for-in 或 forEach 语法进行遍历了，但是结果却只输出一遍，通过源码可以知道 Iterator 是一次性且不可逆的：
public interface Iterator<out T> {
    /**
     * Returns the next element in the iteration.
     */
    public operator fun next(): T

    /**
     * Returns `true` if the iteration has more elements.
     */
    public operator fun hasNext(): Boolean
}
 */

/*
如果希望每次使用 for-in 或 forEach 语法都能从头遍历的话，很简单，只需要保证每次遍历使用的是独立的迭代器实例即可，
这时，我们可以让自定义类 BookList 改为实现 Iterable 接口：
 */
/**
 * 图书集（迭代器模式）：基于 Iterable，能遍历多次
 *
 * @author GitLqr
 */

/*data class Book(val name: String)

class BookList(val books: List<Book>) : Iterable<Book> {
    override fun iterator(): Iterator<Book> = books.iterator()
}

fun main() {
    val bookList = BookList(listOf(Book("Kotlin核心编程"), Book("深入理解Java虚拟机")))
    for (book in bookList) {
        println(book.name)
    }
    bookList.forEach { book -> println(book.name) }
}*/
/*
为防止混淆，这里对 Iterator、Iterable 各自的职能做下总结：

Iterator：迭代器，迭代器模式的核心实现（本质）
Iterable：可迭代的，每次获取一个新的迭代器（让 cursor 重新开始）
 */

/*
三、改良迭代器模式
例子：遍历图书
重点：重载运算符(iterator)、扩展函数
在 Kotlin 中，除了可以使用 Iterator 或 Iterable 来实现迭代器模式外，还可以通过让任意类重载运算符 iterator() 来实现 Iterable 接口相同的功能：
 */

/*data class Book(val name: String)

*//**
 * 图书集（迭代器模式）改良：重载 iterator 运算符
 *
 * @author GitLqr
 *//*
class BookList(val books: List<Book>){
    operator fun iterator() = books.iterator()
}

fun main() {
// 使用
    val bookList = BookList(listOf(Book("Kotlin核心编程"), Book("深入理解Java虚拟机")))
    for (book in bookList) {
        println(book.name)
    }
// bookList.forEach { book -> println(book.name) } // 语法错误，无法编译通过
}*/

/*
使用【重载运算符 iterator()】与【实现 Iterable 接口】这两种方式来实现的迭代器模式，在代码结构上差不多，
但是前者会让 forEach 无法使用，因为 forEach 是 Iterator 和 Iterable 的扩展函数，与运算符 iterator() 无关：
 */

/*
// Iterators.kt
public inline fun <T> Iterator<T>.forEach(operation: (T) -> Unit): Unit {
    for (element in this) operation(element)
}

// _Collections.kt
@kotlin.internal.HidesMembers
public inline fun <T> Iterable<T>.forEach(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}
 */

/*
不过呢，运算符 iterator() 是一个方法，它的返回值类型是 Iterator<T> ，所以我们可以变相的使用 forEach：
 */

/*
bookList.iterator().forEach { book -> println(book.name) }
另外，Kotlin 还支持用扩展函数的方式来重载运算符，于是，上述代码还可以变成这样：
 */

data class Book(val name: String)

/**
 * 图书集（迭代器模式）改良：重载 iterator 运算符
 *
 * @author GitLqr
 */
/*class BookList(val books: List<Book>) {
    operator fun BookList.iterator(): Iterator<Book> = this.books.iterator()
}

fun main() {
    val bookList = BookList(listOf(Book("Kotlin核心编程"), Book("深入理解Java虚拟机")))
    for (book in bookList) {
        println(book.name)
    }
    bookList.iterator().forEach { book -> println(book.name) }
}*/
/*
你可能会觉得，这也算改良吗？像 forEach 这种常见的遍历方式还得变相使用才行，感觉有点多余啊，确实如此，与其说是改良，倒不如说是 Kotlin 中迭代器模式的另一种实现思路。不过，假设 BookList 是第三方库中的类，你无法对其源码进行修改，这就意味着【实现 Iterable 接口】这种方式行不通，这时，不妨考虑一下【重载运算符 iterator()】（配合扩展函数）的方式。
 */
