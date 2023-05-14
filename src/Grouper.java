import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grouper {
    public static Map<String, Collection<NamedObject>> groupByName(Collection<NamedObject> objects) {
        return objects.stream()
                .collect(Collectors.toMap(NamedObject::getName,
                        o -> Stream.of(o).collect(Collectors.toList()), // there could be lambda block with creating LinkedList
                        // and adding object for better performance in merge function, but there is no performance requirements in task
                        (o1, o2) -> Stream.concat(o1.stream(), o2.stream()).collect(Collectors.toList()))
                );
    }
}
