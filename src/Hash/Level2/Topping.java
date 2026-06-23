package Hash.Level2;

import java.util.*;

public class Topping {
    public static void main(String[] args) {
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};

        solution(topping);
    }
    public static int solution(int[] topping) {
        int answer = 0;

        Map<Integer, Integer> mapA = new HashMap<>();
        Map<Integer, Integer> mapB = new HashMap<>();

        for(int i=0; i<topping.length; i++) { //처음 시작은 A는 안가지고 B가 다 갖는다고 하자.
            mapB.put(topping[i], mapB.getOrDefault(topping[i], 0) + 1);
        }

        for(int i=0; i<topping.length; i++) {
            int toppingA = topping[i];
            mapA.put(toppingA, mapA.getOrDefault(toppingA, 0) + 1);

            if(mapB.containsKey(toppingA)) { //B의 경우 하나를 제거
                mapB.put(toppingA, mapB.get(toppingA) - 1);

                if(mapB.get(toppingA) < 1) { //제거했더니 가지지 못한 경우
                    mapB.remove(toppingA);
                }
            }

            if(mapA.size() == mapB.size()) { //토핑의 개수가 같을 경우
                answer++;
            }
        }

        return answer;
    }
}

/*
롤케이크를 공평하게 나눠먹으려고 한다. 롤케잌 크기보다 위에 토핑 종류에 더 관심이 많다.
그래서 잘린 조각에 상관 없이 토핑의 개수만 동일하게 가져가면 상관 없다.

토핑의 종류가 중요하기 때문에 공평하게 나누고자 한다면, 맛보는 종류의 개수도 같아야하며, 토핑의 개수도 같아야한다.
단 공평하게 나누지 못할 수도 있다.

공평하게 나눌 수 있는 방법의 수를 return하라.

A와 B 중 A가 하나도 없을 때 = B가 모두 가지고 있을 때를 시작으로
A가 롤케익 영역을 하나씩 넓혀가며, 토핑의 개수가 같아지는 시점에 answer++ 진행
토핑의 경우에는 Map을 사용해서 관리한다. 그 이유는 토핑의 개수를 따져야지만, 추가적인 방식을 도출할 수 있기 떄문

이럴 경우 하나씩 영역을 넓혀 가더라도 초기 Map 세팅 비용 O(N) + 영역 넓혀가며 비교하는 비용 O(N)으로 결국 O(N)이기에
토핑 길이가 100만이 되어도 충분히 가능한 시간대이다.
*/