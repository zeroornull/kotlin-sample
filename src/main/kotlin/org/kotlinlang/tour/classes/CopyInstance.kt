package indi.zeroornull.org.kotlinlang.tour.classes
data class User(val name: String, val id: Int)
fun main() {
    val user = User("Alex",1)
    println(user.copy())
    println(user.copy("Max"))
    println(user.copy(id = 3))
}