package com.example.enum_test.business.enumTest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/testStream")
public class streamController {

    @GetMapping("findFirst")
    public OptionalInt findFirstAndFindAny(){
        IntStream intStream01 = IntStream.of(4,2,5,3,1,1,6);
        IntStream intStream02 = IntStream.of(7,2,5,3,7,6,6);

        return intStream01.sorted().findAny();
    }

    @GetMapping("reduce")
    public OptionalInt reduce(){
        IntStream intStream01 = IntStream.of(4,2,5,3,1,1,6);
        IntStream intStream02 = IntStream.of(7,2,5,3,7,6,6);

        return intStream01.reduce((x,y)-> x+y);
    }

    @GetMapping("ArrayToStream")
    public Optional<String> ArrayToStream(){
        String[] arr1 = {"aaa","bbb","ccc"};
        Stream<String> strStream = Arrays.stream(arr1);
        return strStream.sorted().findAny();
    }

    @GetMapping("ListToStream")
    public Optional<String> ListToStream(){
        List<String> arr1 = Arrays.asList("aaa","bbb","ccc","ddd","ffff");
        Stream<String> List1 = arr1.stream();
        //List1.limit(2).forEach(System.out::println);

        return List1.sorted().findAny();
    }

    //static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)
    //iterate()는 씨앗값(seed)으로 지정된 값부터 시작해서 람다식 f에 의해 계산된 결과를 다시 seed값으로 계산을 반복한다.
    @GetMapping("iterateTest")
    public void iterateTest(){
        Stream<Integer> intStream = Stream.iterate(0, n -> n+2).limit(3);
        intStream.forEach(System.out::println);
    }

    //generate()는 iterate()와 달리 이전 결과를 이용해서 다음 요소를 계산하지 않는다.
    //무한으로 generate -> limit 필요!
    @GetMapping("generateTest")
    public void generateTest(){
        Stream<Integer> intStream = Stream.generate(()->2+3).limit(3);
        intStream.forEach(System.out::println);
    }

    //Stream의 static 메서드인 concat()을 사용해서 두 스트림을 하나로 연결할 수 있다.
    @GetMapping("concatStreamTest")
    public Stream<String> concatStreamTest(){
        Stream<String> strStream01  = Stream.of(new String[]{"aaa","bbb","ccc"});
        Stream<String> strStream02  = Stream.of(new String[]{"ddd","eee","fff"});
        return Stream.concat(strStream01,strStream02);
    }

    //parallel()을 사용하면 병렬처리로 연산을 수행할 수 있습니다. 순차적으로 연산을 수행하지 않고 여러개의 작업을 병렬로 처리합니다.
    // (1 + 2) + (3 + 4) + ... + (9 + 10)처럼 병렬적으로 처리됩니다.
    @GetMapping("parallelAndReduceStreamPlus")
    public Optional<Integer> parallelAndReduceStream(){
        Stream<Integer> integerStream = Stream.of(1,2,3,4,5,6,7,8,9,10);
        Optional<Integer> resultStream = integerStream.parallel().reduce((x,y) -> x+y);
        return resultStream;
    }

    //parallel()을 사용하면 병렬처리로 연산을 수행할 수 있습니다. 순차적으로 연산을 수행하지 않고 여러개의 작업을 병렬로 처리합니다.
    // (1 + 2) + (3 + 4) + ... + (9 + 10)처럼 병렬적으로 처리됩니다.
    @GetMapping("parallelAndReduceStreamMinus")
    public Optional<Integer> parallelAndReduceStreamMinus(){
        Stream<Integer> integerStream = Stream.of(1,2,3,4,5,6,7,8,9,10);
        Optional<Integer> resultStream = integerStream.parallel().reduce((x,y) -> x-y);


        return resultStream;
    }

    // 중복 요소 제거
    @GetMapping("distinctTest")
    public Stream<Integer> distinct(){
        Stream<Integer> integerStream = Stream.of(1,2,2,3,4,4,4,5);
        return integerStream.distinct();
    }

    //filter()는 주어진 조건(Predicate)에 맞지 않는 요소를 걸러낸다.
    @GetMapping("filter")
    public Stream<Integer> filter(){
        Stream<Integer> integerStream = Stream.of(1,2,2,3,4,4,4,5);
        return integerStream.distinct().filter(n -> n %2 ==0);
    }
    @GetMapping("filter02")
    public Stream<Integer> filter02(){
        Stream<Integer> integerStream = Stream.of(1,2,2,3,4,4,4,5);
        return integerStream.distinct().filter(n -> n %2 ==0 && n !=2);
    }

    //map() 스트림의 요소에 저장된 값 중에서 원하는 필드만 뽑아내거나, 특정 형태로 변환해야 할 때
    //peek() :출력  / forEach()와 달리 스트림의 요소를 소모하지 않으므로 연산 사이에 여러 번 사용 가능
    @GetMapping("mapTest")
    public Stream<Integer> mapTest(){
        Stream<Integer> integerStream = Stream.of(1,2,2,3,4,4,4,5);
        return integerStream
                .peek(n-> System.out.println("n1:"+n))
                .map(n -> n +1)
                .peek(n-> System.out.println("n2:"+n))
                .distinct()
                .peek(n-> System.out.println("n3:"+n));
    }

    //Optional
    @GetMapping("OptionalOfNullable")
    public Optional<?> ofNullable(){
        Optional<?> opt =  Optional.ofNullable(null);
        return opt;
    }
    @GetMapping("OptionalOf")
    public Optional<?> OptionalOf(){
        Optional<String> optVal = Optional.of("abc");
        String str1 = optVal.get();   // optVal에 저장된 값을 반환. null이면 예외발생
        String str2 = optVal.orElse("");  // optVal에 저장된 값이 null이면 ""를 반환
        String str3 = optVal.orElseGet(String::new);  // null을 대체할 값을 반환하는 람다식 지정
        String str4 = optVal.orElseThrow(NullPointerException::new);  // null일 때 지정된 예외를 발생

        System.out.println("str1:"+str1);
        System.out.println("str2:"+str2);
        System.out.println("str3:"+str3);
        System.out.println("str4:"+str4);
        return optVal;
    }

    //match Test
    //match는 스트림에서 찾고자 하는 객체가 존재하는지 탐색을 하고 boolean 타입으로 결과를 리턴합니다.
    // 메소드는 anyMatch(), allMatch(), noneMatch()가 있습니다.
    @GetMapping("matchTest")
    public boolean matchTest(){
        Stream<String> strStream = Stream.of("aaa","bbb","ccc");
        Stream<Integer> intStream = Stream.of(1,2,3);
        //strStream.anyMatch(n->n.equals("aaa"));
        //strStream.peek(n -> System.out.println("n :"+n)).allMatch(n->n.startsWith("a"));
        //strStream.peek(n -> System.out.println("n :"+n)).anyMatch(n->n.contains("b"));

        return intStream.anyMatch(n->n.intValue() ==1);
    }


    //collectTest
    //Collectors
    @GetMapping("collectTest")
    public Map<String,Object> collectTest(){
        Stream<String> stringStream = Stream.of("aaa","abbb","ccc","ddd","ahhh");
        return stringStream.map(n-> n.startsWith("a"))
                .peek(n-> System.out.println("peek n :"+n))
                .collect(Collectors.toMap(n->n+"id",n -> n));
    }

}
