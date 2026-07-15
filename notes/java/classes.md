
#  КЛАССЫ

## 1. Обычный класс (Class)

**Что это:** Базовая единица ООП. Может наследоваться, реализовывать интерфейсы, иметь состояние (поля) и поведение (методы).

```java
public class User {
    // Состояние
    private String name;
    private int age;
    
    // Конструктор
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Поведение
    public void greet() {
        System.out.println("Hi, I'm " + name);
    }
}
```

**Особенности:**
- Может наследоваться (`extends`)
- Может реализовывать интерфейсы (`implements`)
- Может иметь конструкторы
- Может иметь состояние (поля)
- Можно создавать экземпляры через `new`

---

## 2. Интерфейс (Interface)

**Что это:** **Контракт** — описание того, что класс должен делать, без реализации (до Java 8). С Java 8+ может иметь дефолтные и static методы.

```java
public interface Drawable {
    // Абстрактный метод (без реализации)
    void draw();
    
    // Константа (всегда public static final)
    int MAX_SIZE = 100;
    
    // Дефолтный метод (с реализацией, Java 8+)
    default void resize(int size) {
        System.out.println("Resizing to " + size);
    }
    
    // Static метод (Java 8+)
    static Drawable getDefault() {
        return new Circle();
    }
    
    // Private метод (Java 9+) — для переиспользования внутри интерфейса
    private void log(String msg) {
        System.out.println(msg);
    }
}
```

**Особенности:**
- ✅ Класс может реализовывать **несколько** интерфейсов
- ✅ Все методы по умолчанию `public abstract`
- ✅ Все поля по умолчанию `public static final`
- ❌ Нельзя иметь состояние (не-static поля)
- ❌ Нельзя иметь конструкторы
- ❌ Нельзя наследоваться от класса (только от другого интерфейса)

**Когда использовать:**
- Определение контракта для нескольких классов
- Множественное наследование поведения
- Стратегия, Observer, Factory Method

**Пример множественного наследования:**
```java
public class Circle implements Drawable, Resizable, Printable {
    @Override
    public void draw() { /* ... */ }
    
    @Override
    public void resize(int size) { /* ... */ }
    
    @Override
    public void print() { /* ... */ }
}
```

---

## 3. Абстрактный класс (Abstract Class)

**Что это:** Неполный класс, который **нельзя инстанциировать**. Может содержать как абстрактные методы (без реализации), так и обычные.

```java
public abstract class Animal {
    // Состояние (в отличие от интерфейса!)
    protected String name;
    
    // Конструктор (вызывается из подкласса)
    public Animal(String name) {
        this.name = name;
    }
    
    // Обычный метод (с реализацией)
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    // Абстрактный метод (без реализации — подкласс ОБЯЗАН реализовать)
    public abstract void makeSound();
    
    // Защищенный метод
    protected void sleep() {
        System.out.println(name + " is sleeping");
    }
}

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}
```

**Особенности:**
- ✅ Может иметь состояние (поля)
- ✅ Может иметь конструкторы
- ✅ Может иметь обычные и абстрактные методы
- ✅ Может наследоваться только от **одного** класса
- ❌ Нельзя создать экземпляр через `new`

**Когда использовать:**
- Есть общая логика для группы классов
- Нужны поля и конструкторы
- Хочешь частично реализовать поведение

---

## 4. Сравнение: Интерфейс vs Абстрактный класс

| Характеристика | Интерфейс | Абстрактный класс |
|---|---|---|
| Наследование | Множественное | Только одно |
| Состояние (поля) | ❌ Только константы | ✅ Любые поля |
| Конструкторы | ❌ | ✅ |
| Модификаторы доступа | Только public | Любые |
| Абстрактные методы | Все (до Java 8) | Могут быть |
| Дефолтные методы | ✅ (Java 8+) | ✅ (обычные методы) |
| Static методы | ✅ (Java 8+) | ✅ |
| Когда использовать | Контракт, множественное наследование | Общая логика, состояние |

**Правило выбора:**
- Нужен **контракт** для разных классов → **Интерфейс**
- Нужна **общая база** с состоянием → **Абстрактный класс**
- Нужно **множественное наследование** → **Интерфейс**

---

## 5. Sealed классы (Java 17+)

**Что это:** Классы, которые **ограничивают**, кто может от них наследоваться. Повышают безопасность и позволяют использовать pattern matching.

```java
// Только эти классы могут наследоваться от Shape
public sealed class Shape permits Circle, Rectangle, Triangle {
    // ...
}

public final class Circle extends Shape { }        // final — нельзя дальше наследовать
public sealed class Rectangle extends Shape permits Square { } // sealed — можно ограничить дальше
public non-sealed class Triangle extends Shape { } // non-sealed — открыт для всех
```

**Три модификатора для наследников:**
- `final` — нельзя наследовать дальше
- `sealed` — снова ограничить список наследников
- `non-sealed` — открыть для всех (как обычный класс)

**Зачем нужно:**
- Контроль над иерархией
- Pattern matching (Java 17+):
```java
switch (shape) {
    case Circle c    -> System.out.println("Circle");
    case Rectangle r -> System.out.println("Rectangle");
    case Triangle t  -> System.out.println("Triangle");
}
```

**На LeetCode:** Пока встречается редко, но полезно знать для современных Java-проектов.

---

# 📦 NESTED КЛАССЫ

## Виды вложенных классов

```java
public class Outer {
    private int x = 10;
    
    // 1. Static nested class (статический вложенный класс)
    public static class StaticNested {
        public void show() {
            // ❌ Не имеет доступа к не-static полям Outer
            // System.out.println(x); // Ошибка!
            System.out.println("Static nested");
        }
    }
    
    // 2. Inner class (внутренний класс)
    public class Inner {
        public void show() {
            System.out.println(x); // ✅ Имеет доступ ко всем полям Outer
        }
    }
    
    // 3. Local class (локальный класс — внутри метода)
    public void method() {
        class Local {
            public void show() {
                System.out.println("Local");
            }
        }
        Local local = new Local();
    }
    
    // 4. Anonymous class (анонимный класс)
    public void test() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous");
            }
        };
    }
}
```

## Сравнение nested классов

| Тип | Static? | Доступ к Outer | Создание | Когда использовать |
|---|---|---|---|---|
| **Static nested** | ✅ | Только к static | `new Outer.StaticNested()` | Логически связан с Outer, но не нуждается в экземпляре |
| **Inner** | ❌ | Ко всем полям | `outer.new Inner()` | Нужен доступ к состоянию Outer |
| **Local** | ❌ | К final/effectively final переменным метода | Внутри метода | Временный класс для одного метода |
| **Anonymous** | ❌ | Как inner | `new Interface() { ... }` | Одноразовая реализация (часто для callback) |

**Важно для LeetCode:**
- **Static nested** — часто используется для `Node` в связных списках/деревьях:
```java
public class LinkedList {
    private static class Node { // static! не нужен доступ к List
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }
}
```
- **Anonymous** — часто встречается при создании компараторов:
```java
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
```

---

# 🛠️ STATIC — ПОЛЯ, МЕТОДЫ, БЛОКИ

## Static поля

**Что это:** Поля, которые принадлежат **классу**, а не экземпляру. Общие для всех объектов.

```java
public class Counter {
    // Static поле — общее для всех экземпляров
    private static int count = 0;
    
    // Не-static поле — свое для каждого экземпляра
    private int id;
    
    public Counter() {
        count++;          // Увеличиваем общий счетчик
        id = count;       // Присваиваем уникальный ID
    }
    
    public static int getCount() {
        return count;
    }
}

// Использование:
Counter c1 = new Counter(); // count = 1, c1.id = 1
Counter c2 = new Counter(); // count = 2, c2.id = 2
System.out.println(Counter.getCount()); // 2 (через класс)
System.out.println(c1.getCount());      // 2 (через экземпляр — но так НЕ делают!)
```

**Особенности:**
- Одно на класс (не на объект)
- Доступ через `ClassName.field`
- Инициализируется при загрузке класса
- Хранится в metaspace (не в heap)

---

## Static методы

**Что это:** Методы, которые принадлежат классу. **Не имеют доступа к `this`** и не-static полям.

```java
public class MathUtils {
    // Static метод — можно вызывать без создания объекта
    public static int add(int a, int b) {
        return a + b;
    }
    
    // ❌ НЕЛЬЗЯ использовать this
    public static void wrong() {
        // this.toString(); // Ошибка компиляции!
    }
    
    // ❌ НЕЛЬЗЯ вызывать не-static методы напрямую
    public static void alsoWrong() {
        // instanceMethod(); // Ошибка!
    }
    
    public void instanceMethod() {
        // ✅ А тут можно и static, и не-static
        int result = add(1, 2);
    }
}

// Использование:
int sum = MathUtils.add(2, 3); // Без new!
```

**Особенности:**
- Вызываются через `ClassName.method()`
- Не имеют `this`
- Не могут обращаться к не-static членам
- Не могут быть переопределены (только скрыты — см. ниже)

---

## Static блоки инициализации

**Что это:** Блок кода, который выполняется **один раз** при загрузке класса.

```java
public class Config {
    private static Map<String, String> settings;
    
    // Static блок — выполняется при загрузке класса
    static {
        settings = new HashMap<>();
        settings.put("timeout", "30");
        settings.put("retries", "3");
        System.out.println("Config loaded!");
    }
    
    // Обычный блок — выполняется при каждом создании объекта
    {
        System.out.println("Instance created!");
    }
}
```

**Порядок инициализации:**
1. Static поля и static блоки (при загрузке класса, один раз)
2. Не-static поля и блоки (при каждом `new`)
3. Конструктор

---

## Static nested классы

См. раздел [Nested классы](#nested-классы) выше.

---

# 🎯 ВИДЫ МЕТОДОВ

## 1. Абстрактные методы

**Что это:** Метод без реализации. **Обязан** быть реализован в подклассе.

```java
public abstract class Shape {
    // Абстрактный метод — нет тела
    public abstract double area();
    
    // Обычный метод
    public void draw() {
        System.out.println("Drawing shape with area: " + area());
    }
}

public class Circle extends Shape {
    private double radius;
    
    @Override // Обязаны переопределить!
    public double area() {
        return Math.PI * radius * radius;
    }
}
```

**Особенности:**
- Только в абстрактных классах и интерфейсах
- Нет тела метода
- Подкласс **обязан** реализовать (или сам стать абстрактным)
- Аннотация `@Override` — обязательна (для читаемости)

---

## 2. Дефолтные методы (Default methods)

**Что это:** Методы в интерфейсах с реализацией. Появились в Java 8.

```java
public interface Vehicle {
    void start(); // Абстрактный
    
    // Дефолтный метод — с реализацией
    default void stop() {
        System.out.println("Vehicle stopped");
    }
    
    // Еще один дефолтный
    default void honk() {
        log("Honking");
        System.out.println("Beep!");
    }
    
    // Private метод (Java 9+) — для переиспользования
    private void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
}

public class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car started");
    }
    
    // stop() и honk() наследуются автоматически
    
    // Можно переопределить:
    @Override
    public void honk() {
        System.out.println("CAR HONK!");
    }
}
```

**Зачем нужны:**
- Добавлять новые методы в старые интерфейсы без поломки кода
- Делать "опциональные" методы с реализацией по умолчанию

**Проблема ромбовидного наследования:**
```java
interface A { default void foo() { System.out.println("A"); } }
interface B { default void foo() { System.out.println("B"); } }

class C implements A, B {
    // ❌ Ошибка компиляции — какой foo вызывать?
    
    // ✅ Решение — переопределить явно:
    @Override
    public void foo() {
        A.super.foo(); // Или B.super.foo()
    }
}
```

---

## 3. Static методы

См. раздел [Static методы](#static-методы) выше.

**Важно:** Static методы **не переопределяются**, а **скрываются**:
```java
class Parent {
    public static void show() { System.out.println("Parent"); }
}
class Child extends Parent {
    public static void show() { System.out.println("Child"); }
}

Parent p = new Child();
p.show(); // "Parent"! (не "Child" — это не полиморфизм)
```

---

## 4. Final методы

**Что это:** Методы, которые **нельзя переопределить** в подклассе.

```java
public class Base {
    public final void criticalMethod() {
        System.out.println("Cannot be overridden");
    }
}

public class Derived extends Base {
    // ❌ Ошибка компиляции!
    // @Override
    // public void criticalMethod() { }
}
```

**Когда использовать:**
- Логика не должна меняться в подклассах
- Безопасность (например, в `String`)
- Оптимизация (компилятор может inline)

---

## 5. Private методы

**Что это:** Методы, доступные только внутри класса.

```java
public class Calculator {
    public int calculate(int a, int b) {
        validate(a, b); // Вызов внутри класса
        return a + b;
    }
    
    private void validate(int a, int b) {
        if (a < 0 || b < 0) throw new IllegalArgumentException();
    }
}
```

**В интерфейсах (Java 9+):** Private методы для переиспользования кода между дефолтными методами.

---

# 🔄 ПЕРЕГРУЗКА VS ПЕРЕОПРЕДЕЛЕНИЕ

## Перегрузка (Overloading)

**Что это:** Несколько методов с **одинаковым именем**, но **разными параметрами** в одном классе.

```java
public class Calculator {
    // Три метода с именем "add", но разными параметрами
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // ❌ НЕ перегрузка — только возвращаемый тип отличается!
    // public long add(int a, int b) { return a + b; } // Ошибка компиляции!
}

// Использование:
calc.add(1, 2);        // int add(int, int)
calc.add(1.5, 2.5);    // double add(double, double)
calc.add(1, 2, 3);     // int add(int, int, int)
```

**Правила:**
- ✅ Разное количество параметров
- ✅ Разные типы параметров
- ✅ Разный порядок типов
- ❌ Нельзя только по возвращаемому типу
- ✅ Может быть в одном классе
- ✅ Компилятор выбирает метод по параметрам

---

## Переопределение (Overriding)

**Что это:** Подкласс предоставляет **свою реализацию** метода, который уже есть в родительском классе.

```java
public class Animal {
    public void makeSound() {
        System.out.println("Some sound");
    }
}

public class Dog extends Animal {
    @Override // Аннотация обязательна (для проверки)
    public void makeSound() {
        System.out.println("Woof!");
    }
}

// Полиморфизм:
Animal a = new Dog();
a.makeSound(); // "Woof!" (вызывается метод Dog, а не Animal)
```

**Правила:**
- ✅ То же имя метода
- ✅ Те же параметры (сигнатура)
- ✅ Возвращаемый тип тот же или подтип (ковариантность)
- ✅ Модификатор доступа не строже (можно расширить, но не сузить)
- ❌ Нельзя переопределить `final` метод
- ❌ Нельзя переопределить `static` метод (только скрыть)
- ❌ Нельзя переопределить `private` метод (его нет в подклассе)
- ✅ Аннотация `@Override` — обязательна

---


