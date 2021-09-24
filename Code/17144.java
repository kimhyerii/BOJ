import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R;
	static int C;
	static int[][] room;
	static int up = -1;
	static int down = -1;
	
	static final int[] dr = {1, 0, -1, 0};
	static final int[] dc = {0, -1, 0, 1};
	
	
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		int T = Integer.parseInt(stk.nextToken());
		
		room = new int[R][C];
		for(int i = 0; i < R; i++) {
			stk = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(stk.nextToken());
				if(room[i][j] == -1) {
					room[i][j] = 0;
					if(up == -1) up = i;
					else down = i;
				}
			}			
		}
		
		
		for(int t = 0; t < T; t++) {
			spread();
			clean();
		}
		
		int sum = 0;
		for(int i = 0; i < R; i++) {			
			for(int j = 0; j < C; j++) {
				sum += room[i][j];
			}
		}
		System.out.println(sum);
		
	}

	private static void spread() {
		int[][] temp = new int[R][C];
		
		for(int i = 0; i < R; i++) {			
			for(int j = 0; j < C; j++) {
				if(room[i][j] != 0) {
					
					int spread = room[i][j]/5;
					int count = 0;
					
					for(int d = 0; d < 4; d++) {
						int r = i + dr[d];
						int c = j + dc[d];
						
						if(r < 0 || r == R || c < 0 || c == C) continue;
						if(r == up && c == 0) continue;
						if(r == down && c == 0) continue;
						
						temp[r][c] += spread;
						count++;
					}
					temp[i][j] += room[i][j] - spread*count;
				}
			}
		}
		
		room = temp;
	}
	

	private static void clean() {
		//counterclockwise
		for(int r = up - 1; r >= 1; r--) {
			room[r][0] = room[r-1][0];
		}
		
		for(int c = 0; c < C - 1; c++) {
			room[0][c] = room[0][c+1];
		}
		
		for(int r = 0; r <= up - 1; r++) {
			room[r][C-1] = room[r+1][C-1];
		}
		
		for(int c = C - 1; c >= 1; c--) {
			room[up][c] = room[up][c-1];
		}		
		
		
		//clockwise
		for(int r = down + 1; r < R - 1; r++) {
			room[r][0] = room[r+1][0];
		}
		
		for(int c = 0; c < C - 1; c++) {
			room[R-1][c] = room[R-1][c+1];
		}
		
		for(int r = R-1; r >= down + 1; r--) {
			room[r][C-1] = room[r-1][C-1];
		}
		
		for(int c = C - 1; c >= 1; c--) {
			room[down][c] = room[down][c-1];
		}	
	}
	
}
