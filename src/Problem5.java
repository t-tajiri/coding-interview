import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class Problem5 {
    public static void main(String[] args) {
        var ids = new int[0];
        var discounts = new ArrayList<Discount>();

        try (var in = new BufferedReader(new InputStreamReader(System.in))) {
            ids = convertIntArrayFrom(in.readLine());

            while (true) {
                // 入力が終了したか判断するため、空白行で入力終了とする
                var line = in.readLine();
                if (line.isEmpty()) {
                    break;
                }

                int[] target = convertIntArrayFrom(line);
                var discount = constructElementOf(target);
                discounts.add(discount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Discount> candidates = deriveCandidatesFrom(discounts, ids);
        List<Discount> results = determineDiscountsBy(candidates, ids);

        var discountPrice = sumOf(results);

        System.out.println(discountPrice);
    }

    private static int[] convertIntArrayFrom(String line) {
        String[] array = line.split(" ");
        // @formatter:off
        return Arrays.stream(array)
                 .mapToInt(Integer::parseInt)
                 .toArray();
        // @formatter:on
    }

    private static Discount constructElementOf(int[] target) {
        var discount = new Discount();

        discount.setPrice(target[0]);
        int[] rest = Arrays.copyOfRange(target, 1, target.length);

        var m = new HashMap<Integer, Boolean>();
        Arrays.stream(rest).forEach(targetId -> m.put(targetId, false));
        discount.setTarget(m);

        return discount;
    }

    private static List<Discount> deriveCandidatesFrom(List<Discount> discounts, int[] ids) {
        var candidates = new CopyOnWriteArrayList<>(discounts);

        for (var id : ids) {
            // @formatter:off
            candidates.stream()
                .filter(discount -> discount.getTarget().containsKey(id))
                .forEach(discount -> discount.getTarget().put(id, true));
            // @formatter:on
        }

        // @formatter:off
        return candidates.stream()
                    .filter(Discount::isCandidate)
                    .collect(Collectors.toList());
        // @formatter:on
    }

    private static List<Discount> determineDiscountsBy(List<Discount> candidates, int[] ids) {
        var results = new CopyOnWriteArrayList<>(candidates);

        for (var id : ids) {
            Discount discount = null;
            for (var result: results) {
                Map<Integer, Boolean> m = result.getTarget();
                if (!m.containsKey(id)) {
                    continue;
                }

                if (shouldRemovePastCandidate(discount, result)) {
                    results.remove(discount);
                }

                discount = result;
            }
        }

        return results;
    }

    private static boolean shouldRemovePastCandidate(Discount past, Discount present) {
        return past != null && past.getPrice() < present.getPrice();
    }

    private static int sumOf(List<Discount> discounts) {
        // @formatter:off
        return discounts.stream()
                    .mapToInt(Discount::getPrice)
                    .sum();
        // @formatter:on
    }

    private static class Discount {
        private int price;

        private Map<Integer, Boolean> target;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public Map<Integer, Boolean> getTarget() {
            return target;
        }

        public void setTarget(Map<Integer, Boolean> target) {
            this.target = target;
        }

        public boolean isCandidate() {
            return !target.containsValue(false);
        }
    }
}
