package BruteForce.Lv2;

public class PushMatrix {
    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};

        for(int s : solution(rows,columns,queries)) {
            System.out.println(s);
        }
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] map = new int[rows][columns];

        // 1. Map 초기화 (1부터 시작)
        int value = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = value++;
            }
        }

        // 2. 쿼리 회전 수행
        for (int q = 0; q < queries.length; q++) {
            // 0-based index 변환 (-1 처리)
            int x1 = queries[q][0] - 1;
            int y1 = queries[q][1] - 1;
            int x2 = queries[q][2] - 1;
            int y2 = queries[q][3] - 1;

            // 시작점 저장 및 최솟값 초기화
            int temp = map[x1][y1];
            int min = temp;

            // [좌측 변] 아래에서 위로 당기기
            for (int x = x1; x < x2; x++) {
                map[x][y1] = map[x + 1][y1];
                min = Math.min(min, map[x][y1]);
            }

            // [하단 변] 오른쪽에서 왼쪽으로 당기기
            for (int y = y1; y < y2; y++) {
                map[x2][y] = map[x2][y + 1];
                min = Math.min(min, map[x2][y]);
            }

            // [우측 변] 위에서 아래로 당기기
            for (int x = x2; x > x1; x--) {
                map[x][y2] = map[x - 1][y2];
                min = Math.min(min, map[x][y2]);
            }

            // [상단 변] 왼쪽에서 오른쪽으로 당기기
            for (int y = y2; y > y1 + 1; y--) {
                map[x1][y] = map[x1][y - 1];
                min = Math.min(min, map[x1][y]);
            }

            // 따로 보관했던 temp를 (x1, y1 + 1) 위치에 대입
            map[x1][y1 + 1] = temp;

            answer[q] = min;
        }

        return answer;
    }
}
/*
1. queries에 x1 y1 x2 y2로 주어진다. + 회전의 갯수 (queries.length())
2. x1과 y1부터 x2와 y2까지 (중간 공간은 제외) 한 채로 시계방향으로 이동 + 해당 범위에서 가장 작은 숫자 뽑기

Q. 범위를 잡는 방식
Q. 각 숫자 겹치지 않으며, Sorting 작업이 필요하다 (비오름차순)
Q. 1 ≤ x1 < x2 ≤ rows, 1 ≤ y1 < y2 ≤ columns이라는 조건이기에, 항상 범위는 좌에서 우측으로 구성된다
*/