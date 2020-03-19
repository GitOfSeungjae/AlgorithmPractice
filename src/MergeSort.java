import java.util.Arrays;

public class MergeSort {
    static int[] sorted;
    public static void main(String[] args) {
        int[] arr = {3,2,6,5,8,7,9,1,4};
        sorted = new int[arr.length];

        mergeSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    public static void mergeSort(int[]  arr, int s, int e){
        if(e > s){
            int middle = (s + e) / 2;
            mergeSort(arr, s, middle);
            mergeSort(arr, middle+1, e);
            merge(arr, s, middle, e);
        }
    }

    public static void merge(int[] arr, int s, int mid, int e){

       int i = s; int j = mid + 1; int k = s;
       while(i <= mid && j <= e){
           if(arr[i] <= arr[j]){
               sorted[k] = arr[i];
               i++;
           }else{
              sorted[k] = arr[j];
              j++;
           }
           k++;
       }
       if(i > mid){
           for(int t = j; t <= e; t++){
               sorted[k] = arr[t];
               k++;
           }
       }else{
           for(int t = i; t <= mid; t++){
               sorted[k] = arr[t];
               k++;
           }
       }
       for(int t=s;t<=e;t++){
           arr[t] = sorted[t];
       }
//        System.out.println(s + " " + e + " " + Arrays.toString(arr));
    }
}
