package lacquerednocap.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Указывает, что метод должен быть выполнен после каждого теста.
 * Можно помечать несколько методов в одном классе, однако порядок выполнения может быть любым.
 * Если метод выбросит исключение, то оставшиеся тесты в классе не будут выполняться.
 * Методы, помеченные комбинацией из аннотаций {@link Before}, {@link Test} или {@link After},
 * не будут выполнены вообще.
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface After {
}
