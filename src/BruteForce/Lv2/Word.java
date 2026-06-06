package BruteForce.Lv2;

import java.util.ArrayList;
import java.util.Stack;

public class Word {
    public static void main(String[] args) {
        //CASE A
        String word = "AAAAE";

        System.out.println(solution(word));
    }

    public static int solution(String word) {
        String[] words = {"A", "E", "I", "O", "U"};

        Stack<String> stack = new Stack<>();
        ArrayList<String> dictionary = new ArrayList<>();

        for(int i = 4; i >= 0; i--){
            stack.push(words[i]);
        }

        while(!stack.isEmpty()){
            String w = stack.pop();
            dictionary.add(w);

            if(w.length() < 5) {
                for(int j = 4; j >= 0; j--) {
                    stack.push(w + words[j]);
                }
            }
        }

        return dictionary.indexOf(word) + 1; //+1인 이유는 사전의 경우 1부터 시작하기 때문
    }
}

/*
알파뱃 A E I O U 만을 사용하여 만들 수 있는 길이 5 이하의 모든 단어가 수록되어있을 때, 몇 번째 단어인지 return

일반적인 DFS로 진행할 경우 visited 배열 때문에 중복된 내용을 만들 수 없다. 즉, 문자열 5까지가 최대라는 제한 조건에 집중할 필요가 있다.

즉, 문자열을 쭉 구성하는 과정에서 문자열 길이가 5가 넘을 경우에는 더이상 문자열 구성을 멈춘다.

다만 DFS를 재귀로 구현했을 경우에는 함수 내부의 for문을 통해 index를 0부터 시작하므로 A부터 시작하여 문자를 구성할 수 있지만,
Stack으로 사용할 경우에는 A가 먼저 와야하므로, Stack에 A가 마지막에 올 수 있도록 설정한다.

indexOf를 할 경우 데이터의 길이만큼 탐색을 진행할텐데 문제가 없을까?
지금이야 문자열 5로 제한이 되어있기에 사소할 수 있지만, 문자열의 수를 장담할 수 없을 때에는 Map을 통해서 이미 만든 내용이 있는지를 검토하고 있을 경우 반환하는 방식으로 진행하는 것이
좋아보인다.
*/