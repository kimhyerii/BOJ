import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        int[] arr = new int[10000001];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(input.readLine());
        }

        Arrays.sort(arr, 0, n);

        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < n; i++){
            answer.append(Integer.toString(arr[i])).append("\n");
        }

        System.out.println(answer);
    }
}
