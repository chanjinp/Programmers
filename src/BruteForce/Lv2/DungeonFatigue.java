package BruteForce.Lv2;

public class DungeonFatigue {

    static int answer = 0;
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeon = {{80,20},{50,40},{30,10}};

        System.out.println(solution(k, dungeon));
    }

    public static int solution(int k, int[][] dungeons) {

        boolean[] visited = new boolean[dungeons.length];

        dfs(k, dungeons, visited, 0);

        return answer;
    }

    public static void dfs(int k, int[][] dungeons, boolean[] visited, int count) {
        answer = Math.max(answer, count);

        for(int i=0; i<dungeons.length; i++){
            if(dungeons[i][0] <= k && !visited[i]){ //최소 피로도를 만족하며, 던전 방문을 안했을 경우
                visited[i] = true;
                dfs(k - dungeons[i][1], dungeons, visited, count + 1);
                visited[i] = false; //상태 복원 필수!
            }
        }
    }
}

/*
게임 피로도 시스템

- 각 던전마다 시작하기 위한 최소의 피로도
- 던전을 마쳤을 때 소모 피로도

각 던전별 최소 피로도, 소모 피로도가 담긴 2차원 배열을 줄 때 유저가 탐험할 수 있는 최대 던전 수를 return하라.

K는 현재 피로도를 나타내며 1이상 5000이하이다.
던전 배열의 가로(열) 길이는 2이다. >> [][0]: 최소 필요 피로도 [][1]: 소모 피로도

DFS를 구현하는 방식에 익숙해지면 쉽게 풀릴 문제 >> DFS를 통해 끝점까지 조건에 만족할 때까지 진행하고, 마무리 되는대로 백트레킹을 통해 다른 경우의 수를 더하며 하나씩 찾아간다.
저번 문제에서는 Stack을 활용해서 풀었기 떄문에, 이번에는 재귀 방식으로 풀이 진행

재귀 방식으로 풀었으니, 추후 문제 반복 간 Stack으로 풀이 필요

재귀에서의 핵심은 visited 배열을 계속 공유하기 때문에 다소 편한 점이 있지만, 직관적이지는 않음 >> 스택 메모리를 사용하는 것이기 때문에, 구조상 같게 동작할지 몰라도
실제 Stack 자료구조의 특징을 보면서 사용하지 않기 때문에 직관성은 부족하다고 볼 수 있다.

다만 실무에서 재귀함수로 사용하는 것을 선호할 거 같다. 메모리 공간이야 서버의 입장에서 생각했을 때 크게 지장가지 않을 정도의 데이터라고 가정을 할 것이고, 코드가 Stack보다
줄어들며, 은근 실무에서 import나 불필요한 import 참조 등의 문제로 유지보수 혹은 에러 문제로 인해 발생할 수 있는 문제점들이 더 손해가 크다.

물론 크게 지장갈 것은 아니지만, 가독성으로도 매우 좋다는 점에서 추후 코드 관리에 매우 강점을 보인다는 것이 가장 큰 특징
*/
