import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Pos{
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static final int[] dr = {1, 0, -1, 0};
	static final int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(stk.nextToken());
		int N = Integer.parseInt(stk.nextToken());
		
		int[][] tomatos = new int[N][M];
		int total = 0;
		Queue<Pos> queue = new LinkedList<Pos>();
		for(int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());

			for(int j = 0; j < M; j++) {
				tomatos[i][j] = Integer.parseInt(stk.nextToken());
				if(tomatos[i][j] != -1) total++;
				if(tomatos[i][j] == 1) queue.offer(new Pos(i, j));
			}			
		}
		
		int day = 0;
		int count = 0;
		while(!queue.isEmpty()) {
			int len = queue.size();

			count += len;
			if(count == total) break;
			
			for(int i = 0; i < len; i++) {
				Pos now = queue.poll();
				
				for(int j = 0; j < 4; j++) {
					int r = now.r + dr[j];
					int c = now.c + dc[j];
					
					if(r < 0 || r >= N || c < 0 || c >= M) continue;
					if(tomatos[r][c] != 0) continue;
					
					tomatos[r][c] = 1;
					queue.offer(new Pos(r, c));					
				}				
			}
			day++;
		}
		
		if(count == total) System.out.println(day);
		else System.out.println(-1);
	}
	
}
