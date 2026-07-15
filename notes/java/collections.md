#  КОЛЛЕКЦИИ В JAVA

---

## ArrayList

### 🔧 Реализация
Динамический массив (`Object[]`). При переполнении увеличивается на 50% с копированием элементов.

###  Характеристики
- **Порядок:** Сохраняет порядок вставки
- **Дубликаты:** Разрешены
- **null:** Разрешен
- **Потокобезопасность:** Нет

### ️ Асимптотика
| Операция | Сложность |
|----------|-----------|
| `get(i)` | **O(1)** |
| `set(i, e)` | **O(1)** |
| `add(e)` в конец | **O(1)** амортизированно |
| `add(i, e)` в середину | **O(N)** |
| `remove(i)` | **O(N)** |
| `contains(e)` | **O(N)** |
| `indexOf(e)` | **O(N)** |

### 📝 Основные методы
```java
List<Integer> list = new ArrayList<>();

// Добавление
list.add(10);                    // O(1)
list.add(0, 5);                  // O(N) - вставка по индексу
list.addAll(otherList);          // O(M)

// Доступ
list.get(0);                     // O(1)
list.set(0, 100);                // O(1)

// Удаление
list.remove(0);                  // O(N) - по индексу
list.remove(Integer.valueOf(10)); // O(N) - по объекту
list.clear();                    // O(N)

// Поиск
list.contains(10);               // O(N)
list.indexOf(10);                // O(N)
list.lastIndexOf(10);            // O(N)

// Другое
list.size();                     // O(1)
list.isEmpty();                  // O(1)
list.subList(0, 5);              // O(1) - view на часть списка
list.sort(Comparator.naturalOrder()); // O(N log N)
```

### ✅ Когда использовать
- Доступ по индексу важен
- Много чтений, мало вставок/удалений в середине

---

## LinkedList

### 🔧 Реализация
Двусвязный список. Каждый узел хранит данные + ссылки на предыдущий и следующий узлы.

###  Характеристики
- **Порядок:** Сохраняет порядок вставки
- **Дубликаты:** Разрешены
- **null:** Разрешен
- **Потокобезопасность:** Нет

### ⏱️ Асимптотика
| Операция | Сложность |
|----------|-----------|
| `get(i)` | **O(N)** |
| `add(e)` в конец | **O(1)** |
| `add(i, e)` | **O(N)** |
| `remove(i)` | **O(N)** |
| `addFirst(e)`, `addLast(e)` | **O(1)** |
| `removeFirst()`, `removeLast()` | **O(1)** |

###  Основные методы
```java
List<Integer> list = new LinkedList<>();

list.add(10);
list.addFirst(5);                // O(1)
list.addLast(20);                // O(1)
list.get(0);                     // O(N) ️
list.removeFirst();              // O(1)
list.removeLast();               // O(1)
```

### ️ Для очередей/стеков лучше `ArrayDeque`.

---

## HashSet

### 🔧 Реализация
Основан на `HashMap`. Элементы хранятся как ключи, значения — заглушки `new Object()`.

###  Характеристики
- **Порядок:** Не гарантирует
- **Дубликаты:** Запрещены
- **null:** Разрешен (один элемент)
- **Потокобезопасность:** Нет

### ️ Асимптотика
| Операция | Сложность |
|----------|-----------|
| `add(e)` | **O(1)** |
| `remove(e)` | **O(1)** |
| `contains(e)` | **O(1)** |
| `size()` | **O(1)** |

### 📝 Основные методы
```java
Set<Integer> set = new HashSet<>();

// Добавление
set.add(10);                     // O(1), true если добавлен
set.add(10);                     // false, уже есть

// Проверка
set.contains(10);                // O(1)

// Удаление
set.remove(10);                  // O(1)

// Другое
set.size();                      // O(1)
set.isEmpty();                   // O(1)
set.clear();                     // O(N)

// Операции над множествами
set.addAll(otherSet);            // O(M)
set.removeAll(otherSet);         // O(N)
set.retainAll(otherSet);         // O(N) - пересечение
```

### ✅ Когда использовать
- Проверка на дубликаты
- Проверка наличия элемента за O(1)

---

## TreeSet

### 🔧 Реализация
Красно-черное дерево (Red-Black Tree). Элементы всегда отсортированы.

### 📊 Характеристики
- **Порядок:** Отсортированный (natural order или Comparator)
- **Дубликаты:** Запрещены
- **null:** Запрещен (с Java 7+)
- **Потокобезопасность:** Нет

### ️ Асимптотика
| Операция | Сложность |
|----------|-----------|
| `add(e)` | **O(log N)** |
| `remove(e)` | **O(log N)** |
| `contains(e)` | **O(log N)** |
| `first()`, `last()` | **O(log N)** |

### 📝 Основные методы
```java
Set<Integer> set = new TreeSet<>();

set.add(3); set.add(1); set.add(2);
// set = [1, 2, 3]

// Навигационные методы
set.first();                     // 1 - минимальный
set.last();                      // 3 - максимальный

set.lower(2);                    // 1 - строго меньше
set.floor(2);                    // 2 - меньше или равно
set.higher(2);                   // 3 - строго больше
set.ceiling(2);                  // 2 - больше или равно

// Подмножества
set.subSet(1, 3);                // [1, 2] - view
set.headSet(2);                  // [1]
set.tailSet(2);                  // [2, 3]
```

### ✅ Когда использовать
- Нужны отсортированные уникальные элементы
- Поиск ближайших элементов (floor, ceiling)
- Задачи на медиану, ранги

---

## LinkedHashSet

### 🔧 Реализация
`HashSet` + двусвязный список для сохранения порядка вставки.

### 📊 Характеристики
- **Порядок:** Порядок вставки
- **Дубликаты:** Запрещены
- **null:** Разрешен
- **Потокобезопасность:** Нет

### ⏱️ Асимптотика
Такая же как `HashSet`: **O(1)** для add/remove/contains

### 📝 Основные методы
Те же что у `HashSet`

### ✅ Когда использовать
- **LRU Cache** (Least Recently Used)
- Нужен порядок вставки + уникальность

---

## HashMap

### 🔧 Реализация
Массив бакетов (корзин). При коллизии — связный список (после 8 элементов → сбалансированное дерево).

### 📊 Характеристики
- **Порядок:** Не гарантирует
- **Дубликаты ключей:** Запрещены
- **null ключ:** Разрешен (один)
- **null значения:** Разрешены
- **Потокобезопасность:** Нет

### ⏱️ Асимптотика
| Операция | Сложность |
|----------|-----------|
| `put(k, v)` | **O(1)** |
| `get(k)` | **O(1)** |
| `remove(k)` | **O(1)** |
| `containsKey(k)` | **O(1)** |
| `containsValue(v)` | **O(N)** ⚠️ |

### 📝 Основные методы
```java
Map<String, Integer> map = new HashMap<>();

// Добавление
map.put("a", 1);                 // O(1), null или старое значение
map.putIfAbsent("a", 10);        // O(1), не перезапишет если есть

// Получение
map.get("a");                    // O(1), null если нет
map.getOrDefault("a", 0);        // O(1), 0 если нет ⭐
map.containsKey("a");            // O(1)

// Удаление
map.remove("a");                 // O(1)
map.remove("a", 1);              // O(1), только если значение=1

// Продвинутые (Java 8+)
map.compute("a", (k, v) -> v == null ? 1 : v + 1);  // O(1)
map.computeIfAbsent("a", k -> 0);                   // O(1)
map.merge("a", 1, Integer::sum); // O(1) ⭐ для подсчета

// Перебор 
for (Map.Entry<String, Integer> e : map.entrySet()) {
    e.getKey(); e.getValue();
}
map.forEach((k, v) -> System.out.println(k + "=" + v));

// Коллекции
map.keySet();                    // Set ключей
map.values();                    // Collection значений
map.entrySet();                  // Set пар
```

### ✅ Когда использовать
- Подсчет частоты элементов
- Two Sum, группировка
- Кеширование

---

## TreeMap

###  Реализация
Красно-черное дерево. Ключи всегда отсортированы.

### 📊 Характеристики
- **Порядок:** Отсортированный по ключам
- **Дубликаты ключей:** Запрещены
- **null ключ:** Запрещен
- **null значения:** Разрешены
- **Потокобезопасность:** Нет

### ⏱️ Асимптотика
| Операция | Сложность |
|----------|-----------|
| `put(k, v)` | **O(log N)** |
| `get(k)` | **O(log N)** |
| `remove(k)` | **O(log N)** |
| `firstKey()`, `lastKey()` | **O(log N)** |

### 📝 Основные методы
```java
Map<Integer, String> map = new TreeMap<>();

map.put(3, "c"); map.put(1, "a"); map.put(2, "b");
// map = {1=a, 2=b, 3=c}

// Навигация по ключам
map.firstKey();                  // 1
map.lastKey();                   // 3
map.floorKey(2);                 // 2
map.ceilingKey(2);               // 2
map.lowerKey(2);                 // 1
map.higherKey(2);                // 3

// Поддиапазоны
map.subMap(1, 3);                // {1=a, 2=b}
map.headMap(2);                  // {1=a}
map.tailMap(2);                  // {2=b, 3=c}
```

### ✅ Когда использовать
- Нужна сортировка по ключам
- Поиск диапазона ключей
- Задачи на ранги, порядковые статистики

---

## LinkedHashMap

### 🔧 Реализация
`HashMap` + двусвязный список для сохранения порядка.

### 📊 Характеристики
- **Порядок:** Порядок вставки (или порядок доступа)
- **Дубликаты ключей:** Запрещены
- **null ключ:** Разрешен
- **null значения:** Разрешены
- **Потокобезопасность:** Нет

### ⏱️ Асимптотика
Такая же как `HashMap`: **O(1)** для основных операций

### 📝 Основные методы
Те же что у `HashMap` + special constructor:
```java
// LRU Cache: accessOrder=true, удаляем старейший
Map<Integer, Integer> cache = new LinkedHashMap<>(16, 0.75f, true) {
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }
};
```

### ✅ Когда использовать
- **LRU Cache** (основное применение)
- Нужен порядок вставки + быстрый доступ

---

## ArrayDeque

###  Реализация
Циклический динамический массив. При переполнении удваивается.

### 📊 Характеристики
- **null:** Запрещен (используется как маркер)
- **Потокобезопасность:** Нет
- **Универсальность:** Может быть стеком И очередью

### ⏱️ Асимптотика
| Операция | Сложность |
|----------|-----------|
| `addFirst(e)`, `addLast(e)` | **O(1)** |
| `removeFirst()`, `removeLast()` | **O(1)** |
| `offerFirst(e)`, `offerLast(e)` | **O(1)** |
| `pollFirst()`, `pollLast()` | **O(1)** |
| `peekFirst()`, `peekLast()` | **O(1)** |

###  Основные методы
```java
Deque<Integer> deque = new ArrayDeque<>();

// ===== КАК СТЕК (LIFO) =====
deque.push(10);                  // addFirst
deque.pop();                     // removeFirst, exception если пусто
deque.peek();                    // peekFirst, null если пусто

// ===== КАК ОЧЕРЕДЬ (FIFO) =====
deque.offer(10);                 // offerLast
deque.poll();                    // pollFirst, null если пусто 
deque.peek();                    // peekFirst

// Двусторонняя очередь
deque.addFirst(1);
deque.addLast(2);
deque.removeFirst();
deque.removeLast();
```

### ✅ Когда использовать
- **Стек:** push/pop/peek
- **Очередь (BFS):** offer/poll/peek
- **Монотонный стек/очередь**
- **ЗАБУДЬ про `Stack` и `LinkedList` для этих целей!**

---

## PriorityQueue

### 🔧 Реализация
Куча (heap). По умолчанию **Min-Heap** (минимальный элемент наверху).

###  Характеристики
- **Порядок:** Приоритетный (не полностью отсортирован)
- **null:** Запрещен
- **Потокобезопасность:** Нет (используй `PriorityBlockingQueue`)

### ⏱️ Асимптотика
| Операция | Сложность |
|----------|-----------|
| `offer(e)` | **O(log N)** |
| `poll()` | **O(log N)** |
| `peek()` | **O(1)** |
| `add(e)` | **O(log N)** |
| `remove(e)` | **O(N)** ⚠️ |

###  Основные методы
```java
// Min-Heap (по умолчанию)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max-Heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
// или
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

// Кастомный компаратор
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

// Основные методы
pq.offer(10);                    // O(log N)
pq.poll();                       // O(log N), извлечь мин/макс
pq.peek();                       // O(1), посмотреть мин/макс
pq.size();                       // O(1)
```

---

 стек                               → ArrayDeque (push/pop)
 очередь                       → ArrayDeque (offer/poll)

