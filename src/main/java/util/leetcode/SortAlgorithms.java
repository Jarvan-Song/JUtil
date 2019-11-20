package util.leetcode;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

/**
 * Created by songpanfei on 2019-09-23.
 */
public class SortAlgorithms {
    public static void main(String[] args){
        int[] a = new int[]{1,11,3,9,5,6};
        charu1118(a);
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
        System.out.println(ZhConverterUtil.convertToSimple("幺儿"));
    }

    public static int[] maopao(int[] arry){
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
        return arry;
    }

    public static int[] xuanze(int[] arry){
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
        return arry;
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

    public static void kuaisu(int[] array, int low, int high){
        if(low >= high){return;}
        int avr = helper(array, low, high);
        kuaisu(array, low, avr-1);
        kuaisu(array, avr + 1, high);
    }

    public static int helper(int[] array, int low, int high){
        int p = array[low];
        while (low < high){
            while (low < high && array[high] > p) high--;
            array[low] = array[high];
            while (low < high && array[low] < p) low++;
            array[high]  = array[low];
        }
        array[low] = p;
        return low;
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
        int vow = helper2(array, 0, array.length);
        kuaisu1118(array, 0, vow-1);
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
        return vow;
    }































}
