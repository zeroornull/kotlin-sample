package indi.zeroornull.org.kotlinlang.tour.classes

class Contact(val id: Int, var email: String) {
    fun printId() {
        println(id)
    }
}

fun main() {
    val contact = Contact(1,  "mary@gmail.com")
    contact.printId();
}