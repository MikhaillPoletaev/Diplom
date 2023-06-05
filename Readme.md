## Порядок запуска автотестов

___
Тестовые классы для оплаты по дебетовой карте и оплаты в кредит находятся в папке src/test/java

CardTest.java для проверки оплаты в кредит
CardTest2.java для проверки оплаты по дебетовой карте

Для запуска тестов нужно:
1. Запустить docker-compose командой **"docker compose up"**
2. Запустить jar-файл командой **"java -jar aqa-shop.jar"**
3. Запустить тесты

___
Тестовый класс для проверки совместимости с СУБД находятся в папке src/test/java.
Перед тестами необходимо установить плагин Database Navigator и установить соединения с тестируемыми СУБД.
Настройки соединения с MySQL:
- Connection type - database
- Host - localhost
- Port - 3306
- URL - jdbc:mysql://localhost:3306/app
- User - app
- Password - pass

Настройки соединения с PostgreSQL:
- Connection type - database
- Host - localhost
- Port - 5432
- URL - jdbc:postgresql://localhost:5432/app
- User - app
- Password - 9mREsvXDs9Gk89Ef

Для запуска тестов нужно:</br>
- Mysql
1. Запустить docker-compose командой **"docker compose up"**
2. Запустить jar-файл совместо с коннектором командой **"java -jar aqa-shop.jar -P:jdbc.url:jdbc:mysql://localhost:3306/app** 
3. Запустить тесты

- PostgreSQL
1. Прервать текущие запущенные процессы
2. Перейти в ветку PostgreSQL:
   * Открыть терминал GIT
   * В терминале прописать команду **"git switch PostgreSQL"**
3. Запустить docker-compose командой **"docker compose up"**
4. Запустить jar-файл совместо с коннектором командой **"java -jar aqa-shop.jar -P:jdbc.url:jdbc:postgresql://localhost:5432/app** 
5. Запустить тесты
