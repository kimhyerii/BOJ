import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int[] dr = {1, 0, -1, 0};
	static final int[] dc = {0, -1, 0, 1};
	static int[] parents;
	static int N, M;
	static int[][] map;
	
	static class Pos{
		int x,y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Bridge implements Comparable<Bridge>{
		int land1;
		int land2;
		int distance;
		
		public Bridge(int land1, int land2, int distance) {
			this.land1 = land1;
			this.land2 = land2;
			this.distance = distance;
		}

		@Override
		public int compareTo(Bridge o) {
			return this.distance - o.distance;
		}

		@Override
		public String toString() {
			return "Bridge [land1=" + land1 + ", land2=" + land2 + ", distance=" + distance + "]";
		}				
	}

	
	static int find(int s) {
		if(parents[s] == s) return s;
		else return parents[s] = find(parents[s]);
	}
	
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa == pb) return false;
		else {
			parents[pb] = pa;
			return true;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		ArrayList<int[][]> islands = new ArrayList<>();
		boolean[][] visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					Queue<Pos> queue = new LinkedList<Pos>();
					visited[i][j] = true;
					queue.offer(new Pos(i, j));
					
					int[][] island = new int[N][M];
					island[i][j] = 1;
					
					while(!queue.isEmpty()) {
						Pos now = queue.poll();
						
						for(int d = 0; d < 4; d++) {
							int newr = now.x + dr[d];
							int newc = now.y + dc[d];
							
							if(newr < 0 || newr >= N || newc < 0 ||newc >= M) continue;
							if(map[newr][newc] != 1) continue;
							if(visited[newr][newc]) continue;
							
							queue.offer(new Pos(newr, newc));
							island[newr][newc] = 1;
							visited[newr][newc] = true;
						}
					}
					islands.add(island);
				}
			}
		}
				
		int num = islands.size();		
		ArrayList<Bridge> bridgelist = new ArrayList<Bridge>();
		
		for(int i = 0; i < num; i++) {
			for(int j = i+1; j < num; j++) {
				
				int len = getDistance(islands.get(i), islands.get(j));
				
				if(len != -1) {
					bridgelist.add(new Bridge(i, j, len));
				}
			}
		}
		
		Collections.sort(bridgelist);

		
		parents = new int[num];
		for(int i = 0; i < num; i++) parents[i] = i;
		
		int total = 0;
		int cnt = 0;
		for(int i = 0; i < bridgelist.size(); i++) {
			Bridge bridge = bridgelist.get(i);
			if(union(bridge.land1, bridge.land2)) {
				total += bridge.distance;
				if(++cnt == num - 1) break;
			}			
		}
		
		if(cnt == num - 1) System.out.println(total);		
		else System.out.println(-1);
	}

	private static int getDistance(int[][] is, int[][] is2) {
		int min = Integer.MAX_VALUE;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(is[r][c] == 1) {
					for(int i = 0; i < 4; i++) {
						int row = r + dr[i];
						int col = c + dc[i];
						int count = 0;
						boolean meet = false;
						while(row>= 0 && row < N && col >= 0 && col < M) {
							if(is[row][col] == 1) break;
							if(is2[row][col] == 1) {
								meet = true;
								break;
							}
							if(map[row][col] == 1) break;

							count++;
							row += dr[i];
							col += dc[i];							
						}
						if(meet && count < min && count > 1) min = count;
					}
				}
			}
		}
		
		if(min == Integer.MAX_VALUE) return -1;
		
		return min;
	}
	
}
