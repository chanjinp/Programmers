package BruteForce.Lv1;

public class CardVault {
    public static void main(String[] args) {
        int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};

        System.out.println(solution(sizes));
    }
    public static int solution(int[][] sizes) {
        int answer = 0;

        for(int[] size : sizes) {

            int temp = 0;

            if(size[0] < size[1]) { //바꾸어 주는 작업
                temp = size[0];
                size[0] = size[1];
                size[1] = temp;
            }
        }

        int height = 0;
        int width = 0;


        for(int[] size : sizes) {
            height = Math.max(height, size[0]);
            width = Math.max(width, size[1]);
        }

        answer = height * width;

        return answer;
    }
}

/*
문제 풀이
명함 지갑 만들기 >> 모든 명함을 담을 수 이쓴 지갑을 만들어라. 다만 그중 가장 작은 지갑일 경우?
단 명함의 경우 가로 세로를 바꾸어서 넣을 수도 있다.

(세로든 가로든 하나를 기준으로 잡아서 사용한다) > 코드에서는 세로를 기준으로 잡아서 사용 = 가로가 더 길 경우 세로로 바꾸어서 사용

1. 가로 길이가 세로 길이보다 길다면 그대로 놔둔다.
2. 가로 길이가 세로 길이보다 짧다면, 세로 길이를 가로 길이로 (눕혔다고 판단) 바꾼다.
3. 이 후 가로에서 제일 큰 값, 세로에서 제일 큰 값을 사용한다.

완전 탐색 문제를 아침 머리풀 겸 진행, 오늘 하루는 SQL 공부를 위해, 간단히 진행
*/
