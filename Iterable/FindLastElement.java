import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class FindLastElement {

    public static void main(String[] args) {
        Iterator<String> sourceIterator = Arrays.asList("one", "two", "three").iterator();

        Iterable<String> iterable = () -> sourceIterator;
        String last = StreamSupport.stream(iterable.spliterator(), false).reduce((first, second) -> second).orElse(null);
        System.out.println("Last element "+last);
    }
}
