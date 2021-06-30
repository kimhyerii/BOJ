import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int queue[] = new int[2000001];
        int front = -1, back = -1;
        int n;
        StringBuilder answer = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++){
            String input = br.readLine();
            String[] inputArr = input.split(" ");
            String cmd = inputArr[0];

            if(cmd.equals("push")){
                back++;
                queue[back] = Integer.parseInt(inputArr[1]);
            }

            else if(cmd.equals("pop")){
                if(front == back){
                    answer.append("-1\n");
                }
                else{
                    front++;
                    answer.append(Integer.toString(queue[front])).append("\n");
                }
            }

            else if(cmd.equals("size")){
                answer.append(Integer.toString(back - front)).append("\n");
            }

            else if(cmd.equals("empty")){
                if(front == back){
                    answer.append("1\n");
                }
                else{
                    answer.append("0\n");
                }
            }

            else if(cmd.equals("front")){
                if(front == back){
                    answer.append("-1\n");
                }
                else{
                    answer.append(Integer.toString(queue[front+1])).append("\n");
                }
            }

            else if(cmd.equals("back")){
                if(front == back){
                    answer.append("-1\n");
                }
                else{
                    answer.append(Integer.toString(queue[back])).append("\n");
                }
            }
        }
        System.out.println(answer);
    }
}
