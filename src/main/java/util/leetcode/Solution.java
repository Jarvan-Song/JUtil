package util.leetcode;

import com.sun.org.apache.regexp.internal.RE;
import org.omg.CORBA.MARSHAL;
import util.net.NetUtil;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by songpanfei on 2019-09-19.
 */
public class Solution {

    public static void main(String[] args) {
//        System.out.println(reverse2(-2147483648));
//        System.out.println(IsPalindrome3(1234));
//        System.out.println(searchInsert2(new int[]{1,2,4,5},6));
//        System.out.println(maxSubArray1(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
//        System.out.println(mySqrt(110));；
//        int[] nums1 = new int[]{1,2,3,0,0,0};
//        int m = 3;
//        int[] nums2 = new int[]{2,5,6};
//        int n = 3;
//        merge2(nums1,m,nums2,n);
//        for(int i=0;i<nums1.length;i++){
//            System.out.println("test "+nums1[i]);
//        }
//        int[] nums = new int[]{7,1,5,3,6,4};
//        maxProfit1(nums);2,2,1
//        System.out.println(lengthOfLongestSubstring("dvdf"));
//        System.out.println(lastSubstring("leetcode"));
//                int[] nums = new int[]{4,1,2,1,2};
//        System.out.println(singleNumber(nums));
//        System.out.println(isPalindrome("0p"));
//        System.out.println(getSum(997,24));
//        System.out.println(minDistance("张恒中国内地女演员","胡可中国内地女演员"));
//        float distance = (float)minDistance("张恒中国内地女演员", "胡可中国内地女演员");
//        System.out.println(distance);
//        System.out.println(distance/"张恒中国内地女演员".length());
//        System.out.println(distance/"胡可中国内地女演员".length());
//        if(distance/"张恒中国内地女演员".length() < 0.2 || distance/"胡可中国内地女演员".length() < 0.2){
//            System.out.println("111111111111");
//        }
//        String title ="测试(13123)";
//        int inx = title.indexOf("(");
//        if(inx > -1){
//            title = title.substring(0, inx);
//        }
//        System.out.println(title);
//        int[] nums = new int[]{1,2,2};
//        System.out.println(threeSum2(nums));
//        System.out.println(majorityElement(nums));
//        System.out.println(isPowerOfThree(45));
//        System.out.println(Math.log(45) / Math.log(3));
//        System.out.println(Math.log(  99999.999999999999999999999)/Math.log(99999.9999999999));
//        System.out.println(Math.log10(99999.999999999999999999999)/Math.log10(99999.9999999999));
//        System.out.println(isHappy(19));
//        System.out.println(findUnsortedSubarray(nums));
//        System.out.println(topKFrequent(nums, 2));
//        System.out.println(subsets(nums));
//        System.out.println(findDuplicate(nums));
//        ListNode a = new ListNode(1);
//        ListNode b = new ListNode(2);
//        ListNode c = new ListNode(3);
//        ListNode d = new ListNode(4);
//        ListNode e = new ListNode(5);
//        a.next = b;
//        b.next = c;
//        c.next = d;
//        d.next = e;
//        a = oddEvenList2(a);
//        while (a != null){
//            System.out.println(a.val);
//            a = a.next;
//        }
//        String s = "3[a]2[bc]";
//        System.out.println(decodeString2(s));
//        char[][] a = new char[][]{
//                {'1','1','1','1','0'},
//                {'1','1','0','1','0'},
//                {'1','1','0','0','0'},
//                {'0','0','0','0','0'}};
//        char[][] w = {{'1'}};
        int[] a = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea3(a));
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
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode f = l1;
        ListNode s = l2;
        int carray = 0;
        ListNode head = null;
        ListNode curr = null;
        while (f != null || s!= null){
            int f1 = f!=null?f.val:0;
            int s1 = s!=null?s.val:0;
            int total = f1+s1+carray;
            int yu = total%10;
            carray = total/10;
            if(head == null || curr == null){
                head = new ListNode(yu);
                curr = head;
            }else {
                curr.next =  new ListNode(yu);
                curr = curr.next;
            }
            if(f!=null) f=f.next;
            if(s!=null) s=s.next;
        }
        if(carray>0){
            curr.next = new ListNode(carray);
        }
        return head;
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

    public static int climbStairs1(int n) {
        if(n <= 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        if(n==1) return 1;
        if(n==2) return 2;
        Integer res = map.get(n);
        if(res != null) {
            return res;
        }
        res = climbStairs1(n-1)+climbStairs1(n-2);
        map.put(n, res);
        return res;
    }

    public static int climbStairs2(int n) {
        if(n <= 0) return 0;
        if(n==1) return 1;
        if(n==2) return 2;
        int[] dp = new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public static int climbStairs3(int n) {
        if(n<=0) return 0;
        if(n==1) return 1;
        if(n==2) return 2;
        int n_2=1;
        int n_1=2;
        int n_0=0;
        for(int i=3;i<n;i++){
            n_0=n_2+n_1;
            n_2=n_1;
            n_1=n_0;
        }
        return  n_0;
    }
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        ListNode curr = head;
        ListNode next = curr.next;
        map.put(curr.val, 1);
        while (next != null){
            if(map.get(next.val)!= null){
                curr.next = next.next;
                next = next.next;
            }else {
                map.put(next.val, 1);
                curr = next;
                next = next.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode curr = head;
        ListNode next = curr.next;
        while (next != null){
            if(next.val == curr.val){
                curr.next = next.next;
                next = next.next;
            }else {
                curr = next;
                next = next.next;
            }
        }
        return head;
    }
    /*
        Input:
        nums1 = [1,2,3,0,0,0], m = 3
        nums2 = [2,5,6],       n = 3
        Output: [1,2,2,3,5,6]
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int s1 = nums1.length;
        int s2 = nums2.length;
        for(int i=0;i<s2;i++){
            int target = nums2[i];
            int index = helper(nums1, m, target);
            for(int j=s1-1;j>index;j--){
                nums1[j] = nums1[j-1];
            }
            nums1[index] = target;
            m++;
        }
    }

    public static int helper(int[] arry, int m, int target){
        if(m<=0){
            return 0;
        }
        if(target < arry[0]) return 0;
        if(target > arry[m -1]) return m;
        int low = 0;
        int high = m-1;
        int middle;
        while (low <= high){
            middle = low + (high - low)/2;
            if(arry[middle] == target){
                return middle + 1;
            }else if(arry[middle] > target){
                high = middle -1;
            }else {
                low = middle + 1;
            }
        }
        return low;
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int s1 = m-1;
        int s2 = n-1;
        int s3 = m+n-1;
        while (s1 >=0 && s2 >=0){
            if(nums1[s1] > nums2[s2]){
                nums1[s3] = nums1[s1];
                s1--;
            }else {
                nums1[s3] = nums2[s2];
                s2--;
            }
            s3--;
        }
        while (s2 >=0){
            nums1[s2] = nums2[s2];
            s2--;
        }
    }
    public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if((p != null && q == null) || (p == null && q != null)){
            return false;
        }
        if(p.val != q.val){
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            queue.add(t1.left);
            queue.add(t2.left);
            queue.add(t1.right);
            queue.add(t2.right);
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isSymmetri(root.left, root.right);
    }

    public boolean isSymmetri(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null){
            return false;
        }
        if(p.val != q.val){
            return false;
        }
        return isSymmetri(p.left, q.right) && isSymmetri(p.right, q.left);
    }

    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left  = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left+1, right+1);
    }

    public int maxDepth2(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue  = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        Queue<TreeNode> tmp;
        queue.add(root);
        int h = 0;
        boolean flag = false;
        while (!queue.isEmpty()){
            for(TreeNode node: queue){
                if(node != null){
                    flag = true;
                    break;
                }
            }
            if(flag){
                h++;
                for(TreeNode node: queue){
                    if(node != null){
                        queue2.add(node.left);
                        queue2.add(node.right);
                    }
                }
            }
            tmp = queue;
            queue = queue2;
            queue2 = tmp;
            queue2.clear();
        }
        return h-1;
    }

    public int maxDepth3(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);
        int h = 0;
        while (!queue.isEmpty()){
            boolean flag = false;
            int size = queue.size();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                if(node != null){
                    if(!flag){
                        flag = true;
                        h++;
                    }
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
        return h;
    }

    /*
    For example:
    Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    */
    // BFS
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null){
            return new LinkedList<>();
        }
        List<TreeNode> queue1  = new LinkedList<>();
        List<TreeNode> queue2 = new LinkedList<>();
        List<List<Integer>> queue3 = new LinkedList<>();
        List<TreeNode> tmp;
        queue1.add(root);
        while (!queue1.isEmpty()){
            List<Integer> queue4 = new LinkedList<>();
            for(TreeNode node: queue1){
                queue4.add(node.val);
            }
            for(TreeNode node: queue1){
                if(node.left !=null){
                    queue2.add(node.left);
                }
                if(node.right !=null){
                    queue2.add(node.right);
                }
            }
            queue3.add(0,queue4);
            tmp = queue1;
            queue1 = queue2;
            queue2 = tmp;
            queue2.clear();
        }
        return queue3;
    }

    // BFS
    public List<List<Integer>> levelOrderBottom2(TreeNode root) { // BFS
        if(root == null){
            return new LinkedList<>();
        }
        Queue<TreeNode> queue  = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> tem = new LinkedList<>();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                tem.add(node.val);
                if(node.left !=null){
                    queue.add(node.left);
                }
                if(node.right !=null){
                    queue.add(node.right);
                }
            }
            result.add(0, tem);
        }
        return result;
    }

    // DFS
    public List<List<Integer>> levelOrderBottom3(TreeNode root) {
        List<List<Integer>> wrapList = new LinkedList<>();
        levelMaker(wrapList, root, 0);
        return wrapList;
    }

    public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
        if(root == null) return;
        if(level >= list.size()) {
            list.add(0, new LinkedList<Integer>());
        }
        levelMaker(list, root.left, level+1);
        levelMaker(list, root.right, level+1);
        list.get(list.size()-level-1).add(root.val);
    }

    /*
    Input: [7,1,5,3,6,4]
    Output: 7
    Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
                 Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     */

    public static int maxProfit(int[] prices) {
        if(prices.length < 1) return 0;
        int max=0;
        int min=prices[0];
        for(int i=1;i<prices.length;i++){
            max=Math.max(prices[i]-min,max);
            min=Math.min(min,prices[i]);
        }
        return max;
    }

    public static int maxProfit1(int[] prices) {
        return calculate(prices, 0);
    }

    public static int calculate(int prices[], int s) {
        if (s >= prices.length)
            return 0;
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxprofit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > maxprofit)
                        maxprofit = profit;
                }
            }
            if (maxprofit > max)
                max = maxprofit;
        }
        return max;
    }

    public int maxProfit3(int[] prices) {
        int max = 0;
        int low;
        int high;
        int i=0;
        while (i<prices.length-1){
            while (i<prices.length-1 && prices[i] >= prices[i+1]){
                i++;
            }
            low = prices[i];
            while (i<prices.length-1 && prices[i] <= prices[i+1]){
                i++;
            }
            high = prices[i];
            max = max + high -low;
        }
        return max;
    }
    /*
    Example 1:

    Input: "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.

    Example 2:

    Input: "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.
     */

    public static int lengthOfLongestSubstring(String s) {
        char[] ar = s.toCharArray();
        int le = ar.length;
        HashSet<Character> set = new HashSet<>();
        int max = 0;
        for(int i=0;i<le;i++){
            int m = 0;
            int tem = 0;
            for(int j = i;j<le;){
                boolean ce = set.contains(ar[j]);
                if(!ce){
                    set.add(ar[j]);
                    tem++;
                    j++;
                }else {
                    m = Math.max(m,tem);
                    tem=0;
                    set.clear();
                }
            }
            set.clear();
            m   = Math.max(m,tem);
            max = Math.max(max, m);
        }
        return max;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        int length = s.length();
        HashSet<Character> set = new HashSet<>();
        while (left < length && right < length){
            if(!set.contains(s.charAt(right))){
                set.add(s.charAt(right++));
                max = Math.max(right - left, max);
            }else {
                set.remove(s.charAt(left++));
            }
        }
        return max;
    }

    public static int lengthOfLongestSubstring3(String s) {
        int max = 0;
        int length = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for(int left=0,right=0;right<length;right++){
            Integer index = map.get(s.charAt(right));
            left = Math.max(index==null?0:index, left);
            map.put(s.charAt(right), right+1);
            max = Math.max(max, right-left+1);
        }
        return max;
    }

    /*
    Given the sorted array: [-10,-3,0,5,9],

    One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

          0
         / \
       -3   9
       /   /
     -10  5
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0){
            return null;
        }
        return helper2(nums,0, nums.length);
    }

    public TreeNode helper2(int[] nums, int low, int high) {
        if(low>high){
            return null;
        }
        int middle = low + (high-low)/2;
        TreeNode node = new TreeNode(nums[middle]);
        node.left  = helper2(nums, low, middle-1);
        node.right = helper2(nums, middle+1, high);
        return node;
    }

    static boolean flag = true;
    public boolean isBalanced(TreeNode root) {
        maxDepth1(root);
        return flag;
    }

    public int maxDepth1(TreeNode root) {
        if(root == null || !flag) {
            return 0;
        }
        int left  = maxDepth1(root.left);
        int right = maxDepth1(root.right);
        if(left - right > 1 || right - left > 1){
            flag = false;
        }
        return Math.max(left, right)+1;
    }

    public int minDepth1(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null)  return (minDepth1(root.right)+1);
        if(root.right == null) return (minDepth1(root.left )+1);
        return Math.min(minDepth1(root.left), minDepth1(root.right))+1;
    }

    public int minDepth2(TreeNode root) {
        if(root == null) return 0;
        TreeNode curr = root;
        int min = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(curr);
        while (!queue.isEmpty()){
            min++;
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if(node.left == null && node.right == null){
                    return min;
                }
                if(node.left != null ){
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return min;
    }

    /*
     Given the below binary tree and sum = 22,
          5
         / \
        4   8
       /   / \
      11  13  4
     /  \      \
    7    2      1
    return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     */
    public boolean hasPathSum1(TreeNode root, int sum) {
        if(root == null) return false;
        if(isLeaf(root) && root.val == sum ) return true;
        return hasPathSum1(root.left, sum - root.val) || hasPathSum1(root.right, sum - root.val);
    }

    public boolean isLeaf(TreeNode node){
        if(node.left == null && node.right == null){
            return true;
        }
        return false;
    }

    public boolean hasPathSum2(TreeNode root, int sum) {
        if(root == null) return false;
        boolean flag = false;
        TreeNode curr = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(curr);
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                if(flag){
                    return true;
                }
                TreeNode node = queue.poll();
                if(node.left == null && node.right == null){
                    flag = (node.val == sum);
                }
                if(node.left != null){
                    node.left.val = node.val+node.left.val;
                    queue.add(node.left);
                }
                if(node.right != null) {
                    node.right.val = node.val+node.right.val;
                    queue.add(node.right);
                }
            }
        }
        return flag ;
    }

    public static String lastSubstring(String s) {
        return "";
    }

    public static int singleNumber(int[] nums) {
        int single = 0;
        for(int i=0;i<nums.length;i= i+1){
            single = nums[i]^single;
        }
        return single;
    }

    /*
    Example 1:

    Input: [2,2,1]
    Output: 1
     */
    public boolean hasCycle1(ListNode head) {
        ListNode currF = head;
        ListNode currS = head;
        while (currS != null && currS.next != null){
            currF = currF.next;
            currS = currS.next.next;
            if(currF == currS){
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }


    /*
     Example 1:

     Input: head = [3,2,0,-4], pos = 1
     Output: tail connects to node index 1
     Explanation: There is a cycle in the linked list, where tail connects to the second node.
      */
    public ListNode detectCycle(ListNode head) {
        ListNode first = head;
        ListNode second = head;
        boolean hasCycle =false;
        while (first != null && second != null&& second.next !=null){
            first = first.next;
            second = second.next.next;
            if(first == second){
                hasCycle= true;
                break;
            }
        }
        if(!hasCycle) return null;
        second = head;
        while (first!=second){
            first = first.next;
            second = second.next;
        }
        return first;
    }

    /*
    Example:

    Input:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    Output: 1->1->2->3->4->4->5->6
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        ListNode res = new ListNode(0);
        ListNode curr = res;
        ListNode a = min(lists);
        while (a != null){
            curr.next = a;
            curr = curr.next;
            a = min(lists);
        }
        return res.next;
    }

    public ListNode min(ListNode[] lists){
        ListNode min = null;
        int j = 0;
        for(int i=0;i<lists.length;i++){
            ListNode listNode = lists[i];
            if(min == null && listNode != null){
                j = i;
                min = listNode;
            }
            if(min != null && listNode != null){
                if(min.val > listNode.val){
                    j = i;
                    min = listNode;
                }
            }
        }
        if(min !=null) {
            lists[j] = min.next;
        }
        return min;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        ListNode res = new ListNode(0);
        ListNode curr = res;
        Queue<ListNode> queue = new PriorityQueue(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if(o1.val > o2.val){
                    return 1;
                }else if(o1.val < o2.val){
                    return -1;
                }
                return 0;
            }
        });
        for(int i=0;i<lists.length;i++){
            ListNode node = lists[i];
            if(node != null){
                queue.add(node);
            }
        }
        ListNode poll = queue.poll();
        while (poll != null){
            curr.next = poll;
            curr = curr.next;
            if(poll.next != null){
                queue.add(poll.next);
            }
            poll = queue.poll();
        }
        return res.next;
    }



    /*
    Example:

    Input: 5
    Output:
    [
         [1],
        [1,1],
       [1,2,1],
      [1,3,3,1],
     [1,4,6,4,1]
    ]
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows <= 0 ) return res;
        List<Integer> prev = new ArrayList<>();
        prev.add(1);
        res.add(prev);
        if(numRows == 1) return res;
        for(int i=2;i<=numRows;i++){
            List<Integer> curr = new ArrayList<>(i);
            curr.add(1);
            for(int j=0;j<prev.size()-1;j++){
                curr.add(prev.get(j)+prev.get(j+1));
            }
            curr.add(1);
            res.add(curr);
            prev = curr;
        }
        return res;
    }

    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        return helper(numRows, res);
    }

    public List<List<Integer>> helper(int numRows, List<List<Integer>> res) {
        if(numRows<=0) return res;
        if(numRows == 1){
            List<Integer> list = new ArrayList<>();
            list.add(1);
            res.add(list);
            return res;
        }
        List<List<Integer>> lastRes = helper(numRows-1, res);
        List<Integer> newList = new ArrayList<>();
        newList.add(1);
        List<Integer> lastList = lastRes.get(lastRes.size()-1);
        for(int i=0; i<lastList.size() - 1; i++){
            newList.add(lastList.get(i) + lastList.get(i+1));
        }
        newList.add(1);
        res.add(newList);
        return res;
    }

    public static boolean isPalindrome(String s) {
        if(s == null || s.length() == 0) return true;
        int left = 0;
        int right = s.length()-1;
        while (left <= right){
            char l = s.charAt(left);
            while (!isChar(l)){
                left++;
                if(left > right) break;
                l = s.charAt(left);
            }
            char r = s.charAt(right);
            while (!isChar(r) && left <= right){
                right--;
                if(left > right) break;
                r = s.charAt(right);
            }
            if(!isSame(l,r)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isSame(char l, char r){
        if(((l+"").toLowerCase()).equals((r+"").toLowerCase())) {
            return true;
        }
        return false;
    }

    public static boolean isChar(char r){
        if( (r>=65 && r<= 90) || (r>=97 && r<= 122) || (r>=48 && r<=57)) return true;
        return false;
    }
    /*
        Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
        Example 1:
        Input: a = 1, b = 2
        Output: 3
     */
    public static int getSum(int a, int b) {
        while (b>0){
            int c = a^b;
            b = (a&b)<<1;
            a = c;
        }
        return a;
    }

    static class MinStack1 {
        Stack<Integer> stack;
        int min = Integer.MAX_VALUE;
        public MinStack1() {
            stack = new Stack<>();
        }

        public void push(int x) {
            if(x <= min){
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            if(stack.pop() == min) {
                min = stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    class MinStack2 {
        private Node head;

        public void push(int x) {
            if(head == null)
                head = new Node(x, x);
            else
                head = new Node(x, Math.min(x, head.min), head);
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }

        private class Node {
            int val;
            int min;
            Node next;

            private Node(int val, int min) {
                this(val, min, null);
            }

            private Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode a = headA;
        ListNode b = headB;
        while (a != null){
            set.add(a);
            a = a.next;
        }
        while (b != null){
            if(set.contains(b)){
                return b;
            }
            b = b.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        int n1 = 0;
        int n2 = 0;
        while (a != null){
            n1++;
            a = a.next;
        }
        while (b != null){
            n2++;
            b = b.next;
        }
        int cha = Math.abs(n1 - n2);
        if(n1 > n2){
            while (cha>0){
                headA = headA.next;
                cha--;
            }
        }
        if(n2 > n1){
            while (cha>0){
                headB = headB.next;
                cha--;
            }
        }
        while (headA != null && headB != null){
            if(headA.hashCode() == headB.hashCode()){
                return headA;
            } else {
                headA = headA.next;
                headB = headB.next;
            }
        }
        return null;
    }

    /*
        Input: word1 = "horse", word2 = "ros"
        Output: 3
        Explanation:
        horse -> rorse (replace 'h' with 'r')
        rorse -> rose (remove 'r')
        rose -> ros (remove 'e')
     */

    public static int minDistance(String word1, String word2) {
        if(word1 == null && word2 == null){
            return 0;
        }
        if(word1 == null){
            return word2.length();
        }
        if(word2 == null){
            return word1.length();
        }
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<word1.length()+1;i++){
            dp[i][0] = i;
        }

        for(int i=0;i<word2.length()+1;i++){
            dp[0][i] = i;
        }

        for(int i=1;i<word1.length()+1;i++){
            for(int j=1;j<word2.length()+1;j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    int add = dp[i][j-1]+1;
                    int delete = dp[i-1][j]+1;
                    int replace = dp[i-1][j-1]+1;
                    int min = Math.min(Math.min(add, delete), replace);
                    dp[i][j] = min;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public void reverseString(char[] s) {
        char temp;
        for(int i=0;i<s.length/2;i++){
            temp = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = temp;
        }
    }

    /*
    Example:
    Input: 10
    Output: 4
    Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
     */
    public static int countPrimes1(int n) {
        if(n <= 1) return 0;
        int count = 0;
        for(int i=2;i<n;i++){
            boolean flag = false;
            for(int j=2;j<= Math.sqrt(i);j++){
                if(i%j==0){
                    flag = true;
                    break;
                }
            }
            if(!flag) count++;
        }
        return count;
    }

    public static int countPrimes2(int n) {
        if(n <= 1) return 0;
        int count = 0;
        boolean[] ar = new boolean[n+1];
        for(int i=2;i<n;i++){
            if(ar[i] == false){
                count++;
                for(int j = 2;i*j<n;j++){
                    ar[i*j] = true;
                }
            }
        }
        return count;
    }

    /*
        Input: [1,2,3,4,5,6,7] and k = 3
        Output: [5,6,7,1,2,3,4]
        Explanation:
        rotate 1 steps to the right: [7,1,2,3,4,5,6]
        rotate 2 steps to the right: [6,7,1,2,3,4,5]
        rotate 3 steps to the right: [5,6,7,1,2,3,4]
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void rotate2(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr !=null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /*
    Example:
    Input: [0,1,0,3,12]
    Output: [1,3,12,0,0]
     */
    public static void moveZeroes1(int[] nums) {
        int[] tem = new int[nums.length];
        int count = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                tem[count]=nums[i];
                count++;
            }
        }
        for(int i=0;i<tem.length;i++){
            if(tem[i]!=0){
                nums[i] = tem[i];
            }else {
                nums[i]=0;
            }
        }
    }

    public static void moveZeroes2(int[] nums) {
       for(int i=0,k=0;i<nums.length;i++){
           if(nums[i]!=0){
               nums[k]=nums[i];
               if(i!=k){
                   nums[i]=0;
               }
               k++;
           }
       }
    }

    /*
        Example 1:
        Input: [3,0,1]
        Output: 2
     */
    public int missingNumber1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }
        for(int i=0;i<nums.length+1;i++){
            if(!set.contains(i)){
                return i;
            }
        }
        return -1;
    }

    public int missingNumber2(int[] nums) {
        int miss = 0;
        for(int i=0;i<nums.length;i++){
            miss = miss^nums[i];
        }
        for(int i=0;i<nums.length+1;i++){
            miss = miss^i;
        }
        return miss;
    }

    /*
        Example:
        Given array nums = [-1, 0, 1, 2, -1, -4],
        A solution set is:
        [
          [-1, 0, 1],
          [-1, -1, 2]
        ]
     */
    public static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            for(int j=i;j<nums.length;j++){
                for(int k=j;k<nums.length;k++){
                    if(i != j && j != k){
                        if(nums[i]+nums[j]+nums[k]==0){
                            List<Integer> temp = new LinkedList<>();
                            temp.add(nums[i]);
                            temp.add(nums[j]);
                            temp.add(nums[k]);
                            boolean add = true;
                            for(List<Integer> list: res){
                                if(isSam(list, temp)){
                                    add = false;
                                    break;
                                }
                            }
                            if(add){
                                res.add(temp);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    public static boolean isSam(List<Integer> left, List<Integer> right){
        for(Integer a: left){
            if(!right.contains(a)){
                return false;
            }
        }
        for(Integer a: right){
            if(!left.contains(a)){
                return false;
            }
        }
        return true;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length - 2;i++){
            int fix = nums[i];
            if(fix>0) continue;
            if(i>0 && nums[i] == nums[i-1]) continue;
            int left = i+1;
            int right = nums.length-1;
            int target = 0 - fix;
            while (left < right){
                int sum = nums[left] + nums[right];
                if(sum == target){
                    List<Integer> temp = new LinkedList<>();
                    temp.add(fix);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    res.add(temp);
                    while (left<right && nums[left]==nums[left+1]){
                        left++;
                    }
                    while (left<right && nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if(sum > target){
                    right--;
                }else {
                    left++;
                }
            }
        }
        return res;
    }

    /*
        Example 1:
        Input: 00000010100101000001111010011100
        Output: 00111001011110000010100101000000
        Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
     */
    public int reverseBits(int n) {
        int result = 0;
        for(int i=0;i<32;i++){
            result = result << 1;
            result = result + (n&1);
            n = n >> 1;
        }
        return result;
    }

    /*
        Examples:
        s = "leetcode"
        return 0.

        s = "loveleetcode",
        return 2.
     */
    public int firstUniqChar1(String s) {
        Set<Character> set = new HashSet<>();
        char[] ar = s.toCharArray();
        for(int i=0;i<ar.length;i++){
            boolean flag = false;
            char target = ar[i];
            if(!set.contains(target)){
                for(int j=i+1;j<ar.length;j++){
                    if(set.contains(target) || target == ar[j]){
                        flag = true;
                        break;
                    }
                }
            }else {
                flag = true;
            }
            set.add(target);
            if(!flag) return i;
        }
        return -1;
    }

    public int firstUniqChar2(String s) {
        List<Character> list = new ArrayList<>(s.length());
        for(int i=0;i<s.length();i++) {
            list.add(i,s.charAt(i));
        }
        for(int i=0;i<s.length();i++) {
            list.remove(i);
            char target = s.charAt(i);
            if(!list.contains(target)){
                return i;
            }
            list.add(i,target);
        }
        return -1;
    }

    public int firstUniqChar3(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            Integer x = count.get(c);
            count.put(c, x==null?1:(x + 1));
        }
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    /*
        Example 1:
        Input: [1,2,3,1]
        Output: true
     */
    public boolean containsDuplicate1(int[] nums) {
        HashMap<Integer, Integer> list = new HashMap<>();
        for(Integer i: nums){
            if(list.containsKey(i)){
                return true;
            }else {
                list.put(i, 1);
            }
        }
        return false;
    }

    /*
        Example 2:
        Input: 1->2->2->1
        Output: true
     */

    public boolean isPalindrome(ListNode head) {
        int count = 0;
        ListNode curr = head;
        ListNode first = head;
        ListNode second = head;
        while (curr != null){
            count++;
            curr = curr.next;
        }
        int middle = 0;
        if(count%2==0) {
            middle=count/2;
        }else {
            middle = (count+1)/2;
        }
        int middle2 = middle;
        second = head;
        while (middle != 0){
            second = second.next;
            middle--;
        }
        second = helper4(second);
        ListNode curr_s = second;
        boolean flag = true;
        while (first != null && second != null){
            if(first.val != second.val){
                flag = false;
                break;
            }
            first = first.next;
            second = second.next;
        }
        second = helper4(curr_s);
        ListNode x = head;
        while (--middle2 != 0){
            x = x.next;
        }
        x.next = second;
        return flag;
    }

    public ListNode helper4(ListNode head){
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /*
        Example 1:
        Input: s = "anagram", t = "nagaram"
        Output: true
        Example 2:

        Input: s = "rat", t = "car"
        Output: false
     */

    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer x: nums){
            if(!map.containsKey(x)){
                map.put(x, 1);
            } else {
                int w = map.get(x);
                map.put(x, w+1);
            }
            if(map.get(x) > (nums.length/2)){
                return x;
            }
        }
        return -1;
    }

    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /*
    Example:
    Input:

         4
       /   \
      2     7
     / \   / \
    1   3 6   9
    Output:

         4
       /   \
      7     2
     / \   / \
    9   6 3   1
     */
    public TreeNode invertTree1(TreeNode root) {
        TreeNode curr = root;
        if(curr == null){
            return root;
        }
        TreeNode left  = curr.left;
        TreeNode right = curr.right;
        curr.left  = right;
        curr.right = left;
        invertTree1(curr.left);
        invertTree1(curr.right);
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        TreeNode curr;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        if(root == null) return root;
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                curr = queue.poll();
                TreeNode temp = curr.left;
                curr.left = curr.right;
                curr.right = temp;
                if(curr.left != null) queue.add(curr.left);
                if(curr.right != null) queue.add(curr.right);
            }
        }
        return root;
    }

    /*
        Input: [1,2,3,1]
        Output: 4
        Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
                     Total amount you can rob = 1 + 3 = 4.
     */
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        if(nums.length == 2) return dp[1];
        for(int i=2;i<nums.length;i++){
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }
        return dp[nums.length -1 ];
    }

    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        return pathSumHelper(root, sum) +  pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    public int pathSumHelper(TreeNode node, int sum) {
        if(node == null) return 0;
        return (node.val == sum ? 1:0) + pathSumHelper(node.left, sum - node.val) + pathSumHelper(node.right, sum - node.val);
    }

    public List<String> fizzBuzz(int n) {
        List<String> list = new LinkedList<>();
        for(int i=1;i<=n;i++){
            if(i%15==0){
                list.add("FizzBuzz");
            }else if(i%5==0){
                list.add("Buzz");
            }else if(i%3==0){
                list.add("Fizz");
            }else {
                list.add(i+"");
            }
        }
        return list;
    }

    /*
     判断是否是3的倍数
     */
    public static boolean isPowerOfThree(int n) {
        if(n==0) return false;
        while (n%3==0){
            n = n/3;
        }
        return n == 1;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new LinkedList();
        for(int i=0;i<nums1.length;i++){
            Integer value = map.get(nums1[i]);
            if(value == null){
                map.put(nums1[i], 1);
            }else {
                map.put(nums1[i], value+1);
            }
        }
        for(int i=0;i<nums2.length;i++){
            Integer value = map.get(nums2[i]);
            if(value != null && value > 0){
                list.add(nums2[i]);
                map.put(nums2[i], value-1);
            }
        }
        int[] array = new int[list.size()];
        int j=0;
        for(Integer integer: list){
            array[j++] = integer;
        }
        return array;
    }

    /*
    Example:

    Input: 19
    Output: true
    Explanation:
    12 + 92 = 82
    82 + 22 = 68
    62 + 82 = 100
    12 + 02 + 02 = 1
     */
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        int sum;
        while (true){
            sum = 0;
            while (n > 0){
                int yu = n % 10;
                sum = sum + yu * yu;
                n = n / 10;
            }
            if(sum == 1){
                return true;
            }
            if(set.contains(sum)){
                break;
            }
            set.add(sum);
            n = sum;
        }
        return false;
    }

    boolean isHappy2(int n) {
        int slow, fast;
        slow = fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            if (fast == 1) return true;
            fast = digitSquareSum(fast);
            if (fast == 1) return true;
        } while (slow != fast);
        return false;
    }

    int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n != 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }

    /*
        计算二进制1个数
     */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for(int i=0;i<32;i++){
            int flag  =  n & 1;
            n = n >> 1;
            count = count + flag;
        }
        return count;
    }

    /*
        Example 1:

        Input: 3
        Output: 0
        Explanation: 3! = 6, no trailing zero.
        Example 2:

        Input: 5
        Output: 1
        Explanation: 5! = 120, one trailing zero.
     */
    public int trailingZeroes(int n) {
        int count = 0;
        while (n != 0) {
            int tmp = n / 5;
            count += tmp;
            n = tmp;
        }
        return count;
    }

    public int titleToNumber(String s) {
        double sum = 0;
        double j = 0;
        double x = 26;
        for(int i= s.length()-1;i>=0;i--){
            sum = sum + (s.charAt(i)- 'A' + 1) * Math.pow(x, j);
            j++;
        }
        return (int)sum;
    }

    /*
    Example:

    Input:
    [4,3,2,7,8,2,3,1]

    Output:
    [5,6]
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int newIndex = Math.abs(nums[i]) - 1;

            if (nums[newIndex] > 0) {
                nums[newIndex] *= -1;
            }
        }
        List<Integer> result = new LinkedList<Integer>();
        for (int i = 1; i <= nums.length; i++) {

            if (nums[i - 1] > 0) {
                result.add(i);
            }
        }
        return result;
    }

    /*
    Example:
    Given a binary tree
          1
         / \
        2   3
       / \
      4   5
      Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
     */
    HashMap<TreeNode, Integer> hashMap1 = new HashMap<>();
    public int diameterOfBinaryTree1(TreeNode root) {
        if(root == null) return 0;
        TreeNode curr = root;
        HashMap<TreeNode, Integer> hashMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(curr);
        int max = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            TreeNode node;
            for(int i=0;i<size;i++){
                node = queue.poll();
                Integer dist = hashMap.get(node);
                if(dist == null){
                    dist = helper(node.left) + helper(node.right);
                    hashMap.put(node, dist);
                }
                max = Math.max(max, dist);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
        }
        return max;
    }

    public int diameterOfBinaryTree2(TreeNode root) {
        if(root == null) return 0;
        int left  = helper(root.left);
        int right = helper(root.right);
        int curr = left + right;
        return Math.max(curr, Math.max(diameterOfBinaryTree2(root.left), diameterOfBinaryTree2(root.right)));
    }

    int max = 0;
    public int diameterOfBinaryTree3(TreeNode root) {
        helper2(root);
        return max;
    }

    public int helper(TreeNode root) {
        if(root == null) return 0;
        Integer left  = hashMap1.get(root.left);
        Integer right = hashMap1.get(root.right);
        if(left == null){
            left = helper(root.left);
            hashMap1.put(root.left, left);
        }
        if(right == null){
            right = helper(root.right);
            hashMap1.put(root.right, right);
        }
        return 1 + Math.max(left, right);
    }

    public int helper2(TreeNode root) {
        if(root == null) return 0;
        Integer left  = hashMap1.get(root.left);
        Integer right = hashMap1.get(root.right);
        if(left == null){
            left = helper2(root.left);
            hashMap1.put(root.left, left);
        }
        if(right == null){
            right = helper2(root.right);
            hashMap1.put(root.right, right);
        }
        max = Math.max(max, left+right);
        return 1 + Math.max(left, right);
    }

    /*
    Example 1:

    Input:
        Tree 1                     Tree 2
              1                         2
             / \                       / \
            3   2                     1   3
           /                           \   \
          5                             4   7
    Output:
    Merged tree:
             3
            / \
           4   5
          / \   \
         5   4   7
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null) return t2;
        if(t2 == null) return t1;
        t1.val = t1.val+ t2.val;
        t1.left  = mergeTrees(t1.left, t2.left);
        t2.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    /*
    Example 1:
    Input: [2, 6, 4, 8, 10, 9, 15]
    Output: 5
    Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
    Note:
    Then length of the input array is in range [1, 10,000].
    The input array may contain duplicates, so ascending order here means <=.
     */
    public static int findUnsortedSubarray(int[] nums) {
        int length = nums.length;
        int[] copy = new int[length];
        for(int i=0;i<length;i++){
            copy[i]=nums[i];
        }
        Arrays.sort(nums);
        int i = 0;
        int j = length-1;
        int start = -1;
        int end  = -1;
        while (i<length&&j<length&&i<j){
            if(end !=-1 && start !=-1){
                return end - start+1;
            }
            if(nums[i] != copy[i] && start == -1){
                start=i;
            }
            if(nums[j] != copy[j] && end == -1){
                end=j;
            }
            if(start == -1){
                i++;
            }
            if(end == -1){
                j--;
            }
        }
        return 0;
    }

    public int findUnsortedSubarray2(int[] A) {
        int n = A.length, beg = -1, end = -2, min = A[n-1], max = A[0];
        for (int i=1;i<n;i++) {
            max = Math.max(max, A[i]);
            min = Math.min(min, A[n-1-i]);
            if (A[i] < max) end = i;
            if (A[n-1-i] > min) beg = n-1-i;
        }
        return end - beg + 1;
    }

    /*
    Example 1:

    Input: "babad"
    Output: "bab"
    Note: "aba" is also a valid answer.
    Example 2:

    Input: "cbbd"
    Output: "bb"
     */
    int start =0;
    int len = 0;
    public String longestPalindrome(String s) {
        if(s.length()<2) return s;
        for(int i=0;i<s.length()-1;i++){
            helper(s, i,i); //奇数
            helper(s, i,i+1);//偶数
        }
        return s.substring(start, len+start+1);
    }

    public void helper(String s, int left, int right){
        while (right<s.length()&&left>=0&&s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        right--;
        left++;
        if(right-left+1 > len){
            start = left;
            len = right-left;
        }
    }

    /*
    Example:

    Input: [1,null,2,3]
       1
        \
         2
        /
       3

    Output: [1,3,2]
     */
    List<Integer> list_result = new LinkedList<>();
    public List<Integer> inorderTraversal1(TreeNode root) {
        if(root == null){
            return list_result;
        }
        inorderTraversal1(root.left);
        list_result.add(root.val);
        inorderTraversal1(root.right);
        return list_result;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        TreeNode node = root;
        List<Integer> list = new LinkedList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null){
            if(node != null){
                stack.push(node);
                node = node.left;
            }else {
                TreeNode a = stack.pop();
                list.add(a.val);
                node = a.right;
            }
        }
        return list;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode curr = stack.pop();
            list.add(curr.val);
            if(curr.right != null){
                stack.push(curr.right);
            }
            if(curr.left != null){
                stack.push(curr.left);
            }
        }
        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        return list;
    }

    /*
        example:
        Input: [1,2,3]
        Output:
        [
          [1,2,3],
          [1,3,2],
          [2,1,3],
          [2,3,1],
          [3,1,2],
          [3,2,1]
        ]
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new LinkedList<>();
        helper(nums, 0,  lists);
        return lists;
    }

    public void helper(int[] a, int start,  List<List<Integer>> list){
        if(start == a.length-1){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int num: a){
                temp.add(num);
            }
            list.add(temp);
            return;
        }
        for(int i = start;i<a.length;i++){
            swap(a, start, i);
            helper(a,   start+1, list);
            swap(a, start, i);
        }
    }

    public void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackPermute2(list, new ArrayList<Integer>(), nums, 0);
        return list;
    }

    private void backtrackPermute2(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        }else {
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrackPermute2(list, tempList, nums, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if(nums==null) return res;
        List<Integer> list = new LinkedList<>();
        list.add(nums[0]);
        res.add(list);
        for(int i = 1;i<nums.length;i++){
            List<List<Integer>> res_new = new LinkedList<>();
            for(List<Integer> a: res){
                List<Integer> w1 = new LinkedList<>(a);
                w1.add(0, nums[i]);
                res_new.add(new LinkedList<>(w1));
                for(int w=1;w<a.size();w++){
                    List<Integer> w3 = new LinkedList<>(a);
                    w3.add(w, nums[i]);
                    res_new.add(w3);
                }
                List<Integer> w2 = new LinkedList<>(a);
                w2.add(nums[i]);
                res_new.add(new LinkedList<>(w2));
            }
            res = res_new;
        }
        return res;
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new LinkedList<>();
        helper(list, "", 0, 0, n);
        return list;
    }

    public void helper(List<String> list, String str, int open, int close, int n){
        if(str.length() == 2*n){
            list.add(str);
            return;
        }
        if(open < n){
            helper(list, str+"(", open+1, close, n);
        }
        if(close < open){
            helper(list, str+")", open, close+1, n);
        }
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map1 = new HashMap();
        for(int i=0;i<nums.length;i++){
            Integer va = map1.get(nums[i]);
            if(va!=null){
                map1.put(nums[i], va+1);
            }else {
                map1.put(nums[i], 1);
            }
        }
        List<Integer> list = new LinkedList<>();
        for(int i=0;i<k;i++){
            int[] max = new int[2];
            for(Integer j: map1.keySet()){
                if(map1.get(j) > max[0]){
                    max[0] = map1.get(j);
                    max[1] = j;
                }
            }
            list.add(max[1]);
            map1.remove(max[1]);
        }
        return list;
    }

    public int[] productExceptSelf(int[] nums) {
        int[] array = new int[nums.length];
        array[0]=1;
        for(int i=1;i<array.length;i++){
            array[i]=nums[i-1]*array[i-1];
        }
        int temp =1;
        for(int i=array.length-1;i>=0;i--){
            array[i]=array[i]*temp;
            temp = temp*nums[i];
        }
        return array;
    }
    /*
        Input: nums = [1,2,3]
    Output:
    [
      [3],
      [1],
      [2],
      [1,2,3],
      [1,3],
      [2,3],
      [1,2],
      []
    ]
     */

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<Integer>(), nums, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    /*
    Example 1:

    Input: root = [3,1,4,null,2], k = 1
       3
      / \
     1   4
      \
       2
    Output: 1
    Example 2:

    Input: root = [5,3,6,2,4,null,null,1], k = 3
           5
          / \
         3   6
        / \
       2   4
      /
     1
    Output: 3
     */
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return 0;
        ArrayList<Integer> nums =  new ArrayList<>();
        TreeNode curr = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(curr);
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i =0;i<size;i++){
                curr = queue.poll();
                nums.add(curr.val);
                if(curr.left != null){
                    queue.add(curr.left);
                }
                if(curr.right != null){
                    queue.add(curr.right);
                }
            }
        }
        Object[] a = nums.toArray();
        Arrays.sort(a);
        return (Integer)a[k-1];
    }

    public int kthSmallest2(TreeNode root, int k) {
        if(root == null) return 0;
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> nums =  new ArrayList<>();
        while (curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else {
                curr = stack.pop();
                nums.add(curr.val);
                curr = curr.right;
            }
        }
        return nums.get(k-1);
    }

    public int kthSmallest3(TreeNode root, int k) {
        ArrayList<Integer> nums =  new ArrayList<>();
        helper3(root, nums);
        return nums.get(k-1);
    }

    private void helper3(TreeNode root, List<Integer> list){
        if(root == null) return;
        helper3(root.left, list);
        list.add(root.val);
        helper3(root.right, list);
    }

    /*
        Example:

    Input:
    A = [ 1, 2]
    B = [-2,-1]
    C = [-1, 2]
    D = [ 0, 2]

    Output:
    2

    Explanation:
    The two tuples are:
    1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
    2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer a: A){
            for(Integer b: B){
                Integer w = map.get(a+b);
                map.put(a+b, w==null?1:w+1);
            }
        }
        int j=0;
        for(Integer c: C){
            for(Integer d: D){
                Integer w = map.get(0-(c+d));
                if(w !=null){
                    j = j+w;
                }
            }
        }
        return j;
    }


    /*
        Example 1:

        Input: [1,3,4,2,2]
        Output: 2
        Example 2:

        Input: [3,1,3,4,2]
        Output: 3
     */
    public static int findDuplicate(int[] nums) {
        int left = 1, right = nums.length-1;
        while (left <= right){
            int mid = left + (right-left)/2;
            int num=0;
            for(Integer a: nums){
                if(a<=mid)num++;
            }
            if(num<=mid){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return left;
    }

    /*
    Example 1:

    Input: 2
    Output: [0,1,1]
    Example 2:

    Input: 5
    Output: [0,1,1,2,1,2]
     */
    public int[] countBits(int num) {
        int[] f = new int[num + 1];
        for (int i=1; i<=num; i++) f[i] = f[i >> 1] + (i%2);
        return f;
    }
    /*
        Note:
        The number of people is less than 1,100.


        Example

        Input:
        [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

        Output:
        [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (p1, p2) -> (p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]));  //按身高降序排序，身高相同按第二个数据升序
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] p : people) list.add(p[1], p);
        return list.toArray(new int[0][0]);
    }

    /*
    Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

    For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

    Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
     */

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Arrays.fill(res, 0);
        for(int i=0;i<T.length;i++){
            for(int j=i+1;j<T.length;j++){
                if(T[j]>T[i]){
                    res[i]=j-i;
                    break;
                }
            }
        }
        return res;
    }

    public int[] dailyTemperatures2(int[] T) {
        int[] res = new int[T.length];
        Arrays.fill(res, 0);
        Stack<Integer> stack = new Stack<>();
        for(int i=T.length-1;i>=0;i--){
            while (!stack.isEmpty()&&T[stack.peek()]<=T[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                res[i]=stack.peek()-i;
            }
            stack.push(i);
        }
        return res;
    }

    public int[] dailyTemperatures3(int[] T) {  //利用next数组下标充当温度，T索引为数据来进行计算
        int[] ans = new int[T.length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = T.length - 1; i >= 0; --i) {
            int warmer_index = Integer.MAX_VALUE;
            for (int t = T[i] + 1; t <= 100; ++t) {
                if (next[t] < warmer_index)
                    warmer_index = next[t];
            }
            if (warmer_index < Integer.MAX_VALUE)
                ans[i] = warmer_index - i;
            next[T[i]] = i;
        }
        return ans;
    }

    /**
     Example:
     Input: [1,8,6,2,5,4,8,3,7]
     Output: 49
     */
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length-1;
        int max = 0;
        while (i != j){
            if(height[i]>height[j]){
                max = Math.max(max, (j-i)*height[j]);
                j--;
            }else {
                max = Math.max(max, (j-i)*height[i]);
                i++;
            }
        }
        return max;
    }

    /*
    Example:
    Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
    Output:
    [
      ["ate","eat","tea"],
      ["nat","tan"],
      ["bat"]
    ]*/

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> re = new HashMap<>();
        for(String string: strs){
            char[] a = string.toCharArray();
            Arrays.sort(a);
            String key = String.valueOf(a);
            if(re.containsKey(key)){
                re.get(key).add(string);
            }else {
                List<String> list = new ArrayList<>();
                list.add(string);
                re.put(key, list);
            }
        }
        return new ArrayList<>(re.values());
    }

    /*
    Example 1:

Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
     */
    //顺时旋转90度：先转置，后列交换
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i=0;i<m;i++){
            for(int j=i;j<n;j++){
                int tmp = matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=tmp;
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n/2;j++){
                int tmp = matrix[i][j];
                matrix[i][j]=matrix[i][n-1-j];
                matrix[i][n-1-j]=tmp;
            }
        }
    }
    //逆时旋转90度：先转置，后行交换
    public void rotate2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i=0;i<m;i++){
            for(int j=i;j<n;j++){
                int tmp = matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=tmp;
            }
        }
        for(int i=0;i<m/2;i++){
            for(int j=0;j<n;j++){
                int tmp = matrix[i][j];
                matrix[i][j]=matrix[m-1-i][j];
                matrix[m-1-i][j]=tmp;
            }
        }
    }

    /*
    Example 1:

    Input: 1->2->3->4->5->NULL
    Output: 1->3->5->2->4->NULL
    Example 2:

    Input: 2->1->3->5->6->4->7->NULL
    Output: 2->3->6->7->1->5->4->NULL
    两条链表最后连接在一起
     */
    public static ListNode oddEvenList(ListNode head) {
        if(head == null) return null;
        ListNode odd_head = null;
        ListNode odd_curr = null;
        ListNode even_head = null;
        ListNode even_curr = null;
        ListNode curr = head;
        int i=1;
        while (curr != null){
            if(i%2!=0){
                if(odd_head == null){
                    odd_head = curr;
                    odd_curr = curr;
                }else {
                    odd_curr.next = curr;
                    odd_curr = curr;
                }
            }else {
                if(even_head == null){
                    even_head = curr;
                    even_curr = curr;
                }else {
                    even_curr.next = curr;
                    even_curr = curr;
                }
            }
            curr = curr.next;
            i++;
        }
        if(even_curr != null){
            even_curr.next=null;
        }
        odd_curr.next = even_head;
        return odd_head;
    }

    public static ListNode oddEvenList2(ListNode head) {
        if(head == null) return null;
        ListNode even_head = null;
        ListNode even_curr = null;
        ListNode curr = head;
        ListNode prev = null;
        ListNode prev_odd = null;
        int i=1;
        while (curr != null){
            if(i%2 ==0){
                if(even_head == null){
                    even_head = curr;
                    even_curr = curr;
                }else {
                    even_curr.next = curr;
                    even_curr = curr;
                }
                prev.next = curr.next;
            }else {
                prev_odd = curr;
            }
            prev = curr;
            curr = curr.next;
            i++;
        }
        if(even_head!=null){
            even_curr.next=null;
            prev_odd.next = even_head;
        }
        return head;
    }

    //选择排序，排序前k个元素
    public int findKthLargest(int[] nums, int k) {
        for(int i=0;i<k;i++){
            int max = i;
            for(int j=i+1;j<nums.length;j++){
                if(nums[max]<nums[j]){
                    max = j;
                }
            }
            if(max!=i){
                int tmp = nums[max];
                nums[max] = nums[i];
                nums[i]=tmp;
            }
        }
        return nums[k-1];
    }

    //快速排序法
    public int findKthLargest2(int[] nums, int k) {
        kuaiSort(nums, 0, nums.length-1, k);
        return nums[k-1];
    }
    public void kuaiSort(int[] array,int low, int high, int k){
        if(low>=high) return;
        int aow = kuaiSortHelper(array, low, high);
        kuaiSort(array, low, aow, k);
        kuaiSort(array, aow+1, high, k);
    }
    public int kuaiSortHelper(int[] array,int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&array[high]<=aow) high--;
            array[low]=array[high];
            while (low<high&&array[low]>=aow) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }

    //快速选择法
    public int findKthLargest3(int[] nums, int k) {
        return kuaiSort3(nums, 0, nums.length-1, k);
    }
    public int kuaiSort3(int[] array,int low, int high, int k){
        int aow = kuaiSortHelper3(array, low, high);
        if(aow==k-1){
            return array[aow];
        }else if(aow>k-1){
            return kuaiSort3(array, low, aow, k);
        }else {
            return kuaiSort3(array, aow+1, high, k);
        }
    }
    public int kuaiSortHelper3(int[] array,int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&array[high]<=aow) high--;
            array[low]=array[high];
            while (low<high&&array[low]>=aow) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }


    /*
    For example:
    Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root==null) return res;
        Queue<TreeNode> queue1 = new LinkedBlockingQueue<>();
        queue1.offer(root);
        Queue<TreeNode> queue2 = new LinkedBlockingQueue<>();
        while (!queue1.isEmpty()||!queue2.isEmpty()){
            List<Integer> a =null;
            List<Integer> b =null;
            if(!queue1.isEmpty()){
                a = new LinkedList<>();
                while (!queue1.isEmpty()){
                    TreeNode node = queue1.poll();
                    a.add(node.val);
                    if(node.left!=null){
                        queue2.offer(node.left);
                    }
                    if(node.right!=null){
                        queue2.offer(node.right);
                    }
                }
            }
            if(a!=null) res.add(a);
            if(!queue2.isEmpty()){
                b = new LinkedList<>();
                while (!queue2.isEmpty()){
                    TreeNode node = queue2.poll();
                    b.add(node.val);
                    if(node.left!=null){
                        queue1.offer(node.left);
                    }
                    if(node.right!=null){
                        queue1.offer(node.right);
                    }
                }
            }
            if(b!=null) res.add(b);
        }
        return res;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root==null) return res;
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> sub = new LinkedList<>();
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                sub.add(node.val);
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            res.add(sub);
        }
        return res;
    }


    /*
    Example:
    matrix = [
       [ 1,  5,  9],
       [10, 11, 13],
       [12, 13, 15]
    ],
    k = 8,

    return 13.
     */
    class Node{
        int row;
        int col;
        int value;
        Node(int row, int col, int value){
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.value - o2.value;
            }
        });
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i=0;i<n;i++){
            queue.add(new Node(0, i, matrix[0][i]));
        }
        Node node = null;
        for(int i=0;i<k;i++){
            node = queue.poll();
            int row = node.row;
            int col = node.col;
            if(row+1<m){
                queue.add(new Node(row+1, col, matrix[row+1][col]));
            }
        }
        return node !=null?node.value:0;
    }

    /*
    Example 1:

    Input: m = 3, n = 2
    Output: 3
    Explanation:
    From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
    1. Right -> Right -> Down
    2. Right -> Down -> Right
    3. Down -> Right -> Right
    Example 2:

    Input: m = 7, n = 3
    Output: 28
     */
    public int uniquePaths1(int m, int n) {
        int[][] dp = new int[n][m];
        for(int i=0;i<n;i++){
            dp[i][0] = 1;
        }
        for(int j=0;j<m;j++){
            dp[0][j] = 1;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        return dp[n-1][m-1];
    }

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0 || j==0){
                    dp[i][j]=1;
                }else {
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }
        return dp[n-1][m-1];
    }

    //山间蓄水问题
    public int trap(int[] height) {
        int res = 0;
        for(int i=0;i<height.length;i++){
            int left = 0;
            int right = 0;
            for(int j=0;j<i;j++){
                if(left<height[j]){
                    left=height[j];
                }
            }
            for(int j=i+1;j<height.length;j++){
                if(right<height[j]){
                    right=height[j];
                }
            }
            res = res + (Math.min(left, right) > height[i]?(Math.min(left, right) - height[i]):0);
        }
        return res;
    }

    public int trap2(int[] height) {
        if(height==null||height.length==0) return 0;
        int res = 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        left[0]=height[0];
        right[height.length-1]=height[height.length-1];
        for(int i=1;i<height.length;i++){
            left[i] = Math.max(left[i-1], height[i]);
        }
        for(int i=height.length-2;i>=0;i--){
            right[i] = Math.max(right[i+1], height[i]);
        }
        for(int i=0;i<height.length;i++){
            res = res + Math.min(left[i], right[i])-height[i];
        }
        return res;
    }

    public int trap3(int[] height) {
        if(height==null||height.length==0) return 0;
        int res = 0;
        int left=0;
        int right=0;
        int i=0;
        int j=height.length-1;
        while (i<j){
            if(height[i]<=height[j]){
                left = Math.max(left, height[i]);
                res = res+ left-height[i];
                i++;
            }else {
                right = Math.max(right, height[i]);
                res = res+ right-height[j];
                j--;
            }
        }
        return res;
    }

    /*
    Example:

    Input:

       1
     /   \
    2     3
     \
      5

    Output: ["1->2->5", "1->3"]

    Explanation: All root-to-leaf paths are: 1->2->5, 1->3
     */
    //dfs
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        if(root == null) return res;
        Stack<TreeNode> stackT = new Stack<>();
        Stack<String> stackS = new Stack<>();
        stackS.add("");
        stackT.add(root);
        while (!stackT.isEmpty()){
            String   a  = stackS.pop();
            TreeNode b  = stackT.pop();
            if(b.left == null && b.right==null){
                res.add(a + b.val);
            }
            if(b.right != null){
                stackT.add(b.right);
                stackS.add(a +  b.val + "->");
            }
            if(b.left != null){
                stackT.add(b.left);
                stackS.add(a +  b.val + "->");
            }
        }
        return res;
    }

    //bfs
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> list = new ArrayList<String>();
        Queue<TreeNode> qNode = new LinkedList<TreeNode>();
        Queue<String> qStr = new LinkedList<String>();
        if (root == null) return list;
        qNode.add(root);
        qStr.add("");
        while (!qNode.isEmpty()) {
            TreeNode curNode = qNode.remove();
            String curStr = qStr.remove();

            if (curNode.left == null && curNode.right == null) list.add(curStr + curNode.val);
            if (curNode.left != null) {
                qNode.add(curNode.left);
                qStr.add(curStr + curNode.val + "->");
            }
            if (curNode.right != null) {
                qNode.add(curNode.right);
                qStr.add(curStr + curNode.val + "->");
            }
        }
        return list;
    }

    public List<List<Integer>> pathSumII(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null) return res;
        Stack<TreeNode> TreeNodeStack = new Stack<>();
        Stack<List<Integer>> listStack = new Stack<>();
        TreeNodeStack.push(root);
        listStack.push(new LinkedList<>());
        while (!TreeNodeStack.isEmpty()){
            TreeNode node = TreeNodeStack.pop();
            List<Integer> list = listStack.pop();
            if(node.left == null&&node.right==null){
                list.add(node.val);
                if(total(list)==sum){
                    res.add(list);
                }
            }
            if(node.right!=null){
                List<Integer> a= new LinkedList<>();
                a.addAll(list);
                a.add(node.val);
                listStack.push(a);
                TreeNodeStack.push(node.right);
            }
            if(node.left!=null){
                List<Integer> a= new LinkedList<>();
                a.addAll(list);
                a.add(node.val);
                TreeNodeStack.push(node.left);
                listStack.push(a);
            }
        }
        return res;
    }

    public int total( List<Integer> list){
        int sum = 0;
        for(Integer a: list){
            sum = sum+a;
        }
        return sum;
    }

    public List<List<Integer>> pathSumII2(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null) return res;
        pathSumII2Helper(root, sum, new LinkedList<>(), res);
        return res;
    }

    public void pathSumII2Helper(TreeNode curr, int sum, List<Integer> tmp, List<List<Integer>> res){
        if(curr==null) return;
        tmp.add(curr.val);
        if(curr.left==null&&curr.right==null&&curr.val==sum){
            res.add(new LinkedList<>(tmp));
        }else {
            if(curr.left!=null) pathSumII2Helper(curr.left, sum-curr.val, tmp, res);
            if(curr.right!=null) pathSumII2Helper(curr.right, sum-curr.val, tmp, res);
        }
        tmp.remove(tmp.size()-1);
    }


    public static List<String> getPermutation0(String A){
        if(A == null || A.length() ==0) return null;
        return getPermutation0Helper(A, A.length()-1);
    }

    public static List<String> getPermutation0Helper(String A, int n){
        if(n ==0){
            List<String> a = new LinkedList<>();
            a.add(A.charAt(0)+"");
            return a;
        }
        List<String> pre = getPermutation0Helper(A, n-1);
        List<String> list_new = new LinkedList<>();
        for(String s: pre){
            String before = A.charAt(n)+s;
            list_new.add(before);
            for(int i=1;i<s.toCharArray().length;i++){
                list_new.add(s.substring(0,i)+A.charAt(n)+s.substring(i));
            }
            String after = s+A.charAt(n);
            list_new.add(after);
        }
        return list_new;
    }


    public static List<String> getPermutation1(String A){
        if(A == null || A.length() ==0) return null;
        if(A.length()==1){
            List<String> a = new LinkedList<>();
            a.add(A);
            return a;
        }
        List<String> res = new LinkedList<>();
        res.add(A.charAt(0)+"");
        for(int i=1;i<A.length();i++){
            List<String> res_new = new LinkedList<>();
            for(String s: res){
                String before = A.charAt(i)+s;
                res_new.add(before);
                for(int j=1;j<s.toCharArray().length;j++){
                    res_new.add(s.substring(0,j)+A.charAt(i)+s.substring(j));
                }
                String after = s+A.charAt(i);
                res_new.add(after);
            }
            res = res_new;
        }
//        Collections.sort(res);
        return res;
    }

    static  List<String> res = new LinkedList<>();
    public static List<String> getPermutation3(String A) {
        return getPermutation3Helper(A.toCharArray(), 0);
    }
    public static List<String> getPermutation3Helper(char[] a, int k) {
        if(k==a.length-1){
            res.add(new String(a));
        }
        for(int i=k;i<a.length;i++){
            swap(a, i, k);
            getPermutation3Helper(a, k+1);
            swap(a, i, k);
        }
        return res;
    }
    public static void swap(char[] a, int i, int k){
        char w = a[i];
        a[i]=a[k];
        a[k]=w;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null) return null;
        int[] res = new int[nums.length-k+1];
        int i = 0;
        int j = k-1;
        while (i<=j&&j<nums.length){
            int max = nums[i];
            for(int w = i;w<=j;w++){
                if(max<nums[w]){
                    max = nums[w];
                }
            }
            res[i] = max;
            i++;
            j++;
        }
        return res;
    }


    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] res = new int[nums.length-k+1];
        Deque <Integer> deque = new ArrayDeque<>();
        int j=0;
        for(int i=0;i<nums.length;i++){
            while (!deque.isEmpty()&&deque.peek()<i-k+1){  //去除过期的下标
                deque.poll();
            }
            while (!deque.isEmpty()&&nums[deque.peekLast()]<nums[i]){ //如果下一个数比前一个数大，则删除前一个无用的下标数据
                deque.pollLast();
            }
            deque.offer(i);
            if(i>=k-1){ //满足滑动窗口长度即可开始赋值
                res[j++] = nums[deque.peek()];
            }
        }
        return res;
    }

    /*
    Sort a linked list in O(n log n) time using constant space complexity.

    Example 1:

    Input: 4->2->1->3
    Output: 1->2->3->4
     */

    public ListNode sortList(ListNode head) {
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        ListNode curr = head;
        while (curr!=null){
            queue.add(curr.val);
            curr = curr.next;
        }
        curr = head;
        while (!queue.isEmpty()){
            curr.val = queue.poll();
            curr = curr.next;
        }
        return head;
    }

    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // step 1. cut the list to two halves
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        // step 2. sort each half
        ListNode l1 = sortList2(head);
        ListNode l2 = sortList2(slow);
        // step 3. merge l1 and l2
        return merge(l1, l2);
    }

    ListNode merge(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(0), p = l;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = l1!=null?l1:l2;
        return l.next;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> recombinationSums_res = new LinkedList<>();
        combinationSumHelper(candidates, target, 0, new LinkedList<>(), recombinationSums_res);
        return recombinationSums_res;
    }

    public void combinationSumHelper(int[] candidates, int target, int index, List<Integer> res, List<List<Integer>> recombinationSums_res) {
        if(target == 0){
            recombinationSums_res.add(new LinkedList<>(res));
        }
        if(target>0){
            for(int i=index;i<candidates.length;i++){
                res.add(candidates[i]);
                combinationSumHelper(candidates, target-candidates[i], i, res, recombinationSums_res);
                res.remove(res.size()-1);
            }
        }
    }

    /*
        Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

    Example:

    Input: 3
    Output: 5
    Explanation:
    Given n = 3, there are a total of 5 unique BST's:

       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                dp[i] = dp[i] + dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }

    /*
    Example 1:

    Input: "abc"
    Output: 3
    Explanation: Three palindromic strings: "a", "b", "c".
     */

    public static int countSubstrings(String s) {
        int res = 0;
        for(int i=0;i<s.length();i++){
            for(int j = i;j<s.length();j++){
                if(checkcountSubstrings(s, i, j)){
                    res++;
                }
            }
        }
        return res;
    }
    public static boolean checkcountSubstrings(String a, int low, int high){
        while (low<=high){
            if(a.toCharArray()[low] != a.toCharArray()[high]){
                return false;
            }
            low++;
            high--;
        }
        return true;
    }


    public static int k = 0;
    public static int countSubstrings2(String s) {
        for(int i=0;i<s.length();i++){
            checkcountSubstrings2(s, i, i);
            checkcountSubstrings2(s, i, i+1);
        }
        return k;
    }

    public static  void checkcountSubstrings2(String a, int low, int high){
        while (low>=0&&high<=a.length()-1&&a.charAt(low)==a.charAt(high)){
            k++;
            low--;
            high++;
        }
    }


    /*
    Example 1:

    Input: [3,2,3,null,3,null,1]

         3
        / \
       2   3
        \   \
         3   1

    Output: 7
    Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
     */

    public int rob(TreeNode root) {
        if(root==null) return 0;
        int val = root.val;
        if(root.left!=null){
            val=val+rob(root.left.left)+rob(root.left.right);
        }
        if(root.right!=null){
            val=val+rob(root.right.left)+rob(root.right.right);
        }
        val = Math.max(val, rob(root.left)+rob(root.right));
        return Math.max(val, rob(root.left)+rob(root.right));
    }

    HashMap<TreeNode, Integer> rob2Map = new HashMap<>();
    public int rob2(TreeNode root) {
        if(root==null) return 0;
        if(rob2Map.get(root) != null){
            return rob2Map.get(root);
        }
        int val = root.val;
        if(root.left!=null){
            val=val+rob2(root.left.left)+rob2(root.left.right);
        }
        if(root.right!=null){
            val=val+rob2(root.right.left)+rob2(root.right.right);
        }
        val = Math.max(val, rob2(root.left)+rob2(root.right));
        rob2Map.put(root, val);
        return Math.max(val, rob(root.left)+rob(root.right));
    }

    public int rob3(TreeNode root) {
        if(root==null) return 0;
        int[] res = rob3Helper(root);
        return Math.max(res[0], res[1]);
    }
    public int[] rob3Helper(TreeNode root) {
        if(root==null) return new int[]{0,0};
        int[] left = rob3Helper(root.left);
        int[] right = rob3Helper(root.right);
        int[] res = new int[2];  //0不抢劫 1抢劫
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = left[0] + right[0] + root.val;
        return res;
    }

    /*
    Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

    Note: You can only move either down or right at any point in time.

    Example:

    Input:
    [
      [1,3,1],
      [1,5,1],
      [4,2,1]
    ]
    Output: 7
    Explanation: Because the path 1→3→1→1→1 minimizes the sum.
     */
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[0].length;j++){
                if(i==0&&j==0){
                    dp[0][0] = grid[0][0];
                } else if(i==0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }else if(j==0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }
    /*
    Example 1:

    Input: s = "3[a]2[bc]"
    Output: "aaabcbc"
    Example 2:

    Input: s = "3[a2[c]]"
    Output: "accaccacc"
     */

    public static String decodeString(String s) {
        char[] c = s.toCharArray();
        String res = new String();
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<c.length;i++){
            char a = c[i];
            if(a != ']'){
                stack.push(a);
            }else{
                String str = new String();
                String num = new String();
                while (!stack.isEmpty()){
                    char m = stack.peek();
                    if(m != '[' && ((m >= 'a' && m<= 'z') || (m >= 'A' && m<= 'Z'))){
                        m = stack.pop();
                        str = m + str;
                    }else if(m != '[' && m >= '0' && m<= '9'){
                        while (!stack.isEmpty()){
                            m = stack.pop();
                            if(m >= '0' && m<= '9'){
                                num = m + num;
                            }else {
                                stack.push(m);
                                break;
                            }
                        }
                        String tmp2 = new String();
                        for(int j=0;j<Integer.parseInt(num);j++){
                            tmp2 = tmp2 + str;
                        }
                        char[] x = tmp2.toCharArray();
                        for(char x1: x){
                            stack.push(x1);
                        }
                        break;
                    }else {
                        stack.pop();
                    }
                }
            }
        }
        while (!stack.isEmpty()){
            res = stack.pop() + res;
        }
        return res.toString();
    }

    public static String decodeString2(String s) {
        Stack<String> resStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        String res = new String();
        int idx = 0;
        while (idx<s.length()){
            char c = s.charAt(idx);
            if(Character.isDigit(c)){
                int num = 0;
                while (Character.isDigit(s.charAt(idx))){
                    num = num*10 + (c-'0');
                    idx++;
                }
                numStack.push(num);
            }else if(c == '['){
                resStack.push(res);
                res = "";
                idx++;
            }else if(c == ']'){
                StringBuilder tmp  = new StringBuilder(resStack.pop());
                int count = numStack.pop();
                for(int i=0;i<count;i++){
                   tmp.append(res);
                }
                res = tmp.toString();
                idx++;
            }else {
                res+= c;
                idx++;
            }
        }
        return res;
    }


    /*
    Example:

    Input: 1->2->4, 1->3->4
    Output: 1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while (l1 !=null&&l2 !=null){
            if(l1.val<l2.val){
                curr.next = l1;
                l1 = l1.next;
            }else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1==null?l2:l1;
        return head.next;
    }

    /*
    Given a binary tree, flatten it to a linked list in-place.
    For example, given the following tree:

        1
       / \
      2   5
     / \   \
    3   4   6
    The flattened tree should look like:

    1
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6
     */

    public void flatten(TreeNode root) {
        if(root == null || (root.left == null && root.right==null)) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        stack.push(curr);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node.right!=null) stack.push(node.right);
            if(node.left!=null) stack.push(node.left);
            node.left = null;
            curr.right = node;
            curr = node;
        }
    }

    /*
    Given preorder and inorder traversal of a tree, construct the binary tree.

    Note:
    You may assume that duplicates do not exist in the tree.

    For example, given

    preorder = [3,9,20,15,7]
    inorder = [9,3,15,20,7]
    Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
     */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    public TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if(preStart>preEnd || inStart>inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIdx = 0;
        for(int i=inStart;i<=inEnd;i++){
            if(root.val == inorder[i]){
                rootIdx = i;
                break;
            }
        }
        int leftNum = rootIdx-inStart;
        root.left = buildTreeHelper(preorder, preStart+1, preStart+leftNum, inorder, inStart, rootIdx-1);
        root.right = buildTreeHelper(preorder, preStart+leftNum+1, preEnd, inorder, rootIdx+1, inEnd);
        return root;
    }

    Map<Integer, Integer> buildMap = new HashMap<>();
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        for(int i=0;i<inorder.length;i++){
            buildMap.put(inorder[i], i);
        }
        return buildTreeHelper2(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    public TreeNode buildTreeHelper2(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if(preStart>preEnd || inStart>inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIdx = buildMap.get(root.val);
        int leftNum = rootIdx-inStart;
        root.left = buildTreeHelper2(preorder, preStart+1, preStart+leftNum, inorder, inStart, rootIdx-1);
        root.right = buildTreeHelper2(preorder, preStart+leftNum+1, preEnd, inorder, rootIdx+1, inEnd);
        return root;
    }


    public static int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j*j<=i;j++){
                dp[i]=Math.min(dp[i], dp[i-j*j]+1);
            }
        }
        return dp[n];
    }



    /*
    Note: You are not suppose to use the library's sort function for this problem.

    Example:

    Input: [2,0,2,1,1,0]
    Output: [0,0,1,1,2,2]
     */
    public void sortColors(int[] nums) {
        int[] array = new int[3];
        Arrays.fill(array, 0);
        for(int i=0;i<nums.length;i++){
            array[nums[i]]++;
        }
        int idx = 0;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i];j++){
                nums[idx++]=i;
            }
        }
    }

    public void sortColors2(int[] nums) {
        int red = 0;int blue = nums.length-1;
        for(int i=0;i<=blue;i++){
            if(nums[i]==0){
                int tmp = nums[i];
                nums[i]=nums[red];
                nums[red]=tmp;
                red++;
                i++;
            }else if(nums[i]==2){
                int tmp = nums[i];
                nums[i]=nums[blue];
                nums[blue]=tmp;
                blue--;
            }else {
                i++;
            }
        }
    }

    /*
    Example:

    Input: "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new LinkedList<>();
        if(digits == null || digits.length() == 0) return list;
        char[][] map = {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
        letterCombinationsHelper(digits, 0, map, "", list);
        return list;
    }

    public void letterCombinationsHelper(String digits, int idx, char[][] map, String sb,  List<String> res) {
        if(idx == digits.length()){
            res.add(sb);
        }else {
            char[] a = map[digits.charAt(idx)-'0'];
            for(int i=0;i<a.length;i++){
                sb = sb+a[i];
                letterCombinationsHelper(digits, idx+1, map, sb, res);
                sb = sb.substring(0,sb.length()-1);
            }
        }
    }

    //abc
    public static List<String> test(String input){
        List<String> res = new LinkedList<>();
        if(input==null||input.length()==0) return res;
        char[] ch = input.toCharArray();
        return testHelper(ch, ch.length-1);
    }
    public static List<String> testHelper(char[] ch, int idx){
        if(idx == 0){
            List<String> res = new LinkedList<>();
            res.add(ch[idx]+"");
            return res;
        }
        List<String> res = testHelper(ch, idx-1);
        List<String> res_new = new LinkedList<>();
        for(String str: res){
            res_new.add(ch[idx]+str);
            for(int i=1;i<str.length();i++){
                res_new.add(str.substring(0, i) + ch[idx] + str.substring(i));
            }
            res_new.add(str+ch[idx]);
        }
        return res_new;
    }

    /*
    Example 1:

    Input: grid = [
      ["1","1","1","1","0"],
      ["1","1","0","1","0"],
      ["1","1","0","0","0"],
      ["0","0","0","0","0"]
    ]
    Output: 1
     */

    //dfs
    public int numIslands(char[][] grid) {
        if(grid==null||grid.length==0) return 0;
        int res = 0;
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == '1' && !visit[i][j]){
                    numIslandsDfs(grid, visit, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void numIslandsDfs(char[][] grid, boolean[][] visit, int x, int y){
        if(x<0||x>grid.length-1||y<0||y>grid[0].length-1||grid[x][y]=='0'||visit[x][y]){
            return;
        }
        visit[x][y]=true;
        numIslandsDfs(grid, visit, x-1, y);
        numIslandsDfs(grid, visit, x+1, y);
        numIslandsDfs(grid, visit, x, y-1);
        numIslandsDfs(grid, visit, x, y+1);
    }

    //bfs
    public static int numIslands2(char[][] grid) {
        int res = 0;
        if(grid==null||grid.length==0) return res;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == '1'){
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i,j});
                    while (!queue.isEmpty()){
                        int[] cur = queue.poll();
                        for (int[] dir : dirs) {
                            int x = cur[0] + dir[0];
                            int y = cur[1] + dir[1];
                            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1') {
                                grid[x][y] = '0';
                                queue.add(new int[]{x, y});
                            }
                        }
                    }
                    res++;
                }
            }
        }
        return res;
    }

    //bfs
    public static int numIslands3(char[][] grid) {
        int res = 0;
        if(grid==null||grid.length==0) return res;
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == '1' && !visit[i][j]){
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i,j});
                    while (!queue.isEmpty()){
                        int[] cur = queue.poll();
                        for (int[] dir : dirs) {
                            int x = cur[0] + dir[0];
                            int y = cur[1] + dir[1];
                            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1'&&!visit[x][y]) {
                                visit[x][y] = true;
                                queue.add(new int[]{x, y});
                            }
                        }
                    }
                    res++;
                }
            }
        }
        return res;
    }

    /*

        Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

    Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

    Example 1:

    [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,1,1,0,1,0,0,0,0,0,0,0,0],
     [0,1,0,0,1,1,0,0,1,0,1,0,0],
     [0,1,0,0,1,1,0,0,1,1,1,0,0],
     [0,0,0,0,0,0,0,0,0,0,1,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
     */
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        if(grid==null||grid.length==0) return 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    AtomicInteger tmp = new AtomicInteger();
                    maxAreaOfIslandDFS(grid, i, j, tmp);
                    res = Math.max(res, tmp.get());
                }
            }
        }
        return res;
    }

    public void maxAreaOfIslandDFS(int[][] grid, int x, int y,  AtomicInteger tmp){
        if(x<0||x>grid.length-1||y<0||y>grid[0].length-1||grid[x][y]==0){
            return;
        }
        grid[x][y]=0;
        tmp.incrementAndGet();
        maxAreaOfIslandDFS(grid, x-1, y, tmp);
        maxAreaOfIslandDFS(grid, x+1, y, tmp);
        maxAreaOfIslandDFS(grid, x, y-1, tmp);
        maxAreaOfIslandDFS(grid, x, y+1, tmp);
    }


    /*
    Example 1:

    Input: nums is [1, 1, 1, 1, 1], S is 3.
    Output: 5
    Explanation:

    -1+1+1+1+1 = 3
    +1-1+1+1+1 = 3
    +1+1-1+1+1 = 3
    +1+1+1-1+1 = 3
    +1+1+1+1-1 = 3

    There are 5 ways to assign symbols to make the sum of nums be target 3.
     */
    public int findTargetSumWays(int[] nums, int S) {
        AtomicInteger res = new AtomicInteger();
        findTargetSumWaysdfs(nums, S, nums.length-1, res);
        return res.get();
    }
    public void findTargetSumWaysdfs(int[] nums, int target, int idx, AtomicInteger res) {
        if(target==0&&idx==-1){
            res.incrementAndGet();
            return;
        }
        if(idx>=0){
            findTargetSumWaysdfs(nums, target-nums[idx], idx-1,  res);
            findTargetSumWaysdfs(nums, target+nums[idx], idx-1,  res);
        }
    }

    /*
                      sum(P) - sum(N) = target
    sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
                           2 * sum(P) = target + sum(nums)
    So the original problem has been converted to a subset sum problem as follows:
    Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
     */
    public int findTargetSumWays2(int[] nums, int S) {
        if(nums == null || nums.length==0)return 0;
        int sum = 0;
        for(int a: nums) sum+=a;
        if(S> sum || (sum+S)%2==1) return 0;
        return subsetSum(nums, (S+sum)/2);
    }

    private int subsetSum(int[] nums, int k) {
        int[][] dp = new int[nums.length+1][k+1];
        dp[0][0] = 1;
        for(int i=1;i<=nums.length;i++){
            for(int j=0;j<=k;j++){
                if(j<nums[i-1]){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[nums.length][k];
    }

    /*
         416. Partition Equal Subset Sum
         Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

        Note:

        Each of the array element will not exceed 100.
        The array size will not exceed 200.


        Example 1:

        Input: [1, 5, 11, 5]

        Output: true

        Explanation: The array can be partitioned as [1, 5, 5] and [11].
     */

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n: nums) sum+=n;
        if(sum%2==1) return false;
        sum = sum/2;
        boolean[][] dp = new boolean[nums.length+1][sum+1];
        dp[0][0] = true;
        for(int i= 1;i<=nums.length;i++){
            for(int j=0;j<=sum;j++){
                if(j<nums[i-1]){
                    dp[i][j]=dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j]||dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[nums.length][sum];
    }

    /*
    Example 1:

    Input:nums = [1,1,1], k = 2
    Output: 2
     */
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int tmp = 0;
        for(int i=0;i<nums.length;i++){
            tmp = 0;
            for(int j=i;j<nums.length;j++){
                tmp += nums[j];
                if(tmp == k){
                    res++;
                }
            }
        }
        return res;
    }

    /*
    使用一个字典保存数组某个位置之前的数组和，然后遍历数组求和，这样当我们求到一个位置的和的时候，向前找sum-k是否在数组中，如果在的话，更新结果为之前的结果+(sum-k出现的次数)。同时，当前这个sum出现的次数就多了一次。

    这个字典的意义是什么呢？其意义就是我们在到达i位置的时候，前i项的和出现的次数的统计。我们想找的是在i位置向前的连续区间中，有多少个位置的和是k。有了这个统计，我们就不用向前一一遍历找sum - k在哪些位置出现了，而是直接得出了前面有多少个区间。所以，在每个位置我们都得到了以这个位置为结尾的并且和等于k的区间的个数，所以总和就是结果
     */
    public int subarraySum2(int[] nums, int k) {
        int res = 0;
        int tmp = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i=0;i<nums.length;i++){
            tmp+=nums[i];
            if(map.get(tmp-k)!=null){
                res+=map.get(tmp-k);
            }
            map.put(tmp, map.getOrDefault(tmp, 0)+1);
        }
        return res;
    }

    /*

    Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
    According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
    Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
    Example 1:
    Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
    Output: 3
    Explanation: The LCA of nodes 5 and 1 is 3.
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode head = root;
        queue.add(head);
        while (!queue.isEmpty()){
            TreeNode curr = queue.poll();
            if(curr.left==null&&curr.right==null) continue;
            if(lowestCommonAncestorHelper(curr, p, q)){
                res = curr;
            }
            if(curr.left != null) queue.offer(curr.left);
            if(curr.right != null) queue.offer(curr.right);
        }
        return res;
    }

    public boolean lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
        int size = 0;
        TreeNode head = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            TreeNode curr = queue.poll();
            if(curr == p || curr == q){
                size++;
            }
            if(size==2) return true;
            if(curr.left != null) queue.offer(curr.left);
            if(curr.right != null) queue.offer(curr.right);
        }
        return false;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)  return root;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if(left != null && right != null)   return root;
        return left != null ? left : right;
    }

    /*
    Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
    Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
    The order of output does not matter.

    Example 1:
    Input:
    s: "cbaebabacd" p: "abc"
    Output:
    [0, 6]
    Explanation:
    The substring with start index = 0 is "cba", which is an anagram of "abc".
    The substring with start index = 6 is "bac", which is an anagram of "abc".
     */

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for(Character a: p.toCharArray()){
            map.put(a, map.getOrDefault(a, 0)+1);
        }
        for(int i=0;i<s.toCharArray().length-p.toCharArray().length+1;i++){
            HashMap<Character, Integer> mapUse = new HashMap<>();
            boolean flag = true;
            for(int j=i;j<i+p.toCharArray().length;j++){
                Character me = s.charAt(j);
                int to = mapUse.getOrDefault(me, 0);
                if(map.containsKey(me) && to < map.get(me)){
                    mapUse.put(me, to+1);
                }else {
                    flag=false;
                    break;
                }
            }
            if(flag) res.add(i);
        }
        return res;
    }
    //数组里均保持p.toArrayChar.length个字符数量
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() < p.length()) {
            return list;
        }
        int[] pArr = new int[26];
        int[] sArr = new int[26];
        for(int i=0; i<p.length(); i++){
            pArr[p.charAt(i)-'a']++;
            sArr[s.charAt(i)-'a']++;
        }
        if (Arrays.equals(pArr, sArr)) {
            list.add(0);
        }
        for (int j=p.length(); j<s.length(); j++) {
            ++sArr[s.charAt(j)-'a'];
            --sArr[s.charAt(j-p.length())-'a'];
            if (Arrays.equals(pArr, sArr)) {
                list.add(j-p.length()+1);
            }
        }
        return list;
    }


    public static List<Integer> findAnagrams3(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        int[] hash = new int[26]; //character hash
        for (char c : p.toCharArray()) {
            hash[c-'a']++;
        }
        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if (hash[s.charAt(right)-'a'] >= 1) {
                count--;
            }
            hash[s.charAt(right)-'a']--;
            right++;
            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0) {
                list.add(left);
            }
            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length() ) {
                if (hash[s.charAt(left)-'a'] >= 0) {
                    count++;
                }
                hash[s.charAt(left)-'a']++;
                left++;

            }
        }
        return list;
    }

    /*
    Example:
    Consider the following matrix:

    [
      [1,   4,  7, 11, 15],
      [2,   5,  8, 12, 19],
      [3,   6,  9, 16, 22],
      [10, 13, 14, 17, 24],
      [18, 21, 23, 26, 30]
    ]
    本质上是个搜索二叉树
     */

    public static boolean searchMatrix(int[][] matrix, int target) {
        for(int[] a: matrix){
            if(binarySearch(a, target)) return true;
        }
        return false;
    }
    public static boolean binarySearch(int[] array, int target){
        int left = 0;
        int right = array.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if(array[mid]==target){
                return true;
            }else if(array[mid]>target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }


    /*
    207. Course Schedule
    Example 1:
    Input: numCourses = 2, prerequisites = [[1,0]]
    Output: true
    Explanation: There are a total of 2 courses to take.
                 To take course 1 you should have finished course 0. So it is possible.
     */
    //bfs
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 建立邻接表
        LinkedList<Integer>[]  array = new LinkedList[numCourses];
        for(int i=0;i<numCourses;i++){
            array[i] = new LinkedList<>();
        }
        // 初始化邻接表数据
        int[] degree = new int[numCourses];
        for(int i=0;i<prerequisites.length;i++){
            int first = prerequisites[i][0];
            int second = prerequisites[i][1];
            degree[first]++;
            array[second].add(first);
        }
        //入队列所有入度为0的节点，从他们开始遍历
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(degree[i]==0){
                queue.offer(i);
                count++;
            }
        }
        //bfs遍历其所有出度节点
        while (!queue.isEmpty()){
            int a = queue.poll();
            for(int i=0;i<array[a].size();i++){
                int x = array[a].get(i);
                degree[x]--;
                if(degree[x]==0){  //经过路径剪断，如果入度为0则加入队列
                    queue.offer(x);
                    count++;       //没有环的图，经过剪断路径，所有节点都会变成入度为0的节点。
                }
            }
        }
        if(count != numCourses){
            return false;
        }
        return true;
    }

    // dfs
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        // 建立邻接表
        LinkedList<Integer>[]  array = new LinkedList[numCourses];
        for(int i=0;i<numCourses;i++){
            array[i] = new LinkedList<>();
        }
        // 初始化邻接表数据
        int[] degree = new int[numCourses];
        for(int i=0;i<prerequisites.length;i++){
            int first = prerequisites[i][0];
            int second = prerequisites[i][1];
            degree[first]++;
            array[second].add(first);
        }
        //dfs遍历邻接表
        boolean[] visited = new boolean[numCourses];
        boolean[] dp = new boolean[numCourses];
        for(int i=0;i<numCourses;i++){
            if(!dfs(visited, dp, array, i)){
                return false;
            }
        }
        return true;
    }

    public boolean dfs(boolean[] visited, boolean[] dp, LinkedList<Integer>[] list, int i){
        if(visited[i]){
            return dp[i];
        }else {
            visited[i]=true;
        }
        for(int j=0;j<list[i].size();j++){
            if(!dfs(visited, dp, list, list[i].get(j))){
                return false;
            }
        }
        dp[i] = true;
        return true;
    }

    /*
       Example:
       Input: [10,9,2,5,3,7,101,18]
       Output: 4
       Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
    */
    public int lengthOfLIS(int[] nums) {
        if(nums == null||nums.length==0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for(int i=1;i<nums.length;i++){
            int max = 0;
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max +1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }


    /*
        Example 1:

        Input: s = "leetcode", wordDict = ["leet", "code"]
        Output: true
        Explanation: Return true because "leetcode" can be segmented as "leet code".
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        return wordBreakHelper(s, 0, wordDict, map);
    }

    public boolean wordBreakHelper(String s, int idx, List<String> wordDict, HashMap<Integer, Boolean> map){
        if(map.get(idx) != null) return map.get(idx);
        if(wordDict.contains(s.substring(idx))){
            return true;
        }
        boolean res = false;
        for(int i=idx; i<s.toCharArray().length;i++){
            String str = s.substring(idx, i+1);
            if(wordDict.contains(str)){
                res = res || wordBreakHelper(s, i+1, wordDict, map);
            }
        }
        map.put(idx, res);
        return res;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.toCharArray().length];
        dp[0] = wordDict.contains(s.substring(0,1));
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<=i;j++){
                if(wordDict.contains(s.substring(j, i+1)) && (j==0 || dp[j-1])){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[dp.length-1];
    }


    /*
        Example 1:
        Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
        Output: [[1,6],[8,10],[15,18]]
        Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
     */
    public int[][] merge(int[][] intervals) {
        int x = intervals.length;
        int count = x;
        for(int i=0;i<x-1;i++){
            for(int j=i+1;j<x;j++){
                boolean flag = false;
                int f_f = intervals[i][0];
                int f_s = intervals[i][1];
                int s_f = intervals[j][0];
                int s_s = intervals[j][1];
                if(f_f>=s_f && f_f <=s_s || s_f>=f_f && s_f <=f_s){
                    s_f = Math.min(f_f, s_f);
                    s_s = Math.max(f_s, s_s);
                    intervals[i][0] = Integer.MIN_VALUE;
                    intervals[i][1] = Integer.MIN_VALUE;
                    intervals[j][0] = s_f;
                    intervals[j][1] = s_s;
                    flag = true;
                }
                if(flag) {
                    count--;
                    break;
                }
            }
        }
        int i=0;
        int[][] res = new int[count][2];
        for(int[] a: intervals){
            if(a[0]>Integer.MIN_VALUE){
                res[i][0] = a[0];
                res[i][1] = a[1];
                i++;
            }
        }
        return res;
    }

    public int[][] merge2(int[][] intervals) {
        Collections.sort(Arrays.asList(intervals), new IntervalComparator());
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
    private class IntervalComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] < b[0] ? -1 : a[0] == b[0] ? 0 : 1;
        }
    }
    /*
        Example:
        Input:
        1 0 1 0 0
        1 0 1 1 1
        1 1 1 1 1
        1 0 0 1 0
        Output: 4
     */
    public static int maximalSquare(char[][] matrix) {
        if(matrix.length ==0 || matrix[0].length == 0) return 0;
        int res = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j =0;j<n;j++){
                if(matrix[i][j]=='0'){
                    continue;
                }
                int max = 1;
                queue.offer(new int[]{i, j});
                boolean flag = true;
                while (!queue.isEmpty()){
                    int size = queue.size();
                    for(int w=0;w<size;w++){
                        int[] tmp = queue.poll();
                        int x = tmp[0];
                        int y = tmp[1];
                        if(x+1<m && y+1 <n && matrix[x][y] == '1' && matrix[x+1][y] == '1' && matrix[x+1][y+1] == '1' && matrix[x][y+1] == '1'){
                            queue.offer(new int[]{x+1, y});
                            queue.offer(new int[]{x+1, y+1});
                            queue.offer(new int[]{x, y+1});
                        }else {
                            flag = false;
                            queue.clear();
                            break;
                        }
                    }
                    if(flag){
                        max++;
                    }
                }
                res = Math.max(res, max*max);
            }
        }
        return res;
    }

    public static int maximalSquare2(char[][] matrix) {
        if(matrix.length ==0 || matrix[0].length == 0) return 0;
        int res = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j = 0;j<matrix[0].length;j++){
                if(i==0||j==0){
                    dp[i][j] = matrix[i][j]-'0';
                }else if(matrix[i][j] == '0'){
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res*res;
    }

    class Node2 {
        int val;
        Node2 next;
        Node2 random;

        public Node2(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

   /*
   Copy List with Random Pointer
    */
    public Node2 copyRandomList(Node2 head) {
        if(head == null) return null;
        Node2 res = new Node2(0);
        Node2 rescurr = res;
        Map<Node2, Integer> map = new HashMap<>();
        Map<Integer, Node2> map2 = new HashMap<>();
        Node2 curr = head;
        Node2 next;
        int i=1;
        while (curr!=null){
            next = curr.next;
            Node2 node2 = new Node2(curr.val);
            rescurr.next = node2;
            Node2 random = curr.random;
            if(random != null){
                int j=1;
                Node2 curr2 = head;
                while (curr2!=null){
                    if(curr2 == random){
                        map.put(node2, j);
                        break;
                    }
                    curr2=curr2.next;
                    j++;
                }
            }
            rescurr = rescurr.next;
            curr = next;
            map2.put(i, node2);
            i++;
        }
        curr = res.next;
        while (curr!=null){
            curr.random = map2.get(map.get(curr));
            curr = curr.next;
        }
        return res.next;
    }

    public Node2 copyRandomList2(Node2 head) {
        if (head == null) {
            return null;
        }
        Node2 p = head;
        Node2 next = null;
        // 先完成copy
        while(p!=null){
            next = p.next;
            Node2 node = new Node2(p.val);
            node.next = next;
            p.next = node;
            p = next;
        }
        p = head;
        Node2 q = p.next;
        // 复制random
        while(p!=null){
            if(p.random!=null){
                q.random = p.random.next;
            }
            p = p.next.next;
            // q移动两步指针则需要先进行判断
            if(p!=null){
                q = q.next.next;
            }
        }
        Node2 newHead = head.next;
        Node2 pHead = head;
        Node2 qHead = newHead;
        // 分离两个链表，这里特别注意不能改动原先链表状态。
        while(pHead!=null ){
            pHead.next = qHead.next;
            if(qHead.next!=null){
                qHead.next = qHead.next.next;
            }
            pHead = pHead.next;
            if(pHead!=null){
                qHead = qHead.next;
            }
        }
        return newHead;
    }


    /*
        Find First and Last Position of Element in Sorted Array
        Example 1:
        Input: nums = [5,7,7,8,8,10], target = 8
        Output: [3,4]
     */
    public int[] searchRange(int[] nums, int target) {
        if(nums.length==0 || target<nums[0] || target>nums[nums.length-1]) return new int[]{-1, -1};
        int[] res = new int[2];
        int low = 0;
        int high = nums.length-1;
        int idx = -1;
        while (low<=high){
            int mid = low+(high-low)/2;
            if(target == nums[mid]){
                idx = mid;
                break;
            }else if(target>nums[mid]){
                low = mid+1;
            }else {
                high = mid-1;
            }
        }
        if(idx<0){
            res[0] = -1;
            res[1] = -1;
        }else {
            int i = idx;
            int j = idx;
            int left = idx;
            int right = idx;
            while (left>=0 || right<nums.length){
                if(left>=0&&nums[left]==target){
                    i = left;
                    left--;
                }else {
                    left = -1;
                }
                if(right<nums.length&&nums[right]==target){
                    j = right;
                    right++;
                }else {
                    right = nums.length;
                }
            }
            res[0] = i;
            res[1] = j;
        }
        return res;
    }

    public int[] searchRange2(int[] nums, int target) {
        if(nums.length==0 || target<nums[0] || target>nums[nums.length-1]) return new int[]{-1, -1};
        int[] res = new int[2];
        int low = 0;
        int high = nums.length;
        int left = binarySerachLeft(nums, target);
        if(left == 0 && left == nums.length){
            return new int[]{-1, -1};
        }
        int right = binarySerachRight(nums, target);
        return new int[]{left, right};
    }

    public int binarySerachLeft(int[] nums, int target){
        int low = 0;
        int high = nums.length;
        while (low<high){
            int mid = low+(high-low)/2;
            if(nums[mid] == target){
                high = mid;
            }else if(nums[mid] > target) {
                high = mid;
            }else if(nums[mid] < target){
                low = mid+1;
            }
        }
        return low;
    }

    public int binarySerachRight(int[] nums, int target){
        int low = 0;
        int high = nums.length;
        while (low<high){
            int mid = low+(high-low)/2;
            if(nums[mid] == target){
                low = mid+1;
            }else if(nums[mid] < target) {
                low = mid+1;
            }else if(nums[mid] > target){
                high = mid;
            }
        }
        return low-1;
    }


    /*
        Word Search
        Example:
        board =
        [
          ['A','B','C','E'],
          ['S','F','C','S'],
          ['A','D','E','E']
        ]
        Given word = "ABCCED", return true.
        Given word = "SEE", return true.
        Given word = "ABCB", return false.
        dfs 回溯法
     */

    public boolean exist(char[][] board, String word) {
        if(board.length == 0 || board[0].length ==0 || word ==null || word == "") return false;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                boolean[][] visit = new boolean[board.length][board[0].length];
                if(existDfs(board, word, 0, i, j, visit)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existDfs(char[][] board, String word, int idx, int x, int y, boolean[][] visit){
        if(idx == word.length()){
            return true;
        }
        if(x<0||y<0||x>=board.length||y>=board[0].length){
            return false;
        }
        if(visit[x][y]){
            return false;
        }
        if(board[x][y] != word.charAt(idx)){
            return false;
        }
        visit[x][y] = true;
        boolean res =   existDfs(board, word, idx+1, x-1, y, visit)||
                         existDfs(board, word, idx+1, x+1, y, visit)||
                         existDfs(board, word, idx+1, x, y-1, visit)||
                        existDfs(board, word, idx+1, x, y+1, visit);
        visit[x][y] =false;
        return res;
    }

    /*
        Example 1:
        Input: coins = [1, 2, 5], amount = 11
        Output: 3
        Explanation: 11 = 5 + 5 + 1
     */

    public static int coinChange(int[] coins, int amount) {
        return coinChangeDfs(coins, 0, amount, new LinkedList<>());
    }

    public static int coinChangeDfs(int[] coins, int idx, int target, LinkedList<Integer> tmp){
        if(target == 0){
            return tmp.size();
        }
        if(target < 0){
            return -1;
        }
        int a = Integer.MAX_VALUE;
        for(int i=idx;i<coins.length;i++){
            tmp.add(coins[i]);
            int res = coinChangeDfs(coins, idx, target-coins[i], tmp);
            if(res>=0){
                a = Math.min(a, res);
            }
            tmp.removeLast();
        }
        if(a == Integer.MAX_VALUE) {
            return -1;
        }
        return a;
    }

    public static int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount];
        Arrays.fill(dp, 0);
        return coinChangeDfs2(coins, 0, amount, dp);
    }

    public static int coinChangeDfs2(int[] coins, int idx, int target, int[] dp){
        if(target == 0){
            return 0;
        }
        if(target < 0){
            return -1;
        }
        if(dp[target-1] !=0) return dp[target-1];
        int min = Integer.MAX_VALUE;
        for(int i=idx;i<coins.length;i++){
            int res = coinChangeDfs2(coins, idx, target-coins[i], dp);
            if(res>=0){
                min = Math.min(min, res+1);
            }
        }
        dp[target-1] = (min == Integer.MAX_VALUE ? -1: min);
        return dp[target-1];
    }

    public static int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int i=1;i<amount+1; i++){
            for(int j=0;j<coins.length;j++){
                if(coins[j]<=i){
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount]>amount ? -1: dp[amount];
    }




    /*
        19. Remove Nth Node From End of List
        Example:
        Given linked list: 1->2->3->4->5, and n = 2.
        After removing the second node from the end, the linked list becomes 1->2->3->5.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int i=0;
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode curr = head;
        while (curr!=null){
            i++;
            map.put(i, curr);
            curr = curr.next;
        }
        int idx = i-n+1;
        if(idx>1){
            ListNode pre = map.get(idx-1);
            curr = map.get(idx);
            pre.next = curr.next;
            curr.next = null;
        }else {
            curr = head;
            head = head.next;
            curr.next = null;
        }
        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (n>0 && fast!=null){
            fast=fast.next;
            n--;
        }
        while (fast !=null && fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        ListNode next = slow.next;
        slow.next = next.next;
        next.next = null;
        return dummy.next;
    }


    /*
    55. Jump Game
    Example 1:
    Input: nums = [2,3,1,1,4]
    Output: true
    Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     */
    public static boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        Arrays.fill(dp, false);
        dp[0]=true;
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<=nums[i];j++){
                if(dp[i]){
                    if(i+j >= nums.length-1){
                        return true;
                    }else if(i+j < nums.length-1){
                        dp[i+j] = true;
                    }
                }
            }
        }
        return false;
    }

    //贪婪算法，计算最远到达点是否到达终点
    public static boolean canJump2(int[] nums) {
        int reach = 0;
        for(int i=0;i<nums.length;i++){
            if(i>reach || reach>=nums.length-1){break;}
            reach = Math.max(reach, i+nums[i]);
        }
        return reach>=nums.length-1;
    }


    /*
        Example 1:
        Input: nums = [4,5,6,7,0,1,2], target = 0
        Output: 4
     */
    public int search(int[] nums, int target) {
        int low = 0; int high = nums.length-1;
        while (low<=high){
            int mid = low+(high-low)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid]<nums[high]){
                if(target>nums[mid]&&target<=nums[high]) {
                    low=mid+1;
                }else {
                    high=mid-1;
                }
            }else {
                if(target>=nums[low]&&target<nums[mid]){
                    high=mid-1;
                }else {
                    low=mid+1;
                }
            }
        }
        return -1;
    }

    /*
    Example 1:

    Input: [2,3,-2,4]
    Output: 6
    Explanation: [2,3] has the largest product 6.
     */
    public int maxProduct(int[] nums) {
        if(nums.length == 1) return nums[0];
        int max = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            int a = nums[i];
            int b = nums[i];
            for(int j = i+1;j<nums.length;j++){
                b = b*nums[j];
                max = Math.max(max, b);
            }
            max = Math.max(max, a);
        }
        return max;
    }

    public int maxProduct2(int[] nums) {
        if(nums.length == 1) return nums[0];
        int[] max = new int[nums.length]; //保存前面最大值
        int[] min = new int[nums.length]; //保存前面最小值
        int res = nums[0];
        max[0] = nums[0];
        min[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            max[i] = Math.max(Math.max(max[i-1]*nums[i], min[i-1]*nums[i]), nums[i]);
            min[i] = Math.min(Math.min(max[i-1]*nums[i], min[i-1]*nums[i]), nums[i]);
            res = Math.max(res, max[i]);
        }
        return res;
    }

    public int maxProduct3(int[] nums) {  //因为只用到i和i-1，所以只需要两个常数即可
        if(nums.length == 1) return nums[0];
        int res = nums[0];
        int max = nums[0];
        int min = nums[0];
        for(int i=1;i<nums.length;i++){
            int tmax = max;
            int tmin = min;
            max = Math.max(Math.max(tmax*nums[i], tmin*nums[i]), nums[i]);
            min = Math.min(Math.min(tmax*nums[i], tmin*nums[i]), nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    /*
        Example 2:

        Input: s = "bbbbb"
        Output: 1
        Explanation: The answer is "b", with the length of 1.
     */

    public int lengthOfLongestSubstring4(String s) {
        int res = 0;
        if(s==null||s.length()==0) return res;
        int i=0,j=0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        while (i<s.length() && j<s.length()){
            Character c = s.charAt(j);
            if(!map.containsKey(c)){
                j++;
                max++;
                map.put(c, 1);
                res = Math.max(max, res);
            }else {
                max = 0;
                map.clear();
                i++;
                j=i;
            }
        }
        return res;
    }


    /*
        98. Validate Binary Search Tree
     */
    public boolean isValidBST1(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }
    public boolean isValidBSTHelper(TreeNode root, Integer low, Integer high) {
        if(root == null) return true;
        int val = root.val;
        if(low!=null && val<=low) return false;
        if(high!=null && val>=high) return false;
        if(!isValidBSTHelper(root.left, low, val)) return false;
        if(!isValidBSTHelper(root.right, val, high)) return false;
        return true;
    }
    public boolean isValidBST2(TreeNode root) {
        if(root == null) return true;
        double inorder = - Double.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (!stack.isEmpty()||curr!=null){
            while (curr!=null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if(curr.val<=inorder) return false;
            inorder = curr.val;
            curr = curr.right;
        }
        return true;
    }


    public class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root==null) return "[]";
            StringBuilder res = new StringBuilder();
            res.append("[");
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()){
                int size = queue.size();
                for(int i=0;i<size;i++){
                    TreeNode curr = queue.poll();
                    if(curr!=null){
                        res.append(curr.val).append(",");
                        queue.offer(curr.left);
                        queue.offer(curr.right);
                    }else {
                        res.append("null").append(",");
                    }
                }
            }
            String a = res.toString();
            return a.substring(0, a.length()-1) + "]";
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data == null || data.length()==0) return null;
            data = data.substring(1, data.length()-1);
            if(data == null || data.length()==0) return null;
            Queue<TreeNode> parent = new LinkedList<>();
            Queue<TreeNode> son = new LinkedList<>();
            String[] str = data.split(",");
            for(String s: str){
                if(!s.equals("null")){
                    son.offer(new TreeNode(Integer.parseInt(s)));
                }else {
                    son.offer(null);
                }
            }
            TreeNode root = son.poll();
            parent.offer(root);
            while (!parent.isEmpty()){
                TreeNode curr = parent.poll();
                if(curr!=null){
                    TreeNode left  = son.poll();
                    TreeNode right = son.poll();
                    curr.left = left;
                    curr.right = right;
                    if(left!=null){
                        parent.offer(left);
                    }
                    if(right!=null){
                        parent.offer(right);
                    }
                }
            }
            return root;
        }
    }

    /*
        Longest Consecutive Sequence
        Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
        Your algorithm should run in O(n) complexity.
        Example:
        Input: [100, 4, 200, 1, 3, 2]
        Output: 4
        Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     */
    public int longestConsecutive(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        kuaisu(nums);
        int res = 1;
        int count = 1;
        int prev = nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i] == prev) continue;
            if(nums[i] == prev+1){
                count++;
                prev++;
                res = Math.max(res, count);
            }else {
                count=1;
                prev=nums[i];
            }
        }
        return res;
    }

    public void kuaisu(int[] array){
        kuaisuHelper(array, 0, array.length-1);
    }
    public void kuaisuHelper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisuHelper2(array, low, high);
        kuaisuHelper(array, low, aow);
        kuaisuHelper(array, aow+1, high);
    }
    public int kuaisuHelper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&array[high]>=aow) high--;
            array[low]=array[high];
            while (low<high&&array[low]<=aow) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }

    public int longestConsecutive2(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int meta: nums){
            map.put(meta, 1);
        }
        int res = 1;
        for(int i=0;i<nums.length;i++){  //很关键，可以去除很多重复计算
            if(!map.containsKey(nums[i]-1)){
                int count = 1;
                int prev = nums[i];
                while (map.containsKey(prev+1)){
                    count++;
                    prev++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    /*
       Largest Rectangle in Histogram
    */
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        if(heights==null||heights.length==0) return res;
        for(int i=0;i<heights.length;i++){
            int min = heights[i];
            for(int j=i;j<heights.length;j++){
                if(min>heights[j]){
                    min=heights[j];
                }
                res=Math.max(res, (j-i+1)*min);
            }
        }
        return res;
    }

    //利用峰值计算
    public int largestRectangleArea2(int[] heights) {
        int res = 0;
        if(heights==null||heights.length==0) return res;
        for(int i=0;i<heights.length;i++){
            if(i+1<heights.length&&heights[i]<=heights[i+1]) continue;
            int min = heights[i];
            for(int j=i;j>=0;j--){
                if(min>heights[j]){
                    min=heights[j];
                }
                res=Math.max(res, (i-j+1)*min);
            }
        }
        return res;
    }

    public static int largestRectangleArea3(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int[] h = Arrays.copyOf(height, height.length + 1);
        int i = 0;
        while(i < h.length){
            if(stack.isEmpty() || h[stack.peek()] <= h[i]){
                stack.push(i++);
            }else {
                int t = stack.pop();
                maxArea = Math.max(maxArea, h[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return maxArea;
    }

    /*
        Maximal Rectangle
        Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
        Example:
        Input:
        [
          ["1","0","1","0","0"],
          ["1","0","1","1","1"],
          ["1","1","1","1","1"],
          ["1","0","0","1","0"]
        ]
        Output: 6
     */
    public int maximalRectangle(char[][] matrix) {
        int res = 0;
        if(matrix==null||matrix.length==0) return res;
        int x = matrix.length;
        int y = matrix[0].length;
        int[] height = new int[y];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                height[j] = matrix[i][j]=='0'?0:(height[j]+1);
            }
            res = Math.max(res, largestRectangleArea(height));
        }
        return res;
    }

    public int maximalRectangle2(char[][] matrix) {
        int res = 0;
        if(matrix==null||matrix.length==0) return res;
        int x = matrix.length;
        int y = matrix[0].length;
        int[] height = new int[y];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                height[j] = matrix[i][j]=='0'?0:(height[j]+1);
            }
            res = Math.max(res, largestRectangleArea2(height));
        }
        return res;
    }

    /*
        Word Search II
        Example:
        Input:
        board = [
          ['o','a','a','n'],
          ['e','t','a','e'],
          ['i','h','k','r'],
          ['i','f','l','v']
        ]
        words = ["oath","pea","eat","rain"]
        Output: ["eat","oath"]
     */
    public List<String> findWords(char[][] board, String[] words) {
        int x = board.length;
        int y = board[0].length;
        List<String> res = new LinkedList<>();
        for(String word: words){
            boolean find = false;
            for(int i=0;i<x;i++){
                for(int j=0;j<y;j++){
                    boolean[][] visit = new boolean[x][y];
                    if(findWordsDfs(i, j, board, 0, word, visit)){
                        res.add(word);
                        find=true;
                        break;
                    }
                }
                if(find) break;
            }
        }
        return res;
    }

    public boolean findWordsDfs(int x, int y, char[][] board, int idx, String word, boolean[][] visit){
        if(idx == word.length()) return true;
        if(x<0 || x>=board.length || y<0 || y>=board[0].length) return false;
        if(visit[x][y]) return false;
        if(board[x][y] != word.charAt(idx)) return false;
        visit[x][y]=true;
        boolean res =  findWordsDfs(x-1, y, board, idx+1, word, visit)
                || findWordsDfs(x+1, y, board, idx+1, word, visit)
                || findWordsDfs(x, y-1, board, idx+1, word, visit)
                || findWordsDfs(x, y+1, board, idx+1, word, visit);
        visit[x][y]=false;
        return res;
    }

    /*
        Minimum Window Substring
        Example:
        Input: S = "ADOBECODEBANC", T = "ABC"
        Output: "BANC"
     */
    public String minWindow(String s, String t) {
        return null;
    }

    /*
        Example 1:
        Input: [1,2,3]

               1
              / \
             2   3
        Output: 6
     */
    public int maxPathSum(TreeNode root) {
        Queue<TreeNode> queue =  new LinkedList<>();
        queue.offer(root);
        int res  = Integer.MIN_VALUE;
        while (!queue.isEmpty()){
            TreeNode curr = queue.poll();
            int left  = maxPathSumHelper(curr.left, curr.val);
            int right = maxPathSumHelper(curr.right, curr.val);
            int val = left+right-curr.val;
            res = Math.max(res, Math.max(Math.max(left, right), val));
            if(curr.left!=null) queue.offer(curr.left);
            if(curr.right!=null) queue.offer(curr.right);
        }
        return res;
    }

    public int maxPathSumHelper(TreeNode root, int res) {
        if(root == null) return res;
        int left  = maxPathSumHelper(root.left,  res+root.val);
        int right = maxPathSumHelper(root.right, res+root.val);
        return Math.max(Math.max(left, right), Math.max(res, res+root.val));
    }

    public int maxPathSum2(TreeNode root) {
        maxPathSumHelper2(root);
        return maxPathSum2Res;
    }
    int maxPathSum2Res = Integer.MIN_VALUE;
    public int maxPathSumHelper2(TreeNode root) {
        if(root == null) return 0;
        int left  = Math.max(maxPathSumHelper2(root.left), 0);
        int right = Math.max(maxPathSumHelper2(root.right), 0);
        maxPathSum2Res = Math.max(maxPathSum2Res, left+right+root.val);
        return Math.max(left, right)+root.val;
    }

    /*
        First Missing Positive
        Example 1:
        Input: [1,2,0]
        Output: 3
        Example 2:
        Input: [3,4,-1,1]
        Output: 2
        Example 3:
        Input: [7,8,9,11,12]
        Output: 1
     */
    public int firstMissingPositive(int[] nums) {
        for(int i=0;i<nums.length;i++){
            while (nums[i]>0&&nums[i]<=nums.length&&nums[i]!= nums[nums[i]-1]){
                int tmp = nums[i];
                nums[i]=nums[nums[i]-1];
                nums[tmp-1]=tmp;
            }
        }
        for(int i=0;i<nums.length;i++){
            if(i+1 != nums[i]) return i+1;
        }
        return nums.length+1;
    }

    /*
        Jump Game II
        Example:
        Input: [2,3,1,1,4]
        Output: 2
        Explanation: The minimum number of jumps to reach the last index is 2.
            Jump 1 step from index 0 to 1, then 3 steps to the last index.
     */
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<=nums[i];j++){
                if(dp[i]<Integer.MAX_VALUE){
                    if(i+j <= nums.length-1){
                        dp[i+j]=Math.min(dp[i+j], dp[i]+1);
                    }
                }
            }
        }
        return dp[nums.length-1];
    }

    public int jump2(int[] nums) {
        if(nums.length==0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<=nums[i];j++){
                if(dp[i]<Integer.MAX_VALUE){
                    if(i+j < nums.length-1){
                        dp[i+j]=Math.min(dp[i+j], dp[i]+1);
                    }else {
                        return dp[i]+1;
                    }
                }
            }
        }
        return -1;
    }

    //https://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
    public int jump(int A[], int n) {
        int ret = 0;
        int last = 0;
        int curr = 0;
        for (int i = 0; i < n; ++i) {
            if (i > last) {
                last = curr;
                ++ret;
            }
            curr = Math.max(curr, i+A[i]);
        }

        return ret;
    }

}

