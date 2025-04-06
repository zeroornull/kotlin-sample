package indi.zeroornull.org.kotlinlang.tour.collections

fun main() {
    val readOnlyJuiceMenu = mapOf("apple" to 100,"kiwi" to 190,"orange" to 100)
    println(readOnlyJuiceMenu)
    
    val juiceMenu: MutableMap<String,Int> = mutableMapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
    println(juiceMenu)
//    val juiceMenu: MutableMap<String, Int> = mutableMapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
    val juiceMenuLocked: Map<String, Int> = juiceMenu
    println("The value of apple juice is: ${readOnlyJuiceMenu["apple"]}")
    println("The value of pineapple juice is: ${readOnlyJuiceMenu["pineapple"]}")
    juiceMenu["coconut"] = 150 // Add key "coconut" with value 150 to the map
    println(juiceMenu)
    juiceMenu.remove("orange")    // Remove key "orange" from the map
    println(juiceMenu)
    println("This map has ${readOnlyJuiceMenu.count()} key-value pairs")
    println(readOnlyJuiceMenu.containsKey("kiwi"))
    println(readOnlyJuiceMenu.keys)
    // [apple, kiwi, orange]
    println(readOnlyJuiceMenu.values)
    // [100, 190, 100]

    println("orange" in readOnlyJuiceMenu.keys)
    println("orange" in readOnlyJuiceMenu)
    println(200 in readOnlyJuiceMenu.values)
    
}