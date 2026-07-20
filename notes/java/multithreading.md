
# Multithreading 

## 1. Создание потоков
Есть три основных способа запустить код в отдельном потоке:

```java
// 1. Наследование от Thread
class MyThread extends Thread {
    public void run() { /* код */ }
}
new MyThread().start(); // start(), а не run()!

// 2. Интерфейс Runnable (предпочтительнее, т.к. можно наследовать другой класс)
Runnable task = () -> { /* код */ };
new Thread(task).start();

// 3. Callable + Future (если нужно вернуть результат или бросить checked exception)
Callable<String> callable = () -> "Result";
FutureTask<String> future = new FutureTask<>(callable);
new Thread(future).start();
String result = future.get(); // блокирующий вызов
```

---

## 2. Синхронизация и видимость
### `synchronized`
Обеспечивает **атомарность** и **видимость**. Блокирует монитор объекта (или класс, если метод `static`).
```java
// Синхронизация метода (блокирует 'this')
public synchronized void increment() { count++; }

// Синхронизация блока (блокирует конкретный объект)
public void doSomething() {
    synchronized (this) { /* критическая секция */ }
    synchronized (MyClass.class) { /* статическая блокировка */ }
}
```

### `volatile`
Гарантирует **видимость** изменений для всех потоков (читает/пишет напрямую в основную память, обходя кэши CPU).
⚠️ **НЕ гарантирует атомарность!** `volatile int i; i++;` — это гонка (Race Condition).
```java
private volatile boolean running = true; // Идеально для флагов остановки
```

### Atomic-классы (`java.util.concurrent.atomic`)
Используют аппаратную инструкцию **CAS** (Compare-And-Swap). Атомарны и быстрее `synchronized`.
```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet(); // Атомарный ++count
count.compareAndSet(expected, newValue); // CAS
```

---

## 3. Взаимодействие потоков
### `wait()`, `notify()`, `notifyAll()`
Методы класса `Object`. **Должны вызываться только внутри `synchronized` блока!**
```java
synchronized (monitor) {
    while (!condition) {      // ⚠️ Всегда в цикле while (защита от ложных пробуждений)
        monitor.wait();       // Освобождает монитор и усыпляет поток
    }
    // делаем работу
    monitor.notifyAll();      // Будит все потоки, ждущие на этом мониторе
}
```

### `join()`
Заставляет текущий поток ждать завершения другого потока.
```java
thread.start();
thread.join(); // Текущий поток встанет здесь, пока thread не умрет
```

---

## 4. Пулы потоков (Executors)
Создавать `new Thread()` в продакшене **плохо**. Используйте `ExecutorService`.

```java
// ⚠️ В продакшене лучше создавать ThreadPoolExecutor вручную, 
// чтобы контролировать размер очереди и избегать OutOfMemoryError.

ExecutorService fixed = Executors.newFixedThreadPool(4);
ExecutorService cached = Executors.newCachedThreadPool(); // создает потоки по требованию
ExecutorService single = Executors.newSingleThreadExecutor();

Future<?> future = fixed.submit(() -> System.out.println("Task"));
fixed.shutdown(); // Запрещает прием новых задач, но доделывает старые
fixed.awaitTermination(10, TimeUnit.SECONDS);
```

---

## 5. Синхронизаторы (`java.util.concurrent`)
Инструменты для координации потоков.

| Класс | Назначение |
| :--- | :--- |
| **`CountDownLatch`** | Ждем, пока N потоков выполнят действие (одноразовый). `countDown()`, `await()`. |
| **`CyclicBarrier`** | N потоков ждут друг друга в определенной точке (можно использовать повторно). `await()`. |
| **`Semaphore`** | Счетчик пермитов (разрешений). Ограничивает доступ к ресурсу. `acquire()`, `release()`. |
| **`Phaser`** | Более гибкая замена Latch и Barrier для динамического числа потоков. |
| **`Exchanger`** | Обмен данными между двумя потоками в точке синхронизации. |

---

## 6. Явные блокировки (Locks)
Альтернатива `synchronized`. Дают больше контроля. **Всегда освобождать в `finally`!**

```java
Lock lock = new ReentrantLock();
lock.lock();
try {
    // критическая секция
} finally {
    lock.unlock();
}

// Полезные фишки ReentrantLock:
lock.tryLock(); // Не блокирует, возвращает false если занято
lock.tryLock(1, TimeUnit.SECONDS); // Ждет 1 секунду
new ReentrantLock(true); // Fair lock (честная блокировка, очередь)
```

**`ReadWriteLock`** (например, `ReentrantReadWriteLock`):
Множество потоков могут **читать** одновременно, но **писать** может только один (и он блокирует и читателей, и писателей).

---

## 7. Потокобезопасные коллекции
| Коллекция | Особенность |
| :--- | :--- |
| **`ConcurrentHashMap`** | Блокирует не всю карту, а только отдельные бакеты (узлы). Высокая производительность. Не поддерживает `null` ключи/значения. |
| **`CopyOnWriteArrayList`** | При любом изменении (add/set) создает новую копию массива. Идеально для частых чтений и редких записей. |
| **`BlockingQueue`** | `ArrayBlockingQueue` (связанный), `LinkedBlockingQueue`. Блокирует поток, если очередь пуста (`take()`) или полна (`put()`). Основа паттерна Producer-Consumer. |
| **`ConcurrentLinkedQueue`** | Неблокирующая очередь на основе CAS. |

---

## 8. Асинхронность (CompletableFuture)
Современный способ писать неблокирующий код (аналог Promise в JS).

```java
CompletableFuture.supplyAsync(() -> fetchData()) // Асинхронный запуск
    .thenApply(data -> process(data))            // Трансформация (thenApply - синхронно, thenApplyAsync - в новом потоке)
    .thenAccept(result -> save(result))          // Обработка результата
    .exceptionally(ex -> { handle(ex); return null; }) // Обработка ошибок
    .thenCombine(otherFuture, (res1, res2) -> merge(res1, res2)); // Комбинирование двух фьючерсов

// Ждем выполнения всех
CompletableFuture.allOf(future1, future2, future3).join();
```

---

## 9. Virtual Threads (Виртуальные потоки, Java 21+)
Революция в Java. Легковесные потоки, которые маппятся на OS-потоки (Platform Threads) виртуальной машиной. Позволяют создавать миллионы потоков.

```java
// Создание
Thread.startVirtualThread(() -> {
    System.out.println("Hello from virtual thread!");
});

// Пул виртуальных потоков (создает по одному виртуальному потоку на задачу)
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    IntStream.range(0, 100_000).forEach(i -> {
        executor.submit(() -> {
            Thread.sleep(Duration.ofSeconds(1)); // Не блокирует OS-поток!
            return i;
        });
    });
}
```
Виртуальные потоки идеальны для I/O-задач (БД, сеть). Плохо подходят для CPU-bound задач и `ThreadLocal`.

