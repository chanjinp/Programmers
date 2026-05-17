package StackAndQueue.Lv2;

import java.util.Stack;

public class CorrectionParentheses {
    public static void main(String[] args) {
        final String FALSE_CASE = ")()(";
        final String TRUE_CASE = "(())()";


        System.out.println(solution(TRUE_CASE));
    }

    public static boolean solution(String s) {
        boolean answer = true;

        //문자로 변환
        char[] charList = s.toCharArray();

        //Stack
        Stack<Character> stack = new Stack<>();

        for(char c : charList){
            if(stack.isEmpty()){
                stack.push(c);
            } else {
                if(stack.peek() == '(' && c == ')') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        if(!stack.isEmpty()) {
            return false;
        }

        return answer;
    }
}

/*
문제 풀이
괄호가 제대로 닫혀있는가를 판단하는 문제

괄호가 제대로 닫혀있는 기준 >> 항상 '(' 와 ')'의 짝이 맞아야한다. 하나라도 안맞을 경우 False, 맞을 경우 True로 반환된다.

1. 제일 최신 이력으로 봐야하기 떄문에, Stack을 활용하는 방식으로 사용
2. Stack이 비어있으면 값을 넣어줄 것이고, 하나씩 peek을 하면서 그 다음 index의 값과 비교해서 한 쌍이 되는지 확인, 아닐 경우 Stack 안에 넣어준다.
3. 반복문의 경우에는 char[] 만큼 진행한다 (String.length만큼 반복)

다른 사람의 풀이
다른 풀이의 경우에는 Stack을 사용하지 않고, 단순히 문자열 길이만큼 반복하여, '('의 개수와 ')'의 개수의 차이가 0이 될 경우 true 아닐 경우 false로
어차피 한 쌍에 대해 매칭되어야하기 때문에 개수 차이로 실행하는 방식으로 작성했다.
다만 여기서 주의할 점은 만약 )))((( 이런 식으로 올 경우 false가 떠야하기 때문에 )의 경우 count를 마이너스 시키는데, 만약 마이너스가 0보다 작을 경우 바로 false로 리턴하게 되어있어,
이런 방식을 해결했다.

내 생각
LIFO의 특징을 통해 최신 이력을 살펴보면서 해결해야겠다는 생각이 든 건 잘했으나, Stack이라는 객체를 호출하지 않고 목적에 맞는 일을 완수한다는 것이 엄청난 효율을 가져올 것이라고는 생각한다.
다만 실무에서는 효율성 있는 코드를 항상 작성할 때, 생각의 시간이 많이 들고, 단순 코드일 수 있으나 코드가 길어져서 중간 중간 기능성 함수 분리를 통한 함수들을 추적해서 코드를 보다보면
너무 길어지는 경우가 발생한다.

요즘들어, 항상 생각하지만 규모가 작고 어느 정도 데이터 처리 양에 대해서 생각할 경우 효율성을 챙기는 코드는 당연하되, 규모가 크고 심지어 나 외에도 다른 사람들이 코드를 같이 확인하고
쉽게 풀이하려면, 대표적인 자료구조나 함수형 인터페이스 등을 활용하는 편이 유지보수 및 가독성을 챙기며, 구현력을 챙기는 코드가 되는 거 같다.
 */
