import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, R;
	static int[][] arr;
	
	//clockwise : d, r, u, l
	//counterclockwise : r, d, l, u
	static final int[][] dr = {{1, 0, -1, 0}, {0, 1, 0, -1}};
	static final int[][] dc = {{0, 1, 0, -1}, {1, 0, -1, 0}};
	static final int CLOCK = 0;
	static final int COUNTERCLOCK = 1;

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());		
		
		arr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
				
		stk = new StringTokenizer(br.readLine());
		for(int i = 0; i < R; i++) {
			int input = Integer.parseInt(stk.nextToken());

			switch(input) {
			case 1:
				switchrow();
				break;

			case 2:
				switchcol();
				break;
			
			case 3:
				rotate(CLOCK);
				break;
				
			case 4:
				rotate(COUNTERCLOCK);
				break;
				
			case 5:
				move(CLOCK);
				break;
				
			case 6:
				move(COUNTERCLOCK);
				break;
			}
			
		}
		
		for(int[] output : arr) {
			for(int e : output) {
				System.out.print(e + " ");
			}
			System.out.println();
		}
	}
	
	public static void switchrow() {
		for(int i = 0; i < N - 1 - i; i++) {
			int[] temp = arr[i];
			arr[i] = arr[N - 1 - i];
			arr[N - 1 - i] = temp;
		}
	}
	
	public static void switchcol() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M - 1 - j; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[i][M - 1 - j];
				arr[i][M - 1 - j] = temp;
			}
		}
	}
	
	public static void rotate(int dir) {

		int[][] temp = new int[M][N];
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(dir == CLOCK) temp[i][j] = arr[N - 1 - j][i];
				else temp[i][j] = arr[j][M - 1 - i];
			}
		}
		arr = temp;
		
		int t = M;
		M = N;
		N = t;		
	}
	
	public static void move(int dir) {
		int len_r = N / 2;
		int len_c = M / 2;
		
		for(int r = 0; r < len_r; r++) {
			for(int c = 0; c < len_c; c++) {
				int row = r;
				int col = c;
				
				int temp = arr[row][col];

				for(int k = 0; k < 4; k++) {
					int newr = row + len_r * dr[dir][k];
					int newc = col + len_c * dc[dir][k];
					
					if(k < 3) {
						arr[row][col] = arr[newr][newc];
						row = newr;
						col = newc;
					}
					else {
						arr[row][col] = temp;						
					}
				}				
			}
		}
	}
	
	
}
