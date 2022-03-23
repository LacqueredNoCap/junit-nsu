package lacquerednocap.cli;

public class Helper {

    public static void help() {
        System.out.println("Параметры программы задаются при запуске через аргументы командной строки, по порядку:\n" +
                "       1. Целое число N - количество потоков, размером от одного до количества потоков процессора;\n" +
                "       2. Перечисление имен классов с тестами, как минимум один.\n" +
                "Примеры запуска из командной строки:\n" +
                "java -cp JUnit.jar;<path to test directory> lacquerednocap.Main 1 <package>.TestClass1 <package>.TestClass2\n" +
                "java -cp JUnit.jar;<path to test directory> lacquerednocap.Main 2 <package>.MyTests1 <package>.MyTests2 <package>.MyTests3"
        );

        System.exit(0);
    }

    public static void getHelpCommand(String message) {
        System.out.println(message +
                " Пожалуйста, введите команду заново или воспользуйтесь \"-help\".\n" +
                "Пример запуска справки из командной строки:\n" +
                "java -jar JUnit.jar -help"
        );

        System.exit(0);
    }
}
