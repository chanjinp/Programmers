package Greedy.Lv2;

import java.util.ArrayList;
import java.util.Arrays;

public class Boat {
    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50};
        int limit = 100;

        System.out.println(solution(people, limit));
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);
        int startIdx = 0;
        int endIdx = people.length - 1;

        while (startIdx <= endIdx) {
            int weight = people[startIdx] + people[endIdx]; //제일 무거운 사람과 제일 가벼운 사람을 넣었을 때

            if(weight > limit) { //limit을 넘어버린다면
                endIdx--;
                answer++; //제일 무거운 사람은 혼자 가야하는 것이 확정
            } else {
                endIdx--;
                startIdx++;
                answer++;
            }
        }

        return answer;
    }
}
/*
구명보트
사람마다 무게가 있고 보트에는 제한 무게가 있다.
얼만큼 해야 최소 횟수로 모든 사람을 옮길 수 있는가?

기준 1.
무게의 합이 가장 limit과 근접한 숫자일 때를 고려해야한다.
ex) limit: 100  A: 90 B: 80 C: 20일 때 A만 옮기는 게 아닌 B와 C를 같이해서 최대한 limit에 근접하도록

무거운 사람을 하나씩 골라서 더했을 때 가장 큰 값이 나오는 경우에 대해서 값으로 짝을 짓는다.

투 포인터 형식으로 진행해도 괜찮을 거 같다.

제한점!! 보트에는 단 두명 씩 밖에 탑승 가능하다.

투 포인터가 가능한 이유
보트에 최대 단 두명만 가능하다는 제한점이 있기 떄문에 투 포인터로 가장 무거운 + 가장 가벼운 조합으로 골라서 갈 경우를 고려해서 정하면 된다.

보트 최대 인원이 정해져있지 않다면, 그리디 알고리즘이 아닌 배낭 문제처럼 완전 탐색 혹은 DP 방식으로 최적의 수를 찾아가며 진행해야한다.

시간 복잡도의 경우, 무조건 무게 순으로 정렬이 되어있는 것을 가정해야하기에
정렬 알고리즘 O(NlogN + N) = O(NlogN)이 된다.
*/