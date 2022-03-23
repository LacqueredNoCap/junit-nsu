package lacquerednocap.assertions;

import java.util.Objects;

public class Assertions {

    // + +
    public static void assertEquals(int expected, int actual) {
        if (expected == actual) return;
        failEquals(expected, actual);
    }

    // + +
    public static void assertEquals(String expected, String actual) {
        if (Objects.equals(expected, actual)) return;
        failEquals(expected, actual);
    }

    // + +
    public static void assertEquals(Object expected, Object actual) {
        if (Objects.equals(expected, actual)) return;
        failEquals(expected, actual);
    }




    public static void assertNotEquals(int expected, int actual) {
        if (expected != actual) return;
        failNotEquals(expected, actual);
    }

    public static void assertNotEquals(String expected, String actual) {
        if (!Objects.equals(expected, actual)) return;
        failNotEquals(expected, actual);
    }

    public static void assertNotEquals(Object expected, Object actual) {
        if (!Objects.equals(expected, actual)) return;
        failNotEquals(expected, actual);
    }




    public static void assertTrue(boolean condition) {
        if (!condition) {
            failMessage("Condition is not true");
        }
    }

    public static void assertFalse(boolean condition) {
        if (condition) {
            failMessage("Condition is not false");
        }
    }




    public static void assertNotNull(Object e) {
        if (e == null) {
            failMessage("Instance is null");
        }
    }

    public static void assertNull(Object e) {
        if (e != null) {
            failMessage("Instance is not null");
        }
    }




    private static void failMessage(String message) {
        throw new AssertionException(message);
    }

    private static void failEquals(Object expected, Object actual) {
        failMessage("Expected equality. But expected value:<" + expected.toString() + "> is not equal to actual value:<" + actual.toString() + ">");
    }

    private static void failNotEquals(Object expected, Object actual) {
        failMessage("Expected inequality. But expected value:<" + expected.toString() + "> equals to actual value:<" + actual.toString() + ">");
    }

}
