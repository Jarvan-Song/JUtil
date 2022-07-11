package util.leetcode;

import java.util.*;

public class Solution202206 {

    public static void main(String[] args) {
        lengthOfLongestSubstring("au");
    }

    //1. 两数之和
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i], i);
        }
        for(int i=0;i<nums.length;i++){
            int remain = target - nums[i];
            Integer j = map.get(remain);
            if(j != null && j != i){
                return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    static class ListNode2 {
        int val;
        ListNode2 next;
        ListNode2() {}
        ListNode2(int val) { this.val = val; }
        ListNode2(int val, ListNode2 next) { this.val = val; this.next = next; }
    }

    //2. 两数相加
    public static ListNode2 addTwoNumbers(ListNode2 l1, ListNode2 l2) {
        ListNode2 head = new ListNode2();
        ListNode2 curr = head;
        ListNode2 l1C = l1;
        ListNode2 l2C = l2;
        int i = 0;
        while (l1C != null && l2C != null){
            int sum = l1C.val + l2C.val + i;
            int val = sum % 10;
            i = sum / 10;
            curr.next = new ListNode2(val);
            curr = curr.next;
            l1C = l1C.next;
            l2C = l2C.next;
        }
        while (l1C != null){
            int sum = l1C.val + i;
            int val = sum % 10;
            i = sum / 10;
            curr.next = new ListNode2(val);
            curr = curr.next;
            l1C = l1C.next;
        }
        while (l2C != null){
            int sum = l2C.val + i;
            int val = sum % 10;
            i = sum / 10;
            curr.next = new ListNode2(val);
            curr = curr.next;
            l2C = l2C.next;
        }
        if(i > 0){
            curr.next = new ListNode2(i);
        }
        return head.next;
    }

    //3. 无重复字符的最长子串  "abcabcbb"
    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        if(s.length() == 1){
            return 1;
        }
        int max = 0;
        for(int i=0;i<s.length()-1;i++){
            int maxCurr = 1;
            Set<String> set = new HashSet<>();
            set.add(String.valueOf(s.charAt(i)));
            for(int j=i+1;j<s.length();j++){
                if(!set.contains(String.valueOf(s.charAt(j)))){
                    set.add(String.valueOf(s.charAt(j)));
                    maxCurr = Math.max(maxCurr, set.size());
                }else {
                    break;
                }
            }
            max = Math.max(maxCurr, max);
        }
        return max;
    }

    //滑动窗口法
    public static int lengthOfLongestSubstring2(String s) {
        int[] tmp = new int[128];
        Arrays.fill(tmp, -1);
        int n = s.length();
        int start = 0;
        int res = 0;
        for(int i=0;i<n;i++){
            int idx = s.charAt(i);
            start =  Math.max(start, tmp[idx]+1);
            res = Math.max(res, i-start+1);
            tmp[idx] = i;
        }
        return res;
    }

    //4. 寻找两个正序数组的中位数
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length ==0 && nums2.length ==0){
            return 0;
        }
        int[] tmp = new int[nums1.length + nums2.length];
        int i = 0, j= 0, k=0;
        while (i<nums1.length && j<nums2.length){
            tmp[k++] = nums1[i]>nums2[j]?nums2[j++]:nums1[i++];
        }
        while (i<nums1.length){
            tmp[k++] = nums1[i++];
        }
        while (j<nums2.length){
            tmp[k++] = nums2[j++];
        }
        if(tmp.length == 1){
            return tmp[0]/1.0;
        }
        if(tmp.length%2 ==0){
            return (tmp[tmp.length/2] + tmp[tmp.length/2-1])/2.0;
        }else {
            return tmp[tmp.length/2]/1.0;
        }
    }


    /**
     * 这道题让我们求两个有序数组的中位数，而且限制了时间复杂度为O(log (m+n))，看到这个时间复杂度，自然而然的想到了应该使用二分查找法来求解。
     * 那么回顾一下中位数的定义，如果某个有序数组长度是奇数，那么其中位数就是最中间那个，如果是偶数，那么就是最中间两个数字的平均值。
     * 这里对于两个有序数组也是一样的，假设两个有序数组的长度分别为m和n，由于两个数组长度之和 m+n 的奇偶不确定，因此需要分情况来讨论，对于奇数的情况，直接找到最中间的数即可，偶数的话需要求最中间两个数的平均值。
     * 为了简化代码，不分情况讨论，我们使用一个小trick，我们分别找第 (m+n+1) / 2 个，和 (m+n+2) / 2 个，然后求其平均值即可，这对奇偶数均适用。
     * 加入 m+n 为奇数的话，那么其实 (m+n+1) / 2 和 (m+n+2) / 2 的值相等，相当于两个相同的数字相加再除以2，还是其本身。
     * 这里我们需要定义一个函数来在两个有序数组中找到第K个元素，下面重点来看如何实现找到第K个元素。首先，为了避免产生新的数组从而增加时间复杂度，
     * 我们使用两个变量i和j分别来标记数组nums1和nums2的起始位置。然后来处理一些边界问题，比如当某一个数组的起始位置大于等于其数组长度时，说明其所有数字均已经被淘汰了，
     * 相当于一个空数组了，那么实际上就变成了在另一个数组中找数字，直接就可以找出来了。还有就是如果K=1的话，那么我们只要比较nums1和nums2的起始位置i和j上的数字就可以了。
     * 难点就在于一般的情况怎么处理？因为我们需要在两个有序数组中找到第K个元素，为了加快搜索的速度，我们要使用二分法，对K二分，意思是我们需要分别在nums1和nums2中查找第K/2个元素，
     * 注意这里由于两个数组的长度不定，所以有可能某个数组没有第K/2个数字，所以我们需要先检查一下，数组中到底存不存在第K/2个数字，如果存在就取出来，否则就赋值上一个整型最大值。
     * 如果某个数组没有第K/2个数字，那么我们就淘汰另一个数字的前K/2个数字即可。有没有可能两个数组都不存在第K/2个数字呢，这道题里是不可能的，因为我们的K不是任意给的，而是给的m+n的中间值，
     * 所以必定至少会有一个数组是存在第K/2个数字的。最后就是二分法的核心啦，比较这两个数组的第K/2小的数字midVal1和midVal2的大小，如果第一个数组的第K/2个数字小的话，
     * 那么说明我们要找的数字肯定不在nums1中的前K/2个数字，所以我们可以将其淘汰，将nums1的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归。
     * 反之，我们淘汰nums2中的前K/2个数字，并将nums2的起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归即可。
     *
     * 赋予最大值的意思只是说如果第一个数组的K/2不存在，则说明这个数组的长度小于K/2，那么另外一个数组的前K/2个我们是肯定不要的。
     * 给你举个例子，加入第一个数组长度是2，第二个数组长度是12，则K为7，K/2为3，因为第一个数组长度小于3，则无法判断中位数是否在其中，而第二个数组的前3个肯定不是中位数！故当K/2不存在时，将其置为整数型最大值，这样就可以继续下一次循环。
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left  = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }
    //i: nums1的起始位置 j: nums2的起始位置
    public int findKth(int[] nums1, int i, int[] nums2, int j, int kth){
        if( i >= nums1.length) return nums2[j + kth - 1];//nums1为空数组
        if( j >= nums2.length) return nums1[i + kth - 1];//nums2为空数组
        if(kth == 1){
            return Math.min(nums1[i], nums2[j]);
        }
        int midVal1 = (i + kth / 2 - 1 < nums1.length) ? nums1[i + kth / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + kth / 2 - 1 < nums2.length) ? nums2[j + kth / 2 - 1] : Integer.MAX_VALUE;
        if(midVal1 < midVal2){
            return findKth(nums1, i + kth / 2, nums2, j , kth - kth / 2);
        }else{
            return findKth(nums1, i, nums2, j + kth / 2 , kth - kth / 2);
        }
    }

    //5. 最长回文子串
    public String longestPalindrome(String s) {
        String res = "";
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                if(longestPalindromeHelp(s, i, j) && res.length() < (j-i+1)){
                    res = s.substring(i,j+1);
                }
            }
        }
        return res;
    }

    public boolean longestPalindromeHelp(String s, int left, int right) {
        while (left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;right--;
        }
        return true;
    }

    public String longestPalindrome2(String s) {
        int len = s.length();
        if(len < 2){
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        for(int i=0;i<s.length();i++){
            dp[i][i] = true;
        }
        int begin = 0;
        int max = 1;
        for(int i=1;i<s.length();i++){
            for(int j=0;j<i;j++){
                if(s.charAt(i) != s.charAt(j)){
                    dp[j][i] = false;
                }else {
                    if(i-j < 3){
                        dp[j][i] = true;
                    }else {
                        dp[j][i] = dp[j+1][i-1];
                    }
                }
                if(dp[j][i] && (i-j+1)>max){
                    max = i-j+1;
                    begin = j;
                }
            }
        }
        return s.substring(begin, begin+max);
    }

}
