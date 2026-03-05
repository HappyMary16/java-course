### Структура створених обʼктів (без використання патерну Одинак - Singleton)

    Головний клас                     Main
                                    /      \
    Сервіси              ParkingService  StatisticService
                                |               |
    Керування сховищем      ParkingDb       ParkingDb
                                |               |
    Сховище           Map<String, Car>       Map<String, Car>