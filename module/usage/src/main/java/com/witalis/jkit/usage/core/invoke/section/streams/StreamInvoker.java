package com.witalis.jkit.usage.core.invoke.section.streams;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.streams.content.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collector.Characteristics;

/**
 * Desc: Stream API
 * User: Wellaxis
 * Date: 2021/03/15
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class StreamInvoker extends Invoker {

    public StreamInvoker() {
        setTitle("Stream chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // intermediates
        log.info("## Intermediate Operations");
        invokeIntermediate();
        // tab
        log.info("");
        // terminals
        log.info("## Terminal Operations");
        invokeTerminal();
        // tab
        log.info("");
        // iterators
        log.info("## Iterator Operations");
        invokeIterator();
    }

    /**
     * Stream API.
     */
    private void invokeBasis() {
        // Streams have three parts:
        // - a data source (provides the elements to the pipeline)
        // - zero or more intermediate operations (all lazy)
        // - zero or one terminal operation (initiate a work)

        // empty
        {
            var empty = Stream.empty();
            log.info(" - empty: {}", empty.toString());
        }
        // builder
        {
            Stream<Object> build = Stream.builder()
                .add("It")
                .add("is")
                .add("ok")
                .build();
            final var builder = new StringBuilder("data[ ");
            build.forEach((i) -> builder.append(i).append(" "));
            builder.append("]");
            log.info(" - builder: {}", builder.toString());
        }
        // of
        {
            var ofs = Stream.of(
                "Not", "bad", "too"
            );
            final var builder = new StringBuilder("data[ ");
            ofs.forEach((i) -> builder.append(i).append(" "));
            builder.append("]");
            log.info(" - of: {}", builder.toString());
        }
        // array
        {
            var array = new long[]{1L, 7L, 3L, 0L, 11L, 45L, 90L};
            var longs = Arrays.stream(array);
            final var builder = new StringBuilder("data[ ");
            longs.forEach((i) -> builder.append(i).append(" "));
            builder.append("]");
            log.info(" - array: {}", builder.toString());
        }
        // collection
        {
            Stream<Integer> nums = new ArrayList<>(
                List.of(7, 18, 10, 24, 17, 5, 33, 4)
            ).stream();
            final var builder = new StringBuilder("data[ ");
            nums.forEach((i) -> builder.append(i).append(" "));
            builder.append("]");
            log.info(" - collection: {}", builder.toString());
        }
        // primitive
        {
            var range = IntStream.range(0, 11);
            final var builder = new StringBuilder("data[ ");
            range.forEach((i) -> builder.append(i).append(" "));
            builder.append("]");
            log.info(" - primitive: {}", builder.toString());
        }
        // generate
        {
            var random = new Random(100);
            Stream<Boolean> generator = Stream.generate(random::nextBoolean).limit(8);
            final var builder = new StringBuilder("data[ ");
            generator.forEach((i) -> builder.append(i).append(" "));
            builder.append("]");
            log.info(" - generator: {}", builder.toString());
        }
        // iterator
        {
            var initial = 11;
            Stream<Integer> iterator = Stream.iterate(initial, i -> i + 2).limit(9);
            final var builder = new StringBuilder("data[ ");
            iterator.forEach((i) -> builder.append(i).append(" "));
            builder.append("]");
            log.info(" - iterator: {}", builder.toString());
        }
    }

    /**
     * Intermediate operations.
     */
    private void invokeIntermediate() {
        // intermediate ops [lazy]: filter, map, peek, sorted, distinct, limit, etc
        // all intermediate ops - initiate a new stream

        List<Integer> numbers = new ArrayList<>(
            List.of(7, 18, 10, 24, 17, 5, 33, 4)
        );
        log.info(" > Original list: {}", numbers);
        Stream<User> users = Stream.of(
            new User("Bob", 20_000D),
            new User("Max", Double.parseDouble("2345")),
            new User("Roo", Double.sum(2400, 9100))
        );
        log.info(" > Original users: {}", users);

        // sorting
        {
            log.info(" - Sorting operations: {}", "sorted");
            Stream<Integer> sorted = numbers.stream().sorted();
            log.info("   * sorted list: {}", Arrays.toString(sorted.toArray()));
            var unordered = numbers.stream().unordered().parallel();
            log.info("   * unordered list: {}", Arrays.toString(unordered.toArray()));
        }

        // distinct
        {
            log.info(" - Unique operations: {}", "distinct");
            Stream<String> unique = List.of(
                "ok", "bad", "ok", "so-so", "bad", "Ok"
            ).stream().distinct();
            log.info("   * distinct list: {}", Arrays.toString(unique.toArray()));
        }

        // limit
        {
            log.info(" - Limit operations: {}", "limit");
            var limit = numbers.stream().limit(5);
            log.info("   * limit list: {}", Arrays.toString(limit.toArray()));
        }

        // skip
        {
            log.info(" - Skip operations: {}", "skip");
            var skip = numbers.stream().skip(3);
            log.info("   * skip list: {}", Arrays.toString(skip.toArray()));
        }

        // filter
        {
            log.info(" - Filtering operations: {}", "filter");
            Stream<Integer> odds = numbers.stream()
                .filter((n) -> (n % 2) == 1)
                .filter((n) -> n >= 8);
            log.info("   * odd big numbers: {}", Arrays.toString(odds.toArray()));
        }

        // base mapping - apply op to every item
        {
            log.info(" - Mapping operations: {}", "map");
            // map standard
            Stream<Double> sqrt = numbers.stream().map(Math::sqrt);
            log.info("   * mapping sqrt list: {}", Arrays.toString(sqrt.map(
                (num) -> String.format("%.2f", num)
            ).toArray()));
            // map to primitive
            DoubleStream doubleStream = numbers.stream()
                .map(Math::sqrt)
                .mapToDouble(Double::doubleValue);
            double multi = doubleStream.mapToInt(
                d -> (int) Math.round(d)
            ).reduce(1, (r, n) -> r * n);
            log.info(" - Mapping multiplication: {}", String.format("%.4f", multi));
            // one more
            var baseMap = numbers.stream()
                .map(i -> ++i)
                .toArray();
            log.info("   * base map list: {}", Arrays.toString(baseMap));
        }

        // flat mapping - for every item - stream of values [0..lot]
        {
            List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(lists -> lists.stream())
                .collect(toList());
            log.info("   * flat map list: {}", together);
            // one more
            var twin = List.of(
                List.of(1, 6, -3),
                List.of(2, 9, 5),
                List.of(-4, 0, 7)
            );
            var flatMap = twin.stream()
                .flatMap(Collection::stream)
                .toArray();
            log.info("   * flat map list: {}", Arrays.toString(flatMap));
        }

        // peek - operation does not replace the element (modify only), for debug
        {
            users
                .peek((u) -> u.setSalary(u.getSalary() + 1000))
                .sorted(comparing(User::getSalary))
                .forEach((u) -> log.info("   * user: {}", u));
        }
    }

    /**
     * Terminal operations.
     */
    private void invokeTerminal() {
        // terminal ops [eager]: count, min, max, forEach, etc
        // all terminal ops - close a strem

        long before, after;
        List<Integer> numbers = new ArrayList<>(
            List.of(7, 18, 10, 24, 10, 17, -5, 33, 4)
        );
        log.info(" > Original list: {}", numbers);
        List<User> users = List.of(
            new User("Bob", 20_000D),
            new User("Max", Double.parseDouble("2345")),
            new User("Den", 44_444D),
            new User("Roo", Double.sum(2400, 9100))
        );
        log.info(" > Original users: {}", users);

        // aggregation - count, min, max
        {
            log.info(" - Simple operations: {}", "min/max");
            // count
            var count = numbers.stream().count();
            log.info("   * count of elements = {}", count);
            // min
            Stream<Integer> stream = numbers.stream();
            Optional<Integer> min = stream.min(Integer::compareTo);
            min.ifPresent(integer -> log.info("   * minimal value: {}", integer));
            // max
            stream = numbers.stream();
            Optional<Integer> max = stream.max(Integer::compareTo);
            max.ifPresent(integer -> log.info("   * maximal value: {}", integer));
        }

        // search - to find elements
        {
            log.info(" - Find operations: {}", "find any");
            // find 1st element
            var first = numbers.stream().findFirst();
            first.ifPresentOrElse(
                num -> log.info("   * first value: {}", num),
                () -> log.info("   * stream is empty")
            );
            // find any element
            var any = numbers.stream().findAny();
            first.ifPresentOrElse(
                num -> log.info("   * any value: {}", num),
                () -> log.info("   * stream is empty")
            );
        }

        // check - to verify elements
        {
            log.info(" - Check operations: {}", "all match");
            // for all elements
            boolean all = numbers.stream().allMatch(
                (num) -> num.compareTo(0) > 0
            );
            log.info("   * match [all]: {}", all);
            // for some elements
            boolean any = numbers.stream().anyMatch(
                (num) -> num.compareTo(0) > 0
            );
            log.info("   * match [any]: {}", any);
            // for none elements
            boolean none = numbers.stream().noneMatch(
                (num) -> num.compareTo(0) > 0
            );
            log.info("   * match [any]: {}", none);
        }

        // for each - cycle operation
        {
            log.info(" - Cycle operations: {}", "for each");
            var builder = new StringBuilder("[ ");
            numbers.stream().sorted().forEach(
                (num) -> builder.append(num).append(", ")
            );
            builder.append("etc]");
            log.info("   * for each: {}", builder.toString());
        }

        // reduce - val1 contain the previous result and val2 contain the next element
        {
            log.info(" - Reduce operations: {}", "reduce");
            // accumulator
            before = System.nanoTime();
            Optional<Integer> accumulator = numbers.stream().reduce(
                (result, next) -> result * next
            );
            after = System.nanoTime();
            log.info(
                "   * accumulator [v1]: {}, time {} ns",
                accumulator.orElse(-1),
                after - before
            );
            // identity
            before = System.nanoTime();
            int identity = numbers.stream().reduce(
                1, (r, n) -> r * n
            );
            after = System.nanoTime();
            log.info(
                "   * identity [v2]: {}, time {} ns",
                identity,
                after - before
            );
            // parallel
            before = System.nanoTime();
            int parallel = numbers.parallelStream().reduce(
                1, (r, n) -> r * n
            );
            after = System.nanoTime();
            log.info(
                "   * parallel [v3]: {}, time {} ns",
                parallel,
                after - before
            );
            // scenario
            int evenProduct = numbers.stream().reduce(
                1,
                (a, b) -> {
                    if (b % 2 == 0 && a < 1_000_000) return a * b;
                    else return a;
                }
            );
            log.info("   * multiply even, less than 1m: {}", evenProduct);
        }

        // collect - to group elements
        {
            log.info(" - Collect operations");

            // collection
            {
                log.info(" - To collection operations: {}", "collect");
                // to array
                var array = numbers.stream().sorted().toArray();
                log.info("   * to array: {}", Arrays.toString(array));
                // to list - not unique, ordered
                var list = numbers.stream().sorted().collect(toList());
                log.info("   * collect [list]: {}", list);
                // to unmodifiable list
                var unmodifiableList = numbers.stream().collect(toUnmodifiableList());
                try {
                    var add = unmodifiableList.add(99);
                } catch (UnsupportedOperationException uoe) {
                    log.error("Unable to add element to unmodifiable list");
                }
                // to set - unique, not ordered
                var set = numbers.stream().sorted().collect(toSet());
                log.info("   * collect [set]: {}", set);
                // to unmodifiable set
                var unmodifiableSet = numbers.stream().collect(toUnmodifiableSet());
                try {
                    var add = unmodifiableSet.add(99);
                } catch (UnsupportedOperationException uoe) {
                    log.error("Unable to add element to unmodifiable set");
                }
                // to collection - collection of required choice
                var linkedList = numbers.stream().sorted().collect(toCollection(LinkedList::new));
                log.info("   * collect [collection/linked list]: {}", linkedList);
                var treeSet = numbers.stream().sorted().collect(toCollection(TreeSet::new));
                log.info("   * collect [collection/tree set]: {}", treeSet);
                // to map - key, value and merge function
                Map<Integer, Integer> map = numbers.stream().sorted().collect(
                    toMap(
                        Function.identity(),
                        Object::hashCode,
                        (item, identical) -> item)
                );
                log.info("   * collect [map]: {}", map);
            }

            // useful
            {
                log.info(" - Useful operations: {}", "collect");
                // collectingAndThen - map and action
                var copy = numbers.stream().collect(
                    collectingAndThen(toSet(), Set::copyOf)
                );
                log.info("   * collect [collection/action]: {}", copy);
                // counting
                var size = numbers.stream().collect(counting());
                log.info("   * collect [collection/counting]: {}", size);
                // joining
                var join = numbers.stream().map(Object::toString).collect(
                    joining(", ", "<", ">")
                );
                log.info("   * collect [collection/joining]: {}", join);
                // statistics
                IntSummaryStatistics stats = numbers.stream().collect(summarizingInt(Integer::intValue));
                log.info("   * collect [collection/summarizing]: {}", stats);
                // summing
                var sum = numbers.stream().collect(summingInt(Integer::intValue));
                log.info("   * collect [collection/summing]: {}", sum);
                // average
                var avg = numbers.stream().collect(averagingInt(Integer::intValue));
                log.info("   * collect [collection/average]: {}", avg);
                // min
                var min = numbers.stream().collect(minBy(Comparator.naturalOrder()));
                log.info("   * collect [collection/minimal]: {}", min);
                // max
                var max = numbers.stream().collect(maxBy(Integer::compareTo));
                log.info("   * collect [collection/maximal]: {}", max);
                // partition - map key is true/false
                var parts = numbers.stream().collect(
                    partitioningBy(
                        (num) -> (num % 2) == 0,
                        toUnmodifiableSet()
                    )
                );
                log.info("   * collect [collection/partitioning]: {}", parts);
                // grouping - map key is any type
                var groups = numbers.stream().collect(
                    groupingBy(
                        (num) -> (Math.abs(num) % 3),
                        toUnmodifiableSet()
                    )
                );
                log.info("   * collect [collection/grouping]: {}", groups);
                // teeing
                var result = numbers.stream().collect(
                    teeing(
                        minBy(Integer::compareTo),
                        maxBy(Integer::compareTo),
                        (minim, maxim) -> {
                            var minimal = minim.orElse(0);
                            var maximal = maxim.orElse(0);
                            var percent = (double) (minimal + maximal) / 100;
                            return String.format("%.2f", percent).concat(" %");
                        }
                    )
                );
                log.info("   * collect [collection/teeing]: {}", result);
                // mapping
                var mapping = users.stream().collect(
                    mapping(
                        User::getName,
                        toSet()
                    )
                );
                log.info("   * collect [collection/mapping]: {}", mapping);
            }

            // custom
            {
                log.info(" - Custom operations: {}", "collect");
                // collector: supplier, accumulator, finisher, combiner, characteristics
                var custom = numbers.stream().collect(
                    CustomCollectors.CustomCollector.toCustomize()
                );
                log.info("   * collect [custom/originals]: {}", numbers);
                log.info("   * collect [custom/implement]: {}", custom);

                // builder
                final var tax = 20;
                Collector<User, Map<String, Double>, Map<String, Double>> taxer = Collector.of(
                    // supplier - new instance
                    HashMap::new,
                    // accumulator - add element
                    (map, user) -> map.put(user.getName(), user.getTaxes(tax)),
                    // combiner - merge in parallel
                    (map1, map2) -> {
                        map1.putAll(map2);
                        return map1;
                    },
                    // finisher - return result
                    Function.identity(),
                    // characteristics - metadata
                    Characteristics.CONCURRENT
                );
                var taxes = users.stream().collect(taxer);
                log.info("   * collect [custom/originals]: {}", users);
                log.info("   * collect [custom/builder-of]: {}", taxes);
            }
        }
    }

    /**
     * Iterator operations.
     */
    private void invokeIterator() {
        // Iterator - has two key methods are hasNext() and next().
        // * hasNext() - returns true, if there is another element to iterate, and false otherwise.
        // * next() - method returns the next element in the iteration.

        // Spliterator - alternative to Iterator, especially in parallel processing.
        // * tryAdvance() - returns true if there is a next element, and false - if no elements remain
        // * forEachRemaining() - to perform some action on each element collectively
        // * trySplit() - splits the elements in two parts, returning a new spliterator to one of the partitions

        List<Integer> numbers = new ArrayList<>(
            List.of(7, 18, -10, 3)
        );
        log.info(" > Original list: {}", numbers);

        // iterator
        {
            log.info("  > Stream Iterator");
            Iterator<Integer> iterator = numbers.stream().iterator();
            while (iterator.hasNext()) {
                log.info("    - Iterator: next element: {}", iterator.next());
            }
        }

        // spliterator
        {
            log.info("  > Stream Spliterator");
            Spliterator<Integer> spliterator = numbers.stream().spliterator();
            // try advance - one by time
            while (spliterator.tryAdvance(
                i -> log.info("    - Spliterator: next element: {}", i)
            ));
            // for each remaining - together
            var remaining = numbers.stream().spliterator();
            remaining.tryAdvance(i -> log.info("    - Spliterator: take one: {}", i));
            remaining.forEachRemaining(
                i -> log.info("    - Spliterator: for each: {}", i)
            );
            // try split - useful in parallel processing
            Stream<Integer> stream = numbers.stream();
            var baseSpliterator = stream.spliterator();
            var additionalSpliterator = baseSpliterator.trySplit();
            if (additionalSpliterator != null)
                additionalSpliterator.forEachRemaining(
                    i -> log.info("    - Split [additional]: for each: {}", i)
                );
            baseSpliterator.forEachRemaining(
                i -> log.info("    - Split [base]: for each: {}", i)
            );
        }
    }
}
