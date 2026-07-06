package StackAndQueue.Lv2;

import java.util.*;

public class BiggerThanMe {
    public static void main(String[] args) {

    }

    public static int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];

        Stack<Integer> stack = new Stack<>(); //뒷 큰수를 찾지 못한 index들

        for (int i = 0; i < numbers.length; i++) {
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) { //비어있지 않은 상태에서 최신에 본 값보다 현재 보는 값이 더 크다면?
                int idx = stack.pop();
                answer[idx] = numbers[i];
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = -1;
        }
        return answer;
    }

}
/*
자신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이 있는 수를 뒷 큰수라고 한다.
길이가 매우 길기 때문에, 이중 For문으로 문제를 작성하면, 시간 초과 일어난다.

뒷 큰수의 문제 정의를 보면, 자신보다 큰 값 중 가장 가까운 값을 뜻한다.
그렇기 때문에 자신보다 큰 값들 중에 idx가 가장 가까우면 된다.

이미 지나간 결과를 저장하는 방식 >> Stack을 활용해서 풀어보자.

최신 이력을 기준으로 Stack을 털어내는 방식으로 처리한다. 만약 남아있다면 더 큰수가 없으므로, 해당 인덱스에는 -1 값을 넣어준다.
*/