package Hash.Level1;

import java.util.HashSet;
import java.util.Set;

public class Ponketmon {
    public static void main(String[] args) {
        int[] nums = {3,3,3,2,2,2};

        System.out.println(solution(nums));

    }

    public static int solution(int[] nums) {
        int answer = 0;
        Set<Integer> set =  new HashSet<>();
        int choice = nums.length / 2;

        for(int i : nums) {
            set.add(i);
        }

        if(set.size() > choice) { //Math.min 활용 필요
            answer = choice;
        } else {
            answer = set.size();
        }

        return answer;

        //다른 사람 문제 풀이 분석
        /*return Arrays.stream(nums)
                .boxed()// boxed 함수의 경우 Stream<Integer> 형태로 리턴되며, 로직 중 mapToObj(Integer::valueOf, 0)에서 0이라는 defaultFlag를 사용해서 원시형 타입을 래퍼 타입으로 변환시킨다. (오버헤드 발생)
                .collect(Collectors.collectingAndThen(Collectors.toSet(),
                        phonekemons -> Integer.min(phonekemons.size(), nums.length / 2)));*/
                //.collect 함수 내부 파라미터 형식을 통해 return을 결정 지으며, 내용은 아래와 같다.
                //Collection의 collectingAndThen 함수의 경우 첫 번째 파라미터로 Collection을 Set으로 변환해라(toSet) 그리고 나오는 함수를 실행하여 결과를 리턴해라
                //다른 풀이를 분석해보면, Stream의 활용이 돋보이며 코드가 매우 간결하고 명확하다. 다만 래퍼 타입으로 바꾸거나 Collection의 내부 동작에서 걸리는 오버헤드로 단순 코드보단 비교적 비효율 적일 수 있다.

        // 내 생각: 실무에서는 위와 같은 코드를 매우 선호하는 거 같다. 솔직히 성능의 차이라고 해봐야 하드웨어적인 면에서 별 차이도 안난다. 다만, 열약한 컴퓨터 환경 속에서 해야한다면, 이런 오버헤드까지 생각해볼 필요는 있을 거 같다.
    }

}

/*
가진 폰켓몬 중 절반을 가져도 된다.
종류에 따라 각기 다른 번호를 가지며, 같은 종류는 같은 번호르 가진다.
최대 얼마나 많은 종류의 폰켓몬을 가질 수 있을까?

총 가질 수 있는 폰켓몬은 nums.length / 2

문제 풀이.
Set<int>로 중복 전부 없앤 후 가질 수 있는 길이보다 Set<int>의 크기가 클 경우 가질 수 있는 폰켓몬의 수 아닐 경우 Set의 길이 수
*/
