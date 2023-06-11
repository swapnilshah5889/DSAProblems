package LeetCode;

public class LeetCodePractice {

    public static int findFirstOccurence(String haystack, String needle) {
        int end = 0;
        int len = needle.length();
        for(int i=0; i<haystack.length(); i++) {
            if(haystack.charAt(i) == needle.charAt(end) ) {
                end++;
                if(end == len) {
                    System.out.println("In : "+i);
                    return (i - end + 1);
                }
            }
            else {
                int iend = i+end;
                i-=end;
                while(i<iend && haystack.charAt(i) != needle.charAt(0)) {
                    i++;
                }
                end = 0;
            }
        }

        if(end == len)
            return len-end;
        else
            return -1;
    }

    public static void main(String[] args) {

        //Find first occurrence of a substring
        System.out.println(findFirstOccurence("mississippi", "issip"));
    }

}
