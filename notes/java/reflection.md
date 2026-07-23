# Reflection API
- механизм позволяющий программе исследовать и модифицировать свое поведение в runtime
- Reflection API очень старое поэтому можно пользовать сторонними библиотеками рефлексии



## Способы получения объекта Class

```java
//1. используется когда класс известен на момент написания кода
Class<User> passport = User.class;

//2. через метод .getClass() (Динамический)
// используется когда надо узнать точный тип объекта (полиморфизм)
User user = new AdminUser(); // AdminUser наследуется от User
Class<? extends User> passport = user.getClass();

// 3. через Class.forName()
// используется когда класс неизвестен на момент компиляции а имя класса приходит в виде строки (например из конфиг файла или из бд)
// Загружаем класс просто по его имени в виде строки
Class<?> passport = Class.forName("com.example.User");
```

### с помощью объекта Class можно узнать все о объекте и классе
```java
Class<User> clazz = User.class;

// 1. Узнать имя класса
String name = clazz.getName(); // "com.example.User"

// 2. Посмотреть все поля (переменные) класса
Field[] fields = clazz.getDeclaredFields(); 

// 3. Посмотреть все методы
Method[] methods = clazz.getDeclaredMethods();

// 4. Узнать, от какого класса он наследуется
Class<?> superClass = clazz.getSuperclass();

// 5. Посмотреть реализованные интерфейсы
Class<?>[] interfaces = clazz.getInterfaces();

// 6. Прочитать аннотации (те самые "наклейки")
Annotation[] annotations = clazz.getAnnotations();

// 7. Создать новый объект этого класса (если есть пустой конструктор)
// (В новых версиях Java лучше использовать clazz.getDeclaredConstructor().newInstance())
User newUser = clazz.getDeclaredConstructor().newInstance(); 
```
- по умолчанию доступ опрделяется уровнем видимости
- можно сделать private член класса доступным через setAccessible(true)



## Class literals
- литерал - строка в коде которая пораждает объект или значение
- ("f" литерал пораждающией объект String с содержимым "f")
- литреалы это числа, строки, boolean значения (true false)
- литералы классов - специльные выражения пораждающие объекты с типом Class<...>
```java
Class<String> c1 = String.class;
Class<Integer> c2 = Integer.class;
Class<Integer> c3 = int.class; // так тоже можно
```
- так же есть странные имена классов массивов примитивов


