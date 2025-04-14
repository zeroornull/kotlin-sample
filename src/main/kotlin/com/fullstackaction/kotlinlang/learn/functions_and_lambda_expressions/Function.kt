package indi.zeroornull.com.fullstackaction.kotlinlang.learn.functions_and_lambda_expressions

//fun sum(arg1:Int,arg2:Int):Int{
//    return arg1+arg2
//}
fun sum(arg1: Int, arg2: Int) = arg1 + arg2

// 任何函数都是有返回值的，一般函数方法体中会使用 return 将结果进行返回，但有的函数就是不需要返回结果，此时可以将 return 省略：
//fun main(args: Array<String>) {
//    
//}
// 即使函数方法体中没有 return 返回结果，但函数还是有返回值类型的，默认的返回值类型就是 Unit ，但一般 Unit 会省略不写：
//fun main(args:Array<String>):Unit{
//    
//}
// 匿名函数
// 匿名函数就是没有名字的函数，但是需要赋值给一个变量，否则 IDE 会报错：
//val sum = fun(arg1: Int, arg2: Int): Int {
//    return arg1 + arg2
//}
//
//fun main() {
//    val result = sum(1, 2)
//    println(result)
//}
// 此时 sum 是一个变量，只是它的值是一个函数。