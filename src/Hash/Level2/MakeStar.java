package Hash.Level2;

import java.util.ArrayList;
import java.util.Arrays;

public class MakeStar {
    public static void main(String[] args) {
        int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};

        for(String s : solution(line)) {
            System.out.println(s);
        }
    }

    public static String[] solution(int[][] line) {
        ArrayList<Coordinate> coordinate = new ArrayList<>();

        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        for(int i = 0; i < line.length - 1; i++) {
            long A = line[i][0];
            long B = line[i][1];
            long C = line[i][2];
            for(int j = i+1; j < line.length; j++) {
                long a = line[j][0];
                long b = line[j][1];
                long c = line[j][2];

                long denominator = (long)A*b - (long)B*a;

                if(denominator == 0) {
                    continue;
                }

                long numX = (long)B*c - (long)C*b;
                long numY = (long)A*c - (long)C*a;

                if(numX % denominator == 0 && numY % denominator == 0) {
                    long targetX = numX / denominator;
                    long targetY = numY / denominator;

                    coordinate.add(new Coordinate(targetX, targetY));
                    minX = Math.min(minX, targetX);
                    maxX = Math.max(maxX, targetX);
                    minY = Math.min(minY, targetY);
                    maxY = Math.max(maxY, targetY);
                }
            }
        }

        int width = (int)(maxX - minX) + 1;
        int height = (int)(maxY - minY) + 1;

        char[][] board = new char[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(board[i], '.'); // 우선 전부 평지로 채우기
        }

        // 2. 저장해 둔 교점들을 하나씩 꺼내며 도화지에 별(*) 찍기
        // (교점들을 고유한 객체나 리스트에 담아두었다고 가정)
        for (Coordinate c : coordinate) {
            // 공식을 대입해 가상 좌표를 배열 인덱스로 변환
            int row = (int) (maxY - c.y);
            int col = (int) (c.x - minX);

            board[row][col] = '*'; // 교점에 별 박기
        }

        // 3. char[][] 도화지를 String[] 배열로 변환하기
        String[] answer = new String[height];
        int idx = 0;
        for (int i = height - 1; i >= 0; i--) {
            // char 배열 한 줄을 통째로 문자열로 변환하여 저장
            answer[idx++] = new String(board[i]);
        }
        return answer;
    }

    public static class Coordinate {
        public long x;
        public long y;

        public Coordinate(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
/*
Ax + By + C = 0으로 표현 가능한 n개의 직선이 주어질 때, 이 직선의 교점 중 정수 좌표에 별을 그리려고 한다.

A, B, C가 각 주어질 때 그려지는 직선에서 교차점의 좌표를 구하고, 해당 좌표에 '*'을 찍어서 return해라. (정수 좌표)

문제 제일 하단에, 두 점의 교차점을 구하는 공식이 있음 참고!!

1. 문제의 핵심은 교점을 구할 때 분모가 0이 되는 점을 막을 수 있는가? → 예외 처리
2. 좌표의 격자 크기를 제대로 체크할 수 있는가?
3. 좌표 관리를 어떻게 할 수 있는가?

이렇게 주요 특징을 초점으로 체크하는 것으로 보인다.

해당 내용은 단순 문자열을 찍어낼 수 있는가를 넘어서, 예외 처리 케이스와 이차원 배열을 어떻게 구성할 것인가 등의 구현력을 변별하는 문제로 보인다.

추후 복습 필요
*/