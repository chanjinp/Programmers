package StackAndQueue;

import java.util.Arrays;
import java.util.Stack;

public class IdontWantSameNumber {
    public static void main(String[] args) {
        int [] input = {1,1,3,3,0,1,1};

        System.out.println(Arrays.toString(solution(input)));
    }


    public static int[] solution(int[] arr) {
        int[] answer = {};

        Stack<Integer> stack = new Stack<>();

        for(int a : arr) {
            if(stack.isEmpty()) {
                stack.push(a);
            } else {
                int s = stack.peek();
                if(s != a) {
                    stack.push(a);
                }
            }
        }
        answer = stack.stream().mapToInt(Integer::valueOf).toArray();
        return answer;
    }

}
/*
문제 풀이
연속적으로 나오는 숫자만 남기고 나머지를 전부 제거하려고 한다. 어떻게 하면 좋을까?

Stack을 저장소로 사용

1. 비어있다면, 값을 하나 넣어준다.
2. stack에서 값을 하나 본다. (최신 보고 있는 기록)
3. stack에 꺼낸 값과 같으면 넘어가고 아니면 넣는다.


다른 사람들의 풀이
ArrayList를 사용해서 비교하는 방식으로 넣어주고 반환하는 방식이다.

내 생각
카테고리가 결정되어 푸는 방식이기 때문에, Stack과 Queue를 썼지만 문제를 보자마자 생각한 건 Input의 중복 제거? Set을 사용하면 되겠구나 먼저 생각났다.
그리고 stream 함수를 사용하며, mapToInt(Integer::valueOf) 등과 같이 이런 Return 해주는 방법에 대해서도 효율성에 대해 생각해보면,
굳이 래퍼 타입을 Mapping해서 하는 것보다 Stack의 크기만큼 answer 배열 크기를 설정하고 N만큼 반복해서 값을 그저 꺼내서 넣어주는 방식이 더 효율 좋을 거 같다고 생각한다.
다만 for문을 추가로 쓰고 size 조절까지하는 등 코드가 길어지긴 하겠지만, 항상 상황에 따른 적절한 코드 선택하는 방안이 중요한 거 같다.
 */