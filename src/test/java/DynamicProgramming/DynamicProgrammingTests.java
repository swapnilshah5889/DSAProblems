package DynamicProgramming;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DynamicProgrammingTests {

    @Test
    public void TestMaxSubarrayProduct(){

        // Test Case 1
        int index = 1;
        int[] arr = new int[]{-3, 0, -5, 0};
        int ans = DynamicProgramming.maxSubarrayProduct(arr);
        assertEquals(0, ans);

        //Test Case 2
        arr = new int[]{4, 2, -5, 1};
        ans = DynamicProgramming.maxSubarrayProduct(arr);
        assertEquals(8, ans);

        // Test Case 3
        arr = new int[]{0,0,0,-3,-2,0,1,0,0,0,0,0,-2,0,0,0,3,3,0,0,0,0,3,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-2,
                0,0,0,-1,0,0,0,0,0,0,2,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,2,0,0,0,0,0,0,0,0,-1,0,0,3,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,-2,0,0,0,0,-1,0,0,0,0,0,0,-3,0,0,0,0,-1,0,2,0,0,0,0,3,0,0,0,0,0,0,0,0,0,2,0,2,0,0,-2,0,1,
                0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,-3,1,0,0,0,0,0,0,0,0,0,0,-2,0,0,3,0,0,0,0,0,0,
                0,0,0,1,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,-3,0,0,0,
                0,0,0,0,-1,-2,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0};
        ans = DynamicProgramming.maxSubarrayProduct(arr);
        assertEquals(9, ans);

        arr = new int[]{0,0,-1,0,0,0,3,0,0,-2,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,1,0,0,3,0,0,0,0,-1,-1,0,3,0,0,0,0,0,0,
                0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,2,0,0,0,-2,1,0,0,0,0,0,0,-1,0,0,0,0,1,0,0,0,0,0,0,0,0,-1,0,-2,0,
                0,0,0,2,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-3,-2,0,0,0,0,0,0,0,-2,0,0,-1,0,0,0,0,0,
                0,0,-1,0,0,0,0,0,0,2,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,1,0,0,0,0,0,0,0,2,0,0,-2,
                0,0,0,0,3,-2,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-3,0,0,0,0,
                0,0,0,0,-3,0,0,0,0,2,0,0,0,0,0,0,-2,0,0,-3,-3,0,0,0,0,0,0,0,-3,0,0,2,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,
                0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,1,
                0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-2,0,0,0,0,1,0,0,0,0,0,0,0,0,0,3,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,3,0,0,0,0,-3,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,
                0,0,0,0,0,0,0,0,0,0,-1,-1,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-3,-2,0,0,-2,0,1,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-3,0,0,0,0,0,0,0,0,0,-2,0,0,0,0,0,2,0,0,0,-1,0,0,0,-2,0,0,0,0,0,0,0,0,
                0,-3,0,1,0,0,0,0,0,-2,0,0,0,0,0,0,0,-2,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,3,0,0,0,0,1,0,0,0,0,0,
                0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,-2,0,0,0,0,0,0,0,-3,0,-3,-2,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,1,2,1,0,
                0,0,0,0,0,-1,0,3,1,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,-2,0,2,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                -3,0,0,0,0,0,0,0,0,0,1,-2,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-2,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,-2,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,3,0,1,0,0};

        ans = DynamicProgramming.maxSubarrayProduct(arr);
        assertEquals(9, ans);
    }

    @Test
    public void TestMaxSumValue() {
        // Test Case 1
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 5, -3, 4, -2));
        int B = 2;
        int C = 1;
        int D = -1;
        int ans = DynamicProgramming.maxSumValue(A,B,C,D);
        assertEquals(18, ans);

        // Test Case 2
        A = new ArrayList<>(Arrays.asList(3, 2, 1));
        B = 1;
        C = -10;
        D = 3;
        ans = DynamicProgramming.maxSumValue(A,B,C,D);
        assertEquals(-4, ans);

    }

    @Test
    public void TestDecodeString() {
        // Test case 1
        int ans = DynamicProgramming.decodeString("01");
        assertEquals(0,ans);

        // Test case 2
        ans = DynamicProgramming.decodeString("10");
        assertEquals(1,ans);

        // Test case 3
        ans = DynamicProgramming.decodeString("21");
        assertEquals(2,ans);

        // Test case 4
        ans = DynamicProgramming.decodeString("20");
        assertEquals(1,ans);

        // Test case 5
        ans = DynamicProgramming.decodeString("00");
        assertEquals(0,ans);

        // Test case 6
        ans = DynamicProgramming.decodeString("51");
        assertEquals(1,ans);

        // Test case 7
        ans = DynamicProgramming.decodeString("27");
        assertEquals(1,ans);

        // Test case 8
        ans = DynamicProgramming.decodeString("90");
        assertEquals(0,ans);

        // Test case 9
        ans = DynamicProgramming.decodeString("13");
        assertEquals(2,ans);

        // Test case 10
        ans = DynamicProgramming.decodeString("25");
        assertEquals(2,ans);

        // Test case 11
        ans = DynamicProgramming.decodeString("19");
        assertEquals(2,ans);

        // Test case 12
        ans = DynamicProgramming.decodeString("35");
        assertEquals(1,ans);

        // Test case 13
        ans = DynamicProgramming.decodeString("43");
        assertEquals(1,ans);

        // Test case 14
        ans = DynamicProgramming.decodeString("0");
        assertEquals(0,ans);

        // Test case 15
        ans = DynamicProgramming.decodeString("7");
        assertEquals(1,ans);
    }
}
