###1.java的优势
####跨平台，一次编译到处运行，
####java提供了自动内存管理机制，由垃圾回收器在后台自动回收
####面向对象，继承，多态
###2.java支持的数据类型有哪些？什么是自动拆装箱？
####基本类型byte,short,char,boolean,int,long,float,double
####引用类型：包括类，接口，数组
####注意：String不是基本数据类型，而是引用类型，
####引用类型声明的变量，是指该变量在内存中实际上存储的是个引用地址，创建的对象实际是在堆中
####自动拆装箱：是指基本数据类型与引用数据类型之间的自动转换https://blog.csdn.net/qq_18433441/article/details/78027344
####3.Enumeration和Iterator接口的区别
链接：https://www.nowcoder.com/questionTerminal/245d15607aa54fdf889f33371016b12f
来源：牛客网

package java.util;

public interface Enumeration<E> {
    boolean hasMoreElements();
    E nextElement();
}
public interface Iterator<E> {
    boolean hasNext();
    E next();
    void remove();
}

(01) 函数接口不同
Enumeration 只有2个函数接口。 通过Enumeration，我们只能读取集合的数据，而不能对数据进行修改。 
Iterator 只有3个函数接口。 Iterator除了能读取集合的数据之外，也能数据进行删除操作。
(02) Iterator 支持 fail-fast 机制，而 Enumeration 不支持 
Enumeration 是JDK 1.0添加的接口。使用到它的函数包括Vector、Hashtable等类，
这些类都是JDK 1.0中加入的，Enumeration存在的目的就是为它们提供遍历接口。
Enumeration本身并没有支持同步，而在Vector、Hashtable实现Enumeration时，添加了同步。 
而Iterator 是JDK 1.2才添加的接口，它也是为了HashMap、ArrayList等集合提供遍历接口。
Iterator是支持fail-fast机制的：当多个线程对同一个集合的内容进行操作时，
就可能会产生fail-fast事件。

####4.Math.round(11.5)=12,Math.round(-11.5)=-11
####5.请说出你所知道的线程同步的方法
同步代码块synchronized(object){}
同步函数public synchronized void sale(){}

####.Overload和Override的区别？
1、综述 
　　重写(Override)也称覆盖，它是父类与子类之间多态性的一种表现，而重载(Overload)是一个类中多态性的一种表现。 override从字面就可以知道，它是覆盖了一个方法并且对其重写，以求达到不同的作用。overload它是指我们可以定义一些名称相同的方法，通过定义不同的输入参数来区分这些方法，然后再调用时，VM就会根据不同的参数样式，来选择合适的方法执行。

2、override（重写，覆盖） 
（1）方法名、参数、返回值相同。 
（2）子类方法不能缩小父类方法的访问权限。 
（3）子类方法不能抛出比父类方法更多的异常(但子类方法可以不抛出异常)。 
（4）存在于父类和子类之间。 
（5）方法被定义为final不能被重写。 
（6）被覆盖的方法不能为private，否则在其子类中只是新定义了一个方法，并没有对其进行覆盖。

3、overload（重载，过载） 
（1）参数类型、个数、顺序至少有一个不相同。 
（2）不能重载只有返回值不同的方法名。 
（3）针对于一个类而言。 
（4）不能通过访问权限、返回类型、抛出的异常进行重载； 
（5）方法的异常类型和数目不会对重载造成影响；

4、override应用： 
（1）最熟悉的覆盖就是对接口方法的实现，在接口中一般只是对方法进行了声明，而我们在实现时，就需要实现接口声明的所有方法。 
（2）除了这个典型的用法以外，我们在继承中也可能会在子类覆盖父类中的方法。

5、总结 
　　override是在不同类之间的行为，overload是在同一个类中的行为。

8.说说ArrayList,Vector,LinkedList的存储性能和特性https://blog.csdn.net/pactoer/article/details/70132674
ArrayList和vector是基于数组实现的，是顺序结构，能很方便的进行随机访问，LinkedList的是基于链接结构实现的，能方便对集合进行添加和删除操作，但不方便进行随机访问。
ArrayList和LinedList是非线程安全的，Vector是线程安全的。

9.java8新特性

10.java的两种异常类型是什么？他们有什么区别？https://www.nowcoder.com/questionTerminal/3ded1983c85c4ae197e005bd31777bc7
链接：https://www.nowcoder.com/questionTerminal/3ded1983c85c4ae197e005bd31777bc7
来源：牛客网
Throwable是所有异常的根，java.lang.Throwable
Error是错误，java.lang.Error
Exception是异常，java.lang.Exception

二、Exception
一般分为Checked异常和Runtime异常，所有RuntimeException类及其子类的实例被称为Runtime异常，
不属于该范畴的异常则被称为CheckedException。

①Checked异常
只有java语言提供了Checked异常，Java认为Checked异常都是可以被处理的异常，
所以Java程序必须显示处理Checked异常。如果程序没有处理Checked异常，该程序在编译时就会发生错误无法编译。
这体现了Java的设计哲学：没有完善错误处理的代码根本没有机会被执行。
对Checked异常处理方法有两种
1 当前方法知道如何处理该异常，则用try...catch块来处理该异常。
2 当前方法不知道如何处理，则在定义该方法是声明抛出该异常。
我们比较熟悉的Checked异常有
Java.lang.ClassNotFoundException
Java.lang.NoSuchMethodException
java.io.IOException

②RuntimeException
Runtime如除数是0和数组下标越界等，其产生频繁，处理麻烦，
若显示申明或者捕获将会对程序的可读性和运行效率影响很大。
所以由系统自动检测并将它们交给缺省的异常处理程序。当然如果你有处理要求也可以显示捕获它们。
我们比较熟悉的RuntimeException类的子类有

Java.lang.ArithmeticException
Java.lang.ArrayStoreException
Java.lang.ClassCastException
Java.lang.IndexOutOfBoundsException
Java.lang.NullPointerException
三、Error
当程序发生不可控的错误时，通常做法是通知用户并中止程序的执行。与异常不同的是Error及其子类的对象不应被抛出。
Error是throwable的子类，代表编译时间和系统错误，用于指示合理的应用程序不应该试图捕获的严重问题。
Error由Java虚拟机生成并抛出，包括动态链接失败，虚拟机错误等。程序对其不做处理。

####11.谈一谈对数据库事务的理解（特性，隔离级）
值得一提的是：大多数数据库默认的事务隔离级别是Read committed，
比如Sql Server , Oracle。Mysql的默认隔离级别是Repeatable read。
https://blog.csdn.net/qq_33290787/article/details/51924963

acid，原子性Atomicity，一致性consistency，隔离性Isolation,永久性Durability
read uncommitted,read committed,repeatable read, Serializable

####12.数据库编程时，连接池有什么作用？
连接池技术的主要优点包括：
（1） 缩短了连接创建时间
创建新的JDBC连接会导致联网操作和一定的JDBC驱动开销，
如果这类连接是“循环”使用的，使用该方式，可避免这类不利因素。
（2）简化的编程模型
使用连接池技术时，每个单独线程能够像创建了自己的JDBC连接那样进行操作，
从而允许使用直接的JDBC编程技术。
（3）受控的资源使用
如果不使用连接池技术，而是在每次需要时为线程创建新的连接，
那么应用程序的资源使用将十分浪费，
而且在负载较重的情况下会导致无法预期的结果。

####13.用java写一个二分查找https://www.cnblogs.com/luoxn28/p/5767571.html









