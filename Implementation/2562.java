import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int maximum = 0;
        int index = 0;

        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < 9; i++){
            int num = scanner.nextInt();

            if (num > maximum){
                maximum = num;
                index = i+1;
            }

        }

        System.out.println(maximum);
        System.out.println(index);
    }
}
