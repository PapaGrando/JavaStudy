import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;

class Scratch {
    public static void main(String[] args) {

        ClassToTest test = new ClassToTest();
        TestHelper.startTest(test);
    }
}

class ClassToTest {

    @BeforeSuit
    void method1() {
    }

    @Test(priority = 0)
    public void method2() {
    }

    @Test(priority = 1)
    private void method3() {
    }

    @Test(priority = 3)
    void method4() {
    }

    @AfterSuit
    void method5() {
    }

    @Test(priority =  1)
    void method6() {
    }
}

class TestHelper {
    static public void startTest(Object testClass) {
        new Tester(testClass);
    }
}

class Tester {
    private Object obj;

    private Class testedClass;

    private ArrayList<Method> testMethods = new ArrayList<>();
    private Method beforeSuitMethod = null;
    private Method afterSuitMethod = null;

    public Tester(Object testedClass) {
        this.obj = testedClass;
        this.testedClass = testedClass.getClass();
        getTestingMethods();
        test();
    }

    private void getTestingMethods() {
        Method[] methods = this.testedClass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.getAnnotation(Test.class) != null) {
                testMethods.add(method);
            } else if (method.getAnnotation(BeforeSuit.class) != null) {
                if (this.beforeSuitMethod != null)
                    throw new RuntimeException("several @BeforeSuit annotations");
                else
                    beforeSuitMethod = method;
            } else if (method.getAnnotation(AfterSuit.class) != null) {
                if (this.afterSuitMethod != null)
                    throw new RuntimeException("several @AfterSuit annotations");
                else afterSuitMethod = method;
            }
        }

        testMethods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));

        if (beforeSuitMethod != null) {
            testMethods.add(0, beforeSuitMethod);
        }
        if (afterSuitMethod != null) {
            testMethods.add(afterSuitMethod);
        }
    }


    void test() {

        try {
            for (Method method : testMethods) {
                if (Modifier.isPrivate(method.getModifiers()))
                    method.setAccessible(true);

                method.invoke(obj);
                System.out.println(method.getName() + " tested");
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test {
    int priority() default 0;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BeforeSuit {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AfterSuit {
}