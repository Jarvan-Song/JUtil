package util.leetcode;

/**
 * Created by songpanfei on 2019-09-23.
 */
public class SortAlgorithms {
    public static void main(String[] args){
        int[] a = new int[]{1,11,3,9,5,6};
        insertionSort2(a);
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }

    public static int[] maopao(int[] arry){
        for(int i=0;i<arry.length-1;i++){
            boolean flag = false;
            for(int j=0;j<arry.length-1-i;j++){
                int pre = arry[j];
                int nex = arry[j+1];
                if(pre > nex){
                    flag = true;
                    int tep = pre;
                    arry[j] = nex;
                    arry[j+1] = tep;
                }
            }
            if(!flag) break;
        }
        return arry;
    }

    public static int[] maopao2(int[] arry){
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
        for (int i=1; i<arr.length; ++i){
            int value = arr[i];
            int position=i;
            while (position>0 && value < arr[position-1]){
                arr[position] = arr[position-1];
                position--;
            }
            arr[position] = value;
        }
    }



    public static void insertionSort2(int[] arr){
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

}
