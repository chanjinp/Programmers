package StackAndQueue.Lv2;

import java.util.*;
public class GameMapDistance {
    static int[] dx = {-1, 1, 0, 0}; // 좌, 우, 상, 하
    static int[] dy = {0, 0, -1, 1}; // 좌, 우, 상, 하

    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};

        System.out.println(solution(maps));
    }

    public static int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0}); // 시작점 (x, y)

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            //4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 1. 맵 범위 체크
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                // 2. 벽(0)이 아니고, 아직 방문하지 않은 길(1)인 경우
                if (maps[nx][ny] == 1) {
                    // 이전 위치 거리 +1를 저장
                    maps[nx][ny] = maps[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        /* Stack 구조로 변경 → Time Limit 위험함
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, 0, 1}); // {x, y, 현재까지 이동 거리}

        int[][] minDist = new int[n][m];
        for (int[] row : minDist) Arrays.fill(row, Integer.MAX_VALUE);
        minDist[0][0] = 1;

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int x = cur[0], y = cur[1], count = cur[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && maps[nx][ny] == 1) {
                    // 더 적은 이동 횟수로 도착할 수 있는 경우에만 탐색 진행
                    if (count + 1 < minDist[nx][ny]) {
                        minDist[nx][ny] = count + 1;
                        stack.push(new int[]{nx, ny, count + 1});
                    }
                }
            }
        }
        */

        int answer = maps[n - 1][m - 1];

        return answer == 1 ? -1 : answer;
    }
}
