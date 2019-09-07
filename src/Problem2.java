import java.util.*;

public class Problem2 {
    public static List<String> getListDiff(List<String> a, List<String> b) {
        var result = new ArrayList<String>();

        a.parallelStream().forEach(word -> {
            if(!b.contains(word)) result.add(word);
        });

        return result;
    }

    public static void main(String[] args) {
        var a = Arrays.asList("x", "y", "z");
        var b = Arrays.asList("y", "xyz");

        var result = getListDiff(a, b);

        System.out.println(result);
    }
}
