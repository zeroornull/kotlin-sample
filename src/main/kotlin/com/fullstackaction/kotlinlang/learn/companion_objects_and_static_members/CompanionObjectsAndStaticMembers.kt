package indi.zeroornull.com.fullstackaction.kotlinlang.learn.companion_objects_and_static_members

import com.sun.media.jfxmedia.events.PlayerEvent

//object 关键字
//Kotlin 中有一种特殊的类，它本身也是一个实例（单例），
// 这种既是类又是对象的类需要使用 object 关键字声明（普通类声明使用 class），它跟普通类一样，也可以实现接口和继承父类：

//object MusicPlayer : Player(), OnStatusChangeListener {
//
//    var state: Int = 0
//
//    fun play(url: String) {
//        ...
//    }
//
//    fun stop() {
//        ...
//    }
//
//    override fun onMount(driver: Driver) {
//    }
//
//    override fun onUnmount(driver: Driver) {
//    }
//}

//这种 object 类的方法可以通过类名直接调用：
//fun main() {
//    MusicPlayer.play("http://qqmusic.com/123213.mp3")
//    MusicPlayer.stop()
//}

//借助 Show Kotlin Bytecode 工具，将上述 Kotlin 代码反编译成 java 代码如下：
//public final class MusicPlayer extends Player implements OnStatusChangeListener {
//    public static final MusicPlayer INSTANCE;
//
//    private MusicPlayer() {
//    }
//
//    static {
//        MusicPlayer var0 = new MusicPlayer();
//        INSTANCE = var0;
//    }
//    ...
//}
//可以发现，Kotlin 中的 object 本质上就是 Java 中简单的单例模式，
// 同时它的构造方法是 private 的，因此 object 类不能自定义构造方法！

//伴生对象(companion object)
//日常开发中，经常会编写一些 Bean 类，
// 用于描述一类事物，Bean 中会包含事物的属性，
// 有的 Bean 类还会提供一系列"工具方法"来帮助开发者快速创建 Bean 对象，
// 就比如在 Java 中，Integer.valueOf() 就可以帮助开发者快速将字符串转成整型，进入源码一看，
// 可以发现这是一个使用了 static 关键字修饰的静态方法：

//可是，在 Kotlin 中是没有 static 关键字的！！！
// 想要实现通过 类名.xxx() 的方式调用工具方法，可以使用上述的 object 类，
// 但 object 本身就是一个对象，我们无法在外部调用其构造方法，
// 既然想拥有 object 类(通过类名调用方法)与 class 类(外部可以调用构造方法)的两种特性，
// 这时伴生对象 companion object 就完全可以满足这个需求：

class Rectangle(val width:Int,val height:Int){
    companion object{
        @JvmStatic
        fun ofSize(width: Int,height: Int): Rectangle{
            return Rectangle(width,height)
        }
        
        @JvmStatic
        fun ofRectangle(rectangle: Rectangle):Rectangle{
            return Rectangle(rectangle.width,rectangle.height)
        }
        
        @JvmField
        val Tag = "Rectangle"
    }
}

//伴生对象顾名思义，就是与类一起诞生的对象，类一加载，它的伴生对象也就被创建了，
// 每个类都可以对应一个伴生对象，并且该伴生对象的成员全局独一份，也就是说伴生对象 
// companion object 也是一种单例，再次借助 Show Kotlin Bytecode 工具，
// 将上述 Kotlin 代码反编译成 java 代码如下：

//可以发现，Kotlin 中的 companion object 其实对应到 Java 中也就只是 Rectangle 的一个静态内部类实例而已。

// JVM 静态成员(@JvmStatic)

//结合上述反编译后的 java 代码，应该不难理解，因为 Kotlin 中 Rectangle 的伴生对象本质上就是这个 Rectangle.Companion 实例，
// ofSize 和 ofRectangle 都是 Rectangle.Companion 的方法，所以，用 Rectangle.Companion 对象调用它自己的方法这完全没有毛病，
// 可是，难道就不能在 Java 中也像 Kotlin 那样，直接通过 类名.xxx() 的方法来调用伴生对象方法吗？
// 答案肯定是有的啦，Kotlin 提供了 @JvmStatic 和 @JvmField，可以让伴生对象中的方法和属性在 java 中通过类名直接调用：

fun main(){
//    val width = 4
//    val height = 5
//    val rectangle1 = Rectangle(width, height) // 调用构造方法
//    val rectangle2 = Rectangle.ofRectangle(rectangle1) // 通过类名调用方法
//    val rectangle3 = Rectangle.ofSize(width, height) // 通过类名调用方法
//    这次 java 代码也可以使用 Rectangle 类名来调用 ofSize 和 ofRectangle 方法了：
//
//    int width = 4;
//    int height = 5;
//    Rectangle rectangle1 = new Rectangle(width, height);
//    Rectangle rectangle2 = Rectangle.ofSize(width, height);
//    Rectangle rectangle3 = Rectangle.ofRectangle(rectangle2);
//    System.out.println(Rectangle.Tag);
    
    
}

//再来反编译一次，看其究竟是什么原理，原来就只是在 Rectangle 中增加了对应的 static 属性和方法而已：
//
//public final class Rectangle {
//    public static final Rectangle.Companion Companion = new Rectangle.Companion((DefaultConstructorMarker)null);
//
//    @JvmField
//    @NotNull
//    public static final String Tag = "Rectangle";
//
//    @JvmStatic
//    @NotNull
//    public static final Rectangle ofSize(int width, int height) {
//        return Companion.ofSize(width, height);
//    }
//
//    @JvmStatic
//    @NotNull
//    public static final Rectangle ofRectangle(@NotNull Rectangle rectangle) {
//        return Companion.ofRectangle(rectangle);
//    }
//
//    public static final class Companion {
//        @JvmStatic
//        @NotNull
//        public final Rectangle ofSize(int width, int height) {
//            ...
//        }
//
//        @JvmStatic
//        @NotNull
//        public final Rectangle ofRectangle(@NotNull Rectangle rectangle) {
//            ...
//        }
//    }
//}

