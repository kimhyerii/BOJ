import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int stack[] = new int[10001];
        int ptr = -1;
        int n;
        StringBuilder answer = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++){
            String input = br.readLine();
            String[] inputArr = input.split(" ");
            String cmd = inputArr[0];

            if(cmd.equals("push")){
                ptr++;
                stack[ptr] = Integer.parseInt(inputArr[1]);
            }

            else if(cmd.equals("pop")){
                if(ptr == -1){
                    answer.append("-1\n");
                }
                else{
                    answer.append(Integer.toString(stack[ptr])).append("\n");
                    ptr--;
                }
            }

            else if(cmd.equals("size")){
                answer.append(Integer.toString(ptr + 1)).append("\n");
            }

            else if(cmd.equals("empty")){
                if(ptr == -1){
                    answer.append("1\n");
                }
                else{
                    answer.append("0\n");
                }
            }

            else if(cmd.equals("top")){
                if(ptr == -1){
                    answer.append("-1\n");
                }
                else{
                    answer.append(Integer.toString(stack[ptr])).append("\n");
                }
            }
        }
        System.out.println(answer);
    }
}

