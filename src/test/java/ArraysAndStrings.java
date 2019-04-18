import org.junit.Test;

import java.util.Arrays;

public class ArraysAndStrings {

    /**
     * Implement an algorithm to determine if a string has
     * all unique characters. What if you cannot use additional
     * data structures?
     */
    @Test
    public void isUnique() {
        boolean r0 = isUniqueChars("Sean");
        System.out.println("r0: " + r0);

        boolean r1 = isUniqueChars("Matthew");
        System.out.println("r1: " + r1);
    }

    /**
     * The time complexity for this code is O(n), where 'n' is the length
     * of the string. The space complexity is O(1). (You could also argue
     * the time complexity is O(1), since the for loop will never iterate
     * through more than 128 characters.) If you don't want to assume the
     * character set is fixed, you could express the complexity as O(c)
     * space and O(min(c,n)) or O(c) time, where c is the size of the character
     * set.
     */
    private boolean isUniqueChars(String str) {
        if (str.length() > 128) return false;

        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                // Already found this char in string
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    /**
     * Given two strings, write a method to decide if one is a permutation
     * of the other.
     */
    @Test
    public void checkPermutation0() {
        boolean r0 = permutation0("Matthew", "wehttaM");
        System.out.println("r0: " + r0);
        boolean r1 = permutation0("Sean", "sean");
        System.out.println("r1: " + r1);
    }

    /**
     * If two strings are permutations, then we know they have the same characters,
     * but in different orders. Therefore, sorting the strings will put the characters
     * from the two permutations in the same order. We just need to compare the sorted
     * versions of the strings.
     */

    private String sort0(String s) {
        char[] content = s.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }

    private boolean permutation0(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return sort0(s).equals(sort0(t));
    }

    /**
     * Given two strings, write a method to decide if one is a permutation
     * of the other.
     */
    @Test
    public void checkPermutation1() {
        boolean r0 = permutation1("Matthew", "wehttaM");
        System.out.println("r0: " + r0);
        boolean r1 = permutation1("Sean", "sean");
        System.out.println("r1: " + r1);
    }

    private boolean permutation1(String s, String t) {
        if (s.length() != t.length()) return false; // Permutations must be same length

        int[] letters = new int[128]; // Assume ASCII
        for (int i = 0; i < s.length(); i++) {
            letters[s.charAt(i)]++;
        }

        System.out.println("letters: " + Arrays.toString(letters));
        int c = 0;
        for (int x : letters) {
            c += x;
        }
        System.out.println("c: " + c);

        for (int i = 0; i < t.length(); i++) {
            letters[t.charAt(i)]--;
            if (letters[t.charAt(i)] < 0) {
                return false;
            }
        }
        return true; // letters has no neg values, and therefore no pos values either
    }

    /**
     * Given two strings, write a method to decide if one is a permutation
     * of the other.
     */
    @Test
    public void urlify() {
        String s0 = "Mr. John Smith";
        String r0 = replaceSpaces(s0.toCharArray());
        System.out.println("r0: " + r0);

        String s1 = "13";
        String r1 = replaceSpaces(s1.toCharArray());
        System.out.println("r1: " + r1);
    }

    private String replaceSpaces(char[] str) {
        int trueLength = str.length;

        System.out.println("trueLength: " + trueLength);

        int numberOfSpaces = countOfChar(str, 0, trueLength, ' ');
        int newIndex = trueLength - 1 + numberOfSpaces * 2;

        char[] r = new char[newIndex];

        /**
         * If there are excess spaces, add a null character. This indicates that the
         * spaces after that point have not been replaced with %20.
         */
        if (newIndex + 1 < str.length) str[newIndex + 1] = '\0';
        for (int oldIndex = trueLength - 1; oldIndex >= 0; oldIndex -= 1) {

            System.out.println();

            if (str[oldIndex] == ' ') {
                // Insert %20
                r[newIndex] = '0';
                r[newIndex - 1] = '2';
                r[newIndex - 2] = '%';
                newIndex -= 3;
            } else {
                r[newIndex] = str[oldIndex];
                newIndex -= 1;
            }
        }

        return new String(r);
    }

    /**
     * Count occurrences of target between start (inclusive) and end (exclusive).
     */
    private int countOfChar(char[] str, int start, int end, int target) {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (str[i] == target) {
                count++;
            }
        }
        return count;
    }

    /**
     * The algorithm takes O(N) time, where N is the length of the string.
     */
    @Test
    public void permutationPalindrome() {
        boolean r0 = isPermutationPalindrome("wehttaM");
        System.out.println("r0: " + r0);
        boolean r1 = isPermutationPalindrome("Taco Coa");
        System.out.println("r1: " + r1);
    }

    private boolean isPermutationPalindrome(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }

    /* Check that no more than one character has an odd count */
    private boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for(int count : table) {
            if(count % 2 == 1) {
                if(foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }

    /**
     * Map each character to a number. a -> 0, b -> 1, c -> 2, etc.
     * This is case insensitive. Non-letter characters map to -1.
     */
    private int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if(a <= val && val <= z) {
            return val - a;
        }
        return -1;
    }

    /**
     * Count how many times each character appears.
     */
    private int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for(char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if(x != -1) {
                table[x]++;
            }
        }
        return table;
    }


    @Test
    public void permutationPalindrome0() {
        boolean r0 = isPermutationPalindrome0("wehttaM");
        System.out.println("r0: " + r0);
        boolean r1 = isPermutationPalindrome0("Taco Coa");
        System.out.println("r1: " + r1);
    }

    /**
     * Instead of checking the number of odd counts at the end, we can check as we
     * go along. Then, as soon as we get to the end, we have our answer.
     */

    private boolean isPermutationPalindrome0(String phrase) {
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];

        for(char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if(x != -1) {
                table[x]++;
                if(table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }

    /**
     * It's interesting to note a solution we did not explore. We avoided solutions along the lines
     * of "create all possible permutations and check if they are palindromes". While such a solution
     * would work, it's entirely infeasible in the real world. Generating all permutations requires
     * factorial time (which is actually worse than exponential time), and it is essentially infeasible
     * to perform on strings longer than about 10-15 characters.
     *
     * I mention this (impractical) solution because a lot of candidates hear a problem like this and
     * say "In order to check if A is in group B, I must know everything that is in B and then check
     * if one of the items equals A". That's not always the case, and this problem is a simple demonstration
     * of it. You don't need to generate all permutations in order to check if one is a palindrome.
     */
    @Test
    public void permutationPalindromeX() {
        boolean r0 = isPermutationPalindromeX("wehttaM");
        System.out.println("r0: " + r0);
        boolean r1 = isPermutationPalindromeX("Taco Coa");
        System.out.println("r1: " + r1);
    }

    /* Toggle the ith bit in the integer */
    private int toggle(int bitVector, int index) {
        if(index < 0) return bitVector;

        int mask = 1 << index;
        bitVector ^= mask;
        return bitVector;
    }

    /**
     * Create bit vector for string. For each letter with value i,
     * toggle the ith bit.
     */
    private int createBitVector(String phrase) {
        int bitVector = 0;
        for(char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            bitVector = toggle(bitVector, x);
        }
        return bitVector;
    }

    /**
     * Check that at most one bit is set by subtracting one from the
     * integer and ANDing it with the original integer.
     */
    private boolean checkAtMostOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }

    private boolean isPermutationPalindromeX(String phrase) {
        int bitVector = createBitVector(phrase);
        return checkAtMostOneBitSet(bitVector);
    }
}




