package lacquerednocap.common;

import lacquerednocap.assertions.AssertionException;
import lacquerednocap.common.annotations.After;
import lacquerednocap.common.annotations.Before;
import lacquerednocap.common.annotations.Test;
import lacquerednocap.common.utils.Utils;
import lacquerednocap.settings.Settings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestRunner {
    private final String[] testNames;
    private final TestsQueue testsQueue;
    private final Thread[] threads;

    public AtomicInteger totalTest = new AtomicInteger(0);
    public AtomicInteger failedTest = new AtomicInteger(0);
    public AtomicInteger totalClassTest = new AtomicInteger(0);

    public TestRunner(Settings settings) throws IllegalArgumentException {
        testNames = settings.getTestNames();

        testsQueue = new TestsQueue(() -> totalClassTest.get() == settings.getTestNames().length);

        threads = new Thread[settings.getThreadsCount()];
        for (int i = 0; i < settings.getThreadsCount(); i++) {
            threads[i] = new Thread(new TestTask());
            threads[i].setName("TestThread-" + (i+1));
        }
    }

    public void start() {
        for (Thread thread : threads) {
            if (!thread.isAlive()) {
                thread.start();
            }
        }

        for (String test : testNames) {
            testsQueue.putTest(test);
            totalClassTest.incrementAndGet();
        }

        try {
            joinAllTestThreads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void joinAllTestThreads() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private void runTest(Class<?> testClass) {
        List<Method> beforeMethods = Utils.getMethodsWithAnnotation(testClass, Before.class);
        List<Method> testMethods = Utils.getMethodsWithAnnotation(testClass, Test.class);
        List<Method> afterMethods = Utils.getMethodsWithAnnotation(testClass, After.class);

        runTestList(testClass, testMethods, beforeMethods, afterMethods);
    }

    private void runTestList(
            Class<?> source,
            List<Method> testMethods,
            List<Method> beforeMethods,
            List<Method> afterMethods
    ) {
        if (testMethods == null || testMethods.isEmpty()) return;

        for (Method m : testMethods) {
            if (Utils.methodHasIncompatibleAnnotations(m)) {
                continue;
            }

            TestResult result = new TestResult(source.getCanonicalName(), m.getName());
            result.setExecutionThread(Thread.currentThread().getName());

            Object instance = null;
            try {
                instance = source.getDeclaredConstructor().newInstance();
            } catch (InstantiationException |
                    NoSuchMethodException |
                    InvocationTargetException |
                    IllegalAccessException e) {
                e.printStackTrace();
            }

            long startTime = System.nanoTime();

            Test ann = m.getAnnotation(Test.class);
            Class<?> expectedException = ann.expectedException();
            try {
                if (runMethodsOnInstance(beforeMethods, instance, "Exception in @Before method:")) {
                    m.invoke(instance);
                } else {
                    return;
                }
            } catch (InvocationTargetException e) {

                if (expectedException == Test.DefaultException.class) {
                    failTest(result, e.getCause().getMessage());
                }

                if (e.getCause().getClass() != AssertionException.class) {

                    if (e.getCause().getClass() != expectedException) {
                        String message;

                        if (expectedException != Test.DefaultException.class) {
                            message = "Caught <" + e.getCause().getClass().getName() +
                                    "> but expected <" + expectedException.getName() + ">";
                        }
                        else {
                            message = "Caught <" + e.getCause().getClass().getName() + ">" +
                                    " but no exception expected";
                        }

                        failTest(result, message);

                    } else {
                        String message = "Expected and caught <" +
                                e.getCause().getClass().getName() + ">";
                        result.setOptionalMessage(message);
                    }
                }
            } catch (IllegalAccessException | IllegalArgumentException e) {
                e.printStackTrace();
            }

            if (!result.isFailed &&
                    expectedException != Test.DefaultException.class &&
                    result.getOptionalMessage() == null) {
                String message =
                        "Expected <" +
                                expectedException.getName() +
                                "> but nothing was thrown";
                failTest(result, message);
            }

            totalTest.incrementAndGet();

            if (result.isFailed) {
                failedTest.incrementAndGet();
            }

            result.setExecutionTime(System.nanoTime() - startTime);

            printResult(result);

            if (!runMethodsOnInstance(afterMethods, instance, "Exception in @After method")) {
                return;
            }
        }
    }

    private boolean runMethodsOnInstance(List<Method> methods, Object instance, String message) {
        if (methods == null || methods.isEmpty()) return true;

        for (Method m : methods) {
            if (Utils.methodHasIncompatibleAnnotations(m)) {
                continue;
            }
            try {
                m.invoke(instance);
            } catch (InvocationTargetException | IllegalAccessException e) {
                printErrorMessage(m, message, e);
                return false;
            }
        }
        return true;
    }

    private void printErrorMessage(Method method, String message, Throwable throwable) {
        synchronized (System.out) {
            System.out.println(message);
            System.out.println("Error while try to execute <" + method + ">");
            throwable.printStackTrace(System.out);
        }
    }

    private void failTest(TestResult result, String message) {
        result.isFailed = true;
        result.setOptionalMessage(message);
    }

    private void printResult(TestResult result) {
        synchronized (System.out) {
                System.out.println(result.formatToString());
        }
    }

    public void printOverallResult(long startTime, long finishTime) {
        System.out.println("TOTAL TESTS: " + totalTest);
        System.out.println("FAILED TESTS: " + failedTest);
        System.out.println("TOTAL TIME: " + (finishTime - startTime) / 1_000_000 + " ms");
    }

    private class TestTask implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Class<?> testClass = testsQueue.getTest();
                    if (testClass != null) {
                        runTest(testClass);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
