package indi.zeroornull.org.kotlinlang.tour.nullsafety

fun describeString(maybeString: String?):String{
    if (maybeString!=null&& maybeString.length>0){
        return "String of length ${maybeString.length}"
    }else{
        return "Empty or null string"
    }
}
fun main() {
    val nullString: String? = null
    println(describeString(nullString))
    
}