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
        
可以获取到它的修饰符

        public int getModifiers();
我们拿到Field最重要的目的是：进行Field内容的读取和赋值。具体的代码编写和测试见工程能项目。

        A testa = new A();
        testa.a = 10;
        Class c = A.class;
        try {
            Field fieldA = c.getField("a");//获取属性
            int ra = fieldA.getInt(testa);//获取属性值
            System.out.println("reflection testa.a=" + ra);//打印
            fieldA.setInt(testa, 15);//赋值
            System.out.println("testa.a=" + testa.a);//打印
            } catch (NoSuchFieldException e) {
            e.printStackTrace();
            }
        
###### ❷ Method的获取以及操控
一个方法由下面几个要素构成：方法名称、方法参数、方法返回值、方法的修饰符、方法可能抛出的异常。反射提供了相应的api来获取这些要素。此处只简要展示一下方法的执行，使用反射的方式。

    public native Object invoke(Object obj, Object... args)
第一个参数obj是Method所依附的Class对应的类的实例，如果这个方法是一个静态方法，则obj为null，后面对应的是方法的参数。<br>

invoke()返回的结果是Object类型的，所以使用的时候一般要进行强制转换。

在对Method 调用 invoke() 的时候，如果方法本身会抛出异常，那么这个异常会被包装，由Method 统一抛出 InvocationTargetException。而通过 InvocationTargetException.getCause() 可以获取真正的异常。

        //非静态方法的调用
        MethodInvokeTestModel model = new MethodInvokeTestModel();

        try {
            Method method2_nonstatic = clazz.getDeclaredMethod("add", int.class, int.class);
            method2_nonstatic.setAccessible(true);
            int result = (int) method2_nonstatic.invoke(model, 5, 11);
            System.out.println("result : " + result);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
###### ❸ Constructor的获取以及操控
Constructor，构造方法，在反射机制中把它和Method分离开了，单独使用Constructor这个类表示。Constructor和Method差不多，但是还是有区别的，如：它能够创建一个对象。

    Class clazz = ConstructorTestModel.class;
        try {
            System.out.println(clazz.newInstance().toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Constructor constructor = clazz.getConstructor(String.class);
            ConstructorTestModel model = (ConstructorTestModel) constructor.newInstance("我是小明,找我干啥?");
            System.out.println(model.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

在java反射机制中，有两种方法可以用来创建类的实例对象：Class.newInstance()和Constructor.newInstance()。官方文档建议开发者使用见面这种方法，原因如下：<br>
1.Class.newInstance()只能调用无参的构造方法，而Constructor.newInstance()则可以调用任意的构造方法。

2.Class.newInstance()通过构造方法直接抛出异常，而Constructor.newInstance()会把抛出的异常包装到InvocationTargetException中去，这个和Method行为一致。

3.Class.newInstance()要求构造方法能够被访问，而Constructor.newInstance()却能够访问private修饰的构造方法。

###### ❹ 反射中的数组和枚举
java反射机制还另外细分了两个概念：数组和枚举。
数组本质上是一个Class，在Class中存在一个方法用来判断它是否是一个数组

        public native boolean isArray();
我们可以使用反射动态的创建数组，然后进行读取和赋值

        try {
            Shuzu shuzu = (Shuzu) clz.newInstance();

            //获取array属性
            Field arrayF = clz.getDeclaredField("array");
            arrayF.setAccessible(true);

            //创建一个数组对象，数组长度为3,并赋值
            Object o = Array.newInstance(int.class, 3);
            Array.set(o, 0, 1);
            Array.set(o, 1, 3);
            Array.set(o, 2, 5);

            //对对象中的属性赋值
            arrayF.set(shuzu, o);

            //打印
            int[] array = shuzu.getArray();
            for (int i = 0; i < array.length; i++) {
                System.out.println("array index : " + i + " , value : " + array[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
枚举这里就不写了，具体可见工程代码。

#### 总结
文章开头，用自动驾驶来比喻反射，实际目的是为了使初学者对反射有一个大体的印象和一个模糊的认知，实际上反射不是自动驾驶，它是什么取决于我们自己对它的理解。<br>
1.java中反射是一种非常规编码方式。

2.java中反射机制的操作入口是获取Class对象，有Class.forName(...),.class,object.getClass()三种方式。

3.一般我们获取Class对象的目的都是为了获取它的Field或者Method或者Constructor，然后进行一些操作。

4.Field操作主要涉及到类别的获取，以及数值的读取和赋值。

5.Method算是反射机制中最核心的内容，通常的反射都是为了调用某个Method的invoke方法。

6.通过Class.newInstance()和Constructor.newInstance()可以创建类的实例对象，但是推荐后者。

7.数组和枚举可以被看成普通的Class对待

需要注意的是：反射是非常规开发手段，它会抛弃java虚拟机的很多优化，所以同样功能的代码，使用反射会比使用正常方式慢。所以在考虑使用反射时，要考虑到它的时间成本。就如无人驾驶，用着很爽，但是也会有一些风险。


        






