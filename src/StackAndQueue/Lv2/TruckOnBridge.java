package StackAndQueue.Lv2;

import java.util.LinkedList;
import java.util.Queue;

public class TruckOnBridge {
    public static void main(String[] args) {
        /*TEST CASE A*/
        final int bridge_length = 2;
        final int wight = 10;
        final int[] truck = {7,4,5,6};

        /*TEST CASE B*/
//        final int bridge_length = 100;
//        final int wight = 100;
//        final int[] truck = {10,10,10,10,10,10,10,10,10,10};

        System.out.println(solution(bridge_length, wight, truck));

    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        int currentWeight = 0;
        Queue<Integer> bridge = new LinkedList<>();
        Queue<Integer> waitTruck = new LinkedList<>();

        for(int truck : truck_weights) {
            waitTruck.offer(truck);
        }

        // 다리를 0으로 초기화 (다리 길이만큼의 공간 확보)
        for(int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        while(!bridge.isEmpty()) {
            time++;

            // 1. 1초가 지날 때마다 다리 맨 앞의 트럭(또는 0)이 빠져나옴
            currentWeight -= bridge.poll();

            if(!waitTruck.isEmpty()) {
                // 2. 대기 중인 트럭이 다리에 올라갈 수 있는지 하중 확인
                if(currentWeight + waitTruck.peek() <= weight) {
                    int nextTruck = waitTruck.poll();
                    bridge.offer(nextTruck);
                    currentWeight += nextTruck;
                } else {
                    // 3. 못 올라간다면 빈 공간(0)을 넣어 다리 위 차량들의 이동을 유지
                    bridge.offer(0);
                }
            }
        }

        return time;
    }
}

/*
문제 풀이
모든 트럭이 다리를 건널 때 얼마나 걸리는지?
다리 최대 트럭 개수: bridge_length
다리 최대 무게 개수: weight

Logic
대기 트럭을 차례로 Queue에 넣고 하나씩 뽑는다.
뽑은 트럭과 다음 차례의 트럭의 합이 weight보다 작으면서, length보다는 크지 않을 때 하나 더 뽑는다.
이를 반복해서 최대한으로 뽑는다

여기서 주의할 점은 다리를 건너기 완료했을 때와 다리를 건너고 있을 때의 시간을 잘 확인해야한다.

다리를 건너는 시간은 length만큼 걸린다.
다리를 건너기 위해 Queue에 올라온 시점부터 1초 그 이후 건너기 위해서는 length만큼 건너야한다.
다리를 건너기 위해 Queue에 올라온 트럭이 다중일 경우
ex) A B C가 올라온 경우이며, length는 10이라고 가정했을 때 A는 8초가 걸리고 B는 9초 C는 10초가 걸린다. 그럼 전부 건너야 하는 시간은 어쨋든 length이다.

위의 방식과 생각은 틀렸다.
해당 문제의 중점은 bridge_length를 어떻게 관리할 것인가가 키 포인트였던 거 같다.
다리의 길이만큼 가는 건데, 초마다 시뮬레이션을 진행하지 않고, 효율적으로 생각하려고 하다보니 결과로만 따질 경우, 예시에서 보여진 동시에 다리가 올라가는 경우에 대하여
각기 다르게 적용되는 초를 length만큼 퉁쳐버리니까 오차 계산이 일어나서 시간이 추가로 더해지게 된다.

스스로의 피드백
다리를 건너는 시간을 계산 결과를 통해 한 번에 반영하는 아이디어는 좋았으나, 아이디어 검증 단계에서 좀 부족함을 느꼈다.
length는 10000 이하의 값이라는 조건을 확인하고, 해당 길이의 경우에는 시뮬레이션을 진행해도 시간 상 문제 없는지 잘 판단해야할 것이라고 생각이 든다.
*/