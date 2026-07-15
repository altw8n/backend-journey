#Примитивные типы данных
##8 Примитивных типов (Primitive Types)
Примитивы хранят само значение в памяти (в стеке). Они не являются объектами.
| тип | размер | диапазон значений |
|byte | 1 byte (8 bit) | -128...127 (2^7...2^7 - 1) | 
|short | 2 byte (16 bit) | (2^15...2^15 - 1) | 
|int | 4 byte (32 bit) | (2^31...2^31 - 1) | 
|long | 8 byte (64 bit) | (2^63...2^63 - 1 )| 

|float | 4 byte  | IEEE | 
|double | 8 byte  | IEEE |

|boolean | 1 bit | true/false |
|char | 2 byte | 0...65000 (один символ unicode)|

##Управляющие конструкции
'''java
int age = 18;

if (age < 18) {
    System.out.println("Доступ запрещен");
} else if (age == 18) {
    System.out.println("Вам только исполнилось 18");
} else {
    System.out.println("Доступ разрешен");
}'''

#Логические операторы
'''java
boolean x = true;
boolean y = false;

boolean and = (x && y); // false (И. Верно, только если ОБА true)
boolean or = (x || y);  // true  (ИЛИ. Верно, если ХОТЯ БЫ ОДИН true)
boolean not = (!x);     // false (НЕ. Инверсия)'''


##Оператор switch
'''java
int dayOfWeek = 3;

// Классический синтаксис
switch (dayOfWeek) {
    case 1:
        System.out.println("Понедельник");
        break; // Обязательно! Иначе выполнится следующий case
    case 2:
        System.out.println("Вторник");
        break;
    case 3:
        System.out.println("Среда");
        break;
    default:
        System.out.println("Другой день");
}

// Современный синтаксис (Java 14+, без break, удобнее)
String dayName = switch (dayOfWeek) {
    case 1 -> "Понедельник";
    case 2 -> "Вторник";
    case 3 -> "Среда";
    default -> "Выходной";
};'''


##Циклы
###for
'''java
// Вывести числа от 0 до 4
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}'''

###for each
'''java
int[] numbers = {10, 20, 30, 40};

for (int number : numbers) { // "Для каждого number в numbers"
    System.out.println(number);
}'''

###while
'''java 
int count = 0;
while (count < 5) {
    System.out.println(count);
    count++;
}'''


##String
String — это не примитив, а ссылочный тип (объект). Строки в Java неизменяемы (immutable). Любое изменение создает новую строку в памяти.

НИКОГДА не использовать == для строк! == сравнивает ссылки на объекты в памяти, а не их содержимое

'''java
String a = "Java";
String b = "Java";
String c = new String("Java");

System.out.println(a == b); // true  (потому что они из пула строк)
System.out.println(a == c); // false (потому что c создан через new)

// ПРАВИЛЬНО: использовать метод .equals()
System.out.println(a.equals(c)); // true'''

###String Pool
Чтобы экономить память, Java использует Пул строк (String Constant Pool) — специальную область в памяти (в куче/Heap), где хранятся все строковые литералы.
'''java
String s1 = "Hello"; // Java проверяет пул. Если "Hello" там нет, она создает её и кладет в пул.
String s2 = "Hello"; // Java видит, что "Hello" уже есть в пуле, и просто направляет s2 на тот же объект.

String s3 = new String("Hello"); // Ключевое слово new ВСЕГДА создает новый объект в куче, вне пула.'''





##StringBuilder

Поскольку String неизменяем, частое изменение строк в цикле убивает производительность (создается мусор в памяти).
'''java
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(" ");
sb.append("World");
sb.insert(5, ","); // "Hello, World"

String result = sb.toString(); // Превращаем обратно в String'''


###String API

## 🔍 Поиск и проверка

| Метод | Описание | Пример | Результат |
|-------|----------|--------|-----------|
| `length()` | Возвращает длину строки | `"Java".length()` | `4` |
| `isEmpty()` | Проверяет, пустая ли строка (длина = 0) | `"".isEmpty()` | `true` |
| `isBlank()` | Проверяет, пустая или состоит из пробелов | `"   ".isBlank()` | `true` |
| `charAt(int index)` | Возвращает символ по индексу | `"Java".charAt(1)` | `'a'` |
| `indexOf(String str)` | Индекс первого вхождения | `"Hello".indexOf("l")` | `2` |
| `lastIndexOf(String str)` | Индекс последнего вхождения | `"Hello".lastIndexOf("l")` | `3` |
| `contains(CharSequence s)` | Проверяет наличие подстроки | `"Hello".contains("ell")` | `true` |
| `startsWith(String prefix)` | Начинается ли с подстроки | `"Hello".startsWith("He")` | `true` |
| `endsWith(String suffix)` | Заканчивается ли на подстроку | `"Hello".endsWith("lo")` | `true` |

## ✂️ Извлечение частей

| Метод | Описание | Пример | Результат |
|-------|----------|--------|-----------|
| `substring(int beginIndex)` | Подстрока от индекса до конца | `"Hello".substring(2)` | `"llo"` |
| `substring(int begin, int end)` | Подстрока от begin до end (не включительно) | `"Hello".substring(1, 4)` | `"ell"` |
| `toCharArray()` | Преобразует в массив символов | `"Hi".toCharArray()` | `['H', 'i']` |
| `getBytes()` | Преобразует в массив байтов | `"Hi".getBytes()` | `[72, 105]` |

## 🔄 Изменение (возвращают новую строку)

| Метод | Описание | Пример | Результат |
|-------|----------|--------|-----------|
| `concat(String str)` | Конкатенация (склеивание) | `"Hi".concat("!")` | `"Hi!"` |
| `replace(char old, char new)` | Заменяет все вхождения символа | `"banana".replace('a', 'o')` | `"bonono"` |
| `replace(CharSequence old, CharSequence new)` | Заменяет все вхождения подстроки | `"Java".replace("va", "vaScript")` | `"JavaScript"` |
| `replaceAll(String regex, String replacement)` | Заменяет по regex | `"a1b2".replaceAll("\\d", "#")` | `"a#b#"` |
| `replaceFirst(String regex, String replacement)` | Заменяет первое вхождение по regex | `"a1b1".replaceFirst("1", "#")` | `"a#b1"` |
| `toLowerCase()` | В нижний регистр | `"JAVA".toLowerCase()` | `"java"` |
| `toUpperCase()` | В верхний регистр | `"java".toUpperCase()` | `"JAVA"` |
| `trim()` | Удаляет пробелы по краям | `"  hi  ".trim()` | `"hi"` |
| `strip()` | Удаляет пробелы Unicode по краям (Java 11+) | `"  hi  ".strip()` | `"hi"` |
| `stripLeading()` | Удаляет пробелы в начале (Java 11+) | `"  hi  ".stripLeading()` | `"hi  "` |
| `stripTrailing()` | Удаляет пробелы в конце (Java 11+) | `"  hi  ".stripTrailing()` | `"  hi"` |

## ⚖️ Сравнение

| Метод | Описание | Пример | Результат |
|-------|----------|--------|-----------|
| `equals(Object obj)` | Сравнивает содержимое (чувствительно к регистру) | `"A".equals("a")` | `false` |
| `equalsIgnoreCase(String str)` | Сравнивает содержимое (игнорирует регистр) | `"A".equalsIgnoreCase("a")` | `true` |
| `compareTo(String str)` | Лексикографическое сравнение | `"abc".compareTo("abd")` | `-1` |
| `compareToIgnoreCase(String str)` | Лексикографическое сравнение без учета регистра | `"abc".compareToIgnoreCase("ABC")` | `0` |
| `contentEquals(StringBuffer sb)` | Сравнивает с StringBuffer | `"abc".contentEquals(new StringBuffer("abc"))` | `true` |

## ✂️ Разделение и объединение

| Метод | Описание | Пример | Результат |
|-------|----------|--------|-----------|
| `split(String regex)` | Разбивает на массив по разделителю | `"a,b,c".split(",")` | `["a", "b", "c"]` |
| `split(String regex, int limit)` | Разбивает с ограничением количества | `"a,b,c".split(",", 2)` | `["a", "b,c"]` |
| `String.join(CharSequence delimiter, CharSequence... elements)` | Склеивает элементы (статический) | `String.join("-", "a", "b", "c")` | `"a-b-c"` |
| `String.join(CharSequence delimiter, Iterable<? extends CharSequence> elements)` | Склеивает коллекцию (статический) | `String.join(", ", List.of("a", "b"))` | `"a, b"` |

## 🔢 Преобразование

| Метод | Описание | Пример | Результат |
|-------|----------|--------|-----------|
| `valueOf(Object obj)` | Преобразует в строку (статический) | `String.valueOf(123)` | `"123"` |
| `format(String format, Object... args)` | Форматирует строку (статический) | `String.format("Price: %.2f", 19.99)` | `"Price: 19.99"` |
| `intern()` | Возвращает строку из пула | `new String("Java").intern()` | Ссылка на объект в пуле |

##  Новые методы (Java 11+)

| Метод | Описание | Пример | Результат |
|-------|----------|--------|-----------|
| `repeat(int count)` | Повторяет строку n раз | `"ha".repeat(3)` | `"hahaha"` |
| `lines()` | Возвращает Stream строк | `"a\nb\nc".lines()` | Stream<"a", "b", "c"> |
| `isBlank()` | Проверяет на пустоту или пробелы | `"   ".isBlank()` | `true` |
| `strip()` | Удаляет пробелы Unicode | `"  hi  ".strip()` | `"hi"` |
| `stripLeading()` | Удаляет пробелы в начале | `"  hi  ".stripLeading()` | `"hi  "` |
| `stripTrailing()` | Удаляет пробелы в конце | `"  hi  ".stripTrailing()` | `"  hi"` |

##  Работа с кодом символов

| Метод | Описание | Пример | Результат |
|-------|----------|--------|-----------|
| `codePointAt(int index)` | Возвращает Unicode код символа | `"A".codePointAt(0)` | `65` |
| `codePointBefore(int index)` | Код символа перед индексом | `"AB".codePointBefore(1)` | `65` |
| `codePointCount(int begin, int end)` | Количество кодовых точек | `"Hello".codePointCount(0, 5)` | `5` |
| `offsetByCodePoints(int index, int codePointOffset)` | Смещение по кодовым точкам | `"Hello".offsetByCodePoints(0, 2)` | `2` |






##Массивы Array
Массив — это фиксированная по размеру коллекция элементов одного типа.
'''java
// 1. Объявление и выделение памяти (все элементы будут 0)
int[] numbers = new int[5];

// 2. Инициализация значениями
int[] primes = {2, 3, 5, 7, 11};

// Обращение по индексу (начинается с 0)
primes[0] = 10; // Меняем первый элемент

// Длина массива
int len = primes.length; // 5

// Перебор массива
for (int i = 0; i < primes.length; i++) {
    System.out.println(primes[i]);
}'''



