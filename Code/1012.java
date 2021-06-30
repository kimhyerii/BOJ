import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static class Pair{
        int first, second;

        Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++){
            String[] input = br.readLine().split(" ");
            int row = Integer.parseInt(input[0]);
            int col = Integer.parseInt(input[1]);
            int k = Integer.parseInt(input[2]);

            int[][] field = new int[row][col];
            int[][] visited = new int[row][col];

            for(int j = 0; j < k; j++){
                String[] pos = br.readLine().split(" ");
                field[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])] = 1;
            }

            int count = 0;
            for(int r = 0; r < row; r++){
                for(int c = 0; c < col; c++){
                    if(field[r][c] == 0) continue;
                    if(visited[r][c] == 1) continue;

                    count++;
                    Queue<Pair> queue = new LinkedList<>();
                    queue.add(new Pair(r, c));
                    visited[r][c] = 1;

                    while(!queue.isEmpty()){
                        Pair pos = queue.poll();

                        for(int d = 0; d < 4; d++){
                            int newR = pos.first + dr[d];
                            int newC = pos.second + dc[d];

                            if(newR < 0 || newR >= row) continue;
                            if(newC < 0|| newC >= col) continue;
                            if(field[newR][newC] == 0) continue;
                            if(visited[newR][newC] == 1) continue;

                            queue.add(new Pair(newR, newC));
                            visited[newR][newC] = 1;
                        }
                    }
                }
            }
            answer.append(Integer.toString(count)).append("\n");
        }
        System.out.println(answer);
    }
}
