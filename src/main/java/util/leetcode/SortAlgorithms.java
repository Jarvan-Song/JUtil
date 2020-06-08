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
        heap20200608(a);
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
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



    public static void maopao1118(int[] array){
        for(int i = array.length-1;i>0;i--){
            boolean flag = false;
            for(int j = 0;j<i;j++){
                if(array[j] > array[j+1]){
                    flag = true;
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    public static void xuanze1118(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j = i + 1;j<array.length;j++){
                if(array[min] > array[j]){
                    min = j;
                }
            }
            if(min != i){
                int temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
    }

    public static void charu1118(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int value = array[i];
            while (key >0 && array[key-1] > value){
                array[key] = array[key-1];
                key--;
            }
            array[key] = value;
        }
    }

    public static void kuaisu1118(int[] array, int low, int high){
        if(high <= low) return;
        int vow = helper2(array, low, high);
        kuaisu1118(array, low, vow-1);
        kuaisu1118(array, vow + 1, high);
    }

    public static int helper2(int[] array, int low, int high){
        int vow = array[low];
        while (low < high){
            while (low < high && array[high]>vow){
                high--;
            }
            array[low]=array[high];
            while (low < high && array[low]<vow){
                low++;
            }
            array[high] = array[low];
        }
        array[low]=vow;
        return low;
    }

    public static void maopao1125(int[] array){
        for(int i=array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j] > array[j+1]){
                    int tmp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                    flag = true;
                }
            }
            if(!flag) break;
        }
    }

    public static void xuanze1125(int[] array){
        for(int i = 0;i<array.length-1;i++){
            int min = i;
            for(int j = i +1;j<array.length;j++){
                if(array[min] > array[j]){
                    min = j;
                }
            }
            if(min != i){
                int tmp = array[min];
                array[min] = array[i];
                array[i] = tmp;
            }
        }
    }

    public static void charu1125(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int value = array[i];
            while (key > 0 && array[key-1] > value){
                array[key] = array[key-1];
                key--;
            }
            array[key]=value;
        }
    }

    public static void guibingSort1125(int[] array){
        int[] tmp = new int[array.length];
        guibing1125(array, tmp, 0, array.length-1);
    }

    public static void guibing1125(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int middle = low + (high-low)/2;
        guibing1125(array, tmp, low, middle);
        guibing1125(array, tmp, middle+1, high);
        guibingHelp1125(array, tmp, low, middle, high);
    }

    public static void guibingHelp1125(int[] array, int[] tmp, int low, int middle, int high){
        int i = low;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <=high){
            tmp[k++] = array[i]<array[j]?array[i++]:array[j++];
        }
        while (i<=middle){
            tmp[k++] = array[i++];
        }
        while (j<=high){
            tmp[k++] = array[j++];
        }
        for(int w=0;w<k;w++){
            array[low+w]=tmp[w];
        }
    }

    public static void kuansu1125(int[] array){
        kuaisuhelper(array, 0, array.length-1);
    }

    public static void kuaisuhelper(int[] array, int low, int high){
        if(low >= high) return;
        int aow = helper3(array, low, high);
        kuaisuhelper(array, low, aow-1);
        kuaisuhelper(array, aow+1, high);
    }

    public static int helper3(int[] array, int low, int high){
        int aow = array[low];
        while (low < high){
            while (low < high && array[high] >= aow) high--;
            array[low] = array[high];
            while (low < high && array[low] <= aow) low++;
            array[high] = array[low];
        }
        array[low] = aow;
        return low;
    }


    public static void maopao1202(int[] array){
        for(int i=array.length -1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j] > array[j+1]){
                    flag = true;
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    public static void xuanze1202(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j = i+1;j<array.length;j++){
                if(array[min] > array[j]){
                    min = j;
                }
            }
            if(min != i){
                int tmp = array[min];
                array[min] = array[i];
                array[i] = tmp;
            }
        }
    }

    public static void charu1202(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int value = array[i];
            while (key>0 && array[key-1]>value){
                array[key] = array[key-1];
                key--;
            }
            array[key] = value;
        }
    }

    public static void kuaisu1202(int[] array){
        kuaisu1202helper(array, 0, array.length-1);
    }

    public static void kuaisu1202helper(int[] array, int low, int high){
        if(low >= high) return;
        int aow = kuaisu1202helper2(array, low, high);
        kuaisu1202helper(array, low, aow);
        kuaisu1202helper(array, aow+1, high);
    }

    public static int kuaisu1202helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low < high){
            while (low<high&&array[high]>=aow) high--;
            array[low]=array[high];
            while (low<high&&array[low]<=aow) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }

    public static void guibing1202(int[] array){
        int[] tmp = new int[array.length];
        guibing1202helper(array, tmp, 0, array.length-1);
    }

    public static void guibing1202helper(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int middle = low + (high-low)/2;
        guibing1202helper(array, tmp, low, middle);
        guibing1202helper(array, tmp, middle+1, high);
        guibing1202helper2(array, tmp, low, middle, high);
    }

    public static void guibing1202helper2(int[] array, int[] tmp, int low, int middle, int high){
        int i = low;
        int j = middle+1;
        int k=0;
        while (i<=middle&& j<=high){
            tmp[k++]=array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=middle){
            tmp[k++] = array[i++];
        }
        while (j<=high){
            tmp[k++] = array[j++];
        }
        for(int w=0;w<k;w++){
            array[w+low] = tmp[w];
        }
    }


    public static void maopao1210(int[] array){
        for(int i = array.length -1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j] > array[j+1]){
                    flag = true;
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
            if(!flag) break;
        }
    }

    public static void xuanze1210(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j = i+1;j<array.length;j++){
                if(array[min]>array[j]){
                    min = j;
                }
            }
            if(min != i){
                int tmp = array[min];
                array[min] = array[i];
                array[i] = tmp;
            }
        }
    }

    public static void charu1210(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int value = array[i];
            while (key>0&&array[key-1]>value){
                array[key] = array[key-1];
                key--;
            }
            array[key] = value;
        }
    }

    public static void guibing1210(int[] array){
        int[] tmp = new int[array.length];
        guibing1210Helper(array, tmp, 0, array.length-1);
    }

    public static void guibing1210Helper(int[] array, int[] tmp, int low, int high){
        if(low >= high) return;
        int middle = low + (high-low)/2;
        guibing1210Helper(array, tmp, low, middle);
        guibing1210Helper(array, tmp, middle+1, high);
        guibing1210Helper2(array, tmp, low, middle, high);
    }

    public static void guibing1210Helper2(int[] array, int[] tmp, int low, int middle, int high){
        int i = low;
        int j = middle+1;
        int k=0;
        while (i <= middle && j <= high){
            tmp[k++] = array[i] > array[j] ? array[j++]:array[i++];
        }
        while (i<=middle){
            tmp[k++] = array[i++];
        }
        while (j<=high){
            tmp[k++] = array[j++];
        }
        for(int w = 0;w<k;w++){
            array[w+low] = tmp[w];
        }
    }

    public static void kuaisu1210(int[] array){
        kuaisu1210helper(array, 0, array.length-1);
    }

    public static void kuaisu1210helper(int[] array, int low, int high){
        if(low >= high) return;
        int aow = kuaisu1210helper2(array, low, high);
        kuaisu1210helper(array, low, aow);
        kuaisu1210helper(array, aow+1, high);
    }

    public static int kuaisu1210helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low < high){
            while (low<high && array[high] >= aow) high--;
            array[low] = array[high];
            while (low<high && array[low]  <= aow) low++;
            array[high] = array[low];
        }
        array[low] = aow;
        return low;
    }

    public static void maopao1217(int[] array){
        for(int i=array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    int tmp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                    flag = true;
                }
            }
            if(!flag) break;
        }
    }

    public static void xuanze1217(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[j]<array[min]){
                    min = j;
                }
            }
            if(min!=i){
                int tmp = array[min];
                array[min] = array[i];
                array[i]=tmp;
            }
        }
    }

    public static void charu1217(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int value = array[key];
            while (key>0&&array[key-1]>value){
                array[key]=array[key-1];
                key--;
            }
            array[key]=value;
        }
    }

    public static void guibing1217(int[] array){
        int[] tmp = new int[array.length];
        guibing1217helper(array, tmp, 0, array.length-1);
    }

    public static void guibing1217helper(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int middle = low + (high-low)/2;
        guibing1217helper(array, tmp, low, middle);
        guibing1217helper(array, tmp, middle+1, high);
        guibing1217helper2(array, tmp, low, middle, high);
    }

    public static void guibing1217helper2(int[] array, int[] tmp, int low, int middle, int high){
        int i=low;
        int j=middle+1;
        int k=0;
        while (i<=middle && j<=high){
            tmp[k++] = array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=middle){
            tmp[k++] = array[i++];
        }
        while (j<=high){
            tmp[k++] = array[j++];
        }
        for(int w=0;w<k;w++){
            array[low+w] = tmp[w];
        }
    }

    public static void kuaisu1217(int[] array){
        kuaisu1217helper(array, 0, array.length-1);
    }

    public static void kuaisu1217helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu1217helper2(array, low, high);
        kuaisu1217helper(array, low, aow);
        kuaisu1217helper(array, aow+1, high);
    }

    public static int kuaisu1217helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high && array[high]>=aow) high--;
            array[low]=array[high];
            while (low<high && array[low]<=aow) low++;
            array[high]=array[low];
        }
        array[low] = aow;
        return low;
    }

    public static void maopao1225(int[] array){
        for(int i = array.length - 1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    flag = true;
                    int tmp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    public static void xuanze1225(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min] > array[j]){
                    min = j;
                }
            }
            if(min != i){
                int tmp = array[min];
                array[min]=array[i];
                array[i]=tmp;
            }
        }
    }

    public static void charu1225(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int value = array[i];
            while (key>0&&array[key-1]>value){
                array[key] = array[key-1];
                key--;
            }
            array[key] = value;
        }
    }

    public static void kuaisu1225(int[] array){
        kuaisu1helper225(array, 0 , array.length-1);
    }

    public static void kuaisu1helper225(int[] array, int low, int high){
        if(low >= high) return;
        int aow = kuaisuhelper21225(array, low, high);
        kuaisu1helper225(array, low, aow);
        kuaisu1helper225(array, aow+1, high);
    }
    public static int kuaisuhelper21225(int[] array, int low, int high){
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

    public static void guibing1225(int[] array){
        int[] tmp = new int[array.length];
        guibinghelper225(array, tmp, 0, array.length-1);
    }

    public static void guibinghelper225(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int middle = low+(high-low)/2;
        guibinghelper225(array, tmp, low, middle);
        guibinghelper225(array, tmp,middle+1, high);
        guibinghelper21225(array, tmp, low, middle, high);
    }

    public static void guibinghelper21225(int[] array, int[] tmp, int low, int middle, int high){
        int i=low;
        int j=middle+1;
        int k=0;
        while (i<=middle&&j<=high){
            tmp[k++]=array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=middle){
            tmp[k++]=array[i++];
        }
        while (j<=high){
            tmp[k++]=array[j++];
        }
        for(int w=0;w<k;w++){
            array[low+w]=tmp[w];
        }
    }

    public static void maopao1231(int[] array){
        for(int i = array.length-1;i>0;i--){
            boolean flag = false;
            for(int j = 0;j<i;j++){
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

    public static void xuanze1231(int[] array){
        for(int i = 0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]>array[j]){
                    min = j;
                }
            }
            if(min != i){
                int tmp = array[min];
                array[min] = array[i];
                array[i] = tmp;
            }
        }
    }

    public static void charu1231(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int value = array[key];
            while (key>0&&array[key-1]>value){
                array[key] = array[key-1];
                key--;
            }
            array[key] = value;
        }
    }

    public static void kuaisu1231(int[] array){
        kuaisuhelper1231(array, 0, array.length-1);
    }

    public static void kuaisuhelper1231(int[] array, int low, int high){
        if(low >= high) return;
        int aow = kuaisuhelper21231(array, low, high);
        kuaisuhelper1231(array, low, aow);
        kuaisuhelper1231(array, aow+1, high);
    }
    public static int kuaisuhelper21231(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&array[high]>=aow) high--;
            array[low] = array[high];
            while (low<high&&array[low]<=aow) low++;
            array[high] = array[low];
        }
        array[low] = aow;
        return low;
    }


    public static void guibing1231(int[] array){
        int[] tmp = new int[array.length];
        guibinghelper1231(array, tmp, 0, array.length-1);
    }

    public static void guibinghelper1231(int[] array, int[] tmp, int low, int high){
        if(low >= high) return;
        int middle = low + (high-low)/2;
        guibinghelper1231(array, tmp, low, middle);
        guibinghelper1231(array, tmp,  middle+1, high);
        guibinghelper21231(array, tmp, low, middle, high);
    }

    public static void guibinghelper21231(int[] array, int[] tmp, int low, int middle, int high){
        int i = low;
        int j = middle+1;
        int k = 0;
        while (i<=middle&&j<=high){
            tmp[k++] = array[i]>array[j]? array[j++]:array[i++];
        }
        while (i<=middle){
            tmp[k++] = array[i++];
        }
        while (j<=high){
            tmp[k++] = array[j++];
        }
        for(int w = 0;w<k;w++){
            array[low+w] = tmp[w];
        }
    }

    public static void heap1231(int[] array){
        for(int i= array.length/2-1;i>=0;i--){
            heaphelper1231(array, i, array.length);
        }
        for(int j = array.length-1;j>0;j--){
            int tmp = array[0];
            array[0] = array[j];
            array[j] = tmp;
            heaphelper1231(array, 0, j);
        }
    }

    public static void heaphelper1231(int[] array, int i, int n){
        int tmp = array[i];
        for(int k = 2*i+1;k<n;k=2*k+1){
            if(k+1<n && array[k]<array[k+1]){
                k++;
            }
            if(array[k]> tmp){
                array[i] = array[k];
                i = k;
            }else {
                break;
            }
        }
        array[i] = tmp;
    }




    public static void maopao0106(int[] array){
        for(int i= array.length-1;i>0;i--){
            boolean flag = false;
            for(int j = 0;j<i;j++){
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

    public static void xuanze0106(int[] array){
        for(int i= 0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min] > array[j]){
                    min = j;
                }
            }
            if(min != i){
                int tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }
        }
    }

    public static void charu0106(int[] array){
        for(int i= 1;i<array.length;i++){
            int key = i;
            int value = array[key];
            while (key>0&&array[key-1]>value){
                array[key] = array[key-1];
                key--;
            }
            array[key]=value;
        }
    }

    public static void kuaisu0106(int[] array){
        kuaisuhelper0106(array, 0, array.length-1);
    }

    public static void kuaisuhelper0106(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisuhelper20106(array, low, high);
        kuaisuhelper0106(array, low, aow);
        kuaisuhelper0106(array, aow+1, high);
    }
    public static int kuaisuhelper20106(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&array[high]>=aow) high--;
            array[low] = array[high];
            while (low<high&&array[low]<=aow) low++;
            array[high] = array[low];
        }
        array[low]=aow;
        return low;
    }

    public static void guibing0106(int[] array){
        int[] tmp = new int[array.length];
        guibinghelper0106(array, tmp, 0, array.length-1);
    }

    public static void guibinghelper0106(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int middle = low +(high-low)/2;
        guibinghelper0106(array, tmp, low, middle);
        guibinghelper0106(array, tmp, middle+1, high);
        guibinghelper20106(array, tmp, low, middle, high);
    }

    public static void guibinghelper20106(int[] array, int[] tmp, int low, int middle, int high){
        int i=low;
        int j=middle+1;
        int k=0;
        while (i<=middle&&j<=high){
            tmp[k++] = array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=middle){
            tmp[k++]=array[i++];
        }
        while (j<=high){
            tmp[k++]=array[j++];
        }
        for(int w =0;w<k;w++){
            array[w+low]=tmp[w];
        }
    }

    public static void heap0106(int[] array){
        for(int i=array.length/2-1;i>=0;i--){
            heaphelper0106(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[i];
            array[i] = array[0];
            array[0] = tmp;
            heaphelper0106(array, 0, i);
        }
    }

    public static void heaphelper0106(int[] array, int i, int n){
        int tmp = array[i];
        for(int k = 2*i+1;k<n;k=2*k+1){
            if(k+1<n&&array[k]<array[k+1]){
                k++;
            }
            if(tmp<array[k]){
                array[i]=array[k];
                i=k;
            }else {
                break;
            }
        }
        array[i]=tmp;
    }




    public static void maopao1013(int[] array){
        for(int i = array.length -1;i>0;i--){
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

    public static void xuanze1013(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j= i+1;j<array.length;j++){
                if(array[min]>array[j]){
                    min = j;
                }
            }
            if(i!=min){
                int tmp = array[i];
                array[i]=array[min];
                array[min]=tmp;
            }
        }
    }

    public static void charu1013(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int value = array[i];
            while (key>0&&array[key-1]>value){
                array[key] = array[key-1];
                key--;
            }
            array[key] = value;
        }
    }

    public static void kuasi1013(int[] array){
        kuasi1013helper(array, 0, array.length-1);
    }

    public static void kuasi1013helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuasi1013helper2(array, low, high);
        kuasi1013helper(array, low, aow);
        kuasi1013helper(array, aow+1, high);
    }

    public static int kuasi1013helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (high>low&&array[high]>=aow) high--;
            array[low] = array[high];
            while (high>low&&array[low]<=aow) low++;
            array[high] = array[low];
        }
        array[low]=aow;
        return low;
    }


    public static void guibing1013(int[] array){
        int[] tmp = new int[array.length];
        guibing1013helper(array, tmp, 0, array.length-1);
    }

    public static void guibing1013helper(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int middle = low+(high-low)/2;
        guibing1013helper(array, tmp, low, middle);
        guibing1013helper(array, tmp, middle+1, high);
        guibing1013helper2(array, tmp, low, middle, high);
    }

    public static void guibing1013helper2(int[] array, int[] tmp, int low, int middle, int high){
        int i = low;
        int j = middle+1;
        int k=0;
        while (i<=middle&&j<=high){
            tmp[k++]=array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=middle){
            tmp[k++]=array[i++];
        }
        while (j<=high){
            tmp[k++]=array[j++];
        }
        for(int w=0;w<k;w++){
            array[low+w]=tmp[w];
        }
    }

    public static void dui0113(int[] array){
        for(int i=array.length/2-1;i>=0;i--){
            dui0113helper(array, i, array.length);
        }
        for(int i= array.length-1;i>=0;i--){
            int tmp = array[0];
            array[0]=array[i];
            array[i]=tmp;
            dui0113helper(array, 0, i);
        }
    }

    public static void dui0113helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k = 2*i+1;k<n;k=2*k+1){
            if(k+1<n&&array[k]<array[k+1]){
                k++;
            }
            if(tmp<array[k]){
                array[i] = array[k];
                i = k;
            }else{
                break;
            }
        }
        array[i]=tmp;
    }


    public static void maopao0120(int[] array){
        for(int i= array.length-1;i>0;i--){
            boolean flag = false;
            for(int j = 0;j<i;j++){
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
    public static void xuanze0120(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j = i+1;j<array.length;j++){
                if(array[j]<array[min]){
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
    public static void charu0120(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int value = array[i];
            while (key>0&&array[key-1]>value){
                array[key]=array[key-1];
                key--;
            }
            array[key]=value;
        }
    }

    public static void kuaisu0120(int[] array){
        kuaisu0120helper(array, 0 , array.length-1);
    }

    public static void kuaisu0120helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu0120helper2(array, low, high);
        kuaisu0120helper(array, low, aow);
        kuaisu0120helper(array, aow+1, high);
    }

    public static int kuaisu0120helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (array[high]>=aow&&low<high) high--;
            array[low]=array[high];
            while (array[low]<=aow&&low<high) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }

    public static void guibing0120(int[] array){
        int[] tmp = new int[array.length];
        guibing0120helper(array, tmp, 0, array.length-1);
    }

    public static void guibing0120helper(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int middle = low+(high-low)/2;
        guibing0120helper(array, tmp, low, middle);
        guibing0120helper(array, tmp, middle+1, high);
        guibing0120helper2(array, tmp, low, middle, high);
    }

    public static void guibing0120helper2(int[] array, int[] tmp, int low, int middle, int high){
        int i = low;
        int j = middle+1;
        int k=0;
        while (i<=middle&&j<=high){
            tmp[k++]=array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=middle){
            tmp[k++]=array[i++];
        }
        while (j<=high){
            tmp[k++]=array[j++];
        }
        for(int w=0;w<k;w++){
            array[w+low]=tmp[w];
        }
    }

    public static void heap0120(int[] array){
        for(int i=array.length-1;i>=0;i--){
            heap0120helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[i];
            array[i]=array[0];
            array[0]=tmp;
            heap0120helper(array, 0, i);
        }
    }

    public static void heap0120helper(int[] array,int i, int n){
       int tmp = array[i];
       for(int k=2*i+1;k<n;k=2*i+1){
           if(k+1<n&&array[k+1]>array[k]){
               k++;
           }
           if(tmp<array[k]){
               array[i]=array[k];
               i=k;
           }else{
               break;
           }
       }
       array[i]=tmp;
    }

    public static void maopao0407(int[] array){
        for(int i = array.length-1;i>0;i--){
            boolean flag = false;
            for(int j = 0;j<i;j++){
                if(array[j]>array[j+1]){
                    flag = true;
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
            if(!flag){
               break;
            }
        }
    }

    public static void xuanze0407(int[] array) {
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j = i+1;j<array.length;j++){
                if(array[min] > array[j]){
                    min = j;
                }
            }
            if(min != i){
                int temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
    }

    public static void charu0407(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int val = array[i];
            while (key>0 && array[key-1]>val){
                array[key] = array[key-1];
                key--;
            }
            array[key]=val;
        }
    }


    public static void kuaisu0407(int[] array){
        kuaisu0407helper(array, 0, array.length-1);
    }

    public static void kuaisu0407helper(int[] array, int low, int high){
        if(low>=high)return;
        int aow = kuaisu0407helper2(array, 0, array.length-1);
        kuaisu0407helper(array, low, aow);
        kuaisu0407helper(array, aow+1, high);
    }

    public static int kuaisu0407helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low < high && array[high]>=aow) high--;
            array[low] = array[high];
            while (low < high && array[low]<aow) low++;
            array[high] = array[low];
        }
        array[low]=aow;
        return low;
    }



    public static void maopao20200421(int[] array){
        for(int i = array.length -1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    flag = true;
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
            if(!flag){break;}
        }
    }

    public static void xuanze20200421(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]>array[j]){
                    min = j;
                }
            }
            if(min != i){
                int temp=array[min];
                array[min]=array[i];
                array[i]=temp;
            }
        }
    }

    public static void charu20200421(int[] array){
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


    public static void kuaisu20200421(int[] array){
        kuaisu20200421helper(array, 0, array.length-1);
    }

    public static void kuaisu20200421helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200421helper2(array, low, high);
        kuaisu20200421helper(array, 0, aow);
        kuaisu20200421helper(array, aow+1, high);
    }

    public static int kuaisu20200421helper2(int[] array, int low, int high){
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


    public static void guibing20200421(int[] array){
        int[] temp = new int[array.length];
        guibing20200421helper(array, temp, 0, array.length-1);
    }

    public static void guibing20200421helper(int[] array, int[] temp, int low, int high){
        if(low>=high) return;
        int middle = low + (high-low)/2;
        guibing20200421helper(array, temp, low, middle);
        guibing20200421helper(array, temp, middle+1, high);
        guibing20200421helper2(array, temp, low, middle, high);
    }

    public static void guibing20200421helper2(int[] array, int[] temp,int low, int middle, int high){
        int i=low;
        int j=middle+1;
        int k=0;
        while (i<=middle&&j<=high){
            temp[k++]=array[i]>array[j]?array[j++]:array[i++];
        }
        while (i<=middle){
            temp[k++]=array[i];
            i++;
        }
        while (j<=high){
            temp[k++]=array[j];
            j++;
        }
        for(int w=0;w<k;w++){
            array[low+w]=temp[w];
        }
    }

    public static void maopao20200506(int[] array){
        for(int i=array.length-1;i>0;i--){
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

    public static void xuanze20200506(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]>array[j]){
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

    public static void charu20200506(int[] array){
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

    public static void kuaisu20200506(int[] array){
        kuaisu20200506helper1(array, 0, array.length-1);
    }

    public static void kuaisu20200506helper1(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200506helper2(array, low, high);
        kuaisu20200506helper1(array, low, aow);
        kuaisu20200506helper1(array, aow+1, high);
    }

    public static int kuaisu20200506helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&array[high]>=aow) high--;
            array[low]=array[high];
            while (low<high&&array[low]<aow) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }

    public static void guibing20200506(int[] array){
        int[] tmp = new int[array.length];
        guibing20200506helper1(array, tmp, 0, array.length-1);
    }


    public static void guibing20200506helper1(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int mid = low+(high-low)/2;
        guibing20200506helper1(array, tmp, low, mid);
        guibing20200506helper1(array, tmp, mid+1, high);
        guibing20200506helper2(array, tmp, low, mid, high);
    }

    public static void guibing20200506helper2(int[] array, int[] tmp, int low, int mid, int high){
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
        for(int wow=0;wow<k;wow++){
            array[low+wow]=tmp[wow];
        }
    }


    public static void maopao20200511(int[] array){
        for(int i=array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    flag = true;
                    int tmp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    public static void xuanze20200511(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]>array[j]){
                    min = j;
                }
            }
            if(min != i){
                int tmp = array[min];
                array[min]=array[i];
                array[i]=tmp;
            }
        }
    }
    public static void charu20200511(int[] array){
        for(int i = 1;i<array.length;i++){
            int key = i;
            int value = array[i];
            while (key>0&&array[key-1]>value){
                array[key]=array[key-1];
                key--;
            }
            array[key]=value;
        }
    }
    public static void kuaisu20200511(int[] array){
        kuaisu20200511helper1(array, 0, array.length-1);
    }
    public static void kuaisu20200511helper1(int[] array, int low, int high){
        if(low>=high)return;
        int aow = kuaisu20200511helper2(array, low, high);
        kuaisu20200511helper1(array, low, aow);
        kuaisu20200511helper1(array, aow+1, high);
    }
    public static int kuaisu20200511helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low < high){
            while (low<high && array[high]>=aow) high--;
            array[low] = array[high];
            while (low<high && array[low]<=aow) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }
    public static void guibing20200511(int[] array){
        int[] tmp = new int[array.length];
        guibing20200511helper1(array, tmp, 0, array.length-1);
    }
    public static void guibing20200511helper1(int[] array, int[] tmp, int low, int high){
        if(low>=high)return;
        int mid = low+(high-low)/2;
        guibing20200511helper1(array, tmp, low, mid);
        guibing20200511helper1(array, tmp, mid+1, high);
        guibing20200511helper2(array, tmp, low, mid, high);
    }
    public static void guibing20200511helper2(int[] array, int[] tmp, int low,int mid, int high){
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
        for(int a=0;a<k;a++){
            array[low+a]=tmp[a];
        }
    }

    public static void maopao20200518(int[] array){
        for(int i=array.length-1;i>0;i--){
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
    public static void xuanze20200518(int[] array){
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
    public static void charu20200518(int[] array){
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

    public static void kuaisu20200518(int[] array){
        kuaisu20200518helper1(array, 0, array.length-1);
    }
    public static void kuaisu20200518helper1(int[] array, int low, int high){
        if(low>=high)return;
        int aow=kuaisu20200518helper2(array, low, high);
        kuaisu20200518helper1(array, low, aow);
        kuaisu20200518helper1(array, aow+1, high);
    }
    public static int kuaisu20200518helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&aow<=array[high]) high--;
            array[low]=array[high];
            while (low<high&&aow>=array[low]) low++;
            array[high]=array[low];
        }
        array[low] = aow;
        return low;
    }
    public static void kuaisu20200518feidigui(int[] array){
        kuaisu20200518feidiguihelper1(array, 0, array.length-1);
    }
    public static void kuaisu20200518feidiguihelper1(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(high);
        stack.push(low);
        while (!stack.isEmpty()){
            int l = stack.pop();
            int r = stack.pop();
            int aow = kuaisu20200518feidiguihelper2(array, l, r);
            if(l<aow){
                stack.push(aow);
                stack.push(l);
            }
            if(r>aow){
                stack.push(r);
                stack.push(aow+1);
            }
        }
    }
    public static int kuaisu20200518feidiguihelper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (low<high&&aow<=array[high]) high--;
            array[low]=array[high];
            while (low<high&&aow>=array[low]) low++;
            array[high]=array[low];
        }
        array[low] = aow;
        return low;
    }

    public static void guibing20200518(int[] array){
        int[] tmp = new int[array.length];
        guibing20200518helper1(array, tmp, 0, array.length-1);
    }
    public static void guibing20200518helper1(int[] array, int[] tmp, int low, int high){
        if(low>=high)return;
        int mid = low+(high-low)/2;
        guibing20200518helper1(array, tmp, low, mid);
        guibing20200518helper1(array, tmp, mid+1, high);
        guibing20200518helper2(array, tmp, low, mid, high);
    }
    public static void guibing20200518helper2(int[] array, int[] tmp, int low, int mid, int high){
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
            array[low+w]=tmp[w];
        }
    }

    public static void heap20200520(int[] array){
        for(int i=array.length-1;i>=0;i--){
            heap20200520helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0]=array[i];
            array[i]=tmp;
            heap20200520helper(array, 0, i);
        }
    }

    public static void heap20200520helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*k+1){
            if(k+1<n&&array[k]<array[k+1]){
                k++;
            }
            if(tmp<array[k]){
                array[i]=array[k];
                i = k;
            }else {
                break;
            }
        }
        array[i]=tmp;
    }


    public static void maopao20200526(int[] array){
        for(int i=array.length-1;i>0;i--){
            boolean flag = false;
            for(int j=0;j<i;j++){
                if(array[j]>array[j+1]){
                    flag = true;
                    int tmp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                }
            }
            if(!flag)break;
        }
    }

    public static void xuanze20200526(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j< array.length;j++){
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
    public static void charu20200526(int[] array){
        for(int i=1;i<array.length;i++){
            int key = i;
            int val = array[i];
            while (key>0&&array[key-1]>val){
                array[key]=array[key-1];
                key--;
            }
            array[key] =val;
        }
    }

    public static void kuaisu20200526(int[] array){
        kuaisu20200526helper(array, 0, array.length-1);
    }

    public static void kuaisu20200526helper(int[] array, int low, int high){
        if(low>=high)return;
        int aow = kuaisu20200526helper2(array, low, high);
        kuaisu20200526helper(array, low, aow);
        kuaisu20200526helper(array, aow+1, high);
    }

    public static int kuaisu20200526helper2(int[] array, int low, int high){
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

    public static void guibing20200526(int[] array){
        int[] tmp = new int[array.length];
        guibing20200526helper(array, tmp, 0, array.length-1);
    }

    public static void guibing20200526helper(int[] array, int[] tmp, int low, int high){
        if(low>=high)return;
        int mid = low+(high-low)/2;
        guibing20200526helper(array, tmp, low, mid);
        guibing20200526helper(array, tmp, mid+1, high);
        guibing20200526helper2(array, tmp, low, mid, high);
    }

    public static void guibing20200526helper2(int[] array, int[] tmp, int low, int mid, int high){
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
        for(int w=0;w<k;w++){
            array[w+low]=tmp[w];
        }
    }

    public static void heap20200526(int[] array){
        for(int i=array.length-1;i>=0;i--){
            heap20200526helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0]=array[i];
            array[i]=tmp;
            heap20200526helper(array, 0, i);
        }
    }

    public static void heap20200526helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
            if(k+1<n&&array[k+1]>array[k]){
                k++;
            }
            if(tmp<array[k]){
                array[i]=array[k];
                i=k;
            }else {
                break;
            }
        }
        array[i]=tmp;
    }



    public static void maopao20200601(int[] array){
        for(int i=array.length-1;i>0;i--){
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
    public static void xuanze20200601(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[min]>array[j]){
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
    public static void charu20200601(int[] array){
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
    public static void kuaisu20200601(int[] array){
        kuaisu20200601helper(array, 0, array.length-1);
    }
    public static void kuaisu20200601helper(int[] array, int low, int high){
        if(low>=high)return;
        int aow = kuaisu20200601helper2(array, low, high);
        kuaisu20200601helper(array, low, aow);
        kuaisu20200601helper(array, aow+1, high);
    }
    public static int kuaisu20200601helper2(int[] array, int low, int high){
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

    public static void kuaisu20200601_feidigui(int[] array){
        kuaisu20200601helper_feidigui(array, 0, array.length-1);
    }

    public static void kuaisu20200601helper_feidigui(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(high);
        stack.push(low);
        while (!stack.isEmpty()){
            int left = stack.pop();
            int right  = stack.pop();
            if(left >= right) continue;
            int aow = kuaisu20200601helper2_feidigui(array, left, right);
            stack.push(aow);
            stack.push(left);
            stack.push(right);
            stack.push(aow+1);
        }
    }

    public static int kuaisu20200601helper2_feidigui(int[] array, int low, int high){
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


    public static void guibing20200601(int[] array){
        int[]  tmp = new int[array.length];
        guibing20200601helper(array, tmp, 0, array.length-1);
    }
    public static void guibing20200601helper(int[] array, int[] tmp, int low, int high){
        if(low>=high)return;
        int mid = low+(high-low)/2;
        guibing20200601helper(array, tmp, low, mid);
        guibing20200601helper(array, tmp, mid+1, high);
        guibing20200601helper2(array, tmp, low, mid, high);
    }
    public static void guibing20200601helper2(int[] array, int[] tmp, int low, int mid, int high){
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
        for(int w =0;w<k;w++){
            array[low+w]=tmp[w];
        }
    }
    public static void heap20200601(int[] array){
        for(int i=array.length-1;i>=0;i--){
            heap20200601helper(array, i, array.length);
        }
        for(int i=array.length-1;i>0;i--){
            int tmp = array[0];
            array[0]=array[i];
            array[i]=tmp;
            heap20200601helper(array, 0, i);
        }
    }
    public static void heap20200601helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
            if(k+1<n && array[k]<array[k+1]){
                k++;
            }
            if(tmp<array[k]){
                array[i]=array[k];
                i=k;
            }else {
                break;
            }
        }
        array[i]=tmp;
    }

    public static void maopao20200608(int[] array){
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
            if(!flag) break;
        }
    }
    public static void xuanze20200608(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j = i+1;j<array.length;j++){
                if(array[j]<array[min]) min= j;
            }
            if(min!=i){
                int tmp = array[min];
                array[min] = array[i];
                array[i]=tmp;
            }
        }
    }
    public static void charu20200608(int[] array){
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
    public static void kuaisu20200608(int[] array){
        kuaisu20200608helper(array, 0, array.length-1);
    }
    public static void kuaisu20200608helper(int[] array, int low, int high){
        if(low>=high) return;
        int aow = kuaisu20200608helper2(array, low, high);
        kuaisu20200608helper(array, low, aow);
        kuaisu20200608helper(array, aow+1, high);
    }
    public static int kuaisu20200608helper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (array[high]>=aow && low<high) high--;
            array[low]=array[high];
            while (low<high&&array[low]<=aow) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }
    public static void kuaisu20200608_feidigui(int[] array) {
        kuaisu20200608_feidiguihelper(array, 0, array.length-1);
    }
    public static void kuaisu20200608_feidiguihelper(int[] array, int low, int high){
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if(l>=r) continue;
            int aow = kuaisu20200608_feidiguihelper2(array, l, r);
            stack.push(l);
            stack.push(aow);
            stack.push(aow+1);
            stack.push(r);
        }
    }
    public static int kuaisu20200608_feidiguihelper2(int[] array, int low, int high){
        int aow = array[low];
        while (low<high){
            while (array[high]>=aow && low<high) high--;
            array[low]=array[high];
            while (low<high&&array[low]<=aow) low++;
            array[high]=array[low];
        }
        array[low]=aow;
        return low;
    }
    public static void guibing20200608(int[] array){
        int[] tmp = new int[array.length];
        guibing20200608helper(array, tmp, 0, array.length-1);
    }
    public static void guibing20200608helper(int[] array, int[] tmp, int low, int high){
        if(low>=high) return;
        int mid = low+(high-low)/2;
        guibing20200608helper(array, tmp, low, mid);
        guibing20200608helper(array, tmp, mid+1, high);
        guibing20200608helper2(array, tmp, low, mid, high);
    }
    public static void guibing20200608helper2(int[] array, int[] tmp, int low, int mid, int high){
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
    public static void heap20200608(int[] array){
        for(int i=array.length-1;i>=0;i--){
            heap20200608helper(array, i, array.length);
        }
        for(int i=array.length-1;i>=0;i--){
            int tmp = array[0];
            array[0]=array[i];
            array[i]=tmp;
            heap20200608helper(array, 0, i);
        }
    }
    public static void heap20200608helper(int[] array, int i, int n){
        int tmp = array[i];
        for(int k=2*i+1;k<n;k=2*i+1){
            if(k+1<n&&array[k]<array[k+1]){
                k++;
            }
            if(tmp<=array[k]){
                array[i]=array[k];
                i=k;
            }else {
                break;
            }
        }
        array[i]=tmp;
    }













}
