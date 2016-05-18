package mytest;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestLambda4 {
    static int outerStaticNum;
    int        outerNum = 1;

    @Test
    public void testLambda1() {
        int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
    }

    @Test
    public void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }

    @Test
    public void testPredicates() {
        Predicate<String> predicate = (s) -> s.length() > 0;
        predicate.test("foo");// true
        predicate.negate().test("foo");// false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    @Test
    public void testFunction() {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123"); // "123"
    }

    @Test
    public void testSupplier() {
        //Supplier接口产生一个给定类型的结果。与Function不同的是，Supplier没有输入参数。
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get(); // new Person
    }

    @Test
    public void testConsumers() {
        //Consumer代表了在一个输入参数上需要进行的操作。
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));
    }

    @Test
    public void testComparator() {
        //Comparator接口在早期的Java版本中非常著名。Java 8 为这个接口添加了不同的默认方法。
        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");

        comparator.compare(p1, p2); // > 0
        comparator.reversed().compare(p1, p2); // < 0
    }

    @Test
    public void testOptional() {
        //Optional是一个简单的值容器，这个值可以是null，也可以是non-null。考虑到一个方法可能会返回一个non-null的值，也可能返回一个空值。为了不直接返回null，我们在Java 8中就返回一个Optional.
        Optional<String> optional = Optional.of("bam");
        optional.isPresent(); // true
        optional.get(); // "bam"
        optional.orElse("fallback"); // "bam"
        optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"
    }

    @Test
    public void testStream() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);

        // "aaa2", "aaa1"
        stringCollection.stream().sorted().filter((s) -> s.startsWith("a"))
            .forEach(System.out::println);
        // "aaa1", "aaa2"

        stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
            .forEach(System.out::println);
        // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"

        boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA); // true

        boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA); // false

        boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ); // true

        long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();

        System.out.println(startsWithB); // 3
        /*
         */
        Optional<String> reduced = stringCollection.stream().sorted()
            .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
        // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"
    }

    @Test
    public void testParallel() {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
        // sequential sort took: 899 ms

        long tt0 = System.nanoTime();

        long countt = values.parallelStream().sorted().count();
        System.out.println(countt);

        long tt1 = System.nanoTime();

        long millistt = TimeUnit.NANOSECONDS.toMillis(tt1 - tt0);
        System.out.println(String.format("parallel sort took: %d ms", millistt));

// parallel sort took: 472 ms
    }
}