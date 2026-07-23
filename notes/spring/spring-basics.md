## Создание Application context
```java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");
// из пакета org.example

// из файла с конфигурацией
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskConfiguration.class);
```

- имя бина = имя класса с маленькой буквы
- имя бина уникально (его можно поменять в аргументе аннотации)
## 2 способа получить бин
1. по классу
```java
Task task = context.getBean(Task.class);
```
2. по имени бина
```java
Task task2 = (Task) context.getBean("task");
```

## способы создания application context
1. сканирование пакета + поиск аннотаций @Component

2. на основе файла конфигураций


# Аннотации для создания бинов
- @Component - основная 
- @Service
- @Repository
- @Controller
- @Configuration


# DI

## 3 Способа внедрения зависимостей
1. Инъекция через конструктор (Constructor Injection)
- лучший способ
```java
@Service
public class OrderService {
    
    // 1. Поле объявляем как final (неизменяемое)
    private final PaymentService paymentService;

    // 2. Зависимость приходит через конструктор
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processOrder() {
        paymentService.pay();
    }
}
```

2. Инъекция через сеттер (Setter Injection)
- нормальный
```java
@Service
public class NotificationService {

    private EmailSender emailSender;

    // Аннотация вешается на метод
    @Autowired
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void send() {
        emailSender.send();
    }
}
```

3. Инъекция через поле (Field Injection) 
- плохой
```java
@Service
public class UserService {
    
    // Аннотация прямо над полем
    @Autowired
    private UserRepository userRepository;

    public void saveUser() {
        userRepository.save();
    }
}
```



