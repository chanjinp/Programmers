package Hash.Level1;

import java.util.HashMap;
import java.util.Map;

// 완주하지 못한 선수
public class UnfinishedPlayer {
    public static void main(String[] args) {
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};

        System.out.println(solution(participant, completion));
    }

    public static String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();

        //MAP 구성
        for (String s : participant) {
            map.put(s, map.getOrDefault(s, 0) + 1); //getOrDefault 함수를 통해 Key 값이 없을 경우 0으로 설정 후 + 1
        }

        //완주하지 못한 선수명 구하기
        for (String s : completion) {
            int val = map.get(s);
            if (val == 1)  {
                map.remove(s);
            } else  {
                map.put(s, val - 1);
            }
        }

        return map.keySet().iterator().next();
    }
}

/*
코드 작성 전 문제 풀이
- 참가자 명단 중 완주자 명단에 이름이 없다면 >> 완주 못한 사람
- 다만, 참가자 명단 중 동명 이인이 있을 수 있다.

Map<String, Integer>에서 Key 값으로 참가자 명단을 기준으로 Count한 결과를 Value에 넣는다.

Logic
참가자 명단만큼 반복
if: Map에 Key 값이 있으면? Value 값 ++
else: Map에 Key 값이 없으면? Map에 추가하며 Value 값은 1로 설정

그 후 완주자 명단만큼 반복 진행
완주자 명단의 Key 값에 해당되는 Value 값 -- 만약 value가 그냥 1일 경우에는 동명이인이 아니므로 Map에서 삭제

최종적으로 value가 0 이상인 애를 뽑는다.

결과: 효율성 테스트에서 시간 초과

배운 점: 시간 초과의 이유 computeIfPresent과 같은 함수형 호출을 진행하면서 단순 if-else보다 비용이 크게 듦

!!실무에서는 큰 성능 차이가 안나거나 데이터 양이 비정상적으로 많은 케이스의 경우가 적을 수 있기 때문에, 놓칠 수 있지만,
가독성이 크고 코드가 깔끔해지며, 유지보수가 그만큼 높아지기 때문에 경우의 수를 고려하여 적절하게 함수형 인터페이스 사용 필요!!
*/
