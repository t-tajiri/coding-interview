import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Problem2 {
    public static List<String> getListDiff(List<String> a, List<String> b) {
        // @formatter:off
        return  a.parallelStream()
                 .filter(Predicate.not(b::contains))
                 .collect(Collectors.toList());
        // @formatter:on
    }

    public static void main(String[] args) {
        var a = Arrays.asList("x", "y", "z");
        var b = Arrays.asList("y", "xyz");

        var result = getListDiff(a, b);

        System.out.println(result);
    }
}
