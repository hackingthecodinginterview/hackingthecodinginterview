import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigO {

    /**
     * What is the runtime of this code?
     */
    @Test
    public void example1() {
        // This will take O(N) time.
        // The fact that we iterate through the array twice doesn't matter.
        int[] test0 = new int[]{0, 1, 2, 3};
        foo(test0);
        int[] test1 = new int[]{1, 2, 3, 4};
        foo(test1);
    }

    private void foo(int[] array) {
        int sum = 0;
        int product = 1;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        for (int i = 0; i < array.length; i++) {
            product *= array[i];
        }
        System.out.println(sum + ", " + product);
    }

    /**
     * What is the runtime of the code below?
     */
    @Test
    public void example2() {
        // The inner 'for' loop has O(N) iterations, and it is called N times.
        // Therefore, the runtime is O(N^2)
        int[] test0 = new int[]{0, 1, 2, 3, 4};
        printPairs(test0);
//        int[] test1 = new int[]{9, 8, 7, 6, 5};
//        printPairs(test1);
    }

    private void printPairs(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.println(array[i] + ", " + array[j]);
            }
        }
    }

    @Test
    public void example3() {
        int[] test0 = new int[]{0, 1, 2, 3, 4};
        printUnorderedPairs(test0);
//        int[] test1 = new int[]{9, 8, 7, 6, 5};
//        printUnorderedPairs(test1);
    }

    /**
     * COUNTING THE ITERATIONS:
     * <p>
     * The first time 'j' runs through N-1 steps.
     * The second time, it's N-2 steps.
     * Then N-3 steps. And so on...
     * Therefore the number of steps total is:
     * <p>
     * (N-1) + (N-2) + (N-3) + ... + 2 + 1
     * <p>
     * = 1 + 2 + 3 + ... + N-1
     * <p>
     * = sum of 1 through N-1
     * <p>
     * The sum of 1 through N-1 is (N(N-1))/2,
     * so the runtime will be O(N^2)
     * <p>
     * WHAT IT MEANS
     * <p>
     * Alternatively, we can figure out the runtime
     * by thinking about what the code "means".
     * It iterates through each pair of values for (i,j)
     * where j is bigger than i.
     * <p>
     * There are N^2 total pairs.
     * Roughly half of those will have i < j and
     * the remaining half will have i > j
     * This code goes through roughly (N^2)/2 pairs
     * so it does O(N^2) work
     * <p>
     * VISUALIZING WHAT IT DOES
     * <p>
     * The code iterates through the following (i,j) pairs when N=8:
     * <p>
     * (0,1) (0,2) (0,3) (0,4) (0,5) (0,6) (0,7)
     * (1,2) (1,3) (1,4) (1,5) (1,6) (1,7)
     * (2,3) (2,4) (2,5) (2,6) (2,7)
     * (3,4) (3,5) (3,6) (3,7)
     * (4,5) (4,6) (4,7)
     * (5,6) (5,7)
     * (6,7)
     * <p>
     * This looks like half of an NxN matrix, which has size (roughly) (N^2)/2
     * Therefore, it takes O(N^2) time.
     * <p>
     * AVERAGE WORK
     * <p>
     * We know that the outer loop runs N times.
     * How much work does the inner loop do?
     * It varies across iterations, but we can think
     * about the average iteration.
     * <p>
     * What is the average of 1, 2, 3, 4, 5, 6, 7, 8, 9, 10?
     * The average will be in the middle, so it will be roughly 5.
     * (We could give a more precise answer, of course, but we don't
     * need to for big O)
     * <p>
     * What about for 1, 2, 3, ... N?
     * The average value in this sequence is N/2
     * <p>
     * Therefore, since the inner loop does N/2 work on average
     * and it runs N times, the total work is (N^2)/2 which is O(N^2)
     */
    private void printUnorderedPairs(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                System.out.println(array[i] + ", " + array[j]);
            }
        }
    }

    @Test
    public void example4() {

        // We can break up this analysis.
        // The 'if-statement' within 'j's for-loop is O(1) time since
        // it's just a sequence of constant-time statements.
        //
        // We now have:
        /**
         * private void printUnorderedPairs(int[] arrayA, int[] arrayB) {
         *         for (int i = 0; i < arrayA.length; i++) {
         *             for (int j = 0; j < arrayB.length; j++) {
         *
         *                 --> O(1) work <--
         *
         *             }
         *         }
         *     }
         */
        // For each element of arrayA, the inner for loop goes through 'b'
        // iterations, where b = arrayB.length
        // If a = array.length, then the runtime is O(ab)
        //TODO: If you said O(N^2), then remeber your mistake for the future...
        //TODO: It's not O(N^2) because there are two different inputs.
        //TODO: Both matter.
        //TODO: This is an extremely common mistake.
        int[] test0 = new int[]{0, 1, 2, 3};
        int[] test1 = new int[]{4, 3, 2, 1};
        printUnorderedPairs(test0, test1);
    }

    private void printUnorderedPairs(int[] arrayA, int[] arrayB) {
        for (int i = 0; i < arrayA.length; i++) {
            for (int j = 0; j < arrayB.length; j++) {
                if (arrayA[i] < arrayB[j]) {
                    System.out.println(arrayA[i] + ", " + arrayB[j]);
                }
            }
        }
    }


    /**
     * Nothing has really changed here.
     * 10,000 units of work is still constant, so the runtime is O(ab)
     */
    @Test
    public void example5() {
        int[] test0 = new int[]{0, 1, 2, 3};
        int[] test1 = new int[]{4, 3, 2, 1};
        strangePrintUnorderedPairs(test0, test1);
    }

    private void strangePrintUnorderedPairs(int[] arrayA, int[] arrayB) {
        for(int i = 0; i < arrayA.length; i++) {
            for(int j = 0; j < arrayB.length; j++) {
                for(int k = 0; k < 10000; k++) {
                    System.out.println(arrayA[i] + ", " + arrayB[j]);
                }
            }
        }
    }

    /**
     * This algorithm runs in O(N) time.
     * The fact that it only goes through half of the array
     * (in terms of iterations) does not impact the big O time.
     */
    @Test
    public void example6() {
        int[] test0 = new int[]{0, 1, 2, 3};
        System.out.println(Arrays.toString(reverse(test0)));
    }

    private int[] reverse(int[] array) {
        for(int i = 0; i < array.length / 2; i++) {
            int other = array.length - i - 1;
            int temp = array[i];
            array[i] = array[other];
            array[other] = temp;
        }
        return array;
    }


}