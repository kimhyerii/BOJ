import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

	static class Pos{
		int h, r, c;
		public Pos(int h, int r, int c) {
			this.h = h;
			this.r = r;
			this.c = c;
		}
	}
	
	static final int[] dh = {0, 0, 0, 0, 1, -1};
	static final int[] dr = {1, 0, -1, 0, 0, 0};
	static final int[] dc = {0, -1, 0, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(stk.nextToken());
		int N = Integer.parseInt(stk.nextToken());
		int H = Integer.parseInt(stk.nextToken());
		
		int[][][] tomatos = new int[H][N][M];
		int total = 0;
		Queue<Pos> queue = new LinkedList<Pos>();
		for(int h = 0; h < H; h++) {
			for(int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < M; j++) {
					tomatos[h][i][j] = Integer.parseInt(stk.nextToken());
					if(tomatos[h][i][j] != -1) total++;
					if(tomatos[h][i][j] == 1) queue.offer(new Pos(h, i, j));
				}			
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
				
				for(int j = 0; j < 6; j++) {
					int h = now.h + dh[j];
					int r = now.r + dr[j];
					int c = now.c + dc[j];
					
					if(h < 0 || h >= H || r < 0 || r >= N || c < 0 || c >= M) continue;
					if(tomatos[h][r][c] != 0) continue;
					
					tomatos[h][r][c] = 1;
					queue.offer(new Pos(h, r, c));					
				}				
			}
			day++;
		}
		
		if(count == total) System.out.println(day);
		else System.out.println(-1);
	}
	
}
