import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] zero = new int[41];
    static int[] one = new int[41];
    static StringBuilder answer = new StringBuilder();

    public static void fibonacci(int n){
        if(n == 0){
            zero[0] = 1;
            one[0] = 0;
        }

        else if (n == 1) {
            zero[1] = 0;
            one[1] = 1;
        }

        else {
            if (zero[n] == -1 || one[n] == -1) {
                if (zero[n - 1] == -1 || one[n - 1] == -1) {
                    fibonacci(n - 1);
                }
                if (zero[n - 2] == -1 || one[n - 2] == -1) {
                    fibonacci(n - 2);
                }
            }
            zero[n] = zero[n - 1] + zero[n - 2];
            one[n] = one[n - 1] + one[n - 2];
        }
    }

    public static void main(String[] args) throws IOException {
        Arrays.fill(zero, -1);
        Arrays.fill(one, -1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[] arr = new int[t];

        for(int i = 0; i < t; i++){
            int num = Integer.parseInt(br.readLine());
            fibonacci(num);
            answer.append(Integer.toString(zero[num])).append(" ").append(one[num]).append("\n");
        }

        System.out.println(answer);
    }
}
