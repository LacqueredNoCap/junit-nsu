package testClasses;

import lacquerednocap.assertions.Assertions;
import lacquerednocap.common.annotations.Test;

import java.util.NoSuchElementException;

public class ExceptionTest {

    @Test(expectedException = IndexOutOfBoundsException.class)
    public void mismatchingExceptions() throws NoSuchElementException {
        throw new NoSuchElementException();
    }

    @Test(expectedException = IllegalArgumentException.class)
    public void matchingExceptions() throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }

    @Test(expectedException = IllegalArgumentException.class)
    public void expectedExceptionButNothingWasThrown() {
        Assertions.assertEquals("ABC123","ABC" + "123");
    }

    @Test
    public void noExpectedExceptionButSomethingWasThrown() throws RuntimeException {
        throw new RuntimeException();
    }
}
