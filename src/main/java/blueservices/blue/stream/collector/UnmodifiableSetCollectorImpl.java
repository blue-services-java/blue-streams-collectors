package blueservices.blue.stream.collector;

import java.util.*;

class UnmodifiableSetCollectorImpl<T> extends UnmodifiableCollectorImpl<T, Set<T>, Set<T>> {
    UnmodifiableSetCollectorImpl() {
        super(
                HashSet::new,
                Set::add,
                (left, right) -> { left.addAll(right); return left; },
                Collections::unmodifiableSet,
                UnmodifiableCollectorImpl.CH_UNORDERED_ID
        );
    }
}
