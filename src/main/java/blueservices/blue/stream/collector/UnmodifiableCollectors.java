package blueservices.blue.stream.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class UnmodifiableCollectors {
    public static <T> Collector<T, List<T>, List<T>> toList() {
        return new UnmodifiableListCollector<>();
    }

    public static <T> Collector<T, Set<T>, Set<T>> toSet() {
        return new UnmodifiableSetCollector<>();
    }

    private static class UnmodifiableListCollector<T> implements Collector<T, List<T>, List<T>> {
        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            return List::add;
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            return (left, right) -> {
                List<T> list = new ArrayList<>(left.size() + right.size());
                list.addAll(left);
                list.addAll(right);
                return list;
            };
        }

        @Override
        public Function<List<T>, List<T>> finisher() {
            return Collections::unmodifiableList;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.EMPTY_SET;
        }
    }

    private static class UnmodifiableSetCollector<T> implements Collector<T, Set<T>, Set<T>> {
        @Override
        public Supplier<Set<T>> supplier() {
            return LinkedHashSet::new;
        }

        @Override
        public BiConsumer<Set<T>, T> accumulator() {
            return Set::add;
        }

        @Override
        public BinaryOperator<Set<T>> combiner() {
            return (left, right) -> {
                Set<T> set = new LinkedHashSet<>(left.size() + right.size());
                set.addAll(left);
                set.addAll(right);
                return set;
            };
        }

        @Override
        public Function<Set<T>, Set<T>> finisher() {
            return Collections::unmodifiableSet;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.EMPTY_SET;
        }
    }
}