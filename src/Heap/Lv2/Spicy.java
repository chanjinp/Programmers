package Heap.Lv2;

import java.util.PriorityQueue;

public class Spicy {
    public static void main(String[] args) {
        int[] scovile = {1, 2, 3, 9, 10, 12};
        int k = 7;

        System.out.println(solution(scovile, k));
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        //Min Heap에 전부 값을 넣어준다.
        for(int s : scoville){
            minHeap.offer(s);
        }
        //TODO 수정 전
        while(minHeap.peek() < K) { //2개 이상일 경우 섞을 수 있으니까.
            int first = minHeap.poll();

            if(first >= K) { //최소의 값을 뽑았는데, 스코빌 지수 기준 치보다 이상이라면, 반복문 탈출
                break;
            }

            int second =  minHeap.poll();

            int newScovile = first + (second * 2);

            minHeap.offer(newScovile);
            answer++;
        }

        return minHeap.peek() < K ? -1 : answer;
    }
}
/*
문제 풀이
모든 음식의 스코빌 지수를 K 이상으로 만들고 싶어한다.
K 이상으로 만드는 법은 스코빌 지수가 가장 낮은 두 개의 음식을 섞어서 새로운 음식을 만든다.

섞은 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)

모든 으식의 스코빌 지수가 K 이상이 될 때까지 섞는다.

최소 몇 번을 섞어야 하는가? (결과)

제한 사항
scoville의 길이는 2 이상 1,000,000 이하입니다.
K는 0 이상 1,000,000,000 이하입니다.
scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.

가장 작은 값을 섞은 공식과 같이 섞은 다음 Heap에 넣어서 다시 정렬시킨다.

작은 노드가 부모 노드가 되어야하기 떄문에, Min Heap으로 적용을 진행한다. 모든 것들을 다 섞었는데 스코빌 지수가 낮다면? -1로 리턴한다.


내 생각
Min Heap과 Max Heap에 대한.. 즉, Heap에 대한 특징만 알고 있다면, 크게 푸는데 문제는 없었다. 다만, While문의 조건이 깔끔하지 않았다.
Size를 비교가 아닌 어차피 스코빌 배열을 전부 값을 복사했기에, Queue는 항상 존재하기 떄문에 minHeap.peek() < K로 두는게 더 깔끔했을 거 같다.
그러면 While문 안에 if 조건이 빠져도 된다. 더 깔끔한 코드 완성
마지막 모든 것들을 섞었다고 가정했을 때도 같은 조건으로 구분하면 더 깔끔하다.

실무에서는 조금이라도 깔끔한 코드들이 더 좋다. 물론 상황에 따른 성능 분석도 필요하지만, 요즘 고도화 작업(유지보수)를 진행하면서, 깔끔한 코드들이 얼마나 추후 도움이 되는지를 뼈져리게 느끼고 있다.
쓸데 없는 For문과 의미가 있더라도 줄일 수 있는 조건절의 경우 아무런 의미를 모르는 사람이 코드를 봤을 때 그저 왜?라는 의문점을 한 번 더 들게 만들기 때문이다.
*/