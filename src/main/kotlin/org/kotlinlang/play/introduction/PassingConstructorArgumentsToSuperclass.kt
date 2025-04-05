package indi.zeroornull.org.kotlinlang.play.introduction

open class Lion(val name:String,val origin: String){
    fun sayHello(){
        println("$name, the lion from $origin says: graoh!")
    }
}
class Asiatic(name: String): Lion(name=name,origin="India")

fun main(){
    var lion: Lion = Asiatic("Rufo")
    lion.sayHello()
}