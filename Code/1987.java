import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	final static int[] dr = {-1, 1, 0, 0};
	final static int[] dc = {0, 0, -1, 1};
	
	static int R, C;
	static int maxNum;
	static char[][] board;
	static boolean[] isChecked;
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		
		board = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		maxNum = 0;
		isChecked = new boolean['Z' - 'A' + 1];
		
		route(0, 0, 1);
		
		System.out.println(maxNum);
	}
	
	public static void route(int r, int c, int count) {
		isChecked[board[r][c] - 'A'] = true;
		
		for(int d = 0; d < 4; d++) {
			int newr = r + dr[d];
			int newc = c + dc[d];
			
			if(newr < 0 || newr >= R || newc < 0 || newc >= C ||
					isChecked[board[newr][newc] - 'A']) {
				maxNum = Math.max(count, maxNum);
			}
			else {
				route(newr, newc, count + 1);
			}
		}
		
		isChecked[board[r][c] - 'A'] = false;		
	}
	
}
