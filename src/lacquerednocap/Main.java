package lacquerednocap;

import lacquerednocap.cli.CommandLineParser;
import lacquerednocap.common.TestRunner;
import lacquerednocap.settings.Settings;

public class Main {
    public static void main(String[] args) {

        Settings settings = CommandLineParser.parseCommandLine(args);
        TestRunner runner = new TestRunner(settings);

        long startTime = System.nanoTime();

        runner.start();

        long finishTime = System.nanoTime();

        runner.printOverallResult(startTime, finishTime);
    }
}
