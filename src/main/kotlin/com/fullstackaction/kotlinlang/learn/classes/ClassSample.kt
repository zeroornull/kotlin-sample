package indi.zeroornull.com.fullstackaction.kotlinlang.learn.classes
import indi.zeroornull.com.fullstackaction.kotlinlang.sample.anhui.Human as anhuiHuman
import indi.zeroornull.com.fullstackaction.kotlinlang.sample.shanghai.Human as shanghaiHuman
/**
 * constructor 是构造器关键字，如果只有一个构造器，则该关键字可以省略。
 * init 是构造方法的方法体，当用该类创建出一个对象时就会执行。
 */
//class Girl constructor(var nature:String,var appearance:String,var sound:String) {
//    // 构造方法的方法体
//    init {
//        println("girl's nature is $nature, appearance is $appearance, sound is $sound")
//    }
//    // 次级构造器，无法使用 var 声明变量
//    constructor(nature: String,appearance: String):this(nature,appearance,sound="unknown"){
//        println("次级构造器")
//    }
//    
//    
//}

/**
 * 当没有次级构造器，只有一个主构造器时，可以省略 constructor 关键字：
 * 
 * 主构造器中，使用 var 声明的参数，将成为成员变量，可以在类的各个方法中调用。
 * 而不使用 var 声明参数，只是临时变量，只能在 init 方法体中使用。
 */
//class Boy (var nature:String, appearance:String, sound:String){
//    // 构造方法的方法体
//    init {
//        println("boy's nature is $nature, appearance is $appearance, sound is $sound")
//    }
//    fun test(){
////        println(nature) // 编译OK
////        println(appearance)// 编译不通过
////        println(sound)// 编译不通过
//    }
//}

/**
 * 子类继承父类，可以获得父类的能力，Kotlin 中使用 : 连接子父类，形成继承关系，同时，父类需要使用 open 关键字修饰：
 * Kotlin 中的类默认是 final 类，而使用 open 修饰过的类，会去除 final 关键字。
 * 主构造器参数都没有使用 var 声明，实事上也不能使用 var 声明
 * Boy 主构造器中使用 var 声明 nature，会报错提示 nature 在父类 Human 中是隐藏成员，你需要使用 override 对其修改，那就按提示把 override 关键字加上，但还会报另一个错：
 * 如果单单对 nature 追加 override 关键字是不够的，因为构造器中的参数默认是 final 修饰过的不可修改变量，需要对父类 Human 主构造器中的 nature 参数再追加 open 关键字来去除这个限制。
 * 当父类主构造器参数没有使用 var 声明，而子类又需要在成员方法中使用该参数时，可以在子类的构造器中，为该参数使用 var 追加声明，让其成为子类的成员变量。除此之外，子类主构造器参数的使用与普通的主构造器参数没什么区别。
 */
open class Human(open var nature: String, open var appearance:String, open var sound:String){
    
    init {
        println("${this.javaClass.simpleName} 的性格：$nature，长相：$appearance，声音：$sound")
    }

    override fun toString(): String {
        return "Human(nature='$nature', appearance='$appearance', sound='$sound')"
    }

}

class Girl(nature: String, appearance:String, sound:String):Human(nature,appearance,sound){}
class Boy(override var nature: String,appearance: String,sound:String):Human(nature,appearance,sound)


fun main() {
//    val girl:Girl = Girl("Girl","appearance","sound")
//    val girl: Girl = Girl("温柔", "甜美")
    
//    val anhuiHuman = indi.zeroornull.com.fullstackaction.kotlinlang.sample.anhui.Human()
//    val shanghaiHuman = indi.zeroornull.com.fullstackaction.kotlinlang.sample.shanghai.Human()
    val anhuiHuman = anhuiHuman()
    val shanghaiHuman = shanghaiHuman()
    
}

