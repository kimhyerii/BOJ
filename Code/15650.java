import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] num = new int[9];
    static boolean[] used = new boolean[9];
    static int n, m;
    static StringBuilder answer = new StringBuilder();

    public static void bt(int j, int k){
        if(k == m){
            for(int i = 0; i < m; i++){
                answer.append(Integer.toString(num[i])).append(" ");
            }
            answer.append("\n");
        }

        else{
            for(int t = j; t <= n; t++){
                num[k] = t;
                bt(t+1, k+1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String[] inputArr = input.readLine().split(" ");
        n = Integer.parseInt(inputArr[0]);
        m = Integer.parseInt(inputArr[1]);

        bt(1,0);
        System.out.println(answer);
    }
}
