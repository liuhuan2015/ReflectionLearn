# ReflectionLearn
>在大学学习java的时候就接触过反射(Reflection)，但是只会一些简单、基本的用法，工作后做的是android开发，感觉反射很少用到，虽然我也知道它很重要，最近在看代理模式，需要熟悉反射，然后发现自己反射这块忘记的差不多了。嗯，就是这样。

#### 一 什么是反射?
下面贴一段官方文档介绍:
>Reflection is commonly used by programs which require the ability to examine or modify the runtime behavior of applications running in the Java virtual machine. This is a relatively advanced feature and should be used only by developers who have a strong grasp of the fundamentals of the language. With that caveat in mind, reflection is a powerful technique and can enable applications to perform operations which would otherwise be impossible.<br>

用通俗的的汉语表述，大意是：

>反射是一个很牛的功能，它能够在程序运行的时候修改程序的行为，但是反射是一种非常规手段，使用有风险，需谨慎。

#### 二 Class概念
java是面向对象的语言，基本上是以类为基础构造了整个程序系统，反射中需要提供的规格说明书其实就是一个类的规格说明书，它就是Class。<br>
这里的Class是首字母大写的，它不同于class首字母小写，class是定义一个类的关键字，而Class本质也是一个类。<br>
Class就是一个对象，它用来代表运行在就java虚拟机中的类和接口。如果把Java虚拟机比作高速公路，那么Class就是用来描述在公路上行驶的汽车的，即汽车的规格说明书。

    public final class Class<T> implements java.io.serializable,GenericDeclaration,Type,AnnotatedElement{ }
  
#### 三 Class对象的获取
反射的入口是Class，但是Class是没有公开的构造方法的，所以就没有方法像构建一个类一样通过new关键字来获取一个Class对象。java中提供了Class对象的三种获取方式。

##### (1) 通过Object.getClass()，这种方式不适合基本类型如：int，float等。

    Car car = new Car();
    Class clazz=car.getClass();
    
##### (2) 通过.class获取
在（1）中是通过一个类的实例获得它的Class，如果不想创建这个类的实例的话，就需要通过.class这个方法。

     Class clz1 = float.class;

     Class clz2 = Void.class;

     Class clz3 = new int[]{}.getClass();

     Class clz4 = new Car[]{}.getClass();
     
##### (3) 通过Class.forName()方法
有的时候我们没有办法创建一个类的实例，甚至没有办法用Car.class这样的方式获取到Class对象。例：有些类被加上了@hide注解，这些类会被隐藏，开发者无法直接使用它。
此时我们就可以使用Class.forName()这个方法来获取其Class对象。

     Class clz = Class.forName("com.liuh.reflectionlearn.Car");
     
#### 四 Class内容清单
我们获取Class对象的最终目的是获取其中的内容。
##### (1) Class的名字
具体的结果可见项目测试代码。
Class.getName():

        Class clz3 = new int[]{}.getClass();
        System.out.println(clz3.getName());
        
Class.getSimpleName():

        Class clz = Outter.Inner.class;
        System.out.println("Inner Class name:" + clz.getName());
        System.out.println("Inner Class simple name:" + clz.getSimpleName());
        
getCanonicalName():返回一个Class对象的官方名字。

##### (2) Class的成员
一个类的成员包括属性和方法，对应到Class中就是Field，Method，Constructor。

###### ❶ Field的获取以及操控

public Field getField(String name);//获取public属性，当前类没有时会向祖先类获取

public native Field getDeclaredField(String name);//可获取到private属性，但是不会获取到从祖先类继承下来的属性

public Field[] getFields();//获取所有的public属性，当前类没有时会向祖先类获取

public native Field[] getDeclaredFields();///获取所有的属性，可获取到private属性，但是不会获取到从祖先类继承下来的属性

具体的使用代码见工程项目。

当我们拿到一个Field（属性）时，我们可以获取到它的名称、类型<br>
    
    Class clazz = Son.class;
        Field[] fields = clazz.getFields();

        for (Field field : fields) {
            System.out.println("Field Name:" + field.getName());
            System.out.println("Field type:" + field.getType());
            System.out.println("Field generic type:" + field.getGenericType());//能够获取到泛型类型
            }
        
可以获取到它的修饰符<br>
    public int getModifiers();
    
我们拿到Field最重要的目的是：进行Field内容的读取和赋值。



        






