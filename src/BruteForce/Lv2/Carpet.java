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

        for(int h = 3; h <= size / h; h++) {
            int height = h;

            if (size % height == 0) {
                int width = size / height;

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

조금 더 최적화할 수 있는 방법 추가.

넓이는 가로 * 세로 => Area = w * h

단 조건이 w >= h이기 때문에 w = Area / h >= h와 동일하며, Area >= h^2  → sqrt(Area) >= h 이런 공식이 나온다.

이런 공식을 이용했을 때 세로든 가로든 어차피 마지막은 가로가 무조건 길어야하는 조건이며, 넓이에 대해 위와 같이 나오기 때문에
for문의 최대 조건을 좀 수정할 수 있다.

좀 더 수식화 + 효율적인 코드가 완성된다.
*/