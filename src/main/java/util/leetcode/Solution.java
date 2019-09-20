package util.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songpanfei on 2019-09-19.
 */
public class Solution {

    public static void main(String[] args){
        System.out.println(reverse2(-2147483648));
        System.out.println(IsPalindrome3(1234));
    }

    public boolean wordPattern(String pattern, String str) {
        HashMap<String,String> h=new HashMap<String,String>();
        String[] a =str.split(" ");
        int s1=pattern.length();
        int s2=a.length;
        if(s1!=s2){return false;}
        for(int i=0;i<s1;i++){
            String d=a[i];
            String g=pattern.substring(i,i+1);
            if((!h.containsKey(g) && h.containsValue(d)) || (h.containsKey(g) && !h.get(g).equals(d))) {
                return false;
            }else  {
                h.put(g,d);
            }
        }
        return true;
    }

    /**
     Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     Output: 7 -> 0 -> 8
     Explanation: 342 + 465 = 807.
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            return  val + " ";
        }
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String num1 = "";
        while (l1 != null){
            num1 = l1.val + num1 ;
            l1 = l1.next;
        }
        String num2 ="";
        while (l2 != null){
            num2 = l2.val + num2 ;
            l2 = l2.next;
        }
        int v1 = num1.length();
        int v2 = num2.length();
        if(v1 > v2){
            for(int i=0;i<v1 - v2;i++){
                num2 = "0" + num2;
            }
        }
        if(num2.length() > v1){
            for(int i=0;i<v2 - v1;i++){
                num1 = "0" + num1;
            }
        }
        v1 = num1.length();
        int carry = 0;
        int yu = 0;
        ListNode h = null;
        ListNode cu = null;
        for(int i=v1-1;i>=0;i--){
            int result = Integer.parseInt(String.valueOf(num1.charAt(i))) +Integer.parseInt(String.valueOf(num2.charAt(i))) + carry;
            carry = result / 10;
            yu = result % 10;
            ListNode c = new ListNode(yu);
            if(h == null){
                h = c;
                cu = c;
            }else {
                cu.next = c;
                cu = c;
            }
        }
        if(carry == 1){
            ListNode c = new ListNode(1);
            cu.next = c;
        }
        return h;
    }

    public static int reverse1(int x) {
        String r = x + "";
        String result = "";
        for(int i=r.length()-1;i>=0;i--){
            String w = r.substring(i,i+1);
            if(w.equals("-")){
                result = w + result;
            }else {
                result = result + w;
            }
        }
        try {
            return Integer.parseInt(result);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int reverse2(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x = x / 10;
        }
        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return 0;
        } else {
            return (int)res;
        }
    }

    public static boolean isPalindrome1(int x) {
        String k = x + "";
        String result="";
        int length = k.length();
        for(int i=length-1;i>=0;i--){
            char index  = k.charAt(i);
            result = result + index;
        }
        return k.equals(result);
    }

    public static boolean isPalindrome2(int x) {
        String k = x + "";
        for(int i=0;i<k.length()/2;i++){
            if(k.charAt(i) != k.charAt(k.length()-1-i)){
                return false;
            }
        }
        return true;
    }
    public static boolean IsPalindrome3(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber/10;
    }

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] chars = s.toCharArray();
        int result = 0;
        int i = 0, j = 1;
        for(; j < chars.length; i++, j++) {
            if (map.get(chars[i]) >= map.get(chars[j])) {
                result += map.get(chars[i]);
            } else {
                result -= map.get(chars[i]);
            }
        }
        result += map.get(chars[i]);
        return result;
    }
}
