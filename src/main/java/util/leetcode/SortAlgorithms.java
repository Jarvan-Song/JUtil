package util.leetcode;


import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by songpanfei on 2019-09-23.
 */
public class SortAlgorithms {
    public static void main(String[] args){
        int[] w = new int[]{1,11,3,9,5,6,13,4};
        System.out.println(kuaisuxuanze20201124(w,9));
        System.out.println(heapxuanze20201124(w,8));

        int[] a2 = new int[]{1,3,4,5,6,9,11,13};
        SortAlgorithms dst = new SortAlgorithms();
        String[] methods = new String[]{"maopao", "xuanze", "charu", "kuaisu","kuaisufei", "guibing", "heap",  "xier", "count"};
        String suffix = "20201124";
        boolean flag = true;
        for(String meta : methods){
            int[] a = new int[]{1,11,3,9,5,6,13,4};
            try {
                String methodName = meta + suffix;
                Method method = SortAlgorithms.class.getMethod(methodName, int[].class);
                if(meta.equals("count")){
                    a = (int[])method.invoke(dst, a);
                }else {
//                    System.out.println(meta);
                    method.invoke(dst, a);
                }
                for(int i=0;i<a.length;i++){
                    if(a[i] != a2[i]){
                        System.out.println(meta + " error");
                        flag = false;
                        break;
                    }
                }
            }catch (Exception e){
                System.out.println(meta + " " + e);
                flag = false;
            }
        }
        if(flag) System.out.println("success");
        else System.out.println("error");
//        int[] a = new int[]{1,11,3,9,5,6,13,4};
//        charu(a);
//        for(int i=0;i<a.length;i++){
//                System.out.println(a[i]);
//        }
//        System.out.println(a[2]);
//        Splitter splitter = Splitter.on(",").trimResults(CharMatcher.is('_')).omitEmptyStrings();
//        for(String meta: splitter.split("1,2,3,null")){
//            System.out.println(meta);
//        }
//        Joiner joiner = Joiner.on(",").skipNulls();
//        System.out.println(joiner.join(Lists.newArrayList("b","c","1")));
//        CharMatcher charMatcher = CharMatcher.DIGIT;
//        System.out.println(charMatcher.retainFrom("1231231lfsjedj1122"));
//        System.out.println(charMatcher.removeFrom("1231231lfsjedj1122"));
//        char x1 = 'ç';
//        char x2 = '¼';
//        char x3 = '\u0096';
//        char x4 = 'è';
//        char x5 = 's';
//        Character.UnicodeBlock ub = Character.UnicodeBlock.of(x5);
//        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
//                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
//                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
//                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C
//                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D
//                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
//                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
//                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT
//                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
//                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
//                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
//                || ub == Character.UnicodeBlock.VERTICAL_FORMS){
//            System.out.println("ssssssssss");
//        }
//        System.out.println( "\\u"+ Integer.toHexString(x1));
//        System.out.println( "\\u"+ Integer.toHexString(x2));
//        System.out.println( "\\u"+ Integer.toHexString(x3));空中浩劫
//        System.out.println( "\\u"+ Integer.toHexString(x4));
//        System.out.println( "\\u"+ Integer.toHexString(x5));
    }


    public static void maopao(int[] arry){
        for(int i=arry.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(arry[j] > arry[j+1]){
                    flag = true;
                    int tem = arry[j];
                    arry[j] = arry[j+1];
                    arry[j+1] = tem;
                }
            }
            if(!flag)break;
        }
    }

    public static void xuanze(int[] arry){
        for(int i = 0; i<arry.length-1;i++){
            int min = i;
            for(int j=i+1;j<arry.length;j++){
                if(arry[j]<arry[min]){
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

    public static void charu(int[] arr){
        for(int i=1;i<arr.length;i++){
            int key = i;
            int value = arr[i];
            while (key >0&&arr[key-1]>value){
                arr[key] = arr[key-1];
                key--;
            }
//            arr[key] = arr[i];
            arr[key] = value;
        }
    }

    public static void guibing(int[] array){
        int[] tmp = new int[array.length];
        guibing(array, tmp, 0, array.length-1);
    }

    public static void guibing(int[] array, int[] tmp, int low, int high){
        if(low >=high) return;
        int middle = low +(high-low)/2;
        guibing(array, tmp, low, middle);
        guibing(array, tmp,middle+1, high);
        guibingHelper(array, tmp, low, middle, high);
    }

    public static void guibingHelper(int[] array, int[] tmp, int low, int middle, int high){
        int i = low;
        int j = middle + 1;
        int k = 0;
        while (i<=middle && j<=high){
            tmp[k++] = array[i] < array[j] ? array[i++] : array[j++];
        }
        while (i<=middle){
            tmp[k++] = array[i++];
        }
        while (j<=high){
            tmp[k++] = array[j++];
        }
        for(int w = 0;w<k;w++){
            array[low + w] = tmp[w];
        }
    }

    public static void kuaisu(int[] array){
        kuaisu(array, 0, array.length-1);
    }

    public static void kuaisu(int[] array, int low, int high){
        if(low >= high) return;
        int avr = kuaisuHelper(array, low, high);
        kuaisu(array, low, avr-1);
        kuaisu(array, avr + 1, high);
    }

    public static int kuaisuHelper(int[] array, int low, int high){
        int p = array[low];
        while (low < high){
            while (low < high && array[high] >= p) high--;
            array[low] = array[high];
            while (low < high && array[low] <= p) low++;
            array[high]  = array[low];
        }
        array[low] = p;
        return low;
    }

    public static void kuaisufei(int[] array){
        kuaisuFeihelper(array, 0, array.length-1);
    }

    public static void kuaisuFeihelper(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r) continue;
            int aow = kuaisuHelper(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(r);
        }
    }


    public static void heap(int[] array){
        for(int i= array.length/2-1;i>=0;i--){
            heapHelper(array, i, array.length);
        }

        for(int i= array.length-1;i>0;i--){
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            heapHelper(array, 0, i);
        }
    }

    public static void heapHelper(int[] array, int i, int len){
        int tmp = array[i];
        for(int k= 2*i+1;k<len;k=2*i+1){
            if(k+1<len&&array[k+1]>array[k]){
                k++;
            }
            if(array[k]>tmp){
                array[i] = array[k];
                i = k;
            }else {
                break;
            }
        }
        array[i] = tmp;
    }

    public static int[] count(int[] array){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<array.length;i++){
            int meta = array[i];
            max = Math.max(max, meta);
            min = Math.min(min, meta);
        }
        int[] countArray = new int[max-min+1];
        Arrays.fill(countArray, 0);
        for(int i=0;i<array.length;i++){
            countArray[array[i]-min]++;
        }
        int sum = 0;
        for(int i=0;i<countArray.length;i++){
            sum = sum+countArray[i];
            countArray[i]=sum;
        }
        int[] res = new int[array.length];
        for(int i=array.length-1;i>=0;i--){
            res[countArray[array[i]-min]-1] = array[i];
            countArray[array[i]-min]--;
        }
        array = res;
        return res;
    }

    public static void xier(int[] arr){
        int temp;
        for (int delta = arr.length/2; delta>=1; delta/=2){                              //对每个增量进行一次排序
            for (int i=delta; i<arr.length; i++){
                for (int j=i; j-delta>=0 && arr[j]<arr[j-delta]; j-=delta){ //注意每个地方增量和差值都是delta
                    temp = arr[j-delta];
                    arr[j-delta] = arr[j];
                    arr[j] = temp;
                }
            }//loop i
        }//loop delta
    }


    //二分查找法
    public static int binarySearch(int[] array, int k){
        int low = 0;
        int high = array.length;
        while (low<high){
            int mid = low+(high-low)/2;
            if(array[mid] == k){
                return mid;
            }else if(array[mid]>k){
                high = mid-1;
            }else if(array[mid]<k){
                low = mid+1;
            }
        }
        return -1;
    }

    //快速查找算法topk
    public static int kuaiSuTopk(int[] array, int k){
        int low = 0;
        int high = array.length-1;
        k = k-1;
        while (low<=high){
            int mid = kuaiSuTopk(array, low, high);
            if(mid == k) {
                return array[mid];
            }else if(mid > k) {
                high = mid-1;
            }else if(mid < k){
                low = mid +1;
            }
        }
        return -1;
    }

    public static int kuaiSuTopk(int[] array, int low, int high){
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

    //最小堆topk
    public static int minHeapTopk(int[] array, int k){
        int[] tmp = new int[k];
        for(int i=0;i<k;i++){
            tmp[i] = array[i];
        }
        for(int i=k/2;i>=0;i--){
            minHeapTopkHelper(tmp, i, k);
        }
        for(int i=k;i<array.length;i++){
            if(array[i]<=tmp[0]) continue;
            tmp[0] = array[i];
            minHeapTopkHelper(tmp, 0, k);
        }
        return tmp[0];
    }
    public static void minHeapTopkHelper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
            if(k+1<n&&array[k+1]<array[k]){
                k++;
            }
            if(array[k]<tmp){
                array[i] = array[k];
                i = k;
            }else {
                break;
            }
        }
        array[i] = tmp;
    }


    public static void maopao20201116(int[] array){
        for(int i=array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    flag=true;
                    int tmp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                }
            }
            if(!flag) break;
        }
    }
    public static void xuanze20201116(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]>array[j]){
                    min=j;
                }
            }
            if(min!=i){
                int tmp = array[min];
                array[min]=array[i];
                array[i]=tmp;
            }
        }
    }
    public static void charu20201116(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int val = array[i];
            while (key>0&&array[key-1]>val){
                array[key]=array[key-1];
                key--;
            }
            array[key]=val;
        }
    }
    public static void kuaisu20201116(int[] array){
        kuaisu20201116helper(array, 0, array.length-1);
    }
    public static void kuaisu20201116helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20201116helper2(array, low, high);
        kuaisu20201116helper(array, low, aow);
        kuaisu20201116helper(array, aow+1, high);
    }
    public static int kuaisu20201116helper2(int[] array, int  low, int high){
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
    public static void kuaisufei20201116(int[] array){
        kuaisuFei20201116helper(array, 0, array.length-1);
    }
    public static void kuaisuFei20201116helper(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if (l>=r) continue;
            int aow = kuaisu20201116helper2(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(r);
        }
    }

    public static void guibing20201116(int[] array){
        int[] tmp = new int[array.length];
        guibing20201116helper(array, tmp, 0, array.length-1);
    }
    public static void guibing20201116helper(int[] array,int[] tmp, int low, int high){
        if(low>=high) return;
        int mid = low+(high-low)/2;
        guibing20201116helper(array, tmp, low, mid);
        guibing20201116helper(array,  tmp,mid+1, high);
        guibing20201116helper2(array, tmp, low, mid, high);
    }
    public static void guibing20201116helper2(int[] array, int[] tmp, int  low, int mid, int high){
        int i= low;
        int j= mid+1;
        int k=0;
        while (i<=mid&&j<=high){
            tmp[k++]=array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=mid){
            tmp[k++]=array[i++];
        }
        while (j<=high){
            tmp[k++]=array[j++];
        }
        for(int w=0;w<k;w++){
            array[low+w]=tmp[w];
        }
    }

    public static void heap20201116(int[] array){
        for(int i=array.length/2;i>=0;i--){
            heap20201116helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            heap20201116helper(array, 0, i);
        }
    }
    public static void heap20201116helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
            if(k+1<n&&array[k+1]>array[k]){k++;}
            if(array[k]>tmp){
                array[i]=array[k];
                i=k;
            }else {break;}
        }
        array[i]=tmp;
    }

    public static int[] count20201116(int[] array){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int a: array){
            max = Math.max(max, a);
            min = Math.min(min, a);
        }
        int[] cnt = new int[max-min+1];
        Arrays.fill(cnt,0);
        for(int i=0;i<array.length;i++){
            cnt[array[i]-min]++;
        }
        int sum = 0;
        for(int i=0;i<cnt.length;i++){
            sum += cnt[i];
            cnt[i] = sum;
        }
        int[] sort = new int[array.length];
        for(int i=array.length-1;i>=0;i--){
            sort[cnt[array[i]-min]-1] = array[i];
            cnt[array[i]-min]--;
        }
        return sort;
    }
    public static void xier20201116(int[] array){
        for(int delt = array.length/2;delt>=1;delt=delt/2){
            for(int i=delt;i<array.length;i++){
                for(int j = i;j-delt>=0&&array[j-delt]>array[j];j=j-delt){
                    int tmp = array[j];
                    array[j]=array[j-delt];
                    array[j-delt]=tmp;
                }
            }
        }
    }
    public static int binary(int[] array, int k){
        int low = 0;
        int hih = array.length;
        while (low<hih){
            int mid = low+(hih-low)/2;
            if(k==array[mid]){
                return mid;
            }else if(k>array[mid]){
                low = mid+1;
            }else if(k<array[mid]){
                hih = mid-1;
            }
        }
        return -1;
    }

    public static int kuaisuxuanze(int[] array, int topK){
        int low = 0;
        int high = array.length-1;
        while (low<=high){
            int aow = kuaisuxuanzehelper(array, low, high);
            if(topK-1 == aow){
                return array[topK-1];
            }else if(topK-1<aow){
                high = aow-1;
            }else if(topK-1>aow){
                low = aow+1;
            }
        }
        return -1;
    }
    public static int kuaisuxuanzehelper(int[] array, int low, int high){
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
    public static int heapxuanze(int[] array, int topK){
        int[] tmp = new int[topK];
        for(int i=0;i<topK;i++){
            tmp[i] = array[i];
        }
        for(int i=tmp.length/2;i>=0;i--){
            heapxuanzehelper(array, i, tmp.length);
        }
        for(int i= topK;i<array.length;i++){
            if(array[i]<=tmp[0])continue;
            tmp[0] = array[i];
            heapxuanzehelper(tmp, 0, tmp.length);
        }
        return tmp[0];
    }
    public static void heapxuanzehelper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
            if(k+1<n&&array[k+1]<array[k]){
                k++;
            }
            if(array[k]<tmp){
                array[i] = array[k];
                i=k;
            }else {
                break;
            }
        }
        array[i]=tmp;
    }




    public static void maopao20201124(int[] array){
        for(int i=array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    flag=true;
                    int tmp = array[j];
                    array[j]=array[j+1];
                    array[j+1] = tmp;
                }
            }
            if(!flag) break;
        }
    }
    public static void xuanze20201124(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]>array[j]){
                    min=j;
                }
            }
            if(min!=i){
                int tmp = array[min];
                array[min]=array[i];
                array[i]=tmp;
            }
        }
    }
    public static void charu20201124(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int val = array[i];
            while (key>0&&array[key-1]>val){
                array[key]=array[key-1];
                key--;
            }
            array[key]=val;
        }
    }
    public static void kuaisu20201124(int[] array){
        kuaisu20201124helper(array, 0, array.length-1);
    }
    public static void kuaisu20201124helper(int[] array, int low, int high){
        if(low>=high)return;
        int aow =kuaisu20201124helper1(array, low, high);
        kuaisu20201124helper(array, low, aow);
        kuaisu20201124helper(array, aow+1, high);
    }
    public static int kuaisu20201124helper1(int[] array, int low, int high){
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
    public static void kuaisufei20201124(int[] array){
        kuaisufei20201124helper(array, 0, array.length-1);
    }
    public static void kuaisufei20201124helper(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r) continue;
            int aow = kuaisu20201124helper1(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(high);
        }
    }
    public static void guibing20201124(int[] array){
        int[] tmp = new int[array.length];
        guibing20201124helper(array, tmp, 0, array.length-1);
    }
    public static void guibing20201124helper(int[] array, int[] tmp, int low, int high){
        if(low>=high)return;
        int mid = low+(high-low)/2;
        guibing20201124helper(array, tmp, low, mid);
        guibing20201124helper(array, tmp, mid+1, high);
        guibing20201124helper1(array, tmp, low, mid, high);
    }
    public static void guibing20201124helper1(int[] array, int[] tmp, int low, int mid,  int high){
        int i= low;
        int j = mid+1;
        int k=0;
        while (i<=mid&&j<=high){
            tmp[k++]=array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=mid){
            tmp[k++]=array[i++];
        }
        while (j<=high){
            tmp[k++]=array[j++];
        }
        for(int w=0;w<k;w++){
            array[low+w]=tmp[w];
        }
    }
    public static void heap20201124(int[] array){
        for(int i=array.length/2;i>=0;i--){
            heap20201124helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            heap20201124helper(array, 0, i);
        }
    }
    public static void heap20201124helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k = 2*i+1;k<n;k=2*i+1){
            if(k+1<n&&array[k+1]>array[k]){
                k++;
            }
            if(array[k]>tmp){
                array[i]=array[k];
                i=k;
            }else {
                break;
            }
        }
        array[i]=tmp;
    }

    public static int[] count20201124(int[] array){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int a: array){
            min = Math.min(a, min);
            max = Math.max(a, max);
        }
        int[] cnt = new int[max-min+1];
        for(int i=0;i<array.length;i++){
            cnt[array[i]-min]++;
        }
        int sum = 0;
        for(int i=0;i<cnt.length;i++){
            sum += cnt[i];
            cnt[i]=sum;
        }
        int[] sort = new int[array.length];
        for(int i=array.length-1;i>=0;i--){
            sort[cnt[array[i]-min]-1] = array[i];
            cnt[array[i]-min]--;
        }
        return sort;
    }
    public static void xier20201124(int[] array){
        for(int delet= array.length/2;delet>=1;delet=delet/2){
            for(int i=delet;i<array.length;i++){
                for(int j=i;j-delet>=0&&array[j-delet]>array[j];j=j-delet){
                    int tmp = array[j];
                    array[j]=array[j-delet];
                    array[j-delet]=tmp;
                }
            }
        }
    }

    public static int binary20201124(int[] array, int k){
        if(k<array[0]||k>array[array.length-1]) return -1;
        int low = 0;
        int high = array.length-1;
        while (low<=high){
            int mid = low+(high-low)/2;
            if(array[mid]==k){
                return mid;
            }else if(array[mid]>k){
                high=mid-1;
            }else if(array[mid]<k){
                low=mid+1;
            }
        }
        return -1;
    }

    public static int kuaisuxuanze20201124(int[] array, int topK){
        if(topK<0||topK>array.length) return -1;
        int low =0;
        int high = array.length-1;
        while (low<=high){
            int aow = kuaisuxuanzehelper20201124(array, low, high);
            if(topK-1 ==aow){
                return array[aow];
            }else if(topK-1<aow){
                high=aow-1;
            }else if(topK-1>aow){
                low=aow+1;
            }
        }
        return -1;
    }
    public static int kuaisuxuanzehelper20201124(int[] array, int low, int high){
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
    public static int heapxuanze20201124(int[] array, int topK){
        if(topK<0||topK>array.length) return -1;
        int[] tmp = new int[topK];
        for(int i=0;i<topK;i++){
            tmp[i]=array[i];
        }
        for(int i=topK;i<array.length;i++){
            if(array[i]<=tmp[0]) continue;
            tmp[0]=array[i];
            heapxuanzehelper20201124(array, 0, topK);
        }
        return tmp[topK-1];
    }
    public static void heapxuanzehelper20201124(int[] array, int i, int n){
        int tmp = array[i];
        for(int k = 2*i+1;k<n;k=2*i+1){
            if(k+1<n&&array[k+1]<array[k]){
                k++;
            }
            if(array[k]<tmp){
                array[i]=array[k];
                i=k;
            }else {
                break;
            }
        }
        array[i]=tmp;
    }
























}
