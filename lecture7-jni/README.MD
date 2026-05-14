# Приклад з лекції №7 (JNI)

Приклад використання JNI для виклику функцій з C++ бібліотек.

## С++ бібліотека
C++ бібліотека знаходиться в [src/main/cpp](src/main/cpp).
[Library.h](src/main/cpp/Library.h) згенерований за допомогою команди `javac -h`.

Приклад використання команди `javac -h`:
```shell
javac -h <target-directory> <path-to-java-file>
```

```shell
javac -h ./src/main/cpp src/main/java/com/mborodin/javacourse/Main.java
```

Щоб ваш java код міг викликати методи з C++ бібліотеки, її потрібно зібрати 
й покласти в директорію з якої ви запускаєте java проєкт. Якщо ви запускаєте проєкт 
в IntellijIDEA, то зібрану бібліотеку треба покласти в кореневу директорію проєкту, 
в цьому репозиторії це директорія `java-course`.
