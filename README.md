# :fire: ***Зачетное задание по спецкурсу "Язык программирования Java" НГУ 2022*** :fire:

### Ссылка на .jar-файл программы находится [здесь](https://github.com/LacqueredNoCap/junit-nsu/blob/master/out/artifacts/JUnit_jar/JUnit.jar).
### Ссылка на .jar-файл с тестами находится [здесь](https://github.com/LacqueredNoCap/junit-nsu/blob/master/out/test/JUnit/Test.jar).
### `lacquerednocap.Main` - главный класс.
### Классы с тестами:
- `testClasses.CommonTest`  - класс с тестами на ассерты Equals / NotEquals, True / False и Null / NotNull;
- `testClasses.ExceptionTest` - класс с тестами на ожидаемое и/или выбрасываемое исключения;
- `testClasses.BeforeAfterTest` - класс с тестами и с @Before и @After методами;
- `testClasses.TimeTest` - класс с тестами, которые работают 1, 10, 100 и 1000 миллисекунд.
### Пример запуска через командную строку:
`java -cp C:\JUnit.jar;C:\Test.jar lacquerednocap.Main 2 testClasses.BeforeAfterTest testClasses.ExceptionTest`
### Используемая версия языка Java: `openjdk version "11.0.13"`