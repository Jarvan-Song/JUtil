package util.leetcode;

import com.sun.org.apache.regexp.internal.RE;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by songpanfei on 2019-09-19.
 */
public class Solution {

    public static void main(String[] args){
//        System.out.println(reverse2(-2147483648));
//        System.out.println(IsPalindrome3(1234));
//        System.out.println(searchInsert2(new int[]{1,2,4,5},6));
//        System.out.println(maxSubArray1(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
//        System.out.println(mySqrt(110));
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
//        System.out.println(minDistance("horse","ros"));
//        System.out.println(countPrimes2(10));
        int[] nums = new int[]{1,2,2};
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
        System.out.println(findDuplicate(nums));
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


    public int maxProfit2(int[] prices) {
        int max = 0;
        for(int i= 1;i<prices.length;i++){
            if(prices[i] - prices[i-1] > 0){
                max = max + (prices[i] - prices[i-1]);
            }
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
        ListNode res = new ListNode(0);
        ListNode curr = res;
        Map<ListNode, Integer> map = minNode(lists);
        while (map != null && map.size() > 0){
            ListNode min = null;
            Set<ListNode> set = map.keySet();
            for(ListNode node: set){
                min = node;
            }
            curr.next = min;
            curr = curr.next;
            lists[map.get(min)] = min.next;
            map = minNode(lists);
        }
        return res.next;
    }

    public Map<ListNode, Integer> minNode(ListNode[] lists){
        if(lists.length == 0){
            return null;
        }
        Map<ListNode, Integer> map = null;
        ListNode min = null;
        for(int i=0;i<lists.length;i++){
            ListNode curr = lists[i];
            if(min == null && curr != null){
                if(map == null){
                    map = new HashMap<>();
                }
                map.clear();
                min = curr;
                map.put(min, i);
            }else if(min != null && curr != null){
                if(map == null){
                    map = new HashMap<>();
                }
                if(min.val > curr.val){
                    map.clear();
                    min = curr;
                    map.put(min, i);
                }
            }
        }
        return map;
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
                    int min = Math.min(add, delete);
                    min = Math.min(min, replace);
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
        return pathSumHelper(root, sum) +  pathSum(root.left, sum)+pathSum(root.right, sum);
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

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (p1, p2) -> {
            return p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0];
        });
        LinkedList<int[]> list = new LinkedList<>();
        for (int i = 0; i < people.length; i++)
            list.add(people[i][1], people[i]);
        return list.toArray(people);
    }
}
