import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MostSignificantDigit {

    int[] exampleA = new int[]{1,2,3};
    int[] exampleB = new int[]{4,3,2,1};
    int[] exampleC = new int[]{9,9};
    int[] exampleD = new int[]{9};
    int[] exampleE = new int[]{2};

    int[] exampleF = new int[]{8,9,9,9};

    @Test
    public void mostSignificantDigit() {
        int[] x = plusOne(exampleF);

        System.out.println("--> " + Arrays.toString(x));
    }

    public int[] plusOne(int[] digits) {
        if(digits.length == 1) {
            if(digits[digits.length - 1] == 9) {
                return new int[]{1,0};
            } else {
                digits[digits.length - 1] += 1;
                return digits;
            }
        }

        if(digits.length > 1) {

            if(digits[digits.length - 1] == 9) {

                digits[digits.length - 1] = 0;

                for(int j = digits.length - 2; j >= 0; j--){

                    if(digits[j] == 9) {
                        digits[j] = 0;
                    } else {
                        digits[j] += 1;
                        return digits;
                    }

                    if(j == 0) {

                        int[] output = new int[digits.length + 1];

                        System.arraycopy(digits, 0, output, 1, digits.length);

                        output[0] = 1;

                        return output;
                    }
                }
            } else {
                digits[digits.length - 1] += 1;
                return digits;
            }
        }
        return null;
    }
}
