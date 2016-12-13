package blueservices.blue.stream.collector

import spock.lang.Specification

class UnmodifiableCollectorsSetSpec extends Specification {
    def "should return unmodifiable set when no action are invoked on stream"() {
        given:
        Set<Integer> set = [1, 2, 3]

        when:
        def unmodifiableSet = set.stream()
                .collect(UnmodifiableCollectors.toSet())

        then:
        set == unmodifiableSet
    }

    def "should return unmodifiable set when some action are invoked on stream"() {
        given:
        Set<Integer> set = [1, 2, 3]

        when:
        def unmodifiableSet = set.stream()
                .filter { i -> i < 3 }
                .collect(UnmodifiableCollectors.toSet())

        then:
        unmodifiableSet.size() == 2
        unmodifiableSet.containsAll([1, 2])
    }

    def "should throw exception when add new element to set"() {
        given:
        Set<Integer> unmodifiableSet = [1, 2, 3].stream()
                .collect(UnmodifiableCollectors.toSet())

        when:
        unmodifiableSet.add(4)

        then:
        thrown(UnsupportedOperationException.class)
    }
}