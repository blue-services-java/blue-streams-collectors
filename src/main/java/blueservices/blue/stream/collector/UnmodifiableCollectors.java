package blueservices.blue.stream.collector;

import java.util.*;
import java.util.stream.Collector;

public class UnmodifiableCollectors {
    private UnmodifiableCollectors() {
    }

    public static <T> Collector<T, List<T>, List<T>> toList() {
        return new UnmodifiableListCollectorImpl<>();
    }

    public static <T> Collector<T, Set<T>, Set<T>> toSet() {
        return new UnmodifiableSetCollectorImpl<>();
    }
}