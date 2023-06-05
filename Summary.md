## Что было запланировано:
1. Автоматизация тестов с использованием Selenium Web Driver для тестирования элементов веб-страницы;
2. Автоматизация тестов для проверки поддержки СУБД (MySQL, PostgreSQL);
___
## Что было реализовано:
1. Автоматизация тестов с использованием Selenium Web Driver для тестирования элементов веб-страницы:
- Сценарии для тестирования оплаты по дебетовой карте реализованы в классе CardTest2.java
- Сценарии для тестирования оплаты в кредит реализованы в классе CardTest.java
2. Автоматизация тестов для проверки поддержки СУБД (MySQL, PostgreSQL):
- Поддержка MySQL протестирована в классе DBTest в ветке Main; 
- Поддержка PostgreSQL протестирована в классе DBTest в ветке PostgreSQL; 
___
## Сработавшие риски:
1. Ошибки при написание автотестов. Часть тестов падала из-за технической ошибки связаной с Уведомлениями об успешной операции или ошибке. При асерте Идея в данных уведомлениях присылала пустой текст. Проблема была решена исправлением теста, вместо сравнения отражаемого текста, тесты проверяли, что нужное уведомление действительно отображается.
2. Сложности в тестирование связанные с поиском элементов на тестируемой странице. Т.к. у элементов отсутсвовали 'id', это усложняло их поиск, и увеличивало время для написания тестов.
3. Сложности при настройке СУБД. Требуется очень внимательно следить за синтаксисом. Так, например, при запуске jar-файла командой **"java -jar aqa-shop.jar -P:jdbc.url:<u>jdbc<span style="color:red">.</span>mysql</u>://localhost:3306/app** jar-файл будет активирован, но соединение с СУБД установлено не будет. Корректный вариант в данном случае  **"java -jar aqa-shop.jar -P:jdbc.url:<u>jdbc<span style="color:green">:</span>mysql</u>://localhost:3306/app**