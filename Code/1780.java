import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] paper;
	static int[] count;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int size = Integer.parseInt(br.readLine());
		paper = new int[size][size];
		count = new int[size];
		
		for(int i = 0; i < size; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for(int j = 0; j < size; j++) {
				paper[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		checknum(0, 0, size);
		
		for(int i = 0; i < 3; i++) {
			System.out.println(count[i]);
		}
		
	}
	
	public static void checknum(int startr, int startc, int size) {
		final int check = paper[startr][startc];
		
		for(int r = startr; r < startr + size; r++) {
			for(int c = startc; c < startc + size; c++) {

				if(paper[r][c] != check) {
					int newsize = size/3;
					for(int newr = startr; newr < startr + size; newr += newsize) {
						for(int newc = startc; newc < startc + size; newc += newsize) {
							checknum(newr, newc, newsize);
						}
						
					}
					
					return;
				}
			}
		}
		
		count[check + 1]++;
	}
	
}
