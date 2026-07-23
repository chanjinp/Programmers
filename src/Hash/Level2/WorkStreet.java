package Hash.Level2;

import java.util.*;
public class WorkStreet {
    public static void main(String[] args) {
        String cmd = "ULURRDLLU";

        System.out.println(solution(cmd));
    }

    //초기 좌표
    static int x = 0;
    static int y = 0;

    public static int solution(String dirs) {
        int answer = 0;

        //이미 지나온 길을 저장하는 자료구조
        Set<String> work = new HashSet<>();

        for(char d : dirs.toCharArray()) {
            String startPoint = x+""+y;

            if('U' == d) {
                y++;
            }

            if('D' == d) {
                y--;
            }

            if('R' == d) {
                x++;
            }

            if('L' == d) {
                x--;
            }

            if(checkOverRange(x, y)) {
                if(x > 5) {
                    x = 5;
                } else if(x < -5){
                    x = -5;
                }else if(y > 5) {
                    y = 5;
                } else if(y<-5) {
                    y = -5;
                }
                continue;
            } else {
                String endPoint = x+""+y;

                String w = startPoint+endPoint;
                String revW = endPoint+startPoint;
                if(!work.contains(w)) {
                    work.add(w);
                    work.add(revW);
                    answer++;
                }
            }
        }


        return answer;
    }

    public static boolean checkOverRange(int x, int y) {
        // X 좌표가 범위 밖이라면?
        if(x < -5 || x > 5) {
            return true;
        }

        // Y 좌표가 범위 밖이라면?
        if(y < -5 || y > 5) {
            return true;
        }

        return false;
    }
}

/*
처음 걸어본 길의 길이를 구하라.
최대 길이는 x y 각 -5 ~ 5까지 입니다.

이를 넘어가는 명령이 있을 경우 무시합니다.

핵심 포인트는 내가 갔던 길을 기록하는 것인데 어떻게 기록할 것인가?
출발 좌표와 도착 좌표를 저장하는 방식이 좋을까?
만약 저렇게 저장을 한다면? 모든 이동 간 자신의 위치와 다음 목표 위치를 저장해야하고 이동해야할 것.

의사코드는 아래와 같다. (단! 예외처리로는 명령에 의해 수행했을 때 거리를 벗어나면 카운트 x = 명령 무시)
1. 출발 좌표와 이동 후 목표 좌표를 각 구한 후
2. 이미 내가 가봤던 길인지 판단한다.
3. 가봤던 길이라면 count를 안한채로 이동
3-1. 가보지 못한 길이라면 count한채로 이동

어떤 자료구조를 통해서 출발 좌표와 목표 좌표를 저장할까?
Set<String>을 통해서 문자열로 출발지, 목적지를 붙여서 저장하되, 역방향으로도 한 번 더 저장하는 방식
*/
