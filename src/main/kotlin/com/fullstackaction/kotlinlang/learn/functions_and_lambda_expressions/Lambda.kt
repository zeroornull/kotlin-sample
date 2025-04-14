package indi.zeroornull.com.fullstackaction.kotlinlang.learn.functions_and_lambda_expressions


//val sum = { arg1: Int, arg2: Int ->
//    println("$arg1+$arg2 = ${arg1 + arg2}")
//    arg1 + arg2
//}

// Lambda 的类型

//() -> Unit : 无参，返回值为 null
//(Int) -> Int : 传入整型，返回一个整型
//(String, (String)->String) -> Boolean : 传入字符串、Lambda 表达式，返回 Boolean

val sum = { arg1: Int, arg2: Int -> arg1 + arg2 }

val printlnHello = {
    println("Hello")
}

fun main() {
    println(sum(1, 2))
    println(sum.invoke(1, 2))
    // 实际上，() 就是 invoke()。
    val args = arrayOf("1", "2", "3", "l", "q", "r")
    // 使用 for-in 遍历数组并打印元素内容
    for (arg in args) {
        println(arg)
    }
    // Lambda完整写法
    args.forEach({ it -> println(it) })
    // Lambda简化写法
    args.forEach({ println(it) })
    // 重命名参数it，此时ele不可省略
    args.forEach({ ele -> println(ele) })

    // Lambda 只有一个参数时可默认为 it，当然了，如果你不想使用 it 也是可以改名的，但如果改名了，就不能将其省略。除了省略默认参数 it，还有如下方法简化 Lambda 表达式：

    // 函数参数调用时最后一个 Lambda 可以移出去
    args.forEach() { println(it) }
    args.forEach { println(it) }
    args.forEach(::println)
    for (arg in args) {
        if (arg == "q") break
        println(arg)
    }
    println("")
    args.forEach {
        for (arg in args) {
            if (arg == "q") break
            println(arg)
        }
    }
    println("The End")

//    args.forEach {
//        if (it == "q") break // IDE报错：'break' and 'continue' are only allowed inside a loop
//        print("$it ")
//    }
//    println("The End")
    //Lambda 表达式仅仅是一个表达式而已，不是循环体，所以无法使用 break 和 continue，那这里为了中断遍历只能使用 return 了：
//    args.forEach {
//        if (it == "q") return
//        print(it)
//    }
//    println("The End")
    // 乍一看，这里使用了 return 实现了中断遍历，但其实 "The End" 没有输出，说明 return 并非中断遍历，而是中断了当前的 main 函数，原因还是那个：Lambda 表达式仅仅只是表达式而已。
    args.forEach {
        if (it == "q") return@forEach
        print(it)
    }
    println("The End")
    //程序跳过 q 继续往下执行了，这又是什么情况？这还是要回到 Lambda 表达式的本质，forEach里的 Lambda 表达式其实就是一个函数体，因此标签式返回 return@xxx 对这个函数体的退出只能退出当前的调用罢了。那就没有办法使用 forEach 实现这个需求了吗？是的，单靠 forEach 不行，需要配置 StreamApi 对数组进行过滤后再遍历：
    args.takeWhile { it!="q" }.forEach { println(it) }
    println("The End")
}
