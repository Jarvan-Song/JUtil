package util.leetcode;


/**
 * Created by songpanfei on 2019-09-23.
 */
public class SortAlgorithms {
    public static void main(String[] args){
        int[] a = new int[]{1,11,3,9,5,6};
        heapSort(a);
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
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























}
