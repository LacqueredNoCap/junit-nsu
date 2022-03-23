package lacquerednocap.cli;

import lacquerednocap.settings.DataValidator;
import lacquerednocap.settings.Settings;
import lacquerednocap.settings.SettingsImp;

public class CommandLineParser {

    public static Settings parseCommandLine(String[] args) {

        if (args.length > 0 && args[0].equals("-help")) {
            Helper.help();
        }

        int threadCount = 0;
        String[] testNames = new String[0];

        try {
            if (args.length < 2) {
                throw new IllegalArgumentException("Введены не все параметры.");
            }
            if (!DataValidator.isDigit(args[0])) {
                throw new IllegalArgumentException("Не задано количество потоков.");
            }
            threadCount = Integer.parseInt(args[0]);

            testNames = new String[args.length - 1];
            System.arraycopy(args, 1, testNames, 0, testNames.length);

        } catch (IllegalArgumentException e) {
            Helper.getHelpCommand(e.getMessage());
        }

        return new SettingsImp(threadCount, testNames);
    }
}
