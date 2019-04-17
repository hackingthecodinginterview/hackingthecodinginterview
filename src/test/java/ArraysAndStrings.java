import org.junit.Test;

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
        if(str.length() > 128) return false;

        boolean[] char_set = new boolean[128];
        for(int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if(char_set[val]) {
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
    public void checkPermutation() {
        
    }

    /**
     *
     */
}
