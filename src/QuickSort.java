import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {

        int[] v = {3,5,2,1,7,4,9,8};
        Arrays.stream(v).forEach(i -> System.out.print(i + " "));
        System.out.println();
        qsort(v, 0, v.length-1);
        Arrays.stream(v).forEach(i -> System.out.print(i + " "));
//        System.out.println();
    }

    private static void qsort(int[] v, int s, int e){
        int pivot = v[s];
        int bs = s; int be = e;
        while(s < e){
            while(pivot <= v[e] && s < e) e--;
            if(s > e) break;
            while(pivot >= v[s] && s < e) s++;
            if(s > e) break;
            int temp = v[s];
            v[s] = v[e];
            v[e] = temp;
        }
//        System.out.println(v[bs] + " " + bs + " " + be);
        int temp = v[bs];
        v[bs] = v[s];
        v[s] = temp;
//        Arrays.stream(v).forEach(i -> System.out.print(i + " "));
//        System.out.println();
        if(bs < s) qsort(v, bs, s-1);
        if(be > e) qsort(v, s+1, be);
    }
}
