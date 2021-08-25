import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	final static int[] dr = {0, 0, -1, 1};
	final static int[] dc = {-1, 1, 0, 0};	
	
	static class Pos implements Comparable<Pos>{
		int r, c;
		int dist;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		public Pos(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		@Override
		public int compareTo(Pos o) {
			if(this.dist == o.dist) {
				if(this.r == o.r) {
					return this.c - o.c;
				}
				return this.r - o.r;
			}
			return this.dist - o.dist;
		}
	}
	
	static int[][] map;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		Pos shark = null;
		int size = 2;
		
		for(int i = 0 ; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j] == 9) {
					shark = new Pos(i, j, 0);
					map[i][j] = 0;
				}
			}			
		}
		
		int totaltime = 0;
		int eat = 0;
		while(true) {
			// 물고기 탐색
			Queue<Pos> move = new LinkedList<Pos>();
			move.offer(shark);			
			ArrayList<Pos> fishes = new ArrayList<Pos>();
			boolean[][] visited = new boolean[N][N];
			visited[shark.r][shark.c] = true; 
			
			while(!move.isEmpty()) {
				Pos p = move.poll();
				int r = p.r;
				int c = p.c;
				int dist = p.dist;
				Queue<Pos> temp = new LinkedList<Pos>();
				
				boolean next = true;
				for(int i = 0; i < 4; i++) {
					int newr = r + dr[i];
					int newc = c + dc[i];
					
					if(newr < 0 || newr == N || newc < 0 || newc == N) continue;
					if(visited[newr][newc]) continue;
					
					if(map[newr][newc] == 0 || map[newr][newc] == size) {
						visited[newr][newc] = true;
						temp.offer(new Pos(newr, newc, dist + 1));
					}
					else if(map[newr][newc] < size) {						
						visited[newr][newc] = true;
						fishes.add(new Pos(newr, newc, dist + 1));
						next = false;
					}
				}
				if(next) {
					while(!temp.isEmpty()) {
						move.offer(temp.poll());
					}
				}
			}			
			
			// 있으면 이동
			if(fishes.size() > 0) {
				Collections.sort(fishes);
				Pos fish = fishes.get(0);

				map[fish.r][fish.c] = 0;
				shark.r = fish.r;
				shark.c = fish.c;
				totaltime += fish.dist;
				eat++;
				
				if(eat == size) {
					size++;
					eat = 0;
				}
			}			
			// 없으면 종료
			else break;
		}
		
		System.out.println(totaltime);
		
	}
	
}
