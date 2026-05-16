package StackAndQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FunctionDevelopment {
    public static void main(String[] args) {
        int[] progress = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};

        int[] progress2 = {93, 30, 55};
        int[] speeds2 = {1, 30, 5};

        System.out.println(Arrays.toString(solution(progress, speeds)));

    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        ArrayList<Integer> list = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int days = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);

            queue.offer(days);
        }

        while (!queue.isEmpty()) {
            int count = 1;
            int day = queue.poll();

            while(!queue.isEmpty() && day >= queue.peek()) {
                queue.poll();
                count++;
            }

            list.add(count);
        }
        answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
/*
문제 풀이
- 각 기능은 진도가 100%일 때 서비스에 반영 가능
- 배포의 경우에는 순차적으로 배포해야함
>> 예를 들어 1번 째 진도율이 100이 아니고 2번 째가 100이라면, 2번째를 배포하는 것이 아닌 1번 째가 끝난 뒤 2번 째를 배포하는 방식

Input 1. 먼저 배포되어야하는 순서대로 작업의 진도 정수 배열 (progresses: length는 100 이하)
Input 2. 각 개발 속도를 나타내는 배열 speeds

각 배포마다 몇 개의 기능이 배포되는지 return

주의점: 100이 먼저 되었더라도 앞에 안끝나면 배포 못함

100이 된 것들을 관리해주는 게 중요 포인트
1. 각 100이되는데 필요한 일자 수들을 전부 구함
2. 일자 수들을 전부 Queue에 넣는다.
3. Queue가 빌 떄까지 반복하며, 반복문 시작 시 하나를 뽑으며 시작하고, 비어있지 않는 선에서 peek을 통해 다음 값을 미리 보고 조건(뽑은 일 수 >= 미리 본 일수)일 경우 Count 증가
4. 반복문 완료 시 list에 값을 추가한다.

핵심 Why?
Queue를 사용해야하는 이유??
Queue는 FIFO(First In First Out)이라는 처음 들어온 값이 처음으로 나가는 (웨이팅) 개념의 대표적 자료구조이다.
문제에서는 아무리 다른 진도율이 100으로 빠르게 되더라도 순차대로 배포가 되어야만 하는 특성이 있기 때문에, Queue를 사용해서 처음 값을 하나씩 순차대로 뽑으며, 걸린 일자들 값을 비교해가며 카운트 한다.

사소한 Tip
여기서 Math 라이브러리의 올림 함수(ceil)을 활용하여 걸린 일자들을 구하는데, 왜 올림을 했는가?
ex) 진도율 30에 진행 속도 30이라고 하면 100 이상이 되는 값을 만들려면 최소 3일을 지나야한다. 그러나 올림을 하지 않는다면 100 - 30 / 30 의 경우 2.3xxx로 나오게 되는데 Int 타입 변환 시 내림 처리가 되어
2라는 결과가 나와서 틀릴 수 있다.
*/