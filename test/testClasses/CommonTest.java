package testClasses;

import lacquerednocap.assertions.Assertions;
import lacquerednocap.common.annotations.Test;

public class CommonTest {

    @Test
    public void assertEqualsInt1() {
        Assertions.assertEquals(12, 2 + 10);
    }

    @Test
    public void assertEqualsInt2() {
        Assertions.assertEquals(32, 17 * (22 - 20));
    }

    @Test
    public void assertEqualsString1() {

        Assertions.assertEquals("ABCjE6", "ABC" + "jE6");
    }

    @Test
    public void assertEqualsString2() {
        Assertions.assertEquals("12345", "67890");
    }

    @Test
    public void assertEqualsObject1() {
        Object expected = "Object";

        Assertions.assertEquals(expected, "Object");
    }

    @Test
    public void assertEqualsObject2() {
        Assertions.assertEquals(new Object(), new Object());
    }



    @Test
    public void assertNotEqualsInt1() {
        Assertions.assertNotEquals(12, 2 + 11);
    }

    @Test
    public void assertNotEqualsInt2() {
        Assertions.assertNotEquals(32, 16 * (22 - 20));
    }

    @Test
    public void assertNotEqualsString1() {

        Assertions.assertNotEquals("ABCDE6", "ABC" + "jE6");
    }

    @Test
    public void assertNotEqualsString2() {
        Assertions.assertNotEquals("@Test", "@Test");
    }

    @Test
    public void assertNotEqualsObject1() {
        Assertions.assertNotEquals(new Object(), new Object());
    }

    @Test
    public void assertNotEqualsObject2() {
        Object expected = "Object";

        Assertions.assertNotEquals(expected, "Object");
    }



    @Test
    public void assertTrue1() {
        String str = "abc";

        Assertions.assertTrue(str.equals("abc"));
    }

    @Test
    public void assertTrue2() {
        float expected = 10.0f;
        float actual = 0.0f;

        for (int i = 0; i < 100; i++) {
            actual += 0.1f;
        }

        Assertions.assertTrue(expected == actual);
    }

    @Test
    public void assertFalse1() {
        Assertions.assertFalse(70 + 29 == 101);
    }

    @Test
    public void assertFalse2() {
        Assertions.assertFalse(100 * 65 == 6500);
    }

    @Test
    public void assertNull1() {
        Object object = null;

        Assertions.assertNull(object);
    }

    @Test
    public void assertNull2() {
        String str = "";

        Assertions.assertNull(str);
    }

    @Test
    public void assertNotNull1() {
        Integer i = 10;

        Assertions.assertNotNull(i);
    }

    @Test
    public void assertNotNull2() {
        Object object = null;

        Assertions.assertNotNull(object);
    }
}
