package indi.zeroornull.com.fullstackaction.kotlinlang.learn.array

import indi.zeroornull.com.fullstackaction.kotlinlang.learn.classes.Boy
import indi.zeroornull.com.fullstackaction.kotlinlang.learn.classes.Girl
import indi.zeroornull.com.fullstackaction.kotlinlang.learn.classes.Human

fun main() {
    val array = Array<String>(5){
        index->"No.$index"
    }
    println(array.size)
    for (str in array){
        print(str)
    }
    val arrayOfString: Array<String> = arrayOf("我", "是", "LQR")
    val arrayOfHuman: Array<Human> = arrayOf(Boy("温和", "英俊", "浑厚"), Girl("温柔", "甜美", "动人"))
    val arrayOfInt: IntArray = intArrayOf(1, 3, 5, 7)
    val arrayOfChar: CharArray = charArrayOf('H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd')
    println(arrayOfHuman[1]) // 我是性格温柔，长相甜美，声音动人的人
    arrayOfHuman[1] = Boy("温和1", "英俊1", "浑厚1")
    println(arrayOfHuman[1]) // 我是性格温和1，长相英俊1，声音浑厚1的人
    // 字符串拼接
    println(arrayOfChar.joinToString())
    println(arrayOfChar.joinToString(""))
    // 切片
    println(arrayOfInt.slice(1..2))
}