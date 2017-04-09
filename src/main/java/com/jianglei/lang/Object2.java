package com.jianglei.lang;

/**
 * Object类是所有类层次结构的根
 * 每个类都将Object类作为其父类。
 * 从有对象，包括数组，实现了此类的方法
 * Created by jianglei on 2017/4/6.
 */
public abstract class Object2 {

    /**
     * 返回此对象运行时的类。
     * 返回的Class对象是由所表示类的
     * static synchronized 方法锁定的对象。
     *
     * 实际结果类型是Class<? extends |x|>,其中|x|表示清除表达式中的
     * 静态类型，该表达式调用getClass。
     * 例如，以下代码片段中不需要强制转换：
     *  Number n = 0;
     *  Class<? extends Number> c = n.getClass();
     * @return 表示此对象运行类的Class对象。
     */
    public abstract Class<?> getClass2();

    /**
     * 返回一个对象的hashcode值。
     * 支持此方法是为了提高哈希表（例如java.util.Hashtable提供的哈希表）的性能
     *
     * hashCode通常的规定是：
     *  在java应用程序执行期间，无论什么时候对同一个对象多次调用此方法，
     *  hashCode方法一定始终如一的返回相同的整数，
     *
     *  如果根据equals(Object)方法，那么调用两个对象的每个hashCode方法一定
     *  要产生一样的整数结果。
     *
     *  如果根据equals方法两个对象为相等，那么对这两个对象中的任一对象上调用
     *  hashCode方法不要求一定生成不同的整数结果。但是，程序员应该意识到产生不相等
     *  的整数结果为不相等的对象可以提高哈希表的性能。
     *
     *  实际上，由Object类定义的hashCode方法确实会针对不同的对象返回不同的整数。（
     *  这通常的实现是转换对象的内存地址成整数，但是Java编程语言不需要这种实现技巧
     *
     *
     *  @return 此对象的哈希值
     */
    public abstract Integer hashCode2();

    /**
     * 声明别的对象是否与此对象相等。
     * equals方法在非空对象引用上实现了相等关系的判断
     *
     * 自反性：对于任何非空引用x, x.equals(x)应该返回true
     * 对称性：对于任何非空引用x和y, 如果y.equals(x)返回true，
     * 那么x.equals(y)也应该返回true
     * 传递性：对于任何非空引用，如果x.equals(y), y.equals(z)
     * 返回true,那么x.equals(z)也应该返回true
     * 一致性：对于任何非空引用，多次调用x.equals(y)要么总是返回true
     * 要么总是返回false。比较的前提是对象的信息没有发生改变。
     *
     * 对于任何非空引用，x.equals(null)应该返回false.
     *
     * Object类的equals方法最大区别可能的等价关系，
     * 对于任何非空引用x, y。如果x,y引用了同一个对象
     * 此方法才返回true(x == y 具有值true)
     *
     * 注意当此方法被重写时，通常要需要重写hashCode方法，
     * 以维护hashCode方法的常规协定，该协定声明相等对象必须
     * 具有相等的哈希码。
     *
     * @param obj 引用的对象用来比较相等性
     * @retrun true如果此对象与指定obj对象相等；
     * 否则返回false
     */
    public boolean equals(Object obj){
        return this == obj;
    }

    /**
     * 创建并返回此对象的复制，copy更确切的意思可能依赖于此对象
     * 的类。通常的意图，对于任何对象x,表达式：
     * x.clone() != x
     * 将会返回true，
     * 表达式：
     * x.clone().getClass() = x.getClass()
     * 将会返回true
     * 但这不是绝对的要求。
     * 但是他传统的case:
     * x.clone().equals(x)将会为true，这不是绝对的要求，
     *
     * 执照规范，返回的对象应该通过调用super.clone获得。如果
     * 一个类及所有的父类（Object除外）都遵守此约定，则
     * x.clone().getClass() == x.getClass().
     *
     * 通常规范，此方法返回的对象应该独立于此对象（要被克隆的）。
     * 为了达到此独立性，在调用super.clone返回对象之前应该
     * 更改此对象一个或更多的字段。通常，这意味着要复制包含正在被
     * 复制对象的内部"深层结构"的所有可变对象，并使用对副本的引用替换
     * 对这此对象的引用。如果一个类仅包含原始字段或引用不可变对象，
     * 通常super.clone返回的对象的字段不需要被改变。
     *
     * Object类的clone方法执行特定的复制操作。首先，如果此对象
     * 的类不能实现接口Cloneable,则会抛出CloneNotSupportedException。
     * 注意，所有的数组被认为实现了Cloneable接口。否则，此方法会创建此对象
     * 的类的一个新实例，并像通过分配那样，严格使用此对象相应字段的内容初始化
     * 该对象的所有字段；这些字段的内容没有被自我复制。所以，此方法执行的是该对象的
     * "浅表复制"，而不是"深层复制"操作
     *
     * Object类没有自身实现Cloneable接口，所以调用用类为Object的对象调用clone
     * 将会抛出一个运行时异常
     *
     * @return 一个对实现的clone
     * @throws CloneNotSupportedException 如果对象的类没有支持Cloneable接口。
     * 子类覆盖clone方法也可能抛出此异常来声明此实例不能被clone
     */
    public abstract Object clone2() throws CloneNotSupportedException;

    /**
     * 返回一个字符串代表此对象。通常来说，
     * toString方法返回一个字符串文本的代表此对象。
     * 返回的结果应该简洁的但是富含信息的代表此对象，
     * 人们容易阅读。
     *
     * Object类的toString方法返回一个字符串，该字符串由此实例
     * 的类的名字，一个标识符@，一个无符号哈希值代表此对象的哈希值。
     * 换句话说，此方法返回一个字符串：
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     *
     * @return 一个代表此对象的字符串。
     */

    public String toString(){
        return getClass().getName() + '@' + Integer.toHexString(hashCode());
    }

    /**
     * 唤醒一个正在等待此对象监视器的线程。如果有多个线程正在此对象上等待，则会选择唤醒其中
     * 一个。选择是任意的并且在对实现做出决定时发生。线程通过调用其中对象的wait方法来等待对象
     * 的监视器。
     * 被唤醒的线程不会被执行直到当前纯种放弃此对象的锁。
     * 被唤醒的线程将以常规的方式与在该对象上主动同步的其他所
     * 有线程进行竞争；例如，唤醒的线程在作为锁定此对象的下一个线程方面没有可靠的特权或劣势。
     *
     * 此方法只应由作为此对象监视器的拥有者线程来调用。
     * 一个线程通过三种方式来成为对象监视器的拥有者：
     * 通过执行一个对象的实例同步方法
     * 通过执行同步语句
     * 对于对象的类型，通过执行此类的静态同步方法
     *
     * @throws IllegalMonitorStateException 如果当前纯种不是此对象监视器的拥有者
     *
     *
     */
    public abstract void notify2();

    /**
     * 唤醒此对象监视器上所有等待的线程。
     * 一个线程通过调用wait方法的其中一个来等待此对象的监视器
     *
     * 被唤醒的线程不会执行直到当前纯种放弃此对象的锁。
     * 被唤醒的线程将以常规的方式与在该对象上主动同步的其他所
     * 有线程进行竞争；例如，唤醒的线程在作为锁定此对象的下一个线程方面没有可靠的特权或劣势。
     * 此方法只应由作为此对象监视器的拥有者线程来调用。
     */
    public abstract void notifyAll2();

    /**
     * 导致当前线程wait直到别的纯程调用notify()或notifyAll()方法，
     * 或者超过指定的时间量前，
     *
     * 当前线程一定要拥有对象的监视器。
     *
     * 此方法导致当前线程（称之为T)将其自身放置在对象的等待集中，然后放弃此对象上
     * 的所有同步要求。出于纯程调度目的，在发生以下四种情况之一前，线程T被禁用，且
     * 处于休眠状态：
     *  一些其他线程调用此对象的notify方法，并且线程T正好被选中作为唤醒的线程。
     *  一些其他线程调用此对象的notifyAll方法。
     *  一些其他线程interrupt线程T
     *  大约已经到达指定的实际时间。但是，如果timeout为0,则不考虑实际时间，
     *  线程仅会等待直到被唤醒。
     *
     * 线程T随后会从此对象的等待集中被移除，并重新进行线程调度。然后，该线程以常规
     * 方式与其他线程竞争，以获得在该对象上同步的权利；一旦获得对该对象的控制权，该
     * 对象上所有其同步声明都将被恢复到以前的状态，这就是调用wait方法时的情况。
     * 然后，线程T从wait方法的调用中返回。所以，从wait方法返回时，该对象和线程T的
     * 同步状态与调用wait方法时的情况完全相同。
     *
     * 注意，由于wait方法将当前线程放入了对象的等待集中，所以它只能解除此对象的锁定；
     * 可以同步当前线程的任何其他对象在线程等待时仍处于锁定状态。
     *
     * 此方法只应由作为此对象监视器的所有者的线程来调用。
     *
     */
    public abstract void wait2();
    /**
     * 当垃圾回收器确定此对象没有任何引用时，该对象的垃圾回收器调用此方法。
     * 子类重写finalized方法分配系统资源或执行其他清除
     *
     * finalize被调用的通常的规定是：当Java虚拟机确定任何活动的线程可能访问此
     * 对象时，除非由于准备终止的其他某个对象或类的终结操作执行了某个操作。finalize
     * 方法可以采取任何行动，包括使该对象对于其他纯程再次可用；不过，finalize的主要目的
     * 是在不可撤消地丢弃对象之前执行清除操作。例如，表示输入/输出连接的对象的finalize
     * 方法可执行显示I/O事务，以便在永久丢弃对象之前中断连接。
     *
     * Java编程语言不确保哪个线程将会调用finalize方法对于给定的对象。但可以保证在调用finalize时，调用finalize
     * 的线程将不会持有任何用户可见的同步锁定。如果finalize方法抛出任何未捕获的异常，此异常会被忽略，此对象的终结操作
     * 将终止。
     *
     * 在一个对象的finalize方法调用之后，将不会执行任何操作直到
     * 虚拟机在确定任何线程都访问不到该对象时，包括由准备终止的其他对象
     * 或类执行的可能操作，在执行该操作时，对象可能被丢弃。
     *
     * 对于任何给定对象，Java虚拟机不会调用多次。
     *
     * finalize方法抛出的任何异常都会停止对该对象的终结操作，但可以通过其他方法忽略他。
     *
     * @throws Throwable
     */
    public abstract void finalize2() throws Throwable;
}
