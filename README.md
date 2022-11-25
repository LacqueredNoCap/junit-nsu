# ***Изготовить аналог библиотеки JUnit (зачетное задание по спецкурсу "Язык программирования Java" НГУ 2022)***

Необходимо реализовать следующее API - аннотация `@Test` с опциональным атрибутом ожидаемого исключения, аннотации `@Before`, `@After`, свои методы проверки условий - `Assertions`. 
 
Система должна работать на множестве потоков. Каждый поток должен после запуска в бесконечном цикле: 
1) Ожидать поступления в некоторой общей очереди нового имени класса с тестами;
2) Выполнять эти тесты;
3) Возвращать отчёт о прошедших/упавших тестах; 
 
Запуск тестов должен осуществляться следующим командным интерфейсом:\
&emsp; `java -cp <your-junit-jar>;<tested-classes> <your-main-class> N <class-names>`
 - N - количество потоков, в которых должны запускаться тесты;
 - Остальные аргументы (произвольное количество) - полные имена классов, в которых находятся тесты.
#
Ссылка на jar-файл программы находится [здесь](https://github.com/LacqueredNoCap/junit-nsu/blob/master/out/artifacts/JUnit_jar/JUnit.jar).\
Ссылка на jar-файл с тестами находится [здесь](https://github.com/LacqueredNoCap/junit-nsu/blob/master/out/test/JUnit/Test.jar).

`lacquerednocap.Main` - мейн-класс.

Классы с тестами:
  - `testClasses.CommonTest`  - класс с тестами на ассерты Equals / NotEquals, True / False и Null / NotNull;
  - `testClasses.ExceptionTest` - класс с тестами на ожидаемое и/или выбрасываемое исключения;
  - `testClasses.BeforeAfterTest` - класс с тестами и с @Before и @After методами;
  - `testClasses.TimeTest` - класс с тестами, которые работают 1, 10, 100 и 1000 миллисекунд.

Пример запуска через командную строку:\
&emsp; `java -cp C:\Temp\JUnit.jar;C:\Temp\Test.jar lacquerednocap.Main 2 testClasses.BeforeAfterTest testClasses.ExceptionTest`

Используемая версия языка Java: `openjdk version 11.0.13`
