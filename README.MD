# Поглиблений курс програмування Java

В цей репозиторій додаватимуться завдання для лабораторних робіт та приклади з лекцій.

## Перед виконанням лабораторних потрібно
- Встановити [git](https://git-scm.com/install/).
- Встановити середовище розробки (рекомендовано [Intellij IDEA](https://lp.jetbrains.com/intellij-idea-promo/)).
- Встановити [Java 21](https://adoptium.net/temurin/releases/?version=21) (це можна зробити прямо в [Intellij IDEA](https://lp.jetbrains.com/intellij-idea-promo/) при відкритті/створенні першого проєкту).

## Лекції
- Лекція 1: [Приклади, щоб подивитись як працює стек викликів](./lecture1-stack/README.MD)
- Лекція 2:
   - [Анотації](./lecture2-annotations/README.MD)
   - [Багатопоточність](./lecture2-multithreading/README.MD)
- Лекція 3: Генерація коду
   - [AbstractProcessor і класичні інструменти Jаva](./lecture3-codegen/README.MD)
   - [AbstractProcessor і JavaPoet](./lecture3-javapoet/README.MD)
   - [AbstractProcessor і приватні API Java](./lecture3-codegen2/README.MD)
- Лекція 4: [Приклади роботи з винятками](./lecture4-exceptions/README.MD)
- Лекція 5: [Юніт тести](./lecture5-unit-tests)

## Лабораторні
- Лабораторна 1: [Використання Java синтаксису](./lab1-syntax-practice/README.MD)
- Лабораторна 6: [Винятки](./lab6-exceptions/README.MD)
- Лабораторна 8: [Інтеграційні тести](./lab8-integration-tests/README.MD)

## Додатки
- [Патерн Одинак (Singleton)](./extras-singleton)

## Збирання проєкту використовуючи maven wrapper

Maven wrapper знаходить директорію в яку встановлена java використовуючи 
змінну середовища JAVA_HOME. Тому для його використання потрібно проставити
змінну середовища JAVA_HOME. В JAVA_HOME має бути вказаний шлях до директорії
куди встановлена java.

Приклад виконання maven команд використовуючи wrapper:
- Windows
  - `mvnw.cmd package`
  - `mvnw.cmd clean package`
  - `mvnw.cmd spring-boot:run`
- macOS, Linux
  - `./mvnw package`
  - `./mvnw clean package`
  - `./mvnw spring-boot:run`

Щоб зібрати тільки один модуль потрібно перейти в директорію модуля і 
зібрати проєкт вказавши відносний або повний шлях до maven wrapper 
(тобто до файлу `mvnw.cmd` або `mvnw` залежно від операційної системи).

Більше про maven wrapper можна подивитись в https://www.baeldung.com/maven-wrapper.
