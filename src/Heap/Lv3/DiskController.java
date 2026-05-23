package Heap.Lv3;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DiskController {
    public static void main(String[] args) {
//        int[][] jobs = {{0, 3}, {1, 9}, {3, 5}};
        int[][] jobs = {{1, 9}, {0, 3}, {3, 5}};

        System.out.println(solution(jobs));
    }

    public static int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        PriorityQueue<Job> queue = new PriorityQueue<>();
        int time = 0;
        int idx = 0; // 몇 번째 job까지 대기 큐에 고려했는지
        int count = 0; // 완료한 작업 수
        int answer = 0;

        while (count < jobs.length) {
            // 1. 현재 시점(time) 이전에 요청된 모든 작업을 큐에 추가
            while (idx < jobs.length && jobs[idx][0] <= time) {
                queue.offer(new Job(jobs[idx][1], jobs[idx][0], idx));
                idx++;
            }

            // 2. 큐가 비어있다면, 다음 요청이 올 때까지 시간 점프
            if (queue.isEmpty()) {
                time = jobs[idx][0];
            } else {
                // 3. 작업 수행
                Job job = queue.poll();
                time += job.jobTime;
                answer += (time - job.reqTime);
                count++;
            }
        }
        return answer / jobs.length;
    }

    static class Job implements Comparable<Job> {
        int jobTime;
        int reqTime;
        int jobNum;

        public Job(int jobTime, int reqTime, int jobNum) {
            this.jobTime = jobTime;
            this.reqTime = reqTime;
            this.jobNum = jobNum;
        }

        @Override
        public int compareTo(Job o) {
            if (this.jobTime != o.jobTime) {
                return this.jobTime - o.jobTime; // 1. 작업 시간 오름차순
            } else if (this.reqTime != o.reqTime) {
                return this.reqTime - o.reqTime; // 2. 요청 시각 오름차순
            } else {
                return this.jobNum - o.jobNum;   // 3. 작업 번호 오름차순
            }
        }
    }
}

/*
우선 순위 디스크 컨트롤러
1. 작업 번호, 작업 요청 시각, 작업의 소요 시간을 저장해두는 대기 큐가 있다 (처음엔 비어있음)
2. 대기큐가 비어있지 않다면, 가장 우선 순위가 높은 작업을 대기 큐에서 꺼내 하드에게 그 작업을 시킴
(이때, 작업의 소요 시간이 짧은 것, 작업의 요청 시각이 빠른 것, 작업의 번호가 작은 것으로 우선 순위가 높다.)
3. 하드는 작업이 시작하면 끝날 때까지 그 작업만 수행
4. 마치는 시점과 다른 작업이 들어오는 시점이 겹칠 경우, 디스크 컨트롤러에서 들어온 작업을 대기 큐에 저장한 뒤 우선순위가 높은 작업을 대기 큐에서 꺼내서 하드에 작업을 시킨다.

주요 포인트 1. 작업을 시작하면, 작업이 끝날 떄까지 하드는 주어진 일만한다 >> 작업이 끝났을 때의 시점 중요
주요 포인트 2. 작업의 소요 시간이 짧은 것, 작업의 요청 시각이 빠른 것, 작업의 번호가 작은 것으로 우선 순위 구분
(클래스 하나 만들어서 Comparator 정의 후 하는 것이 맞을까?)
주요 포인트 3. 시점을 중요하기 때문에, 시간 추적 필요 (시뮬)
*/
