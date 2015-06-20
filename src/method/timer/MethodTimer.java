package method.timer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author huntter
 */
public class MethodTimer {
    private final int size;
    private final Method method;
    private Object instance;

    public MethodTimer(int size, Method method) throws Exception {
        this.size = size;
        this.method = method;
        this.instance = createInstance();
    }

    public MethodTimer(int iterations) throws Exception {
        this(0, MethodTimer.Overhead.class.getMethod("nothing", new Class[0]));
    }

    private Object createInstance() throws Exception {
        Constructor<?> constructor = method.getDeclaringClass().getConstructor(new Class[] { int.class });

        return constructor.newInstance(new Object[] { size });
    }

    private long totalTime;
    private int iterations;
    private long overhead;
    private long currentTime = System.nanoTime();
    public double getMethodTime() {
        return (double) ( totalTime - overhead ) / (double) iterations;
    }

    public void run() throws Exception {
        iterations = 1;
        while(true) {
            totalTime = computeTotalTime();

            if(totalTime > MethodsTimer.ONE_SECOND) {
                break;
            }
            iterations *= 2;
        }

        overhead = overheadTimer(iterations).computeTotalTime();
    }

    private long computeTotalTime() throws Exception {
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++)
            method.invoke(instance, new Object[0]);
        return System.nanoTime() - start;
    }

    private static MethodTimer overheadTimer(int iterations) throws Exception {
        return new MethodTimer(iterations);
    }

    public static class Overhead {
        public Overhead(int size) {

        }

        public void nothing() {

        }
    }
}
