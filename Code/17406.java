import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n, m, k;
	static int[][] arr;
	static ArrayList<int[]> operators;
	static boolean[] isSelected;
	static int[] idxs;
	static int minsum;
	final static int[] dirr = {0, 1, 0, -1}; // left, down, right, up
	final static int[] dirc = {-1, 0, 1, 0}; // left, down, right, up

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		
		arr = new int[n + 1][m + 1];
		for(int i = 1; i < n + 1; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 1; j < m + 1; j++) {
				arr[i][j] = Integer.parseInt(stk.nextToken());
			}
		}

		int r, c, s;
		operators = new ArrayList<int[]>();
		
		for(int i = 0; i < k; i++) {
			stk = new StringTokenizer(br.readLine());
			r = Integer.parseInt(stk.nextToken());
			c = Integer.parseInt(stk.nextToken());
			s = Integer.parseInt(stk.nextToken());			
			
			operators.add(new int[]{r, c, s});
		}
		
		idxs = new int[k];
		isSelected = new boolean[k];
		minsum = Integer.MAX_VALUE;
		
		order(0);
		
		System.out.println(minsum);
		
	}
	
	public static void order(int count) {

		if(count == k) {
			
			int[][] arrcopy = new int[n + 1][m + 1];
			for(int r = 0; r < n + 1; r++) {
				arrcopy[r] = Arrays.copyOf(arr[r], m + 1);
			}
			
			for(int j = 0; j < k; j++) {
				int[] rcs = operators.get(idxs[j]);
				arrcopy = rotate(arrcopy, rcs[0], rcs[1], rcs[2]);
			}
			
			int sum = getsum(arrcopy);
			minsum = (sum < minsum) ? sum : minsum;
			return;
		}
		
		for(int i = 0; i < k; i++) {
			if(isSelected[i]) continue;
			
			idxs[count] = i;
			isSelected[i] = true;			
			order(count + 1);
			
			isSelected[i] = false;
		}
	}
	
	public static int[][] rotate (int[][]arr, int r, int c, int s) {
		
		for(int i = 1; i <= s; i++) {
			
			int row = r - i;
			int col = c + i;
			int temp = arr[row][col];
			int dir = 0;
			
			while(dir < 4) {
			
				int newr = row + dirr[dir];
				int newc = col + dirc[dir];
				
				if(newr >= r - i && newr <= r + i && newc >= c - i && newc <= c + i) {
					arr[row][col] = arr[newr][newc];
					row = newr;
					col = newc;
				}				
				else {
					dir++;
				}		
			}
			
			arr[row + 1][col] = temp;
		}					
				
		return arr;
	}
	
	public static int getsum(int[][]arr) {
		int minsum = Integer.MAX_VALUE;
		
		for(int i = 1; i < n + 1; i++) {
			int sum = 0;
			for(int j = 1; j < m + 1; j++) {
				sum += arr[i][j];
			}
			minsum = (sum < minsum) ? sum : minsum;
		}
		return minsum;
	}
	
	
}
