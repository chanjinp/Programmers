package Greedy.Lv2;

public class JoyStick {

    public static final int posA = 65; //A의 아스키 코드 10진
    public static final int posZ = 90;

    public static void main(String[] args) {
        String name = "JEROEN";

        System.out.println(solution(name));
    }

    public static int solution(String name) {
        char[] chars = name.toCharArray();

        return countUpAndDown(chars) + countLeftAndRight(chars);
    }

    public static int countUpAndDown(char[] chars) { //조이스틱 위 아래 움직이는 건에 대해서 카운트
        int count = 0;

        for (char c : chars) {
            if ('A' != c) {
                int distanceByA = Math.abs(posA - (int) c);
                int distanceByZ = Math.abs(posZ - (int) c);

                if (distanceByA <= distanceByZ) { // A와 더 가깝거나 같다면 (같을 경우는 굳이 뒤로 가서 +1 해줄 필요 없음)
                    count += distanceByA;
                } else {
                    count += distanceByZ + 1; //A에서 뒤로 한 칸 움직이면 Z가 나오기 때문에 뒤로 간만큼 1증가
                }
            }
        }
        return count;
    }
    public static int countLeftAndRight(char[] chars) { //TODO 꼭 다시 풀어보기
        int minMove = chars.length - 1; // 기본: 정방향

        for (int i = 0; i < chars.length; i++) {
            // 1. 연속된 A의 끝 지점 찾기
            int next = i + 1;
            while (next < chars.length && chars[next] == 'A') {
                next++;
            }

            // 2. 3가지 경우의 수 중 최솟값 업데이트
            // Case 1: 정방향 (이미 minMove에 들어있음)
            // Case 2: i까지 갔다가 돌아오기
            minMove = Math.min(minMove, (i * 2) + (chars.length  - next));

            // Case 3: 왼쪽으로 먼저 갔다가 돌아오기
            minMove = Math.min(minMove, i + (chars.length  - next) * 2);
        }
        return minMove;
    }
}

/*
조이스틱을 위 아래 왼쪽 오른쪽으로 돌려가며 문자를 구성해라.
최소한으로 움직였을 때의 수를 구해라

첫 알파벳의 경우 A부터 Z까지 시작하며, !! Z에서 위로 올릴 경우 A로 !! A에서 아래로 내릴 경우 Z로 즉, 끝과 끝의 숫자로 이동 가능하다.

왼쪽과 오른쪽의 이동 또한 각 인덱스의 끝으로 갈 수 있다.

최소한으로 움직여야하는 규칙?

첫 번째. 일단 왼쪽 혹은 오른쪽으로 움직이기 전에 자기 자리에서 해당되는 문자가 Z와 가까운지? A와 가까운지를 판단해서 움직인다.

두 번째, 그 다음 내 왼쪽 혹은 오른쪽의 값을 보며, 더 횟수가 적은 쪽으로 이동 후 알파벳을 맞춘다.

예외: 만약 해당되는 문자가 'A'에 해당하는 경우 움직일 필요 없음

좌 우 이동의 비용과

상 하 이동의 비용을 따로 보면 될 듯하다.

피드백: 좌 우 이동의 경우 AAA... 연속된 경우가 나올 경우 계산하기 힘들 수 있다.
A가 아닌 문자들을 기점으로 최적으로 움직이는 방안을 생각해보면 좋을 듯하다.

 */
