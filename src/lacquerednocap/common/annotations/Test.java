package lacquerednocap.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Используется для метки тестируемых методов.
 * Методы, помеченные комбинацией из аннотаций {@link Before}, {@link Test} или {@link After},
 * не будут выполнены вообще.
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

    /**
     * <p> Класс ожидаемого исключения. </p>
     * Тест пройден успешно, если:
     * <ul>
     *  <li> не было выброшено исключение во время выполнения метода. </li>
     *  <li> Указано ожидаемое исключение и оно было выброшено </li>
     * </ul>
     * Тест не пройден, если:
     * <ul>
     *  <li> Указано ожидаемое исключение, но в результате выполнения было получено другое.
     *  (Класс исключения и его наследники не считаются равными)</li>
     *  <li> Указано ожидаемое исключение, но оно не было выброшено </li>
     *  <li> Не указано ожидаемое исключение и было выброшено любое исключение </li>
     * </ul>
     */
    Class<? extends Throwable> expectedException() default DefaultException.class;

    class DefaultException extends Throwable {}
}

