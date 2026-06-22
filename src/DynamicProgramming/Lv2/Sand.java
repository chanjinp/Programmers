package DynamicProgramming.Lv2;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

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
        // 1. 격자판 및 기본 가중치 기록
        int[][] sand = new int[m][n];
        for (int[] row : sand) Arrays.fill(row, Integer.MAX_VALUE); // 빈 곳은 무한대로 초기화

        for (int i = 0; i < drops.length; i++) {
            sand[drops[i][0]][drops[i][1]] = i + 1;
        }

        // 2. 가로 방향(너비 w)에 대한 최솟값 압축 배열 생성
        // rowMin[i][j] = i번째 행의 j부터 j+w-1까지 중 최솟값
        int[][] rowMin = new int[m][n - w + 1];
        for (int i = 0; i < m; i++) {
            Deque<Integer> deque = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                // 범위를 벗어난 인덱스는 덱에서 제거
                if (!deque.isEmpty() && deque.peekFirst() < j - w + 1) {
                    deque.pollFirst();
                }
                // 현재 값보다 큰 이전 값들은 최솟값 경쟁에서 밀리므로 제거
                while (!deque.isEmpty() && sand[i][deque.peekLast()] >= sand[i][j]) {
                    deque.pollLast();
                }
                deque.offerLast(j);

                // 윈도우 크기 w를 충족했을 때부터 최솟값 기록
                if (j >= w - 1) {
                    rowMin[i][j - w + 1] = sand[i][deque.peekFirst()];
                }
            }
        }

        // 3. 세로 방향(높이 h)에 대한 최솟값 최종 압축 (선인장 크기 w x h 완성)
        // 최종적으로 이중 for문 내부에서 O(1) 만에 최솟값을 꺼낼 수 있게 지도를 만듭니다.
        int endX = m - h;
        int endY = n - w;

        int saveX = 0;
        int saveY = 0;
        int max = -1;

        for (int startY = 0; startY <= endY; startY++) {
            Deque<Integer> deque = new LinkedList<>();
            for (int startX = 0; startX < m; startX++) {
                if (!deque.isEmpty() && deque.peekFirst() < startX - h + 1) {
                    deque.pollFirst();
                }
                while (!deque.isEmpty() && rowMin[deque.peekLast()][startY] >= rowMin[startX][startY]) {
                    deque.pollLast();
                }
                deque.offerLast(startX);

                if (startX >= h - 1) {
                    int currentX = startX - h + 1;
                    int minNumber = rowMin[deque.peekFirst()][startY];

                    // 비를 안 맞은 완벽한 명당 발견 시 즉시 반환
                    if (minNumber == Integer.MAX_VALUE) {
                        return new int[]{currentX, startY};
                    }

                    // 최소 가중치 중 최댓값 갱신
                    if (minNumber > max) {
                        saveX = currentX;
                        saveY = startY;
                        max = minNumber;
                    }
                }
            }
        }

        return new int[]{saveX, saveY};
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

*/
