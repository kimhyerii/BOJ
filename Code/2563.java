import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		final int SIZE = 100;
		final int SIZE_COLORED = 10;
		boolean[][] paper = new boolean[SIZE][SIZE];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int count = 0;
		
		for(int i = 0; i < n; i++) { 
			String[] input = br.readLine().split(" ");
			int start_r = Integer.parseInt(input[0]);
			int start_c = Integer.parseInt(input[1]);
			
			for(int r = start_r; r < start_r + SIZE_COLORED; r++) {
				for(int c = start_c; c < start_c + SIZE_COLORED; c++) {
					if(!paper[r][c]) {
						paper[r][c] = true;
						count++;
					}
				}
			}
		}
		
		System.out.println(count);
	
	}
}
