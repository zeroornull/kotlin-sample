package indi.zeroornull.com.fullstackaction.kotlinlang.learn.branching_and_looping

fun main() {
    var local = ""
    var name = ""
    if (local == "en"){
        name = "xx"
    }else{
        name = "吴彦祖"
    }
    
     name =  if (local == "en") "xx" else "xxx"
}