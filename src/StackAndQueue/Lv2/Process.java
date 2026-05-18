package StackAndQueue.Lv2;

import java.util.LinkedList;
import java.util.Queue;

public class Process {
    public static void main(String[] args) {
        final int[] CASE_A = {2, 1, 3, 2};
        final int CASE_A_LOC = 2;

        final int[] CASE_B = {1, 1, 9, 1, 1, 1};
        final int CASE_B_LOC = 0;

        System.out.println(solution(CASE_B, CASE_B_LOC));
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();
        for(int p : priorities){
            queue.offer(p);
        }

        while(!queue.isEmpty()) {
            int currentPriority = queue.poll();
            boolean hasHighPriority = false;

            // 변경 포인트 1: 원본 배열이 아니라 '현재 큐에 남아있는 녀석들'과 비교합니다.
            for(int p : queue) {
                if(p > currentPriority) {
                    hasHighPriority = true;
                    break;
                }
            }

            if(hasHighPriority) {
                // [케이스 A] 나보다 큰 게 있어서 뒤로 다시 들어가는 경우
                queue.offer(currentPriority);

                // 내 타겟이 맨 앞(0)이었다면 큐의 맨 뒤 인덱스로 이동
                if(location == 0) {
                    location = queue.size() - 1;
                } else {
                    location--; // 그 외에는 줄이 당겨지므로 -1
                }
            } else {
                // [케이스 B] 내가 제일 커서 실제로 '실행(인쇄)'되는 경우
                answer++; // 인쇄 횟수 증가

                // ★ 탈출 조건 ★: 인쇄된 게 마침 내 타겟(location == 0)이었다면 종료!
                if(location == 0) {
                    break;
                }

                // 내 타겟이 아니었다면, 줄이 한 칸 당겨졌으므로 location 감소
                location--;
            }
        }

        return answer;
    }
}

/*
문제 풀이
- 대기 큐에서 대기 중인 프로세스 하나를 꺼낸다.
- 대기 중인 프로세스 중 우선순위가 더 높은 프로세스가 있다면 방금 꺼낸 프로세스를 다시 큐에 넣는다.
- 만약 그런 프로세스가 없을 경우 방금 꺼낸 프로세스를 실행한다.
- 한 번 실행한 프로세스는 다시 큐에 넣지 않고 그대로 종료한다.

ex) A B C D 우선순위는 2 1 3 2
1. A를 뽑음 >> 우선순위 높은 C가 있음 >> Queue에 넣음
2. B를 뽑음 >> 우선순위 높은 B,C,D가 있음 >> Queue에 넣음
3. C를 뽑음 >> 보다 큰 우선 순위 없음 >> 실행
4. D를 뽑음 >> 보다 큰 우선 순위 없음 (B랑 같음) >> 실행
5. A를 뽑음 >> 보다 큰 우선 순위 없음 >> 실행
5. B를 뽑음 >> 보다 큰 우선 순위 없음 >> 실행
결과 >> C D A B이며 location의 경우 값 2로 C의 실행 순서는 1번이다.

로직은 단순히 위와 같다 하지만 location을 어떻게 활용할 것인가?
location을 포인터처럼 활용하는 방식은 어떨까? 큐의 움직임에 따라 poll할 때마다 --를 진행하고 0인 경우에는 poll 했을 때 실행 여부에 따라서 실행이 안되면 queue.size - 1로 세팅해주는 방식

TODO 오늘은 해답을 보고 풀이를 진행 >> 주말 간 해답이 없는 상태에서 프로그래머스만을 통해 문제 복습 필요

Queue를 사용하면서 들었던 생각
add와 offer의 차이는 뭘까? 직관적으로 add라는게 List에 추가하는 느낌인데, 기능상 add와 offer는 같다. 어떤 차이점과 효율성은 뭐가 더 좋을까?

add의 경우에는 Queue가 정해진 용량에서 가득 찼을 때 IllegalStateException을 실행하며, 예외 Throw와 함께 프로그램을 종료
offer의 경우에는 동일 상황에서 false(boolean)을 리턴하며, 프로그램 종료 대신 값을 준다.

실무에서의 관점
실무에서는 운영 환경 속에서 예외 처리를 했을 때, 어떻게 관리할 것인가도 매우 중요하다. 실제 API의 예외 처리의 경우 HTTP 에러 코드에 따라 유저에게 보여줘야하는 정보가 다르고,
처리가 다르다. 심지어 에러 코드를 뱉고 서버가 멈춘다면 운영 서버가 멈춰버리는 경우도 발생하기 때문에 매우 위험하다.

offer의 경우 이러한 점들을 대응하기 위해 동일 기능이지만, 값을 리턴하는 방식으로 개발자 역량에 따른 Exception 관리하는 방식으로 보인다. 이럴 경우, 개발자는 인지하기 쉽고
대처하기 쉽다.

!! 서버가 멈추는 경우에 대해서는 매우 위험하며, 차라리 기능 하나가 먹통인게 낫다. !!
 */