package blueservices.blue.stream.collector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class UnmodifiableListCollectorImpl<T> extends UnmodifiableCollectorImpl<T, List<T>, List<T>> {
    UnmodifiableListCollectorImpl() {
        super(
                ArrayList::new,
                List::add,
                (left, right) -> { new ArrayList<>(left).addAll(right); return left; },
                Collections::unmodifiableList,
                UnmodifiableCollectorImpl.CH_ID
        );
    }
}
