package indi.zeroornull.de.tuotuo.kotlin_jike.reflection

import kotlin.reflect.KMutableProperty
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberProperties

fun main() {
    val student = Student("Tom", 99.5, 170)
    val school = School("PKU", "Beijing...")
    readMembers(student)
    readMembers(school)
    
    //
    modifyAddressMember(school)
    readMembers(student)
    readMembers(school)
}

fun readMembers(obj: Any) {
    obj::class.memberProperties.forEach {
        println("${obj::class.simpleName}.${it.name}=${it.getter.call(obj)}")
    }
}

fun modifyAddressMember(obj: Any) {
    obj::class.memberProperties.forEach {
        if (it.name == "address" &&
            it is KMutableProperty1 &&
            it.setter.parameters.size == 2 &&
            it.getter.returnType.classifier == String::class
        ) {
            it.setter.call(obj,"China")
            println("====Address changed.====")
        }else{
            //
            println("====Wrong type.====")
        }
    }
}

data class Student(val name: String, val score: Double, val height: Int)

data class School(val name: String, var address: String)

