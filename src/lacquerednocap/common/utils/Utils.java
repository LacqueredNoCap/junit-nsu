package lacquerednocap.common.utils;

import lacquerednocap.common.annotations.After;
import lacquerednocap.common.annotations.Before;
import lacquerednocap.common.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Method> getMethodsWithAnnotation(Class<?> source, Class<? extends Annotation> annotation) {
        List<Method> methods = new ArrayList<>();
        for (Method m : source.getDeclaredMethods()) {
            if (m.isAnnotationPresent(annotation)) {
                methods.add(m);
            }
        }
        return methods;
    }

    public static boolean methodHasIncompatibleAnnotations(Method m) {
        boolean before = m.isAnnotationPresent(Before.class);
        boolean test = m.isAnnotationPresent(Test.class);
        boolean after = m.isAnnotationPresent(After.class);

        return (before && test) || (before && after) || (test && after);
    }
}
