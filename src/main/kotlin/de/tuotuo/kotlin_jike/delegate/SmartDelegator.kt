package indi.zeroornull.de.tuotuo.kotlin_jike.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringDelegate(private var s: String = "Hello") : ReadWriteProperty<Owner, String> {
    override fun getValue(thisRef: Owner, property: KProperty<*>): String {
        return s
    }
    override fun setValue(thisRef: Owner, property: KProperty<*>, value: String) {
        s = value
    }
}

class SmartDelegator{
    operator fun provideDelegate(thisRef: Owner, prop: KProperty<*>): ReadWriteProperty<Owner, String> {
        return if (prop.name.contains("log")){
            StringDelegate("log")
        }else{
            StringDelegate("normal")
        }
        
    }
}

class Owner{
    var normalText: String by SmartDelegator()
    var logText: String by SmartDelegator()
}

fun main() {
    val owner = Owner()
    println(owner.normalText)
    println(owner.logText)
}

class Model{
    val data:List<String> by ::_data
    private val _data:MutableList<String> = mutableListOf()
    fun load(){
        _data.add("Hello")
    }
}
