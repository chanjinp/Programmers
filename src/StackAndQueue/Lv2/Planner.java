package StackAndQueue.Lv2;

import java.util.*;
public class Planner {
    public static void main(String[] args) {
        String[][] plans = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};

        for(String s : solution(plans)) {
            System.out.println(s);
        }
    }

    public static String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();

        // 1. 과제 리스트 생성 및 시작 시간 기준 오름차순 정렬
        List<Plan> planList = new ArrayList<>();
        for (String[] p : plans) {
            planList.add(new Plan(p[0], p[1], p[2]));
        }

        //정렬
        planList.sort(Comparator.comparingInt(p -> p.startTime));

        Stack<Plan> stopStack = new Stack<>(); // 잠시 멈춘 과제 보관용

        int currentTime = planList.get(0).startTime; // 현재 시간 흐름 tracking

        for (int i = 0; i < planList.size(); i++) {
            Plan current = planList.get(i);

            // 마지막 과제인 경우, 다음 과제가 없으므로 바로 처리 완료
            if (i == planList.size() - 1) {
                answer.add(current.name);
                break;
            }

            Plan next = planList.get(i + 1);

            // 현재 과제를 다음 과제 시작 전까지 다 끝낼 수 있는 경우
            if (current.startTime + current.playTime <= next.startTime) {
                answer.add(current.name);
                currentTime = current.startTime + current.playTime;

                // 다음 과제 시작 전까지 여유 시간이 있다면 멈춰둔 과제 털기
                while (!stopStack.isEmpty()) {
                    Plan stopped = stopStack.peek();

                    if (currentTime + stopped.playTime <= next.startTime) {
                        answer.add(stopStack.pop().name);
                        currentTime += stopped.playTime;
                    } else {
                        // 멈춘 과제를 다 끝내진 못하고 시간만 소요한 경우
                        stopped.playTime -= (next.startTime - currentTime);
                        break;
                    }
                }
            } else {
                // 현재 과제를 다 못 끝내고 중간에 멈춰야 하는 경우
                current.playTime -= (next.startTime - current.startTime);
                stopStack.push(current);
            }
        }

        // 남아있는 멈춘 과제들 역순으로 정산
        while (!stopStack.isEmpty()) {
            answer.add(stopStack.pop().name);
        }

        return answer.toArray(new String[0]);
    }

    // 분 단위 변환을 적용한 깔끔한 내부 클래스 설계
    public static class Plan {
        String name;
        int startTime; // 분(minute) 단위 정수로 통합
        int playTime;

        public Plan(String name, String time, String playTime) {
            this.name = name;
            String[] t = time.split(":");
            this.startTime = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
            this.playTime = Integer.parseInt(playTime);
        }
    }

    
}

/*
과제는 시작하기로 한 시각이 되면 시작
새로운 과제가 시작할 시각이 되었을 때, 기존 진행 중이던 과제가 있다면, 진행 중이던 과제를 멈추고! 과제 시작한다.
진행 중이던 과제를 끝냈을 때, 잠시 멈춘 과제가 있다면, 멈춰둔 과제를 이어서 진행한다.
만약, 과제를 끝낸 시각에 새로 시작 vs 잠시 멈춰둔 과제가 모두 있을 경우, 새로운 과제부터 진행
멈춰둔 과제가 여러 개일 경우, 가장 최근에 멈춘 과제부터 시작한다.

과제 계획을 담은 plans(2차원 배열)이 주어질 때, 과제를 끝낸 순서대로 이름을 배열에 담아 return해라.

1. 과제라는 클래스를 만들고 List<객체>에 각 데이터들을 담는다.
2. 우선 과제의 경우 시간을 기준으로 가장 먼저 오는 것부터 정렬을 해야한다 (시간 순 정렬)
3. 시뮬레이션의 경우 과제를 최초 시작한 순간을 기준으로 1씩 증가한다.

Case 1. 새로운 과제가 들어오는 경우
- 진행 중이던 과제는 Stack에 넣고 새로운 과제를 진행한다.

Case 2. 과제가 끝났을 때
Case 2-1. 과제가 끝났는데, 새로운 과제가 없을 경우
- Stack에 담아둔 잠시 멈춘 과제를 하나 꺼내서 수행한다.

Case 2-2. 과제가 끝났는데, 새로운 과제가 있을 경우
- 새로운 과제를 수행한다.

Case 3. 새로운 과제가 하나도 없는 경우
- 잠시 맡아두었던 과제들을 하나씩 수행한다. (Stack 털기)
 */