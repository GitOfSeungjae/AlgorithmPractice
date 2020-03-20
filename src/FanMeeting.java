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
            for (int i = 0; i < idolStr.length(); i++){
                if(idolStr.charAt(i) == 'F') idols.add(0);
                else idols.add(1);
            }
            for (int i = 0; i < fanStr.length(); i++){
                if(fanStr.charAt(fanStr.length() - i - 1) == 'F') fans.add(0);
                else fans.add(1);
            }
            ArrayList<Integer> multiply = karatuba(idols, fans);

            int allHugs = 0;
            for(int i = idols.size()-1; i < fans.size(); ++i){
                if(multiply.size() < i + 1) {
                    allHugs++;
                    continue;
                }
                if((int)multiply.get(multiply.size()-1-i) == 0) allHugs++;
            }

            multiply.stream().forEach(i -> System.out.print(i + " "));
            System.out.println();
            System.out.println(allHugs);

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

        addTo(a0, a1, 0); addTo(b0, b1, 0);

        ArrayList z1 = karatuba(a0, b0);

        subFrom(z1, z0);
        subFrom(z1, z2);

        ArrayList ret = new ArrayList();
        addTo(ret, z0, 0);
        addTo(ret, z1, half);
        addTo(ret, z2, half+half);


       return ret;
    }

    static ArrayList copyArray(ArrayList src, int from, int to){
        ArrayList dest = new ArrayList();
        if(from == to) return null;
        for(int i=from; i < to; i++){
            dest.add(src.get(i));
        }
        return dest;
    }

    static void addTo(ArrayList<Integer> a, ArrayList<Integer> b, int k){
        if(a.size() < b.size() + k){
            while(a.size() == b.size() + k) a.add(0);
        }
        for(int i=0; i<b.size();i++) a.set(i+k,a.get(i) + b.get(i));
        normalize(a);
    }

    static void subFrom(ArrayList<Integer> a, ArrayList<Integer> b){
        for(int i = 0; i < b.size(); i++) a.set(i, a.get(i) - b.get(i));
        normalize(a);
    }



    static ArrayList multiply(ArrayList<Integer> a, ArrayList<Integer> b){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i =0; i < a.size(); i++){
            for (int j=0; j < b.size(); j++){
                if(ret.size() <= i + j) ret.add(a.get(i) * b.get(j));
                else ret.set(i+j, ret.get(i+j) + (a.get(i) * b.get(j)));
            }
        }
        normalize(ret);
        return ret;
    }

    static void normalize(ArrayList<Integer> a){
        a.add(0);
        for (int i=0; i<a.size()-1; i++){
            if((int)a.get(i) < 0){
                int borrow = (Math.abs((int)a.get(i)) + 9) / 10;
                a.set(i+1, (int)a.get(i+1) - borrow);
                a.set(i, borrow * 10 - (int)a.get(i));

            }else{
                a.set(i+1, (int)a.get(i+1) + ((int)a.get(i)/10));
                a.set(i, (int)a.get(i)%10);
            }
        }

        if (a.get(a.size() - 1) == 0)
            a.remove(a.size() - 1);
    }

}
