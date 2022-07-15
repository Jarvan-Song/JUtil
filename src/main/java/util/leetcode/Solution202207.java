package util.leetcode;


import java.util.*;

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

    //11. 盛最多水的容器
    public int maxArea(int[] height) {
        int max = 0;
        for(int i = 1;i<height.length;i++){
            for(int j=0;j<i;j++){
                int cur = (i-j)*Math.min(height[i], height[j]);
                max = Math.max(max, cur);
            }
        }
        return max;
    }

    public int maxArea2(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length-1;
        while (i<j){
            int cur = (j-i)*Math.min(height[i], height[j]);
            max = Math.max(max, cur);
            if(height[i] > height[j]){
                j--;
            }else {
                i++;
            }
        }
        return max;
    }

    //15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if(nums.length < 3){
            return res;
        }
        Map<String, String> map = new HashMap();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            for(int j = i+1;j<nums.length-1;j++){
                for(int x = j+1;x<nums.length;x++){
                    if(nums[i]+nums[j]+nums[x] == 0){
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[x]);
                        String key = "" + nums[i] + nums[j] + nums[x];
                        String val = map.get(key);
                        if(val == null){
                            res.add(list);
                            map.put(key, "1");
                        }
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if(nums.length < 3){
            return res;
        }
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++) {
            if(nums[i] > 0) continue;
            if(i> 0 && nums[i] == nums[i-1]) continue;
            int l = i+1;
            int r = nums.length -1;
            while (l<r){
                int sum = nums[i] + nums[l] + nums[r];
                if(sum == 0){
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l<r && nums[l] == nums[l+1]) l++;
                    while (l<r && nums[r] == nums[r-1]) r--;
                    l++;
                    r--;
                }else if(sum > 0){
                    r--;
                }else {
                    l++;
                }
            }
        }
        return res;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next;}
    }

    //19. 删除链表的倒数第 N 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = head;
        ListNode cur = head;
        ListNode next = head.next;
        ListNode first = head;
        while (n != 1){
            first = first.next;
            n--;
        }
        while (first.next != null){
            first = first.next;
            prev = cur;
            cur = next;
            next = cur.next;
        }
        if(cur == head){
            cur.next = null;
            return next;
        }
        prev.next = next;
        cur.next = null;
        return head;
    }

    //21. 合并两个有序链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null || list2 == null){
            return list1 != null ? list1 : list2;
        }
        ListNode head = new ListNode();
        ListNode cur = head;
        ListNode h1 = list1;
        ListNode h2 = list2;
        while (h1 != null && h2 != null){
            if(h1.val < h2.val){
                cur.next = new ListNode(h1.val);
                cur = cur.next;
                h1 = h1.next;
            }else {
                cur.next = new ListNode(h2.val);
                cur = cur.next;
                h2 = h2.next;
            }
        }
        while (h1 != null){
            cur.next = new ListNode(h1.val);
            cur = cur.next;
            h1 = h1.next;
        }
        while (h2 != null){
            cur.next = new ListNode(h2.val);
            cur = cur.next;
            h2 = h2.next;
        }
        return head.next;
    }

    //20. 有效的括号
    public boolean isValid(String s) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            int c = s.charAt(i);
            if(stack.isEmpty()){
                stack.push(c);
                continue;
            }
            int diff = c - stack.peek();
            if(diff == ')' -'(' || diff == '}'-'{' || diff == ']'-'['){
                stack.pop();
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }



}
