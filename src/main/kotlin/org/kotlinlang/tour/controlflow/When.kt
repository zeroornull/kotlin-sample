package indi.zeroornull.org.kotlinlang.tour.controlflow

fun main() {
    val obj = "Hello"
    when (obj) {
        //
        "1" -> println("One")
        // Check whether ob
        "Hello" -> println("Greeting")
        else -> println("Unknown")
    }
    val result = when (obj) {
        // If obj equals "1", sets result to "one"
        "1" -> "One"
        // If obj equals "Hello", sets result to "Greeting"
        "Hello" -> "Greeting"
        // Sets result to "Unknown" if no previous condition is satisfied
        else -> "Unknown"
    }
    println(result)
// Greeting

//    val trafficLightState = "Red"
//
//    val trafficAction = when {
//        trafficLightState == "Green" -> "Go"
//        trafficLightState == "Yellow" -> "Slow down"
//        trafficLightState == "Red" -> "Stop"
//        else -> "Malfunction"
//    }
//    println(trafficAction)

    val trafficLightState = "Red" // This can be "Green", "Yellow", or "Red"

    val trafficAction = when (trafficLightState) {
        "Green" -> "Go"
        "Yellow" -> "Slow down"
        "Red" -> "Stop"
        else -> "Malfunction"
    }

    println(trafficAction)
    // Stop
}