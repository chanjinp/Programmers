package BruteForce.Lv2;

import java.util.*;

public class AdditionalServer {
    public static void main(String[] args) {
        int[] players = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m = 3;
        int k = 5;

        System.out.println(solution(players, m, k));
    }

    public static int solution(int[] players, int m, int k) { //증설한 서버는 k 시간만큼 진행
        int answer = 0;
        List<Server> list = new ArrayList<>();

        for (int i = 0; i < players.length; i++) {

            // 1. 오픈 시간이 지난 서버 먼저 반납하기
            Iterator<Server> it = list.iterator();
            while (it.hasNext()) {
                Server s = it.next();
                s.minusOpenTime();
                if (s.isClose()) {
                    it.remove(); // k시간이 지나 반납할 서버 제거
                }
            }

            // 2. 현재 시간에 필요한 추가 서버 개수 계산
            int player = players[i];
            int neededServers = player / m; // 필요한 총 증설 최소 서버 수

            // 3. 현재 가동 중인 증설 서버가 부족하다면 추가 증설
            if (neededServers > list.size()) {
                int addCount = neededServers - list.size();
                answer += addCount;

                // 부족한 수만큼 신규 서버 추가 (k시간 동안 운영)
                for (int c = 0; c < addCount; c++) {
                    list.add(new Server(k));
                }
            }
        }
        return answer;
    }

    static class Server {
        private int openTime;

        public Server(int k) {
            this.openTime = k;
        }

        public boolean isClose() {
            return this.openTime <= 0;
        }

        public void minusOpenTime() {
            this.openTime--;
        }
    }
}
/*
같은 시간대에 게임을 이용하려는 사람이 M명 늘어날 때마다 서버 1대가 추가로 필요
어느 시간대의 이용자가 M명 미만일 경우 서버 증설 필요 x

어느 시간대의 이용자가 N x M명 이상 (N+1) x M 미만일 경우 n대의 증설된 서버가 운영 중이어야한다.

한 번 증설한 서버는 k시간동안 운영하고 그 이후에 반납

즉, 서버를 증설할 경우 각 서버당 운영하는 시간이 존재하며, 각 시각은 1시간 단위로 시뮬레이션을 진행한다.
그리고 증설 조건은 M명이 늘어 날 때마다 서버 1대가 추가로 필요하며, 증설된 서버의 최소 개수는 nxm <= 증설된 서버 개수 < (n+1) x m

그렇다면 관리되어야할 건 총 3가지

1. 시간에 따른 증설된 서버 개수
2. 사용자 플레이어
3. 상황에 따른 서버 증설 횟수 카운팅

서버를 증설하기 전, 만료된 서버를 찾아서 우선 거른 후 최소 필요한 서버의 수를 가지고 부족한 만큼 서버 증설하며 증설 횟수 카운팅

아래는 피드백을 받은 내용이며, 시뮬레이션 해봐야 하루 기준이기 때문에 정적 배열로 size가 24인 서버 기록 배열을 가지며, 서버 개수를 계산해서 구하는 로직
클래스 구성을 진행하지 않아도 되는 장점과 코드가 간결해지는 장점이 있다.

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] addedServers = new int[24]; // 시간대별 증설한 서버 대수 기록

        for (int i = 0; i < players.length; i++) {
            // 1. 현재 시간(i) 기준, 최근 k시간 동안 증설되어 아직 가동 중인 서버 대수 계산
            int currentActiveServers = 0;
            int startHour = Math.max(0, i - k + 1);
            for (int h = startHour; h < i; h++) {
                currentActiveServers += addedServers[h];
            }

            // 2. 현재 시간(i)에 필요한 추가 서버 대수
            int neededServers = players[i] / m;

            // 3. 부족한 만큼 서버 증설
            if (neededServers > currentActiveServers) {
                int needToAdd = neededServers - currentActiveServers;
                addedServers[i] = needToAdd; // i시에 증설한 대수 기록
                answer += needToAdd;        // 총 증설 횟수 누적
            }
        }

        return answer;
    }
}

*/
