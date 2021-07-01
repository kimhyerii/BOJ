import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        int[][] arr = new int[n][2];

        for(int i = 0; i < n; i++){
            String[] temp = input.readLine().split(" ");
            arr[i][0] = Integer.parseInt(temp[0]);
            arr[i][1] = Integer.parseInt(temp[1]);
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]) {
                    return Integer.compare(o1[0], o2[0]);
                }
                else{
                    return Integer.compare(o1[1], o2[1]);
                }
            }
        });

        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < n; i++){
            answer.append(Integer.toString(arr[i][0]))
                    .append(" ")
                    .append(Integer.toString(arr[i][1]))
                    .append("\n");
        }

        System.out.println(answer);
    }
}
