package indi.zeroornull.org.kotlinlang.tour.lambda_expressions



fun main() {
    val numbers = listOf(1,-2,-3,-4,5,-6)
    val doubled=numbers.map{x->x*2}
    val isTripled = {x:Int -> x*3}
    val tripled = numbers.map(isTripled)
    println(doubled)
    println(tripled)
    val positives  = numbers.filter({x->x>0})
    val isNegative =  { x: Int -> x < 0 }
    val negatives = numbers.filter(isNegative)
    println(positives)
    println(negatives)
}
        