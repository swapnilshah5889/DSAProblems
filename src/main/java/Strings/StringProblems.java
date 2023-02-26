package Strings;

import java.util.HashMap;
import java.util.HashSet;

public class StringProblems {

    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> map = new HashSet<>();
        int tempLen = 0;
        int max = 0;
        for(int i=0; i<s.length();i++){
            Character c = s.charAt(i);
            if(map.contains(c)){
                int index = i-tempLen;
                while(map.contains(c)){
                    tempLen--;
                    map.remove(s.charAt(index));
                    index++;
                }
                map.add(c);
                tempLen++;
            }
            else{
                tempLen++;
                map.add(c);
            }

            if(tempLen>max)
                max=tempLen;
        }

        return max;

    }

    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("umvejcuuk"));
    }

}
