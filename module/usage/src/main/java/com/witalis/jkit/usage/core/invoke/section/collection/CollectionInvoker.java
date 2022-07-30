package com.witalis.jkit.usage.core.invoke.section.collection;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.collection.context.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Desc: Collection API
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class CollectionInvoker extends Invoker {

    public CollectionInvoker() {
        setTitle("Collection chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // array
        log.info("## Arrays");
        invokeArray();
        // collection
        log.info("## Collections");
        invokeCollection();
        // iterator
        log.info("## Iterators");
        invokeIterator();
        // comparator
        log.info("## Comparators");
        invokeComparator();
        // algorithm
        log.info("## Algorithms");
        invokeAlgorithm();
        // binary tree
        log.info("## Trees");
        invokeTree();
        // binary tree
        log.info("## Scenarios");
        invokeScenario();
    }

    /**
     * Basic postulates of collections.
     */
    private void invokeBasis() {
        // Collections: list, set, queue + map

        log.info("Collection API");
    }

    /**
     * Array functionality.
     */
    private void invokeArray() {
        Random rand = new Random();
        int[] a = new int[rand.nextInt(20)];
        for (int i = 0; i < a.length; ++i) {
            a[i] = rand.nextInt(500);
        }
        log.info("length of a = " + a.length);
        log.info(Arrays.toString(a));

        // array initializer
        Object[] b = new Integer[]{1, null, 3, 4};
        Object[] c = new Double[]{1d, null, 5.6, 4.3};

        log.info(Arrays.toString(b));
        log.info(Arrays.toString(c));

        // variable-length arguments
        VarArgs varArgs = null;

        varArgs = new VarArgs(new Object[] {47, Float.valueOf(3.14f), 11.11});
        varArgs.printArray(varArgs.getValues().length);

        varArgs = new VarArgs(new Object[] {"one", "two", "three"});
        varArgs.printArray(varArgs.getValues().length);

        varArgs = new VarArgs((Object[]) new Integer[] {1, 2, 3, 4});
        varArgs.printArray(varArgs.getValues().length);

        varArgs = new VarArgs(new Object[0]);
        varArgs.printArray(varArgs.getValues().length);

        // array var declaration
        var myArray = new int[10];

        // optional (since jdk8)
        String[] arr = new String[]{"a", "b", "c"};
        List list = Arrays.asList(arr);
        log.info("[N1] - Array is not null: " + list);

        // array as list
        arr = null;
        list = Arrays.asList(Optional.ofNullable(arr).orElse(new String[0]));
        log.info("[N2] - Array is null: " + list);

        // list of array
        List<String> strings = List.of("One", "Two", "Three", "Four", "Five");
        log.info("" + Arrays.asList(strings));
    }

    /**
     * Collection functionality.
     */
    private void invokeCollection() {
        // iterable - parent for all
        Iterable<String> i1 = new ArrayList<>();
        Iterable<String> i2 = new TreeSet<>();
        // collection - extends iterable
        Collection<Integer> c1 = new LinkedList<>();
        Collection<Integer> c2 = new PriorityQueue<>();
        // abstract
        AbstractCollection<Long> ac = new ArrayList<>();

        // list vs set
        // =================================
        // 1. List can contain duplicate elements whereas Set contains unique elements only

        // unmodifiable
        Collection<Double> le = List.of(1D, 2D, 3D);
        try {
            le.add(4D);
        } catch (UnsupportedOperationException uoe) {
            log.error("List.of() uses as immutable, error: " + uoe.getMessage());
        }

        // list - extends collection
        {
            List<Double> l1 = new ArrayList<>();
            List<Double> l2 = new Vector<>();
            List<Double> l3 = new Stack<>();
            // abstract
            AbstractList<Double> al = new ArrayList<>();
            AbstractSequentialList<Double> sl = new LinkedList<>();

            // array list - extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable
            // =================================
            // USE - accessing of the stored data (get)
            // =================================
            // Complexity - O(1) add, O(n) add index, O(1) get, O(n) remove
            // =================================
            // 1. can contain duplicate elements
            // 2. maintains insertion order
            // 3. is non synchronized
            // 4. allows random access because array works at the index basis
            // 5. manipulation is little bit slower than the LinkedList
            {
                log.info("========== List - ArrayList ==========");
                ArrayList<Double> l = new ArrayList<>(
                    Arrays.asList(1D, 2D, 3D)
                );
                log.info(" * List - ArrayList before: " + l.toString());
                l.add(4D);
                l.remove(1D);
                l.add(2, 100D);
                var the1st = l.get(1);
                log.info(" * List - ArrayList first: " + the1st);
                l.set(1, 5D);
                log.info(" * List - ArrayList after: " + l.toString());
                l.sort(Double::compareTo);
                log.info(" * List - ArrayList sort: " + l.toString());
                var subs = l.subList(1, 3);
                log.info(" * List - ArrayList subs: " + subs.toString());
                // array
                Double[] doubles = l.toArray(Double[]::new);
                // synchronized
                var value = Collections.synchronizedList(l).get(1);
            }

            // linked list - extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable
            // =================================
            // USE - manipulation of the stored data (add/remove)
            // =================================
            // Complexity - O(1) add, O(n) get, O(1) remove
            // =================================
            // 1. can contain duplicate elements
            // 2. maintains insertion order
            // 3. is non synchronized
            // 4. manipulation is fast because no shifting needs to occur
            // 5. can be used as a list, stack or queue
            {
                // doubly linked list
                log.info("========== List - LinkedList ==========");
                LinkedList<Double> l = new LinkedList<>(
                    Arrays.asList(1D, 2D, 3D)
                );
                log.info(" * List - LinkedList before: " + l.toString());
                l.add(4D);
                l.remove(1D);
                l.add(2, 100D);
                var the1st = l.get(1);
                log.info(" * List - LinkedList first: " + the1st);
                l.set(1, 5D);
                log.info(" * List - LinkedList after: " + l.toString());
                l.sort(Double::compareTo);
                log.info(" * List - LinkedList sort: " + l.toString());
                var subs = l.subList(1, 3);
                log.info(" * List - LinkedList subs: " + subs.toString());
                // queue
                l.offer(6D); // adds the specified element as the tail
                var peek = l.peek(); // retrieves, but does not remove the head
                var poll = l.poll(); // Retrieves and removes the head
                l.push(7D); // pushes an element onto the stack - as the head
                var pop = l.pop(); // pops an element from the stack (remove and return) - as the head
                log.info(" * List - LinkedList queue: " + l.toString());
                // synchronized
                var value = Collections.synchronizedList(l).get(1);
            }

            // vector - extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable
            // =================================
            // USE - if thread-safe implementation is needed [LEGACY]
            // =================================
            // 1. is synchronized
            // 2. contains many legacy methods that are not the part of a collections framework
            {
                log.info("========== List - Vector ==========");
                Vector<Float> v = new Vector<>(
                    Arrays.asList(1F, 2F, 3F)
                );
                log.info(" * List - Vector before: " + v.toString());
                v.add(4F); // synchronized
                v.remove(1);
                var the1st = v.firstElement();
                log.info(" * List - Vector first: " + the1st);
                v.set(1, 5F);
                log.info(" * List - Vector after: " + v.toString());
                v.sort(Float::compareTo);
                log.info(" * List - Vector sort: " + v.toString());
            }

            // stack - extends Vector<E>
            // =================================
            // USE - LIFO (Last-In-First-Out) manner [LEGACY]
            // =================================
            // 1. is synchronized
            // 2. contains many legacy methods that are not the part of a collections framework
            {
                log.info("========== List - Stack ==========");
                Stack<Float> s = new Stack<>();
                s.push(1F);
                s.push(3F);
                s.push(2F);
                s.push(4F);
                log.info(" * List - Stack before: " + s.toString());
                s.add(5F); // adds to the end
                s.remove(1);
                var peek = s.peek(); // takes the last, not remove - max index
                log.info(" * List - Stack peek: " + peek);
                var pop = s.pop(); // takes the last, and removes - max index
                log.info(" * List - Stack pop: " + pop);
                var search = s.search(4F);
                log.info(" * List - Stack search: " + search);
                log.info(" * List - Stack after: " + s.toString());
            }

            // array list vs vector
            // =================================
            // 1. Synchronization: ArrayList - not synchronized, Vector - synchronized
            // 2. Performance: ArrayList is faster than Vector
            // 3. Data Growth: both grow dynamically: ArrayList increments 50% of the current size, vector 100% (doubling the current size)
            // 4. Traversal: ArrayList can only use Iterator, Vector can use both Enumeration and Iterator for traversing
            // 5. Applications : Most of the time use ArrayList - using Collections.synchronizedList

            // array list vs linked list
            // =================================
            // 1. Store Data: ArrayList uses - a dynamic array of elements, LinkedList - a doubly linked list
            // 2. Performance: LinkedList is faster in add and remove, but slower in get - due to bit shifting of array
            // 3. Act: ArrayList can act as a list only, LinkedList - as a list and as a queue.
            // 4. Use: ArrayList is better for storing and accessing data. LinkedList is better for manipulating data.
        }

        // set - extends collection
        {
            Set<Long> s1 = new TreeSet<>();
            Set<Long> s2 = new HashSet<>();
            Set<Long> s3 = new LinkedHashSet<>();
            // abstract
            AbstractSet<Long> bs = new HashSet<>();
            // sorted set - extends set
            SortedSet<Long> ss = new TreeSet<>();
            // navigable set - extends sorted set
            NavigableSet<Long> ns = new TreeSet<>();

            // tree set - extends AbstractSet<E> implements NavigableSet<E>, Cloneable, java.io.Serializable
            // =================================
            // USE - if you want to sort the elements according to some Comparator
            // =================================
            // Complexity - O(log(n))
            // =================================
            // 1. contains unique elements only like HashSet
            // 2. access and retrieval times are quiet fast
            // 3. doesn't allow null element
            // 4. is non synchronized
            // 5. maintains ascending order
            {
                log.info("========== Set - TreeSet ==========");
                TreeSet<Long> s = new TreeSet<>();
                s.add(1L);
                s.add(2L);
                s.add(3L);
                boolean added = s.add(3L);
                try {
                    s.add(null);
                } catch (NullPointerException npe) {
                    log.error("TreeSet - unable to add null element: " + npe.getMessage());
                }
                log.info(" * Set - TreeSet before: " + s.toString());
                s.add(4L);
                s.add(5L);
                s.remove(3L);
                log.info(" * Set - TreeSet after: " + s.toString());
                var first = s.first();
                log.info(" * Set - TreeSet first: " + first);
                var last = s.last();
                log.info(" * Set - TreeSet last: " + last);
                var ceiling = s.ceiling(3L);
                log.info(" * Set - TreeSet ceiling: " + ceiling);
                var floor = s.floor(3L);
                log.info(" * Set - TreeSet floor: " + floor);
                var higher = s.higher(3L);
                log.info(" * Set - TreeSet higher: " + higher);
                var lower = s.lower(3L);
                log.info(" * Set - TreeSet lower: " + lower);
                var heads = s.headSet(3L);
                log.info(" * Set - TreeSet heads: " + heads.toString());
                var tails = s.tailSet(3L);
                log.info(" * Set - TreeSet tails: " + tails.toString());
                var subs = s.subSet(2L, 4L);
                log.info(" * Set - TreeSet subs: " + subs.toString());
                var desc = s.descendingSet();
                log.info(" * Set - TreeSet descending: " + desc.toString());
            }

            // hash set - extends AbstractSet<E> implements Set<E>, Cloneable, java.io.Serializable
            // =================================
            // USE - if you do not want to maintain insertion order, but want store unique objects
            // =================================
            // Complexity - O(1)
            // =================================
            // 1. stores the elements by using a mechanism called hashing
            // 2. contains unique elements only
            // 3. allows null value
            // 4. is non synchronized
            // 5. doesn't maintain the insertion order (elements are inserted on the basis of their hashcode)
            // 6. is the best approach for search operations
            // 7. initial default capacity of HashSet is 16, and the load factor is 0.75
            {
                log.info("========== Set - HashSet ==========");
                HashSet<Long> s = new HashSet<>();
                s.add(2L);
                s.add(1L);
                s.add(3L);
                boolean added = s.add(3L);
                log.info(" * Set - HashSet before: " + s.toString());
                s.add(5L);
                s.remove(3L);
                s.add(4L);
                log.info(" * Set - HashSet after: " + s.toString());
            }

            // linked hash set - extends HashSet<E> implements Set<E>, Cloneable, java.io.Serializable
            // =================================
            // USE - if you want to maintain insertion order of elements
            // =================================
            // Complexity - O(1)
            // =================================
            // 1. contains unique elements only like HashSet
            // 2. provides all optional set operation and permits null elements
            // 3. is non synchronized
            // 4. maintains insertion order
            {
                log.info("========== Set - LinkedHashSet ==========");
                LinkedHashSet<Long> s = new LinkedHashSet<>();
                s.add(2L);
                s.add(1L);
                s.add(3L);
                boolean added = s.add(3L);
                log.info(" * Set - LinkedHashSet before: " + s.toString());
                s.add(5L);
                s.remove(3L);
                s.add(4L);
                log.info(" * Set - LinkedHashSet after: " + s.toString());
            }

            // enum set - extends AbstractSet<E> implements Cloneable, java.io.Serializable
            // =================================
            // USE - if you use enumeration (quicker set computation)
            // =================================
            // Complexity - O(1)
            // =================================
            // 1. quickest set - values are specified already
            // 2. order of keys as in enumeration
            {
                log.info("========== Set - EnumSet ==========");
                EnumSet<DayOfWeek> days = EnumSet.allOf(DayOfWeek.class);
                log.info(" * Map - EnumSet all: " + days.toString());
                EnumSet<Month> winter = EnumSet.of(
                    Month.JANUARY, Month.FEBRUARY, Month.DECEMBER
                );
                log.info(" * Map - EnumSet of: " + winter.toString());
            }
        }

        // queue - extends collection
        {
            Queue<Integer> q1 = new ArrayDeque<>();
            Queue<Integer> q2 = new PriorityQueue<>();
            Queue<Integer> q3 = new LinkedList<>();
            // deque - acronym for "double ended queue"
            Deque<Integer> d1 = new ArrayDeque<>();
            Deque<Integer> d2 = new LinkedList<>();
            // abstract
            AbstractQueue<Integer> aq = new PriorityQueue<>();
            AbstractSequentialList<Integer> sl = new LinkedList<>();

            // linked list - extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable
            // =================================
            // USE - manipulation of the stored data (add/remove)
            // =================================
            // Complexity - O(1) add, O(n) get, O(1) remove
            // =================================
            // 1. can contain duplicate elements
            // 2. maintains insertion order
            // 3. is non synchronized
            // 4. manipulation is fast because no shifting needs to occur
            // 5. can be used as a list, stack or queue
            {
                // it has been processed above
                log.info("========== Queue - LinkedList ==========");
            }

            // priority queue - extends AbstractQueue<E> implements java.io.Serializable
            // =================================
            // USE - the objects are supposed to be processed based on the priority (comparator)
            // =================================
            // Complexity - O(log(n))
            // =================================
            // 1. it does not orders the elements in FIFO manner
            // 2. is non synchronized
            // 3. null elements are not accepted
            {
                log.info("========== Queue - PriorityQueue ==========");
                PriorityQueue<Integer> q = new PriorityQueue<>();
                q.add(1);
                q.add(3);
                q.add(2);
                q.add(4);
                log.info(" * Queue - PriorityQueue before: " + q.toString());
                q.remove(3);
                q.add(5); // adds element to the tail - not head
                var offer = q.offer(10); // inserts element into queue
                log.info(" * Queue - PriorityQueue offer: " + offer);
                var peek = q.peek(); // retrieves, but does not remove
                log.info(" * Queue - PriorityQueue peek: " + peek);
                var poll = q.poll(); // retrieves and removes element
                log.info(" * Queue - PriorityQueue poll: " + poll);
                log.info(" * Queue - PriorityQueue after: " + q.toString());
            }

            // array deque - extends AbstractCollection<E> implements Deque<E>, Cloneable, Serializable
            // =================================
            // USE - implement a LIFO (Last-In-First-Out) stacks and FIFO queues
            // =================================
            // Complexity - O(n)
            // =================================
            // 1. is non synchronized
            // 2. null elements are not accepted
            // 3. works faster than the synchronized stack
            // 4. works faster than the LinkedList due to the better locality of reference
            // 5. most operations have amortized constant time complexity
            // 6. Iterator returned by an ArrayDeque is fail-fast
            // 7. automatically doubles the size of an array when head and tail pointer meets each other while adding an element
            {
                log.info("========== Deque - ArrayDeque ==========");
                ArrayDeque<Integer> d = new ArrayDeque<>();
                d.add(1);
                d.add(3);
                d.add(2);
                d.add(4);
                log.info(" * Deque - ArrayDeque before: " + d.toString());
                d.remove(3);
                d.add(5); // adds element to the tail - not head
                d.addFirst(100);
                d.addLast(200);
                var offer = d.offer(10); // inserts element into queue - to end
                log.info(" * Deque - ArrayDeque offer: " + offer);
                var peek = d.peek(); // retrieves, but does not remove
                log.info(" * Queue - ArrayDeque peek: " + peek);
                var poll = d.poll(); // retrieves and removes element
                log.info(" * Deque - ArrayDeque poll: " + poll);
                log.info(" * Deque - ArrayDeque after: " + d.toString());
            }

            // queue vs deque
            // =================================
            // 1. unlike Queue, Deque can add or remove elements from both sides
        }

        // map - not extends collection
        {
            Map<String, Long> m1 = new TreeMap<>();
            Map<String, Long> m2 = new HashMap<>();
            Map<String, Long> m3 = new LinkedHashMap<>();
            // abstract
            AbstractMap<String, Long> am = new HashMap<>();
            // sorted map - extends map
            SortedMap<String, Long> sm = new TreeMap<>();
            // navigable map - extends sorted map
            NavigableMap<String, Long> nm = new TreeMap<>();
            // legacy
            Dictionary<String, Long> d = new Hashtable<>();

            // tree map - extends AbstractMap<K,V> implements NavigableMap<K,V>, Cloneable, java.io.Serializable
            // =================================
            // USE - if you want to sort the keys according to some Comparator
            // =================================
            // Complexity - O(log(n))
            // =================================
            // 1. contains only unique elements
            // 2. cannot have a null key but can have multiple null values
            // 3. is non synchronized
            // 4. maintains ascending order
            {
                log.info("========== Map - TreeMap ==========");
                TreeMap<String, Long> m = new TreeMap<>();
                m.put("A", 1L);
                m.put("C", 3L);
                m.put("B", 22L);
                Long value = m.put("B", 2L);
                try {
                    m.put(null, 4L);
                } catch (NullPointerException npe) {
                    log.error("TreeMap - unable to put null key: " + npe.getMessage());
                }
                log.info(" * Map - TreeMap before: " + m.toString());
                value = m.put("E", 5L);
                value = m.remove("B");
                value = m.replace("C", 6L);
                value = m.putIfAbsent("F", 7L);
                log.info(" * Map - TreeMap after: " + m.toString());
                var first = m.firstEntry();
                log.info(" * Map - TreeMap first: " + first);
                var last = m.lastEntry();
                log.info(" * Map - TreeMap last: " + last);
                var ceiling = m.ceilingEntry("D"); // >= key
                log.info(" * Map - TreeMap ceiling: " + ceiling);
                var floor = m.floorEntry("D"); // <= key
                log.info(" * Map - TreeMap floor: " + floor);
                var higher = m.higherEntry("D"); // > key
                log.info(" * Map - TreeMap higher: " + higher);
                var lower = m.lowerEntry("D"); // < key
                log.info(" * Map - TreeMap lower: " + lower);
                var heads = m.headMap("D");
                log.info(" * Map - TreeMap heads: " + heads.toString());
                var tails = m.tailMap("D");
                log.info(" * Map - TreeMap tails: " + tails.toString());
                var subs = m.subMap("B", true, "F", false);
                log.info(" * Map - TreeMap subs: " + subs.toString());
                var desc = m.descendingMap();
                log.info(" * Map - TreeMap descending: " + desc.toString());
            }

            // hash map - extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable
            // =================================
            // USE - if you do not want to maintain insertion order, but want store unique objects
            // =================================
            // Complexity - O(n) / O(1)
            // =================================
            // 1. contains only unique keys
            // 2. may have one null key and multiple null values
            // 3. is non synchronized
            // 4. maintains no order
            // 5. initial default capacity is 16 with a load factor of 0.75
            {
                log.info("========== Map - HashMap ==========");
                boolean bool;

                HashMap<String, Long> m = new HashMap<>();
                m.put("A", 1L);
                m.put("C", 3L);
                m.put("B", 22L);
                Long value = m.put("B", 2L);
                m.put(null, 4L);
                m.put(null, null);
                log.info(" * Map - HashMap before: " + m.toString());
                value = m.put("E", 5L);
                value = m.remove("B");
                value = m.replace("C", 6L);

                log.info(" * Map - HashMap after: " + m.toString());
                var containsKey = m.containsKey("A");
                log.info(" * Map - HashMap E key: " + containsKey);
                var containsValue = m.containsValue(3L);
                log.info(" * Map - HashMap E value: " + containsValue);
                value = m.getOrDefault("X", 10L);
                log.info(" * Map - HashMap default value: " + value);
                var keys = m.keySet();
                log.info(" * Map - HashMap keys: " + keys);
                var values = m.values();
                log.info(" * Map - HashMap values: " + values);
                var entries = m.entrySet();
                log.info(" * Map - HashMap entries: " + entries);
                if (!m.isEmpty()) {
                    m.forEach(
                        (k, v) -> log.info("   - kvp: " + k + "=" + v)
                    );
                }
                var synchronizedMap = Collections.synchronizedMap(m);
                log.info(" * Map - HashMap synchronized: " + synchronizedMap.toString());
                value = m.compute("B", (k, v) -> (v == null) ? 2L : v + 100L);
                log.info(" * Map - HashMap done: " + m.toString());

                // java 8
                value = m.putIfAbsent("F", 7L); // put (absent)
                value = m.putIfAbsent("F", 8L); // present (don't put)

                value = m.compute("F", (key, val) -> key.length() + val); // compute (present)
                value = m.compute("G", (key, val) -> 4L); // compute (absent)

                value = m.computeIfPresent("F", (key, val) -> key.length() + val); // compute (present)
                value = m.computeIfPresent("H", (key, val) -> key.length() + val); // absent (don't compute)

                value = m.computeIfAbsent("F", key -> key.length() + 1L); // present (don't compute)
                value = m.computeIfAbsent("H", key -> key.length() + 1L); // compute (absent)

                bool = m.remove("C", 4L);// don't remove (not match)
                bool = m.remove("C", 6L); // remove (matched)

                value = m.getOrDefault("A", 100L); // present (return value)
                value = m.getOrDefault("I", 100L); // absent (return default)

                value = m.merge("B", 99L, (oldVal, newVal) -> oldVal + newVal); // merged (present)
                value = m.merge("I", 99L, (oldVal, newVal) -> oldVal + newVal); // put (absent)

                bool = m.replace("I", 999L, 9L); // don't replace (not match)
                bool = m.replace("I", 99L, 9L); // replace (matched)

                Map<String, Long> n = new HashMap<>() {{
                    put("X", 7L);
                    put("B", 20L);
                    put("Y", 8L);
                }};

                Map<String, Long> o = Collections.singletonMap("Z", 9L);

                Map<String, Long> p = Map.of("P", 33L, "C", 44L);

                Map<String, Long> q = Map.ofEntries(
                    Map.entry("K", 55L),
                    Map.entry("L", 66L)
                );

                m.putAll(n);
                m.putAll(o);
                m.putAll(p);
                m.putAll(q);

                log.info("Map entries:");
                m.forEach(
                    (k, v) -> log.info("    key=" + k + ", value=" + v)
                );

                m.clear();
                log.info(" * Map - HashMap size: " + m.size());
            }

            // linked hash map - extends HashMap<K,V> implements Map<K,V>
            // =================================
            // USE - if you want to maintain insertion order of keys
            // =================================
            // Complexity - O(n) / O(1)
            // =================================
            // 1. contains unique elements
            // 2. may have one null key and multiple null values
            // 3. is non synchronized
            // 4. maintains insertion order
            // 5. initial default capacity is 16 with a load factor of 0.75
            {
                log.info("========== Map - LinkedHashMap ==========");
                LinkedHashMap<String, Long> m = new LinkedHashMap<>();
                m.put("A", 1L);
                m.put("C", 3L);
                m.put("B", 22L);
                Long value = m.put("B", 2L);
                m.put(null, 4L);
                m.put(null, null);
                log.info(" * Map - LinkedHashMap before: " + m.toString());
                value = m.put("F", 5L);
                value = m.remove("B");
                value = m.replace("C", 6L);
                value = m.putIfAbsent("E", 7L);
                log.info(" * Map - LinkedHashMap after: " + m.toString());
                var containsKey = m.containsKey("A");
                log.info(" * Map - LinkedHashMap E key: " + containsKey);
                var containsValue = m.containsValue(3L);
                log.info(" * Map - LinkedHashMap E value: " + containsValue);
                value = m.getOrDefault("X", 10L);
                log.info(" * Map - LinkedHashMap default value: " + value);
                var keys = m.keySet();
                log.info(" * Map - LinkedHashMap keys: " + keys);
                var values = m.values();
                log.info(" * Map - LinkedHashMap values: " + values);
                var entries = m.entrySet();
                log.info(" * Map - LinkedHashMap entries: " + entries);
                if (!m.isEmpty()) {
                    m.forEach(
                        (k, v) -> log.info("   - kvp: " + k + "=" + v)
                    );
                }
                value = m.compute("F", (k, v) -> (v == null) ? 1L : v + 100L);
                log.info(" * Map - LinkedHashMap done: " + m.toString());
            }

            // enum map - extends AbstractMap<K, V> implements java.io.Serializable, Cloneable
            // =================================
            // USE - if you use enumeration (quicker hash computation)
            // =================================
            // Complexity - O(1)
            // =================================
            // 1. quickest map - keys are specified already
            // 2. order of keys as in enumeration
            {
                log.info("========== Map - EnumMap ==========");
                EnumMap<DayOfWeek, Long> days = new EnumMap<>(DayOfWeek.class);
                days.put(DayOfWeek.FRIDAY, 50L);
                days.put(DayOfWeek.MONDAY, 10L);
                days.put(DayOfWeek.SUNDAY, 70L);
                days.put(DayOfWeek.WEDNESDAY, 30L);
                log.info(" * Map - EnumMap before: " + days.toString());
                var tuesday = days.get(DayOfWeek.TUESDAY);
                days.put(DayOfWeek.TUESDAY, 20L);
                days.put(DayOfWeek.SATURDAY, 60L);
                days.put(DayOfWeek.THURSDAY, 40L);
                log.info(" * Map - EnumMap after: " + days.toString());
            }

            // hash table - extends Dictionary<K,V> implements Map<K,V>, Cloneable, java.io.Serializable
            // =================================
            // USE - if you need synchronization [LEGACY]
            // =================================
            // Complexity - O(n) / O(1)
            // =================================
            // 1. contains unique elements
            // 2. does not allow null key or value
            // 3. is synchronized
            // 4. initial default capacity is 11 whereas loadFactor is 0.75
            {
                log.info("========== Map - Hashtable ==========");
                Hashtable<String, Long> m = new Hashtable<>();
                m.put("A", 1L);
                m.put("C", 3L);
                m.put("B", 22L);
                Long value = m.put("B", 2L);
                try {
                    m.put(null, 4L);
                } catch (NullPointerException npe) {
                    log.error("Hashtable - unable to put null key: " + npe.getMessage());
                }
                try {
                    m.put("X", null);
                } catch (NullPointerException npe) {
                    log.error("Hashtable - unable to put null value: " + npe.getMessage());
                }
                log.info(" * Map - Hashtable before: " + m.toString());
                value = m.put("F", 5L);
                value = m.remove("B");
                value = m.replace("C", 6L);
                value = m.putIfAbsent("E", 7L);
                log.info(" * Map - Hashtable after: " + m.toString());
                var clone = m.clone();
                log.info(" * Map - Hashtable clone: " + clone);
            }

            // properties - extends Hashtable<Object,Object>
            // =================================
            // USE - if you need synchronization [LEGACY]
            // =================================
            // Complexity - O(n) / O(1)
            // =================================
            // 1. contains unique elements
            // 2. does not allow null key or value
            // 3. is synchronized
            // 4. initial default capacity is 11 whereas loadFactor is 0.75
            // 5. class is non generic
            // 6. designed for string to string mappings
            // 7. maintains default values
            {
                log.info("========== Map - Properties ==========");
                Properties p = new Properties();
                p.put("A", "1");
                p.put("C", "3");
                p.put("B", "22");
                String value = (String) p.put("B", "2");
                try {
                    p.put(null, "4");
                } catch (NullPointerException npe) {
                    log.error("Properties - unable to put null key: " + npe.getMessage());
                }
                try {
                    p.put("X", null);
                } catch (NullPointerException npe) {
                    log.error("Properties - unable to put null value: " + npe.getMessage());
                }
                log.info(" * Map - Properties before: " + p.toString());
                value = (String) p.put("F", "5");
                value = (String) p.remove("B");
                value = (String) p.replace("C", "6");
                value = (String) p.putIfAbsent("E", "7");
                var c = p.getProperty("C", "100");
                log.info(" * Map - Properties get value: " + c);
                var x = p.getProperty("X", "100");
                log.info(" * Map - Properties get default: " + x);
                var f = p.setProperty("G", "8");
                log.info(" * Map - Properties set value: " + f);
                // stream
                if (false) p.list(System.out);
                try (var sr = new StringReader("V"); var sw = new StringWriter()) {
                    p.load(sr);
                    p.store(sw, "Write to string container");
                    sw.flush();
                    var content = sw.toString();
                    log.info(" * Map - Properties load/store:");
                    content.lines().forEach(log::info);
                } catch (IOException ioe) {
                    log.error("Properties - unable to load value: " + ioe.getMessage());
                }
                // enumeration
                Enumeration<?> enumeration = p.propertyNames();
                var values = p.values();
                log.info(" * Map - Properties after: " + values.toString());
                var clone = p.clone();
                log.info(" * Map - Properties clone: " + clone);
            }

            // tree map vs hash map
            // =================================
            // 1. Null Key: TreeMap - cannot contain any null key, HashMap - can contain one null key
            // 2. Performance: HashMap is faster than TreeMap because it provides constant-time performance that is O(1) vs O(log(n))
            // 3. Data: HashMap uses the hash table, TreeMap uses a "Red-Black" (self-balancing binary search) tree
            // 4. Ordering: TreeMap - maintains ascending order, HashMap - maintains no order

            // hash map vs hash table
            // =================================
            // 1. Synchronization: HashMap - non synchronized, Hashtable - is synchronized
            // 2. Null: HashMap allows one null key and multiple null values, Hashtable - doesn't allow any null key or value
            // 3. Version: HashMap - introduced in JDK 1.2, Hashtable - is a legacy class
            // 2. Performance: HashMap is faster than Hashtable because of synchronization
            // 5. Iteration: HashMap is traversed by Iterator (fail-fast), Hashtable - by Enumerator and Iterator (not fail-fast)
        }
    }

    /**
     * Iterator functionality.
     */
    private void invokeIterator() {
        // Iterator
        {
            ArrayList<Double> l = new ArrayList<>(
                Arrays.asList(10D, 50D, 30D, 40D, 20D, 60D)
            );
            log.info(" * Iterator - ArrayList before: " + l.toString());

            // iterator
            Iterator<Double> iterator = l.iterator();
            StringBuilder builder = new StringBuilder();
            while (iterator.hasNext()) {
                builder.append(iterator.next()).append(" ");
            }
            log.info(" * Iterator: " + builder.toString());

            // list iterator
            ListIterator<Double> listIterator = l.listIterator(l.size());
            builder = new StringBuilder();
            while (listIterator.hasPrevious()) {
                Double value = listIterator.previous();
                builder.append(value).append(" ");
                listIterator.set(value + 10D);
            }
            log.info(" * ListIterator: " + builder.toString());

            // spliterator
            Spliterator<Double> spliterator = l.spliterator();
            spliterator.forEachRemaining(d -> log.info(" * item = " + d));

            ArrayList<Double> sqrts = new ArrayList<>(l.size());
            spliterator = l.spliterator();
            while (spliterator.tryAdvance(d -> sqrts.add(Math.sqrt(d)))) ;
            log.info(" * Spliterator - ArrayList sqrts: " + sqrts.toString());

            // characteristics
            spliterator = l.spliterator();
            var characteristics = spliterator.characteristics();
            log.info(" * Characteristics = " + characteristics);
            var concurrent = spliterator.hasCharacteristics(Spliterator.CONCURRENT);
            log.info("\t - Is concurrent: " + concurrent);
            var distinct = spliterator.hasCharacteristics(Spliterator.DISTINCT);
            log.info("\t - Is distinct: " + distinct);
            var immutable = spliterator.hasCharacteristics(Spliterator.IMMUTABLE);
            log.info("\t - Is immutable: " + immutable);
            var nonNull = spliterator.hasCharacteristics(Spliterator.NONNULL);
            log.info("\t - Is non null: " + nonNull);
            var ordered = spliterator.hasCharacteristics(Spliterator.ORDERED);
            log.info("\t - Is ordered: " + ordered);
            var sized = spliterator.hasCharacteristics(Spliterator.SIZED);
            log.info("\t - Is sized: " + sized);
            var sorted = spliterator.hasCharacteristics(Spliterator.SORTED);
            log.info("\t - Is sorted: " + sorted);
            var subSized = spliterator.hasCharacteristics(Spliterator.SUBSIZED);
            log.info("\t - Is sub sized: " + subSized);
        }

        // Enumeration - legacy (not recommended to use)
        {
            Vector<Double> v = new Vector<>(4);
            v.addAll(Arrays.asList(10D, 50D, 30D, 40D));
            v.addElement(20D);
            v.addElement(60D);
            log.info(" * Enumeration - Vector before: " + v.toString());

            Enumeration<Double> enumeration = v.elements();
            StringBuilder builder = new StringBuilder();
            while (enumeration.hasMoreElements()) {
                Double value = enumeration.nextElement();
                builder.append(value).append(" ");
            }
            var iterator = enumeration.asIterator();
            log.info(" * Enumeration - Vector elements: " + builder.toString());
        }
    }

    /**
     * Comparator functionality.
     */
    private void invokeComparator() {

        var books = Set.of(
            new Book(1, "First", new BigDecimal("23.56")),
            new Book(4, "Final", new BigDecimal("48.10")),
            new Book(2, null, null),
            new Book(1, "Limit", BigDecimal.ZERO),
            new Book(3, "Plus", null),
            new Book(3, null, BigDecimal.TEN)
        );

        Comparator<Long> comparator = Long::compareTo;
        var compare = comparator.compare(5L, 3L);
        log.info(" * Compare 5 with 3 => " + compare);
        var reversed = comparator.reversed();
        log.info(" * Reversed 5 with 3 => " + reversed.compare(5L, 3L));

        List<Integer> nullsFirst = new LinkedList<>(
            List.of(4, 2, 8, 7, 9, 5, 0, 6, 1)
        );
        log.info(" - Natural data: " + nullsFirst.toString());
        Comparator<Integer> intComp = Comparator.nullsFirst(Integer::compareTo);
        Collections.sort(nullsFirst, intComp);
        log.info(" - Nulls first: " + nullsFirst.toString());

        var data = List.of("One", "Two", "Three", "Four", "Five", "Six");
        Comparator<String> setDirect = String::compareTo;
        Set<String> direct = new TreeSet<>(setDirect);
        direct.addAll(data);
        log.info(" - Direct data: " + direct.toString());
        Set<String> reverse = new TreeSet<>(setDirect.reversed());
        reverse.addAll(data);
        log.info(" - Reverse data: " + reverse.toString());

        log.info(" - Books data: " + books.toString());
        Comparator<Book> bookComparator = Comparator
            .comparing(Book::getId)
            .thenComparing(Book::getName, Comparator.nullsFirst(Comparator.naturalOrder()))
            .thenComparing(Book::getPrice, Comparator.nullsLast(Comparator.naturalOrder()));
        Set<Book> library = new TreeSet<>(bookComparator);
        library.addAll(books);
        log.info(" - Library data: " + library.toString());

        Comparator<Book> bookReverse = Comparator.reverseOrder();
        var reversedBooks = books.stream()
            .sorted(bookReverse)
            .collect(Collectors.toCollection(LinkedHashSet::new));
        log.info(" - Library reversed: " + reversedBooks.toString());
    }

    /**
     * Algorithm functionality.
     */
    private void invokeAlgorithm() {

        // Arrays API
        {
            log.info("========== Arrays API ==========");
            // data
            var profiles = new String[]{"Test", "dev", "Prod", "local", "Cloud"};
            log.info(" * Array is: " + Arrays.toString(profiles));
            // array to list
            var list = Arrays.asList(profiles);
            log.info(" * As list: " + list.toString());
            // binary search
            var index = Arrays.binarySearch(profiles, "local", String::compareTo);
            log.info(" * Binary search [local]: " + index);
            // copy
            var copy = Arrays.copyOf(profiles, 7);
            log.info(" * Array copy: " + Arrays.toString(copy));
            // range
            var range = Arrays.copyOfRange(profiles, 2, 6);
            log.info(" * Array range: " + Arrays.toString(range));
            // equals
            var equals = Arrays.equals(profiles, range);
            log.info(" * Array equals: " + equals);
            // fill
            Arrays.fill(copy, 2, 4, "Bug");
            log.info(" * Array fill: " + Arrays.toString(copy));
            // trim
            copy = Arrays.stream(copy).filter(Objects::nonNull).toArray(String[]::new);
            log.info(" * Array clean: " + Arrays.toString(copy));
            // mismatch - index of first mismatch
            var mismatch = Arrays.mismatch(profiles, copy);
            log.info(" * Array mismatch: " + mismatch);
            // sort
            Arrays.sort(copy, String::compareToIgnoreCase);
            log.info(" * Array sort: " + Arrays.toString(copy));
            // parallel sort
            Arrays.parallelSort(copy, String::compareTo);
            log.info(" * Array parallel sort: " + Arrays.toString(copy));
            // spliterator
            var spliterator = Arrays.spliterator(profiles);
            log.info(" * Array spliterator: " + spliterator.estimateSize());
            // stream
            var set = Arrays.stream(profiles)
                .sorted(String::compareToIgnoreCase)
                .distinct()
                .filter(s -> !s.isBlank())
                .skip(2)
                .limit(3)
                .collect(Collectors.toUnmodifiableSet());
            log.info(" * Array stream: " + set.toString());
            // set all
            String[] finalCopy = Arrays.copyOf(copy, copy.length);
            Arrays.setAll(copy, value -> finalCopy[value].toUpperCase());
            log.info(" * Array set: " + Arrays.toString(copy));
            // prefix
            Arrays.parallelPrefix(copy, (s, s2) -> s.concat("-").concat(s2));
            log.info(" * Array set: " + Arrays.toString(copy));
        }

        // Collections API
        {
            log.info("========== Collections API ==========");
            // data
            var profiles = new LinkedList<>(List.of("Test", "dev", "Prod", "local", "Cloud"));
            log.info(" * List of profiles: " + profiles.toString());
            // empty collection - immutable
            var values = new ArrayList<>(Collections.emptyList());
            log.info(" * List empty: " + values.toString());
            // add
            Collections.addAll(values, "a", "b", "c", "d", "e", "f", "g");
            log.info(" * List add: " + values.toString());
            // construct
            var errors = Collections.nCopies(4, "Error");
            log.info(" * List n copies: " + errors.toString());
            // fill
            Collections.fill(values, "Undefined");
            log.info(" * List fill: " + values.toString());
            // sort
            Collections.sort(profiles, String::compareToIgnoreCase);
            log.info(" * List sort: " + profiles.toString());
            // copy
            Collections.copy(values, profiles.subList(0, profiles.size() - 1));
            log.info(" * List copy: " + values.toString());
            // disjoin - no intersection elements
            var disjoint = Collections.disjoint(profiles, values);
            log.info(" * List disjoin: " + disjoint);
            // frequency - qty of elements
            var frequency = Collections.frequency(values, "Undefined");
            log.info(" * List frequency: " + frequency);
            // singleton - immutable
            var singleton = Collections.singletonList("Undefined");
            log.info(" * List singleton: " + singleton.toString());
            // index of sub list
            var index = Collections.indexOfSubList(values, singleton);
            log.info(" * List sub list [first]: " + index);
            // index of sub list
            var last = Collections.lastIndexOfSubList(values, singleton);
            log.info(" * List sub list [last]: " + last);
            // replace
            var replace = Collections.replaceAll(values, "Undefined", "Defined");
            log.info(" * List replace: " + values.toString());
            // reverse
            Collections.reverse(values);
            log.info(" * List reverse: " + values.toString());
            // rotate
            Collections.rotate(values, -3);
            log.info(" * List rotate: " + values.toString());
            // swap
            Collections.swap(values, 1, 3);
            log.info(" * List swap: " + values.toString());
            // shuffle
            Collections.shuffle(values, new Random());
            log.info(" * List shuffle: " + values.toString());
            // unmodifiable
            var unmodifiable = Collections.unmodifiableList(values);
            log.info(" * List unmodifiable: " + unmodifiable.toString());
            // synchronized
            var synchronize = Collections.synchronizedList(values);
            log.info(" * List synchronized: " + synchronize.toString());
            // max element
            var max = Collections.max(profiles);
            log.info(" * Max profile: " + max);
            // min element
            var min = Collections.min(profiles, String::compareTo);
            log.info(" * Max profile: " + min);
            // binary search
            var profile = Collections.binarySearch(profiles, "dev", String::compareTo);
            log.info(" * Binary search [dev]: " + profile);
            // checked collection - type compatibility (f.e. verify input values)
            var strings = Collections.checkedCollection(profiles, String.class);
            log.info(" * Checked collection [String]: " + strings);
        }
    }

    /**
     * Tree functionality.
     */
    private void invokeTree() {
        Tree tree = new Tree();

        log.info("");

        // add nodes
        {
            tree.add(9);
            tree.addRecursive(12);
            tree.add(5);
            tree.addRecursive(8);
            tree.add(15);
            tree.addRecursive(2);
            tree.add(10);
            tree.addRecursive(4);
            tree.add(7);
            tree.addRecursive(3);
            tree.add(1);
            tree.addRecursive(14);
            tree.add(13);
            tree.addRecursive(6);
        }

        // show tree
        TreePrinter.print(tree.getRoot());

        // get nodes
        {
            // iterative
            var foundNode = tree.find(14);
            assert foundNode != null : "Iterative node should exist";
            log.info("- Iterative search: {}", foundNode.getValue());
            // recursive
            var recursiveNode = tree.findRecursive(14);
            assert recursiveNode != null : "Recursive node should exist";
            log.info("- Recursive search: {}", recursiveNode.getValue());
            // DFS - vertically
            var dfsNode = tree.dfsFind(14);
            assert dfsNode != null : "DFS node should exist";
            log.info("- Vertically (DFS) search: {}", dfsNode.getValue());
            // BFS - horizontally
            var bfsNode = tree.bfsFind(14);
            assert bfsNode != null : "BFS node should exist";
            log.info("- Horizontally (BFS) search: {}", bfsNode.getValue());
        }

        // remove nodes
        {
            tree.removeRecursive(5);
            tree.remove(10);
        }

        // check nodes
        {
            var contains1 = tree.contains(14);
            log.info("- Contains '{}': {}", 14, contains1);
            var contains2 = tree.contains(5);
            log.info("- Contains '{}': {}", 5, contains2);
        }

        // show tree
        TreePrinter.print(tree.getRoot());

        // traverse nodes
        {
            // dfs pre-order
            log.info("DFS [pre-order][iterative]:");
            tree.traversePreOrder();
            log.info("");
            log.info("DFS [pre-order][recursive]:");
            tree.traversePreOrderRecursive();
            log.info("");

            // dfs in-order
            log.info("DFS [in-order][iterative]:");
            tree.traverseInOrder();
            log.info("");
            log.info("DFS [in-order][recursive]:");
            tree.traverseInOrderRecursive();
            log.info("");

            // dfs post-order
            log.info("DFS [post-order][iterative]:");
            tree.traversePostOrder();
            log.info("");
            log.info("DFS [post-order][recursive]:");
            tree.traversePostOrderRecursive();
            log.info("");

            // bfs level-order
            log.info("BFS [level-order][iterative]:");
            tree.traverseLevelOrder();
            log.info("");
        }
    }

    /**
     * Scenario use-cases.
     */
    private void invokeScenario() {
        // LRU cache
        {
            log.info("* LRU Cache scenario");

            LRUCache<Integer, String> cache = new LRUCache<>(3);
            cache.put(1, "One");
            cache.put(2, "Two");
            cache.put(3, "Three");
            log.info("Cache [init]: {}", cache);

            cache.put(4, "Four");
            log.info("Cache [next]: {}", cache);

            log.info("Use the 3rd element: {}", cache.get(3));
            log.info("Cache [take]: {}", cache);

            cache.put(5, "Five");
            log.info("Cache [next]: {}", cache);
        }
    }
}
