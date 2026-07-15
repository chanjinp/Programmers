package StackAndQueue.Lv1;

import java.util.*;
public class Card {
    public static void main(String[] args) {
        String[] cards1 = {"i", "water", "drink"};
        String[] cards2 = {"want", "to"};
        String[] goal = {"i", "want", "to", "drink", "water"};

        System.out.println(solution(cards1, cards2, goal));
    }

    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        Queue<String> card1 = new LinkedList<>();
        Queue<String> card2 = new LinkedList<>();

        for(String c : cards1) card1.offer(c);
        for(String c : cards2) card2.offer(c);

        // 굳이 goalQ를 만들지 않고, 향상된 for문을 돌며 즉시 리턴하는 방식
        for(String g : goal) {
            String c1 = card1.isEmpty() ? "" : card1.peek();
            String c2 = card2.isEmpty() ? "" : card2.peek();

            if(g.equals(c1)) {
                card1.poll();
            } else if(g.equals(c2)){
                card2.poll();
            } else {
                // 중간에 하나라도 어긋나면 그 즉시 "No" 반환하고 종료
                return "No";
            }
        }

        // 중간에 튕겨 나가지 않고 goal을 끝까지 다 돌았다면 성공!
        return "Yes";
    }
}
/*
원하는 카드 뭉치에서 카드를 순서대로 한 장씩 사용
한 번 사용한 카드는 다시 사용 x
카드를 사용하지 않고 다음 카드로 넘어갈 수 없다.
기존 주어진 카드 뭉치의 단어 순서는 바꿀 수 없다.

각 카드를 Queue에 넣는다.
Queue에서 뽑을려고 할 때 goal을 만들 수 있는 문자가 있다면?

각 카드를 큐에 넣고 목표 글자를 만들었을 때,
*/
