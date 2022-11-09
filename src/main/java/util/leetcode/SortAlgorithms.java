package util.leetcode;


import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by songpanfei on 2019-09-23.
 */
public class SortAlgorithms {
    public static void main(String[] args) {
        int[] w = new int[]{1, 11, 3, 9, 5, 6, 13, 4, 7, 15, 2, 12, 16};  //共13位
        System.out.println(kuaisuxuanze202211(w, 13));
        System.out.println(heapxuanze202211(w, 13));
        maopao(w);
        System.out.println(binary202211(w, 16));
        int[] a2 = new int[]{1, 3, 4, 5, 6, 9, 11, 13};
        SortAlgorithms dst = new SortAlgorithms();
        String[] methods = new String[]{"maopao", "xuanze", "charu", "kuaisu", "kuaisufei", "guibing", "guibingFei",
                "count", "bucket", "xier", "heap", "radixSort"};
        String suffix = "202211";
        boolean flag = true;
        for (String meta : methods) {
            int[] a = new int[]{1, 11, 3, 9, 5, 6, 13, 4};
            try {
                String methodName = meta + suffix;
                Method method = SortAlgorithms.class.getMethod(methodName, int[].class);
                if (meta.equals("count")) {
                    a = (int[]) method.invoke(dst, a);
                } else {
                    method.invoke(dst, a);
                }
                for (int i = 0; i < a.length; i++) {
                    if (a[i] != a2[i]) {
                        System.out.println(meta + " error");
                        flag = false;
                        break;
                    }
                }
                System.out.println(meta + " success");
            } catch (Exception e) {
                System.out.println(meta + " " + e);
                flag = false;
            }
        }
        if (flag) {
            System.out.println("success");
        } else {
            System.out.println("error");
        }
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void maopao(int[] arry) {
        for (int i = arry.length - 1; i > 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (arry[j] > arry[j + 1]) {
                    flag = true;
                    int tem = arry[j];
                    arry[j] = arry[j + 1];
                    arry[j + 1] = tem;
                }
            }
            if (!flag) break;
        }
    }

    public static void xuanze(int[] arry) {
        for (int i = 0; i < arry.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arry.length; j++) {
                if (arry[j] < arry[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arry[i];
                arry[i] = arry[min];
                arry[min] = temp;
            }
        }
    }

    public static void charu(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = i;
            int value = arr[i];
            while (key > 0 && arr[key - 1] > value) {
                arr[key] = arr[key - 1];
                key--;
            }
            arr[key] = value;
        }
    }

    public static void guibing(int[] array) {
        int[] tmp = new int[array.length];
        guibing(array, tmp, 0, array.length - 1);
    }

    public static void guibing(int[] array, int[] tmp, int low, int high) {
        if (low >= high) return;
        int middle = low + (high - low) / 2;
        guibing(array, tmp, low, middle);
        guibing(array, tmp, middle + 1, high);
        guibingHelper(array, tmp, low, middle, high);
    }

    public static void guibingHelper(int[] array, int[] tmp, int low, int middle, int high) {
        int i = low;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= high) {
            tmp[k++] = array[i] < array[j] ? array[i++] : array[j++];
        }
        while (i <= middle) {
            tmp[k++] = array[i++];
        }
        while (j <= high) {
            tmp[k++] = array[j++];
        }
        for (int w = 0; w < k; w++) {
            array[low + w] = tmp[w];
        }
    }

    //使用非递归的方式来实现归并排序
    public static void guibingFei(int[] arr) {
        int width = 1;
        while (width < arr.length) {
            guibingFeiHelper(arr, width);
            width = width * 2;
        }
    }

    //guibingFeiHelper方法负责将数组中的相邻的有k个元素的字序列进行归并
    public static void guibingFeiHelper(int[] arr, int width) {
        int start = 0;
        //从前往后,将2个长度为k的子序列合并为1个
        while (start + 2 * width - 1 < arr.length) {
            guibingFeiHelper2(arr, start, start + width - 1, start + 2 * width - 1);
            start = start + 2 * width;
        }
        //这段代码保证了，将那些“落单的”长度不足两两merge的部分和前面merge起来，这里的前面指的是不足两两部分的第一部分。
        //这段代码只处理长度大于1，不足两个的部分。小于1个的部分本次不处理，在下一次merge时会处理。
        if (start + width - 1 < arr.length) {
            guibingFeiHelper2(arr, start, start + width - 1, arr.length - 1);
        }
    }

    public static void guibingFeiHelper2(int[] array, int low, int mid, int high) {
        int[] tmp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            tmp[k++] = array[i] < array[j] ? array[i++] : array[j++];
        }
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= high) {
            tmp[k++] = array[j++];
        }
        for (int w = 0; w < k; w++) {
            array[low + w] = tmp[w];
        }
    }


    public static void kuaisu(int[] array) {
        kuaisu(array, 0, array.length - 1);
    }

    public static void kuaisu(int[] array, int low, int high) {
        if (low >= high) return;
        int avr = kuaisuHelper(array, low, high);
        kuaisu(array, low, avr);
        kuaisu(array, avr + 1, high);
    }

    public static int kuaisuHelper(int[] array, int low, int high) {
        int p = array[low];
        while (low < high) {
            while (low < high && array[high] >= p) high--;
            array[low] = array[high];
            while (low < high && array[low] <= p) low++;
            array[high] = array[low];
        }
        array[low] = p;
        return low;
    }

    public static void kuaisufei(int[] array) {
        kuaisuFeihelper(array, 0, array.length - 1);
    }

    public static void kuaisuFeihelper(int[] array, int low, int high) {
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()) {
            int r = stack.pop();
            int l = stack.pop();
            if (l >= r) continue;
            int aow = kuaisuHelper(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow + 1);
            stack.push(r);
        }
    }

    public static int[] count(int[] array) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            int meta = array[i];
            max = Math.max(max, meta);
            min = Math.min(min, meta);
        }
        int[] countArray = new int[max - min + 1];
        Arrays.fill(countArray, 0);
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }
        int sum = 0;
        for (int i = 0; i < countArray.length; i++) {
            sum = sum + countArray[i];
            countArray[i] = sum;
        }
        int[] res = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            res[countArray[array[i] - min] - 1] = array[i];
            countArray[array[i] - min]--;
        }
        return res;
    }

    public static void bucket(int[] array) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int a : array) {
            max = Math.max(max, a);
            min = Math.min(min, a);
        }
        int num = (max - min) / array.length + 1;
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            list.add(new LinkedList<>());
        }
        for (int a : array) {
            int idx = (a - min) / array.length;
            list.get(idx).add(a);
        }
        int idx = 0;
        for (List<Integer> a : list) {
            a.sort(null);
            for (int b : a) {
                array[idx++] = b;
            }
        }
    }

    //基数排序
    public static void radixSort(int[] arr) {
        int N = 1;
        int maxValue = arr[0];
        for (int element : arr) {
            if (element > maxValue) {
                maxValue = element;
            }
        }
        while (maxValue / 10 != 0) {
            maxValue = maxValue / 10;
            N += 1;
        }
        for (int i = 0; i < N; i++) {
            List<List<Integer>> radix = new ArrayList<>();
            for (int k = 0; k < 10; k++) {
                radix.add(new ArrayList<>());
            }
            for (int element : arr) {
                int idx = (element / (int) Math.pow(10, i)) % 10;
                radix.get(idx).add(element);
            }
            int idx = 0;
            for (List<Integer> l : radix) {
                for (int n : l) {
                    arr[idx++] = n;
                }
            }
        }
    }


    public static void xier(int[] arr) {
        int temp;
        for (int delta = arr.length / 2; delta >= 1; delta /= 2) {                              //对每个增量进行一次排序
            for (int i = delta; i < arr.length; i++) {
                for (int j = i; j - delta >= 0 && arr[j] < arr[j - delta]; j -= delta) { //注意每个地方增量和差值都是delta
                    temp = arr[j - delta];
                    arr[j - delta] = arr[j];
                    arr[j] = temp;
                }
            }//loop i
        }//loop delta
    }

    public static void heap(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapHelper(array, i, array.length);
        }

        for (int i = array.length - 1; i > 0; i--) {
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            heapHelper(array, 0, i);
        }
    }

    public static void heapHelper(int[] array, int i, int len) {
        int tmp = array[i];
        for (int k = 2 * i + 1; k < len; k = 2 * i + 1) {
            if (k + 1 < len && array[k + 1] > array[k]) {
                k++;
            }
            if (array[k] > tmp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = tmp;
    }

    //二分查找法
    public static int binarySearch(int[] array, int k) {
        int low = 0;
        int high = array.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == k) {
                return mid;
            } else if (array[mid] > k) {
                high = mid - 1;
            } else if (array[mid] < k) {
                low = mid + 1;
            }
        }
        return -1;
    }

    //快速查找算法topk
    public static int kuaiSuTopk(int[] array, int k) {
        int low = 0;
        int high = array.length - 1;
        k = k - 1;
        while (low <= high) {
            int mid = kuaiSuTopk(array, low, high);
            if (mid == k) {
                return array[mid];
            } else if (mid > k) {
                high = mid - 1;
            } else if (mid < k) {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int kuaiSuTopk(int[] array, int low, int high) {
        int aow = array[low];
        while (low < high) {
            while (low < high && array[high] <= aow) high--;
            array[low] = array[high];
            while (low < high && array[low] >= aow) low++;
            array[high] = array[low];
        }
        array[low] = aow;
        return low;
    }

    //最小堆topk
    public static int minHeapTopk(int[] array, int k) {
        int[] tmp = new int[k];
        for (int i = 0; i < k; i++) {
            tmp[i] = array[i];
        }
        for (int i = k / 2; i >= 0; i--) {
            minHeapTopkHelper(tmp, i, k);
        }
        for (int i = k; i < array.length; i++) {
            if (array[i] <= tmp[0]) continue;
            tmp[0] = array[i];
            minHeapTopkHelper(tmp, 0, k);
        }
        return tmp[0];
    }

    public static void minHeapTopkHelper(int[] array, int i, int n) {
        int tmp = array[i];
        for (int k = 2 * i + 1; k < n; k = 2 * i + 1) {
            if (k + 1 < n && array[k + 1] < array[k]) {
                k++;
            }
            if (array[k] < tmp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = tmp;
    }


    public static void maopao202211(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    public static void xuanze202211(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(array, min, i);
            }
        }
    }

    public static void charu202211(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = i;
            int val = array[i];
            while (key > 0 && array[key - 1] > val) {
                array[key] = array[key - 1];
                key--;
            }
            array[key] = val;
        }
    }

    public static void kuaisu202211(int[] array) {
        kuaisu202211helper(array, 0, array.length - 1);
    }

    public static void kuaisu202211helper(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int aow = kuaisu202211helper1(array, low, high);
        kuaisu202211helper(array, low, aow);
        kuaisu202211helper(array, aow + 1, high);
    }

    public static int kuaisu202211helper1(int[] array, int low, int high) {
        int aow = array[low];
        while (low < high) {
            while (low < high && array[high] >= aow) high--;
            array[low] = array[high];
            while (low < high && array[low] <= aow) low++;
            array[high] = array[low];
        }
        array[low] = aow;
        return low;
    }

    public static void kuaisufei202211(int[] array) {
        kuaisufei202211helper(array, 0, array.length - 1);
    }

    public static void kuaisufei202211helper(int[] array, int low, int high) {
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()) {
            int r = stack.pop();
            int l = stack.pop();
            if (l >= r) continue;
            int aow = kuaisu202211helper1(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow + 1);
            stack.push(high);
        }
    }

    public static void guibing202211(int[] array) {
        guibing202211helper(array, 0, array.length - 1);
    }

    public static void guibing202211helper(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        guibing202211helper(array, low, mid);
        guibing202211helper(array, mid + 1, high);
        guibing202211helper1(array, low, mid, high);
    }

    public static void guibing202211helper1(int[] array, int low, int mid, int high) {
        int[] tmp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            tmp[k++] = array[i] > array[j] ? array[j++] : array[i++];
        }
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= high) {
            tmp[k++] = array[j++];
        }
        for (int w = 0; w < k; w++) {
            array[w + low] = tmp[w];
        }
    }

    public static void guibingFei202211(int[] array) {
        int width = 1;
        while (width < array.length) {
            guibingFei202211Helper(array, width);
            width = 2 * width;
        }
    }

    public static void guibingFei202211Helper(int[] array, int width) {
        int start = 0;
        while (start + 2 * width - 1 < array.length) {
            guibing202211helper1(array, start, start + width - 1, start + 2 * width - 1);
            start = start + 2 * width;
        }
        if (start + width - 1 < array.length) {
            guibing202211helper1(array, start, start + width - 1, array.length - 1);
        }
    }

    public static void heap202211(int[] array) {
        for (int i = array.length / 2; i >= 0; i--) {
            heap202211helper(array, i, array.length);
        }
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            heap202211helper(array, 0, i);
        }
    }

    public static void heap202211helper(int[] array, int i, int n) {
        int tmp = array[i];
        for (int k = 2 * i + 1; k < n; k = 2 * i + 1) {
            if (k + 1 < n && array[k + 1] > array[k]) {
                k++;
            }
            if (array[k] > tmp) {
                array[i] = array[k];
                i = k;
            } else break;
        }
        array[i] = tmp;
    }

    public static int[] count202211(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int a : array) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        int[] cnt = new int[max - min + 1];
        for (int a : array) {
            cnt[a - min]++;
        }
        int sum = 0;
        for (int i = 0; i < cnt.length; i++) {
            sum = sum + cnt[i];
            cnt[i] = sum;
        }
        int[] sort = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            sort[cnt[array[i] - min] - 1] = array[i];
            cnt[array[i] - min]--;
        }
        return sort;
    }

    public static void bucket202211(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int a : array) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        int num = (max - min) / array.length + 1;
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            list.add(new LinkedList<>());
        }
        for (int a : array) {
            int idx = (a - min) / array.length;
            list.get(idx).add(a);
        }
        int idx = 0;
        for (List<Integer> list1 : list) {
            list1.sort(null);
            for (int a : list1) {
                array[idx++] = a;
            }
        }
    }

    public static void radixSort202211(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int a : array) {
            max = Math.max(max, a);
        }
        int n = 1;
        while (max / 10 != 0) {
            n++;
            max = max / 10;
        }
        for(int i=0;i<n;i++){
            List<List<Integer>> list = new LinkedList<>();
            for(int j=0;j<10;j++){
                list.add(new LinkedList<>());
            }
            for(int a: array){
                int c = (a / (int)Math.pow(10, i)) % 10;
                list.get(c).add(a);
            }
            int idx = 0;
            for(List<Integer> list1: list){
                for(Integer w: list1){
                    array[idx++] = w;
                }
            }
        }
    }

    public static void xier202211(int[] array) {
        for (int delt = array.length / 2; delt >= 1; delt = delt / 2) {
            for (int i = delt; i < array.length; i++) {
                for (int j = i; j - delt >= 0 && array[j - delt] > array[j]; j = j - delt) {
                    swap(array, j, j - delt);
                }
            }
        }
    }

    public static int binary202211(int[] array, int k) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == k) {
                return mid;
            } else if (array[mid] > k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int kuaisuxuanze202211(int[] array, int topK) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int aow = kuaisuxuanzehelper202211(array, low, high);
            if (aow == topK - 1) {
                return array[aow];
            } else if (aow > topK - 1) {
                high = aow - 1;
            } else {
                low = aow + 1;
            }
        }
        return -1;
    }

    public static int kuaisuxuanzehelper202211(int[] array, int low, int high) {
        int aow = array[low];
        while (low < high) {
            while (low < high && array[high] <= aow) high--;
            array[low] = array[high];
            while (low < high && array[low] >= aow) low++;
            array[high] = array[low];
        }
        array[low] = aow;
        return low;
    }

    public static int heapxuanze202211(int[] array, int topK) {
        int[] tmp = new int[topK];
        for (int i = 0; i < topK; i++) {
            tmp[i] = array[i];
        }
        for (int i = topK / 2; i >= 0; i--) {
            heapxuanzehelper202211(tmp, i, topK);
        }
        for (int i = topK; i < array.length; i++) {
            if (tmp[0] >= array[i]) continue;
            tmp[i] = array[i];
            heapxuanzehelper202211(tmp, 0, topK);
        }
        return tmp[0];
    }

    public static void heapxuanzehelper202211(int[] array, int i, int n) {
        int tmp = array[i];
        for (int k = 2 * i + 1; k < n; k = 2 * i + 1) {
            if (k + 1 < n && array[k + 1] < array[k]) {
                k++;
            }
            if (array[k] < tmp) {
                array[i] = array[k];
                i = k;
            } else break;
        }
        array[i] = tmp;
    }
}
