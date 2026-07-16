# Lambda выражение
- анонимная функция которую можно
- передать как параметр в функцию
- присвоить в переменную 
- вернуть из метода
- вызвать позже

#### Синтаксис
```java
(parameters) -> { body }

// без параметров
Runnable task = () -> System.out.println("Hello");
task.run();

// блок кода (нужен return)
Function<Integer, Integer> complex = x -> {
    int result = x * 2;
    result += 10;
    return result;
};
```

- лямбда функция принимает столько параметров сколько принимает **аюстрактный метод** соответсвующего **функционального интерфейса**
- то есть стандартно может быть 0, 1 или 2 параметра


# Функциональные интерфейсы
- интерфейс у которого ровно один абстрактный метод
- лямбда выражение это реализация этого абстрактного метода
```java
@FunctionalInterface
public interface MyInterface {
    // ✅ Ровно ОДИН абстрактный метод
    void doSomething(String param);

    // ✅ Можно иметь default методы (не считаются абстрактными)
    default void doSomethingElse() {
        System.out.println("Default");
    }

    // ✅ Можно иметь static методы
    static void staticMethod() {
        System.out.println("Static");
    }

    // ✅ Можно переопределять методы из Object (не считаются)
    @Override
    boolean equals(Object obj);
}
```

## Стандартные функциональные интерфейсы

### 1. Predicate<T>
- проверка условия
- метод  boolean test(T t) возвращает boolen (проверяет условие)

### 2. Function<T, R> 
- преобразование
- R apply(T t) - преобразовывает T в R

### 3. Consumer<T>
- потребитель
- void accept(T t) - принимает Т ничего не возвращает


### 4. Supplier<T>
- поставщик
- T get() - ничего не принимает возвращает Т

- эти интерфейсы для 1 параметра
- для 2 параметров есть интерфейсы с префиксом Bi



##### так же функциональными являются интерфейсы
- **Runnable**
- **Callable**
- **Comparable**
- **Comparator**





