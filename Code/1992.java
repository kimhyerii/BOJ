import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	final static int[] dr = {0, 0, 1, 1};
	final static int[] dc = {0, 1, 0, 1};
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {				
				arr[i][j] = input.charAt(j) - '0';
			}			
		}
		
		System.out.println(recur(0, 0, N));
		
	}
	
	public static String recur(int startr, int startc, int n) {
		StringBuilder sb = new StringBuilder();

		if(n == 2) {
			int sum = 0;
			sb.append("(");
			for(int i = 0; i < 4; i++) {
				sum += arr[startr + dr[i]][startc + dc[i]];
				sb.append(arr[startr + dr[i]][startc + dc[i]]);
			}
			sb.append(")");
			
			if(sum == 0) return "0";
			if(sum == 4) return "1";
			return sb.toString();
		}
		
		int nxtN = n / 2;
		for(int i = 0; i < 4; i++) {
			sb.append(recur(startr + nxtN * dr[i], startc + nxtN * dc[i], nxtN));
		}

		if(sb.toString().equals("0000")) return "0";
		if(sb.toString().equals("1111")) return "1";
		
		sb.insert(0, "(");
		sb.append(")");
		
		return sb.toString();
	}
	
}
