import java.util.*;

public class Problem1 {
    private static final double TAX_RATES = 0.13;

    public static void main(String[] args) {
        var sc = new Scanner(System.in);

        var price = sc.nextInt();
        var includeTax = (int) (price * (1 + TAX_RATES));

        System.out.println(includeTax);

        sc.close();
    }
}
