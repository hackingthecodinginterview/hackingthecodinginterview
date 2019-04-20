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

    /**
     * There are three types of edits that can be performed on strings: insert a character,
     * remove a character, or replace a character. Given two strings, write a function to
     * check if they are one edit (or zero edits) away.
     *
     * EXAMPLE
     *
     * "pale",  "ple"  --> true
     * "pales", "pale" --> true
     * "pale",  "bale" --> true
     * "pale",  "bae"  --> false
     *
     * There's a "brute force" algorithm to do this. We could check all possible strings that
     * are one edit away by testing the removal of each character (and comparing), testing
     * the replacement of each character (and comparing), and then testing the insertion
     * of each possible character (and comparing).
     *
     * That would be too slow, so let's not bother with implementing it.
     *
     * This is one of those problems where it's helpful to think about the "meaning" of each
     * of these operations. What does it mean for two strings to be one insertion, replacement,
     * or removal away from each other?
     *
     * REPLACEMENT: Consider two strings such as "bale" and "pale", that are one replacement away.
     * Yes, that does mean that you could replace a character in "bale" to make "pale". But more
     * precisely, it means that they are different in only one place.
     *
     * INSERTION: The strings "apple" and "aple" are one insertion away. This means that if you compared
     * the strings, they would be identical -- except for a shift at some point ion the strings.
     *
     * REMOVAL: The strings "apple" and "aple" are also one removal away, since removal is just the inverse
     * of insertion.
     */

    @Test
    public void oneAway(){
        String replacement0 = "bale";
        String replacement1 = "pale";
        boolean r0 = oneEditAway(replacement0, replacement1);
        System.out.println("r0: " + r0);

        String insertion0 = "apple";
        String insertion1 = "aple";
        boolean r1 = oneEditAway(insertion0, insertion1);
        System.out.println("r1: " + r1);


        String removal0 = "apple";
        String removal1 = "aple";
        boolean r2 = oneEditAway(removal0, removal1);
        System.out.println("r2: " + r2);
    }

    // Observe that you don't need to check the strings for insertion, removal, and replacement edits.
    // The lengths of the strings will indicate which of these you need to check.
    private boolean oneEditAway(String first, String second) {
        if(first.length() == second.length()) {
            return oneEditReplace(first, second);
        } else if (first.length() + 1 == second.length()) {
            return oneEditInsert(first, second);
        } else if (first.length() - 1 == second.length()) {
            return oneEditInsert(second, first);
        }
        return false;
    }

    private boolean oneEditReplace(String str1, String str2) {
        boolean foundDifference = false;
        for(int i = 0; i < str1.length(); i++)  {
            if(str1.charAt(i) != str2.charAt(i)) {
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;
            }
        }
        return true;
    }

    /**
     * Check if you can insert a character into str1 to make str2.
     */
    private boolean oneEditInsert(String str1, String str2) {
        int index1 = 0;
        int index2 = 0;
        while(index2 < str2.length() && index1 < str1.length()) {
            if(str1.charAt(index1) != str2.charAt(index2)) {
                if(index1 != index2) {
                    return false;
                }
                index2++;
            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }

    // This algorithm (and almost any reasonable algorithm) takes O(n) time,
    // where n is the length of the shorter string.


    private boolean oneEditAwayX(String first, String second) {
        /* Length check */
        if(Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        /* Get shorter and longer string. */
        String s1 = first.length() < second.length() ? first : second;
        String s2 = first.length() < second.length() ? second : first;

        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;
        while (index2 < s2.length() && index1 < s1.length()) {
            if(s1.charAt(index1) != s2.charAt(index2)) {
                /* Ensure that this is the first difference found */
                if(foundDifference) {
                    return false;
                }
                foundDifference = true;

                if(s1.length() == s2.length()) {
                    // On replace, move shorter pointer.
                    index1++;
                }
            } else {
                index1++; // If matching, move shorter pointer.
            }
            index2++;
        }
        return true;
    }

    @Test
    public void oneAwayX(){
        String replacement0 = "bale";
        String replacement1 = "pale";
        boolean r0 = oneEditAwayX(replacement0, replacement1);
        System.out.println("r0: " + r0);

        String insertion0 = "apple";
        String insertion1 = "aple";
        boolean r1 = oneEditAwayX(insertion0, insertion1);
        System.out.println("r1: " + r1);


        String removal0 = "apple";
        String removal1 = "aple";
        boolean r2 = oneEditAwayX(removal0, removal1);
        System.out.println("r2: " + r2);

        String x0 = "s";
        String x1 = "matthew";
        boolean r3 = oneEditAwayX(x0, x1);
        System.out.println("r3: " + r3);
    }

    /**
     * Implement a method to perform basic string compression using the counts of repeated characters.
     * For example, the string 'aabcccccaaa' would become 'a2b1c5a3'. If the "compressed" string would
     * not become smaller than the original string, your method should return the original string. You
     * can assume the string has only uppercase and lowercase letters (a-z).
     */

    @Test
    public void stringCompression() {
        String i0 = "aabcccccaaa";
        String r0 = compressBad(i0);
        System.out.println("r0: " + r0);
    }

    /**
     * The runtime is O(p + k^2), where p is the size of the original string and k is the number
     * of character sequences. For example, if the string is "aabcccccaaa", then there are six
     * character sequences. It's slow because string concatenation operates in O(n^2) time.
     */
    String compressBad(String str) {
        String compressedString = "";

        int countConsecutive = 0;
        for(int i = 0; i < str.length(); i++){
            countConsecutive++;

            /* If next character is different than current, append this char to result. */
            if(i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedString += "" + str.charAt(i) + countConsecutive;
                countConsecutive = 0;
            }
        }
        return compressedString.length() < str.length() ? compressedString : str;
    }

    private String compress(String str) {
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        for(int i = 0; i < str.length(); i++) {
            countConsecutive++;

            /* If next character is different than current, append this char to result. */
            if(i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.length() < str.length() ? compressed.toString() : str;
    }

    @Test
    public void stringCompressionX() {
        String i0 = "aabcccccaaazzzzzzzzzzzzz";
        String r0 = compress(i0);
        System.out.println("r0: " + r0);
    }

    /**
     * One other benefit of this approach is that we can initialize StringBuilder to its necessary capacity up-front.
     * Without this, StringBuilder will (behind the scenes) need to double its capacity every time it hits capacity.
     * The capacity could be double what we ultimately need.
     */

    private String compressZ(String string) {
        /* Check final length and return input string if it would be longer. */
        int finalLength = countCompression(string);
        if(finalLength >= string.length()) {
            return string;
        }

        StringBuilder compressed = new StringBuilder(finalLength); // initial capacity
        int countConsecutive = 0;
        for(int i = 0; i < string.length(); i++) {
            countConsecutive++;
            /* If next character is different than current, append this char to result. */
            if(i+1 >= string.length() || string.charAt(i) != string.charAt(i + 1)) {
                compressed.append(string.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.toString();
    }

    private int countCompression(String str) {
        int compressedLength = 0;
        int countConsecutive = 0;
        for(int i = 0; i < str.length(); i++) {
            countConsecutive++;

            /* If next character is different than current, increase the length. */
            if(i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedLength += 1 + String.valueOf(countConsecutive).length();
                countConsecutive = 0;
            }
        }
        return compressedLength;
    }

    @Test
    public void stringCompressionZ() {
        String i0 = "aabcccccaaazzzzzzzzzzzzz";
        String r0 = compressZ(i0);
        System.out.println("r0: " + r0);
    }

    /**
     * Given an image represented by an NxN matrix, where each pixel in the image is
     * represented by an integer, write a method to rotate the image by 90 degrees.
     * Can you do it in place?
     *
     * Because we're rotating the matrix by 90 degrees, the easiest way to do this is
     * to implement the rotation in layers. We perform a circular rotation on each
     * layer, moving the top edge to the right edge, the right edge to the bottom
     * edge, the bottom edge to the left edge, and the left edge to the top edge.
     *
     * How do we perform this four-way edge swap?
     *
     * One option is to copy the top edge to an array, and then move the left to the top,
     * the bottom to the left, and so on. This requires O(N) memory, which is actually
     * unnecessary.
     *
     * A better way to do this is to implement the swap index by index. In this case, we
     * do the following:
     *
     * for i = 0 to n
     *     temp = top[i]
     *     top[i] = left[i]
     *     left[i] = bottom[i]
     *     bottom[i] = right[i]
     *     right[i] = temp
     *
     * We perform such a swap on each layer, starting from the outermost layer and working
     * our way inwards. (Alternatively, we could start from the inner layer and work outwards)
     */
    private boolean rotate(int[][] matrix) {
        if(matrix.length == 0 || matrix.length != matrix[0].length) {
            return false;
        }

        int n = matrix.length;
        for(int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            for(int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i]; // save top

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top; // right <- saved top
            }
        }
        return true;
    }

    @Test
    public void rotateMatrix() {

        int[] a0 = new int[]{0, 0, 0, 0};
        int[] a1 = new int[]{1, 1, 1, 1};
        int[] a2 = new int[]{2, 2, 2, 2};
        int[] a3 = new int[]{3, 3, 3, 3};

        int[][] matrix = new int[][]{a0, a1, a2, a3};
        System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        //boolean r0 = rotate(matrix);
        //System.out.println("r0: " + r0);
        System.out.println();

        int[][] matrix0 = rotateX(matrix);
        System.out.println(Arrays.deepToString(matrix0).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }


    /**
     * This algorithm is O(N^2), which is the best we can do since any algorithm must touch all N^2 elements.
     */
    private int[][] rotateX(int[][] matrix) {

        int n = matrix.length;
        for(int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            for(int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i]; // save top

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top; // right <- saved top
            }
        }
        return matrix;
    }

}




