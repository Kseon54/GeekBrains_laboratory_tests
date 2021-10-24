package Test;

import Test.annotations.AfterSuite;
import Test.annotations.BeforeSuite;
import Test.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class TestStarted {

    public final static int MIN_PRIORITY_TEST = 1;
    public final static int MAX_PRIORITY_TEST = 10;

    private TestStarted() {
    }

    public static void start(Class aClass) {
        checkedValid(aClass);

        executingSingleMethodWithAnnotation(aClass, BeforeSuite.class);
        accomplishmentTests(aClass);
        executingSingleMethodWithAnnotation(aClass, AfterSuite.class);
    }

    private static void executingSingleMethodWithAnnotation(Class aClass, Class annotation) {
        try {
            Method method = getMethodByAnnotation(aClass, annotation).get(0);
            checkMethod(method, annotation);

            try {
                method.invoke(aClass.getDeclaredConstructor().newInstance());
            } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    private static void accomplishmentTests(Class aClass) {
        List<Method> tests = getMethodByAnnotation(aClass, Test.class);
        tests.forEach(test -> checkMethod(test, Test.class));
        tests.stream().map(method -> method.getAnnotation(Test.class)).forEach(test -> {
            if (test.priority() < MIN_PRIORITY_TEST || test.priority() > MAX_PRIORITY_TEST)
                throw new IllegalArgumentException("The priority should be from one to ten");
        });

        tests.stream()
                .sorted(Comparator.comparingInt(value -> (value.getAnnotation(Test.class)).priority()))
                .collect(Collectors.toList())
                .forEach(test -> {
                    try {
                        test.invoke(aClass.getDeclaredConstructor().newInstance());
                    } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static List<Method> getMethodByAnnotation(Class aClass, Class annotation) {
        return Arrays.stream(aClass.getMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }

    private static void checkedValid(Class aClass) {
        try {
            aClass.getConstructor();
        } catch (NoSuchMethodException ignored) {
            throw new RuntimeException("The class must contain an empty public constructor");
        }
        if (getMethodByAnnotation(aClass, BeforeSuite.class).size() > 1)
            throw new RuntimeException("A class must have at most one method with an annotation BeforeSuite");
        if (getMethodByAnnotation(aClass, AfterSuite.class).size() > 1)
            throw new RuntimeException("A class must have at most one method with an annotation AfterSuite");
    }

    private static void checkMethod(Method method, Class annotation) {
        if (method.getParameterCount() != 0) {
            throw new RuntimeException(
                    String.format("Methods with the annotation \"@%s\" should not contain parameters", annotation.getSimpleName())
            );
        }
        if (!method.getReturnType().getName().equals("void"))
            throw new RuntimeException("The return type must be void for methods with annotation @" + annotation.getSimpleName());
    }
}
