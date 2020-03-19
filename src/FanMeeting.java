import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class FanMeeting {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("C:/Users/user/IdeaProjects/AlgorithmPractice/resources/FanMeeting/fanmeeting.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int cases = Integer.parseInt(br.readLine());
        for (int k = 0; k < cases; k++){
            final String idolStr = br.readLine();
            final String fanStr = br.readLine();
            ArrayList<Integer> idols = new ArrayList<>();
            ArrayList<Integer> fans = new ArrayList<>();
            for (int i = 0; i < idols.size(); i++){
                if(idolStr.charAt(i) == 'F') idols.add(0);
                else idols.add(1);
            }
            for (int i = 0; i < fans.size(); i++){
                if(fanStr.charAt(i) == 'F') fans.add(0);
                else fans.add(1);
            }
            ArrayList<Integer> multiply = karatuba(idols, fans);

            multiply.stream().forEach(i -> System.out.println(i + " "));

        }
    }

    static ArrayList karatuba(ArrayList a, ArrayList b){
        if(a.size() < b.size()) return karatuba(b,a);
        if(a.size() < 50) return multiply(a,b);

        int half = a.size()/2;

        ArrayList a0 = copyArray(a,0, half);
        ArrayList a1 = copyArray(a, half, a.size());
        int minIndexNo = Math.min(half, b.size());
        ArrayList b0 = copyArray(b, 0, minIndexNo);
        ArrayList b1 = copyArray(b, Math.min(minIndexNo, b.size()), b.size());

        ArrayList z0 = karatuba(a0, b0);
        ArrayList z2 = karatuba(a1, b1);

       return null;
    }

    static ArrayList copyArray(ArrayList src, int from, int to){
        ArrayList dest = new ArrayList();
        if(from == to) return null;
        for(int i=from; i < to; i++){
            dest.add(src.get(i));
        }
        return dest;
    }

    static void addTo(int[] a, int[] b, int k){

    }

    static ArrayList multiply(ArrayList a, ArrayList b){
        int[] ret = new int[a.length + b.length + 1];
        for (int i =0; i < a.length; i++){
            for (int j=0; j < b.length; j++){
                ret[i+j] = a[i]*b[j];
            }
        }

        normalize(ret);

        return ret;
    }

    static void normalize(ArrayList a){
        a.add(0);
        for (int i=0; i<a.size()-1; i++){
            a.set(i+1, (int)a.get(i+1) + ((int)a.get(i)/10));
            a.set(i, (int)a.get(i)%10);
        }

        while(a.size() > 0 && (int)a.get(a.size()-1) == 0) a.remove(a.size()-1);
    }

}
