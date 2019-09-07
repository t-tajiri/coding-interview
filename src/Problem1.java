import java.util.*;

public class Problem1 {

    private static double taxRates = 1.13;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int price = sc.nextInt();
        int includeTax = (int) (price * taxRates);

        System.out.println(includeTax);

        sc.close();
    }
}
