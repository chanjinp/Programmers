package DynamicProgramming.Lv2;

import java.util.Arrays;

public class Sand {
    public static void main(String[] args) {
        int m = 4;
        int n = 4;
        int h = 3;
        int w = 1;
        int[][] drops = {{2, 0}, {1, 3}, {3, 2}, {0, 1}};

        System.out.println(Arrays.toString(solution(m, n, h, w, drops)));
    }

    public static int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[] answer = {0, 0};

        // 1. m x n 크기의 격자판 생성
        int[][] sand = new int[m][n];

        // 2. 빗방울 가중치 기록 (0번째 비는 1, 1번째 비는 2 ... 오버플로우 방지 및 식별용)
        for (int i = 0; i < drops.length; i++) {
            int x = drops[i][0];
            int y = drops[i][1];
            sand[x][y] = i + 1;
        }

        // 선인장 시작점(startX, startY)이 가질 수 있는 최대 한계치 설정
        // x축 방향으로는 세로 길이(h)만큼, y축 방향으로는 가로 길이(w)만큼 공간이 확보되어야 합니다.
        int endX = m - h;
        int endY = n - w;

        int saveX = 0;
        int saveY = 0;
        int max = -1;

        // 3. 안정적인 이중 for문 탐색
        for (int startX = 0; startX <= endX; startX++) {
            for (int startY = 0; startY <= endY; startY++) {

                int minNumber = Integer.MAX_VALUE;

                // 현재 (startX, startY) 위치에서 선인장 크기만큼 내부 탐색
                // 행(x)은 h(세로)만큼, 열(y)은 w(가로)만큼 확장합니다.
                for (int x = startX; x < startX + h; x++) {
                    for (int y = startY; y < startY + w; y++) {
                        int s = sand[x][y];
                        if (s > 0) {
                            minNumber = Math.min(minNumber, s);
                        }
                    }
                }

                // 비를 한 방울도 안 맞는 명당을 찾았다면 즉시 정답으로 반환!
                if (minNumber == Integer.MAX_VALUE) {
                    answer[0] = startX;
                    answer[1] = startY;
                    return answer;
                }

                // 비를 피할 수 없다면, 최솟값 중 최댓값(가장 늦게 비를 맞는 곳) 갱신
                if (minNumber > max) {
                    saveX = startX;
                    saveY = startY;
                    max = minNumber;
                }
            }
        }

        answer[0] = saveX;
        answer[1] = saveY;

        return answer;
    }
}

/*
어딘가에 가로 w, 세로 h 크기의 선인장 구역을 조성하려고 한다. 선인장 구역은 격자 축에 맞춘 연속된 w x h 크기의 부분 격자
회전할 수 없다.

정해진 순서대로 여러 칸에 비를 뿌릴 때, 그 시점을 선인장이 처음으로 비를 맞는 순간을 기록
선인장이 가능한 늦게 비를 맞도록, 선인장 구역의 위치를 정하려고 한다.

1. 선인장이 비를 맞지 않도록 선인장 구역의 위치를 정할 수 있다면, 해당 위치가 가장 우선된다.
2. 가능한 늦게 비를 맞는 선인장 구역 후보가 여러 개라면 그 중 가장 위쪽 행, 그래도 여러개면 가장 왼쪽 열에 위치한 구역

풀이
1. 일단 w와 h로 사막을 구성
2. 비가 오는 지역의 순서를 하나씩 찍는다. (비 맞는 구역)
3. 선인장을 배치하는 구역을 구성한다.
3-1. 선인장을 배치했을 때 안맞거나 혹은 제일 나중에 맞는 지역인지?

그렇다면, 비를 맞는 순서를 가중치라고 두었을 때, 선인장 배치 안에 들어오는 합이 가장 클 경우 혹은 0이될 경우를 찾는다.
우선은 안맞는게 우선이니 0인 지역을 찾는다.

찾을 때 비를 안맞는 혹은 같은 조건에 대해서 제일 왼쪽 상단에 가까워야한다.

가중치를 따지는게 아니라 선인장을 배치했을 때 가장 작은 값이 보다 클 경우

단, 이렇게 구했을 경우 사막이 제한이 없을 경우에는 시간초과가 뜬다 (매번 선인장 배치를 봐야하기 떄문)

TODO 그러므로, 미리 비 온 곳의 누적함을 구하여 확인한다 (DP) << 해당 알고리즘 학습 필요
*/
