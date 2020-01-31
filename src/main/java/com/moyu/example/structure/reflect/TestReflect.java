package com.moyu.example.structure.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by Joker on 19/8/24.
 */
public class TestReflect {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        /****
         *
         * 获取Class对象的三种方式
         *   其中第一种和第二种方法都是直接更具类名获取Class对象, 第二种方法具有如下优势
         *      * 编译阶段检查目标类是否存在, 更安全
         *      * 无需调用方法, 效率更高
         *   所以, 我们通常推荐使用第二种方式获取Class对象, 但是如果在编写程序的时候我们不知道
         *   这里产生哪个Class对象, 只知道有个类名会传入, 这种情况我们只能使用第一种方式获取Class对象,
         *   但这也是反射的精髓, 动态而灵活
         *
         *
         */

        // 第一种: 通过Class的静态方式forName("全路径")来获取, 但是会抛出ClassNotFoundException
        Class c1 = Class.forName("com.moyu.java.examples.reflect.Son");

        // 第二种: 通过类.class来获取
        Class c2 = Son.class;

        // 第三种: 通过getClass()方法
        Son son = new Son();
        Class c3 = son.getClass();


        System.out.println("c1 info: " + c1);
        System.out.println("c2 info: " + c2);
        System.out.println("c3 info: " + c3);


        System.out.println("===============================================");


        /****
         *
         * 好的, 我们既然拿到的Class对象, 那么一个对象里面肯定有成员变量, 那么如何获取成员变量呢?
         *
         *    通过运行我们知道:
         *      getFields:
         *         * 只能获取public修饰的成员变量, 包括父类的public成员变量(private, protected, default都获取不到)
         *
         *      getField:
         *         * 当我们想要获取指定的成员变量, 可以使用该方法, 但是该方法仅限调用当前和父类的public修饰的成员变量
         *           否则会抛出异常
         *
         *      getDeclaredFields:
         *         * 能获取到所有修饰符的成员变量, 但是仅限于本类的, 父类的成员变量无法获取
         *
         *      getDeclaredField:
         *         * 作用和getField一样(但仅限Class类对象的成员变量), 只不过他不论什么修饰符修饰的成员变量都可以获取
         *
         */

        Field[] fields = c1.getFields();
        for (Field f : fields) {
            System.out.println("getFields -> " + f);
        }

        System.out.println("===============================");

        Field[] fields1 = c1.getDeclaredFields();
        for (Field f : fields1) {
            System.out.println("getDeclaredFields -> " + f);
        }


        Field mSonBirthday = c1.getField("mSonBirthday");
        System.out.println("getField -> " + mSonBirthday);

        // 如果去获取一个没有全选访问的类, 就会抛出异常: java.lang.NoSuchFieldException: mSonAge
//        Field mSonAge = c1.getField("mSonAge");
//        System.out.println("getField -> " + mSonAge);


        Field mSonAge = c1.getDeclaredField("mSonAge");
        System.out.println("getField -> " + mSonAge);


        System.out.println("==============================================");


        /****
         *
         * 既然字段都获取到了, 肯定也可以获取到方法把, 答案是肯定的。
         *   其实获取方式想必大家也已经猜到了把。
         *
         *   getMethods:
         *      * 调用获取当前及父类对象的所有public修饰的方法, 但是是不是输出的有点多啊,hhhh。
         *        我们可以看到有很多java.lang.Object的方法, 当然这是肯定的, 所有的类都是继承Object
         *        所以会把Object对象的所有public方法输出, 那么上面的Field怎么没有? 这是因为Object对象没有成员变量
         *
         *   getDeclaredMethods:
         *      * 调用获取当前类(Class)所有的方法, 无论是什么修饰符修饰的...
         *
         *   方法当然也有和字段一样单独获取的方法, 用法几乎和Field是一样的, 只不过需要注意的是如果方法有参数
         *   需要把参数的class写入进去, 如果不写就找不到方法会抛出异常NoSuchMethodException
         *     getMethod
         *     getDeclaredMethod
         *
         *
         */

        Method[] methods = c1.getMethods();
        for (Method m : methods) {
            System.out.println("getMethods -> " + m);
        }

        Method[] methods1 = c1.getDeclaredMethods();
        for (Method m : methods1) {
            System.out.println("getDeclaredMethods -> " + m);
        }


        Method printSonMsg = c1.getMethod("printSonMsg");
        System.out.println("printSonMsg -> " + printSonMsg);

        // 没有权限访问的
        // java.lang.NoSuchMethodException: com.moyu.java.examples.reflect.Son.setSonName(java.lang.String)
//        Method setSonName = c1.getMethod("setSonName", String.class);
//        System.out.println("setSonName -> " + setSonName);


        // java.lang.NoSuchMethodException: com.moyu.java.examples.reflect.Son.setSonName()
        // 如果不写上参数类型, 就会抛出上面的异常
        Method setSonName = c1.getDeclaredMethod("setSonName", String.class);
        System.out.println("setSonName -> " + setSonName);



        System.out.println("===============================================");


        /*****
         *
         *  访问或操作类的私有变量和方法
         *    上面的程序我们成功获取了类的变量和方法, 验证了在运行动态获取信息的观点。
         *    那么, 仅仅只是获取信息吗?
         *
         *    我们知道, 对象是无法访问或操作类的私有变量和方法的, 但是通过反射就可以做到。
         *
         *    在访问私有方法的时候, 我们遇到了一些新的内容
         *      setAccessible:
         *         * 设置可以访问私有方法, 默认为false, 设置为true即可, 如果不设置去访问调用的话会抛出异常
         *           IllegalAccessException
         *      invoke:
         *        通过invoke调用私有方法, 但是这里需要传入一个实例对象以及具体的实参
         *
         *
         */
        Method privateMethod = c1.getDeclaredMethod("privateMethod", String.class, int.class);
        // 获取所有方法的访问权
        // 只是获取访问权，并不是修改实际权限
        privateMethod.setAccessible(true);
        // 使用invoke调用方法
        // son是要操作的对象
        // 后面两个参数传实参
        privateMethod.invoke(son, new Object[]{"xiaomoyu", 666});


        /****
         *
         * 我们可以调用私有方法, 但是可以修改私有变量的数据吗？
         *   当然可以, 通过获取到私有变量的Field对象使用set()来修改值。
         *   MSG字段是一个变量, 那么用final修饰的常量是否可以被修改？
         *
         *   被final修饰的成员变量真的可以修改吗?
         *     JVM在编译.java文件得到.class文件时, 会优化我们的代码提升效率。
         *     其中一个优化就是: JVM在编译阶段把常量的代码替换成具体的值, 如下:
         *
         *     编译前的.java文件
         *       private final String FINAL_VALUE = "hello"
         *       if (FINAL_VALUE.equals("world")) {
         *           //do something
         *       }
         *
         *     编译后得到的.class文件
         *       private final String FINAL_VALUE = "hello"
         *       // 替换为"hello"
         *       if ("hello".eqauls("world")) {
         *           // do something
         *       }
         *
         *     但是并不是所有常量都会优化, 经过测试对于 int/long/boolean/String这些基本类型JVM会优化
         *     而对于Integer/Long/Boolean这种包装类型, 或者其它诸如Date/Object类型则不会被优化
         *
         *     总结来说:
         *       对于基本类型的静态常量, JVM在编译阶段会把引用此常量的代码替换成具体的常量值
         *
         *     这么说来, 在实际开发中, 如果我们想修改某个类的常量值, 恰好哪个常量是基本类型的,
         *     岂不是无能为力？个人认为除非修改源码, 否则真没办法。。。。
         *
         *     这里指的无能为力是指:
         *        我们在程序运行的时候依然可以使用反射修改常量的值(下面的程序已经验证了)
         *        但是JVM在编译阶段得到的.class文件已经将常量优化成具体的值, 在运行阶段
         *        就直接使用具体的值了, 所以即使修改了常量的值也毫无意义了。
         *
         *
         *
         */

        Field msg = c1.getDeclaredField("MSG");
        msg.setAccessible(true);
        System.out.println("MSG 修改前 -> " + son.getMsg());

        msg.set(son, "Modified");
        System.out.println("MSG 修改后 -> " + son.getMsg());


        // 获取私有常量
        Field finalField = c1.getDeclaredField("FINAL_VALUE");
        finalField.setAccessible(true);
        System.out.println("finalField 修改前 -> " + finalField.get(son));
        System.out.println("finalField 修改前 -> " + son.getFinalValue());
        // 修改私有常量
        finalField.set(son, "XIAOMOYU2333");


        // getFinalValue()方法直接return "FINAL"
        // 同时也说明了, 程序运行时是更具编译后的.class来执行的
        System.out.println("finalField son对象调用 修改后 -> " + son.getFinalValue());
        //使用对象调用类的 getter 方法
        //获取值并输出
        System.out.println("finalField 修改后 -> " + finalField.get(son));


        Field ag = c1.getDeclaredField("AG");
        ag.setAccessible(true);
        System.out.println("ag 修改前 -> " + ag.get(son));
        ag.set(son, 2000);
        System.out.println("ag son对象调用 修改后 -> " + son.getAGValue());
        System.out.println("ag 修改后 -> " + ag.get(son));


        Field ac = c1.getDeclaredField("AC");
        ac.setAccessible(true);
        System.out.println("ac 修改前 -> " + ac.get(son));
        // 由于AC这个常量是非基本类型所以没有被JVM优化，可以被修改
        ac.set(son, 5000);
        System.out.println("ac son对象调用 修改后 -> " + son.getACValue());
        System.out.println("ac 修改后 -> " + ac.get(son));


        System.out.println("====================================================");


        /****
         *
         * 常量真的就没办法修改了???
         *   刚才的常量都是在声明的时候就直接赋值了，可能会疑惑，常量不都是在声明时赋值的吗？不赋值不报错？当然不是
         *
         *   方法一：
         *     Java允许我们声明常量的时候不赋值，但必须在构造函数中赋值。
         *     当我们通过IDEA打开对应的class文件就可以看到getNAMEValue()方法指向是一个变量, 而不在是一个固定的值
         *
         *     解释一下:
         *       我们将赋值放在构造函数中, 构造函数是我们运行时new对象才会调用的, 所以不会像周期爱你直接为常量赋值那样
         *       在编译接阶段将getFinalValue()方法化为常量值返回, 而是指向NAME, 这样我们在运行阶段反射修改值就有意义
         *
         *       通过.class文件还是可以看得出程序是有优化的, 将构造函数中的赋值语句优化了。。。。
         *
         *
         *   方法二:
         *     不使用构造方法, 也可以修改常量的值, 但原理都一样, 使用三目运算符赋值也一样。
         *     private final String OP_VALUE = null == null ? "OP" : null;
         *
         *     上述的代码等价直接为OP_VALUE赋值OP, 但是就是可以, 至于为什么？
         *     你想想: null == null ? "OP" : null 是在运行时刻计算的, 在编译时刻不计算,
         *     也就不会被优化, 所以打开.class文件我们也会发现getOPValue()返回的是一个常量, 而非一个固定值
         *
         *
         *   需要注意的是:
         *     如果是private static final 修饰的成员变量想要修改值, 需要比上述代码多一些步骤
         *     Field modifiers1 = Field.class.getDeclaredField("modifiers");
         *     modifiers1.setAccessible(true);
         *     modifiers1.setInt(staticint, staticint.getModifiers() & ~Modifier.FINAL);
         *
         *     那么static final修饰的和直接被final修饰的成员变量能不能被修改是一样的, 只不过是多了个static
         *     JVM还是一样编译。
         *
         *
         *
         *  最后:
         *    无论是直接为常量赋值、通过构造方法为常量赋值还是使用三目运算符, 实际上我们都能通过反射成功修改常量的值
         *    而我们上面说的修改"成功"与否是指: 我们在程序运行阶段通过反射肯定能修改常量值, 但是实际执行优化后的.class文件时
         *    修改后的值真起到作用了吗？换句话说, 如果编译后的.class文件常量值被替换成具体的值，那么反射在怎么修改常量的值
         *    都不会影响最终的结果。。。
         *
         */

        Field name = c1.getDeclaredField("NAME");
        name.setAccessible(true);
        System.out.println("name 修改前 -> " + name.get(son));
        // NAME常量是在构造函数中赋值的
        // 可以看到String已经被修改了
        name.set(son, "XIAOMOYU");
        System.out.println("name son对象调用 修改后 -> " + son.getNAMEValue());
        System.out.println("name 修改后 -> " + name.get(son));





        Field op = c1.getDeclaredField("OP");
        op.setAccessible(true);
        System.out.println("op 修改前 -> " + op.get(son));
        op.set(son, "OPS");
        System.out.println("op son对象调用 修改后 -> " + son.getOPValue());
        System.out.println("op 修改后 -> " + op.get(son));



        Field teststatic = c1.getDeclaredField("TESTSTATIC");
        teststatic.setAccessible(true);

        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(teststatic, teststatic.getModifiers() & ~Modifier.FINAL);

        System.out.println("teststatic 修改前 -> " + teststatic.get(son));
        teststatic.set(son, "OPS");
        System.out.println("TESTSTATIC son对象调用 修改后 -> " + son.getTESTSTATICValue());
        System.out.println("TESTSTATIC 修改后 -> " + teststatic.get(son));


        Field staticint = c1.getDeclaredField("STATICINT");
        staticint.setAccessible(true);

        // 由于我们使用了static final
        // 不可以和上面的代码一样就直接修改了, 需要先把final 去除了
        // 并设置字段可访问
        Field modifiers1 = Field.class.getDeclaredField("modifiers");
        modifiers1.setAccessible(true);
        modifiers1.setInt(staticint, staticint.getModifiers() & ~Modifier.FINAL);

        System.out.println("staticint 修改前 -> " + staticint.get(son));
        staticint.set(son, 9000);
        System.out.println("staticint son对象调用 修改后 -> " + son.getSTATICINTValue());
        System.out.println("staticint 修改后 -> " + staticint.get(son));


    }
}
