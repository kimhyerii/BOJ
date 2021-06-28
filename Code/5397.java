import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();

        for(int i = 0; i < n; i++){
            String log = br.readLine();
            StringBuilder pw = new StringBuilder();
            Stack<Character> front = new Stack<Character>();
            Stack<Character> back = new Stack<Character>();

            for(int idx = 0; idx < log.length(); idx++) {
                char ch = log.charAt(idx);

                if(ch == '<'){
                    if(!front.isEmpty()){
                        back.push(front.pop());
                    }
                }

                else if(ch == '>'){
                    if(!back.isEmpty()){
                        front.push(back.pop());
                    }
                }

                 else if(ch == '-'){
                    if(!front.isEmpty()){
                        front.pop();
                    }
                }

                else{
                    front.push(ch);
                }
            }

            while(!front.isEmpty()){
                back.push(front.pop());
            }
            while(!back.isEmpty()){
                pw.append(back.pop());
            }

            answer.append(pw.toString());
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
