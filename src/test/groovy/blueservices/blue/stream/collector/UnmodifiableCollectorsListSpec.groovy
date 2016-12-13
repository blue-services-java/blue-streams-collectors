package blueservices.blue.stream.collector

import spock.lang.Specification

class UnmodifiableCollectorsListSpec extends Specification {
    def "should return unmodifiable list when no action are invoked on stream"() {
        given:
        def list = [1, 2, 3]

        when:
        def unmodifiableList = list.stream()
                .collect(UnmodifiableCollectors.toList())

        then:
        list == unmodifiableList
    }

    def "should return unmodifiable list when some action are invoked on stream"() {
        given:
        def list = [1, 2, 3]

        when:
        def unmodifiableList = list.stream()
                .filter { i -> i < 3 }
                .collect(UnmodifiableCollectors.toList())

        then:
        unmodifiableList.size() == 2
        unmodifiableList.containsAll([1, 2])
    }

    def "should throw exception when add new element"() {
        given:
        def unmodifiableList = [1, 2, 3].stream()
                .collect(UnmodifiableCollectors.toList())

        when:
        unmodifiableList.add(4)

        then:
        thrown(UnsupportedOperationException.class)
    }
}