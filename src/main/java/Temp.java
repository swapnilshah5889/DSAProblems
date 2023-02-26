import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class Temp {

    public static void main(String args[]) {

        Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
        List<Integer> list = Arrays.asList(arr);

        System.out.println(Lists.partition(list, 4));
    }



}
