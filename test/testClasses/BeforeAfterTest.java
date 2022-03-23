package testClasses;

import lacquerednocap.assertions.Assertions;
import lacquerednocap.common.annotations.After;
import lacquerednocap.common.annotations.Before;
import lacquerednocap.common.annotations.Test;

public class BeforeAfterTest {

    private String message = "Init";

    @Before
    public void beforeMethod() {
        message += " + Before";

        System.out.println("\n" + "Before method: " + message + "\n");
    }

    @Before
    @Test
    public void beforeAndTestAnnotatedMethod() {
        System.out.println("\n" + "Before-Test method" + "\n");

        String expected = "Init + Before";
        Assertions.assertEquals(expected, message);
    }

    @Before
    @After
    public void beforeAndAfterAnnotatedMethod() {
        System.out.println("\n" + "Before-After method" + "\n");
    }

    @Test
    @After
    public void testAndAfterAnnotatedMethod() {
        String expected = "Init + Before";

        System.out.println("\n" + "Test-After method" + "\n");

        Assertions.assertEquals(expected, message);
    }

    @Before
    @Test
    @After
    public void beforeAndTestAndAfterAnnotatedMethod() {
        String expected = "Init + Before";

        System.out.println("\n" + "Before-Test-After method" + "\n");

        Assertions.assertEquals(expected, message);
    }

    @Test
    public void testMethod() {
        message += " + Test";

        String expected = "Init + Before + Test";

        Assertions.assertEquals(expected, message);
    }

    @After
    public void afterMethod() {
        message += " + After";

        System.out.println("\n" + "After method: " + message + "\n");
    }
}
