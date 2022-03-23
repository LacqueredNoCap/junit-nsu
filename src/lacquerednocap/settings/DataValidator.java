package lacquerednocap.settings;

public class DataValidator {

    public static boolean isDigit(String data) {
        try {
            Integer.parseInt(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isCorrectThreadCount(int threadCount) {
        return threadCount >= 1 && threadCount <= Runtime.getRuntime().availableProcessors();
    }

    public static boolean isAtLeastOneClass(String[] testNames) {
        return testNames.length > 0;
    }
}
