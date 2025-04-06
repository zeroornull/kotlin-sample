package indi.zeroornull.org.kotlinlang.tour.collections

fun main() {
    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested.uppercase() in SUPPORTED// Write your code here 
    println("Support for $requested: $isSupported")
}