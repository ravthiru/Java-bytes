import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransformMap {

    /*

    From

        "aa" -> "20"
        "bb" -> "30"
        "cc" -> "20"
        "dd" -> "45"
        "ee" -> "35"
        "ff" -> "35"
        "gg" -> "20"


    Transform to

    "20" -> ["aa","cc","gg"]
    "30" -> ["bb"]
    "35" -> ["ee","ff"]
    "45" -> ["dd"]

     */


    public static void main(String[] args) {

        Map<String, String> values = new HashMap<String, String>();
        values.put("aa", "20");
        values.put("bb", "30");
        values.put("cc", "20");
        values.put("dd", "45");
        values.put("ee", "35");
        values.put("ff", "35");
        values.put("gg", "20");

        Map<String, List<String>> output =
                values.entrySet()
                        .stream()
                        .collect(Collectors.groupingBy(Map.Entry::getValue,
                                Collectors.mapping(Map.Entry::getKey,
                                        Collectors.toList())));
        System.out.println (output);

    }

}
