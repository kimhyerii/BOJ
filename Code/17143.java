import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static final int[] dr = {0, -1, 1, 0, 0};
	static final int[] dc = {0, 0, 0, 1, -1};
	
	static class Shark{
		int r, c;
		int speed;
		int direction;
		int size;
		
		public Shark(int r, int c, int speed, int direction, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}		
	}
	
	static int R, C, M;
	static int sum;
	static Shark[][] map;
	
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		map = new Shark[R+1][C+1];
		for(int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			int speed = Integer.parseInt(stk.nextToken());
			int direction = Integer.parseInt(stk.nextToken());
			int size = Integer.parseInt(stk.nextToken());
			
			map[r][c] = new Shark(r, c, speed, direction, size);
		}
		
		sum = 0;
		for(int col = 1; col <= C; col++) {
			getfish(col);
			move();
		}
		System.out.println(sum);
	}

	private static void getfish(int col) {
		for(int row = 1; row <= R; row++) {
			if(map[row][col] != null) {
				sum += map[row][col].size;
				map[row][col] = null;
				return;
			}
		}	
	}
	
	private static void move() {
		
		Shark[][] newmap = new Shark[R+1][C+1];
		
		for(int row = 1; row <= R; row++) {
			for(int col = 1; col <= C; col++) {
//				if(map[row][col] == null || map[row][col].moved) continue;
				if(map[row][col] == null) continue;
				Shark shark = map[row][col];				
				int newr = row;
				int newc = col;

				// 다음 위치
				if(shark.direction == 1 || shark.direction == 2) {
					for(int k = 0; k < shark.speed; k++) {
						newr += dr[shark.direction];
						if(newr <= 0) {
							newr = 2;
							shark.direction = 2;
						}
						else if(newr > R) {
							newr = R-1;
							shark.direction = 1;							
						}
					}
				}
				else if(shark.direction == 3 || shark.direction == 4) {
					for(int k = 0; k < shark.speed; k++) {
						newc += dc[shark.direction];
						if(newc <= 0) {
							newc = 2;
							shark.direction = 3;
						}
						else if(newc > C) {
							newc = C-1;
							shark.direction = 4;							
						}
					}
				}
				
				
				// 다른 상어 여부
				if(newmap[newr][newc] != null) {
					if(shark.size < newmap[newr][newc].size) {
						continue;
					}
				}
				
				// 이동
				newmap[newr][newc] = shark;
				shark.r = newr;
				shark.c = newc;
			}
		}		
		
		map = newmap;
	}

}
