package Hash.Level2;

import java.util.HashMap;

import java.util.Map;

public class Clothing {

    public static int count = 0;
    public static void main(String[] args) {
        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

        System.out.println(solution(clothes));
    }

    public static int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();

        // 1. 카테고리별로 개수 저장
        for (String[] cloth : clothes) {
            String type = cloth[1];
            map.put(type, map.getOrDefault(type, 0) + 1);
        }

        // 2. 조합 계산
        int answer = 1;
        for (int count : map.values()) {
            answer *= (count + 1); // (개수 + 1) 곱하기
        }

        // 3. 아무것도 안 입는 경우 빼기
        return answer - 1;
    }
}

/*
코니는 종류별 최대 1가지 의상만 착용할 수 있다.
다른 의상이 겹치면 안된다.
코니가 가진 의상들이 담긴 2차원 배열이 주어질 때, 서로 다른 옷의 조합 수를 return 하도록
!! 코니는 하루에 최소 한 개의 의상은 입습니다.

각 행은 의상의 이름, 의상의 종류
코니가 가진 의상의 수는 최소 1 최대 30

종류가 다른지도 봐야한다. 만약 Set에 넣었다면 (이미 입었다면) 다른 종류일 경우에만 입을 수 있기 떄문

옷을 선택하지 않는 조건
1. Type이 겹치면 안된다. 머리 -> 머리 x
2. 입었던 옷은 다시 입을 수 없다.
3. 아예 그냥 선택을 안할 수도 있다.

조합의 개수 -> 각 타입별 개수를 구한 후 곱셉을 통해 구한다.
다만 내가 머리만 쓰고 상의를 선택 안하는 경우도 있기 때문에, 각 안고르는 경우를 카운팅하여 +1을 진행한다.

마지막 answer의 경우 아무것도 안입는 경우는 예외에서 제외처리했기 때문에, -1을 진행한다.

dfs라고 생각했던 이유: 모든 케이스에 대해서 구하려고 하다보니 (Brute Force처럼)

실제는 수학적인 접근으로 조합의 개수를 구한다 다만, 특이 케이스로 옷을 하나만 있는 경우가 발생하기 때문에, 해당 부분을 염두해두고 진행한다.
마지막에는 아무런 옷을 안입는 경우는 문제에서 없다고 했기 때문에 해당 케이스를 예외처리해준다.

피드백
알고리즘을 캐치해야하는 건 당연하지만 너무 한 쪽으로 매몰되는 편도 최대한 배제해야겠다는 생각이 든다. 모든 경우의 수를 구하려고 하기보단,
수학적 접근이 가능한지? 효율적으로 데이터 관리가 필요한지 좀 더 생각이 필요해보인다.
*/