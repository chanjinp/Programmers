package Hash.Level2;

import java.util.*;
public class DiscountEvent {
    public static void main(String[] args) {

    }

    public static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        //Map 구성하기
        Map<String, Integer> map = new HashMap<>();

        for(int i=0; i<want.length; i++) {
            String w = want[i];
            int n = number[i];

            map.put(w, n);
        }

        int maxIdx = discount.length - 10; //회원권 10일이기에 뺸 만큼 가입이 가능한 일자

        for(int i=0; i <= maxIdx; i++) {
            Map<String,Integer> copyMap = new HashMap<>(map);

            for(int j = i; j < i + 10; j++) {
                String d = discount[j];

                if(copyMap.containsKey(d)) {

                    copyMap.put(d, copyMap.get(d) - 1);


                    if(copyMap.get(d) == 0) {
                        copyMap.remove(d);
                    }
                }
            }
            if(copyMap.isEmpty()) {
                answer++;
            }
        }


        return answer;
    }
}

/*
- 일정 금액 지불 시 10일동안 회원 자격 부여
- 회원 상대로 한 가지 제품 할인 행사하며, 하루에 하나씩만 구매 가능
- 자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치할 경우 가입하려고 한다.

원하는 제품: want / 원하는 제품 수량: number / 마트에서 할인하는 제품: discount

return: 회원 등록 시 정현이가 원하는 제품을 모두 할인 받을 수 있는 회원 등록 날짜의 총 일수

풀이
1. 정현이가 원하는 물품과 개수를 Map에 넣는다.
2. discount[0] ~ discount[discount.length - 10]까지 반복하며, Map의 물품 count를 감소 시킨다. 혹여나 Map의 길이가 존재한다면, 원하는 물품을 다 사지 못했기에 정답이 되지 않는다.

[피드백]
해당 풀이의 경우 10일이라는 작은 숫자의 이벤트 기간이 주어졌고 discount 길이 또한 10만으로 작기 떄문에 가능하다.

만약 제한의 범위가 커질 경우에는 시간 초과 우려가 있다.
이 때 사용할 수 있는 방법이 Sliding Window 방식으로 회원이 원하는 항목을 저장하는 Map과 초기 10일치 저장하는 Map을 따로 두고
최초 10일치의 Map과 회원이 원하는 항목 Map이 같다면 answer++이고 아닐 경우에는 10일 이후의 하루 하루씩 항목들을 Count하면서 (맨 앞의 항목의 경우 -1, 다음 일차의 항목은 +1)
진행하며, 회원이 원하는 Map과 같은지를 계속 판단하여 answer++을 진행한다.
*/
