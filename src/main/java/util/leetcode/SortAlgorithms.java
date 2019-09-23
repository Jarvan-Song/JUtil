package util.leetcode;

/**
 * Created by songpanfei on 2019-09-23.
 */
public class SortAlgorithms {
    public static void main(String[] args){
        int[] a = new int[]{1,11,3,9,5,6};
        maopao(a);
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
}
