import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class JLIS {

    int[] inputA;
    int[] inputB;
    int[][] cache;

    JLIS(String[] inputA, String[] inputB){
        cache = new int[inputA.length+1][inputB.length+1];
        int index = 0;
        this.inputA = new int[inputA.length];
        for (String input :inputA){
            this.inputA[index] = Integer.parseInt(input);
            index++;
        }
        index = 0;
        this.inputB = new int[inputB.length];
        for (String input :inputB){
            this.inputB[index] = Integer.parseInt(input);
            index++;
        }
    }

    int getMaxSequence(int indexA, int indexB){
        if(cache[indexA+1][indexB+1] != 0) return cache[indexA+1][indexB+1];
        int ret = 2;
        int elementA = indexA == -1 ? Integer.MIN_VALUE : this.inputA[indexA];
        int elementB = indexB == -1 ? Integer.MIN_VALUE : this.inputB[indexB];
        int maxElement = Math.max(elementA, elementB);
        for(int nextA = indexA + 1; nextA < inputA.length; nextA++){
            if(maxElement < this.inputA[nextA]){
                ret = Math.max(ret, getMaxSequence(nextA, indexB) + 1);
            }
        }
        for(int nextB = indexB+ 1; nextB < inputB.length; nextB++){
            if(maxElement < this.inputB[nextB]) {
                ret = Math.max(ret, getMaxSequence(indexA, nextB) + 1);
            }
        }
        if(cache[indexA+1][indexB+1] < ret) cache[indexA+1][indexB+1] = ret;
        return ret;
    }


    public static void main(String[] args) {
        try{
            System.setIn(new FileInputStream("C:/Users/user/IdeaProjects/AlgorithmPractice/resources/JLIS/JLIS.in"));

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int caseCnt = Integer.parseInt(br.readLine());
            for (int i = 0; i < caseCnt; i++){
                br.readLine();
                String[] inputStrA = br.readLine().split(" ");
                String[] inputStrB = br.readLine().split(" ");
                JLIS JLIS = new JLIS(inputStrA, inputStrB);
                int ret = JLIS.getMaxSequence(-1, -1);
                System.out.println(ret-2);
            }
        }catch(IOException ie){
            System.out.println("i/o exception occured.");
        }
    }
}
