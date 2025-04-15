package indi.zeroornull.com.fullstackaction.kotlinlang.learn.abstract_class_and_interface

// 接口   
//interface InputDevice{
//    fun input(event:Any)
//}
//接口不能有状态，我们可以在接口中声明一个类似"属性"的变量 x，但它并不是一个属性，相当于只是声明 getX/setX 方法：
interface InputDevice {
    fun input(event: Any)

    //val version: String = "default" // IDE报错：Property initializers are not allowed in interfaces
    val version: String // 相当于定义getVersion()和setVersion()方法，但它不是属性，因为接口没有状态

    //在 Kotlin 中接口方法可以默认实现：
    fun onLowPower() {
        println("注意：当前电量低")
    }
    //与 Kotlin 不同，Java 中的接口不支持默认实现。
}



//实现接口需要使用 : 来连接，注意，因为接口没有构造器，所以实现接口时不需要带 ()。
class MyInputDevice(override val version: String) : InputDevice {
    override fun input(event: Any) {
        println("010101010101010101001010101010....")
    }
}

//接口之间是可以继承的，子接口将拥有父接口的所有方法：
interface USBInputDevice : InputDevice

// 蓝牙输入设备
interface BLEInputDevice : InputDevice

//抽象类可以理解为是实现了一部分协议的半成品，需要使用 abstract class 定义：
abstract class USBMouse(val name: String, override val version: String = "1.0") : InputDevice {
    override fun input(event: Any) {
        println("x , y ... ")
    }

    fun click(key: String) {
        println("点击了 $key 键")
    }

    abstract fun isSupport(os: String): Boolean
}

//抽象类是有构造器的，构造器中的 val 或 var 参数会变成成员属性，也就是说抽象类可以有状态。
//抽象类 USBMouse 实现了 USBInputDevice 接口，并实现了 input()方法，说明抽象类可以有方法实现。
//当然了，抽象类中方法实现不依赖于接口，它本身就可以像类那样定义自己的方法，如 click()。
//甚至它也可以像接口那样定义没有方法体的抽象方法 isSupport()，由具体子类去实现：
open class LogitechMouse:USBMouse("罗技鼠标"){
    override fun isSupport(os: String): Boolean {
        return when(os){
            "windows" -> true
            "MacOS" -> true
            "iOS" -> false
            else -> false
            
        }
    }
}
//类继承需要使用 : 来连接，注意，因为抽象类同普通类一样拥有构造器（不管有无构造参数），所以实现抽象类时需要带 ()。
//抽象类无法直接实例化，必须要子类继承后使用：

//接口没有构造器，因此无法像类那样实例化，必须由类对其进行实现后使用：

//单继承多实现
//Kotlin 不支持多继承！但支持多实现！比如，罗技 M720 鼠标本质是罗技鼠标，拥有 USB 和 BLE 两种连接功能：
class LogitechM720:LogitechMouse(),USBInputDevice,BLEInputDevice{}
fun main() {
    // val inputDevice = InputDevice() // IDE报错：Interface InputDevice does not have constructors
    val myInputDevice = MyInputDevice("1.0")
    myInputDevice.input("")
    
    val mouse=LogitechMouse()
    if (mouse.isSupport("windows")){
        mouse.click("left")
    }

    val usbInputDevice: USBInputDevice = LogitechM720()
    val bleInputDevice: BLEInputDevice = LogitechM720()
    val usbMouse: USBMouse = LogitechM720()
    val logitechMouse: LogitechMouse = LogitechM720()
}


