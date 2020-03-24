import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LIS {
    int[] inputNums;
    int[] cache;

    LIS(int[] input){
        this.inputNums = input;
        cache = new int[input.length];
        Arrays.fill(cache, -1);
    }

    int getMaxLength(){
        int max = 0;
        for (int i=0; i<inputNums.length; i++){
            int ref = getMaxSeqSize(i);
            if(ref > max) max = ref;
        }
        return max;
    }

    int getMaxSeqSize(int index){
        int ret = 1;
        if(cache[index] != -1) return cache[index];
        if(index < inputNums.length-1){
            for (int next = index+1; next < inputNums.length; next++){
                if(inputNums[index] < inputNums[next]){
                    ret = Math.max(ret, getMaxSeqSize(next) + 1);
                }
            }
        }
        if(ret > cache[index]) cache[index] = ret;
        return cache[index];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("C:/Users/user/IdeaProjects/AlgorithmPractice/resources/LIS/LIS.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseCnt = Integer.parseInt(br.readLine());
        for (int caseNo = 1; caseNo <= caseCnt; caseNo++){
            int inputSize = Integer.parseInt(br.readLine());
            String[] inputStr = br.readLine().split(" ");
            int[] input = new int[inputStr.length];
            for(int i=0; i<inputStr.length; i++)
                input[i] = Integer.parseInt(inputStr[i]);
            LIS LIS = new LIS(input);
            int getMaxSequenceSize = LIS.getMaxLength();
            System.out.println(getMaxSequenceSize);
        }

    }
}
