package StackAndQueue.Lv2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class StockPrice {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};

        Arrays.stream(solution(prices)).boxed().forEach(System.out::println);
    }
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Queue<Integer> queue = new LinkedList<>();

        //Queue를 세팅
        for(int p : prices){
            queue.offer(p); //보다 안전하게 넣을 수 있는 offer 활용
        }

        int idx = 0;

        while(!queue.isEmpty()) {
            int time = 0;
            int price = queue.poll();

            for(int q : queue) {
                if(price <= q) { //주식 가격이 유지 혹은 그 이상일 경우
                    time++;
                } else {
                    time++; //해당 케이스에서 3 -> 2로 떨어지는 시점의 경우 떨어지는데 1초 걸리니까 기본적으로 1은 더한 값에서 리턴 진행
                    break;
                }
            }

            answer[idx] = time; //값을 넣어준다.
            idx++;
        }

        return answer;
    }
}

/*
문제 풀이
각 index가 n초의 시점에 대한 내용

1초를 기준으로 떨어짐을 판단하는 경우는 선택 되었을 때 (뽑은 값이) 다음 값보다 크거나 같다고 한다면 떨어지지 않음을 의미

prices에 모든 값을 Queue에 넣은 뒤,
index의 경우 0부터 시작

Queue가 빌 때까지 진행을 할 것이며, Queue 내부에서도 남은 Queue만큼 값을 반복하며, 얼마나 가격이 떨어지지 않은 기간은 몇 초인지 확인

단, 여기서 주의할 점은 가격이 떨어지지 않은 기간의 경우에 대해 따지기 때문에, 가격이 떨어졌다가 다시 올랐다고 해서 시간을 더해주거나 그런 것이 아니라서 >> 무조건 하나씩 비교하다가 자신보다 낮을 경우 그때의 시점을 기준으로 값을 저장해야한다.

다른 풀이
다른 풀이의 경우에는 2중 For문을 사용하여, 동일한 로직을 기반으로 진행을 했고 따로 Queue, Stack의 자료 구조를 쓰지 않았다.
나의 풀이 경우에도 사실 Queue가 카테고리에 잡혀 있기 때문에, 사용한 것이지 그것이 아니라면, 2중 For문이랑 다를 바가 없다고 생각이 든다.

내 생각.
이 코드를 최적화하려면 어떤 방법이 있을까? Stack을 활용하는 방식은 괜찮을까?
Stack의 경우에는 큰 특징으로 최신 데이터를 보다 쉽게 가져올 수 있다. 제일 최신의 데이터를 확인할 수 있다는 점.
여기서 Stack은 prices를 넣는 것이 아니라 index(위치)의 값을 넣는다. 이유는 return은 결국 시점이니까.
떨어지는 시점의 index의 값과 Stack에 쌓아온 이력 (떨어지지 않은 시점)들에서 하나 뽑아서
Stack에서 뽑은 index의 결과는 떨어지는 시점의 index - stack에서 뽑은 index 값이 해당 부분의 주식 가격이 보다 떨어지는 시점에 대한 결과가 나온다.
단, 모든 경우에도 떨어지지 않는다면, Stack에 남아있기 때문에, Stack에서 나오는 (총 prices 길이 - stack에서 나오는 index 값) - 1을 진행하면 된다.

그러면 2중 For문으로 해결했던 방식을 Input으로 오는 prices 배열의 길이만큼 반복해서 Stack을 활용하고, Stack의 Pop하는 조건 또한 N의 영향을 받기 떄문에, O(N)만큼으로 시간 복잡도를 줄일 수 있다.
*/
