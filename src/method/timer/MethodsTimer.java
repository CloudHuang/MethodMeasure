package method.timer;

import java.lang.reflect.Method;

/**
 * @author huntter
 */
public class MethodsTimer {
    private static final int MAXIMUM_SIZE = 10000000;
    public static final long ONE_SECOND = 1000000000;
    private final Method[] methods;

    public MethodsTimer(Method[] methods) {
        this.methods = methods;
    }

    public static void main(String[] args) throws Exception {
        MethodsTimer methodsTimer = new MethodsTimer(ListSearch.class.getDeclaredMethods());

        MethodsTimer.class.getPackage()

        methodsTimer.report();
    }

    private void report() throws Exception {
        for(Method method : methods) {
            System.out.print(method.getName() + "\t");

            for(int size=1; size <= MAXIMUM_SIZE; size *=10) {
                MethodTimer methodTimer = new MethodTimer(size, method);
                methodTimer.run();
                System.out.print(String.format("%.2f\t", methodTimer.getMethodTime()));
            }

            System.out.println();
        }
    }
}
