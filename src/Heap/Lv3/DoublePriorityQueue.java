package Heap.Lv3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DoublePriorityQueue {
    public static void main(String[] args) {
        String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};

        for(int i : solution(operations)) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        Map<Integer, Integer> map = new HashMap<>();
        int size = 0; //유효한 데이터를 관리하는 size 변수

        for(String o : operations) {
            String[] data = o.split(" ");

            String cmd = data[0];
            int num = Integer.parseInt(data[1]);

            //cmd: I → 우선순위 큐에 넣어라
            if(cmd.equals("I")) {
                minHeap.offer(num);
                maxHeap.offer(num);

                map.put(num, map.getOrDefault(num,0) + 1); //개수 따지기
                size++;
            }
            //cmd: D → 1일 경우 최댓값 삭제 -1일 경우 최솟값 삭제
            if(cmd.equals("D") && size > 0) {
                int key = 0;
                if(num > 0) { //최댓값 삭제
                    while(!maxHeap.isEmpty()) { // TODO 해당 부분 함수 분리 가능
                        key = maxHeap.poll();

                        if(map.containsKey(key) && map.get(key) > 0) { //삭제 값이 유효한 값을 삭제한 경우
                            map.put(key, map.get(key) - 1);
                            break;
                        }
                    }
                } else { //최솟값 삭제
                    while(!minHeap.isEmpty()) { // TODO 해당 부분 함수 분리 가능
                        key = minHeap.poll();

                        if(map.containsKey(key) && map.get(key) > 0) { //삭제 값이 유효한 값을 삭제한 경우
                            map.put(key, map.get(key) - 1);
                            break;
                        }
                    }
                }

                size--;
            }
        }

        if (size <= 0) {
            return new int[]{0, 0};
        } else {
            // 유효한 최댓값 찾기 (maxHeap에서)
            while (!maxHeap.isEmpty()) {
                int val = maxHeap.peek();
                if (map.get(val) > 0) {
                    answer[0] = val;
                    break;
                }
                maxHeap.poll(); // 유효하지 않으면 버림
            }

            // 유효한 최솟값 찾기 (minHeap에서)
            while (!minHeap.isEmpty()) {
                int val = minHeap.peek();
                if (map.get(val) > 0) {
                    answer[1] = val;
                    break;
                }
                minHeap.poll(); // 유효하지 않으면 버림
            }
        }

        return answer;
    }
}
/*
문제 풀이

[I 숫자] 일 경우 큐에 주어진 숫자 삽입
[D 1] 일 경우 큐에서 최댓값 삭제
[D -1] 일 경우 큐에서 최솟값 삭제

1 <= operations.length <= 1,000,000

** [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해라. **

어떤 기준을 잡기에는 처음 값에 따라서, 혹은 min or max Heap 한 쪽으로 치우칠 수 있다.
즉, 비어있을 경우에는 두 큐에 다 넣고 min값과 max 값을 서로 꺼내서 어떤 큐에 넣을지 확인한다.
그리고 poll해서 삭제했을 경우에 삭제 이력을 관리하는 자료구조를 사용해서 이미 삭제했었는지 확인해야한다.

그렇다면 Map 자료구조에 어떤 값이 몇 개 들어갔는지를 Queue에 넣어줄 때마다 확인해야한다.

해당 문제 로직 시퀀스
 1. 큐의 맨 앞(peek)을 본다.
 2. Map에 적힌 개수가 0이라면? → "어? 이건 이미 다른 힙에서 삭제된(죽은) 데이터네!"
 3. 그럼 poll() 해서 큐에서 아예 빼버린다.
 4. 다시 1번으로 돌아가서 그다음 값을 확인한다.
 5. Map에 적힌 개수가 0보다 크다면? → "아, 이건 진짜 살아있는 데이터구나!"
 6. 여기서 비로소 진짜 최솟값(혹은 최댓값)을 찾은 것이다.

TODO 해당 문제의 경우 시행착오 과정에서 고민하다가 풀이를 본 케이스이므로 시간이 지난 후 해당 케이스 반복 풀이 필요!!
TODO 해당 문제 로직의 경우 TreeMap을 활용하여 풀 수 있다고 한다. 효율성을 챙기고자 하려면 TreeMap으로도 가능하기에 꼭 도전해볼 것.
*/