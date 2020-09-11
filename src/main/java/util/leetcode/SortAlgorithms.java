package util.leetcode;


import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

/**
 * Created by songpanfei on 2019-09-23.
 */
public class SortAlgorithms {
    public static void main(String[] args){
        int[] a = new int[]{1,11,3,9,5,6,13,4};
        heap20200907(a);
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


    public static void maopao20200810(int[] array){
        for(int i=array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    flag = true;
                }
            }
            if(!flag) break;
        }
    }
    public static void xuanze20200810(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j = i+1;j<array.length;j++){
                if(array[min]>array[j]){
                    min = j;
                }
            }
            if(min!=i){
                int tmp = array[min];
                array[min] = array[i];
                array[i] = tmp;
            }
        }
    }
    public static void charu20200810(int[] array){
        for(int i= 1;i<array.length;i++){
            int key = i;
            int val = array[i];
            while (key>0&&array[key-1]>val){
                array[key] = array[key-1];
                key--;
            }
            array[key]=val;
        }
    }
    public static void kuaisu20200810(int[] array){
        kuaisu20200810Helper(array, 0, array.length-1);
    }
    public static void kuaisu20200810Helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200810Helper2(array, low, high);
        kuaisu20200810Helper(array, low, aow);
        kuaisu20200810Helper(array, aow+1, high);
    }
    public static int kuaisu20200810Helper2(int[] array, int low, int high){
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
    public static void kuaisu20200810Fei(int[] array){
        kuaisu20200810FeiHelper(array, 0, array.length-1);
    }
    public static void kuaisu20200810FeiHelper(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r) continue;
            int aow = kuaisu20200810Helper2(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(r);
        }
    }
    public static void guibing20200810(int[] array){
        int[] tmp = new int[array.length];
        guibing20200810Helper(array, tmp, 0, array.length-1);
    }
    public static void guibing20200810Helper(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int mid = low + (high-low)/2;
        guibing20200810Helper(array,tmp, low, mid);
        guibing20200810Helper(array,tmp, mid+1, high);
        guibing20200810Helper2(array,tmp, low, mid, high);
    }
    public static void guibing20200810Helper2(int[] array, int[] tmp, int low, int mid, int high){
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
        for(int w=0;w<k;w++){
            array[low+w]=tmp[w];
        }
    }
    public static void heap20200810(int[] array){
        for(int i=array.length/2;i>=0;i--){
            heap20200810Helper(array, i, array.length);
        }
        for(int i = array.length-1;i>0;i--){
            int tmp = array[i];
            array[i]=array[0];
            array[0]=tmp;
            heap20200810Helper(array, 0, i);
        }
    }
    public static void heap20200810Helper(int[] array, int i, int n){
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

    public static void maopao20200817(int[] array){
        for(int i= array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    int tmp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                    flag = true;
                }
            }
            if(!flag)break;
        }
    }


    public static void xuanze20200817(int[] array){
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

    public static void charu20200817(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int val = array[i];
            while (key>0&&array[key-1]>=val){
                array[key]=array[key-1];
                key--;
            }
            array[key]=val;
        }
    }

    public static void kuaisu20200817(int[] array){
        kuaisu20200817Helper(array, 0, array.length-1);
    }

    public static void kuaisu20200817Helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200817Helper2(array, low, high);
        kuaisu20200817Helper(array, low, aow);
        kuaisu20200817Helper(array, low+1, high);
    }
    public static int kuaisu20200817Helper2(int[] array, int low, int high){
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

    public static void kuaisu20200817Fei(int[] array){
        kuaisu20200817FeiHelper(array, 0, array.length-1);
    }

    public static void kuaisu20200817FeiHelper(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r) continue;
            int aow = kuaisu20200817FeiHelper2(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(r);
        }
    }
    public static int kuaisu20200817FeiHelper2(int[] array, int low, int high){
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

    public static void guibing20200817(int[] array){
        int[] tmp = new int[array.length];
        guibing20200817Helper(array, tmp, 0, array.length-1);
    }
    public static void guibing20200817Helper(int[] array, int[] tmp, int low, int high){
        if(low>=high)return;
        int mid = low+(high-low)/2;
        guibing20200817Helper(array, tmp, low, mid);
        guibing20200817Helper(array, tmp, mid+1, high);
        guibing20200817Helper2(array, tmp, low, mid, high);
    }
    public static void guibing20200817Helper2(int[] array, int[] tmp, int low, int mid, int high){
        int i=low;
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

    public static void heap20200817(int[] array){
        for(int i =array.length/2;i>=0;i--){
            heap20200817Helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0]=array[i];
            array[i]=tmp;
            heap20200817Helper(array, 0, i);
        }
    }

    public static void heap20200817Helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k = 2*i+1;k<n;k=2*i+1){
            if(k+1<n&&array[k+1]>array[k]){
                k++;
            }
            if(array[k]>=tmp){
                array[i]=array[k];
                i=k;
            }else {
                break;
            }
        }
        array[i]=tmp;
    }










    public static void maopao20200824(int[] array){
        for(int i=array.length-1;i>0;i--){
            boolean flag =false;
            for(int j=0;j<i;j++){
                flag=true;
                if(array[j]>array[j+1]){
                    int tmp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                }
            }
            if(!flag) break;
        }
    }
    public static void xuanze20200824(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i;j<array.length;j++){
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
    public static void charu20200824(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int val = array[i];
            while (key>0&&array[key-1]>=val){
                array[key]=array[key-1];
                key--;
            }
            array[key]=val;
        }
    }
    public static void kuaisu20200824(int[] array){
        kuaisu20200824helper(array, 0, array.length-1);
    }
    public static void kuaisu20200824helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200824helper2(array, low, high);
        kuaisu20200824helper(array, low, aow);
        kuaisu20200824helper(array, aow+1, high);
    }
    public static int kuaisu20200824helper2(int[] array, int low, int high){
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

    public static void kuaisu20200824Fei(int[] array){
        kuaisu20200824helperFei(array, 0, array.length-1);
    }
    public static void kuaisu20200824helperFei(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r) continue;
            int aow = kuaisu20200824helper2Fei(array,l,r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(r);
        }
    }
    public static int kuaisu20200824helper2Fei(int[] array, int low, int high){
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

    public static void guibing20200824(int[] array){
        int[] tmp = new int[array.length];
        guibing20200824helper(array, tmp, 0, array.length-1);
    }
    public static void guibing20200824helper(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int mid = low+(high-low)/2;
        guibing20200824helper(array, tmp, low, mid);
        guibing20200824helper(array, tmp,  mid+1, high);
        guibing20200824helper2(array, tmp, low, mid, high);
    }
    public static void guibing20200824helper2(int[] array, int[] tmp, int low, int mid, int high){
        int i=low;
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

    public static void heap20200824(int[] array){
        for(int i=array.length/2;i>=0;i--){
            heap20200824helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0]=array[i];
            array[i]=tmp;
            heap20200824helper(array, 0, i);
        }

    }
    public static void heap20200824helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
            if(k+1<n&&array[k+1]>array[k]) k++;
            if(array[k]>tmp){
                array[i]=array[k];
                i=k;
            }else break;
        }
        array[i]=tmp;
    }

    public static void maopao20200831(int[] array){
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

    public static void xuanze20200831(int[] array){
        for(int i= 0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]>array[j]){
                    min=j;
                }
            }
            if(min!=i){
                int tmp = array[min];
                array[min] = array[i];
                array[i]=tmp;
            }
        }
    }

    public static void charu20200831(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int val = array[i];
            while (key>0&&array[key-1]>=val){
                array[key]=array[key-1];
                key--;
            }
            array[key]=val;
        }
    }

    public static void kuaisu20200831(int[] array){
        kuaisu20200831helper(array, 0, array.length-1);
    }
    public static void kuaisu20200831helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200831helper2(array, low, high);
        kuaisu20200831helper(array, low, aow);
        kuaisu20200831helper(array, aow+1, high);
    }
    public static int kuaisu20200831helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&array[high]>=aow) high--;
            array[low]=array[high];
            while (low<high&&array[low]<=aow) low++;
            array[high]=array[low];
        }
        array[low] = aow;
        return low;
    }

    public static void kuaisu20200831fei(int[] array){
        kuaisu20200831feihelper(array, 0, array.length-1);
    }
    public static void kuaisu20200831feihelper(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r) continue;
            int aow = kuaisu20200831feihelper2(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(r);
        }
    }
    public static int kuaisu20200831feihelper2(int[] array,int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&array[high]>=aow) high--;
            array[low]=array[high];
            while (low<high&&array[low]<=aow) low++;
            array[high]=array[low];
        }
        array[low] = aow;
        return low;
    }

    public static void guibing20200831(int[] array){
        int[] tmp = new int[array.length];
        guibing20200831helper(array, tmp, 0, array.length-1);
    }
    public static void guibing20200831helper(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int mid = low+(high-low)/2;
        guibing20200831helper(array,tmp,low,mid);
        guibing20200831helper(array,tmp,mid+1,high);
        guibing20200831helper2(array,tmp,low,mid,high);
    }
    public static void guibing20200831helper2(int[] array, int[] tmp , int low, int mid, int high){
        int i = low;
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
        for(int w = 0;w<k;w++){
            array[low+w]=tmp[w];
        }
    }

    public static void heap20200831(int[] array){
        for(int i=array.length/2;i>=0;i--){
            heap20200831helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0]=array[i];
            array[i]=tmp;
            heap20200831helper(array, 0, i);
        }
    }

    public static void heap20200831helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
            if(k+1<n&&array[k+1]>array[k]){
                k++;
            }
            if(array[k]>=tmp){
                array[i]=array[k];
                i=k;
            }else {
                break;
            }
        }
        array[i]=tmp;
    }




    public static void maopao20200907(int[] array){
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

    public static void xuanze20200907(int[] array){
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
    public static void charu20200907(int[] array){
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

    public static void kuaisu20200907(int[] array){
        kuaisu20200907helper(array, 0, array.length-1);
    }
    public static void kuaisu20200907helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200907helper2(array, low, high);
        kuaisu20200907helper(array, low, aow);
        kuaisu20200907helper(array, aow+1, high);
    }
    public static int kuaisu20200907helper2(int[] array, int low, int high){
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
    public static void kuaisu20200907Fei(int[] array){
        kuaisu20200907Feihelper(array, 0, array.length-1);
    }
    public static void kuaisu20200907Feihelper(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r) continue;
            int aow = kuaisu20200907Feihelper2(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(r);
        }
    }
    public static int kuaisu20200907Feihelper2(int[] array, int low, int high){
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

    public static void guibing20200907(int[] array){
        int[] tmp = new int[array.length];
        guibing20200907helper(array,tmp,0,array.length-1);
    }
    public static void guibing20200907helper(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int mid = low+(high-low)/2;
        guibing20200907helper(array, tmp, low, mid);
        guibing20200907helper(array, tmp, mid+1, high);
        guibing20200907helper2(array, tmp, low, mid, high);
    }
    public static void guibing20200907helper2(int[] array, int[] tmp, int low, int mid, int high){
        int i=low;
        int j=mid+1;
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
            array[w+low]=tmp[w];
        }
    }

    public static void heap20200907(int[] array){
        for(int i=array.length/2;i>=0;i--){
            heap20200907helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            heap20200907helper(array,0, i);
        }
    }
    public static void heap20200907helper(int[] array, int i, int n){
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

















    }
