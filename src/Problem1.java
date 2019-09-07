import java.util.*;

public class Problem1 {

    private static double taxRates = .13;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        var price = sc.nextInt();
        var includeTax = (int) (price * (1 + taxRates));

        System.out.println(includeTax);

        sc.close();
    }
}
