import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] population;
	static boolean[][] adj;
	static int min;
	static int[] subarea1;
	static int[] subarea2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		population = new int[N+1];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(stk.nextToken());
		}
		
		adj = new boolean[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			stk = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(stk.nextToken());
			for(int c = 0; c < cnt; c++) {
				int j = Integer.parseInt(stk.nextToken());
				adj[i][j] = true;
			}
		}
		
		min = Integer.MAX_VALUE;		
		subarea1 = new int[N+1];
		subarea2 = new int[N+1];
		
		setArea(1, 0, 0);
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(min);
		
	}

	private static void setArea(int num, int idx1, int idx2) {

		if(num > N) {
			
			if(idx1 == 0 || idx2 == 0) return;
			
			if(checkAdj(Arrays.copyOf(subarea1, idx1)) && checkAdj(Arrays.copyOf(subarea2, idx2))) {
				int sum = 0;
				for(int i = 0; i < idx1; i++) {
					sum += population[subarea1[i]];
				}
				for(int i = 0; i < idx2; i++) {
					sum -=  population[subarea2[i]];
				}
				
				int diff = Math.abs(sum);
				if(diff < min) min = diff;				
			}
			
			return;
		}
		
		subarea1[idx1] = num;
		setArea(num+1, idx1+1, idx2);
		
		subarea2[idx2] = num;
		setArea(num+1, idx1, idx2+1);
	}

	
	private static boolean checkAdj(int[] subarea) {
		
		Queue<Integer>queue = new LinkedList<Integer>();
		queue.offer(subarea[0]);
		
		boolean[] visited = new boolean[N+1];
		visited[subarea[0]] = true;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int e : subarea) {
				if(adj[now][e] && !visited[e]) {
					queue.offer(e);
					visited[e] = true;
				}
			}
		}
		
		for(int e : subarea) {
			if(!visited[e]) return false;
		}
		
		return true;
	}
	
}
