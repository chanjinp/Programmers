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
        Map<String, String> map = new HashMap<>();
        boolean[] visited = new boolean[clothes.length];

        map.put(clothes[0][1], clothes[0][0]);
        visited[0] = true;

        dfs(map, clothes, visited);

        return count;
    }

    public static void dfs(Map<String, String> map, String[][] clothes, boolean[] visited) {
        for(int i = 0; i< clothes.length; i++) {

        }
    }
}

/*
코니는 종류별 최대 1가지 의상만 착용하 수 있다.
다른 의상이 겹치면 안된다.
코니가 가진 의상들이 담긴 2차원 배열이 주어질 때, 서로 다른 옷의 조합 수를 return 하도록
!! 코니는 하루에 최소 한 개의 의상은 입습니다.

각 행은 의상의 이름, 의상의 종류
코니가 가진 의상의 수는 최소 1 최대 30

종류가 다른지도 봐야한다. 만약 Set에 넣었다면 (이미 입었다면) 다른 종류일 경우에만 입을 수 있기 떄문

TODO 시간이 늦은 관계로 06/11에 이어서 풀기
*/