import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] inputArr = input.readLine().split(" ");
        String[] inputW = input.readLine().split(" ");
        int n, w, L;
        n = Integer.parseInt(inputArr[0]);
        w = Integer.parseInt(inputArr[1]);
        L = Integer.parseInt(inputArr[2]);

        int[] weight = new int[n];
        for(int i = 0; i < n; i++){
            weight[i] = Integer.parseInt(inputW[i]);
        }

        int time = 0;
        int total = 0;
        Queue<Integer> bridge = new LinkedList<>();

        for(int i = 0; i < w; i++){
            bridge.add(0);
        }

        int t = 0;
        while(t < n){
            total -= bridge.poll();

            if (total + weight[t] <= L) {
                total += weight[t];
                bridge.add(weight[t]);
                t++;
            }
            else{
                bridge.add(0);
            }
            time++;
        }

        System.out.println(time+w);
    }
}
