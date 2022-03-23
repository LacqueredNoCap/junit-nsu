package lacquerednocap.common;

public class TestResult {

    private final String sourceClass;
    private final String methodName;
    private String optionalMessage;
    private String executionThread;

    private long executionTime;

    public boolean isFailed = false;

    public TestResult(String sourceClass, String testName) {
        this.sourceClass = sourceClass;
        this.methodName = testName;
    }

    public void setOptionalMessage(String optionalMessage) {
        this.optionalMessage = optionalMessage;
    }

    public String getOptionalMessage() {
        return optionalMessage;
    }

    public void setExecutionThread(String executionThread) {
        this.executionThread = executionThread;
    }

    public void setExecutionTime(long time) {
        this.executionTime = time;
    }

    public String formatToString() {
        StringBuilder result = new StringBuilder("-----------------------------------------\n");
        result.append("Test class: ")
                .append(sourceClass)
                .append("\n")
                .append("Test method: ")
                .append(methodName)
                .append("\n")
                .append(optionalMessage != null ? (optionalMessage + "\n") : "")
                .append("STATUS: ");
        if (isFailed) {
            result.append("FAIL");
        } else {
            result.append("SUCCESS");
        }
        result.append("\n")
                .append("Execution thread: ")
                .append(executionThread)
                .append("\n")
                .append("Execution time: ")
                .append(executionTime / 1_000_000)
                .append(" ms (")
                .append(executionTime)
                .append(" ns)\n")
                .append("-----------------------------------------");
        return result.toString();
    }
}
