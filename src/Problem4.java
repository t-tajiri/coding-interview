import java.security.*;
import java.util.*;

public class Problem4 {
    public static int drawLot(int[] count) {
        var quantity = 0;

        var boundary = new HashMap<Integer, Integer>();
        boundary.put(0, count[0]);
        for (var i = 1; i < count.length; i++) {
            boundary.put(i, boundary.get(i - 1) + count[i]);
            quantity += count[i];
        }

        var r = new SecureRandom();
        var offset = r.nextInt(quantity);

        return rankLot(boundary, offset);
    }

    private static int rankLot(Map<Integer, Integer> boundary, int offset) {
        var rank = 0;
        for (var i = 0; i < boundary.size(); i++) {
            if (boundary.get(i) > offset) {
                rank = i;
                break;
            }
        }

        return rank;
    }

    public static void main(String[] args) {
        int[] count = {1, 2, 5, 10, 100, 1000};

        System.out.println(drawLot(count));
    }
}
