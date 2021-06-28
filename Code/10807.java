import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);

        int n, integer, count = 0;

        n = sc.nextInt();

        for (int i = 0; i < n; i++){
            int num = sc.nextInt();
            arr.add(num);
        }

        integer = sc.nextInt();

        for (int i = 0; i < n; i++){
            if (arr.get(i) == integer){
                count++;
            }
        }
        System.out.println(count);
    }

}
