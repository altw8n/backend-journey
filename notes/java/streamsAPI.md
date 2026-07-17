# Streams
- появились в java 8
- обработка конечных и потенциально бесконечных наборов данных
- декларативный подход: описываем что хотим получить а не как

## категории методов streams api
## 1. Создание стрима
```java
List<String> names = menu.stream();
```
#### методы создания стрима
```java
//1. пустой стрим
 Stream<Foo> st = Stream.empty();
//2. из коллекций
Stream<String> st2 = strings.stream();
//3. перечисление элементов
Stream<String> st3 = Stream.of("a", "b", "c");
// пораждение с помощью генератора
Stream<Double> randoms = Stream.generate(Math::random);
// ЛУЧШЕ (работает с примитивами а не с объектами)
DoubleStream d = ThreadLocalRandom.current().doubles();
// пораждение итеративно
Stream<Integer> intgs = Stream.iterate(0, x -> x + 1); //индуктивно
// ЛУЧШЕ
IntStream r = IntStream.range(0, 1000);
```
- для оптимизации есть отдельные примитивные стримы
- IntStream
- LongStream
- DoubleStream

## 2. Промежуточные операции
```java
.filter(d -> d.getCalories > 300).map(Dish::getName).limit(3)
```

### Stream API (методы интерфейса стрим)
### Преобразование стримов в стримы
```java
// с головы
Straem<T> limit(long maxSize)
Stream<T> takeWhile(Predicate p)
//с хвоста
Steam<T> skip(long n) // проскипай n элементов
Stream<T> dropWhile(Predikate) //скипай пока истинно
```

### Фильтр
```java
// оставляет только те элементы которые удовлетворяют условию предиката
Stream<T> filter(Predicate p)
```
### Map
```java
// преобразует каждый элемент стрима в другой объект (применяет функцию к каждому элементу)
<R> Stream<R> map(Function<? super T, ? extends R> mapper)
```
### Distinct
- distinct() - анутренний set пропускает только уникальные элементы

### Sorted
- sorted()
- когда стрим заканчивается то происходит сортировка
- не иммеет смысла для бесконечных потоков

### Параллельные стримы
- метод .parallel() включает параллельную обработку
- можно вызвать в любом месте цепочки


## 3. Терминальные операции
```java
Optional<T> findFirst(); // предъяви первый элемент
Optional<T> findAny(); // предъяви любой
// проверить удовлетворяет ли условию
boolean anyMatch(Pridicate) // какой то
boolean allMatch // все
boolean noneMatch // никакой
```
- ForEach
```java
void forEach(Consumer action);
// в случае параллельного выполнения нет гарантий последовательности
```
















