package BruteForce.Lv2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DividePowerGrid {
    public static void main(String[] args) {
        //CASE A
//        int n = 9;
//        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};

        //CASE C
        int n = 7;
        int[][] wires = {{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}};

        System.out.println(solution(n, wires));
    }

    public static int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        //인접 리스트 구현 (ArrayList 2차원 배열)
        ArrayList<Integer>[] graph = new ArrayList[n + 1];

        for(int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < wires.length; i++) {
            int start = wires[i][0];
            int end = wires[i][1];

            graph[start].add(end);
            graph[end].add(start);
        }

        for(int i = 0; i < wires.length; i++) {
            //제거된 간선
            int targetStart = wires[i][0];
            int targetEnd = wires[i][1];

            //BFS or DFS 수행 >> 다만 targetStart일 때 targetENd로 가는 건 금지
            //어떤 노드를 방문했는지
            boolean[] visited = new boolean[n+1];
            visited[0] = true; //노드는 1부터 시작하기 떄문에 visited[0]은 항상 true로 고정 (안쓰는 값)

            Queue<Integer> queue = new LinkedList<>();
            int count = 0;
            //BFS 수행

            queue.offer(1); //1번 노드부터 진행
            visited[1] = true;

            while(!queue.isEmpty()) {
                int node = queue.poll();
                count++;

                for(int nextNode : graph[node]) {
                    if((node == targetStart && nextNode == targetEnd) || (node == targetEnd && nextNode == targetStart)) { //양방향이기 때문에 반대의 경우도 조건 필요
                        continue;
                    } else {
                        if(!visited[nextNode]) { //다음 갈 노드가 방문하지 않은 거라면?
                            visited[nextNode] = true; //방문했다고 세팅
                            queue.offer(nextNode); //Queue에 넣어준다.
                        }
                    }
                }
            }
            answer = Math.min(answer,Math.abs(count - (n - count)));
        }

        return answer;
    }
}


/*
하나의 전선을 끊었을 때 최대한 두 전력망을 비슷하게 맞추기 위함

>> 송전탑 개수 n, 전선 정보 wires
두 전력망이 가지고 있는 송전탑 개수의 차이를 return (두 송전탑의 차이는 최솟값이 되어야한다.)

1. 그래프를 구성한다.
2. 끊었을 때 한 쪽의 개수를 체크한다 (어차피 다른 한 쪽의 개수는 n에서 뺀 만큼이다.)
3. Math.min(answer, 한쪽의 개수 - n - (한쪽의 개수));

인접리스트를 통해서 graph를 만드는 방법에 대해 학습하며, 조건 중 양방향이라는 점, node가 1부터 시작한다는 점들을 유의하여,
visited 배열과 연결점이 끊어졌다는 target을 삼아서 진행하여, 해당 문제를 해결했다.

Queue를 사용해서 BFS 알고리즘을 통해 Node를 이동과 동시에 방문했다는 기록을 작성하여, 개수를 측정한다.

그리고 나뉘어진 그래프를 각각 탐색하기보다는 Node의 개수와 한 쪽 연결 그래프의 개수 차이를 이용하여 다른 반대편을 구하고
나온 결과의 차이의 절댓값을 통해서 최소의 값을 구한다.
*/