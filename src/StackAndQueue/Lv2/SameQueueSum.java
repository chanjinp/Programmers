package StackAndQueue.Lv2;

import java.util.*;

public class SameQueueSum {
    public static void main(String[] args) {
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};

        System.out.println(solution(queue1, queue2));
    }

    public static int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long ttQ1 = 0; // long 타입으로 변경하여 오버플로우 방지
        long ttQ2 = 0;

        for (int q : queue1) {
            q1.offer(q);
            ttQ1 += q;
        }

        for (int q : queue2) {
            q2.offer(q);
            ttQ2 += q;
        }

        long total = ttQ1 + ttQ2;

        // 총합이 홀수이면 두 큐의 합을 같게 만들 수 없음
        if (total % 2 != 0) {
            return -1;
        }

        long goalNumber = total / 2;
        int answer = 0;

        // 최대 이동 가능 횟수 제한 (N * 4)
        int maxOperations = queue1.length * 4;

        while (ttQ1 != ttQ2) {
            // 최대 횟수를 넘어가면 원상복구 혹은 순환 상태이므로 불가능 처리
            if (answer > maxOperations) {
                return -1;
            }

            if (ttQ1 > goalNumber) {
                int popNumber = q1.poll();
                q2.offer(popNumber);
                ttQ1 -= popNumber;
                ttQ2 += popNumber;
            } else {
                int popNumber = q2.poll();
                q1.offer(popNumber);
                ttQ1 += popNumber;
                ttQ2 -= popNumber;
            }

            answer++;
        }

        return answer;
    }
}

/*
길이가 같은 2개의 큐
1. 하나의 큐를 골라 원소 추출
2. 추출한 원소를 다른 큐에 넣는 작업을 통해 각 큐의 원소 합이 같도록 만들려고 한다.

추출과 삽입은 작업 1회로 간주한다. (하나에서 뽑은 값은 무조건 다른 큐로 넣는다고 생각)

두 수의 합이 같아하기 때문에, 모든 합의 절반만큼 내가 만들 수 있는지를 봐야함.

기존에는 원소 뽑았을 때를 기점으로 뽑은 값이 목표 숫자보다 클 경우에는 -1을 반환하도록 설정해줬음.
다만 해당 케이스에 예외 존재 → 모든 원소들이 목표 숫자보다 작고 총합이 짝수임에도 목표 숫자를 만들지 못하는 경우 존재할 수 있음.

그렇기 때문에, 가능한 최대로 작업을 진행했을 때 걸리는 제한점을 하나 만든다.
그 제한점의 기준은 아래와 같다.

1. Q1에 있던 모든 원소 Q2로 이동
2. Q2에 모인 원소 중 원하는 원소를 찾기 위해 다시 Q2로 이동 (Q1에서 받은 수를 제외한 나머지 모두 옮기는 경우 ~ 받은 수 포함 모든 수를 Q1에 옮기는)
3. 다시 제자리 상태로 돌아오는 과정 (N)

즉, 최대로 쳤을 때 초기 Queue의 Size * 4 (4N)이라는 결론이 나오기 때문에, 이를 한계점으로 두고 이보다 작업 횟수가 늘어나면 -1로 리턴
*/
