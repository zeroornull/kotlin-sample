package indi.zeroornull.org.kotlinlang.tour.lambda_expressions

fun main() {
    val actions = listOf("title", "year", "author")
    val prefix = "https://example.com/book-info"
    val id = 5
    val urls = actions.map { actions->"$prefix/$id/$actions" }
        println(urls)
}