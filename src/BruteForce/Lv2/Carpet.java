package BruteForce.Lv2;

public class Carpet {
    public static void main(String[] args) {
        int brown = 10;
        int yellow = 2;

        for(int a : solution(brown, yellow)) {
            System.out.print(a + " ");
        }

    }
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int size = brown + yellow;

        for(int i = 3; i < brown - 3; i++) {
            int width = i;

            if (size % width == 0) {
                int height = size / width;

                int yellowSize = (width - 2) * (height - 2);

                if (yellowSize == yellow) {
                    answer[0] = Math.max(height, width); //무조건 가로가 세로보다는 크다.
                    answer[1] = Math.min(height, width); //반대로 세로는 가로보다는 작다.
                    break;
                }
            }
        }

        return answer;
    }
}




/*
주변 전부 브라운, 중간 노랑이 오도록 각 색깔 개수만 보고 크기가 어떤지 파악해라

직사각형이기 때문에, 무조건 넓이는 가로 x 세로이다.

즉, brown x yellow는 직사각형의 넒이이다.
가로는 세로의 길이보다 같거나 세로의 길이보다 길다!

단 최소 width, height는 3 이상이어야 한다 >> 왜냐하면 yello가 항상 중앙에 와야하기 때문

brown을 3개 깔고 시작했을 때, 하나씩 깔면서 세로의 길이를 구하고
brown이 테두리를 담당하기 떄문에 길이의 경우 brown의 개수만 생각하면된다.

즉, 가로의 길이를 3으로 두고 시작하면 height의 길이는 brown의 개수가 되며,
중간에 yellow가 올려면 width - 2 * height - 2의 값이 yello의 개수와 같아져야 한다
*/