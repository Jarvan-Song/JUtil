package util.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songpanfei on 2019-09-19.
 */
public class Solution {

    public static void main(String[] args){
//        System.out.println(reverse2(-2147483648));
//        System.out.println(IsPalindrome3(1234));
//        System.out.println(searchInsert2(new int[]{1,2,4,5},6));
//        System.out.println(maxSubArray1(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        String a = "1010", b = "1011";
        System.out.println(mySqrt(110));
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

    public static int searchInsert1(int[] nums, int target) {
        int index = 0;
        if(target < nums[0]){
            return 0;
        }
        if(target > nums[nums.length-1]){
            return nums.length;
        }
        for(int i=0;i<nums.length;i++){
            int r = nums[i];
            if(target <= r){
                index = i;
                break;
            }
        }
        return index;
    }

    public static int searchInsert2(int[] nums, int target) {
        int low = 0;
        int high = nums.length -1;
        if(target < nums[0]){
            return 0;
        }
        if(target > nums[nums.length-1]){
            return nums.length;
        }
        while (low <= high){
            int middle = low + (high - low)/2;
            if(target == nums[middle]){
                return middle;
            }else if(target > nums[middle]){
                low = middle + 1;
            }else {
                high = middle -1;
            }
        }
        return low;
    }

    public static String countAndSay(int n) {
        String result = new String("1");
        for(int i=1;i<n;i++){
            result = temp(result);
        }
        return result;
    }

    public static String temp(String result){
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        char tep = result.charAt(0);
        for(int j=0;j<result.length();j++){
            if(result.charAt(j) == tep){
                count++;
            }else {
                stringBuilder.append(count).append(tep);
                count = 1;
                tep = result.charAt(j);
            }
        }
        return stringBuilder.append(count).append(tep).toString();
    }

    /*
     * Input: [-2,1,-3,4,-1,2,1,-5,4],
     * Output: 6
     * Explanation: [4,-1,2,1] has the largest sum = 6.
     */
    public static int maxSubArray1(int[] nums) {
        int res = Integer.MIN_VALUE, curSum = 0;
        for (int num : nums) {
            curSum = Math.max(curSum + num, num);
            res = Math.max(res, curSum);
        }
        return res;
    }

    public static int lengthOfLastWord1(String s) {
        int result = 0;
        int count = 0;
        if(s == null || s.equals("")){return 0;}
        for(int i=0;i<s.length();i++){
            char curr = s.charAt(i);
            if(String.valueOf(curr).equals(" ")){
                if(count > 0){
                    result = count;
                    count = 0;
                }
            }else {
                count++;
                result = count;
            }
        }
        return result;
    }

    public static int lengthOfLastWord2(String s) {
        String use = s.trim();
        int count = 0;
        for (int i = use.length() - 1; i >= 0; i--) {
            if (use.charAt(i) != ' ') count++;
            else break;
        }
        return count;
    }

    /**
     Input: [1,2,3]
     Output: [1,2,4]
     Explanation: The array represents the integer 123.
     */
    public static int[] plusOne(int[] digits) {
        int carry = 0;
        int length = digits.length;
        int[] result = new int[length];
        for(int i=length-1;i>=0;i--){
            int num = digits[i]+carry+(i==length-1?1:0);
            int yu = num % 10;
            carry = num/10 ;
            result[i] = yu;
        }
        if(carry == 0){
            return result;
        }else {
            int[] result2 = new int[length+1];
            result2[0] = 1;
            for(int j = 1;j<=length;j++){   //这个地方也可以省略，因为+1进位只有9这种情况，一旦进位则为0
                result2[j] = result[j-1];
            }
            return result2;
        }
    }

    /*
    Input: a = "1010", b = "1011"
    Output: "10101"
     */
    public static String addBinary(String a, String b) {
        int s1 = a.length();
        int s2 = b.length();
        int max = Math.max(s1,s2);
        int carry = 0;
        int v1,v2,res;
        String j = "";
        for(int i=0;i<max;i++){
            if(i<=s1-1){
                v1 = Integer.parseInt(a.charAt(s1-1-i)+"");
            }else {
                v1 =0;
            }
            if(i<=s2-1){
                v2 = Integer.parseInt(b.charAt(s2-1-i)+"");
            }else {
                v2 =0;
            }
            res = v1 + v2 + carry;
            int yu = res%2;
            carry = (res >=2?1:0);
            j = yu + j;
        }
        if(carry > 0){
            j = 1 + j;
        }
        return j;
    }

    public static String addBinary2(String a, String b) {
        int s1 = a.length()-1;
        int s2 = b.length()-1;
        int carry = 0;
        int v1,v2,res;
        String j = "";
        while (s1>=0 || s2>=0){
            if(s1>=0) {
                v1 = a.charAt(s1) - '0';
                s1--;
            }else {
                v1 = 0;
            }
            if(s2>=0) {
                v2 = b.charAt(s2) - '0';
                s2--;
            }else {
                v2 = 0;
            }
            res = v1 + v2 + carry;
            int yu = res%2;
            carry  = res/2;
            j = yu + j;
        }
        if(carry > 0){
            j = 1 + j;
        }
        return j;
    }

    public static int mySqrt(int x) {
        if (x == 0) return 0;
        int left = 1;
        int right = x;
        int middle = 0;
        while (left <= right){
            middle = left + (right - left)/2;
            if(middle == x/middle){
                return middle;
            }else if(middle > x/middle){
                right = middle -1;
            }else {
                left = middle + 1;
            }
        }
        return right;
    }
}
