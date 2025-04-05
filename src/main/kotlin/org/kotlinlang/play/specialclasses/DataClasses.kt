package indi.zeroornull.org.kotlinlang.play.specialclasses

data class User(val name: String, val id: Int) {           // 1
    override fun equals(other: Any?) =
        other is User && other.id == this.id               // 2
}
fun main(){
    val user = User("Alex",1)
    println(user)
    
    val secondUser = User("Alex",1)
    val thirdUser = User("Max",2)
    
    println("user == secondUser: ${user == secondUser}")
    println("user == thirdUser: ${user == thirdUser}")
    
    
}