import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        StringBuilder answer = new StringBuilder();
        answer.append("<");

        ArrayList<Integer> arr = new ArrayList<>();
        int idx = 0;
        for(int i = 1; i <= n; i++){
            arr.add(i);
        }

        for(int i = 0; i < n; i++){
            idx += (k - 1);
            idx = idx % arr.size();
            answer.append(Integer.toString(arr.get(idx)));
            arr.remove(idx);
            if(!arr.isEmpty()){
                answer.append(", ");
            }
        }
        answer.append(">");
        System.out.println(answer);
    }
}
