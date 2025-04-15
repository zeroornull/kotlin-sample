package indi.zeroornull.com.fullstackaction.kotlinlang.learn.parameters_and_exceptions

// 具名参数
// 具名参数就是在调用函数时，给函数的实参附上形参：
fun sum(arg1: Int, arg2: Int) = arg1 + arg2
// 使用具名参数可以把实参精确的传递给指定形参，因此具名参数可以不用按顺序传入。

//变长参数
//vararg 可以让某个参数接收多个值，vararg 修饰的参数称为变长参数：
fun hello(vararg ints: Int, string: String) {
    ints.forEach(::println)
    println(string)
}

// 默认参数
fun say(age: Int = 20, name: String, height: Float) {
    TODO()
}

//多返回值
fun multiReturnValues(): Pair<String, Int> {
    //
    return "xxx" to 18;
}

fun multiReturnValues(height: Double): Triple<String, Int, Double> {
    return Triple("xxx", 18, height)
}

fun main(vararg args: String) {
    sum(arg1 = 1, arg2 = 2)
    sum(arg2 = 1, arg1 = 2)

    hello(1, 2, 3, 4, 5, string = "hello")

    //展开操作符（Spread Operator）
    //当变长参数需要传入一个数组时，可以使用展开操作符 * ，* 会将数组展开后一一传入：
    val array = intArrayOf(1, 2, 3, 4, 5)
    hello(*array, string = "Hello")// vararg变长参数场景下，*号可以把Array数组展开，但不支持List
//    展开操作符 * 有以下几个特点：
//    只支持展开 Array
//    只用于变长参数列表的实参
//    不是一般意义上的操作符，不能重载

//    say(18, "xxx", 2.0f)
//    say(name = "xxx", height = 1.8f)
    // 如果默认参数导致传参出现歧义时，需要使用具名参数。换句话说，就是默认参数后面的参数需要使用具名参数传值。

    val (myName, myAge) = multiReturnValues()
    println("myName is $myName, my age is $myAge")

    val (name, age, height) = multiReturnValues(18.0)
    println("name is $name, age is $age, height is $height")
    
    //捕获异常
    try {
        val arg1 = args[0].toInt()
        val arg2 = args[1].toInt()
        println("$arg1+$arg2 = ${sum(arg1,arg2)}")
    }catch (e: NumberFormatException){
        println("输入的数据不是数字")
    }catch (e:ArrayIndexOutOfBoundsException){
        println("需要输入2个数字")
    }catch (e: Exception){
        println("出现未知异常：${e.message}")
    }finally {
        println("感谢使用")
    }
    //跟 if、when 一样，try...catch 也可以是表达式，可以用来赋值：
    val result = try {
        args[0].toInt()/args[1].toInt()
    }catch (e:Exception){
        0
    }
}

