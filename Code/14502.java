import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int[] dr = {1, 0, -1, 0};
	static final int[] dc = {0, -1, 0, 1};
		
	static class Pos{
		int x,y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		Pos[] list = new Pos[N*M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				list[i*M + j] = new Pos(i, j);
			}
		}
		
		ArrayList<Pos[]> wall = new ArrayList<Pos[]>();
		for(int i = 0; i < N*M; i++) {
			for(int j = i + 1; j < N*M; j++) {
				for(int k = j + 1; k < N*M; k++) {
					wall.add(new Pos[]{list[i], list[j], list[k]});
				}
			}
		}

		int max = 0;
		
		for(int i = 0; i < wall.size(); i++) {
			int[][] wallmap = new int[N][M];
			for(int r = 0; r < N; r++) {
				wallmap[r] = Arrays.copyOf(map[r], M);
			}
			
			boolean newmap = true;
			for(int j = 0; j < 3; j++) {
				int wx = wall.get(i)[j].x;
				int wy = wall.get(i)[j].y;
				
				if(wallmap[wx][wy] != 0) {
					newmap = false; 
					break;					
				}
				wallmap[wx][wy] = 1;
			}			
			if(!newmap) continue;
			
			
			boolean[][] visited = new boolean[N][M];
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(wallmap[r][c] == 2 && !visited[r][c]) {
						Queue<Pos> queue = new LinkedList<Pos>();
						
						queue.offer(new Pos(r, c));
						visited[r][c] = true;
						
						while(!queue.isEmpty()){
							Pos now = queue.poll();
							
							for(int d = 0; d < 4; d++) {
								int newr = now.x + dr[d];
								int newc = now.y + dc[d];
								
								if(newr < 0 || newr >= N || newc < 0 ||newc >= M) continue;
								if(wallmap[newr][newc] != 0) continue;
								if(visited[newr][newc]) continue;
								
								wallmap[newr][newc] = 2;
								visited[newr][newc] = true;
								queue.offer(new Pos(newr,newc));
							}
						}						
					}
				}
			}
			
			int count = 0;
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(wallmap[r][c] == 0) count++;					
				}
			}
			if(max < count) {
				max = count;		
				
			}
		}
		
		System.out.println(max);
		
	}
	
}
