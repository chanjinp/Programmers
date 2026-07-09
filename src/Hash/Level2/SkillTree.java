package Hash.Level2;

import java.util.*;
public class SkillTree {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

        System.out.println(solution(skill, skill_trees));
    }

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        Map<Character, Character> sMap = new HashMap<>();

        char[] arr = skill.toCharArray();

        for(int i = 0; i < arr.length; i++) {
            if(i != 0) {
                sMap.put(arr[i], arr[i-1]); //이전 배워야하는 스킬을 Value로 관리
            } else {
                sMap.put(arr[i], ' '); //처음 배우는 스킬은 이전에 배워야하는 스킬이 없음
            }
        }

        for(String s : skill_trees) {
            char[] charArray = s.toCharArray();
            Set<Character> mySkill = new HashSet<>();

            for (char sk : charArray) {
                if (sMap.containsKey(sk)) { //스킬 순서에 포함된 스킬이라면?
                    if (sMap.get(sk) == ' ') { //첫 번째 순서의 스킬이라면?
                        mySkill.add(sk); //바로 배운다.
                    } else {
                        if (mySkill.contains(sMap.get(sk))) { //내가 배운 항목에 배워야하는 스킬이 있다?
                            mySkill.add(sk);
                        } else {
                            break;
                        }
                    }
                } else { //그게 아니라면 그냥 배워도 되는 스킬
                    mySkill.add(sk);
                }
            }

            if(mySkill.size() == charArray.length) { //내가 배운 스킬이 스킬 항목의 개수와 같다면?
                answer++; //스킬을 전부 배웠다는 뜻이므로
            }
        }

        return answer;
    }
}
/*
스킬 배우는 순서가 있다 ex) 스파크 > 라이트닝 볼트 > 썬더 순으로 배워야한다.

순서에 없는 다른 스킬(힐링 등)은 순서에 상관 없이 배울 수 있다. 따라서 스파크 > 힐링 > 라이트닝 볼트 > 썬더와 같은 스킬 트리는 가능하지만,
썬더 > 스파크와 같이 스킬트리는 불가능하다.

선행 스킬 순서 skill과 유저들이 만든 스킬트리를 담은 배열 skill_trees가 매개 변수일 때 가능한 스킬트리 개수를 return하는 solution 함수를 작성해라.

1. skill을 char 배열로 만들어서 문자 하나씩 순서를 만들며, 각 값은 Map으로 관리한다.
- Map으로 관리하는 이유는 Key 값은 자기 자신, Value는 이전에 배워야하는 값을 채워서 관리하며, 관리하는 값이 필요 없을 경우 빈 char 값을 넣는다.

2. 스킬트리의 경우에도 하나의 원소에 대해서 char 배열로 구성하며, skill에 포함되는 내용인지 판단하고 만약 포함된다면? 해당 값의 Value 값이 이미
배웠는지를 판단한다. 만약 배우지 않았다면 break로 불가능한 스킬트리라고 판단한다.

2-1. 포함되지 않는다면, 아무때나 배울 수 있는 스킬로 통과한다.

3. 배운 스킬의 개수와 char로 만들었을 때의 길이가 같다면? 스킬트리를 배울 수 있는 것이므로 answer++ 진행

*/