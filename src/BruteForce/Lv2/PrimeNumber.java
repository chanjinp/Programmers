package BruteForce.Lv2;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class PrimeNumber {
    public static void main(String[] args) {
//        String numbers = "17";
        String numbers = "011";

        System.out.println(solution(numbers));
    }

    public static int solution(String numbers) {
        int answer = 0;
        Set<Integer> duplicates = new HashSet<>();

        //각 숫자 뽑아내기
        char[] chars = numbers.toCharArray();
        boolean[] visited = new boolean[chars.length];

        //DFS를 통해 숫자 조합을 하기 위한 Stack
        Stack<Node> stack = new Stack<>();

        stack.push(new Node("", visited));

        while(!stack.isEmpty()){
            Node node = stack.pop();

            if(!node.current.isEmpty()) { //현재 문자열이 완성되어있다면,
                int number = Integer.parseInt(node.current);
                boolean isPrime = true; //기본적으로 소수라고 생각했을 때

                if(number < 2) {
                    //number가 1일 경우에는 소수가 아님
                    isPrime = false;
                } else {
                    for(int i=2; i<= (int) Math.sqrt(number); i++) { //4일 경우에 sqrt 값이 2이므로 소수 판별 불가
                        if(number % i == 0) {
                            isPrime = false;
                            break;
                        }
                    }
                }
                if (isPrime && !duplicates.contains(number)) { //이미 처리된 이력이 없다면?
                    answer++;
                    duplicates.add(number);
                }
            }

            if(node.current.length() < chars.length) {
                for (int i = 0; i < chars.length; i++) {
                    if(!node.visited[i]) { //주의할 점은 항상 Node의 방문 이력을 계속 공유해서 가져가야한다.
                        boolean[] nextVisited = node.visited.clone();
                        nextVisited[i] = true;

                        stack.push(new Node(node.current + chars[i], nextVisited));
                    }
                }
            }
        }

        return answer;
    }

    static class Node {
        protected String current; //현재 만들어진 문자열
        protected boolean[] visited;

        public Node(String current, boolean[] visited) {
            this.current = current;
            this.visited = visited;
        }
    }
}
/*
숫자가 적힌 종이 조각이 흩어져 있다. 흩어진 종이 조각을 붙여 소수를 몇 개까지 만들 수 있는지 알아보자.

소수란? 1 또는 자기 자신으로만 나눌 수 있는 수 즉, 약수가 없는 수를 뜻한다.
단, 1은 제외

numbers의 length만큼의 자릿수까지 만들 수 있다.
다만, 0 1 1의 경우 11로 되는 것처럼 11과 0 1 1은 같은 것이기 떄문에, 자릿수가 늘어난다고 한들 이미 체크한 값인지 확인해야한다. (자료구조 필요 → 중복 체크이기 때문에 SET을 사용)

숫자를 만들고 소수 판별해서 소수일 경우 Set에 중복으로 처리한 이력이 있는지 판단 후 없으면 answer++ 후 Set에 값을 넣고 이력이 있는 경우 패스

숫자를 어떻게 만드는지가 관건 for문에서 i는 자릿수를 의미 → char[]에서 i개 만큼 뽑아서 숫자 조합

숫자의 경우 DFS 알고리즘을 통해 구현 (Stack 활용)

TODO 재귀 방식으로 재구현 필요

재귀 방식 vs 스택 방식
실무에서는 솔직히 재귀 방식이 깔끔하다고 생각한다. 왜냐하면 객체 지향적으로 함수 분리를 통해 코드가 더 깔끔해지고, 반복적인 함수와 변동이 있는 파라미터 값 활용으로 더 코드가 짧아져서
재귀를 아는 개발자라면, 누구나 쉽게 코드를 읽을 수 있다는 장접을 가진다. 다만, 반복적으로 사용되면서 스택 메모리가 어느정도로 감당이 되는지 등에 대해서는 항상 고민이 필요해보인다.

스택 메모리의 경우에는 재귀보다 직관적이라고 생각한다. Stack의 LIFO 특징을 사용해 최신 이력을 보며 숫자를 조합해가는 과정을 좀 더 생각하기 쉬운 거 같다.

다만, 여기서 제일 주요 포인트는 visit 배열의 공유가 가장 중요한 거 같다. 재귀의 경우에는 visit 배열이 파라미터로 넘어가면서 내용 공유가 되지만,
Stack의 경우에는 다소 꼬일 수 있다고 생각이 들었다. 개발자의 실수나,, 스레드 환경 등등에 대해서? → Stack의 경우 Heap 메모리 영역에 들어가기 떄문에, 스레드 환경에서 문제 발생할 가능성 있음

관련해서 알아보다가 알게된 정보
꼬리 재귀 최적화 (Tail Call Optimization) - Scala, Kotlin과 같은 언어의 경우 재귀를 사용해도 시스템 스택을 사용하지 않고 루프처럼 동작 한다고 한다.
언어마다 각기 다른 특징이 돋보인다. 추후 언어를 확장했을 때 해당 언어의 경우 메모리 점유 관련해서 지식을 알아두면 더 깔끔하고, 효율적으로 작성 가능하다고 생각이 든다.
*/