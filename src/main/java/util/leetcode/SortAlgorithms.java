package util.leetcode;


import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Stack;

/**
 * Created by songpanfei on 2019-09-23.
 */
public class SortAlgorithms {
    public static void main(String[] args){
        int[] a = new int[]{1,11,3,9,5,6,13,4};
        heap20200803(a);
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
//        System.out.println(1/2);
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
//        System.out.println( "\\u"+ Integer.toHexString(x3));
//        System.out.println( "\\u"+ Integer.toHexString(x4));
//        System.out.println( "\\u"+ Integer.toHexString(x5));
    }


    public static void maopaoSort(int[] arry){
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

    public static void xuanzeSort(int[] arry){
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

    public static void insertionSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int key = i;
            int value = arr[i];
            while (key >0&&arr[key-1]>value){
                arr[key] = arr[key-1];
                key--;
            }
            arr[key] = arr[i];
        }
    }

    public static void guibingSort(int[] array){
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

    public static void kuaisuSort(int[] array){
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

    public static void heapSort(int[] array){
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

    public static void maopao20200709(int[] array){
        for(int i= array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j] > array[j+1]){
                    flag = true;
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
            if(!flag)break;
        }
    }

    public static void xuanze20200709(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[j]<array[min]){
                    min=j;
                }
            }
            if(min!=i){
                int tmp = array[min];
                array[min] = array[i];
                array[i] = tmp;
            }
        }
    }

    public static void charu20200709(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int val = array[i];
            while (key>0&&array[key-1]>val){
                array[key] = array[key-1];
                key--;
            }
            array[key] = val;
        }
    }

    public static void kuaisu20200709(int[] array){
        kuaisu20200709helper(array, 0, array.length-1);
    }

    public static void kuaisu20200709helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200709helper2(array, low, high);
        kuaisu20200709helper(array, low, aow);
        kuaisu20200709helper(array, aow+1, high);
    }

    public static int kuaisu20200709helper2(int[] array, int low, int high){
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

    public static void kuaisu20200709feidigui(int[] array){
        kuaisu20200709feidiguihelper(array, 0, array.length-1);
    }

    public static void kuaisu20200709feidiguihelper(int[] array, int low, int high){
        if(low>=high) return;
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r)continue;
            int aow = kuaisu20200709feidiguihelper2(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(high);
        }
    }

    public static int kuaisu20200709feidiguihelper2(int[] array, int low, int high){
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


    public static void guibing20200709(int[] array){
        int[] temp = new int[array.length];
        guibing20200709helper(array, temp, 0, array.length-1);
    }

    public static void guibing20200709helper(int[] array, int[] temp, int low, int high){
        if(low>=high) return;
        int mid = low+(high-low)/2;
        guibing20200709helper(array, temp, low, mid);
        guibing20200709helper(array, temp, mid+1, high);
        guibing20200709helper2(array, temp, low, mid, high);
    }

    public static void guibing20200709helper2(int[] array, int[] temp, int low, int mid, int high){
        int i = low;
        int j = mid+1;
        int k = 0;
        while (i<=mid&&j<=high){
            temp[k++] = array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=mid){
            temp[k++]=array[i++];
        }
        while (j<=high){
            temp[k++]=array[j++];
        }
        for(int w =0;w<k;w++){
            array[low+w]=temp[w];
        }
    }

    public static void heap20200709(int[] array){
        for(int i=array.length-1;i>=0;i--){
            heap20200709heper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0]=array[i];
            array[i]=tmp;
            heap20200709heper(array, 0, i);
        }
    }

    public static void heap20200709heper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
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


    public static void maopao20200714(int[] array){
        for(int i=array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    flag = true;
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
            if(!flag) break;
        }
    }

    public static void xuanze20200714(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]>array[j]){
                    min=j;
                }
            }
            if(min != i){
                int tmp = array[min];
                array[min] = array[i];
                array[i] = tmp;
            }
        }
    }

    public static void charu20200714(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int val = array[key];
            while (key>0&&array[key-1]>val){
                array[key] = array[key-1];
                key--;
            }
            array[key]=val;
        }
    }

    public static void kuaisu20200714(int[] array){
        kuaisu20200714helper(array, 0, array.length-1);
    }

    public static void kuaisu20200714helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200714helper2(array, low, high);
        kuaisu20200714helper(array, low, aow);
        kuaisu20200714helper(array, aow+1, high);
    }

    public static int kuaisu20200714helper2(int[] array, int low, int high){
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

    public static void kuaisu20200714feidigui(int[] array){
        kuaisu20200714helperfeidigui(array, 0, array.length-1);
    }

    public static void kuaisu20200714helperfeidigui(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r) continue;
            int aow = kuaisu20200714helper2feidigui(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(high);
        }
    }

    public static int kuaisu20200714helper2feidigui(int[] array, int low, int high){
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

    public static void guibing20200714(int[] array){
        int[] tmp = new int[array.length];
        guibing20200714helper(array, tmp, 0, array.length-1);
    }

    public static void guibing20200714helper(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int mid=low+(high-low)/2;
        guibing20200714helper(array, tmp, low, mid);
        guibing20200714helper(array, tmp, mid+1, high);
        guibing20200714helper2(array, tmp, low, mid, high);
    }

    public static void guibing20200714helper2(int[] array, int[] tmp, int low, int mid, int high){
        int i = low;
        int j = mid+1;
        int k = 0;
        while (i<=mid&&j<=high){
            tmp[k++]=array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=mid){
            tmp[k++]=array[i++];
        }
        while (j<=high){
            tmp[k++]=array[j++];
        }
        for(int w =0;w<k;w++){
            array[low+w]=tmp[w];
        }
    }

    public static void heap20200714(int[] array){
        for(int i=array.length-1;i>=0;i--){
            heap20200714helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0] = array[i];
            array[i]=tmp;
            heap20200714helper(array, 0, i);
        }
    }

    public static void heap20200714helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
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

    public static void maopao20200721(int[] array){
        for(int i=array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>=array[j+1]){
                    int tmp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                    flag = true;
                }
            }
            if(!flag) break;
        }
    }
    public static void xuanze20200721(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]>=array[j]){
                    min = j;
                }
            }
            if(min!=i){
                int tmp = array[min];
                array[min]=array[i];
                array[i]=tmp;
            }
        }
    }
    public static void charu20200721(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int val = array[i];
            while (key>0&&array[key-1]>=val){
                array[key]=array[key-1];
                key--;
            }
            if(i!=key){
                array[key]=val;
            }
        }
    }
    public static void kuaisu20200721(int[] array){
        kuaisu20200721helper(array, 0, array.length-1);
    }
    public static void kuaisu20200721helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200721helper2(array, low, high);
        kuaisu20200721helper(array, low, aow);
        kuaisu20200721helper(array, aow+1, high);
    }
    public static int kuaisu20200721helper2(int[] array, int low, int high){
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
    public static void kuaisu20200721Fei(int[] array){
        kuaisu20200721FeiHelper(array, 0, array.length-1);
    }
    public static void kuaisu20200721FeiHelper(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r) continue;
            int aow = kuaisu20200721FeiHelper2(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(r);
        }
    }
    public static int kuaisu20200721FeiHelper2(int[] array, int low, int high){
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
    public static void guibing20200721(int[] array){
        int[] tmp = new int[array.length];
        guibing20200721Helper(array, tmp, 0, array.length-1);
    }
    public static void guibing20200721Helper(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int mid = low+(high-low)/2;
        guibing20200721Helper(array, tmp, low, mid);
        guibing20200721Helper(array, tmp, mid+1, high);
        guibing20200721Helper2(array, tmp, low, mid, high);
    }
    public static void guibing20200721Helper2(int[] array, int[] tmp, int low, int mid, int high){
        int i= low;
        int j = mid+1;
        int k=0;
        while (i<=mid&&j<=high){
            tmp[k++]=array[i]>=array[j]?array[j++]:array[i++];
        }
        while (i<=mid){
            tmp[k++] = array[i++];
        }
        while (j<=high){
            tmp[k++] = array[j++];
        }
        for(int w =0;w<k;w++){
            array[low+w] = tmp[w];
        }
    }
    public static void heap20200721(int[] array){
        for(int i=array.length-1;i>=0;i--){
            heap20200721Helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0] = array[i];
            array[i]=tmp;
            heap20200721Helper(array, 0, i);
        }
    }
    public static void heap20200721Helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
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




    public static void maopao20200729(int[] array){
        for(int i=array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    int tmp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                    flag=true;
                }
            }
            if(!flag)break;
        }
    }
    public static void xuanze20200729(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min  = i;
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
    public static void charu20200729(int[] array){
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

    public static void kuaisu20200729(int[] array){
        kuaisu20200729helper(array, 0, array.length-1);
    }
    public static void kuaisu20200729helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200729helper2(array, low, high);
        kuaisu20200729helper(array, low, aow);
        kuaisu20200729helper(array, aow+1, high);
    }
    public static int kuaisu20200729helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&array[high]>aow) high--;
            array[low]=array[high];
            while (low<high&&array[low]<aow) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }

    public static void kuaisu20200729Fei(int[] array){
        kuaisu20200729FeiHelper(array, 0, array.length-1);
    }
    public static void kuaisu20200729FeiHelper(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r) continue;
            int aow = kuaisu20200729FeiHelper2(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(r);
        }
    }
    public static int kuaisu20200729FeiHelper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&array[high]>aow) high--;
            array[low]=array[high];
            while (low<high&&array[low]<aow) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }

    public static void guibing20200729(int[] array){
        int[] tmp = new int[array.length];
        guibing20200729Helper(array, tmp, 0, array.length-1);
    }
    public static void guibing20200729Helper(int[] array, int[] tmp ,int low, int high){
        if(low>=high)return;
        int mid = low+(high-low)/2;
        guibing20200729Helper(array, tmp, low, mid);
        guibing20200729Helper(array, tmp, mid+1, high);
        guibing20200729Helper2(array, tmp, low, mid, high);
    }
    public static void guibing20200729Helper2(int[] array, int[] tmp ,int low, int mid,  int high){
        int i=low;
        int j = mid+1;
        int k = 0;
        while (i<=mid&&j<=high){
            tmp[k++] = array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=mid){
            tmp[k++] = array[i++];
        }
        while (j<=high){
            tmp[k++] = array[j++];
        }
        for(int w =0;w<k;w++){
            array[w+low] = tmp[w];
        }
    }

    public static void heap20200729(int[] array){
        for(int i=array.length-1;i>=0;i--){
            heap20200729helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0]=array[i];
            array[i]=tmp;
            heap20200729helper(array, 0, i);
        }
    }

    public static void heap20200729helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
            if(k+1<n&&array[k]<array[k+1]){
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





    public static void maopao20200803(int[] array){
        for(int i= array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    flag = true;
                    int tmp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                }
            }
            if(!flag) break;
        }
    }
    public static void xuanze20200803(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]>array[j]){
                    min=j;
                }
            }
            if(min!=i){
                int tmp = array[i];
                array[i]=array[min];
                array[min]=tmp;
            }
        }
    }
    public static void charu20200803(int[] array){
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
    public static void kuaisu20200803(int[] array){
        kuaisu20200803Helper(array, 0, array.length-1);
    }
    public static void kuaisu20200803Helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200803Helper2(array, low, high);
        kuaisu20200803Helper(array, low, aow);
        kuaisu20200803Helper(array, aow+1, high);
    }
    public static int kuaisu20200803Helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&array[high]>=aow)high--;
            array[low]=array[high];
            while (low<high&&array[low]<=aow) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }
    public static void kuaisu20200803Fei(int[] array){
        kuaisu20200803Helper(array, 0, array.length-1);
    }
    public static void kuaisu20200803HelperFei(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r) continue;
            int aow = kuaisu20200803Helper2Fei(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(r);
        }
    }
    public static int kuaisu20200803Helper2Fei(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&array[high]>=aow)high--;
            array[low]=array[high];
            while (low<high&&array[low]<=aow) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }
    public static void guibing20200803(int[] array){
        int[] tmp = new int[array.length];
        guibing20200803helper(array, tmp, 0, array.length-1);
    }
    public static void guibing20200803helper(int[] array, int[] tmp, int low, int high){
        if(low>=high)return;
        int mid = low+(high-low)/2;
        guibing20200803helper(array, tmp, low, mid);
        guibing20200803helper(array, tmp, mid+1, high);
        guibing20200803helper2(array, tmp, low, mid, high);
    }
    public static void guibing20200803helper2(int[] array, int[] tmp, int low, int mid, int high){
        int i = low;
        int j = mid+1;
        int k = 0;
        while (i<=mid&&j<=high){
            tmp[k++]=array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=mid){
            tmp[k++]=array[i++];
        }
        while (j<=high){
            tmp[k++]=array[j++];
        }
        for (int w =0;w<k;w++){
            array[low+w]=tmp[w];
        }
    }
    public static void heap20200803(int[] array) {
        for(int i=array.length/2-1;i>=0;i--){
            heap20200803Helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0]=array[i];
            array[i]=tmp;
            heap20200803Helper(array, 0, i);
        }
    }
    public static void heap20200803Helper(int[] array, int i, int n) {
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
            if(k+1<n&&array[k+1]>array[k]){
                k++;
            }
            if(array[k]>=tmp){
                array[i]=array[k];
                i=k;
            }else {break;}
        }
        array[i]=tmp;
    }


























    }
