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
        for (int i = 0; i < arrayA.length; i++) {
            for (int j = 0; j < arrayB.length; j++) {
                for (int k = 0; k < 10000; k++) {
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
        for (int i = 0; i < array.length / 2; i++) {
            int other = array.length - i - 1;
            int temp = array[i];
            array[i] = array[other];
            array[other] = temp;
        }
        return array;
    }

    /**
     * Just because it's binary search doesn't mean that there's a log in it.
     * We can look at this two ways...
     */
    @Test
    public void example9() {
        Node a = new Node(1, null, null);
        Node b = new Node(1, null, null);
        Node c = new Node(1, a, b);

        int sum = sum(c);
        System.out.println("sum: " + sum);
    }

    /**
     * The most straightforward way is to think about what this means.
     * This code touches each node in the tree once and does a constant time
     * amount of work with each "touch" (excluding the recursive calls).
     * <p>
     * Therefore, the runtime will be linear in terms of the number of
     * nodes. If there are N nodes, then the runtime is O(N).
     * <p>
     * RECURSIVE PATTERN
     * <p>
     * ...we discussed a pattern for the runtime of recursive functions that
     * have multiple branches.
     * <p>
     * The runtime of a recursive function with multiple branches is typically
     * O(branches^depth)
     * <p>
     * There are two branches at each call, so we're looking at O(2^depth)
     * <p>
     * At this point many people might assume that something went wrong since
     * we have an exponential algorithm -- that something in our logic is
     * flawed or that we've inadvertently created an exponential time
     * algorithm (yikes!)
     * <p>
     * The second statement is correct. We do have an exponential time algorithm,
     * but it's not as bad as one might think. Consider what variable it's exponential
     * with respect to.
     * <p>
     * What is 'depth'? The tree is a balanced binary search tree. Therefore, if there
     * are N total nodes, then 'depth' is roughly log N.
     * <p>
     * The runtime of this code is O(N) where N is the number of nodes.
     */

    private int sum(Node node) {
        if (node == null) {
            return 0;
        }
        return sum(node.left) + node.value + sum(node.right);
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * The following method checks if a number is prime by checking
     * for divisibility on numbers less than it. It only needs to go up
     * to the square root of n because if n is divisible by a number
     * greater than it's square root then it's divisible by something smaller
     * than it.
     *
     * For example, while 33 is divisible by 11 (which is greater than the square
     * root of 33), the "counterpart" to 11 is 3 (3 * 11 = 33).
     * 33 will have already been eliminated as a prime number by 3.
     */
    @Test
    public void example10() {
        System.out.println(isPrime(13));
    }

    /**
     * What is the time complexity of this function?
     *
     * The work inside the for loop is constant.
     * Therefore, we just need to know how many iterations the for loop
     * goes through in the worst case.
     *
     * The for loop will start when 'x = 2' and end when 'x * x = n'
     * Or, in other words, it stops when 'x = sqrt(n)', when 'x' equals
     * the square root of 'n'.
     *
     * This runs in O(sqrt(n)) time.
     */
    private boolean isPrime(int n) {
        for (int x = 2; x * x <= n; x++) {
            if (n % x == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * The following code computes n!
     */
    @Test
    public void example11() {
        int f = factorial(4);
        System.out.println("f: " + f);
    }
    /**
     * This is just a straight forward recursion from 'n' to
     * 'n-1' to 'n-2' down to '1'. It will take O(n) time.
     */
    private int factorial(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /**
     * This code prints all permutations of a string with all distinct characters.
     */
    @Test
    public void example12() {
        permutation("se");
    }

    /**
     * This is a (very!) tricky one.
     * We can think about this by looking at how long each call takes
     * (excluding the recursion piece) and how many times 'permutation' gets
     * called. We'll aim for getting as tight of an upper bound as possible.
     */
    private void permutation(String str) {
        permutation(str, "");
    }

    /**
     * How long does each function call take?
     *
     * Executing line 7 takes O(n) time since each character needs to be printed.
     *
     * Line 10 and line 11 will also take O(n) time combined, due to the string
     * concatenation. Observe that the sum of the lengths of 'rem', 'prefix', and
     * 'str.charAt(i)' will always be 'n'.
     *
     * Each node in our call tree therefore corresponds to O(n) work.
     *
     * How many times does the function get called?
     *
     * Picture a call tree representing our function, for a string like 'abcd'
     *
     * - How many leaf nodes are there?
     *   This is simply the number of permutations. We branch 4 times at the root,
     *   then 3, then 2, then 1. This gives us 4 * 3 * 2 * 1 leaf nodes. Or, expressed
     *   more generically 'n!' permutations.
     *
     * - How many total nodes are there?
     *   Each leaf node is attached to a path with 'n' nodes. So, at most, there
     *   are 'n * n!' total nodes in the tree.
     *
     * The total runtime therefore is (at worst) O(n * n * n!)
     * This can be expressed as O((n+2)!) (which is not the same as O(n!))
     * This is probably as far as most interviewers would expect you to get.
     */
    private void permutation(String str, String prefix) {
        if(str.length() == 0) {
            System.out.println(prefix);
        } else {
            for(int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }

    /**
     * The following code prints all the Fibonacci numbers from 0 to n
     * What is it's time complexity?
     */
    @Test
    public void example13() {
        allFib0(8);
    }

    /**
     * We can use the earlier pattern we'd established for recursive calls:
     * O(branches^depth)
     *
     * There are 2 branches per call, and we go as deep as N, therefore
     * the runtime is O(2^N)
     *
     * Generally speaking, when you see an algorithm with multiple recursive
     * calls, you're looking at exponential runtime.
     */
    private void allFib0(int n) {
        for(int i = 0; i < n; i++) {
            System.out.println(i + ": " + fib0(i));
        }
    }

    private int fib0(int n) {
        if(n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fib0(n - 1) + fib0(n - 2);
    }

    /**
     * Many people will rush to concluding that since fib1(n) takes O(2^n) time
     * and it's called 'n' times, then it's O(n2^n)
     *
     * Not so fast. Can you see the error in logic?
     *
     * The error is that 'n' is changing. Yes fib1(n) takes O(2^n) time and it's called
     * 'n' times, but it matters what the value of 'n' is.
     *
     * Instead let's walk through each call:
     *
     * fib1(1) --> 2^1 steps
     * fib1(2) --> 2^2 steps
     * fib1(3) --> 2^3 steps
     * fib1(4) --> 2^4 steps
     * ...
     * fib1(n) --> 2^n steps
     *
     * Therefore, the total amount of work is:
     *
     * 2^1 + 2^2 + 2^3 + 2^4 + ... + 2^n
     *
     * The runtime to compute the first 'n' Fibonacci numbers
     * using this terrible algorithm is still O(2^n)
     */
    @Test
    public void example14() {
        allFib1(8);
    }

    private void allFib1(int n) {
        for(int i = 0; i < n; i++) {
            System.out.println(i + ": " + fib1(i));
        }
    }

    private int fib1(int n) {
        if(n <= 0) return 0;
        else if (n == 1) return 1;
        return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * The following code prints all Fibonacci numbers from 0 to n.
     * However, this time, it stores (i.e. caches) previously
     * computed values in an integer array. If it has already been computed,
     * it just returns the cache. What is it's runtime?
     */
    @Test
    public void example15() {
        allFib2(8);
    }

    private void allFib2(int n){
        int[] memo = new int[n+1];
        for(int i = 0; i < n; i++) {
            System.out.println(i + ": " + fib2(i, memo));
        }
    }

    /**
     * Let's walk through what this algorithm does.
     *
     * fib2(0) --> return 0
     * fib2(1) --> return 1
     * fib2(2)
     *     fib2(1) --> return 1
     *     fib2(0) --> return 0
     *     store 1 at memo[2]
     * fib2(3) -->
     *     fib2(2) --> lookup memo[2] --> return 1
     *     fib2(1) --> return 1
     *     store 2 at memo[3]
     * fib2(4) -->
     *     fib2(3) --> lookup memo[3] --> return 2
     *     fib2(1) --> return 1
     *     store 3 at memo[4]
     * fib2(5) -->
     *     fib2(4) --> lookup memo[4] --> return 3
     *     fib2(3) --> lookup memo[3] --> return 2
     *     store 5 at memo[5]
     * ...
     *
     * At each call to fib2(i), we have already computed and stored
     * the values for fib2(i-1) and fib2(i-2)
     * We just look up those values, sum them, store the new result, and return.
     *
     * This takes constant amount of time.
     *
     * We're doing a constant amount of work n times, so this is O(n) time.
     *
     * This technique is called memoization, it's a very common one to
     * optimize exponential time recursive algorithms.
     */
    private int fib2(int n, int[] memo) {
        if(n <= 0) return 0;
        else if (n == 1) return 1;
        else if (memo[n] > 0) return memo[n];

        memo[n] = fib2(n - 1, memo) + fib2(n - 2, memo);
        return memo[n];
    }

    /**
     * The following function prints the powers of 2 from 1 through n (inclusive).
     * For example, if 'n' is 4, it would print 1, 2, and 4.
     * What is it's runtime?
     */
    @Test
    public void example16() {
        powersOf2(4);
    }

    /**
     * What it does:
     *
     * Let's walk through a call like powersOf2(50)
     *
     * powersOf2(50)
     *     --> powersOf2(25)
     *         --> powersOf2(12)
     *             --> powersOf2(6)
     *                 --> powersOf2(3)
     *                     --> powersOf2(1)
     *                         --> print & return 1
     *                     print & return 2
     *                 print & return 4
     *             print & return 8
     *         print & return 16
     *     print & return 32
     *
     * The runtime, then, is the number of times we can divide 50 (or n)
     * by 2 until we get down to the base case (1).
     *
     * The number of times we can halve 'n' until we get 1 is O(log n)
     *
     * What it means:
     *
     * We can also approach the runtime by thinking about what the code is
     * supposed to be doing. It's supposed to be computing the powers of 2
     * from 1 through 'n'.
     *
     * Each call to powersOf2() results in exactly one number being printed
     * and returned (excluding what happens in the recursive calls). So if
     * the algorithm prints 13 values at the end, the powersOf2() was called
     * 13 times.
     *
     * In this case, we are told that it prints all the powers of 2 between 1 and
     * 'n'. Therefore, the number of times the function is called (which will be
     * its runtime) must equal the number of powers of 2 between 1 and 'n'.
     *
     * There are log N powers of 2 between 1 and 'n'.
     *
     * Therefore the runtime is O(log n)
     *
     * Rate of increase:
     *
     * A final way to approach the runtime is to think about how the runtime changes
     * as n gets bigger. After all, this is exactly what big O time means.
     *
     * If N goes from P to P+1, the number of calls to powersOf2 might not change at all.
     * When will the number of calls to powersOf2 increase? It will increase by 1 each time
     * 'n' doubles in size.
     *
     * So, each time 'n' doubles, the number of calls to powersOf2 increases by 1. Therefore,
     * the number of calls to powersOf2 is the number of times you can double 1 until you get
     * to 'n'. It is x in the equation 2^x = n
     *
     * What is x? The value of x is log n. This is exactly x = log n
     *
     * Therefore the runtime is O(log n).
     */
    private int powersOf2(int n) {
        if(n < 1) {
            return 0;
        } else if (n == 1) {
            System.out.println(1);
            return 1;
        } else {
            int prev = powersOf2(n / 2);
            int curr = prev * 2;
            System.out.println(curr);
            return curr;
        }
    }


}
