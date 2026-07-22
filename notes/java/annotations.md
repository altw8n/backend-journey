# Annotations
- способ разметки кода, добавление метаданных к коду
- можно создавать свои аннотации
- могут быть обработаны на трех этапах:
1. на этапе чтения java файла (annotation processing)
2. на этапе инструментации байт кода
3. в runtime (reflection API)

## Синтаксис создания собственной аннотации
- специальный вид интерфейсов
- наследуются от java.lang.annotation.Annotation
- методы не могут иметь параметров
- нельзя наследлваться

```java
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface MyService {
    String value(); // Обязательный параметр
    int priority() default 0; // Параметр со значением по умолчанию
    String[] tags() default {}; // Массив
}
```
### Допустимые возвращаемые методами типы 
- примитивы (byte, short, int и т.д)
- enum
- String
- Class
- другие аннотации
- массивы из вышеперечисленного

## Встроенные аннотации
- @Override
- @FunctionalInterface
- @SafeVarargs
- @Deprecated - помечает элемент API как устаревший чтобы его не использовали в новых версиях (он существует для обратной совместимости)
- @@SuppressWarnings - игнорировать ворнинги
- @Generated - код сгенерирован (его не стоит менять т.к. потом сгенерируется новый)



