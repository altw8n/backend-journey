# Класс Collections
- утилита для работы с коллекциями из java.util
- содержит статические методы для работы с коллекциями 
### Сортировка
- **sort(List<T> list)** - сортирует список в натуральном порядке (элементы должны реализовывать Comparable)
```java
Collections.sort(list);
```
- **sort(List<T> list, Comparator<? super T> c)** - сортирует с помощью кастомного компататора
- O(n log n) - использует TimSort

### Поиск
- binarySearch(List<? extends Comparable<? super T>> list, T key) - бинарный поиск в отсортированном списке O(log n)

### Пермещение
- **shuffle(List<?> list)** - перемешивает список случайным образом
- **reverse(List<?> list)** - разворачивает список
- **min(Collection<? extends T> coll)** - возвращает миниму по натуральному порядку
- **max(Collection<? extends T> coll)** - максимум
- **frequency(Collection<?> c, Object o)** - частота вхождений
- **copy(List<? super T> dest, List<? extends T> src)** - копирует
- **synchronizedList(List<T> list)** - создает потокобезопасные обертки


# Интерфейс Comparable
- определяет естественный порядок объектов.
- реализуется самим классом
```java
public interface Comparable<T> {
    int compareTo(T o);
}
```
- возвращает:
- < 0 (текущий меньше)
- == 0 (равны)
- > 0 (ткущий больше)

- используется когда у класса есть естественный порядок (например алфавитный у String)

# Интерфейс Comparator
- определяет **кастомный** порядок сравнения
- реализуется отдельным классом или лямбдой
- используется когда нужно несколько разных порядков сортировки одного класса




