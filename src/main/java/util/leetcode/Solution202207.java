package util.leetcode;


import java.util.Arrays;

public class Solution202207 {

    public static void main(String[] args) {
        int[] a = new int[]{0,0,1,2,4,2,2,3,1,4};
        getLeastNumbers(a, 8);
    }

    //6. Z 字形变换
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
            if(loc == 0 || loc == numRows-1){
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

    //7. 整数反转
    //int 最大值2147483647 最小值-2147483648
    public int reverse(int x) {
        int res = 0;
        while (x != 0){
            int dig = x%10;
            if(res > 214748364 || (res == 214748364 && dig > 7)){
                return 0;
            }
            if(res < -214748364 || (res == -214748364 && res < -8)){
                return 0;
            }
            x = x/10;
            res = res * 10 + dig;
        }
        return res;
    }


    //9. 回文数
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        if(s.length() == 1){
            return true;
        }
        int left = 0;
        int right = s.length()-1;
        while (left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    //面试题40. 最小的k个数 使用大顶堆计算
    public static int[] getLeastNumbers(int[] arr, int k) {
        if(k==0){
            return new int[0];
        }
        return heap(arr, k);
    }

    public static int[] heap(int[] array, int k){
        int[] tmp = new int[k];
        for(int i=0;i<k;i++){
            tmp[i] = array[i];
        }
        for(int i=k/2;i>=0;i--){
            heap(tmp, i, k);
        }
        for(int i=k;i< array.length;i++){
            if(tmp[0]<=array[i]){
                continue;
            }
            tmp[0] = array[i];
            heap(tmp, 0, k);
        }
        return tmp;
    }

    public static void heap(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
            if(k+1<n && array[k+1]>array[k]){
                k++;
            }
            if(array[k] > tmp){
                array[i] = array[k];
                i = k;
            }else break;
        }
        array[i] = tmp;
    }



}
