package util.leetcode;


import java.util.Arrays;

public class Solution202207 {

    public static void main(String[] args) {

    }

    public static String convert(String s, int numRows) {
        if(numRows == 1){
            return s;
        }
        int min = Math.min(s.length(), numRows);
        String[] tmp =  new String[min];
        Arrays.fill(tmp, "");
        int loc = 0;
        boolean down = false;
        for(int i=0;i<s.length();i++){
            tmp[loc] = tmp[loc] + s.charAt(i);
            if(loc ==0 || loc == numRows-1){
                down = !down;
            }
            if(down){
                loc ++;
            }else {
                loc --;
            }
        }
        String res = "";
        for(String a: tmp){
            res = res + a;
        }
        return res;
    }

}
