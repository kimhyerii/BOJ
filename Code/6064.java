import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
			
		StringBuilder answer = new StringBuilder();
		for(int i = 0; i < test; i++) {
			int m, n, x, y;
			m = sc.nextInt();
			n = sc.nextInt();
			x = sc.nextInt();
			y = sc.nextInt();
			
			int a = m;
			int b = n;
			while(b > 0) {
				int temp = b;
				b = a % b;
				a = temp;
			}
						
			int gcd;
			gcd = m / a * n;
			
			int num = -1;
			
			while(x <= gcd && y <=gcd) {
				if(x > y) {
					y += n;
				}
				else if(x < y) {
					x += m;
				}
				else {
					num = x;
					break;
				}
			}
			
			answer.append(num).append("\n");
						
		}
		System.out.println(answer);
		
	}
	
}
