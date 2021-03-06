package lambda.lambda_exec;

/**
 * @Author: fujing
 * @Date: 2022/1/5
 * @Description:
 * @Version: 1.0
 */

import java.util.concurrent.Callable;
import java.util.function.*;

/**
 * 根据函数式接口中 抽象方法参数(个数)，以及抽象方法实现方法体的逻辑量，lambda会有多种写法：
 *  ()->{}
 *  ()->{System.out.println(1);}
 *  ()->{return 100;}
 *  ()->100
 *  ()->null
 *  (int x)->{return x+1;}
 *  (int x)->x+1
 *  (x)->x+1
 *  x->x+1
 *
 */
public class LambdaTest {
    public static void main(String[] args) throws Exception {
        /**
         * 以函数式接口 Runnable为例，展示 lambda写法：
         *    1.以下v1、v2、v3 三种写法，是等价的
         *    2.Runnable 的 run()方法没有返回值
         */
        /*v1*/
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("running1....");
            }
        };
        runnable1.run();
        /*v2*/
        Runnable runnable2 = () -> {
            System.out.println("running2....");
        };
        runnable2.run();
        /*v3*/
        Runnable runnable3 = () -> System.out.println("running3....");
        runnable3.run();

        //--------------------------------------------------------------------

        /**
         * 函数式接口 Callable 中的 call()方法，有返回值
         */
        Callable callable = new Callable() {
            @Override
            public String call() throws Exception {
                return "call...";
            }
        };
        System.out.println(callable.call());

        Callable callable1 = ()->{return "call1...";};
        System.out.println(callable1.call());

        Callable callable2 = ()->"call2...";
        System.out.println(callable2.call());

        //-------------------------------自定义函数式接口：打印返回值-------------------------------------
        //v1
        LambdaInterface lambdaInterface = new LambdaInterface() {
            @Override
            public int getInt() {
                return 100;
            }
        };
        System.out.println(lambdaInterface.getInt());
        //v2
        LambdaInterface lambdaInterface1 = ()->{return 100;};
        System.out.println(lambdaInterface1.getInt());
        //v3
        LambdaInterface lambdaInterface2 = ()->100;
        System.out.println(lambdaInterface2.getInt());
    //-------------------------------自定义函数式接口：带一个入参，打印方法返回值-------------------------------------
        System.out.println("-----------以下三种写法等价----------------");
        //v1
        LambdaInterface2 interface2 = new LambdaInterface2() {
            @Override
            public int getInt(String x) {
                int a = Integer.parseInt(x);
                return a;
            }
        };
        System.out.println(interface2.getInt("101"));
        //v2
        LambdaInterface2 interface21 = (x)->{
            int a = Integer.parseInt(x);
            return a;
        };
        System.out.println(interface21.getInt("102"));

        //v3
        LambdaInterface2 interface22 = x -> {
            int a = Integer.parseInt(x);
            return a;
        };
        System.out.println(interface22.getInt("103"));

        //-------------------------------自定义函数式接口：StudentDao ：函数式接口抽象方法有参数，有返回值--------------------

        System.out.println("-----------以下三种写法等价----------------");
        StudentDao studentDao = new StudentDao() {
            @Override
            public Student insertStudent(Student student) {
                return student;
            }
        };
        System.out.println(studentDao.insertStudent(new Student("zhangsan")));

        //v2
        StudentDao studentDao1 = (s)-> {return s;};
        System.out.println(studentDao1.insertStudent(new Student("李四")));
        //v3
        StudentDao studentDao2 = (s)-> s;
        System.out.println(studentDao2.insertStudent(new Student("王五")));

        //-------------------------------自定义函数式接口：StudentDao ：函数式接口抽象方法有参数，无返回值---------------------
        System.out.println("-----------以下三种写法等价----------------");
        TeacherDao teacherDao = new TeacherDao() {
            @Override
            public void insterTeacher(Teacher teacher1) {
                System.out.println("insert teacher1---"+teacher1);
            }
        };
        teacherDao.insterTeacher(new Teacher());
        //v2
        TeacherDao teacherDao1 = (teacher2) -> {
            System.out.println("insert teacher2---"+teacher2);
        };
        teacherDao1.insterTeacher(new Teacher());
        //v3
        TeacherDao teacherDao2 = (t3) -> System.out.println("inster teacher3---" + t3);
        teacherDao2.insterTeacher(new Teacher());

        //-------------------------------Java API中常用的函数式接口 使用---------------------

        /**
         * 在Java中，提供了一系列函数式接口，用来接收后续传入的逻辑，但对输入和输出有要求。
         *
         *  Java api中常见的函数式接口，有：
         *
         *  Supplier<T>: 代表有一个输出的函数，可以作为lambda表达式或方法引用的赋值对象；
         *
         *  Consumer<T>: 代表接收一个输入，无返回值的一个函数；
         *  BiConsumer<T, U> : 代表接收两个输入，无输出的函数
         *
         *  Function<T, R>: 代表有一个输入和一个输出的函数，一般输入和输出是不同类型的；
         *  BiFunction<T, U, R>: 代表有两个输入，一个输出的函数，一般输入和输出是不同类型的
         *
         *  UnaryOperator<T>: 代表有一个输入，一个输出的函数，输入和输出是相同类型的。
         *  BinaryOperator<T> : 代表两个输入和一个输出，输入和输出是相同类型的。 //BinaryOperator<T> extends BiFunction<T,T,T>
         *
         */

        Supplier<String> supplier = ()->{return "test Str";};  //该语句含义：用一个函数式接口对象，去接收了一段接口抽象方法的实现逻辑，但并未执行
        System.out.println("supplier return result---"+supplier.get());

        Consumer<Integer> consumer = (a) -> System.out.println("hello consumer:" + a);
        consumer.accept(1314520);

        BiConsumer<Integer,Integer> biConsumer = (t1,t2)-> System.out.println("consumer accpte:" + (t1 + t2));
        biConsumer.accept(1, 2);


        Function<String,Integer> function = (String x) ->{int a = 0; a = Integer.parseInt(x);return a;};
        System.out.println("funtion return int value---"+function.apply("123"));

        BiFunction<String,Integer,Integer> biFunction = (a,b) -> {
            int temp = 0;
            int sum = 0;
            if (!"".equals(a)) {
                temp = Integer.parseInt(a);
            }
            sum = temp + b;
            return sum;
        };
        System.out.println("biFunction return results---"+biFunction.apply("789", 1));

        UnaryOperator<String> unaryOperator = (y)-> {return y;};
        System.out.println("unaryOperator return result---" + unaryOperator.apply("456"));

        BinaryOperator<String> binaryOperator = (a, c) -> a + c;
        System.out.println("binaryOperator return---" + binaryOperator.apply("1", "2"));

    }
}
