# Pet-проект по автоматизированному тестированию на Java
Простой тренировочный проект для изучения новых библиотек, некоторых экспериментов и собственного удовольствия.

## Содержание
- [Технологии](#технологии)
- [Использование](#Использование)

## Технологии
- [Java](https://www.java.com/ru/)
- [Apache Maven](https://maven.apache.org/)
- [Selenide](https://ru.selenide.org/)
- [TestNG](https://testng.org/)
- [Allure](https://allurereport.org/)

## Использование

Запустите тесты с помощью команды:
```sh
$ mvn -f ./pom.xml test -DsuiteXmlFile="testng.xml"
```

Для генерации и просмотра отчёта на локальном веб сервисе используйте команду:
```sh
$ allure serve ./target/allure-results 
```
