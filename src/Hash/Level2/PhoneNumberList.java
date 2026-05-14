package Hash.Level2;

import java.util.*;

public class PhoneNumberList {
    public static void main(String[] args) {
        String[] input = {"12","123","1235","567","88"};
        String[] input2 = {"123", "456", "789"};

//        System.out.println(solution(input));
        String[] test = {"123", "12", "567", "1234"};
        Arrays.sort(test);

        Arrays.stream(test).forEach(System.out::println);
    }

    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        Set<String> set = new HashSet<>();

        for(int i = 0; i < phone_book.length; i++){
            set.add(phone_book[i]);
        }

        for(String phone : phone_book){
            for(int i=1; i<phone.length(); i++) {
                String key = phone.substring(0, i);

                if(set.contains(key)){
                    return false;
                }
            }
        }
        return answer;
    }
}

/*
문제 풀이
어떤 전화번호가 다른 번호의 접두어인 경우가 있으면 false 아니면 true
접두어 = A라는 사람의 전화번호가 B라는 사람의 전화번호의 앞부분이 동일한지?

1. Map<K,V>를 사용 → 이때 Key: 전화번호 Value: 전화번호 길이
2. Map의 사이즈만큼 반복 진행
2-1. Key를 꺼내 >> 접두어인지 판단용
2-2. Value를 꺼내 >> 얼마나 자를지 판단용 (만약 꺼낸 Value가 비교할 Value보다 작은 경우는 굳이 비교할 필요 없음.)
2-3. subString으로 자른 값이 Key와 동일하다면 false, 끝까지 돌았는데 아닐 경우 true로 고정

위의 방식은 너무 비효율적인 (오버헤드가 큰 방식) 실제 위와 같이 구현했을 때 시간 초과가 나옴

containsKey를 활용하며, 전화번호 하나를 꺼내서 문자 하나씩 잘라가며, 비교하면되는데.. Value를 쓸 필요가 없음 >> 즉 HashSet을 사용해서 Key 값만 가지고 따지기

다른 사람 풀이
다른 사람의 경우에는 String에서 제공하는 startWiths 함수를 사용해서 DB의 Like '접두사%'를 사용해서 Set 등을 활용하지 않고도 String 자체만으로도 해결 가능

추가로 다른 풀이에서는 정렬하는 로직도 발견
처음에는 정렬을 왜하지? 싶었는데. Arrays.sort의 경우 사전 기준 정렬 진행으로 ["123", "12", "567", "1234"]를 정렬하면 ["12", "123", "1234", "567"]로 되는 것을 활용하여,
startWith을 활용하여 원소 하나씩 2중 For을 하나의 For문으로 줄여 사용

내 생각
실무에서 QueryDsl 구성 과정에서 startWith을 많이 사용했으나, String에서 제공되는 줄은 몰랐다.
이제와서 생각해보니 QueryDsl에서의 Q클래스에 정의된 Entity의 필드 타입의 경우 StringPath 구성되는데 String 파생으로 되기 때문에, StartWith을 자연스레 써왔다는 생각이 든다.

# 꼬리에 꼬리를 무는 생각
StringPath와 String의 startWith의 차이는 무엇일까?
1. String의 경우 자바 문법으로 startWith이라는 함수의 동작을 거쳐 결과를 리턴하며 실제 구동은 자바에서 하기 때문에 JVM에서 처리한다.
2. StringPath의 경우 Query문에서 Like '문자열%'이라는 것을 실행할 수 있도록 해당 문자열로 리턴되도록 하며, 실제 구동은 DB에서 쿼리문으로 실행된다. (+ 직렬화된 객체)
*/
