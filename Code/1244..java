import java.util.Scanner;

public class Main{
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] switchstate = new int[n+1];
		
		for(int i = 1; i <=n; i++) {
			switchstate[i] = sc.nextInt();
		}
		
		int students = sc.nextInt();
		
		for(int i = 0; i < students; i++) {
			int gender = sc.nextInt();
			int num = sc.nextInt();
			
			if(gender == 1) {
				for(int idx = num; idx <= n; idx += num) {
					switchstate[idx] = (switchstate[idx] + 1) % 2;
				}
			}
			
			else {
				switchstate[num] = (switchstate[num] + 1) % 2;
				for(int gap = 1; num + gap <= n && num - gap > 0; gap++) {
					if(switchstate[num + gap] == switchstate[num - gap]) {
						switchstate[num + gap] = (switchstate[num + gap] + 1) % 2;
						switchstate[num - gap] = (switchstate[num - gap] + 1) % 2;
					}
					else break;
				}
			}
		}
		
		int i = 1;
		while(i <= n) {
			int j = 0;
			while(i + j <= n && j < 20) {
				System.out.print(switchstate[i + (j++)] + " ");
			}
			i += j;
			System.out.println();
		}
	}
	
}
