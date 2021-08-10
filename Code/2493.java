import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Laser{
	int index;
	int height;
	
	public Laser(int index, int height) {
		this.index = index;
		this.height = height;
	}
}

public class Main {	

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		Laser[] buildings = new Laser[n];
		int[] numbers = new int[n + 1];
		
		
		Stack<Laser> left = new Stack<Laser>();
		
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			 buildings[i] = new Laser(i + 1, Integer.parseInt(input[i]));
		}
		
		for(Laser building : buildings) {
			if(!left.isEmpty()) {
				Laser top = left.peek();
				
				if(top.height > building.height) {
					numbers[building.index] = top.index;
				}
				else{
					while(!left.isEmpty()) {
						top = left.peek();
						
						if(top.height > building.height) {
							numbers[building.index] = top.index;
							break;
						}
						else {
							left.pop();
						}
					}
				}
			}
			left.push(building);
			
		}
		
		StringBuilder answer = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			answer.append(numbers[i]).append(" ");
		}
		System.out.println(answer);
		
	}
	
}
