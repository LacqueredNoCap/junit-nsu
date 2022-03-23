package lacquerednocap.settings;

import lacquerednocap.cli.Helper;

public class SettingsImp implements Settings{

    private final int threadsCount;
    private final String[] testNames;

    public SettingsImp(int threadsCount, String[] testNames) {
        SettingsImp.validateSettings(threadsCount, testNames);

        this.threadsCount = threadsCount;
        this.testNames = testNames;
    }

    @Override
    public int getThreadsCount() {
        return threadsCount;
    }

    @Override
    public String[] getTestNames() {
        return testNames;
    }

    public static void validateSettings(int threadsCount, String[] testNames) {
        try {
            if (!DataValidator.isCorrectThreadCount(threadsCount)) {
                throw new IllegalArgumentException("Введено недопустимое количество потоков.");
            }
            if (!DataValidator.isAtLeastOneClass(testNames)) {
                throw new IllegalArgumentException("Не было введено ни одного имени класса.");
            }
        } catch (IllegalArgumentException e) {
            Helper.getHelpCommand(e.getMessage());
        }
    }

}
